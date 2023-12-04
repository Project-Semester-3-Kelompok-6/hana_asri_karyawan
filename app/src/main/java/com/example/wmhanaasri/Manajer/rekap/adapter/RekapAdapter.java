package com.example.wmhanaasri.Manajer.rekap.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.wmhanaasri.Manajer.rekap.RekapFragment;
import com.example.wmhanaasri.Manajer.rekap.RekapListAbsensi;
import com.example.wmhanaasri.Manajer.rekap.RekapListTugas;

public class RekapAdapter extends FragmentStateAdapter {
    public RekapAdapter(@NonNull RekapFragment fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return new RekapListAbsensi();
            case 1: return new RekapListTugas();
            default: return  new RekapListAbsensi();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
