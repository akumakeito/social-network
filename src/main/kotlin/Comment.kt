import posts.ThreadComments

data class Comment(
    val entityId : Long,
    val id : Long,
    val fromId : Long,
    val date : Int,
    val text : String,
    val delete : Boolean = false,
    val replyToUser : Long? = null,
    val replyToComment : Long? = null,
    val attachment: Attachment?= null,
    val parentsStack : ArrayList<Comment>? = null,
    val thread : ThreadComments? = null
) {
    override fun toString(): String {
        return text
    }
}
