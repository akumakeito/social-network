data class User(
    val id : Long,
    val lastName : String,
    val firstName : String,
) {
    override fun toString(): String {
        return "$firstName $lastName"
    }
}
