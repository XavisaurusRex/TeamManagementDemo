package cat.devsofthecoast.teammanagementdemo.commons.services.trainers

import cat.devsofthecoast.teammanagementdemo.commons.cache.TMDCache
import cat.devsofthecoast.teammanagementdemo.commons.models.users.Trainer
import cat.devsofthecoast.teammanagementdemo.commons.services.ServiceCallback

interface TrainersService {
    fun setTrainers(trainers: List<Trainer>, listener: ServiceCallback<Boolean>?)
    fun getTrainer(trainerKey: String, serviceCallback: ServiceCallback<Trainer?>, cache: TMDCache<String, Trainer>)
    fun setTrainer(trainer: Trainer, listener: ServiceCallback<Void?>?)
    fun updateTrainer(trainer: Trainer, listener: ServiceCallback<Void?>?)
}