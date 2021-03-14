data class Audio (
    val id : Int,
    val ownerId : Int,
    val artist : String,
    val title : String,
    val duration : Int,
    val url : String,
    val lyricsId : Int? = null,
    val albumId : Int? = null,
    val genreId : Int,
    val date : Int,
    val noSearch : Boolean? = false,
    val isHQ : Boolean? = false
)
