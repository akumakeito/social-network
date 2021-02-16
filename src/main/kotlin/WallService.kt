object WallService {
    private var posts = emptyArray<Post>()
    private var previousId = 0

    fun add(post: Post) : Post {
        val newPost = post.copy(id = previousId + 1)
        posts += newPost
        previousId++
        return posts.last()
    }

    fun updat(post : Post) : Boolean {
        for((index, currentPost) in posts.withIndex()) {
            if (post.id == currentPost.id) {
                posts[index] = post.copy(ownerId = currentPost.ownerId, date = currentPost.date)
                return true
            }
        }

        return false
    }
}