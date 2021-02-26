data class Photo (
    val id : Int,
    val ownerId : Int,
    val albumId : Int,
    val userId : Int?,
    val text : String?,
    val date : Int,
    val sizes : PhotoSizes,
    val width : Int?,
    val height : Int?
    )