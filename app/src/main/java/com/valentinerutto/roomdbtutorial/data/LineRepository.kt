package com.valentinerutto.roomdbtutorial.data

import com.valentinerutto.roomdbtutorial.data.local.PickupLineDao
import com.valentinerutto.roomdbtutorial.data.local.PickuplineEntity
import com.valentinerutto.roomdbtutorial.data.local.mapResponseToEntity
import com.valentinerutto.roomdbtutorial.data.remote.network.ApiService
import com.valentinerutto.roomdbtutorial.utils.Resource
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class LineRepository(private val apiService: ApiService, private val lineDao: PickupLineDao) {

    suspend fun getAllLines(): Flow<List<PickuplineEntity>> {
        return lineDao.getAllLines()
    }

    suspend fun getandSaveListPickupLine(): Flow<Resource<List<PickuplineEntity>>> = flow {
        try {
            val response = apiService.getPickupLines()

            if (!response.isSuccessful) {
                emit(Resource.Error(response.message()))
            }

            val entity = mapResponseToEntity(response.body())!!

            lineDao.insertAll(entity)
            emit(Resource.Success(entity))

        } catch (e: HttpException) {
            emit(
                Resource.Error("something went wrong: ${e.message()}")
            )
        } catch (e: IOException) {
            emit(
                Resource.Error("something went wrong: ${e.message}")
            )
        }
    }.flowOn(IO)


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
            lineDao.insert(listOf(entity))
        }
    }

    suspend fun deleteRandomLine(entity: PickuplineEntity) {
        withContext(IO) {
            lineDao.deleteLine(entity.idKey)
        }
    }

   suspend fun refreshData(){
        lineDao.deleteAll()
       getandSaveListPickupLine()
    }
}