package cl.gerardomascayano.drinksapp.core.extension

import android.content.res.Resources

fun Int.dpToPx() = (this * Resources.getSystem().displayMetrics.density).toInt()