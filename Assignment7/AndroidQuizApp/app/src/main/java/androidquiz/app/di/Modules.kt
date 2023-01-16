package androidquiz.app.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import androidquiz.app.data.database.AndroidQuizDatabase
import androidquiz.app.data.repository.AndroidQuizRepository
import androidquiz.app.data.repository.AndroidQuizRepositoryImpl
import androidquiz.app.ui.analysis.AnalysisViewModel
import androidquiz.app.ui.history.HistoryViewModel
import androidquiz.app.ui.home.HomeViewModel
import androidquiz.app.ui.play.PlayViewModel

val databaseModule = module {
    factory { get<AndroidQuizDatabase>().questionDao() }
    factory { get<AndroidQuizDatabase>().historyDao() }
}

val repositoryModule = module {
    single<AndroidQuizRepository> { AndroidQuizRepositoryImpl(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { PlayViewModel(get()) }
    viewModel { HistoryViewModel(get()) }
    viewModel { AnalysisViewModel(get()) }
}
