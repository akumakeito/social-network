package chat

import exceptions.ChatNotFoundException
import java.util.Collections.emptyList

object ChatService {
    private var chatId = 0L
    private var messageId = 0L
    private var chats: List<Chat> = emptyList()
    private var messages: List<Message> = emptyList()

    fun createMessage(authorId: UserId, text: String, chatId: Long = -1L): Message {
        var newMessage = Message(authorId, messageId++, chatId, text)

        if (chats.none { chat -> chat.id == chatId }) {
            val newChatId = this.chatId++
            newMessage = newMessage.copy(chatId = newChatId)
            val newChat = Chat(authorId, newChatId, listOf(newMessage))
            chats = chats + newChat
            return newMessage
        }

        chats = chats.map { chat ->
            if (chat.id == chatId) {
                chat.copy(messagesList = chat.messagesList + newMessage)
            } else {
                chat
            }
        }

        messages = messages + newMessage

        return newMessage
    }

    fun editMessage(messageId: MessageId, text: String) {
        chats = chats.map { chat ->
            chat.copy(
                messagesList = chat.messagesList.map { message ->
                    if (message.id == messageId) {
                        message.copy(text = text)
                    } else {
                        message
                    }
                }
            )
        }
    }

    fun removeMessage(messageId: MessageId)  {
        chats = chats.map { chat ->
            chat.copy(messagesList = chat.messagesList.filter { it.id != messageId })
        }

        chats = chats.filterNot {it.messagesList.isEmpty()}

    }

    fun getUnreadChatsCount(userId: UserId): Int {
        var unreadChatCount = 0
        val userChats = chats.filter { it.userId == userId }
        userChats.map { chat ->
            if (chat.messagesList.last().id > chat.readMessages[userId] ?: 0) {
                unreadChatCount++
            }
        }

        return unreadChatCount
    }

    fun getChats(userId: UserId): List<Chat> {
        return chats.filter { it.userId == userId }
    }

    fun getMessages(chatId: Long): List<Message> {
        return chats.filter { it.id == chatId }
            .map {
                it.messagesList
            }.flatten()
    }


    fun removeChat(chatId: Long) {
        chats = chats.filter { it.id != chatId }
        messages = messages.filter { it.chatId != chatId }
    }

    fun readMessages(userId: UserId, chatId: Long, messageId: MessageId) {
        val index = chats.indexOfFirst { chat -> chat.id == chatId }

        if (index == -1) {
            throw ChatNotFoundException("Чат не найден")
        }

        val targetChat = chats[index]
        val newReadMessages = targetChat.readMessages.toMutableMap()
            .apply {
                put(userId, messageId)
            }

        chats = chats.toMutableList().apply {
            set(index, targetChat.copy(readMessages = newReadMessages))
        }
    }

    fun getUnreadMessagesCount(userId: UserId, chatId: Long): Int {
        val chatIndex = chats.indexOfFirst { it.id == chatId }

        if (chatIndex == -1) {
            throw ChatNotFoundException("Чат не найден")
        }

        val targetChat = chats[chatIndex]
        val lastReadMessageId = targetChat.readMessages[userId]

        return targetChat.messagesList.takeWhile { it.id != lastReadMessageId }.size
    }

    fun clearData() {
        chatId = 0L
        messageId = 0L
        chats = emptyList()
        messages = emptyList()
    }
}