package com.example.wmhanaasri.Karyawan.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.wmhanaasri.Karyawan.HomeFragment;
import com.example.wmhanaasri.Karyawan.IzinFragment;
import com.example.wmhanaasri.Karyawan.TugasFragment;
import com.example.wmhanaasri.Karyawan.AbsensiFragment;

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
                return new AbsensiFragment();
            case 2:
                return new TugasFragment();
            case 3:
                return new IzinFragment();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 4; // Jumlah tab sesuai dengan jumlah menu navigasi Anda
    }
}
