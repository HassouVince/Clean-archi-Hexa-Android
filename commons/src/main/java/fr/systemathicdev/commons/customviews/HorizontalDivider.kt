package fr.systemathicdev.commons.customviews

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import fr.systemathicdev.commons.R
import fr.systemathicdev.commons.databinding.HorizontalDividerBinding

class HorizontalDivider(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    enum class HeightType {
        STANDARD,
        LARGE,
        MEDIUM,
        SMALL
    }

    private val binding: HorizontalDividerBinding
    private val layoutInflater : LayoutInflater by lazy { LayoutInflater.from(context) }

    init {
        binding = HorizontalDividerBinding.inflate(layoutInflater,this,true)

        val a = context.obtainStyledAttributes(attrs, R.styleable.HorizontalDivider,0,0)
        val color: Drawable? = a.getDrawable(R.styleable.HorizontalDivider_dividerColor)
        val type = a.getInteger(R.styleable.HorizontalDivider_dividerType, 0)
            .let { HeightType.values()[it] }
        a.recycle()

        binding.view.background = color

        when (type){
            HeightType.STANDARD -> context.resources.getDimensionPixelSize(R.dimen.horizontal_divider_standard_height)
            HeightType.LARGE -> context.resources.getDimensionPixelSize(R.dimen.horizontal_divider_large_height)
            HeightType.MEDIUM -> context.resources.getDimensionPixelSize(R.dimen.horizontal_divider_medium_height)
            HeightType.SMALL -> context.resources.getDimensionPixelSize(R.dimen.horizontal_divider_small_height)
        }.let {
            binding.view.layoutParams.height = it
        }
    }
}