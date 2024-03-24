package ru.yasdev.planner

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface PlannerDao {
    @Upsert
    suspend fun insert(model: EventEntity)

    @Query("SELECT * FROM event_entity WHERE userId = :userId")
    suspend fun get(userId: String): List<EventEntity>

    @Query("DELETE FROM event_entity WHERE eventId = :eventId")
    suspend fun delete(eventId: Int)


}