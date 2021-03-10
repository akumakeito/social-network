data class Comment(
    val postId : Int,
    val id : Int,
    val fromId : Int,
    val date : Int,
    val text : String,
    val replyToUser : Int? = null,
    val replyToComment : Int? = null,
    val attachment: Attachment?= null,
    val parentsStack : ArrayList<Comment>? = null,
    val thread : ThreadComments? = null
)
