package com.example.applicationplage.gui

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.applicationplage.R
import com.example.applicationplage.databinding.ActivityNathanBinding
import com.example.applicationplage.framework.AlarmReceiver
import com.example.applicationplage.framework.NotificationService
import com.example.applicationplage.framework.NotificationWorker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.osmdroid.config.Configuration
import java.io.File
import java.util.concurrent.TimeUnit

class NathanActivity : AppCompatActivity() {

    var hasNotificationPermissionGranted = false
    val TAG = "NathanActivity"
    private lateinit var binding: ActivityNathanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNathanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.beachButton.setOnClickListener {
            findNavController(R.id.nav_host_fragment).navigate(R.id.listeFragment)
        }

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        val osmConf = Configuration.getInstance()
        val basePath = File(getCacheDir().getAbsolutePath(), "osmdroid")
        osmConf.osmdroidBasePath = basePath
        val tileCache = File(osmConf.osmdroidBasePath.absolutePath, "tile")
        osmConf.osmdroidTileCache = tileCache
        osmConf.setUserAgentValue(packageName)

        binding.serviceButton.setOnClickListener {
            lancerService()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp()
                || super.onSupportNavigateUp()
    }

    private fun lancerService() {
        Log.d(TAG, "Lancement au service")

        if (Build.VERSION.SDK_INT >= 33) {
            notificationPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
        } else {
            hasNotificationPermissionGranted = true
        }

        if (hasNotificationPermissionGranted) {
            engageTimer()
        }
    }

    private fun showSettingDialog() {
        MaterialAlertDialogBuilder(
            this,
            com.google.android.material.R.style.MaterialAlertDialog_Material3
        )
            .setTitle("Notification Permission")
            .setMessage("Les notifications doivent être activées pour cette application. Voulez-vous activer les notifications ?")
            .setPositiveButton("Ok") { _, _ ->
                val intent = Intent(ACTION_APPLICATION_DETAILS_SETTINGS)
                intent.data = Uri.parse("package : $packageName")
                startActivity(intent)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showNotificationPermissionRationale() {

        MaterialAlertDialogBuilder(
            this,
            com.google.android.material.R.style.MaterialAlertDialog_Material3
        )
            .setTitle("Alert")
            .setMessage("Les notifications doivent être activées pour cette application. ")
            .setPositiveButton("Ok") { _, _ ->
                if (Build.VERSION.SDK_INT >= 33) {
                    notificationPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private val notificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            hasNotificationPermissionGranted = isGranted
            if (!isGranted) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (Build.VERSION.SDK_INT >= 33) {
                        if (shouldShowRequestPermissionRationale(android.Manifest.permission.POST_NOTIFICATIONS)) {
                            showNotificationPermissionRationale()
                        } else {
                            showSettingDialog()
                        }
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Notifications autorisées",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        startService(Intent(this, NotificationService::class.java))
                    }
                }
            }
        }
    private fun engageWorker() {
        val notifWorkRequest =
            PeriodicWorkRequestBuilder<NotificationWorker>(15, TimeUnit.MINUTES)
                .build()

        WorkManager.getInstance(this)
            .enqueueUniquePeriodicWork("notification",
                ExistingPeriodicWorkPolicy.UPDATE,notifWorkRequest)
    }

    private fun engageTimer() {
        val intent = Intent(this, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            System.currentTimeMillis(),
            1000*60*1,
            pendingIntent
        )
    }
}