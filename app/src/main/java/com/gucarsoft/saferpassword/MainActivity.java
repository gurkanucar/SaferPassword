package com.gucarsoft.saferpassword;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.gucarsoft.saferpassword.Views.Login;
import com.gucarsoft.saferpassword.Views.SetPassword;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_passwords, R.id.navigation_add, R.id.navigation_settings)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        if (getIntent().getExtras() != null) {
            if (getIntent().getExtras().getInt("isLogin", 0) == 0) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        }
        else
        {
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
        }
    }

}
