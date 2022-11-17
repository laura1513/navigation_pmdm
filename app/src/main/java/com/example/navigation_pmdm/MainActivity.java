package com.example.navigation_pmdm;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.navigation_pmdm.databinding.ActivityBottomBinding;
import com.example.navigation_pmdm.databinding.ActivityDrawerBinding;
import com.example.navigation_pmdm.databinding.ActivityMainBinding;
import com.example.navigation_pmdm.databinding.ActivityOptionsBinding;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((binding = ActivityMainBinding.inflate(getLayoutInflater())).getRoot());

        setSupportActionBar(binding.toolbar);

        AppBarConfiguration appBarConfigurationDrawer = new AppBarConfiguration.Builder(
                // Top-level destinations:
                R.id.drawer1Fragment, R.id.drawer2Fragment
        )
                .setOpenableLayout(binding.drawerLayout)
                .build();
        NavController navControllerDrawer = ((NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_drawer_fragment)).getNavController();
        NavigationUI.setupWithNavController(binding.navView, navControllerDrawer);
        NavigationUI.setupWithNavController(binding.toolbar, navControllerDrawer, appBarConfigurationDrawer);

        AppBarConfiguration appBarConfigurationOptions = new AppBarConfiguration.Builder(
                R.id.options1Fragment, R.id.options2Fragment
        )
                .build();

        navController = ((NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_options_fragment)).getNavController();
        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfigurationOptions);

        NavController navControllerBottom = ((NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_bottom_fragment)).getNavController();
        NavigationUI.setupWithNavController(binding.bottomNavView, navControllerBottom);
        NavigationUI.setupWithNavController(binding.toolbar, navControllerBottom);

        /*binding.actionGotoDrawerActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DrawerActivity.class));
            }
        });

        binding.actionGotoBottomActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BottomActivity.class));
            }
        });

        binding.actionGotoOptionsActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, OptionsActivity.class));
            }
        });

        binding.actionGotoTabbedActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TabbedActivity.class));
            }
        });*/
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return NavigationUI.onNavDestinationSelected(item, navController)
                || super.onOptionsItemSelected(item);
    }
}