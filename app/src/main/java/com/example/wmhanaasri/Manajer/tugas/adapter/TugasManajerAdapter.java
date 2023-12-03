package com.example.wmhanaasri.Manajer.tugas.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.wmhanaasri.Manajer.karyawan.KaryawanTetap;
import com.example.wmhanaasri.Manajer.karyawan.KaryawanTidakTetap;
import com.example.wmhanaasri.Manajer.tugas.TugasManajerDitugaskan;
import com.example.wmhanaasri.Manajer.tugas.TugasManajerFragment;
import com.example.wmhanaasri.Manajer.tugas.TugasManajerSelesai;

public class TugasManajerAdapter extends FragmentStateAdapter {
    public TugasManajerAdapter(@NonNull TugasManajerFragment fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return new TugasManajerDitugaskan();
            case 1: return new TugasManajerSelesai();
            default: return  new TugasManajerDitugaskan();
        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
