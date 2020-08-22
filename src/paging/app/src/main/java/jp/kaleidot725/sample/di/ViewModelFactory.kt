package jp.kaleidot725.sample.di

import jp.kaleidot725.sample.repository.EpoxyItemDataSourceFactory
import jp.kaleidot725.sample.repository.EpoxyItemRepository
import jp.kaleidot725.sample.ui.MainViewModel

object ViewModelFactory {
    val mainViewModel: MainViewModel get() = MainViewModel(EpoxyItemDataSourceFactory(EpoxyItemRepository()))
}
