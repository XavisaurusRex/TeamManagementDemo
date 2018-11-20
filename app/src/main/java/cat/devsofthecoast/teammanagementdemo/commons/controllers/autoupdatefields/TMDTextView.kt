package cat.devsofthecoast.teammanagementdemo.commons.controllers.autoupdatefields

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.widget.EditText
import androidx.annotation.AttrRes
import cat.devsofthecoast.teammanagementdemo.R
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.schedule


class TMDTextView : EditText {

    private val DEFAULT_CHANGE_VALUE_NOTIFICATION_DELAY: Int = TimeUnit.SECONDS.toMillis(3).toInt()

    constructor(context: Context) : super(context) {
        loadAttributeSet(null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        loadAttributeSet(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?,
                @AttrRes defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        loadAttributeSet(attrs)
    }

    var notifyValueChangeDelay: Long = 0

    private fun loadAttributeSet(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TMDTextView)

        notifyValueChangeDelay = typedArray.getInt(
                R.styleable.TMDTextView_tv_value_change_notification_delay,
                DEFAULT_CHANGE_VALUE_NOTIFICATION_DELAY).toLong()

        typedArray.recycle()
    }

    private var changeValueLister: ((value: String) -> Unit)? = null

    fun onChangeValue(changeValueLister: ((value: String) -> Unit)) {
        this.changeValueLister = changeValueLister
    }

    init {
        this.addTextChangedListener(
                TMDTextWatcher(notifyValueChangeDelay) {
                    this.post {
                        changeValueLister?.invoke(it)
                    }
                }
        )
    }


    inner class TMDTextWatcher(
            private val notificationDelay: Long,
            private val listener: ((String) -> Unit)) : TextWatcher {

        var timer = Timer("TextChangeNotificator", false)

        override fun afterTextChanged(editable: Editable?) {
            timer.cancel()
            timer = Timer()
            timer.schedule(notifyValueChangeDelay) {
                listener.invoke(editable.toString())
            }
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }
    }

}