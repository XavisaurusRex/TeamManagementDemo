package cat.devsofthecoast.teammanagementdemo.feature.commons.repository.impl

import cat.devsofthecoast.teammanagementdemo.feature.commons.repository.TMDRepository
import com.google.firebase.database.FirebaseDatabase

class TMDRepositoryImpl(private val service: TMDService) : TMDRepository {
    override fun fillDatabase(): Boolean {



        return false
    }

    override fun getTeams(): List<Team>? =
            service.getTeams().execute().body()
}