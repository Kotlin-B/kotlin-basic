package com.example.mvvm.state

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class CounterViewModel : ViewModel() {
    // MutableStateFlow로 카운터 상태 관리
    private val _counter = MutableStateFlow(0)

    // 외부에서 관찰할 수 있는 StateFlow
    val counter: StateFlow<Int> = _counter

    // 카운터 증가 함수
    fun incrementCounter() {
        _counter.value += 1
    }

    fun resetCounter() {
        _counter.value = 0
    }
}

class CounterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CounterApp()
        }
    }
}

@Composable
fun CounterApp(viewModel: CounterViewModel = viewModel()) {
    // StateFlow를 collectAsState로 상태를 관찰
    val counter by viewModel.counter.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // 카운터 값 표시
        Text(text = "Counter: $counter", modifier = Modifier.padding(16.dp))

        // 카운터 증가 버튼
        Button(onClick = {
            viewModel.incrementCounter()  // ViewModel에서 값 증가
        }) {
            Text(text = "증가")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 카운터 초기화 버튼
        Button(onClick = {
            viewModel.resetCounter()  // ViewModel에서 초기화
        }) {
            Text(text = "초기화")
        }
    }
}
