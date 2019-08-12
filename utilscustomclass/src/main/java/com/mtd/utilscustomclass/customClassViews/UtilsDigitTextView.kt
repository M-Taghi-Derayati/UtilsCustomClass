package com.mtd.utilscustomclass.customClassViews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.interpolator.view.animation.FastOutLinearInInterpolator
import com.mtd.utilscustomclass.R
import com.mtd.utilscustomclass.animation.UtilsAnimationViewOperator
import java.util.*

class UtilsDigitTextView : FrameLayout {

    private val ANIMATION_DURATION = 50L
    private lateinit var currentTextView: UtilsTextView
    private lateinit var nextTextView: UtilsTextView
    private lateinit var rootFrameLayout: FrameLayout
    private val operator = UtilsAnimationViewOperator(FastOutLinearInInterpolator())

    constructor(mContext: Context, attr: AttributeSet) : super(mContext, attr) {
        init(mContext)
    }

    constructor(mContext: Context) : super(mContext) {
        init(mContext)
    }

    private fun init(mContext: Context) {
        val rootLayout = LayoutInflater.from(mContext).inflate(R.layout.digit_text_view, this)
        currentTextView = rootLayout.findViewById(R.id.currentTextView)
        nextTextView = rootLayout.findViewById(R.id.nextTextView)
        rootFrameLayout = rootLayout.findViewById(R.id.rootFrameLayout)
        nextTextView.translationY = height.toFloat()

        setValue(0)
    }

    fun setValue(desiredValue: Int) {
        if (currentTextView.text == null || currentTextView.text.isEmpty()) {
            currentTextView.text = String.format(Locale.getDefault(), "%d", desiredValue)
        }
        val oldValue = Integer.parseInt(currentTextView.text.toString())
        if (oldValue > desiredValue) {
            nextTextView.text = String.format(Locale.getDefault(), "%d", oldValue - 1)

            operator.translateUpDown(currentTextView, (-height).toFloat(), ANIMATION_DURATION)
                .subscribe()
            nextTextView.translationY = nextTextView.height.toFloat()

            operator.translateUpDown(nextTextView, 0f, ANIMATION_DURATION)
                .doAfterSuccess {
                    currentTextView.text = String.format(Locale.getDefault(), "%d", oldValue - 1)
                    currentTextView.translationY = 0f
                    if (oldValue - 1 != desiredValue) {
                        setValue(desiredValue)
                    }
                }
                .subscribe()

        } else if (oldValue < desiredValue) {
            nextTextView.text = String.format(Locale.getDefault(), "%d", oldValue + 1)
            operator.translateUpDown(currentTextView, height.toFloat(), ANIMATION_DURATION)
                .subscribe()
            nextTextView.translationY = (-nextTextView.height).toFloat()

            operator.translateUpDown(nextTextView, 0f, ANIMATION_DURATION)
                .doAfterSuccess {
                    currentTextView.text = String.format(Locale.getDefault(), "%d", oldValue + 1)
                    currentTextView.translationY = 0f
                    if (oldValue + 1 != desiredValue) {
                        setValue(desiredValue)
                    }
                }
                .subscribe()

        }
    }

    fun setBackgroundDigit(context: Context, background: Int) {
        rootFrameLayout.background = ContextCompat.getDrawable(context, background)
    }
}