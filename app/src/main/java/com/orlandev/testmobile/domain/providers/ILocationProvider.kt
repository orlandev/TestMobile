package com.orlandev.testmobile.domain.providers

import com.google.android.gms.maps.model.LatLng

interface ILocationProvider {
    fun getUserLocation(): LatLng
}