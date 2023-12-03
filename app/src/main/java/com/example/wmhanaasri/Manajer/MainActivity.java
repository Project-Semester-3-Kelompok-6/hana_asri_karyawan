package com.example.wmhanaasri.Manajer;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.wmhanaasri.Manajer.home.ManajerHomeFragment;
import com.example.wmhanaasri.Manajer.karyawan.KaryawanFragment;
import com.example.wmhanaasri.Manajer.rekap.RekapFragment;
import com.example.wmhanaasri.Manajer.tugas.TugasManajerFragment;
import com.example.wmhanaasri.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private ManajerHomeFragment manajerHomeFragment = new ManajerHomeFragment();
    private TugasManajerFragment tugasManajerFragment = new TugasManajerFragment();
    private RekapFragment rekapFragment = new RekapFragment();
    private KaryawanFragment karyawanFragment = new KaryawanFragment();
    private ImageButton imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavbar);
        bottomNavigationView.setOnItemSelectedListener(this);
        if (savedInstanceState == null) {
            // Jika savedInstanceState null, maka aplikasi baru saja dimulai
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, manajerHomeFragment).commit();
        }


//        ImageButton btnTugas = findViewById(R.id.btnTugas);

//        btnTugas.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Ganti tampilan fragmen ke fragment_tugas
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.flFragment, new TugasManajerFragment())
//                        .commit();
//
//                // Setel item navigasi bawah ke "Tugas"
//                bottomNavigationView.setSelectedItemId(R.id.tugas);
//            }
//        });


    }
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        int itemId = item.getItemId();
//
//        if (itemId == R.id.home) {
//            // Ganti tampilan fragmen tanpa menekan tombol navigasi
//            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, manajerHomeFragment).commit();
//            return true;
//        } else if (itemId == R.id.tugas) {
//            // Ganti tampilan fragmen tanpa menekan tombol navigasi
//            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, tugasManajerFragment).commit();
//            return true;
//        } else if (itemId == R.id.rekap) {
//            // Ganti tampilan fragmen tanpa menekan tombol navigasi
//            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, rekapFragment).commit();
//            return true;
//        } else if (itemId == R.id.karyawan) {
//            // Ganti tampilan fragmen tanpa menekan tombol navigasi
//            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, karyawanFragment).commit();
//            return true;
//        }
//        return false;
//    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;

        int itemId = item.getItemId(); // Simpan nilai item.getItemId() dalam variabel itemId

        if (itemId == R.id.homeManajer) {
            selectedFragment = new ManajerHomeFragment();
        } else if (itemId == R.id.tugasManajer) {
            selectedFragment = new TugasManajerFragment();
        } else if (itemId == R.id.rekapManajer) {
            selectedFragment = new RekapFragment();
        } else if (itemId == R.id.karyawanManajer) {
            selectedFragment = new KaryawanFragment();
        }

        if (selectedFragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, selectedFragment).commit();
            return true;
        }

        return false;
    }

}