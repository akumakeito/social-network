package post

import enums.PlatformEnum
import enums.PostSourceTypeEnum

data class PostSource(
    val type : PostSourceTypeEnum,
    val platform : PlatformEnum,
    val url : String,
    val data : String?
)
