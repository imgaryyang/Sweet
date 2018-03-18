package com.lucky.sweet.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebViewClient
import com.lucky.sweet.R
import kotlinx.android.synthetic.main.activity_map_web_acivity.*

class MapWebAcivity : AppCompatActivity() {
    val MAPPATH = "http://apis.map.qq.com/tools/routeplan/eword=故宫博物馆&epointx=116.39710&epointy=39.917200?referer=myapp&key=OB4BZ-D4W3U-B7VVO-4PJWW-6TKDJ-WPB77 "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_web_acivity)
        initView()

    }

    fun initView() {
        wb_map.loadUrl(MAPPATH)
        wb_map.webViewClient = WebViewClient()
    }


    companion object {

        val FIR_LAT = "FIR_LAT"
        val FIR_LON = "FIR_LON"

        fun InStance(activity: Activity, firlat: Double, firlon: Double) {
            var intent = Intent(activity, MapWebAcivity::class.java)
            intent.putExtra(FIR_LAT, firlat)
            intent.putExtra(FIR_LON, firlon)
            activity.startActivity(intent)

        }
    }
}
