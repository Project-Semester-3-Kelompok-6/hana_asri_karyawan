package com.example.wmhanaasri.Manajer.tugas;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wmhanaasri.ListAktivitas;
import com.example.wmhanaasri.Manajer.AktifitasAdapter;
import com.example.wmhanaasri.Manajer.TambahTugasFragment;
import com.example.wmhanaasri.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TugasFragment extends Fragment {

    private RecyclerView recyclerView;
    private AktifitasAdapter adapter;
    private ArrayList<com.example.wmhanaasri.ListAktivitas> AktifitasArrayList;
    private FloatingActionButton btnTambah, btnSesi, btnTugas;
    boolean aBoolean = true;

    public TugasFragment() {
        // Diperlukan konstruktor kosong saat menggunakan fragment
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_tugas, container, false);
        View view = inflater.inflate(R.layout.manajer_fragment_tugas, container, false);
        recyclerView = view.findViewById(R.id.recycle_viewHome);

        // Membuat objek ArrayList Aktifitas
        AktifitasArrayList = new ArrayList<com.example.wmhanaasri.ListAktivitas>();

        // Menambahkan data ke ArrayList Aktifitas
        addData();

        // Membuat dan mengatur adapter
        adapter = new AktifitasAdapter(AktifitasArrayList);

        // Membuat dan mengatur layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity()); // Gunakan getActivity() karena Anda berada dalam fragmen

        // Mengatur layout manager dan adapter untuk RecyclerView
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

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
    void addData(){
        AktifitasArrayList = new ArrayList<>();
        AktifitasArrayList.add(new com.example.wmhanaasri.ListAktivitas("Upload Menu Baru", "Gilang", "14 Oktober 2023"));
        AktifitasArrayList.add(new com.example.wmhanaasri.ListAktivitas("Upload Menu Baru", "Gilang", "14 Oktober 2023"));
        AktifitasArrayList.add(new com.example.wmhanaasri.ListAktivitas("Upload Menu Baru", "Gilang", "14 Oktober 2023"));
        AktifitasArrayList.add(new com.example.wmhanaasri.ListAktivitas("Restok Bahan", "Rizqi", "15 Oktober 2023"));
        AktifitasArrayList.add(new com.example.wmhanaasri.ListAktivitas("Restok Bahan", "Rizqi", "15 Oktober 2023"));
        AktifitasArrayList.add(new com.example.wmhanaasri.ListAktivitas("Upload Feed IG", "Ramadhan", "16 Oktober 2023"));
        AktifitasArrayList.add(new com.example.wmhanaasri.ListAktivitas("Upload Feed IG", "Ramadhan", "16 Oktober 2023"));
        AktifitasArrayList.add(new com.example.wmhanaasri.ListAktivitas("Upload Feed IG", "Ramadhan", "16 Oktober 2023"));
        AktifitasArrayList.add(new ListAktivitas("Upload Feed IG", "Ramadhan", "16 Oktober 2023"));
    }
}