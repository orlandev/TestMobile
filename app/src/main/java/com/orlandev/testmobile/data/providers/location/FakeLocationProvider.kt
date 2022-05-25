package com.orlandev.testmobile.data.providers.location

import com.google.android.gms.maps.model.LatLng
import com.orlandev.testmobile.domain.providers.ILocationProvider

class FakeLocationProvider : ILocationProvider {
    override fun getUserLocation(): LatLng {
        return LatLng(20.008374256942965, -75.83938368136273)
    }
}