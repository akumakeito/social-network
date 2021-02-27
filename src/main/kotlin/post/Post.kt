package post

import Attachment
import enums.PostTypeEnum

data class Post(
    val id : Int,
    val ownerId : Int,
    val date : Int = 0,
    val text : String = "",
    val fromId : Int = 0,
    val postType : PostTypeEnum,
    val postSource : PostSource,
    val attachment: Attachment? = null,
    val likes: Likes? = null,
    val createdBy : Int? = null,
    val replyOwnerId : Int? = null,
    val replyPostId : Int? = null,
    val friendsOnly : Boolean? = null,
    val comments : Comment? = null,
    val copyright : Copyright? = null,
    val reposts: Reposts? = null,
    val views: Views? = null,
    val signerId : Int? = null,
    val canPin : Boolean? = null,
    val canDelete : Boolean? = null,
    val canEdit : Boolean? = null,
    val isPinned: Boolean? = null,
    val markedAsAds : Boolean? = null,
    val isFavorite : Boolean? = null,
    val donut: Donut? = null,
    val postponedId : Int? = null,
    val geo: Geo? = null,
    val copyHistory : Array<String>? = null
)