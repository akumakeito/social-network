interface CrudService<E, T> {
    fun add(entity: E)
    fun delete(id: Long) : Boolean
    fun edit(entity: E)
    fun read(): List<E>
    fun getById(id: Long): E
    fun addComment(entity : T)
    fun deleteComment(id : Long) : Boolean
    fun editComment(entity : T)
    fun readComment(eTypeEntityId : Long, sort : Int = 0) : List<T>
    fun restoreComment(entity: T) : Boolean
}