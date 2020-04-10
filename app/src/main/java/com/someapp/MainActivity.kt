package com.someapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.someapp.presentation.home.HomeFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    @InternalCoroutinesApi
    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, HomeFragment())
            .commit()


        runBlocking {
            val nums = (1..3).asFlow()
            val str = flowOf("one", "two")
            nums.combine(str) { a, b -> "$a -> $b" }
                .collect { println(it)}

        }
    }
}
