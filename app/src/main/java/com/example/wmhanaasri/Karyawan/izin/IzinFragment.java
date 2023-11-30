package com.example.wmhanaasri.Karyawan.izin;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.wmhanaasri.R;

public class IzinFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.karyawan_fragment_izin, container, false);

        // Temukan ImageButton dari layout
        ImageButton btnIzin = view.findViewById(R.id.btn_absenMasuk);
        ImageButton btnDaftar = view.findViewById(R.id.btn_daftarIzin);
        ImageButton btnKetentuan = view.findViewById(R.id.btn_Ketentuan);

        // Atur OnClickListener untuk ImageButton
        btnIzin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent untuk beralih ke PerizinanActivity
                Intent intent = new Intent(getActivity(), PerizinanActivity.class);
                startActivity(intent);
            }
        });


        btnKetentuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent untuk beralih ke PerizinanActivity
                Intent intent = new Intent(getActivity(), KetentuanActivity.class);
                startActivity(intent);
            }
        });

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent untuk beralih ke PerizinanActivity
                Intent intent = new Intent(getActivity(), DaftarIzin.class);
                startActivity(intent);
            }
        });


        return view;
    }
}