package cat.devsofthecoast.teammanagementdemo.commons.controllers.radiogroup

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.CompoundButton
import android.widget.RadioButton
import android.widget.RadioGroup


class TMDRadioGroup : RadioGroup {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    private val buttonsParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
    private var listener: ((RadioButton, Int) -> Unit?)? = null

    fun setOptions(list: List<String>) {
        for (i in 0 until list.size) {
            if (i < childCount) {
                val radioButton = this.getChildAt(i) as RadioButton
                radioButton.visibility = View.VISIBLE
                radioButton.text = list[i]
            } else {
                addOption(list[i])
            }
        }

        for (i in list.size until childCount) {
            this.getChildAt(i).visibility = View.GONE
        }
    }

    private fun addOption(option: String) {
        val rb = RadioButton(context)
        rb.text = option
        rb.tag = childCount
        rb.setOnCheckedChangeListener { _: CompoundButton, checked: Boolean ->
            if (checked) {
                listener?.invoke(rb, rb.tag as Int)
            }
        }
        this.addView(rb, this.childCount, buttonsParams)
    }

    private fun removeAllButtons() {
        while (this.childCount != 0) {
            this.removeViewAt(childCount - 1)
        }

    }

    fun deselectAll() {
        for (i in 0 until childCount) {
            val radioButton = this.getChildAt(i) as RadioButton
            radioButton.isChecked = false
        }
        this.clearCheck()
    }

    fun selectAt(position: Int) {
        if (childCount > position) {
            val radioButton = this.getChildAt(position) as RadioButton
            radioButton.isChecked = true
        }
    }

    fun setOnSelectOptionListener(function: (button: RadioButton, selectedIndex: Int) -> Unit?) {
        this.listener = function
    }
}