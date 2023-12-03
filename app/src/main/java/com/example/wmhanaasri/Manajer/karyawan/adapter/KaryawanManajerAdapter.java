package com.example.wmhanaasri.Manajer.karyawan.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.wmhanaasri.Karyawan.tugas.KaryawanSedangDikerjakan;
import com.example.wmhanaasri.Karyawan.tugas.KaryawanSelesaiDikerjakan;
import com.example.wmhanaasri.Manajer.karyawan.KaryawanDevisi;
import com.example.wmhanaasri.Manajer.karyawan.KaryawanFragment;
import com.example.wmhanaasri.Manajer.karyawan.KaryawanTetap;
import com.example.wmhanaasri.Manajer.karyawan.KaryawanTidakTetap;

public class KaryawanManajerAdapter extends FragmentStateAdapter {
    public KaryawanManajerAdapter(@NonNull KaryawanFragment fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return new KaryawanTetap();
            case 1: return new KaryawanTidakTetap();
            case 2: return new KaryawanDevisi();
            default: return  new KaryawanTetap();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
