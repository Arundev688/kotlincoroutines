package com.hutech.coroutines.locationinbackground

import android.location.Location
import kotlinx.coroutines.flow.Flow

interface LocationClint {

   fun getLocationUpdates(interval:Long) : Flow<Location>

   class LocationException(message : String) : Exception()

}