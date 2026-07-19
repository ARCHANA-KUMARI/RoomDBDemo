package com.example.roomdbdemo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.room.Room
import com.example.roomdbdemo.ui.theme.RoomDBDemoTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var mDatabase: ContactDatabase
    private val TAG = "ArchanaMainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RoomDBDemoTheme {
                //getDataFromDb()
                CenteredButtonColumn(mDatabase)
            }
        }

        //Todo 19TH July create singleton class
        if (!::mDatabase.isInitialized) {
            mDatabase =
                Room.databaseBuilder(applicationContext, ContactDatabase::class.java, "contactDB")
                    .build()
            Log.i(TAG, "onCreate: null block ${mDatabase}")
        } else {
            Log.i(TAG, "onCreate: null block else ${mDatabase}")
        }


    }
}


@Composable
//@Preview(showBackground = true, name = "Preview 4", widthDp = 100, heightDp = 100)
fun CenteredButtonColumn(mDatabase: ContactDatabase) {
    val TAG = "CenteredButtonColumn"
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { Log.i(TAG, "CenteredButtonColumn: click on Insert")
            GlobalScope.launch {
                mDatabase.contactDao().insertContact(Contact(1, "Archana", "8951251568"))
            }}) {
            Text("Insert")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { Log.i(TAG, "CenteredButtonColumn: click on Update") }) {
            Text("Update")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { Log.i(TAG, "CenteredButtonColumn: click on Delete") }) {
            Text("Delete")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { Log.i(TAG, "CenteredButtonColumn: click on Get Data button")}) {
            Text("Get")
        }
    }
}

@Composable
@Preview(showBackground = true, name = "Preview 4", widthDp = 100, heightDp = 100)
fun getDataFromDb() {
    Button(onClick = { Log.i("Archana", "MyButton: ") }) {
        Text("Click Me")
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
    RoomDBDemoTheme {
        Greeting("Android")
    }
}