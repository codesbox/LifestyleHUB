package ru.yasdev.auth.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.yasdev.auth.current_user_id.SaveIdDataSource
import ru.yasdev.auth.current_user_id.SaveIdRepositoryImpl
import ru.yasdev.auth.sign_up.SignUpDataSource
import ru.yasdev.auth.sign_up.SignUpRepositoryImpl
import ru.yasdev.sign_up.data.SaveIdRepository
import ru.yasdev.sign_up.data.SignUpRepository

val authDataModule = module {
    singleOf(::SignUpDataSource){bind<SignUpDataSource>()}
    singleOf(::SignUpRepositoryImpl){bind<SignUpRepository>()}
    singleOf(::SaveIdRepositoryImpl){bind<SaveIdRepository>()}
    singleOf(::SaveIdDataSource){bind<SaveIdDataSource>()}

}