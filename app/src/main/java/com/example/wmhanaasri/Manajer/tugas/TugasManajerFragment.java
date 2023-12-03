package com.example.wmhanaasri.Manajer.tugas;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wmhanaasri.Manajer.AktifitasAdapter;
import com.example.wmhanaasri.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TugasManajerFragment extends Fragment {

    private RecyclerView recyclerView;
    private AktifitasAdapter adapter;
    private ArrayList<com.example.wmhanaasri.ListAktivitas> AktifitasArrayList;
    private FloatingActionButton btnTambah, btnSesi, btnTugas;
    boolean aBoolean = true;

    public TugasManajerFragment() {
        // Diperlukan konstruktor kosong saat menggunakan fragment
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_tugas, container, false);
        View view = inflater.inflate(R.layout.manajer_fragment_tugas, container, false);

        FloatingActionButton btnTambah = view.findViewById(R.id.btnTambah);
        FloatingActionButton btnSesi = view.findViewById(R.id.btnSesi);
        FloatingActionButton btnTugas = view.findViewById(R.id.btnTugas);
        btnSesi.setVisibility(View.GONE);
        btnTugas.setVisibility(View.GONE);
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                btnSesi.setVisibility(View.VISIBLE);
//                btnTugas.setVisibility(View.VISIBLE);
                if (aBoolean){
                    btnSesi.setVisibility(View.VISIBLE);
                    btnTugas.setVisibility(View.VISIBLE);
                    aBoolean = false;
                }else {
                    btnSesi.setVisibility(View.GONE);
                    btnTugas.setVisibility(View.GONE);
                    aBoolean = true;
                }
            }
        });

        btnSesi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TambahSesiActivity.class);
                startActivity(intent);
            }
        });
        btnTugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TambahTugasActivity.class);
                startActivity(intent);
            }
        });

        //btn tambah tugas
//        btnTambah = view.findViewById(R.id.btnTambah);
//        btnSesi = view.findViewById(R.id.btnSesi);
//        btnTugas = view.findViewById(R.id.btnTugas);
//        btnTambah.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                TambahTugasFragment tambahTugas = new TambahTugasFragment();
//
//                // Ganti tampilan fragmen dalam wadah (FrameLayout) dengan fragmen PresensiFragment
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.flFragment, tambahTugas);
//                transaction.addToBackStack(null); // Untuk menambahkan ke back stack
//                transaction.commit();
//            }
//        });


        return view;
    }
}