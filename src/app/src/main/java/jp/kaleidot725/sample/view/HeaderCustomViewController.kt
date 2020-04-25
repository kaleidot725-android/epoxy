package jp.kaleidot725.sample.view

import com.airbnb.epoxy.Typed2EpoxyController

class HeaderCustomViewController : Typed2EpoxyController<List<String>, Boolean>() {

    override fun buildModels(names: List<String>, loadingMore: Boolean) {
        names.forEach {
            headerCustomView{
                id("header")
                title("My Name is ")
                description(it)
            }
        }
    }
}