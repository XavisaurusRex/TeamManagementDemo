package cat.devsofthecoast.teammanagementdemo.commons.controllers.questioncontroller

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.AttrRes
import cat.devsofthecoast.teammanagementdemo.R
import cat.devsofthecoast.teammanagementdemo.commons.models.questions.NumericQuestion
import kotlinx.android.synthetic.main.controller_basequestion.view.*
import kotlinx.android.synthetic.main.controller_numericquestion.view.*

class NumericController : BaseQuestionController<NumericQuestion> {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?,
                @AttrRes defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        vsResponse.layoutResource = R.layout.controller_numericquestion
        vsResponse.inflate()
    }

    override var question: NumericQuestion? = null
    override var listener: QuestionControllerListener<NumericQuestion>? = null

    override fun changeQuestion(newQuestion: NumericQuestion) {
        super.changeQuestion(newQuestion)
        npResponse.setOnValueChangedListener { numberPicker, lastValue, newValue -> null }

        if (newQuestion.picture_url != null) {
            setPicture(newQuestion.picture_url.toString())
        } else {
            ivPicture.setImageDrawable(resources.getDrawable(R.drawable.ic_def_numericquestion, null))
        }

        npResponse.minValue = newQuestion.min
        npResponse.maxValue = newQuestion.max

        npResponse.value = newQuestion.questionResponse
        npResponse.setOnValueChangedListener { numberPicker, lastValue, newValue ->
            newQuestion.questionResponse = newValue
            listener?.onChangeResponse(newQuestion)
        }

    }
}