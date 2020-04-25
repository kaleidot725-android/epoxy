package jp.kaleidot725.sample.view

import com.airbnb.epoxy.Typed2EpoxyController
import jp.kaleidot725.sample.headerDataBindingLayout

class HeaderDatabindingViewController : Typed2EpoxyController<List<String>, Boolean>() {

    override fun buildModels(photos: List<String>, loadingMore: Boolean) {
        headerDataBindingLayout {
            id("header")
            title("My Photos")
            description("My album description!")
        }
    }
}