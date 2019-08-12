package com.mtd.utilscustomclass.animation

import android.view.View
import android.view.animation.Interpolator
import io.reactivex.Single

class UtilsAnimationViewOperator(private val interpolator: Interpolator) {


    fun fadeIn(view: View, duration: Long = 0, delayStart: Long = 0): Single<View> {
        return Single.create {
            view.animate()
                .alpha(1f)
                .setInterpolator(interpolator)
                .setDuration(duration)
                .setStartDelay(delayStart)
                .withEndAction {
                    it.onSuccess(view)
                }
        }
    }

    fun fadeOut(view: View, duration: Long = 0, delayStart: Long = 0): Single<View> {
        return Single.create {
            view.animate()
                .alpha(0f)
                .setInterpolator(interpolator)
                .setDuration(duration)
                .setStartDelay(delayStart)
                .withEndAction {
                    it.onSuccess(view)
                }
        }
    }

    fun scale(
        view: View,
        scaleX: Float = 1f,
        scaleY: Float = 1f,
        duration: Long = 0,
        delayStart: Long = 0
    ): Single<View> {
        return Single.create {
            view.animate()
                .scaleX(scaleX)
                .scaleY(scaleY)
                .setDuration(duration)
                .setInterpolator(interpolator)
                .setStartDelay(delayStart)
                .withEndAction {
                    it.onSuccess(view)
                }
        }
    }

    fun translateUpDown(
        view: View,
        translateY: Float,
        duration: Long = 0,
        delayStart: Long = 0
    ): Single<View> {
        return Single.create {
            view.animate()
                .translationY(translateY)
                .setDuration(duration)
                .setInterpolator(interpolator)
                .setStartDelay(delayStart)
                .withEndAction {
                    it.onSuccess(view)
                }
        }

    }


    fun translateLeftRight(
        view: View,
        translateX: Float,
        duration: Long = 0,
        delayStart: Long = 0
    ): Single<View> {
        return Single.create {
            view.animate()
                .translationX(translateX)
                .setDuration(duration)
                .setInterpolator(interpolator)
                .setStartDelay(delayStart)
                .withEndAction {
                    it.onSuccess(view)
                }
        }

    }


    fun rotate(
        view: View, rotate: Float,
        duration: Long = 0,
        delayStart: Long = 0
    ): Single<View> {
        return Single.create {
            view.animate()
                .rotation(rotate)
                .setDuration(duration)
                .setInterpolator(interpolator)
                .setStartDelay(delayStart)
                .withEndAction {
                    it.onSuccess(view)
                }
        }
    }

}