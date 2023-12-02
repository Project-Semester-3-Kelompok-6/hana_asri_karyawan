package com.example.wmhanaasri.Karyawan.tugas.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.wmhanaasri.Karyawan.tugas.KaryawanSedangDikerjakan;
import com.example.wmhanaasri.Karyawan.tugas.KaryawanSelesaiDikerjakan;
import com.example.wmhanaasri.Karyawan.tugas.TugasFragment;

public class TugasKaryawanAdapter extends FragmentStateAdapter {
    public TugasKaryawanAdapter(@NonNull TugasFragment fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return new KaryawanSedangDikerjakan();
            case 1: return new KaryawanSelesaiDikerjakan();
            default: return  new KaryawanSedangDikerjakan();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
