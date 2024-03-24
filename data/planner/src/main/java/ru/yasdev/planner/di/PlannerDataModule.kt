package ru.yasdev.planner.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.yasdev.details.data.AddEventFromDetailsRepository
import ru.yasdev.planner.AddEventFromDetailsRepositoryImpl
import ru.yasdev.planner.PlannerRepositoryImpl
import ru.yasdev.planner.PlannerStorage
import ru.yasdev.planner.data.PlannerRepository

val plannerDataModule = module {
    singleOf(::PlannerStorage) { bind<PlannerStorage>() }
    singleOf(::PlannerRepositoryImpl) { bind<PlannerRepository>() }
    singleOf(::AddEventFromDetailsRepositoryImpl) { bind<AddEventFromDetailsRepository>() }
}