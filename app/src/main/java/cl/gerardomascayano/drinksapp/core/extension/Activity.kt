package cl.gerardomascayano.drinksapp.core.extension

import android.app.Activity
import android.util.DisplayMetrics

fun Activity.screenWidth(): Int{
    val displayMetrics = DisplayMetrics()
    this.windowManager.defaultDisplay.getMetrics(displayMetrics)
    return displayMetrics.widthPixels
}