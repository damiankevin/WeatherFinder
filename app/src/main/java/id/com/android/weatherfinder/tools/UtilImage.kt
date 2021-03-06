package id.com.android.weatherfinder.tools

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import com.squareup.picasso.Picasso
import id.com.android.weatherfinder.R


object UtilImage {
    fun loadImageWithoutPlaceholder(view: ImageView?, imagePath: String?, context: Context?) {
        val safeImagePath = if (!imagePath.isNullOrBlank()) imagePath else UtilConstant.URL_DEFAULT
        view?.let { Picasso.with(context).load(safeImagePath).config(Bitmap.Config.RGB_565).fit().placeholder(
            R.color.color_background_image).into(view) }
    }
}