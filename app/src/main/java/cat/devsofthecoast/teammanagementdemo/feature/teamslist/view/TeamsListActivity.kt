package cat.devsofthecoast.teammanagementdemo.feature.teamslist.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import cat.devsofthecoast.teammanagementdemo.BuildConfig
import cat.devsofthecoast.teammanagementdemo.R
import cat.devsofthecoast.teammanagementdemo.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.core.mvp.ui.PresenterActivity
import cat.devsofthecoast.teammanagementdemo.TMDApp
import cat.devsofthecoast.teammanagementdemo.feature.commons.repository.TMDRepository
import cat.devsofthecoast.teammanagementdemo.feature.commons.repository.impl.TMDRepositoryImpl
import cat.devsofthecoast.teammanagementdemo.feature.commons.repository.impl.TMDService
import cat.devsofthecoast.teammanagementdemo.feature.commons.repository.impl.Team
import cat.devsofthecoast.teammanagementdemo.feature.commons.useCase.RequestTeamsUseCase
import cat.devsofthecoast.teammanagementdemo.feature.teamslist.TeamsListContract
import cat.devsofthecoast.teammanagementdemo.feature.teamslist.presenter.TeamsListPresenter
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory


class TeamsListActivity : PresenterActivity<TeamsListContract.Presenter, TeamsListContract.View>(), TeamsListContract.View {
    private val RC_SIGN_IN: Int = 177
    private val AUTH_TAG: String = "AuthGoogle"

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

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

    override val presenter: TeamsListContract.Presenter by lazy {
        TeamsListPresenter(appConfig, requestTeamsUseCase)
    }

    private val repository: TMDRepository by lazy {
        TMDRepositoryImpl(service)
    }

    private val appConfig: BaseConfig by lazy {
        (application as TMDApp).getConfig()
    }

    private val requestTeamsUseCase: RequestTeamsUseCase by lazy {
        RequestTeamsUseCase(appConfig, repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = getString(R.string.mainActivityTitle)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        auth = FirebaseAuth.getInstance()

        btnGetDatabaseEntry.setOnClickListener {
            presenter.getTeams()
        }

        btnGoogleAuth.setOnClickListener {
            val currentUser = auth.currentUser
            updateUI(currentUser)
        }
    }

    override fun showTeams(teamsResult: List<Team>) {
        tvLogs.text = ""
        for (team:Team in teamsResult){
            tvLogs.append("TEAM NAME --> " + team.team_name + "\n")
            tvLogs.append("TEAM ID -->" + team.team_id + "\n")
            tvLogs.append("------------------------\n")
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        //hideProgressDialog()
        if (user != null) {
            title = user.email + " is logged"
        } else {
            title = "NOT LOGGED"
            signIn()
        }
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(AUTH_TAG, "Google sign in failed", e)
            }
        }
    }


    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.d(AUTH_TAG, "firebaseAuthWithGoogle:" + acct.id!!)

        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(AUTH_TAG, "signInWithCredential:success")
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(AUTH_TAG, "signInWithCredential:failure", task.exception)
                        updateUI(null)
                    }
                }
    }
}
