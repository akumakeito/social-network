import exceptions.PostNotFoundException
import org.junit.After
import posts.WallService
import enums.PlatformEnum
import enums.PostSourceTypeEnum
import enums.PostTypeEnum
import posts.post.Post
import posts.post.PostSource
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class WallServiceTest {

    @Before
    fun clearBefore() {
        WallService.clearData()
    }

    @After
    fun clearAfter() {
        WallService.clearData()
    }

    @Test
    fun add() {
        val post = Post(
            id = 0L,
            ownerId = 1L,
            date = 15022021,
            text = "Hi, Lily",
            postType = PostTypeEnum.POST,
            postSource = PostSource(PostSourceTypeEnum.API, PlatformEnum.ANDROID, "ntlg.com", null)
        )
        WallService.add(post)
        val expected = Post(
            id = 1L,
            ownerId = 1L,
            date = 15022021,
            text = "Hi, Lily",
            postType = PostTypeEnum.POST,
            postSource = PostSource(PostSourceTypeEnum.API, PlatformEnum.ANDROID, "ntlg.com", null))

        val result = WallService.getById(1)

        assertEquals(expected, result)
    }

    @Test
    fun edit_True() {
        val post1 = Post(
            id = 0L,
            ownerId = 1L,
            date = 15022021,
            text = "Hi, dad",
            postType = PostTypeEnum.POST,
            postSource = PostSource(PostSourceTypeEnum.API, PlatformEnum.ANDROID, "ntlg.com", null)
        )

        val post2 = Post(
            id = 0L,
            ownerId = 2L,
            date = 15022021,
            text = "Hi, mom",
            postType = PostTypeEnum.POST,
            postSource = PostSource(PostSourceTypeEnum.API, PlatformEnum.ANDROID, "ntlg.com", null)

        )
        WallService.add(post1)
        WallService.add(post2)

        assertTrue(
            WallService.delete(1L)
        )
    }

    @Test(expected = PostNotFoundException::class)
    fun edit_False() {
        val post1 = Post(
            id = 1L,
            ownerId = 1L,
            date = 15022021,
            text = "Hi, dad",
            postType = PostTypeEnum.POST,
            postSource = PostSource(PostSourceTypeEnum.API, PlatformEnum.ANDROID, "ntlg.com", null)

        )

        val post2 = Post(
            id = 2L,
            ownerId = 2L,
            date = 15022021,
            text = "Hi, mom",
            postType = PostTypeEnum.POST,
            postSource = PostSource(PostSourceTypeEnum.API, PlatformEnum.ANDROID, "ntlg.com", null)
        )
        WallService.add(post1)
        WallService.add(post2)

        WallService.delete(25L)
    }

    @Test
    fun addComment_success() {
        val comment = Comment(1,1,1,22122, "first comment")
        val post = Post(
            id = 1L,
            ownerId = 2L,
            postType = PostTypeEnum.POST,
            postSource = PostSource(PostSourceTypeEnum.API, PlatformEnum.ANDROID, "ntlg.com", null)
        )
        WallService.add(post)
        WallService.addComment(comment)

        assertTrue(WallService.delete(1L))
    }

    @Test(expected = PostNotFoundException::class)
    fun createComment_shouldThrow() {
        val comment = Comment(1546,1,1,22122, "second comment")
        WallService.addComment(comment)


    }
}
