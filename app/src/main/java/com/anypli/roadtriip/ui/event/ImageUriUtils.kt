package com.anypli.roadtriip.ui.event
import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.provider.MediaStore

object ImageUriUtils {
    fun createImageUri(context: Context): Uri? {
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "captured_image.jpg")
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        }
        return context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
    }
}