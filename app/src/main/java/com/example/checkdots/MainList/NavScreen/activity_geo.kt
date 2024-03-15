package com.example.checkdots.MainList.NavScreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.checkdots.R
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView

class activity_geo : AppCompatActivity() {

    lateinit var mapView: MapView
    private val startLocation = Point(59.9402, 30.315)
    private var zoomValue: Float = 16.5f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MapKitFactory.setApiKey("2753fe23-7b83-415e-8f89-d503b9ba806e")
        MapKitFactory.initialize(this)
        setContentView(R.layout.activity_geo)

        mapView = findViewById(R.id.mapview)
//        moveToStartLocation()
//        mapView.mapWindow.map.move(CameraPosition(Point(47.249309, 39.732767), 11.0f, 0.0f, 0.0f),
//        Animation(Animation.Type.SMOOTH, 300f), null)
    }

//    private fun moveToStartLocation() {
//        mapView.mapWindow.map.move(
//            CameraPosition(startLocation, zoomValue, 0.0f, 0.0f)
//        )
//    }

    override fun onStop(){
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    override fun onStart(){
        mapView.onStart()
        MapKitFactory.getInstance().onStart()
        super.onStart()
    }
}