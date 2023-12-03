package com.example.wmhanaasri.Manajer.tugas;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.wmhanaasri.Karyawan.tugas.adapter.TugasKaryawanAdapter;
import com.example.wmhanaasri.Manajer.AktifitasAdapter;
import com.example.wmhanaasri.Manajer.tugas.adapter.TugasManajerAdapter;
import com.example.wmhanaasri.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class TugasManajerFragment extends Fragment {

    private RecyclerView recyclerView;
    private AktifitasAdapter adapter;
    private ArrayList<com.example.wmhanaasri.ListAktivitas> AktifitasArrayList;
    private FloatingActionButton btnTambah, btnSesi, btnTugas;
    boolean aBoolean = true;
    TabLayout tabLayoutManajer;
    ViewPager2 viewPager2;
    TugasManajerAdapter tugasManajerAdapter;
    FrameLayout frameLayout;

    public TugasManajerFragment() {
        // Diperlukan konstruktor kosong saat menggunakan fragment
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_tugas, container, false);
        View view = inflater.inflate(R.layout.manajer_fragment_tugas, container, false);

        FloatingActionButton btnTambah = view.findViewById(R.id.btnTambah);
        FloatingActionButton btnSesi = view.findViewById(R.id.btnSesi);
        FloatingActionButton btnTugas = view.findViewById(R.id.btnTugas);
        btnSesi.setVisibility(View.GONE);
        btnTugas.setVisibility(View.GONE);

        tabLayoutManajer = view.findViewById(R.id.tabTugasManajer);
        viewPager2 = view.findViewById(R.id.viewPagerTugasManager);
        tugasManajerAdapter = new TugasManajerAdapter(this);
        viewPager2.setAdapter(tugasManajerAdapter);
        frameLayout = view.findViewById(R.id.sedangDitugaskanManajer);

        tabLayoutManajer.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setVisibility(View.VISIBLE);
                frameLayout.setVisibility(View.GONE);
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager2.setVisibility(View.VISIBLE);
                frameLayout.setVisibility(View.GONE);
            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                    case 1:
                    case 2:
                        tabLayoutManajer.getTabAt(position).select();
                }
                super.onPageSelected(position);
            }
        });
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                btnSesi.setVisibility(View.VISIBLE);
//                btnTugas.setVisibility(View.VISIBLE);
                if (aBoolean){
                    btnSesi.setVisibility(View.VISIBLE);
                    btnTugas.setVisibility(View.VISIBLE);
                    aBoolean = false;
                }else {
                    btnSesi.setVisibility(View.GONE);
                    btnTugas.setVisibility(View.GONE);
                    aBoolean = true;
                }
            }
        });

        btnSesi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TambahSesiActivity.class);
                startActivity(intent);
            }
        });
        btnTugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TambahTugasActivity.class);
                startActivity(intent);
            }
        });

        //btn tambah tugas
//        btnTambah = view.findViewById(R.id.btnTambah);
//        btnSesi = view.findViewById(R.id.btnSesi);
//        btnTugas = view.findViewById(R.id.btnTugas);
//        btnTambah.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                TambahTugasFragment tambahTugas = new TambahTugasFragment();
//
//                // Ganti tampilan fragmen dalam wadah (FrameLayout) dengan fragmen PresensiFragment
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.flFragment, tambahTugas);
//                transaction.addToBackStack(null); // Untuk menambahkan ke back stack
//                transaction.commit();
//            }
//        });


        return view;
    }
}