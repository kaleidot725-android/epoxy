package jp.kaleidot725.sample.epoxy

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import jp.kaleidot725.sample.LayoutEpoxyItemBindingModel_
import jp.kaleidot725.sample.entity.EpoxyItemEntity

class ItemPagedListController : PagedListEpoxyController<EpoxyItemEntity>() {
    override fun buildItemModel(currentPosition: Int, item: EpoxyItemEntity?): EpoxyModel<*> {
        return LayoutEpoxyItemBindingModel_().apply {
            id(item!!.title)
            title(item.title)
        }
    }
}
