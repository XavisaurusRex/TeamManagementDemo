package cat.devsofthecoast.teammanagementdemo.feature.devoptions.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ScrollView
import cat.devsofthecoast.teammanagementdemo.R
import cat.devsofthecoast.teammanagementdemo.TMDApp
import cat.devsofthecoast.teammanagementdemo.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.core.mvp.ui.PresenterActivity
import cat.devsofthecoast.teammanagementdemo.commons.models.questions.Question
import cat.devsofthecoast.teammanagementdemo.commons.repository.TMDRepository
import cat.devsofthecoast.teammanagementdemo.commons.repository.impl.TMDRepositoryImpl
import cat.devsofthecoast.teammanagementdemo.feature.devoptions.DevOptionsContract
import cat.devsofthecoast.teammanagementdemo.feature.devoptions.presenter.DevOptionsPresenter
import cat.devsofthecoast.teammanagementdemo.commons.useCase.FillDatabaseUseCase
import cat.devsofthecoast.teammanagementdemo.commons.useCase.GetAllQuestionsUseCase
import cat.devsofthecoast.teammanagementdemo.commons.useCase.GetQuestionUseCase
import cat.devsofthecoast.teammanagementdemo.commons.utilities.toast
import kotlinx.android.synthetic.main.activity_dev_options.*
import java.text.SimpleDateFormat
import java.util.*


class DevOptionsActivity : PresenterActivity<DevOptionsContract.Presenter, DevOptionsContract.View>(), DevOptionsContract.View {


    override val presenter: DevOptionsContract.Presenter by lazy {
        (application as TMDApp).presenterModule.devOptionsPresenter
    }

    val runnable = Runnable { scvLogs.fullScroll(ScrollView.FOCUS_DOWN) }
    val formatHMS = SimpleDateFormat("hh:mm:ss", Locale.US)

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, DevOptionsActivity::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dev_options)

        configureInteractions()
    }

    private fun configureInteractions() {
        btnFillDatabase.setOnClickListener {
            presenter.fillDatabase()
        }

        btnGetAllQuestions.setOnClickListener {
            when (spSelectTable.selectedItemPosition) {
                0 -> presenter.getAllQuestions()
                1, 2, 3, 4 -> postLog("Not Implemented Tables")
            }
        }

        btnGetSingleQuestion.setOnClickListener {
            // todo hardcoded key, get one demo question or simply take the first...
            presenter.getSingleQuestion("-LPh4y9Tj47h1wa7gMke")
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

    fun notImplementedClick(v: View) {
        postLog("Not Implemented Logic in this button")
    }
}
