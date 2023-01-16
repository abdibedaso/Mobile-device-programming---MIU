package androidquiz.app.util.loading

data class Loading(
    val loadingID: Int = 0,
    val loadingState: LoadingState = LoadingState.IDLE
)