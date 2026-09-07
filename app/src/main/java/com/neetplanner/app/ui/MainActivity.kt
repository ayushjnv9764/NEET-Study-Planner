package com.neetplanner.app.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.neetplanner.app.R
import com.neetplanner.app.data.database.NEETStudyDatabase
import com.neetplanner.app.data.repository.StudySessionRepository
import com.neetplanner.app.databinding.ActivityMainBinding
import com.neetplanner.app.ui.viewmodel.StudySessionViewModel
import com.neetplanner.app.ui.viewmodel.StudySessionViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var studySessionViewModel: StudySessionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize database and repository
        val database = NEETStudyDatabase.getDatabase(this)
        val repository = StudySessionRepository(database.studySessionDao())
        val factory = StudySessionViewModelFactory(repository)
        studySessionViewModel = ViewModelProvider(this, factory).get(StudySessionViewModel::class.java)

        // Setup ActionBar
        setSupportActionBar(binding.toolbar)

        // Setup Navigation
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment, R.id.scheduleFragment, R.id.progressFragment, R.id.settingsFragment)
        )

        setupActionBarWithNavController(navController, appBarConfiguration)

        // Setup Bottom Navigation
        val bottomNav: BottomNavigationView = binding.bottomNavigation
        bottomNav.setupWithNavController(navController)

        // Setup FAB click listener
        binding.fab.setOnClickListener {
            navController.navigate(R.id.addStudySessionFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                navController.navigate(R.id.settingsFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
