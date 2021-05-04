package chat

typealias UserId = Long
typealias MessageId = Long

data class Chat(
    val userId : UserId,
    val id : Long,
    var messagesList : List<Message>,
    val readMessages : Map<UserId, MessageId> = emptyMap()
)
