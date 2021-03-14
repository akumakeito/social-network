package posts.post

import posts.enums.PlatformEnum
import posts.enums.PostSourceTypeEnum

data class PostSource(
    val type : PostSourceTypeEnum,
    val platform : PlatformEnum,
    val url : String,
    val data : String?
)