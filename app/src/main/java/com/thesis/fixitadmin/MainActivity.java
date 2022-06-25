package com.thesis.fixitadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.thesis.fixitadmin.notifications.Token;
import com.thesis.fixitadmin.user.HomeFragment;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Fragment selectedFragment = null;
    private String userId;
    SharedPref sharedPref;


    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);
        if (sharedPref.loadNightModeState()){
            setTheme(R.style.DarkTheme);
        }else setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        userId = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(nevigationSelected);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit();
        updateToken(FirebaseInstanceId.getInstance().getToken());



    }

    private void updateToken(String token){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Tokens");
        Token mToken = new Token(token);
        ref.child(userId).setValue(mToken);
    }


    private final BottomNavigationView.OnNavigationItemSelectedListener nevigationSelected =
            item -> {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        selectedFragment = new HomeFragment();

                        break;
                    case R.id.nav_search:
                        Intent intent = new Intent(this, activity_analytics.class);
                        startActivity(intent);
                        break;
//                    case R.id.nav_group:
//                        selectedFragment = new MapsFragment();
//                        break;
//                    case R.id.nav_chat:
//                        selectedFragment = new ChatFragment();
//                        break;
//                    case R.id.nav_user:
//                        SharedPreferences.Editor editor = getSharedPreferences("PREFS", MODE_PRIVATE).edit();
//                        editor.putString("profileid", Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid());
//                        editor.apply();
//                        selectedFragment = new ProfileFragment();
//                        break;
                }
                if (selectedFragment != null){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                }
                return true;
            };
}


