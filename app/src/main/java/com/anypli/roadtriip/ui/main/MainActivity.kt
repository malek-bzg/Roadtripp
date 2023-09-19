package com.anypli.roadtriip.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.anypli.roadtriip.SampleAppNavGraph


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //ComposeMapCenterPointMapMarker()
//            SampleAppNavGraph()
            AppNavHost()

        }
    }


}

