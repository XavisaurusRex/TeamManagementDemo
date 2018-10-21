@file:Suppress("NOTHING_TO_INLINE")

package cat.devsofthecoast.teammanagementdemo.feature.commons.utilities

import android.content.Context
import android.widget.Toast

inline fun Context.toast(text: String): Toast {
    return Toast.makeText(this, text, Toast.LENGTH_SHORT).apply { show() }
}