package ru.yasdev.auth.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.yasdev.auth.current_user_id.GetUserIdDataSource
import ru.yasdev.auth.current_user_id.GetUserIdRepositoryImpl
import ru.yasdev.auth.current_user_id.SaveIdDataSource
import ru.yasdev.auth.current_user_id.SaveIdRepositoryImpl
import ru.yasdev.auth.profile.ProfileDataSource
import ru.yasdev.auth.profile.ProfileRepositoryImpl
import ru.yasdev.auth.sign_in.SignInDataSource
import ru.yasdev.auth.sign_in.SignInRepositoryImpl
import ru.yasdev.auth.sign_up.SignUpDataSource
import ru.yasdev.auth.sign_up.SignUpRepositoryImpl
import ru.yasdev.common.GetUserIdRepository
import ru.yasdev.profile.data.ProfileRepository
import ru.yasdev.sign_in.data.SignInRepository
import ru.yasdev.sign_up.data.SaveIdRepository
import ru.yasdev.sign_up.data.SignUpRepository

val authDataModule = module {
    singleOf(::SignUpDataSource) { bind<SignUpDataSource>() }
    singleOf(::SignUpRepositoryImpl) { bind<SignUpRepository>() }
    singleOf(::SaveIdRepositoryImpl) { bind<SaveIdRepository>() }
    singleOf(::SaveIdDataSource) { bind<SaveIdDataSource>() }
    singleOf(::SignInRepositoryImpl) { bind<SignInRepository>() }
    singleOf(::SignInDataSource) { bind<SignInDataSource>() }
    singleOf(::GetUserIdDataSource) { bind<GetUserIdDataSource>() }
    singleOf(::GetUserIdRepositoryImpl) { bind<GetUserIdRepository>() }
    singleOf(::ProfileDataSource) { bind<ProfileDataSource>() }
    singleOf(::ProfileRepositoryImpl) { bind<ProfileRepository>() }
}