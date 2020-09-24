package cl.gerardomascayano.drinksapp.core.extension

import android.widget.ImageView
import cl.gerardomascayano.drinksapp.core.GlideApp

fun ImageView.loadImage(url: String?){
    GlideApp.with(this.context)
        .load(url)
        .centerCrop()
//                .placeholder()
//                .error()
        .into(this)
}