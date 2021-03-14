package posts.post

import posts.enums.EditModeEnum

data class Donut(
    val isDonut: Boolean?,
    val paidDuration : Int,
    val placeholder : String?,
    val canPublishFreeCopy : Boolean,
    val editMode : EditModeEnum
)