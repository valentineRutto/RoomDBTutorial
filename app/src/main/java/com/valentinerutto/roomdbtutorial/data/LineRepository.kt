package com.valentinerutto.roomdbtutorial.data

import com.valentinerutto.roomdbtutorial.data.local.PickupLineDao
import com.valentinerutto.roomdbtutorial.data.remote.network.ApiService

class LineRepository(val apiService: ApiService,val lineDao: PickupLineDao) {


}