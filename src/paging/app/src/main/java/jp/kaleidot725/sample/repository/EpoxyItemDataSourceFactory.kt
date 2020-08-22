package jp.kaleidot725.sample.repository

import androidx.paging.DataSource
import jp.kaleidot725.sample.entity.EpoxyItemEntity


class EpoxyItemDataSourceFactory(repository: EpoxyItemRepository) : DataSource.Factory<Int, EpoxyItemEntity>() {
    private val source = EpoxyItemDataSource(repository)

    override fun create(): DataSource<Int, EpoxyItemEntity> {
        return source
    }
}
