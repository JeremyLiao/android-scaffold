package com.jeremyliao.android.scaffold.kt.activity

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jeremyliao.android.scaffold.R
import com.jeremyliao.android.scaffold.databinding.ActivityKtMainBinding
import com.jeremyliao.android.scaffold.utils.ToastUtils
import kotlinx.coroutines.*

class KotlinMainActivity : AppCompatActivity() {

    internal lateinit var binding: ActivityKtMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_kt_main)
        binding.handler = this
        binding.setLifecycleOwner(this)
    }

    fun onClick() {
        ToastUtils.showShort("hello world")
    }

    fun testCoroutine1() {
        GlobalScope.launch(Dispatchers.Main) {
            val data = requestData()
            val ret = "show thread: " + Thread.currentThread().name + "|" + data
            binding.tvContent.text = ret
        }
    }

    fun testCoroutine2() {
        val coroutineScope = CoroutineScope(Dispatchers.Main)
        coroutineScope.launch(Dispatchers.Main) {
            val data = requestData()
            val ret = "show thread: " + Thread.currentThread().name + "|" + data
            binding.tvContent.text = ret
        }
    }


    private suspend fun requestData(): String {
        return withContext(Dispatchers.IO) {
            val name = Thread.currentThread().name
            "worker thread: $name"
        }
    }
}
