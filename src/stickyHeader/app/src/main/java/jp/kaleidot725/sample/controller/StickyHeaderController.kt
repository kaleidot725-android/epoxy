package jp.kaleidot725.sample.controller

import com.airbnb.epoxy.Typed2EpoxyController
import jp.kaleidot725.sample.HeaderLayoutBindingModel_
import jp.kaleidot725.sample.contentLayout
import jp.kaleidot725.sample.headerLayout
import jp.kaleidot725.sample.model.Content
import jp.kaleidot725.sample.model.Header

class StickyHeaderController : Typed2EpoxyController<List<Header>, List<Content>>() {
    override fun buildModels(headers: List<Header>, contents: List<Content>) {
        headers.forEach { header ->
            headerLayout {
                id(header.uuid)
                title(header.value)
            }

            contents.forEach { content ->
                contentLayout {
                    id(content.uuid)
                    title(content.value)
                }
            }
        }
    }

    override fun isStickyHeader(position: Int): Boolean {
        return adapter.getModelAtPosition(position)::class == HeaderLayoutBindingModel_::class
    }
}
