import Post.Post

object WallService {
    private var posts = ArrayList<Post>()
    private var previousId = 0

    fun add(post: Post) : Post {
        val newPost = post.copy(id = previousId + 1)
        posts.add(newPost)
        previousId++
        return posts.last()
    }

    fun update(post : Post) : Boolean {
        for((index, currentPost) in posts.withIndex()) {
            if (post.id == currentPost.id) {
                posts[index] = post.copy(ownerId = currentPost.ownerId, date = currentPost.date)
                return true
            }
        }

        return false
    }

    fun remove(post : Post) : Boolean {
        for((index, currentPost) in posts.withIndex()) {
            if (post.id == currentPost.id) {
                posts.remove(post)
                return true
            }
        }

        return false
    }

    fun returnPosts() : ArrayList<Post> {
        return posts
    }
}