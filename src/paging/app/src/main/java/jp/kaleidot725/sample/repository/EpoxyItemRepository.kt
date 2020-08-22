package jp.kaleidot725.sample.repository

import android.util.Log
import jp.kaleidot725.sample.entity.EpoxyItemEntity

class EpoxyItemRepository {
    private val values: List<EpoxyItemEntity> = createSampleValues()

    fun getPageItems(pageNo: Int, pageSize: Int) : List<EpoxyItemEntity> {
        val count = values.count()
        val from = pageNo * pageSize
        val to = from + pageSize

        if (count < from || count < to) {
            return emptyList()
        }

        return values.subList(from, to)
    }

    private fun createSampleValues() : List<EpoxyItemEntity> {
        return mutableListOf<EpoxyItemEntity>().apply {
            for (i in 1..1000) this.add(EpoxyItemEntity(i.toString()))
        }
    }
}
