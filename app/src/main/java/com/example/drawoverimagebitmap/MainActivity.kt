package com.example.drawoverimagebitmap

import android.graphics.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat

import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition


class MainActivity : AppCompatActivity() {

    private lateinit var imgView : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imgView = findViewById(R.id.iv_main)

        getImageBitmap()

    }

    private fun getImageBitmap() {
        Glide.with(this)
            .asBitmap()
            .load("https://avatars.githubusercontent.com/u/59609165?v=4")
            .into(object : CustomTarget<Bitmap?>() {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap?>?
                ) {
                    drawOverImage(resource)
                    //setImage(imgView, resource)
                }

                override fun onLoadCleared(placeholder: Drawable?) {

                }
            })
    }

    private fun drawOverImage(originalBitmap: Bitmap) {
        val canvas = Canvas(originalBitmap)

        val paint = Paint()
        paint.color = Color.BLACK // Text Color

        val customTypeface = ResourcesCompat.getFont(this, R.font.saxmono)
        paint.typeface = customTypeface
        paint.textSize = 24f // Text Size

        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_OVER) // Text Overlapping Pattern

        // some more settings...

        // some more settings...
        canvas.drawBitmap(originalBitmap, 0f, 0f, paint)
        canvas.drawText("Testing...", 10f, 30f, paint)
        // NEWLY ADDED CODE ENDS HERE ]
        setImage(imgView, originalBitmap)
        // NEWLY ADDED CODE ENDS HERE ]
//        originalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
    }

    private fun setImage(iv: ImageView, resource: Bitmap) {


        iv.setImageBitmap(resource)
    }
}