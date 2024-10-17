package com.example.mvvm

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


// Model
data class User(val name: String, val age: Int)

// ViewModel
class UserViewModel : ViewModel() {
    var user by mutableStateOf(User("Default", 0))
        private set  // 외부에서 수정을 막음

    fun updateUser(name: String, age: Int) {
        user = User(name, age)
    }

    init {
        Log.d("MVVM_TAG", "UserViewModel created!")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("MVVM_TAG", "UserViewModel destroyed!")
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UserScreen()
        }

        Log.d("MVVM_TAG", "onCreate")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MVVM_TAG", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MVVM_TAG", "onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MVVM_TAG", "onDestroy")
    }
}

// View
@Composable
fun UserScreen(viewModel: UserViewModel = viewModel()) {
    val user = viewModel.user

    Column(
        modifier = Modifier.padding(20.dp)
    ) {
        Text(text = "Name: ${user.name}")
        Text(text = "Age: ${user.age}")

        Button(onClick = {
            viewModel.updateUser("John", 25)
        }) {
            Text("Update User")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UserScreen()
}
