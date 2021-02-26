package Post

import Enums.EditModeEnum

data class Donut(
    val isDonut: Boolean?,
    val paidDuration : Int,
    val placeholder : String?,
    val canPublishFreeCopy : Boolean,
    val editMode : EditModeEnum
)