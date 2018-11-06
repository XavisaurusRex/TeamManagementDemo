package cat.devsofthecoast.teammanagementdemo.feature.fragments.devoptions.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import cat.devsofthecoast.teammanagementdemo.R
import cat.devsofthecoast.teammanagementdemo.TMDApp
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.ui.PresenterFragment
import cat.devsofthecoast.teammanagementdemo.commons.models.questions.Question
import cat.devsofthecoast.teammanagementdemo.feature.fragments.devoptions.DevOptionsContract
import kotlinx.android.synthetic.main.activity_dev_options.*
import java.text.SimpleDateFormat
import java.util.*


class DevOptionsFragment : PresenterFragment<DevOptionsContract.Presenter, DevOptionsContract.View>(), DevOptionsContract.View {

    override val presenter: DevOptionsContract.Presenter by lazy {
        (activity?.application as TMDApp).presenterModule.devOptionsPresenter
    }

    val runnable = Runnable { scvLogs.fullScroll(ScrollView.FOCUS_DOWN) }
    val formatHMS = SimpleDateFormat("hh:mm:ss", Locale.US)

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, DevOptionsFragment::class.java)
            return intent
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_dev_options, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureInteractions()
    }

    private fun configureInteractions() {
        btnFillDatabase.setOnClickListener {
            presenter.fillDatabase()
        }

        btnGetAll.setOnClickListener {
            when (spSelectTable.selectedItemPosition) {
                0 -> presenter.getAllQuestions()
                1, 2, 3, 4 -> postLog("Not Implemented Tables")
            }
        }

        btnGetSingle.setOnClickListener {
            presenter.getSingleQuestion("-LPh4y9Tj47h1wa7gMke")
        }

        btnClearQuestions.setOnClickListener {
            presenter.clearDatabase("questions")
        }
        btnClearLogs.setOnClickListener {
            tvLogs.text = ""
        }
    }

    private fun postLog(message: String) {
        Log.d(this.javaClass.name, message)
        val dateformat: String = formatHMS.format(Calendar.getInstance().timeInMillis)
        tvLogs.append("$dateformat: $message \n")
        notifyChangesLogs()
    }

    private fun notifyChangesLogs() {
        scvLogs.post(runnable)
    }

    override fun onDatabaseFilledSuccess() {
        postLog("Database filled correctly")
    }

    override fun onDatabaseFilledError(ex: Throwable) {
        postLog("Error Posting to Firebase: ${ex.message}")
    }

    override fun onGetAllQuestionsSuccess(questions: ArrayList<Question>) {
        postLog("Questions received correctly")
        for (question: Question in questions) {
            postLog("------------\n Question with statment ${question.statement} \n ${question.type} \n")
        }
    }

    override fun onGetAllQuestionsError(ex: Throwable) {
        postLog("Error Getting data from Firebase: ${ex.message}")
    }

    override fun onGetQuestionsSuccess(question: Question) {
        postLog("------------\n Question with statment ${question.statement} \n ${question.type} \n")
    }

    override fun onGetQuestionsError(ex: Throwable) {
        postLog("Error Getting data from Firebase: ${ex.message}")
    }

    @Suppress("UNUSED_PARAMETER")
    fun notImplementedClick(v: View) {
        postLog("Not Implemented Logic in this button")
    }

    override fun onClearDatabaseChildSuccess(boolean: Boolean) {
        postLog("Database child cleared")
    }

    override fun onClearDatabaseChildError(ex: Throwable) {
        postLog("Error removing data from Firebase: ${ex.message}")
    }
}
