package com.app.coins.utils

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.PackageManager
import android.icu.text.DecimalFormat
import android.net.Uri
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.core.content.ContextCompat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


fun Context.loadJSONFromAssets(fileName: String): String {
    return applicationContext.assets.open(fileName).bufferedReader().use { reader ->
        reader.readText()
    }
}

fun Context.openChrome(url: String) {
    val urlIntent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse(url)
    )
    this.startActivity(urlIntent)
}

fun restartApp(context: Context) {
    val packageManager: PackageManager = context.packageManager
    val intent: Intent = packageManager.getLaunchIntentForPackage(context.packageName)!!
    val componentName: ComponentName = intent.component!!
    val restartIntent: Intent = Intent.makeRestartActivityTask(componentName)
    context.startActivity(restartIntent)
    Runtime.getRuntime().exit(0)
}


fun hasPermission(context: Context, permission: String): Boolean {
    return ContextCompat.checkSelfPermission(
        context,
        permission
    ) == PackageManager.PERMISSION_GRANTED
}


fun Activity.vibrate() {
    val vib = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val vibratorManager =
            getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
        vibratorManager.defaultVibrator
    } else {
        @Suppress("DEPRECATION")
        getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val vibrationEffect2 = VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK)
        vib.cancel()
        vib.vibrate(vibrationEffect2)
    }
}

internal fun Context.findActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    throw IllegalStateException("Permissions should be called in the context of an Activity")
}

object PriceFormatterUtil {
    fun formatPrice(price: String): String {
        val priceAsDouble = price.toDoubleOrNull()

        return if (priceAsDouble != null && priceAsDouble < 1.0) {
            withZeroFormatPrice(price)
        } else {
            standardFormatPrice(price)
        }
    }

    private fun standardFormatPrice(price: String): String {
        val dec = DecimalFormat("#,###.##")
        return dec.format(price.toDouble())
    }

    private fun withZeroFormatPrice(price: String): String {
        val dec = DecimalFormat("0.####")
        return dec.format(price.toDouble())
    }
}

object DateFormatter {
    private val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    private val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

    fun format(timestamp: Long): String {
        val date = Date(timestamp)
        val now = System.currentTimeMillis()

        return if (isToday(timestamp, now)) {
            timeFormat.format(date)
        } else {
            dateFormat.format(date)
        }
    }

    private fun isToday(timestamp: Long, now: Long): Boolean {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timestamp
        val day = calendar.get(Calendar.DAY_OF_YEAR)

        calendar.timeInMillis = now
        val today = calendar.get(Calendar.DAY_OF_YEAR)

        return day == today
    }
}

