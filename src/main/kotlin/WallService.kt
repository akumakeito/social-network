import post.Post

object WallService {
    private var posts = ArrayList<Post>()
    private var comments = ArrayList<Comment>()
    private var previousId = 0

    fun add(post: Post) : Post {
        val newPost = post.copy(id = previousId + 1)
        posts.add(newPost)
        previousId++
        return posts.last()
    }

    fun update(post : Post) : Boolean {
        for((index, currentPost) in posts.withIndex()) {
            if (post.id == currentPost.id) {
                posts[index] = post.copy(ownerId = currentPost.ownerId, date = currentPost.date)
                return true
            }
        }

        return false
    }

    fun removePost(post : Post) : Boolean {
        for(currentPost in posts) {
            if (post.id == currentPost.id) {
                posts.remove(post)
                return true
            }
        }

        return false
    }

    fun removeComment(comment: Comment) : Boolean {
        for(currentComment in comments) {
            if (comment.id == currentComment.id) {
                comments.remove(comment)
                return true
            }
        }

        return false
    }

    fun findPostById(postId : Int): Post? {
        for(post in posts) {
            if (postId == post.id) {
                return post
            }
        }
        return null
    }

    fun createComment(comment : Comment) {
        findPostById(comment.postId) ?: throw PostNotFoundException("Пост с id ${comment.postId} не найден")
        comments.add(comment)
    }

    fun returnPosts() : ArrayList<Post> {
        return posts
    }
}