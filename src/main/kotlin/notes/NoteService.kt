import exceptions.CommentLengthTooSmallException
import exceptions.CommentNotFoundException
import exceptions.NoteNotFoundException

object NoteService : CrudService<Note>, CrudCommentService<Comment> {
    var noteId = 0L
    var commentId = 0L
    val noteMap = mutableMapOf<Long, Note?>()
    val commentMap = mutableMapOf<Long, Comment?>()


    override fun add(entity: Note) {
        noteId++
        val newNote = entity.copy(id = noteId)
        noteMap[noteId] = newNote

    }

    override fun delete(id: Long): Boolean {
        try {
            noteMap.getValue(id)
        } catch (e: NoSuchElementException) {
            throw NoteNotFoundException("Заметка с id $id не найдена")
        }

        noteMap.remove(id)
        return true

    }

    override fun edit(entity: Note) {
        noteMap[entity.id] = noteMap[entity.id]?.copy(
            title = entity.title,
            text = entity.text
        ) ?: throw NoteNotFoundException("Заметка с id ${entity.id}] не найдена")

    }

    override fun read(): List<Note> {
        val noteList = mutableListOf<Note>()
        noteMap.values.forEach {
            it ?: throw NoteNotFoundException("Заметка не найдена")
            noteList.add(it)
        }
        return noteList
    }

    override fun getById(id: Long): Note {
        noteMap[id] ?: throw NoteNotFoundException("Заметка с id $id не найдена")
        return noteMap.getValue(id)!!
    }

    override fun addComment(entity: Comment) {
        noteMap[entity.entityId] ?: throw NoteNotFoundException("Заметка с id ${entity.entityId} не найдена")

        commentId++
        val note = noteMap.getValue(entity.entityId)
        noteMap[entity.entityId] = note!!.copy(comments = note.comments + 1)
        commentMap[commentId] = entity
    }

    override fun deleteComment(id: Long): Boolean {
        commentMap[id] ?: throw CommentNotFoundException("Комментарий с id $id не существует")
        commentMap[id] = commentMap.getValue(id)?.copy(delete = true)
        val noteId = commentMap.getValue(id)!!.entityId
        noteMap[noteId] = noteMap.getValue(noteId)!!.copy(comments = noteMap.getValue(noteId)!!.comments - 1)
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
        noteMap[eTypeEntityId] ?: throw NoteNotFoundException("Заметка с id $eTypeEntityId не найдена")
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


    private fun printComments(noteId: Long): String {
        val sb = buildString {
            commentMap.values.forEach() {
                if (it?.entityId == noteId && !it.delete) {
                    append("$it\n")
                }
            }
        }

        return sb.toString()
    }

    fun printNotesComments(): String {
        val sb = buildString {
            noteMap.values.forEach() {
                append("$it\n")
                if (it != null) {
                    append(printComments(it.id))
                }
            }
        }

        return sb.toString()
    }

    fun get(noteIds: ArrayList<Long>, sort: Int = 0): MutableList<Note> {
        val noteList = mutableListOf<Note>()
        noteIds.forEach {
            noteMap[it] ?: throw NoteNotFoundException("Заметка с id $it не найдена")
            val note = noteMap.getValue(it)
            noteList.add(note!!)
        }

        when (sort) {
            1 -> noteList.sortBy { it.date }
            0 -> noteList.sortedByDescending { it.date }
        }

        return noteList
    }

    fun getCommentById(id: Long): Comment {
        commentMap[id] ?: throw CommentNotFoundException("Комментарий с id $id не найден")
        return commentMap.getValue(id)!!
    }


    fun clearData() {
        noteId = 0
        commentId = 0
        noteMap.clear()
        commentMap.clear()
    }
}