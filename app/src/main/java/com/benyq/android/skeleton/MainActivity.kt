package com.benyq.android.skeleton

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.benyq.core.http.ErrorHandler
import com.benyq.core.http.RetrofitManager
import com.benyq.core.http.request
import com.benyq.core.utils.Logger
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Logger.setup(true, "benyq")
        ErrorHandler.errorHandler = {
            DataResponse.error(-1, it.message!!)
        }

        val api = RetrofitManager.createApiService("https://www.wanandroid.com", WanAndroidService::class.java)
        lifecycleScope.launch {
            val response = request { api.banner() }
            Logger.v(this@MainActivity, "banner", response)
        }
    }
}