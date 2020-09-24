package cl.gerardomascayano.drinksapp.core.extension

import android.graphics.Bitmap
import android.widget.ImageView
import cl.gerardomascayano.drinksapp.core.GlideApp
import com.bumptech.glide.load.Transformation

fun ImageView.loadImage(url: String?, vararg transformation: Transformation<Bitmap>) {
    GlideApp.with(this.context)
        .load(url)
        .transform(*transformation)
//                .placeholder()
//                .error()
        .into(this)
}

//GlideApp.with(viewBinding.root.context)
//.load(drink.imageUrl)
//.transform(CenterCrop(), RoundedCorners(8.dpToPx()))
//.into(viewBinding.ivDrinkImage)