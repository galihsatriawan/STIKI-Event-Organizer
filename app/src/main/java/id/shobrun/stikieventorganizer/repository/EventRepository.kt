package id.shobrun.stikieventorganizer.repository

import id.shobrun.stikieventorganizer.AppExecutors
import id.shobrun.stikieventorganizer.api.EventApi
import id.shobrun.stikieventorganizer.room.EventDao

class EventRepository constructor(val appExecutors: AppExecutors,val apiService : EventApi, val localDB : EventDao) {

}