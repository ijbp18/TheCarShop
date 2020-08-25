package com.home.thecarshop.presentation.carlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.home.thecarshop.R
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar_main)

    }
}
