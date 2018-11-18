package cat.devsofthecoast.teammanagementdemo.commons.controllers.othercontroller

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.useCase.Callback
import java.util.*
import kotlin.concurrent.schedule

class TMDTextWatcher(
        private val delay: Long,
        private val listener: TextWatcherListener)
    : TextWatcher {

    var timer = Timer("TextChanged", false)

    override fun afterTextChanged(editable: Editable?) {
        timer.cancel()
        timer = Timer()
        timer.schedule(delay) {
            Log.d(this@TMDTextWatcher.javaClass.simpleName, "TEXT CHANGED")
            listener.onTextChange(editable.toString())
        }
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    interface TextWatcherListener{
        fun onTextChange(text: String)
    }
}