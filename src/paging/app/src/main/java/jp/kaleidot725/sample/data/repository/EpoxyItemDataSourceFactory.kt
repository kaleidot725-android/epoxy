package jp.kaleidot725.sample.data.repository

import androidx.paging.DataSource
import jp.kaleidot725.sample.data.entity.Item
import jp.kaleidot725.sample.data.service.QiitaService


class EpoxyItemDataSourceFactory(service: QiitaService) : DataSource.Factory<Int, Item>() {
    private val source = EpoxyItemDataSource(service)

    override fun create(): DataSource<Int, Item> {
        return source
    }
}
