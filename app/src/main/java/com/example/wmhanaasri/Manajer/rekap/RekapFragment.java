package com.example.wmhanaasri.Manajer.rekap;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.wmhanaasri.ListAktivitas;
import com.example.wmhanaasri.Manajer.AktifitasAdapter;
import com.example.wmhanaasri.Manajer.karyawan.adapter.KaryawanManajerAdapter;
import com.example.wmhanaasri.Manajer.rekap.adapter.RekapAdapter;
import com.example.wmhanaasri.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class RekapFragment extends Fragment {

    TabLayout tabLayoutRekapManajer;
    ViewPager2 viewPager2;
    RekapAdapter rekapAdapter;
    FrameLayout frameLayout;
    public RekapFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.manajer_fragment_rekap, container, false);

        tabLayoutRekapManajer = view.findViewById(R.id.tabRekapManajer);
        viewPager2 = view.findViewById(R.id.viewPagerRekapManajer);
        rekapAdapter = new RekapAdapter(this);
        viewPager2.setAdapter(rekapAdapter);
        frameLayout = view.findViewById(R.id.listRekapManajer);

        tabLayoutRekapManajer.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
                        tabLayoutRekapManajer.getTabAt(position).select();
                }
                super.onPageSelected(position);
            }
        });

        return view;
    }

}