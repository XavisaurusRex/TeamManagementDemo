package cat.devsofthecoast.teammanagementdemo.commons.repository.trainers

import cat.devsofthecoast.teammanagementdemo.commons.models.users.Trainer
import cat.devsofthecoast.teammanagementdemo.commons.repository.base.BaseRepository
import cat.devsofthecoast.teammanagementdemo.commons.services.ServiceCallback

interface TrainersRepository : BaseRepository {
    fun setTrainers(trainers: List<Trainer>, listener: ServiceCallback<Boolean>?)
}