package com.example.testewebsocket

import android.Manifest
import android.hardware.SensorManager.getOrientation
import android.os.Bundle
import android.os.Handler
import android.provider.Contacts
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.testewebsocket.Adapters.AdapterChannel
import com.example.testewebsocket.Adapters.AdapterMessage
import com.example.testewebsocket.Adapters.AdapterType
import com.example.testewebsocket.Modelss.EventModel
import com.example.testewebsocket.Socket.EventHandle
import com.example.testewebsocket.Socket.SocketClient
import com.example.testewebsocket.Util.NoScrollLayoutManager
import com.example.testewebsocket.databinding.ActivityMainBinding
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import okhttp3.Dispatcher


class MainActivity : AppCompatActivity() , EventHandle {

    private lateinit var binding: ActivityMainBinding
    private var ws: WebSocket? = null
    private val tryAgainDelay = 5000L

    private var eventList = mutableListOf<EventModel>()

    private val adapterType = AdapterType()
    private val adapterChannel = AdapterChannel()
    private val adapterMessage = AdapterMessage()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //TODO Permissão
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO), 55)

        configureColumns()
        startSocket()
    }

    override fun onEvent(event: EventModel) {

        GlobalScope.launch(Dispatchers.Main) {
            eventList.add(event)
            val tamanho = eventList.size

            if(tamanho > 0){
                adapterType.postData(eventList)
                adapterChannel.postData(eventList)
                adapterMessage.postData(eventList)
            }
        }

    }

    override fun onSocketFail() {
        GlobalScope.launch(Dispatchers.Main) {
            Toast.makeText(baseContext, getString(R.string.msg_socket_fail), Toast.LENGTH_LONG).show()
            Handler().postDelayed({
                startSocket()
            }, tryAgainDelay)
        }
    }

    private fun startSocket() {
        val client = OkHttpClient()
        val request = Request.Builder().url(BuildConfig.server).build()
        val listener = SocketClient(this)
        ws = client.newWebSocket(request, listener)
    }

    private fun configureColumns() {
        binding.rvType.setHasFixedSize(true)
        binding.rvType.layoutManager = NoScrollLayoutManager(this)
        binding.rvType.adapter = adapterType
        val dividerItemDecorationType = DividerItemDecoration(
            binding.rvType.getContext(), DividerItemDecoration.VERTICAL
        )
        binding.rvType.addItemDecoration(dividerItemDecorationType)

        binding.rvChannel.setHasFixedSize(true)
        binding.rvChannel.layoutManager = NoScrollLayoutManager(this)
        binding.rvChannel.adapter = adapterChannel
        val dividerItemDecorationChannel = DividerItemDecoration(
            binding.rvChannel.getContext(), DividerItemDecoration.VERTICAL
        )
        binding.rvChannel.addItemDecoration(dividerItemDecorationChannel)


        binding.rvMessage.setHasFixedSize(true)
        binding.rvMessage.layoutManager = NoScrollLayoutManager(this)
        binding.rvMessage.adapter = adapterMessage
        val dividerItemDecorationMessage = DividerItemDecoration(
            binding.rvMessage.getContext(), DividerItemDecoration.VERTICAL
        )
        binding.rvMessage.addItemDecoration(dividerItemDecorationMessage)
    }

}

