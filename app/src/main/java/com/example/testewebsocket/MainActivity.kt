package com.example.testewebsocket

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.example.testewebsocket.Modelss.EventModel
import com.example.testewebsocket.Modelss.NotifyModel
import com.example.testewebsocket.Socket.EventHandle
import com.example.testewebsocket.Socket.SocketClient
import com.example.testewebsocket.Util.MyLog
import com.example.testewebsocket.databinding.ActivityMainBinding
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket

class MainActivity : AppCompatActivity() , EventHandle {

    private lateinit var binding: ActivityMainBinding
    private var ws: WebSocket? = null
    private val tryAgainDelay = 5000L

    // Adapters
    /*
    private val processoA = SocketProcessoAAdapter()
    private val processoB = SocketProcessoBAdapter()
    private val canal = SocketCanalAdapter()
    private val tipo = SocketTipoAdapter()
    private val servidor = SocketServidorAdapter()
    private val status = SocketStatusAdapter()
     */


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //TODO Permissão
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO), 55)

        startSocket()
    }

    override fun onEvent(data: EventModel) {
        binding.tvType.text = data.data?.type
        binding.tvChannel.text = data.data?.channel
        binding.tvMessage.text = data.data?.message

    }

    override fun onSocketFail() {
        runOnUiThread {
            Toast.makeText(this, getString(R.string.msg_socket_fail), Toast.LENGTH_LONG).show()
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

}