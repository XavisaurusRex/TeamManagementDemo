package cat.devsofthecoast.teammanagementdemo.feature.devoptions.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ScrollView
import cat.devsofthecoast.teammanagementdemo.BuildConfig
import cat.devsofthecoast.teammanagementdemo.R
import cat.devsofthecoast.teammanagementdemo.R.id.scvLogs
import cat.devsofthecoast.teammanagementdemo.TMDApp
import cat.devsofthecoast.teammanagementdemo.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.core.mvp.ui.PresenterActivity
import cat.devsofthecoast.teammanagementdemo.feature.commons.repository.TMDRepository
import cat.devsofthecoast.teammanagementdemo.feature.commons.repository.impl.TMDRepositoryImpl
import cat.devsofthecoast.teammanagementdemo.feature.commons.repository.impl.TMDService
import cat.devsofthecoast.teammanagementdemo.feature.devoptions.DevOptionsContract
import cat.devsofthecoast.teammanagementdemo.feature.devoptions.presenter.DevOptionsPresenter
import cat.devsofthecoast.teammanagementdemo.feature.devoptions.usecase.FillDatabaseUseCase
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_dev_options.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.text.SimpleDateFormat
import java.util.*


class DevOptionsActivity : PresenterActivity<DevOptionsContract.Presenter, DevOptionsContract.View>(), DevOptionsContract.View {


    private val appConfig: BaseConfig by lazy {
        (application as TMDApp).getConfig()
    }

    // TODO Remove change service inizialization, this should work with Firebase Android utilities
    private val service: TMDService by lazy {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(BuildConfig.TEAMMANAGEMENT_API_BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create(ObjectMapper().registerKotlinModule()))
                .build()
        retrofit.create<TMDService>(TMDService::class.java)
    }

    private val repository: TMDRepository by lazy {
        TMDRepositoryImpl(service)
    }

    private val fillDatabaseUseCase: FillDatabaseUseCase by lazy {
        FillDatabaseUseCase(appConfig, repository, this)
    }


    override val presenter: DevOptionsContract.Presenter by lazy {
        DevOptionsPresenter(appConfig, fillDatabaseUseCase)
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

        btnFillDatabase.setOnClickListener {
            presenter.fillDatabase()
        }
    }

    private fun postLog(message: String) {
        val dateformat: String = formatHMS.format(Calendar.getInstance().timeInMillis)
        tvLogs.append("$dateformat: $message \n")
        notifyChangesLogs()
    }

    private fun notifyChangesLogs() {
        scvLogs.post(runnable)
    }

    override fun onDatabaseFilledSuccess() {
        Log.d(this.javaClass.name, "Database filled correctly")
        postLog("Database filled correctly")
    }

    override fun onDatabaseFilledError(ex: Throwable) {
        Log.e(this.javaClass.name, "Error Posting to Firebase", ex)
        postLog("Error Posting to Firebase")
    }
}
