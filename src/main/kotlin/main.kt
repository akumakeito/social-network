fun main() {
    val firstPost = Post(
        0,
        1,
        120221,
        "Hola, Sinora",
        likes = Likes()
    )

    val secondPost = Post(
        0,
        2,
        120221,
        "Hello, madam",
        likes = Likes()
    )

    val thirdPost = Post(
        0,
        3,
        140221,
        "Hi, meow",
        likes = Likes()
    )

    println(WallService.add(firstPost))
    println(WallService.add(secondPost))
    println(WallService.add(thirdPost))


    val updatePost = Post(2,3,4,"Hi", Likes())

    WallService.update(updatePost)

}