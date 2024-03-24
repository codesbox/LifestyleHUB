package ru.yasdev.planner

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [EventEntity::class], version = 1
)
abstract class PlannerDataBase : RoomDatabase() {
    abstract val dao: PlannerDao
}

