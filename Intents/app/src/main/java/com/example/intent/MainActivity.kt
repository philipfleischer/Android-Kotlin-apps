package com.example.intent

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.intent.ui.theme.IntentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Explicit Intent
        val explicitButton = findViewById<Button>(R.id.explicitButton)

        explicitButton.setOnClickListener {
            val explicitIntent = Intent(this, SecondActivity::class.java)
            startActivity(explicitIntent)
            finish()
        }

        //Implicit Intent

        val url = "https://www.google.com"
        val implicitButton = findViewById<Button>(R.id.implicitButton)

        implicitButton.setOnClickListener {
            val implicitIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(implicitIntent)
        }
    }
}
