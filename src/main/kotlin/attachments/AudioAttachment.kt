import Enums.AttachmentTypeEnum

class AudioAttachment (
    override val type: AttachmentTypeEnum = AttachmentTypeEnum.AUDIO,
    val attachment : Audio
) : Attachment