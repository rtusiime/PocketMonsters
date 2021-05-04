package com.rtusiime.pocketmonsters

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.preference.PreferenceManager
import com.rtusiime.pocketmonsters.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var  navHostFragment: NavHostFragment
    private val prefs: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_PocketMonsters)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        NavigationUI.setupActionBarWithNavController(this,navHostFragment.navController)

        if(savedInstanceState==null){
            if(prefs.getBoolean(SHOW_MESSAGE_AT_START, false)){
                welcomeAlert()
            }
        }

        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            supportActionBar?.let {
                it.title = when(destination.id){
                    R.id.settingsFragment -> getString(R.string.settings)
                    R.id.infoFragment -> getString(R.string.info)
                    else -> getString(R.string.app_name)
                }
            }
        }
    }

    override fun onSupportNavigateUp() = Navigation.findNavController(this,R.id.nav_host_fragment).navigateUp()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.reset_menuItem ->{
                with(prefs.edit()){
                    remove(SET_DARK_THEME)
                    remove(SHOW_MESSAGE_AT_START)
                    remove(FONT_SIZE)
                    apply()
                }
                true
            }

            R.id.action_settings -> {
                navHostFragment.navController.navigate(R.id.action_mainFragment_to_settingsFragment)
                true
            }
            R.id.action_info -> {
                navHostFragment.navController.navigate(R.id.action_mainFragment_to_infoFragment)
                true
            }
            else  -> super.onOptionsItemSelected(item)

        }



    }
    private fun welcomeAlert() {
        val msg = resources.getString(R.string.welcome)
        val builder = AlertDialog.Builder(this)
        with(builder) {
            setTitle(R.string.welcome)
            setMessage(msg)
            setIcon(R.drawable.pokemon_logo_removebg_preview)
            setPositiveButton(R.string.ok, null)
            show()
        }
    }

    companion object {
        const val SHOW_MESSAGE_AT_START = "show_message_at_start"
        const val SET_DARK_THEME = "set_dark_theme"
        const val FONT_SIZE = "font_size"
    }
}