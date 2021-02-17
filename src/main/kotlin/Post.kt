import java.util.*

data class Post(
    val id : Int,
    val ownerId : Int = 0,
    val date : Int = 0,
    val text : String = "",
    val likes: Likes = Likes(),
//    val fromId : Int,
//    val createdBy : Int,
//    val replyOwnerId : Int,
//    val replyPostId : Int,
//    val friendsOnly : Boolean,
//    val comments : Comment,
//    val copyright : Copyright,
//    val reposts: Reposts,
//    val views: Views,
//    val postType : String,
//    val signerId : Int,
//    val canPin : Boolean,
//    val canDelete : Boolean,
//    val canEdit : Boolean,
//    val isPinned: Boolean,
//    val markedAsAds : Boolean,
//    val isFavorite : Boolean,
//    val donut: Donut,
//    val postponedId : Int
)