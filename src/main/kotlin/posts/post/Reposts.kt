package posts.post

data class Reposts(
    val count : Int = 0,
    val wallCount : Int? = null,
    val mailCount : Int? = null,
    val userReposted : Boolean?
)