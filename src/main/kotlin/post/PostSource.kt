package Post

import Enums.PlatformEnum
import Enums.PostSourceTypeEnum

data class PostSource(
    val type : PostSourceTypeEnum,
    val platform : PlatformEnum,
    val url : String,
    val data : String?
)