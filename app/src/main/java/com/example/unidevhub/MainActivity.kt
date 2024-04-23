package com.example.unidevhub

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.unidevhub.NavbarFragments.AboutFragment
import com.example.unidevhub.NavbarFragments.HomeFragment
import com.example.unidevhub.NavbarFragments.SettingFragment
import com.example.unidevhub.NavbarFragments.ShareFragment
import com.example.unidevhub.auth.LoginActivity
import com.example.unidevhub.databinding.ActivityMainBinding
import com.example.unidevhub.navbar_Activities.AboutUsActivity
import com.example.unidevhub.navbar_Activities.RateAppActivity
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.qamar.curvedbottomnaviagtion.CurvedBottomNavigation

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener, NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding : ActivityMainBinding
    private lateinit var navController: NavController

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        with(binding) {
            setContentView(root)
            initNavHost()
            setUpBottomNavigation()
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.statusBarColor)
        }

//        val toolbar: Toolbar =
//
//        setSupportActionBar(toolbar)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)



        drawerLayout = findViewById(R.id.drawLayout)
        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

//        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav)
//        drawerLayout.addDrawerListener(toggle)
//        toggle.syncState()

//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment1, HomeFragment()).commit()
//            navigationView.setCheckedItem(R.id.nav_home)
//        }

    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment1, HomeFragment()).commit()
            R.id.nav_settings -> supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment1, SettingFragment()).commit()
            R.id.nav_share ->{

                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                val appLink = "https://play.google.com/store/apps/details?id=${packageName}"
                val shareMessage = "Check out UniDevHub, the awesome Open Source contribution app!\n$appLink"
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                startActivity(Intent.createChooser(shareIntent, "Share UniDevHub"))
                return true
            }
            R.id.nav_about -> {

                val intent = Intent(this, AboutUsActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out)

                return true

            }
            R.id.nav_logout -> {
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                return true
            }
            R.id.nav_rateUs -> {
                val intent = Intent(this, RateAppActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out)
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (navController.currentDestination!!.id == HOME_ITEM)
            super.onBackPressed()
        else {
            when (navController.currentDestination!!.id) {
                HOME_ITEM -> {
                    navController.popBackStack(R.id.homeFragment, false)
                }
                Connection_ITEM -> {
                    navController.popBackStack(R.id.connectionFragment, false)
                }
                Project_ITEM -> {
                    navController.popBackStack(R.id.projectFragment, false)
                }
                Community_ITEM -> {
                    navController.popBackStack(R.id.communityFragment, false)
                }
                Profile_ITEM -> {
                    navController.popBackStack(R.id.profileFragment, false)
                }
                else -> {
                    navController.navigateUp()
                }
            }
        }



        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    private fun ActivityMainBinding.setUpBottomNavigation() {
        val bottomNavigationItems = mutableListOf(
            CurvedBottomNavigation.Model(HOME_ITEM, getString(R.string.home), R.drawable.home),
            CurvedBottomNavigation.Model(Connection_ITEM, getString(R.string.connection), R.drawable.connection),
            CurvedBottomNavigation.Model(Project_ITEM, getString(R.string.project), R.drawable.project),
            CurvedBottomNavigation.Model(Community_ITEM, getString(R.string.community), R.drawable.community),
            CurvedBottomNavigation.Model(Profile_ITEM, getString(R.string.profile), R.drawable.profile),




            )
        bottomNavigation.apply {
            bottomNavigationItems.forEach { add(it) }
            setOnClickMenuListener {
                navController.navigate(it.id)
            }
            // optional
            setupNavController(navController)
        }
    }

    private fun initNavHost() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment1) as NavHostFragment
        navController = navHostFragment.navController
    }

    companion object {
        // you can put any unique id here, but because I am using Navigation Component I prefer to put it as
        // the fragment id.
        val HOME_ITEM = R.id.homeFragment
        val Connection_ITEM = R.id.connectionFragment
        val Project_ITEM = R.id.projectFragment
        val Community_ITEM = R.id.communityFragment
        val Profile_ITEM = R.id.profileFragment
    }




}