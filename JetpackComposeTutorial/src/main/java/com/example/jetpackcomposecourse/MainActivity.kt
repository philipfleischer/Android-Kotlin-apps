package com.example.jetpackcomposecourse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposecourse.ui.theme.JetpackComposeCourseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeCourseTheme {
                //Greeting4("Philip")
                //ClickMeButtonPage()
                SecondScreen()
            }
        }
    }
}

@Composable
fun SecondScreen() {

    var name by remember {
        mutableStateOf("")
    }

    var names by remember {
        mutableStateOf(listOf<String>())
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp).padding(top = 60.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { text ->
                    name = text
                },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(16.dp).height(20.dp))
            Button(onClick = {
                if(name.isNotBlank()) {
                    names = names + name
                    name = ""
                }
            }) {
                Text(text = "Add")
            }
        }
        LazyColumn() {
            items(names) { currentName ->
                Text(
                    text = currentName,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )
                Divider()
            }
        }
    }
}

@Composable
fun ClickMeButtonPage() {
    var count by remember {
        mutableStateOf(0)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = count.toString(),
            fontSize = 30.sp
        )
        Button(onClick = {
            count++
        }) {
            Text(text = "Click me! -> $count")
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan)
    ) {
        Text(
            text = "Hello $name!\n\nYOOOOOO",
            color = Color.Blue,
            fontSize = 30.sp,
            modifier = modifier
                .background(Color.Red)
                .padding(60.dp)
                .fillMaxWidth()
                .alpha(1.2f)
                .background(Color.Green)
        )
        Text(
            text = "Some other text",
            color = Color.Blue,
            fontSize = 30.sp
        )
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier
            .size(600.dp)
    ) {
        Text(
            text = "Hello $name!\n\nYOOOOOO",
            color = Color.Blue,
            fontSize = 30.sp,
            modifier = modifier
                .background(Color.Red)
                .padding(60.dp)
                .fillMaxWidth()
                .alpha(1.2f)
                .background(Color.Green)
        )
        Text(
            text = "Some other text",
            color = Color.Blue,
            fontSize = 30.sp
        )
    }
}

@Composable
fun Greeting3(name: String, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .size(600.dp)
    ) {
        Text(
            text = "Hello $name!\n\nYOOOOOO",
            color = Color.Blue,
            fontSize = 30.sp,
            modifier = Modifier.align(Alignment.BottomEnd)
        )
        Text(
            text = "Some other text",
            color = Color.Blue,
            fontSize = 30.sp
        )
    }
}

@Composable
fun Greeting4(name: String) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(10) { i ->
            Image(
                painter = painterResource(id=R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier.background(Color.Black)
            )
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier
                    .background(Color.White)
                    .size(100.dp)
            )
        }
    }

    LazyRow(modifier = Modifier.fillMaxSize()) {
        items(10) { i ->
            Image(
                painter = painterResource(id=R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier.background(Color.Black)
            )
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier
                    .background(Color.White)
                    .size(100.dp)
            )
        }
    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackComposeCourseTheme {
//        Greeting("Android")
//        Greeting2("Android")
//        Greeting3("Android")
        Greeting4("Android")
    }
}