package com.example.pam_project_medicat_009

import android.os.Bundle
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
import com.example.pam_project_medicat_009.ui.theme.PAM_Project_MediCat_009Theme
import com.example.pam_project_medicat_009.uicontroller.NavGraph

class MainActivity : ComponentActivity() {

    companion object {
        // 👇 BASE URL DI SINI
        const val BASE_URL = "http://172.27.208.1:3000/api/"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PAM_Project_MediCat_009Theme {
                NavGraph()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PAM_Project_MediCat_009Theme {
        Greeting("Android")
    }
}