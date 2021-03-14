interface CrudCommentService<T> {
    fun addComment(entity : T)
    fun deleteComment(id : Long) : Boolean
    fun editComment(entity : T)
    fun readComment(eTypeEntityId : Long, sort : Int = 0) : List<T>
    fun restoreComment(entity: T) : Boolean
}