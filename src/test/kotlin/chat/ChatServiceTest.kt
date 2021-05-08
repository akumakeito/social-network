package chat

import exceptions.ChatNotFoundException
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import javax.jws.soap.SOAPBinding

internal class ChatServiceTest {

    @Before
    fun clearBefore() {
        ChatService.clearData()
    }

    @Test
    fun createMessage_newChat() {
        val authorId = 13L
        val text = "text text"
        val chatId = 0L
        val message = Message(authorId, 0L, 0L, text)
        val expected = listOf(message)

        ChatService.createMessage(authorId, text)
        val result = ChatService.getMessages(chatId)

        assertEquals(expected, result)
    }

    @Test
    fun createMessage_existChat() {
        val authorId = 13L
        val text = "text text"
        val chatId = 0L
        val message1 = Message(authorId, 0L, chatId, text)
        val message2 = Message(authorId, 1L, chatId, text)
        val expected = listOf(message1, message2)

        ChatService.createMessage(authorId, text)
        ChatService.createMessage(authorId, text, chatId)

        val result = ChatService.getMessages(chatId)
        assertEquals(expected, result)
    }

    @Test
    fun editMessage() {
        val authorId = 13L
        val text = "text text"
        val chatId = 0L
        val newText = "new text message"
        val expected = Message(authorId, 2L, chatId, newText)

        ChatService.createMessage(authorId, text)

        repeat(2) {
            ChatService.createMessage(authorId, text, chatId)
        }

        ChatService.editMessage(2, newText)
        val chat = ChatService.getChats(authorId)[0]

        val result = chat.messagesList[2]

        assertEquals(expected, result)
    }

    @Test
    fun removeMessage() {
        val authorId = 13L
        val text = "text text"
        val chatId = 0L
        val expected = listOf(
            Message(authorId, 0L, chatId, text),
            Message(authorId, 2L, chatId, text)
        )

        ChatService.createMessage(authorId, text)

        repeat(2) {
            ChatService.createMessage(authorId, text, chatId)
        }

        ChatService.removeMessage(1L)

        val result = ChatService.getMessages(chatId)

        assertEquals(expected, result)
    }

    @Test
    fun removeMessage_removeChat() {
        val authorId = 13L
        val text = "text text"
        val message = Message(authorId, 1L, 1L, text)
        val expected = listOf(Chat(authorId,1L, listOf(message)))

        repeat(2) {
            ChatService.createMessage(authorId, text)
        }

        ChatService.removeMessage(0L)

        val result = ChatService.getChats(authorId)

        assertEquals(expected, result)
    }



    @Test
    fun getUnreadChatsCount() {
        val authorId = 13L
        val text = "text text"
        val expected = 2

        repeat(3) {
            ChatService.createMessage(authorId, text)
        }

        ChatService.readMessages(13L, 0L, 0L)

        val result = ChatService.getUnreadChatsCount(authorId)

        assertEquals(expected, result)
    }

    @Test
    fun getUnreadChatsCount_zero() {
        val authorId = 13L
        val text = "text text"
        val expected = 0

        repeat(3) {
            ChatService.createMessage(authorId, text)
        }

        ChatService.readMessages(13L, 0L, 0L)
        ChatService.readMessages(13L, 1L, 1L)
        ChatService.readMessages(13L, 2L, 2L)

        val result = ChatService.getUnreadChatsCount(authorId)

        assertEquals(expected, result)
    }

    @Test
    fun getChats() {
        val authorId = 13L
        val text = "text text"
        val message1 = Message(authorId, 0L, 0L, text)
        val message2 = Message(authorId, 1L, 1L, text)
        val expected = listOf(
            Chat(authorId,0L, listOf(message1)),
            Chat(authorId,1L, listOf(message2))
        )

        repeat(2) {
            ChatService.createMessage(authorId, text)
        }

        val result = ChatService.getChats(authorId)

        assertEquals(expected, result)
    }

    @Test
    fun getMessages() {
        val authorId = 13L
        val text = "text text"
        val chatId = 0L
        val message1 = Message(authorId, 0L, 0L, text)
        val message2 = Message(authorId, 1L, 0L, text)
        val message3 = Message(authorId, 2L, 0L, text)
        val expected = listOf(
            message1,
            message2,
            message3
        )

        ChatService.createMessage(authorId, text)

        repeat(2) {
            ChatService.createMessage(authorId, text, chatId)
        }

        val result = ChatService.getMessages(chatId)

        assertEquals(expected, result)
    }

    @Test
    fun getChats_zero() {
        val authorId = 13L
        val expected = emptyList<Chat>()
        val result = ChatService.getChats(authorId)

        assertEquals(expected, result)
    }

    @Test
    fun removeChat() {
        val authorId = 13L
        val text = "text text"
        val message1 = Message(authorId, 0L, 0L, text)
        val message2 = Message(authorId, 1L, 1L, text)
        val expected = listOf(
            Chat(authorId,0L, listOf(message1)),
            Chat(authorId,1L, listOf(message2))
        )

        repeat(3) {
            ChatService.createMessage(authorId, text)
        }

        ChatService.removeChat(2L)

        val result = ChatService.getChats(authorId)

        assertEquals(expected, result)
    }

    @Test
    fun removeChat_removeChatMessages() {
        val authorId = 13L
        val text = "text text"
        val expected = emptyList<Message>()

        repeat(3) {
            ChatService.createMessage(authorId, text)
        }
        repeat(3) {
            ChatService.createMessage(authorId, text, 2L)
        }

        ChatService.removeChat(2L)

        val result = ChatService.getMessages(authorId)

        assertEquals(expected, result)
    }

    @Test
    fun readMessages() {
        val authorId = 13L
        val chatId = 0L
        val messageId = 3L
        val text = "text text"
        val expected = mapOf<UserId, MessageId>(authorId to messageId)

        ChatService.createMessage(authorId, text)
        repeat(5) {
            ChatService.createMessage(authorId, text, chatId)
        }

        ChatService.readMessages(13L, 0L, messageId)

        val result = ChatService.getChats(authorId)[0].readMessages

        assertEquals(expected, result)
    }

    @Test(expected = ChatNotFoundException::class)
    fun readMessages_exception() {
        val authorId = 13L
        val chatId = 0L
        val messageId = 3L
        val text = "text text"

        ChatService.createMessage(authorId, text)
        repeat(5) {
            ChatService.createMessage(authorId, text, chatId)
        }

        ChatService.readMessages(13L, 3L, messageId)
    }

    @Test
    fun getUnreadMessagesCount() {
        val authorId1 = 13L
        val authorId2 = 11L
        val chatId = 0L
        val text = "text text"
        val expected = 10

        ChatService.createMessage(authorId1, text)

        repeat(10) {
            ChatService.createMessage(authorId1, text, chatId)
            ChatService.createMessage(authorId2, text, chatId)
        }

        ChatService.readMessages(13L, 0L, 10L)

        val result = ChatService.getUnreadMessagesCount(authorId1, chatId)

        assertEquals(expected, result)
    }

    @Test(expected = ChatNotFoundException::class)
    fun getUnreadMessagesCount_exception() {
        val authorId1 = 13L
        val authorId2 = 11L
        val chatId = 0L
        val text = "text text"

        ChatService.createMessage(authorId1, text)

        repeat(10) {
            ChatService.createMessage(authorId1, text, chatId)
            ChatService.createMessage(authorId2, text, chatId)
        }

        ChatService.readMessages(13L, 1L, 10L)

    }
}