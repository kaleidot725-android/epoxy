package jp.kaleidot725.sample.repository

import androidx.paging.PageKeyedDataSource
import jp.kaleidot725.sample.entity.EpoxyItemEntity

class EpoxyItemDataSource(
        private val repository: EpoxyItemRepository
) : PageKeyedDataSource<Int, EpoxyItemEntity>() {
    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, EpoxyItemEntity>) {}

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, EpoxyItemEntity>) {
        // 1 ページ目のデータを取得する
        val pageNo = 0

        // ページに表示するデータを取得する
        val items = repository.getPageItems(pageNo, params.requestedLoadSize)

        // 次に表示するページの番号を計算する
        val nextPage = pageNo + 1

        // 取得したデータ、次に表示するページの番号を結果として返す
        callback.onResult(items, null, nextPage)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, EpoxyItemEntity>) {
        // params.key には 前の loadInitial や loadAfter の呼び出しで返した nextPage が格納されている
        val page = params.key

        // ページに表示するデータを取得する
        val items = repository.getPageItems(page, params.requestedLoadSize)

        // 次に表示するページの番号を計算する
        val nextPage = page + 1

        // 取得したデータ、次に表示するページの番号を結果として返す
        callback.onResult(items, nextPage)
    }
}
