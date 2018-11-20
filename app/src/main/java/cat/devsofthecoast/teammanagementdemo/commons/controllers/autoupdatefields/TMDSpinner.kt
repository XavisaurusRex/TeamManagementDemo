package cat.devsofthecoast.teammanagementdemo.commons.controllers.autoupdatefields

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.annotation.AttrRes
import cat.devsofthecoast.teammanagementdemo.R
import cat.devsofthecoast.teammanagementdemo.commons.models.Team
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.schedule


class TMDSpinner : Spinner {

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
    private var changeValueLister: ((value: Team) -> Unit)? = null
    private var tmdAdapter: TMDSpinnerAdapter? = null

    fun onChangeValue(changeValueLister: ((value: Team) -> Unit)) {
        this.changeValueLister = changeValueLister
    }


    private fun loadAttributeSet(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TMDSpinner)

        notifyValueChangeDelay = typedArray.getInt(
                R.styleable.TMDSpinner_spn_value_change_notification_delay,
                DEFAULT_CHANGE_VALUE_NOTIFICATION_DELAY).toLong()

        typedArray.recycle()
    }

    init {
        tmdAdapter = TMDSpinnerAdapter(
                context,
                R.layout.item_team_view,
                R.id.tvName,
                arrayListOf())
        this.adapter = tmdAdapter

        this.onItemSelectedListener = TMDSpinnerWatcher {
            this.post {
                changeValueLister?.invoke(tmdAdapter?.getItem(it)!!)
            }
        }
    }

    fun setSource(teamsList: List<Team>) {
        tmdAdapter?.addAll(teamsList)
    }

    inner class TMDSpinnerWatcher(
            private val listener: ((Int) -> Unit))
        : OnItemSelectedListener {
        var timer = Timer("SpinnerSelectionChangeNotificator", false)

        override fun onNothingSelected(parent: AdapterView<*>?) {

        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            timer.cancel()
            timer = Timer()
            timer.schedule(notifyValueChangeDelay) {
                listener.invoke(position)
            }
        }

    }
}