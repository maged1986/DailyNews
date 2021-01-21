package com.megednan.dailynews.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.megednan.dailynews.R
import com.megednan.dailynews.databinding.ActivityMainBinding
import com.megednan.dailynews.databinding.ActivitySplachBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplachActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplachBinding
    val activityScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplachBinding.inflate(layoutInflater)
        val view = binding.root
        var intent = Intent(this, MainActivity::class.java)

        setContentView(view)
        activityScope.launch {
            delay(3000)
            startActivity(intent)
        }
    }

}