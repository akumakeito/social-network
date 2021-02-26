import Enums.AttachmentTypeEnum

class VideoAttachment (
    override val type: AttachmentTypeEnum = AttachmentTypeEnum.VIDEO,
    val attachment: Video
) : Attachment