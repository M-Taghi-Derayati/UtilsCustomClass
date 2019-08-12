package com.mtd.utilscustomclass.animation

import android.view.View
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.Interpolator
import android.view.animation.Transformation

class UtilsResizeAnimation(
    private var view: View,
    private var mFromHeight_Rel: Int,
    private var mToHeight_Rel: Int
) : Animation() {

    override fun applyTransformation(interpolatedTime: Float, transformation: Transformation) {
        val newHeight: Int

        if (view.height != mToHeight_Rel) {
            newHeight = (mFromHeight_Rel + (mToHeight_Rel - mFromHeight_Rel) * interpolatedTime).toInt()
            view.layoutParams.height = newHeight
            view.requestLayout()
        }
    }

    fun startAnimation(
        view: View,
        animation: UtilsResizeAnimation,
        interpolator: Interpolator = DecelerateInterpolator(),
        duration: Long = 0,
        startDelay: Long = 0

    ) {
        animation.duration = duration
        animation.interpolator = interpolator
        animation.startOffset = startDelay
        view.startAnimation(animation)
        view.animation = animation
    }

    fun setListenerAnimation(listener: AnimationListener? = null) {
        setAnimationListener(listener)
    }

    override fun willChangeBounds(): Boolean {
        return true
    }
}
