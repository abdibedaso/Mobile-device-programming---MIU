package androidquiz.app.ui

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidquiz.app.R
import androidquiz.app.util.util_interface.BackPressed


class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        // Set status bar color
        val window = this.window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

        navController.addOnDestinationChangedListener { _, navDestination, _ ->
            when (navDestination.id) {
                R.id.resultFragment -> {
                    window.statusBarColor = this.getColor(R.color.colorPrimary)
                }
                R.id.historyFragment -> {
                    window.statusBarColor = this.getColor(R.color.lightBlue)
                }
                else -> {
                    window.statusBarColor = this.getColor(R.color.darkBlue1)
                }
            }
        }
    }

    override fun onBackPressed() {
        when (navController.currentDestination!!.id) {
            R.id.playFragment -> {
                val navHostFragment =
                    supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
                val fragment = navHostFragment!!.childFragmentManager.fragments[0]
                (fragment as BackPressed).backPressed()
            }
            R.id.homeFragment -> {
                finish()
            }
            else -> {
                super.onBackPressed()
            }
        }
    }
}
