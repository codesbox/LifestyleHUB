package ru.yasdev.details

import ru.yasdev.details.data.DetailsRepository
import ru.yasdev.details.di.detailsModule
import ru.yasdev.details.models.DetailsModel

class DetailsRepositoryImpl(private val dataSource: DetailsDataSource): DetailsRepository {

    override suspend fun getDetails(id: String): DetailsModel {
        return dataSource.getDetails(id)
    }

}