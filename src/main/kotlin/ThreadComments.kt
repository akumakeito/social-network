data class ThreadComments(
    val count : Int = 0,
    val items : ArrayList<Comment>? = null,
    val canPost : Boolean,
    val showReplyButton : Boolean? = null,
    val groupsCanPost : Boolean? = null
)
