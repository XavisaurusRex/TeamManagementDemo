package cat.devsofthecoast.teammanagementdemo.feature.commons.repository.impl

import retrofit2.Call
import retrofit2.http.GET

interface TMDService {
    @GET("teams.json/")
    fun getTeams(): Call<List<Team>>
}
