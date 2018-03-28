package com.lucky.sweet.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebViewClient
import com.lucky.sweet.R
import com.lucky.sweet.utility.GaoDeMapUtil
import com.tencent.mapsdk.raster.model.LatLng
import kotlinx.android.synthetic.main.activity_map_web_acivity.*

class MapWebAcivity : AppCompatActivity() {
    val MAPPATH = "http://apis.map.qq.com/tools/routeplan/eword=您的目的地&epointx=116.39710&epointy=39.917200?referer=myapp&key=OB4BZ-D4W3U-B7VVO-4PJWW-6TKDJ-WPB77 "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_web_acivity)
        initView()

    }


    fun initView() {
        val urlPath = getUrlPath( intent.getStringExtra(END_LAT), intent.getStringExtra(END_LON))
        println(urlPath)
        wb_map.loadUrl(urlPath)
        wb_map.webViewClient = WebViewClient()
    }

    fun getUrlPath(lat: String, lon: String): String {
        return "http://apis.map.qq.com/tools/routeplan/eword=您的目的地&epointx=$lat&epointy=$lon?referer=myapp&key=OB4BZ-D4W3U-B7VVO-4PJWW-6TKDJ-WPB77 "

    }

    companion object {

        val END_LAT = "END_LAT"
        val END_LON = "END_LON"

        fun InStance(activity: Activity, endLat: String, endlong: String) {
            var intent = Intent(activity, MapWebAcivity::class.java)
            intent.putExtra(END_LAT, endLat)
            intent.putExtra(END_LON, endlong)
            activity.startActivity(intent)

        }
    }
}
