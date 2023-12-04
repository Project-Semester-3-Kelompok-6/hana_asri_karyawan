package com.example.wmhanaasri.Manajer.karyawan;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.wmhanaasri.Connection.DBConnect;
import com.example.wmhanaasri.Karyawan.adapter.AktifitasAdapter;
import com.example.wmhanaasri.Karyawan.tugas.adapter.TugasKaryawanAdapter;
import com.example.wmhanaasri.ListAktivitas;
import com.example.wmhanaasri.Manajer.karyawan.adapter.KaryawanManajerAdapter;
import com.example.wmhanaasri.Manajer.tugas.adapter.TugasManajerAdapter;
import com.example.wmhanaasri.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class KaryawanFragment extends Fragment {

    private FloatingActionButton btnTambah;
    boolean aBoolean = true;
    private RecyclerView recyclerView;
    private AktifitasAdapter adapter;
    private ArrayList<ListAktivitas> AktifitasArrayList;

    TabLayout tabLayoutKaryawanManajer;
    ViewPager2 viewPager2;
    KaryawanManajerAdapter karyawanManajerAdapter;
    FrameLayout frameLayout;


    public KaryawanFragment() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.manajer_fragment_karyawan, container, false);

        FloatingActionButton btnTambah = view.findViewById(R.id.btnTambahKaryawan);
        FloatingActionButton btnTambahAkun = view.findViewById(R.id.btnTambahAkun);
        FloatingActionButton btnTambahDevisi = view.findViewById(R.id.btnTambahDevisi);
        btnTambahAkun.setVisibility(View.GONE);
        btnTambahDevisi.setVisibility(View.GONE);


        tabLayoutKaryawanManajer = view.findViewById(R.id.tabKaryawanManajer);
        viewPager2 = view.findViewById(R.id.viewPagerKaryawanManajer);
        karyawanManajerAdapter = new KaryawanManajerAdapter(this);
        viewPager2.setAdapter(karyawanManajerAdapter);
        frameLayout = view.findViewById(R.id.karyawanListManajer);

        tabLayoutKaryawanManajer.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setVisibility(View.VISIBLE);
                frameLayout.setVisibility(View.GONE);
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager2.setVisibility(View.VISIBLE);
                frameLayout.setVisibility(View.GONE);
            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                    case 1:
                    case 2:
                        tabLayoutKaryawanManajer.getTabAt(position).select();
                }
                super.onPageSelected(position);
            }
        });

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (aBoolean){
                    btnTambahAkun.setVisibility(View.VISIBLE);
                    btnTambahDevisi.setVisibility(View.VISIBLE);
                    aBoolean = false;
                }else {
                    btnTambahAkun.setVisibility(View.GONE);
                    btnTambahDevisi.setVisibility(View.GONE);
                    aBoolean = true;
                }
                fetchData();
            }
        });

        btnTambahAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RequestQueue requestQueue = Volley.newRequestQueue(requireContext());

                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                        Request.Method.GET,
                        DBConnect.getDevisi,
                        null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                Set<String> devisiSet = new HashSet<>();

                                try {
                                    // Ambil data dari respons JSON
                                    for (int i = 0; i < response.length(); i++) {
                                        JSONObject devisi = response.getJSONObject(i);
                                        String devisiID = devisi.getString("DevisiID");
                                        String namaDevisi = devisi.getString("NamaDevisi");

                                        // Gabungkan DevisiID dan NamaDevisi dalam satu string
                                        String devisiData = devisiID + "-" + namaDevisi;

                                        // Tambahkan ke dalam Set
                                        devisiSet.add(devisiData);
                                    }

                                    // Simpan Set ke dalam SharedPreferences
                                    SharedPreferences sharedPreferences = requireContext().getSharedPreferences("getDevisi", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putStringSet("devisi_set", devisiSet);
                                    editor.apply();

                                    // Tampilkan dalam logcat untuk memeriksa hasil
                                    Log.d("DevisiSet", devisiSet.toString());

                                    // Setelah menyimpan data, pindah ke TambahKaryawanFragment
//                                    TambahKaryawanFragment tambahKaryawanFragment = new TambahKaryawanFragment();

                                    // Ganti tampilan fragmen dalam wadah (FrameLayout) dengan fragmen TambahKaryawanFragment
//                                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
//                                    transaction.replace(R.id.flFragment, tambahKaryawanFragment);
//                                    transaction.addToBackStack(null); // Untuk menambahkan ke back stack
//                                    transaction.commit();

                                    Intent intent = new Intent(getContext(), TambahKaryawanActivity.class);
                                    startActivity(intent);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Handle kesalahan pada request
                                error.printStackTrace();
                            }
                        }
                );

                // Tambahkan request ke dalam antrian
                requestQueue.add(jsonArrayRequest);
            }
        });

        btnTambahDevisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), TambahDevisiActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }
    public void fetchData() {
        RequestQueue requestQueue = Volley.newRequestQueue(requireContext()); // Menggunakan "requireContext()" untuk mendapatkan Context dari Fragment

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                DBConnect.getKaryawan, // Ubah URL ini sesuai dengan lokasi API PHP Anda
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Set<String> karyawanSet = new HashSet<>();

                        try {
                            // Ambil data dari respons JSON
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject karyawan = response.getJSONObject(i);
                                String userID = karyawan.getString("UserID");
                                String nama = karyawan.getString("Nama");

                                // Gabungkan UserID dan Nama dalam satu string
                                String karyawanData = userID + "-" + nama;

                                // Tambahkan ke dalam Set
                                karyawanSet.add(karyawanData);
                            }

                            // Simpan Set ke dalam SharedPreferences
                            SharedPreferences sharedPreferences = requireContext().getSharedPreferences("getKaryawan", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putStringSet("karyawan_set", karyawanSet);
                            editor.apply();

                            // Tampilkan dalam logcat untuk memeriksa hasil
                            Log.d("KaryawanSet", karyawanSet.toString());

                            // Setelah menyimpan data, lakukan apa yang perlu dilakukan dengan data tersebut dalam Fragment

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle kesalahan pada request
                        error.printStackTrace();
                    }
                }
        );

        // Tambahkan request ke dalam antrian
        requestQueue.add(jsonArrayRequest);
    }

}
