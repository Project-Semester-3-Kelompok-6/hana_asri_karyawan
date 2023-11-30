package com.example.wmhanaasri.Manajer.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wmhanaasri.ListAktivitas;
import com.example.wmhanaasri.Login.LoginActivity;
import com.example.wmhanaasri.Manajer.AktifitasAdapter;
import com.example.wmhanaasri.Manajer.PresensiFragment;
import com.example.wmhanaasri.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private AktifitasAdapter adapter;
    private ArrayList<ListAktivitas> AktifitasArrayList;
    private ImageView imgView,btnTugas,btnProfile;
    private TextView textView;

    public HomeFragment() {
        // Required empty public constructor
    }



    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.manajer_fragment_home, container, false);


        recyclerView = view.findViewById(R.id.recycle_viewHome);

        // Membuat objek ArrayList Aktifitas
        AktifitasArrayList = new ArrayList<ListAktivitas>();

        // Menambahkan data ke ArrayList Aktifitas
        addData();

        // Membuat dan mengatur adapter
        adapter = new AktifitasAdapter(AktifitasArrayList);

        // Membuat dan mengatur layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity()); // Gunakan getActivity() karena Anda berada dalam fragmen

        // Mengatur layout manager dan adapter untuk RecyclerView
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        imgView = view.findViewById(R.id.btnPresensi);
        btnTugas = view.findViewById(R.id.btnTugas);
        btnProfile = view.findViewById(R.id.btnProfile);

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), LoginActivity.class);
                startActivity(intent);
                requireActivity().finish();
            }
        });
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Buat objek PresensiFragment
                PresensiFragment presensiFragment = new PresensiFragment();

                // Ganti tampilan fragmen dalam wadah (FrameLayout) dengan fragmen PresensiFragment
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.flFragment, presensiFragment);
                transaction.addToBackStack(null); // Untuk menambahkan ke back stack
                transaction.commit();
            }
        });

//        imgView = view.findViewById(R.id.btnTugas);
//        imgView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Buat objek PresensiFragment
//                TugasFragment tugasFragment = new TugasFragment();
//
//                // Ganti tampilan fragmen dalam wadah (FrameLayout) dengan fragmen PresensiFragment
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.flFragment, tugasFragment);
//                transaction.addToBackStack(null); // Untuk menambahkan ke back stack
//                transaction.commit();
//            }
//        });

        // view
        return view;
    }


    void addData(){
        AktifitasArrayList = new ArrayList<>();
        AktifitasArrayList.add(new ListAktivitas("Upload Menu Baru", "Gilang", "14 Oktober 2023"));
        AktifitasArrayList.add(new ListAktivitas("Upload Menu Baru", "Gilang", "14 Oktober 2023"));
        AktifitasArrayList.add(new ListAktivitas("Upload Menu Baru", "Gilang", "14 Oktober 2023"));
        AktifitasArrayList.add(new ListAktivitas("Restok Bahan", "Rizqi", "15 Oktober 2023"));
    }
}