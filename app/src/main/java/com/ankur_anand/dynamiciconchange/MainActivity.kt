package com.ankur_anand.dynamiciconchange

import android.app.Application
import android.content.ComponentName
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ankur_anand.dynamiciconchange.IconType.CYAN
import com.ankur_anand.dynamiciconchange.IconType.GREEN
import com.ankur_anand.dynamiciconchange.ui.theme.DynamicIconChangeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DynamicIconChangeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DynamicIconChange()
                }
            }
        }
    }
}

@Composable
fun DynamicIconChange(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val application = context.applicationContext as Application

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                changeAppIcon(
                    application = application,
                    iconType = GREEN
                )
            }
        ) {
            Text(text = stringResource(R.string.change_to_green_icon))
        }

        Spacer(
            modifier = Modifier.height(25.dp)
        )

        Button(
            onClick = {
                changeAppIcon(
                    application = application,
                    iconType = CYAN
                )
            }
        ) {
            Text(text = stringResource(R.string.change_to_cyan_icon))
        }
    }
}

private fun changeAppIcon(
    application: Application,
    iconType: IconType
) {
    val previousComponentName = when (iconType) {
        GREEN -> ComponentName(
            application,
            "com.ankur_anand.dynamiciconchange.CyanActivity"
        )

        CYAN -> ComponentName(
            application,
            "com.ankur_anand.dynamiciconchange.GreenActivity"
        )
    }

    val currentComponentName = when (iconType) {
        GREEN -> ComponentName(
            application,
            "com.ankur_anand.dynamiciconchange.GreenActivity"
        )

        CYAN -> ComponentName(
            application,
            "com.ankur_anand.dynamiciconchange.CyanActivity"
        )
    }

    application.packageManager.setComponentEnabledSetting(
        previousComponentName,
        PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
        PackageManager.DONT_KILL_APP
    )

    application.packageManager.setComponentEnabledSetting(
        currentComponentName,
        PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP
    )
}

@Preview(showBackground = true)
@Composable
fun DynamicIconChangePreview() {
    DynamicIconChangeTheme {
        DynamicIconChange()
    }
}