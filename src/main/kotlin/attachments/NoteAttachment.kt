import enums.AttachmentTypeEnum

data class NoteAttachment(
    override val type: AttachmentTypeEnum = AttachmentTypeEnum.NOTE,
    val attachment: Note
) : Attachment
