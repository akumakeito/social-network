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
            likes = Likes(count = 3)
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
            likes = Likes(count = 3)
        )

        val post2 = Post(
            id = 0,
            ownerId = 2,
            date = 15022021,
            text = "Hi, mom",
            likes = Likes(count = 3)
        )
        WallService.add(post1)
        WallService.add(post2)

        assertTrue(WallService.remove(Post(2)))
    }

    @Test
    fun update_False() {
        val post1 = Post(
            id = 0,
            ownerId = 1,
            date = 15022021,
            text = "Hi, dad",
            likes = Likes(count = 3)
        )

        val post2 = Post(
            id = 0,
            ownerId = 2,
            date = 15022021,
            text = "Hi, mom",
            likes = Likes(count = 3)
        )
        WallService.add(post1)
        WallService.add(post2)

        assertFalse(WallService.remove(Post(4)))
    }
}