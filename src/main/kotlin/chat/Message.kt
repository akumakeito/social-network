package chat

data class Message(
    val authorId : Long,
    val id : Long,
    val chatId : Long,
    val text : String
    )
