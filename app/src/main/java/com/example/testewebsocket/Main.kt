package com.example.testewebsocket
import android.Manifest
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.example.testewebsocket.Modelss.EventModel
import com.example.testewebsocket.Modelss.NotifyModel
import com.example.testewebsocket.databinding.ActivityMainBinding
import com.example.testewebsocket.Socket.EventHandle
import com.example.testewebsocket.Socket.SocketClient
import com.example.testewebsocket.Util.MyLog
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket

class Main : AppCompatActivity(), EventHandle {

    private lateinit var binding: ActivityMainBinding
    private val log = MyLog(Main::class.java.simpleName)
    private var ws: WebSocket? = null
    private val loopDelay = 500L
    private val tryAgainDelay = 5000L
    private val labelDelay = 1500L
    private val notifyDelay = 1000L
    private val filaNotify = mutableListOf<NotifyModel>()
    private var isNotifying = false
    private var volume = 10000f
    private var isFirstTime = true
    private var isRecording = false

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
        runOnUiThread {
            //binding.tvChannelCount.text = textCount

            /*
                processoA.postData(data)
                processoB.postData(data)
                canal.postData(data)
                servidor.postData(data)
                tipo.postData(data)
                status.postData(data)
             */

        }
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