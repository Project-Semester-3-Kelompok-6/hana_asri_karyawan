package com.example.wmhanaasri.Karyawan.absensi;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.wmhanaasri.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class AbsensiFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.karyawan_fragment_absensi, viewGroup, false);

        ImageButton btnAbsenMasuk = view.findViewById(R.id.btn_absenMasuk);
        ImageButton btnAbsenKeluar = view.findViewById(R.id.btn_absenKeluar);
        ImageButton btnLaporan = view.findViewById(R.id.btn_laporan);

        // Set OnClickListener for btnAbsenMasuk
        btnAbsenMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AbsensiMasukActivity.class);
                startActivity(intent);
            }
        });

        // Set OnClickListener for btnAbsenKeluar
        btnAbsenKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AbsensiKeluarActivity.class);
                startActivity(intent);
            }
        });

        // Set OnClickListener for btnLaporan
        btnLaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AbsensiLaporanActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
