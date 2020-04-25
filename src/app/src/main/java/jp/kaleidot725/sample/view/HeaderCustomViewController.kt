package jp.kaleidot725.sample.view

import com.airbnb.epoxy.Typed2EpoxyController

class HeaderCustomViewController : Typed2EpoxyController<List<String>, Boolean>() {

    override fun buildModels(photos: List<String>, loadingMore: Boolean) {
        headerCustomView{
            id("header")
            title("My Photos")
            description("My album description!")
        }
    }
}