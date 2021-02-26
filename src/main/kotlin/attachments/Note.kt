data class Note(
    val id : Int,
    val ownerId : Int,
    val title : String,
    val text : String,
    val date : Int,
    val comments : Int,
    val readComments : Int? = null,
    val viewUrl : String? = null
)
