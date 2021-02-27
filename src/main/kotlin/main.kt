import enums.PlatformEnum
import enums.PostSourceTypeEnum
import enums.PostTypeEnum
import post.Post
import post.PostSource

fun main() {
    val firstPost = Post(
        0,
        1,
        120221,
        "Hola, Sinora",
        postType = PostTypeEnum.POST,
        postSource = PostSource(PostSourceTypeEnum.API, PlatformEnum.ANDROID, "ntlg.com", null)
    )

    val secondPost = Post(
        0,
        2,
        120221,
        "Hello, madam",
        postType = PostTypeEnum.POST,
        postSource = PostSource(PostSourceTypeEnum.API, PlatformEnum.ANDROID, "ntlg.com", null)
    )

    val thirdPost = Post(
        0,
        3,
        140221,
        "Hi, meow",
        postType = PostTypeEnum.POST,
        postSource = PostSource(PostSourceTypeEnum.API, PlatformEnum.ANDROID, "ntlg.com", null)
    )

    println(WallService.add(firstPost))
    println(WallService.add(secondPost))
    println(WallService.add(thirdPost))


    val updatePost = Post(2,3,4,"Hi",postType = PostTypeEnum.POST,
        postSource = PostSource(PostSourceTypeEnum.API, PlatformEnum.ANDROID, "ntlg.com", null)
    )

    WallService.update(updatePost)

}