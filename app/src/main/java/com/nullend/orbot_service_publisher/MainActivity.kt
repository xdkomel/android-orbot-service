package com.nullend.orbot_service_publisher

import android.content.Intent
import android.net.VpnService
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.core.content.ContextCompat
import com.nullend.orbot_service_publisher.ui.theme.Orbot_service_publisherTheme
import org.torproject.android.service.OrbotConstants
import org.torproject.android.service.OrbotHelper
import org.torproject.android.service.OrbotService
import org.torproject.android.service.util.Prefs


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityResultNotifier.addListener(::onActivityResult)
        initOrbot()
        setContent {
            Orbot_service_publisherTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScreenCompose(ScreenComposeParams(::connect, ::disconnect))
                }
            }
        }
    }

    private fun initOrbot() {
        OrbotHelper.setApplicationId(BuildConfig.APPLICATION_ID)
        Prefs.setContext(this)
        Prefs.putUseVpn(true)
        Prefs.putConnectionPathway(Prefs.PATHWAY_SNOWFLAKE)
    }

    @Deprecated("just for test")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        ActivityResultNotifier.notify(resultCode)
        super.onActivityResult(requestCode, resultCode, data)
    }


    private fun onActivityResult(resultCode: Int) {
        if (resultCode == RESULT_OK) {
            startTor()
        }
    }

    private fun connect() {
        val vpnIntent = VpnService.prepare(this)
        if (vpnIntent != null) {
            startActivityForResult(vpnIntent, 1)
        } else {
            startTor()
        }
    }

    private fun disconnect() {
        sendIntentToService(OrbotConstants.ACTION_STOP)
        sendIntentToService(OrbotConstants.ACTION_STOP_VPN)
    }

    private fun startTor() {
        sendIntentToService(OrbotConstants.ACTION_START)
        sendIntentToService(OrbotConstants.ACTION_START_VPN)
    }


    private fun sendIntentToService(intent: Intent) = ContextCompat.startForegroundService(this, intent)

    private fun sendIntentToService(action: String) = sendIntentToService(
        Intent(this, OrbotService::class.java).apply {
            this.action = action
        })

}




class ScreenComposeParams(val onStart: () -> Unit, val onStop: () -> Unit) {}

class ScreenComposeProvider: PreviewParameterProvider<ScreenComposeParams> {
    override val values = sequenceOf(ScreenComposeParams(onStart = {}, onStop = {}))
}

@Preview(showBackground = true)
@Composable
fun ScreenCompose(@PreviewParameter(ScreenComposeProvider::class) params: ScreenComposeParams) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxSize()
    ) {
        Button(onClick = { params.onStart() }) {
            Text(text = "connect")
        }
        Button(onClick = { params.onStop() }) {
            Text(text = "disconnect")
        }
    }

}