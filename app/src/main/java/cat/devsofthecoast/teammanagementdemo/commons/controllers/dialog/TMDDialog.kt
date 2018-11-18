package cat.devsofthecoast.teammanagementdemo.commons.controllers.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.Window
import android.widget.Button
import androidx.annotation.DrawableRes
import cat.devsofthecoast.teammanagementdemo.R
import kotlinx.android.synthetic.main.controller_tmddialog.*

class TMDDialog(context: Context, viewGroup: ViewGroup) : Dialog(context) {

    private var listener: TMDDialogListener? = null

    fun setTMDDialogListener(listener: TMDDialogListener){
        this.listener = listener
    }
    var title: String = ""
        set(value) {
            field = value
            tvTitle.text = field
        }

    var description: String = ""
        set(value) {
            field = value
            tvDescription.text = field
        }
    var btnAceptText: String = ""
        set(value) {
            field = value
            btnOk.text = field
        }

    var btnCancelText: String = ""
        set(value) {
            field = value
            btnCancel.text = field
        }

    @DrawableRes
    var icon: Int = android.R.drawable.ic_dialog_alert
        set(value) {
            field = value
            ivIcon.setImageResource(field)
        }

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(layoutInflater.inflate(R.layout.controller_tmddialog, viewGroup, false))
        window?.setLayout(MATCH_PARENT, WRAP_CONTENT)

        configureInteractions()
    }

    private fun configureInteractions() {
        btnOk.setOnClickListener {
            listener?.onAcept()
        }

        btnCancel.setOnClickListener {
            dismiss()
            listener?.onCancel()
        }

        setOnDismissListener {
            listener?.onDismiss()
        }
    }

    interface TMDDialogListener {
        fun onAcept()
        fun onCancel()
        fun onDismiss()
    }
}