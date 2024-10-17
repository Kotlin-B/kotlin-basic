package com.example.mvvm.live

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


class CounterViewModel : ViewModel() {
    // MutableLiveData로 카운터 상태 관리
    private val _counter = MutableLiveData(0)

    // 외부에서 관찰할 수 있는 LiveData
    val counter: LiveData<Int> = _counter

    // 카운터 증가 함수
    fun incrementCounter() {
        _counter.value = (_counter.value ?: 0) + 1
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
    // LiveData를 observeAsState로 상태를 관찰
    val counter = viewModel.counter.observeAsState(0)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // 카운터 값 표시
        Text(text = "Counter: ${counter.value}", modifier = Modifier.padding(16.dp))

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
