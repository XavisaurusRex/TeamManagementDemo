package cat.devsofthecoast.teammanagementdemo.feature.fragments.teamslist.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import cat.devsofthecoast.teammanagementdemo.R
import cat.devsofthecoast.teammanagementdemo.TMDApp
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.ui.PresenterActivity
import cat.devsofthecoast.teammanagementdemo.commons.repository.questions.QuestionsRepository
import cat.devsofthecoast.teammanagementdemo.commons.repository.questions.impl.QuestionsRepositoryImpl
import cat.devsofthecoast.teammanagementdemo.feature.fragments.teamslist.TeamsListContract
import cat.devsofthecoast.teammanagementdemo.feature.fragments.teamslist.presenter.TeamsListPresenter
import kotlinx.android.synthetic.main.activity_main.*


class TeamsListActivity : PresenterActivity<TeamsListContract.Presenter, TeamsListContract.View>(), TeamsListContract.View {

    var countEasterEggs: Int = 0
    val EASTER_EGG_WINCOUNT = 7

    val easterListener: View.OnClickListener = View.OnClickListener {
        if (!(it.tag as Boolean)) {
            it.setBackgroundColor(ContextCompat.getColor(this@TeamsListActivity, R.color.colorEasterEgg_on))
            it.tag = true
            udpateCountEasterEgg(true)
        } else {
            it.setBackgroundColor(ContextCompat.getColor(this@TeamsListActivity, R.color.colorEasterEgg_off))
            it.tag = false
            udpateCountEasterEgg(false)
        }
    }

    companion object {

        private val INTENT_USER_ID = "user_id"

        fun newIntent(context: Context): Intent {
            val intent = Intent(context, TeamsListActivity::class.java)
            //you can put extras here
            return intent
        }
    }

    override val presenter: TeamsListContract.Presenter by lazy {
        TeamsListPresenter(appConfig)
    }

    private val repository: QuestionsRepository by lazy {
        QuestionsRepositoryImpl()
    }

    private val appConfig: BaseConfig by lazy {
        (application as TMDApp).getConfig()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = getString(R.string.mainActivityTitle)

        createEasterEgg()
    }

    private fun createEasterEgg() {
        vLeftTop.tag = false
        vRightTop.tag = false
        vLeftBottom.tag = false
        vRightBottom.tag = false
        v05.tag = false
        v06.tag = false
        v07.tag = false

        vLeftTop.setOnClickListener(easterListener)
        vRightTop.setOnClickListener(easterListener)
        vLeftBottom.setOnClickListener(easterListener)
        vRightBottom.setOnClickListener(easterListener)
        v05.setOnClickListener(easterListener)
        v06.setOnClickListener(easterListener)
        v07.setOnClickListener(easterListener)
    }

    private fun udpateCountEasterEgg(increment: Boolean) {
        if (increment) {
            countEasterEggs++
            if (countEasterEggs == EASTER_EGG_WINCOUNT) {
                ivSolaireHavel.visibility = View.VISIBLE
                tvWinMessage.visibility = View.VISIBLE
            } else {
                ivSolaireHavel.visibility = View.GONE
                tvWinMessage.visibility = View.GONE
            }
        } else {
            countEasterEggs--
            if (countEasterEggs != EASTER_EGG_WINCOUNT) {
                ivSolaireHavel.visibility = View.GONE
                tvWinMessage.visibility = View.GONE
            }
        }
    }
}
