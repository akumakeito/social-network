data class Note(
    val id: Long,
    val title: String,
    val text: String,
    val date: Int,
    val readComments: Long? = null,
    val viewUrl: String? = null,
    val comments: Long
)
