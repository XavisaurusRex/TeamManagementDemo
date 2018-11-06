package cat.devsofthecoast.teammanagementdemo.feature.fragments.surveyfragment.adapter.impl

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cat.devsofthecoast.teammanagementdemo.R
import cat.devsofthecoast.teammanagementdemo.commons.controllers.questioncontroller.*
import cat.devsofthecoast.teammanagementdemo.commons.models.questions.*
import cat.devsofthecoast.teammanagementdemo.feature.fragments.surveyfragment.adapter.QuestionsAdapter
import java.util.*

class QuestionsAdapterImpl(
        private val context: Context,
        private val questions: ArrayList<Question>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), QuestionsAdapter {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            QuestionType.TYPE_BOOLEAN.value -> {
                BooleanViewHolder(LayoutInflater.from(context).inflate(R.layout.item_boolean_question, parent, false) as BooleanController)
            }
            QuestionType.TYPE_PLAINTEXT.value -> {
                TextPlainViewHolder(LayoutInflater.from(context).inflate(R.layout.item_textplain_question, parent, false) as PlainTextController)
            }
            QuestionType.TYPE_SINGLECHOICE.value -> {
                SingleChoiceViewHolder(LayoutInflater.from(context).inflate(R.layout.item_singlechoice_question, parent, false) as SingleChoiceController)
            }
            QuestionType.TYPE_MULTICHOICE.value -> {
                MultipleChoiceViewHolder(LayoutInflater.from(context).inflate(R.layout.item_multiplechoice_question, parent, false) as MultipleChoiceController)
            }
            QuestionType.TYPE_NUMERIC.value -> {
                NumericViewHolder(LayoutInflater.from(context).inflate(R.layout.item_numeric_question, parent, false) as NumericController)
            }
            QuestionType.TYPE_HUMANBODY.value -> {
                HumanBodyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_boolean_question, parent, false) as BooleanController)
            }
            else -> throw IllegalStateException("Can't not create a viewHolder for unknown viewType")
        }
    }

    override fun getItemCount(): Int {
        return questions.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            QuestionType.TYPE_BOOLEAN.value -> (holder.itemView as BooleanController).setSource(questions[position] as BooleanQuestion)
            QuestionType.TYPE_PLAINTEXT.value -> (holder.itemView as PlainTextController).setSource(questions[position] as PlaintextQuestion)
            QuestionType.TYPE_SINGLECHOICE.value -> (holder.itemView as SingleChoiceController).setSource(questions[position] as SingleChoiceQuestion)
            QuestionType.TYPE_MULTICHOICE.value -> (holder.itemView as MultipleChoiceController).setSource(questions[position] as MultipleChoiceQuestion)
            QuestionType.TYPE_NUMERIC.value -> (holder.itemView as NumericController).setSource(questions[position] as NumericQuestion)
            QuestionType.TYPE_HUMANBODY.value -> TODO()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return questions[position].type.value
    }

    class BooleanViewHolder(view: BooleanController) : RecyclerView.ViewHolder(view)
    class NumericViewHolder(view: NumericController) : RecyclerView.ViewHolder(view)
    class TextPlainViewHolder(view: PlainTextController) : RecyclerView.ViewHolder(view)
    class SingleChoiceViewHolder(view: SingleChoiceController) : RecyclerView.ViewHolder(view)
    class MultipleChoiceViewHolder(view: MultipleChoiceController) : RecyclerView.ViewHolder(view)
    class HumanBodyViewHolder(view: BooleanController) : RecyclerView.ViewHolder(view)

    override fun add(question: Question) {
        this.questions.add(question)
        this.notifyItemChanged(questions.size - 1)
    }

    override fun add(questions: List<Question>) {
        this.questions.addAll(questions)
        this.notifyItemRangeInserted(this.questions.size - questions.size, this.questions.size)
    }

    override fun remove(question: Question) {
        this.questions.add(question)
        this.notifyItemChanged(questions.size - 1)
    }

    override fun removeAt(position: Int) {
        this.questions.removeAt(position)
        this.notifyItemChanged(position)
    }

    override fun size(): Int {
        return questions.size
    }

    override fun removeAll() {
        questions.clear()
        notifyDataSetChanged()
    }
}