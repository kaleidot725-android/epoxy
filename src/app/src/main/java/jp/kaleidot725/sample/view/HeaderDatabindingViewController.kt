package jp.kaleidot725.sample.view

import com.airbnb.epoxy.Typed2EpoxyController
import jp.kaleidot725.sample.headerDataBindingLayout

class HeaderDatabindingViewController : Typed2EpoxyController<List<String>, Boolean>() {

    override fun buildModels(names: List<String>, loadingMore: Boolean) {
        names.forEach {
            headerDataBindingLayout {
                id("header")
                title("My name is ")
                description(it)
            }
        }
    }
}