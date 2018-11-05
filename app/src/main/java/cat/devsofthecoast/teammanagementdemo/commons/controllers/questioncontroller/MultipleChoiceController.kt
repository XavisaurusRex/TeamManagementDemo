package cat.devsofthecoast.teammanagementdemo.commons.controllers.questioncontroller

import android.content.Context
import android.util.AttributeSet
import android.widget.CheckBox
import androidx.annotation.AttrRes
import cat.devsofthecoast.teammanagementdemo.R
import cat.devsofthecoast.teammanagementdemo.commons.models.questions.MultipleChoiceQuestion
import kotlinx.android.synthetic.main.controller_basequestion.view.*
import kotlinx.android.synthetic.main.controller_multiplechoicequestion.view.*

class MultipleChoiceController : BaseQuestionController<MultipleChoiceQuestion> {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?,
                @AttrRes defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        vsResponse.layoutResource = R.layout.controller_multiplechoicequestion
        vsResponse.inflate()
    }

    override var question: MultipleChoiceQuestion? = null
    override var listener: QuestionControllerListener<MultipleChoiceQuestion>? = null

    override fun changeQuestion(newQuestion: MultipleChoiceQuestion) {
        super.changeQuestion(newQuestion)
        cgOptions.setOnSelectOptionListener { _: CheckBox, _: Int, _: Boolean -> null }

        if (newQuestion.picture_url != null) {
            setPicture(newQuestion.picture_url.toString())
        } else {
            ivPicture.setImageDrawable(resources.getDrawable(R.drawable.ic_def_multiplechoicequestion, null))
        }

        cgOptions.setOptions(newQuestion.options)
        cgOptions.deselectAll()
        cgOptions.selectPositions(newQuestion.questionResponse)

        cgOptions.setOnSelectOptionListener { _: CheckBox, index: Int, isChecked: Boolean ->
            if (isChecked) question?.questionResponse?.add(index)
            else question?.questionResponse?.remove(index)
            listener?.onChangeResponse(question!!)
        }
    }
}