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

@AndroidEntryPoint
class SplachActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplachBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySplachBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        // This is used to hide the status bar and make
        // the splash screen as a full screen activity.
        window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        // we used the postDelayed(Runnable, time) method
        // to send a message with a delayed time.
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000) // 3000 is the delayed time in milliseconds.
    }
}