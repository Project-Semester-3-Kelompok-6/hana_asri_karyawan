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

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.wmhanaasri.Connection.DBConnect;
import com.example.wmhanaasri.Karyawan.adapter.AktifitasAdapter;
import com.example.wmhanaasri.ListAktivitas;
import com.example.wmhanaasri.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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

        //dummy
        //        View view = inflater.inflate(R.layout.karyawan_fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recycle_viewKaryawan);

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


        return view;
    }
    void addData(){
        AktifitasArrayList = new ArrayList<>();
        AktifitasArrayList.add(new ListAktivitas("Karyawan Baru", "Gilang", "14 Oktober 2023"));
        AktifitasArrayList.add(new ListAktivitas("Karyawan Baru", "Sinta", "14 Oktober 2023"));
        AktifitasArrayList.add(new ListAktivitas("Karyawan Baru", "Aji", "14 Oktober 2023"));
        AktifitasArrayList.add(new ListAktivitas("Karyawan Baru", "Rizqi", "15 Oktober 2023"));
    }
}
