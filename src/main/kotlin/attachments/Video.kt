import Post.Likes
import Post.Reposts
import javax.xml.stream.events.Comment

data class Video (
    val id : Int,
    val ownerId : Int,
    val title : String,
    val duration : Int,
    val description : String? = null,
    val image : VideoImage? = null,
    val firstFrame : VideoFirstFrame,
    val date : Int,
    val addingDate : Int,
    val views : Int = 0,
    val localViews : Int? = null,
    val comments : Int = 0,
    val player : String,
    val platform : String? = null,
    val canEdit : Boolean? = null,
    val canAdd : Boolean = true,
    val isPrivate : Boolean = true,
    val accessKey : String? = null,
    val processing : Boolean = true,
    val isFavorite : Boolean = false,
    val canComment : Boolean? = null,
    val canLike : Boolean? = null,
    val canRepost : Boolean? = null,
    val canSubscribe : Boolean? = null,
    val width : Int,
    val height : Int,
    val userId: Int,
    val likes: Likes,
    val reposts: Reposts
 )
