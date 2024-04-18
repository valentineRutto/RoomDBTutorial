package com.valentinerutto.roomdbtutorial.data

import com.valentinerutto.roomdbtutorial.data.local.PickupLineDao
import com.valentinerutto.roomdbtutorial.data.local.PickuplineEntity
import com.valentinerutto.roomdbtutorial.data.local.mapResponseToEntity
import com.valentinerutto.roomdbtutorial.data.remote.network.ApiService
import com.valentinerutto.roomdbtutorial.utils.Resource
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class LineRepository(private val apiService: ApiService, private val lineDao: PickupLineDao) {

    suspend fun getAllLines(): Flow<List<PickuplineEntity>> {
        return lineDao.getAllLines()
    }

    suspend fun getRandomLine(): Resource<PickuplineEntity> {
        val response = apiService.getRandomPickupLine()

        if (!response.isSuccessful) {
            return Resource.Error(response.message())
        }

        val entity = mapResponseToEntity(response.body())

        return Resource.Success(entity)
    }

    suspend fun saveRandomLine(entity: PickuplineEntity) {
        withContext(IO) {
            lineDao.insert(entity)
        }
    }

    suspend fun deleteRandomLine(entity: PickuplineEntity) {
        withContext(IO) {
            lineDao.deleteLine(entity.id)
        }
    }

    //    suspend fun getSavePickupLine(): Resource<List<PickuplineEntity>> {
//        val response = apiService.getPickupLines()
//
//        if (!response.isSuccessful) {
//            return Resource.Error(response.message())
//        }
//
//        val entity = mapResponseToEntity(response.body())
//        if (entity != null) {
//            lineDao.insertAll(entity)
//        }
//
//        return Resource.Success(entity!!)
//    }


}