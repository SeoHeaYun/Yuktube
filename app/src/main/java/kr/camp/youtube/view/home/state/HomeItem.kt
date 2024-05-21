package kr.camp.youtube.view.home.state

sealed interface HomeItem {

    val thumbnailUrl: String
    val description: String

    // HomeVideoAdapter
    data class categoryPopularItem(
        override val thumbnailUrl: String,
        override val description: String,
        val videoTitle: String
    ) : HomeItem

    // HomePopularAdapter
    data class mostPopularItem(
        override val thumbnailUrl: String,
        override val description: String,
        val videoTitle: String
    ) : HomeItem


}