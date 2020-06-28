package com.clarmoph.clarmophmovies.epoxy.models

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.ColorInt
import com.airbnb.epoxy.AfterPropsSet
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.clarmoph.clarmophmovies.R

@ModelView(autoLayout = ModelView.Size.WRAP_WIDTH_WRAP_HEIGHT)
class CarouselItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private val textView: TextView

    init {
        inflate(context, R.layout.carousel_item_view, this)
        orientation = VERTICAL
        textView = (findViewById(R.id.anime_title))
        textView.compoundDrawablePadding = (4 * resources.displayMetrics.density).toInt()
    }

    @TextProp
    lateinit var title: CharSequence

    @AfterPropsSet
    fun useProps() {
        textView.text = title
    }
}