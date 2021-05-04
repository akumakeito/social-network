package posts.post

import Attachment
import enums.PostTypeEnum

data class Post(
    val id : Long,
    val ownerId : Long,
    val date : Long = 0,
    val text : String = "",
    val fromId : Long = 0,
    val postType : PostTypeEnum,
    val postSource : PostSource,
    val attachment: Attachment? = null,
    val likes: Likes? = null,
    val createdBy : Long? = null,
    val replyOwnerId : Long? = null,
    val replyPostId : Long? = null,
    val friendsOnly : Boolean? = null,
    val comments : Comments? = null,
    val copyright : Copyright? = null,
    val reposts: Reposts? = null,
    val views: Views? = null,
    val signerId : Long? = null,
    val canPin : Boolean? = null,
    val canDelete : Boolean? = null,
    val canEdit : Boolean? = null,
    val isPinned: Boolean? = null,
    val markedAsAds : Boolean? = null,
    val isFavorite : Boolean? = null,
    val donut: Donut? = null,
    val postponedId : Long? = null,
    val geo: Geo? = null,
    val copyHistory : Array<String>? = null
)