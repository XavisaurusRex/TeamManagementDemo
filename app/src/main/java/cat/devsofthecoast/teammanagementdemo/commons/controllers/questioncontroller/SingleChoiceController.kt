package cat.devsofthecoast.teammanagementdemo.commons.controllers.questioncontroller

import android.content.Context
import android.support.annotation.AttrRes
import android.util.AttributeSet
import android.widget.RadioButton
import cat.devsofthecoast.teammanagementdemo.R
import cat.devsofthecoast.teammanagementdemo.commons.models.questions.SingleChoiceQuestion
import kotlinx.android.synthetic.main.controller_basequestion.view.*
import kotlinx.android.synthetic.main.controller_singlechoicequestion.view.*

class SingleChoiceController : BaseQuestionController<SingleChoiceQuestion> {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?,
                @AttrRes defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        vsResponse.layoutResource = R.layout.controller_singlechoicequestion
        vsResponse.inflate()
    }

    override var question: SingleChoiceQuestion? = null
    override var listener: QuestionControllerListener<SingleChoiceQuestion>? = null

    override fun changeQuestion(newQuestion: SingleChoiceQuestion) {
        super.changeQuestion(newQuestion)
        rgOptionsContainer.setOnSelectOptionListener { radioButton: RadioButton, i: Int -> null }

        if(newQuestion.picture_url != null){
            setPicture(newQuestion.picture_url.toString())
        } else {
            ivPicture.setImageDrawable(resources.getDrawable(R.drawable.ic_def_singlechoicequestion, null))
        }

        rgOptionsContainer.setOptions(newQuestion.options)
        rgOptionsContainer.deselectAll()
        rgOptionsContainer.selectAt(newQuestion.questionResponse)

        rgOptionsContainer.setOnSelectOptionListener { radioButton: RadioButton, selectedIndex: Int ->
            question?.questionResponse = selectedIndex
            listener?.onChangeResponse(question!!)
        }
    }
}