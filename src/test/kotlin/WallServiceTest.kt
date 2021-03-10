import enums.PlatformEnum
import enums.PostSourceTypeEnum
import enums.PostTypeEnum
import post.Post
import post.PostSource
import org.junit.Assert.*
import org.junit.Test

class WallServiceTest {

    @Test
    fun add() {
        val post = Post(
            id = 0,
            ownerId = 1,
            date = 15022021,
            text = "Hi, Lily",
            postType = PostTypeEnum.POST,
            postSource = PostSource(PostSourceTypeEnum.API, PlatformEnum.ANDROID, "ntlg.com", null)
        )

        val result = WallService.add(post)

        assertTrue(result.id != 0)
    }

    @Test
    fun update_True() {
        val post1 = Post(
            id = 0,
            ownerId = 1,
            date = 15022021,
            text = "Hi, dad",
            postType = PostTypeEnum.POST,
            postSource = PostSource(PostSourceTypeEnum.API, PlatformEnum.ANDROID, "ntlg.com", null)
        )

        val post2 = Post(
            id = 0,
            ownerId = 2,
            date = 15022021,
            text = "Hi, mom",
            postType = PostTypeEnum.POST,
            postSource = PostSource(PostSourceTypeEnum.API, PlatformEnum.ANDROID, "ntlg.com", null)

        )
        WallService.add(post1)
        WallService.add(post2)

        assertTrue(
            WallService.removePost(
                Post(
                    id = 2,
                    ownerId = 2,
                    postType = PostTypeEnum.POST,
                    postSource = PostSource(PostSourceTypeEnum.API, PlatformEnum.ANDROID, "ntlg.com", null)
                )
            )
        )
    }

    @Test
    fun update_False() {
        val post1 = Post(
            id = 0,
            ownerId = 1,
            date = 15022021,
            text = "Hi, dad",
            postType = PostTypeEnum.POST,
            postSource = PostSource(PostSourceTypeEnum.API, PlatformEnum.ANDROID, "ntlg.com", null)

        )

        val post2 = Post(
            id = 0,
            ownerId = 2,
            date = 15022021,
            text = "Hi, mom",
            postType = PostTypeEnum.POST,
            postSource = PostSource(PostSourceTypeEnum.API, PlatformEnum.ANDROID, "ntlg.com", null)
        )
        WallService.add(post1)
        WallService.add(post2)

        assertFalse(
            WallService.removePost(
                Post(
                    id = 4,
                    ownerId = 2,
                    postType = PostTypeEnum.POST,
                    postSource = PostSource(PostSourceTypeEnum.API, PlatformEnum.ANDROID, "ntlg.com", null)
                )
            )
        )
    }

    @Test
    fun createComment_success() {
        val comment = Comment(1,1,1,22122, "first comment")
        val post = Post(
            id = 1,
            ownerId = 2,
            postType = PostTypeEnum.POST,
            postSource = PostSource(PostSourceTypeEnum.API, PlatformEnum.ANDROID, "ntlg.com", null)
        )
        WallService.add(post)
        WallService.createComment(comment)

        assertTrue(WallService.removeComment(comment))
    }

    @Test(expected = PostNotFoundException::class)
    fun createComment_shouldThrow() {
        val comment = Comment(1546,1,1,22122, "second comment")
        WallService.createComment(comment)


    }
}
