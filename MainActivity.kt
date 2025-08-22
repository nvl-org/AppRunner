package com.apprunner

import android.os.Bundle
import android.webkit.WebView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.webView)

        if (!Python.isStarted()) {
            Python.start(AndroidPlatform(this))
        }
        val py = Python.getInstance()
        val runner = py.getModule("runner")
        runner.callAttr("start_server")

        // Default open EcoLog
        webView.settings.javaScriptEnabled = true
        webView.loadUrl("http://127.0.0.1:5000")

        // Button to switch to MGAO
        findViewById<Button>(R.id.btnMGAO).setOnClickListener {
            webView.loadUrl("http://127.0.0.1:5001")
        }

        // Button to switch back to EcoLog
        findViewById<Button>(R.id.btnEcoLog).setOnClickListener {
            webView.loadUrl("http://127.0.0.1:5000")
        }
    }
}