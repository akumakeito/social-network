import posts.enums.AttachmentTypeEnum

data class DocAttachment(
    override val type: AttachmentTypeEnum = AttachmentTypeEnum.DOC,
    val attachment: Doc
) : Attachment
