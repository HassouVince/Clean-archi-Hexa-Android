package fr.systemathicdev.commons.extensions

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import android.view.View.*

private const val SCALE_Y_ZERO = 0f
private const val SCALE_Y_ONE = 1f

fun View.setVisibility(
    shouldBeVisible: Boolean?,
    shouldSetInVisible: Boolean = false,
    shouldAnimate: Boolean = false,
){
    when {
        shouldBeVisible == true && shouldAnimate -> scaleToVisible()
        shouldBeVisible == true && !shouldAnimate -> visibility = VISIBLE
        shouldBeVisible == false && !shouldAnimate -> visibility = GONE
        shouldSetInVisible -> visibility = INVISIBLE
        shouldAnimate -> scaleToGone()
    }
}

fun View.scaleToVisible(){
    animate().scaleY(SCALE_Y_ONE)
        .setListener(object : AnimatorListenerAdapter(){
            override fun onAnimationStart(animation: Animator?) {
                visibility = GONE
            }

            override fun onAnimationEnd(animation: Animator?) {
                visibility = VISIBLE
            }
        })
}

fun View.scaleToGone(){
    animate().scaleY(SCALE_Y_ZERO)
        .setListener(object : AnimatorListenerAdapter(){
            override fun onAnimationStart(animation: Animator?) {
                visibility = VISIBLE
            }

            override fun onAnimationEnd(animation: Animator?) {
                visibility = GONE
            }
        })
}