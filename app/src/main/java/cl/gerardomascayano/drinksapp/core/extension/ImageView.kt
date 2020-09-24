package cl.gerardomascayano.drinksapp.core.extension

import android.widget.ImageView
import cl.gerardomascayano.drinksapp.core.GlideApp
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadImage(url: String?, roundedCorners: Int = 0) {
    var glideRequest = GlideApp.with(this.context)
        .load(url)
        .centerCrop()
    if (roundedCorners > 0) {
        glideRequest = glideRequest.transform(RoundedCorners(roundedCorners), CenterCrop())
    }
//                .placeholder()
//                .error()
    glideRequest.into(this)
}