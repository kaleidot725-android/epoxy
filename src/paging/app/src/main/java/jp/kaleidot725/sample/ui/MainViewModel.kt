package jp.kaleidot725.sample.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import jp.kaleidot725.sample.entity.EpoxyItemEntity
import jp.kaleidot725.sample.repository.EpoxyItemDataSourceFactory

class MainViewModel(factory: EpoxyItemDataSourceFactory) : ViewModel() {
    private val config = PagedList.Config.Builder().setInitialLoadSizeHint(10).setPageSize(10).build()
    val entities: LiveData<PagedList<EpoxyItemEntity>> = LivePagedListBuilder(factory, config).build()
}
