import exceptions.CommentLengthTooSmallException
import exceptions.CommentNotFoundException
import exceptions.NoteNotFoundException
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class NoteServiceTest {

    @Before
    fun clearBefore() {
        NoteService.clearData()
    }

    @After
    fun clearAfter() {
        NoteService.clearData()
    }

    @Test
    fun add() {
        val expected = Note(
            id = 1L,
            title = "Title",
            text = "text",
            date = 210421,
            comments = 0L
        )

        NoteService.add(expected)

        val result = NoteService.getById(1)

        assertEquals(expected, result)
    }

    @Test
    fun printNoteComments() {
        val note1 = Note(0L, "First note", "text", 210421, 0L, "", 0L)
        val note2 = Note(0L, "Second note", "text", 301585, 0L, "", 0L)
        val comment1 = Comment(1L, 0L,1L, 210422,"first note comment2" )
        val comment2 = Comment(2L, 0L,1L, 210422,"first note comment2" )
        NoteService.add(note1)
        NoteService.add(note2)
        NoteService.addComment(comment1)
        NoteService.addComment(comment2)

        val expected = "First note: text (1)\n" +
                "first note comment2\n" +
                "Second note: text2 (2)\n" +
                "second note comment\n"

        val result = NoteService.printNotesComments()

        assertEquals(expected, result)

    }

    @Test
    fun addComment_success() {
        val note1 = Note(0L, "First note", "text", 210421, 0L, "", 0L)
        val note2 = Note(0L, "Second note", "text", 301585, 0L, "", 0L)
        val comment1 = Comment(1L, 0L,1L, 210422,"first note comment2" )

        NoteService.add(note1)
        NoteService.add(note2)
        NoteService.addComment(comment1)

        val expected = Comment(1L, 1L, 1L,210422,"first note comment2", )

        val result = NoteService.getCommentById(1L)

        assertEquals(expected, result)

    }

    @Test
    fun addComment_noteCommentsIntIncrease() {
        val note1 = Note(0L, "First note", "text", 210421, 0L, "", 0L)
        val note2 = Note(0L, "Second note", "text", 301585, 0L, "", 0L)
        val comment1 = Comment(1L, 0L,1L, 210422,"first note comment2" )

        NoteService.add(note1)
        NoteService.add(note2)
        NoteService.addComment(comment1)

        val expected = 1L

        val result = NoteService.getById(1L).comments

        assertEquals(expected, result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun addComment_exception() {
        val note1 = Note(0L, "First note", "text", 210421, 0L, "", 0L)
        val note2 = Note(0L, "Second note", "text", 301585, 0L, "", 0L)
        val comment1 = Comment(23L, 0L,1L, 210422,"first note comment2" )

        NoteService.add(note1)
        NoteService.add(note2)
        NoteService.addComment(comment1)
    }

    @Test
    fun delete_success() {
        val note1 = Note(0L, "First note", "text", 210421, 0L, "", 0L)
        val note2 = Note(0L, "Second note", "text", 301585, 0L, "", 0L)

        NoteService.add(note1)
        NoteService.add(note2)

        val result = NoteService.delete(1L)

        assertTrue(result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun delete_exception() {
        val note1 = Note(0L, "First note", "text", 210421, 0L, "", 0L)
        val note2 = Note(0L, "Second note", "text", 301585, 0L, "", 0L)

        NoteService.add(note1)
        NoteService.add(note2)

        NoteService.delete(34L)
    }

    @Test
    fun deleteComment_success() {
        val note1 = Note(0L, "First note", "text", 210421, 0L, "", 0L)
        val comment1 = Comment(23L, 0L,1L, 210422,"first note comment2" )

        NoteService.add(note1)
        NoteService.addComment(comment1)

        assertTrue(NoteService.deleteComment(1L))
    }

    @Test(expected = CommentNotFoundException::class)
    fun deleteComment_exception() {
        val note1 = Note(0L, "First note", "text", 210421, 0L, "", 0L)
        val comment1 = Comment(23L, 0L,1L, 210422,"first note comment2" )

        NoteService.add(note1)
        NoteService.addComment(comment1)

        NoteService.deleteComment(2342L)
    }

    @Test
    fun edit_success() {
        val note1 = Note(0L, "First note", "text", 210421, 0L, "", 0L)
        val newNote1 = Note(1L, "new", "new text", 210421, 0L, "", 0L)

        NoteService.add(note1)


        val expected = Note(1L, "new", "new text", 210421, comments = 0)
        NoteService.edit(newNote1)

        val result = NoteService.getById(1L)

        assertEquals(expected, result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun edit_exception() {
        val note1 = Note(0L, "First note", "text", 210421, 0L, "", 0L)
        val newNote1 = Note(245L, "new", "new text", 210421, 0L, "", 0L)

        NoteService.add(note1)
        NoteService.edit(newNote1)
    }

    @Test
    fun editComment_success() {
        val note1 = Note(0L, "First note", "text", 210421, 0L, "", 0L)
        val note2 = Note(0L, "Second note", "text", 301585, 0L, "", 0L)
        val comment1 = Comment(1L, 0L,1L, 210422,"first note comment2" )
        val newComment1 = Comment(1L, 1L,1L, 210422,"new first note comment2" )

        NoteService.add(note1)
        NoteService.add(note2)
        NoteService.addComment(comment1)

        NoteService.editComment(newComment1)

        val expected = newComment1
        val result = NoteService.getCommentById(1L)

        assertEquals(expected, result)
    }

    @Test(expected = CommentNotFoundException::class)
    fun editComment_noComment() {
        val note1 = Note(0L, "First note", "text", 210421, 0L, "", 0L)
        val note2 = Note(0L, "Second note", "text", 301585, 0L, "", 0L)
        val comment1 = Comment(1L, 0L,1L, 210422,"first note comment2" )
        val newComment1 = Comment(234L, 1L,1L, 210422,"new first note comment2" )

        NoteService.add(note1)
        NoteService.add(note2)
        NoteService.addComment(comment1)

        NoteService.editComment(newComment1)
    }

    @Test(expected = CommentNotFoundException::class)
    fun editComment_commentDeleted() {
        val note1 = Note(0L, "First note", "text", 210421, 0L, "", 0L)
        val comment1 = Comment(1L, 0L,1L, 210422,"first note comment2")

        NoteService.add(note1)
        NoteService.addComment(comment1)

        NoteService.deleteComment(1L)

        NoteService.editComment(NoteService.getCommentById(1L))
    }

    @Test(expected = CommentLengthTooSmallException::class)
    fun editComment_commentTooSmall() {
        val note1 = Note(0L, "First note", "text", 210421, 0L, "", 0L)
        val comment1 = Comment(1L, 0L,1L, 210422,"first note comment2")
        val newComment1 = Comment(1L, 0L,1L, 210422,"f")

        NoteService.add(note1)
        NoteService.addComment(comment1)

        NoteService.editComment(newComment1)
    }

    @Test
    fun get_successDefaultSort() {
        val note1 = Note(0L, "First note", "text", 210421, 0L, "", 0L)
        val note2 = Note(0L, "First note", "text", 301585, 0L, "", 0L)
        val note3 = Note(0L, "First note", "text", 210420, 0L, "", 0L)
        val note4 = Note(0L, "First note", "text", 123456, 0L, "", 0L)

        NoteService.add(note1)
        NoteService.add(note2)
        NoteService.add(note3)
        NoteService.add(note4)


        val expected = mutableListOf(note1, note3)
        val result = NoteService.get(arrayListOf(1L, 3L))

        assertEquals(expected, result)

    }

    @Test
    fun get_successDescendingSort() {
        val note1 = Note(0L, "First note", "text", 210421, 0L, "", 0L)
        val note2 = Note(0L, "First note", "text", 301585, 0L, "", 0L)
        val note3 = Note(0L, "First note", "text", 210420, 0L, "", 0L)
        val note4 = Note(0L, "First note", "text", 123456, 0L, "", 0L)

        NoteService.add(note1)
        NoteService.add(note2)
        NoteService.add(note3)
        NoteService.add(note4)

        val expected = mutableListOf(note3, note1)
        val result = NoteService.get(arrayListOf(1L, 3L), 1)

        assertEquals(expected, result)

    }

    @Test(expected = NoteNotFoundException::class)
    fun get_exception() {
        val note1 = Note(0L, "First note", "text", 210421, 0L, "", 0L)
        val note2 = Note(0L, "First note", "text", 301585, 0L, "", 0L)

        NoteService.add(note1)
        NoteService.add(note2)

        NoteService.get(arrayListOf(1L, 3L))
    }

    @Test
    fun getById_success() {
        val note1 = Note(0L, "First note", "text", 210421, 0L, "", 0L)
        val note2 = Note(0L, "First note", "text", 301585, 0L, "", 0L)

        NoteService.add(note1)
        NoteService.add(note2)

        val expected = Note(1L, "First note", "text", 210421, 0L, "", 0L)
        val result = NoteService.getById(1L)

        assertEquals(expected, result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun getById_exception() {
        val note1 = Note(0L, "First note", "text", 210421, 0L, "", 0L)
        val note2 = Note(0L, "First note", "text", 301585, 0L, "", 0L)

        NoteService.add(note1)
        NoteService.add(note2)

        NoteService.getById(15L)
    }

    @Test
    fun readComment_successDefaultSort() {
        val note1 = Note(0L, "First note", "text", 210421, 0L, "", 0L)
        val note2 = Note(0L, "First note", "text", 301585, 0L, "", 0L)
        val comment1 = Comment(1L, 0L,1L, 210423,"first note comment2")
        val comment2 = Comment(2L, 0L,1L, 210421,"first note comment2")
        val comment3 = Comment(1L, 0L,1L, 210424,"first note comment2")
        val comment4 = Comment(2L, 0L,1L, 210420,"first note comment2")

        NoteService.add(note1)
        NoteService.add(note2)

        NoteService.addComment(comment1)
        NoteService.addComment(comment2)
        NoteService.addComment(comment3)
        NoteService.addComment(comment4)


        val expected = mutableListOf(comment1, comment3)
        val result = NoteService.readComment(1L)

        assertEquals(expected, result)

    }

    @Test
    fun readComment_successDescendingSort() {
        val note1 = Note(0L, "First note", "text", 210421, 0L, "", 0L)
        val note2 = Note(0L, "First note", "text", 301585, 0L, "", 0L)
        val comment1 = Comment(1L, 0L,1L, 210423,"first note comment2")
        val comment2 = Comment(2L, 0L,1L, 210421,"first note comment2")
        val comment3 = Comment(1L, 0L,1L, 210424,"first note comment2")
        val comment4 = Comment(2L, 0L,1L, 210420,"first note comment2")

        NoteService.add(note1)
        NoteService.add(note2)

        NoteService.addComment(comment1)
        NoteService.addComment(comment2)
        NoteService.addComment(comment3)
        NoteService.addComment(comment4)


        val expected = mutableListOf(comment3, comment1)
        val result = NoteService.readComment(1L, 1)

        assertEquals(expected, result)

    }

    @Test(expected = NoteNotFoundException::class)
    fun readComment_exception() {
        val note1 = Note(0L, "First note", "text", 210421, 0L, "", 0L)
        val note2 = Note(0L, "First note", "text", 301585, 0L, "", 0L)
        val comment1 = Comment(1L, 0L,1L, 210423,"first note comment2")
        val comment2 = Comment(2L, 0L,1L, 210421,"first note comment2")
        val comment3 = Comment(1L, 0L,1L, 210424,"first note comment2")
        val comment4 = Comment(2L, 0L,1L, 210420,"first note comment2")

        NoteService.add(note1)
        NoteService.add(note2)

        NoteService.addComment(comment1)
        NoteService.addComment(comment2)
        NoteService.addComment(comment3)
        NoteService.addComment(comment4)

        NoteService.readComment(5L)
    }

    @Test
    fun restoreComment_success() {
        val note1 = Note(0L, "First note", "text", 210421, 0L, "", 0L)
        val comment1 = Comment(1L, 0L,1L, 210423,"first note comment2")
        val comment2 = Comment(2L, 0L,1L, 210421,"first note comment2")

        NoteService.add(note1)
        NoteService.addComment(comment1)
        NoteService.addComment(comment2)

        NoteService.deleteComment(1L)

        assertTrue(NoteService.restoreComment(NoteService.getCommentById(1L)))
    }

    @Test(expected = CommentNotFoundException::class)
    fun restoreComment_commentDoesntExist() {
        val note1 = Note(0L, "First note", "text", 210421, 0L, "", 0L)
        val comment1 = Comment(1L, 0L,1L, 210423,"first note comment2")
        val comment2 = Comment(2L, 0L,1L, 210421,"first note comment2")

        NoteService.add(note1)
        NoteService.addComment(comment1)
        NoteService.addComment(comment2)

        NoteService.restoreComment(Comment(2L, 24L,1L, 210421,"first note comment2"))
    }

    @Test(expected = CommentNotFoundException::class)
    fun restoreComment_commentDoesntDeleted() {
        val note1 = Note(0L, "First note", "text", 210421, 0L, "", 0L)
        val comment1 = Comment(1L, 0L,1L, 210423,"first note comment2")
        val comment2 = Comment(2L, 0L,1L, 210421,"first note comment2")

        NoteService.add(note1)
        NoteService.addComment(comment1)
        NoteService.addComment(comment2)

        NoteService.restoreComment(Comment(2L, 1L,1L, 210421,"first note comment2"))
    }


}
