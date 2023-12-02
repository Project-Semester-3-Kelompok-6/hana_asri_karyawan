package com.example.wmhanaasri.Karyawan.tugas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;

import com.example.wmhanaasri.Karyawan.tugas.adapter.TugasKaryawanAdapter;
import com.example.wmhanaasri.ListAktivitas;
import com.example.wmhanaasri.R;

import java.util.ArrayList;

import com.example.wmhanaasri.Karyawan.adapter.AktifitasAdapter;
import com.google.android.material.tabs.TabLayout;

import com.example.wmhanaasri.Karyawan.home.KaryawanMainActivity.*;

public class TugasFragment extends Fragment {
    //dummy
    private RecyclerView recyclerView;
    private AktifitasAdapter adapter;
    private ArrayList<ListAktivitas> AktifitasArrayList;
    private ImageView imgView;
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    TugasKaryawanAdapter tugasKaryawanAdapter;
    FrameLayout frameLayout;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.karyawan_fragment_tugas, container, false);

        tabLayout = view.findViewById(R.id.tabTugasKaryawan);
        viewPager2 = view.findViewById(R.id.viewPagerTugasKaryawan);
        tugasKaryawanAdapter = new TugasKaryawanAdapter(this);
        viewPager2.setAdapter(tugasKaryawanAdapter);
        frameLayout = view.findViewById(R.id.sedangDikerjakan);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
                        tabLayout.getTabAt(position).select();
                }
                super.onPageSelected(position);
            }
        });


        //dummy
        recyclerView = view.findViewById(R.id.recycler_viewTugas);

        // Membuat objek ArrayList Aktifitas
        AktifitasArrayList = new ArrayList<ListAktivitas>();

        // Menambahkan data ke ArrayList Aktifitas
        addData();

        // Membuat dan mengatur adapter
        adapter = new AktifitasAdapter(AktifitasArrayList);

        // Membuat dan mengatur layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity()); // Gunakan getActivity() karena Anda berada dalam fragmen

        // Mengatur layout manager dan adapter untuk RecyclerView
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }
    void addData(){
        AktifitasArrayList = new ArrayList<>();
        AktifitasArrayList.add(new ListAktivitas("Upload Menu Baru", "Gilang", "14 Oktober 2023"));
        AktifitasArrayList.add(new ListAktivitas("Upload Menu Baru", "Gilang", "14 Oktober 2023"));
        AktifitasArrayList.add(new ListAktivitas("Upload Menu Baru", "Gilang", "14 Oktober 2023"));
        AktifitasArrayList.add(new ListAktivitas("Restok Bahan", "Rizqi", "15 Oktober 2023"));
    }
}