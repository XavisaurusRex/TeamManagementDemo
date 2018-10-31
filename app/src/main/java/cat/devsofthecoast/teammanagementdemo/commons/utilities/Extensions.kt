@file:Suppress("NOTHING_TO_INLINE")

package cat.devsofthecoast.teammanagementdemo.commons.utilities

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.annotation.RawRes
import java.io.InputStream
import java.nio.charset.Charset


inline fun Context.toast(text: String): Toast {
    return Toast.makeText(this, text, Toast.LENGTH_SHORT).apply { show() }
}

inline fun Context.openRawResource(@RawRes fileRes: Int): String? {
    var json: String? = null
    try {
        val inputStream: InputStream = this.resources.openRawResource(fileRes)

        json = inputStream.readTextAndClose(Charsets.UTF_8)
    } catch (ex: Exception) {
        Log.e(this.javaClass.name, "error opening res file $fileRes", ex)
    }

    return json
}

fun InputStream.readTextAndClose(charset: Charset = Charsets.UTF_8): String {
    return this.bufferedReader(charset).use { it.readText() }
}