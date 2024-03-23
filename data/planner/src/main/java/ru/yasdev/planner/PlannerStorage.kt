package ru.yasdev.planner

import android.content.Context
import androidx.room.Room
import ru.yasdev.common.GetUserIdRepository
import ru.yasdev.planner.mappers.toEventModel
import ru.yasdev.planner.models.EventModel
import ru.yasdev.planner.models.NewEventModel
import ru.yasdev.planner.models.PlannerState

class PlannerStorage(private val context: Context, private val getUserIdRepository: GetUserIdRepository) {

    private val db =
        Room.databaseBuilder(
            context,
            PlannerDataBase::class.java,
            "planner.db"
        ).build()


    suspend fun getEvents(): PlannerState {
        return try{
            val userId = getUserIdRepository.getId()
            if(userId != null){
                val list = db.dao.get(userId)
                val result = list.map { it.toEventModel() }
                PlannerState.Planner(result)
            }
            else{
                PlannerState.UserNotFound
            }
        }
        catch (e: Exception){
            PlannerState.ErrorOnReceipt
        }
    }

    suspend fun saveEvent(eventModel: NewEventModel) {

        val userId = getUserIdRepository.getId()

        db.dao.insert(
            EventEntity(
            title = eventModel.title,
            note = eventModel.note,
            link = eventModel.link,
            date = eventModel.date,
            userId = userId!!
        )
        )
    }

    suspend fun deleteEvent(eventId: Int) {
        db.dao.delete(eventId)
    }
}