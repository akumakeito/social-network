import chat.Chat
import exceptions.UserNotFoundException


object UserService : CrudService<User> {
    var userId = 0L
    val userMap = mutableMapOf<Long, User?>()

    override fun add(entity: User) {
        userId++
        val newUser = entity.copy(id = userId)
        userMap[userId] = newUser

    }

    override fun delete(id: Long): Boolean {
        userMap[id] ?: throw UserNotFoundException("Пользователь ${userMap.getValue(id)} не найден")

        userMap.remove(id)
        return true

    }

    override fun edit(entity: User) {
        userMap[entity.id] = userMap[entity.id]?.copy(
            lastName = entity.lastName,
            firstName = entity.firstName
        ) ?: throw UserNotFoundException("Пользователь ${userMap.getValue(entity.id)} не найден")

    }

    override fun read(): List<User> {
        val userList = mutableListOf<User>()
        userMap.values.forEach {
            it ?: throw UserNotFoundException("Пользователь не найден")
            userList.add(it)
        }
        return userList
    }

    override fun getById(id: Long): User {
        userMap[id] ?: throw UserNotFoundException("Пользователь ${userMap.getValue(id)} не найден")
        return userMap.getValue(id)!!
    }



    fun getUsersMap() : MutableMap<Long, User?> {
        return userMap
    }
}