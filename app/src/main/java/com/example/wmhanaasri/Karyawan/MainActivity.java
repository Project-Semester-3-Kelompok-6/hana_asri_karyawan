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

import com.example.wmhanaasri.adapter.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private HomeFragment homeFragment = new HomeFragment();
    private TugasFragment tugasFragment = new TugasFragment();
    private RekapFragment rekapFragment = new RekapFragment();
    private KaryawanFragment karyawanFragment = new KaryawanFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavbar);
        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);

        //gawe swipe per fragment
        ViewPager2 viewPager = findViewById(R.id.view_pager);
        TabLayout tabLayout = findViewById(R.id.tabs);

        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            // Setel teks tab sesuai dengan posisi
            tab.setText("Tab " + (position + 1));
        }).attach();
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