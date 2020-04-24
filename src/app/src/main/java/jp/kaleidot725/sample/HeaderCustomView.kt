package jp.kaleidot725.sample

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class HeaderCustomView : LinearLayout {
    val titleView: TextView

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    init {
        titleView = TextView(context, null)
        this.addView(titleView)
    }

    @TextProp
    fun setTitle(text: CharSequence?) {
        titleView.text = text
    }
}

