package cat.devsofthecoast.teammanagementdemo.commons.controllers.questioncontroller

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import androidx.constraintlayout.widget.ConstraintLayout
import cat.devsofthecoast.teammanagementdemo.R
import cat.devsofthecoast.teammanagementdemo.commons.models.questions.Question
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.controller_basequestion.view.*

abstract class BaseQuestionController<Q : Question> : ConstraintLayout {
    constructor(context: Context) : super(context) {
        init(context, null, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs, null)
    }

    constructor(context: Context, attrs: AttributeSet?,
                @AttrRes defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs, defStyleAttr)
    }

    @Suppress("UNUSED_PARAMETER")
    private fun init(context: Context, attrs: AttributeSet?, @AttrRes defStyleAttr: Int?) {
        LayoutInflater.from(context)
                .inflate(R.layout.controller_basequestion, this, true)
    }

    abstract var question: Q?
    abstract var listener: QuestionControllerListener<Q>?

    fun setSource(newQuestion: Q) {
        changeQuestion(newQuestion)
        this.question = newQuestion
    }

    open fun changeQuestion(newQuestion: Q) {
        tvStatment.text = newQuestion.statement
    }

    protected fun setPicture(url: String) {
        Glide.with(this).load(url).into(ivPicture)
    }

    fun setOnDataChangeListener(listener: QuestionControllerListener<Q>) {
        this.listener = listener
    }

    interface QuestionControllerListener<Q : Question> {
        fun onChangeResponse(question: Q)
    }
}