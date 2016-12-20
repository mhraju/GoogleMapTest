package com.mhraju.googlemaptest;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Fragment fragment = new MapDetailsFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MapActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
