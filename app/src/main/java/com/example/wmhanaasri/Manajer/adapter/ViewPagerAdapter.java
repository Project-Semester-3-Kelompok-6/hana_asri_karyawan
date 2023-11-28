package com.example.wmhanaasri.Manajer.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.wmhanaasri.Manajer.home.HomeFragment;
import com.example.wmhanaasri.Manajer.karyawan.KaryawanFragment;
import com.example.wmhanaasri.Manajer.rekap.RekapFragment;
import com.example.wmhanaasri.Manajer.tugas.TugasFragment;

public class ViewPagerAdapter extends FragmentStateAdapter{
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity){
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new HomeFragment(); // Ganti dengan fragmen yang sesuai
            case 1:
                return new TugasFragment();
            case 2:
                return new RekapFragment();
            case 3:
                return new KaryawanFragment();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 4; // Jumlah tab sesuai dengan jumlah menu navigasi Anda
    }
}
