package com.example.wmhanaasri.Manajer.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private AktifitasAdapter adapter;
    private ArrayList<ListAktivitas> AktifitasArrayList;
    private ImageView imgView,btnTugas,btnProfile;
    private TextView textView;
    private TextView textViewNama,textViewJabatan;
    SharedPreferences sharedPreferences;


    public HomeFragment() {
        // Required empty public constructor
    }



    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.manajer_fragment_home, container, false);
        TextView textView = view.findViewById(R.id.tanggal);
        textViewNama = view.findViewById(R.id.tv_user);
        textViewJabatan = view.findViewById(R.id.jabatan);

        //atur nama dan jabatan
        SharedPreferences preferences = requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String nama = preferences.getString("nama", "");
        String jabatan = preferences.getString("jabatan", "");
        textViewNama.setText(nama);
        textViewJabatan.setText(jabatan);

        String currentDate = getCurrentDate();
        textView.setText(currentDate);



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
    private String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, d MMMM yyyy", Locale.getDefault());
        return dateFormat.format(calendar.getTime());
    }


    void addData(){
        AktifitasArrayList = new ArrayList<>();
        AktifitasArrayList.add(new ListAktivitas("Upload Menu Baru", "Gilang", "14 Oktober 2023"));
        AktifitasArrayList.add(new ListAktivitas("Upload Menu Baru", "Gilang", "14 Oktober 2023"));
        AktifitasArrayList.add(new ListAktivitas("Upload Menu Baru", "Gilang", "14 Oktober 2023"));
        AktifitasArrayList.add(new ListAktivitas("Restok Bahan", "Rizqi", "15 Oktober 2023"));
    }
}