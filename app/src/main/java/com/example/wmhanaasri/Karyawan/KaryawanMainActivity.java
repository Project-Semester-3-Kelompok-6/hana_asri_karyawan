package com.example.wmhanaasri.Karyawan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.wmhanaasri.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import com.example.wmhanaasri.Karyawan.adapter.ViewPagerAdapter;

public class KaryawanMainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private HomeFragment homeFragment = new HomeFragment();
    private AbsensiFragment tugasFragment = new AbsensiFragment();
    private TugasFragment rekapFragment = new TugasFragment();
    private IzinFragment karyawanFragment = new IzinFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavbar);
        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);

        //gawe swipe per fragment
        ViewPager2 viewPager = findViewById(R.id.view_pager);


        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        int position = 0;       //gawe nentukan posisi tab sng digawe karo ViewPager beno iso diswipe

        if (itemId == R.id.home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, homeFragment).commit();
            return true;
        } else if (itemId == R.id.absensi) {
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, tugasFragment).commit();
            return true;
        } else if (itemId == R.id.tugas) {
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, rekapFragment).commit();
            return true;
        } else if (itemId == R.id.izin) {
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, karyawanFragment).commit();
            return true;
        }
        return false;
    }
}