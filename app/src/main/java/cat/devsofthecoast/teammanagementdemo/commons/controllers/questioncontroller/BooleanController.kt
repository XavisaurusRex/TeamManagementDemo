package cat.devsofthecoast.teammanagementdemo.commons.controllers.questioncontroller

import android.content.Context
import android.support.annotation.AttrRes
import android.util.AttributeSet
import cat.devsofthecoast.teammanagementdemo.R
import cat.devsofthecoast.teammanagementdemo.commons.models.questions.BooleanQuestion
import kotlinx.android.synthetic.main.controller_basequestion.view.*
import kotlinx.android.synthetic.main.controller_booleanquestion.view.*

class BooleanController : BaseQuestionController<BooleanQuestion> {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?,
                @AttrRes defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        vsResponse.layoutResource = R.layout.controller_booleanquestion
        vsResponse.inflate()
    }

    override var question: BooleanQuestion? = null
    override var listener: QuestionControllerListener<BooleanQuestion>? = null

    override fun changeQuestion(newQuestion: BooleanQuestion) {
        super.changeQuestion(newQuestion)

        if(newQuestion.picture_url != null){
            setPicture(newQuestion.picture_url.toString())
        } else {
            ivPicture.setImageDrawable(resources.getDrawable(R.drawable.ic_def_booleanquestion, null))
        }

        tbResponse.isChecked = newQuestion.questionResponse
        tbResponse.setOnClickListener {
            newQuestion.questionResponse = !newQuestion.questionResponse
            listener?.onChangeResponse(newQuestion)
        }
    }
}