package cat.devsofthecoast.teammanagementdemo.commons.controllers.questioncontroller

import android.content.Context
import android.support.annotation.AttrRes
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import cat.devsofthecoast.teammanagementdemo.R
import cat.devsofthecoast.teammanagementdemo.commons.models.questions.PlaintextQuestion
import kotlinx.android.synthetic.main.controller_basequestion.view.*
import kotlinx.android.synthetic.main.controller_plaintextquestion.view.*

class PlainTextController : BaseQuestionController<PlaintextQuestion> {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?,
                @AttrRes defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        vsResponse.layoutResource = R.layout.controller_plaintextquestion
        vsResponse.inflate()
    }

    override var question: PlaintextQuestion? = null
    override var listener: QuestionControllerListener<PlaintextQuestion>? = null

    val textWatcher = object : TextWatcher {
        private var text: String? = null
        override fun afterTextChanged(editable: Editable?) {
            //fixme when input emoji hearth nonstop this setSelection crashes, outofbound
            if (etResponse.lineCount > etResponse.maxLines) {
                val positionAfterChange = etResponse.selectionStart
                etResponse.setText(text)
                etResponse.setSelection(positionAfterChange - 1)
            }

            if (etResponse.text.toString() != question!!.questionResponse) {
                question!!.questionResponse = etResponse.text.toString()
                listener?.onChangeResponse(question!!)
            }
        }

        override fun beforeTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
            text = charSequence.toString()
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    }

    override fun changeQuestion(newQuestion: PlaintextQuestion) {
        super.changeQuestion(newQuestion)
        etResponse.removeTextChangedListener(textWatcher)

        if(newQuestion.picture_url != null){
            setPicture(newQuestion.picture_url.toString())
        } else {
            ivPicture.setImageDrawable(resources.getDrawable(R.drawable.ic_def_plaintextquestion, null))
        }

        etResponse.setText(newQuestion.questionResponse)
        etResponse.addTextChangedListener(textWatcher)
    }
}