package cl.gerardomascayano.drinksapp.core.extension

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.core.animation.addListener


fun View.visible(withAnimation: Boolean = false, duration: Long = 300) {
    if (withAnimation) {
        ObjectAnimator.ofFloat(this, "alpha", 0f, 1f)
            .setDuration(duration)
            .start()
    }
    if (this.visibility != View.VISIBLE) this.visibility = View.VISIBLE
}

fun View.gone(withAnimation: Boolean = false) {
    if (withAnimation) {
        val animator = ObjectAnimator.ofFloat(this, "alpha", 1f, 0f)
            .setDuration(300)

        animator.addListener(onEnd = {
            if (this.visibility != View.GONE) this.visibility = View.GONE
        })
        animator.start()

    } else {
        if (this.visibility != View.GONE) this.visibility = View.GONE
    }
}

fun View.invisible() {
    if (this.visibility != View.INVISIBLE) this.visibility = View.INVISIBLE
}

fun View.isVisible(): Boolean = this.visibility == View.VISIBLE

//fun View.changePropertyWithAnim(property: String, fromValue: Int, toValue: Int, duration: Long = 300) {
//    ObjectAnimator.ofFloat(this, property, fromValue.toFloat(), toValue.toFloat())
//        .setDuration(duration)
//        .start()
//}

fun View.changeWidth(fromValue: Int, toValue: Int, duration: Long = 300) {
    val valueAnimator = ValueAnimator
        .ofInt(fromValue, toValue)
        .setDuration(duration)

    valueAnimator.interpolator = AccelerateDecelerateInterpolator()

    valueAnimator.addUpdateListener { animation ->
        val newValue = animation.animatedValue as Int
        this.layoutParams.width = newValue
        this.requestLayout()
    }
    valueAnimator.start()

}

fun View.changeElevation(fromValue: Float, toValue: Float, duration: Long = 300) {
    ObjectAnimator
        .ofFloat(this, "elevation", fromValue, toValue)
        .setDuration(duration)
        .start()
}