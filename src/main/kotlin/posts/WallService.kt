package posts
import Comment
import CrudCommentService
import CrudService
import exceptions.CommentLengthTooSmallException
import exceptions.CommentNotFoundException
import exceptions.PostNotFoundException
import posts.post.Post

object WallService : CrudService<Post>, CrudCommentService<Comment>{
    var postId = 0L
    var commentId = 0L
    val postMap = mutableMapOf<Long, Post?>()
    val commentMap = mutableMapOf<Long, Comment?>()

    override fun add(entity: Post) {
        postId++
        val newPost = entity.copy(id = postId)
        postMap[postId] = newPost
    }

    override fun edit(entity : Post) {
        postMap[entity.id] ?: throw PostNotFoundException("Пост с id ${entity.id} не найден")
        postMap[entity.id] = entity.copy(ownerId = postMap[entity.id]!!.ownerId, date = postMap[entity.id]!!.date)
    }

    override fun delete(id: Long) : Boolean {
        postMap[id] ?: throw PostNotFoundException("Пост с id $id не найден")

        postMap.remove(id)
        return true
    }

    override fun read(): List<Post> {
        val postList = mutableListOf<Post>()
        postMap.values.forEach {
            it ?: throw PostNotFoundException("Пост не найдена")
            postList.add(it)
        }
        return postList
    }

    override fun getById(id: Long): Post {
        postMap[id] ?: throw PostNotFoundException("Пост с id $id не найден")
        return postMap.getValue(id)!!
    }

    override fun addComment(entity: Comment) {
        postMap[entity.entityId] ?: throw PostNotFoundException("Пост с id ${entity.entityId} не найден")

        commentId++
        commentMap[commentId] = entity
    }

    override fun deleteComment(id: Long): Boolean {
        commentMap[id] ?: throw CommentNotFoundException("Комментарий с id $id не существует")
        commentMap[id] = commentMap.getValue(id)?.copy(delete = true)

        return true
    }

    override fun editComment(entity: Comment) {
        commentMap[entity.id] ?: throw CommentNotFoundException("Комментарий с id ${entity.id} не существует")
        if (commentMap[entity.id]!!.delete) {
            throw CommentNotFoundException("Комментарий с id ${entity.id} удален")
        }
        if (entity.text.length < 2) {
            throw CommentLengthTooSmallException("Минимальная длина комментария - 2 символа")
        }

        commentMap[commentId] = commentMap[commentId]!!.copy(text = entity.text)
    }

    override fun restoreComment(entity: Comment): Boolean {
        commentMap[entity.id] ?: throw CommentNotFoundException("Комментарий с id ${entity.id} не существует")

        val comment = commentMap.getValue(entity.id)
        if (!comment!!.delete) {
            throw CommentNotFoundException("Комментарий с id ${entity.id} не удален")
        }

        commentMap[entity.id] = comment.copy(delete = false)
        return true
    }

    override fun readComment(eTypeEntityId: Long, sort: Int): MutableList<Comment> {
        postMap[eTypeEntityId] ?: throw PostNotFoundException("Пост с id $eTypeEntityId не найден")
        val commentList = mutableListOf<Comment>()
        commentMap.values.forEach {
            if (it?.entityId == eTypeEntityId && !it.delete) {
                commentList.add(it)
            }
        }

        when (sort) {
            1 -> commentList.sortBy { it.date }
            0 -> commentList.sortedByDescending { it.date }
        }

        return commentList
    }

    fun clearData() {
        postId = 0
        commentId = 0
        postMap.clear()
        commentMap.clear()
    }


}