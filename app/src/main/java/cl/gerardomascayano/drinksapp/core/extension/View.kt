package cl.gerardomascayano.drinksapp.core.extension

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator


fun View.visible(withAnimation: Boolean = false, duration: Long = 300) {
    if (this.visibility != View.VISIBLE) this.visibility = View.VISIBLE
    if (withAnimation) {
        ObjectAnimator.ofFloat(this, "alpha", 0f, 1f)
            .setDuration(duration)
            .start()
    }
}

fun View.gone(withAnimation: Boolean = false) {
    if (this.visibility != View.GONE) this.visibility = View.GONE
    if (withAnimation) {
        ObjectAnimator.ofFloat(this, "alpha", 1f, 0f)
            .setDuration(300)
            .start()
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

fun View.changeWidth(fromValue: Int, toValue: Int, duration: Long = 300, animateElevation: Boolean = false) {
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

    if (animateElevation) {
        ObjectAnimator
            .ofFloat(this, "elevation", 6.dpToPx().toFloat(), 10.dpToPx().toFloat())
            .setDuration(duration)
            .start()
    }

}