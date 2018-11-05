package cat.devsofthecoast.teammanagementdemo.commons.controllers.radiogroup

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.RadioGroup

class TMDCheckboxGroup : RadioGroup {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    private val buttonsParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    private var listener: ((checkbox: CheckBox, position: Int, selected: Boolean) -> Unit?)? = null

    /**
     * Override other questions, and recycle textboxs created
     */
    fun setOptions(list: List<String>) {
        for (i in 0 until list.size) {
            if (i < childCount) {
                val checkbox = this.getChildAt(i) as CheckBox
                checkbox.visibility = View.VISIBLE
                checkbox.text = list[i]
            } else {
                addOption(list[i])
            }
        }

        for (i in list.size until childCount) {
            this.getChildAt(i).visibility = View.GONE
        }
    }

    private fun addOption(option: String) {
        val rb = CheckBox(context)
        rb.text = option
        rb.tag = childCount
        rb.setOnCheckedChangeListener { _: CompoundButton, checked: Boolean ->
            listener?.invoke(rb, rb.tag as Int, checked)
        }
        this.addView(rb, this.childCount, buttonsParams)
    }

    fun removeAllButtons() {
        while (this.childCount != 0) {
            this.removeViewAt(childCount - 1)
        }

    }

    fun deselectAll() {
        for (i in 0 until childCount) {
            val checkbox = this.getChildAt(i) as CheckBox
            checkbox.isChecked = false
        }
    }

    fun selectAt(position: Int) {
        if (childCount > position) {
            val checkbox = this.getChildAt(position) as CheckBox
            checkbox.isChecked = true
        }
    }

    fun selectPositions(list: List<Int>) {
        for (position in list) {
            selectAt(position)
        }
    }

    fun setOnSelectOptionListener(function: (button: CheckBox, selectedIndex: Int, selected: Boolean) -> Unit?) {
        this.listener = function
    }
}