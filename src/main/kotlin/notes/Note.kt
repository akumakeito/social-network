package notes
import java.time.LocalDateTime


data class Note(
    val id : Long,
    val title : String,
    val text : String,
    val date : Int, //LocalDateTime = LocalDateTime.now(),
    val comments : Long = 0,
    val readComments : Long = 0,
    val viewURL : String = "url",

) {
    override fun toString(): String {
        return "$title: $text ($id)"
    }
}

