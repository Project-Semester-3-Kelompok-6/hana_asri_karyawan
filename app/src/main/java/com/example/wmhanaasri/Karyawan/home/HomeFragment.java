package com.example.wmhanaasri.Karyawan.home;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.wmhanaasri.Connection.DBConnect;
import com.example.wmhanaasri.ListAktivitas;
import com.example.wmhanaasri.Login.LoginActivity;
import com.example.wmhanaasri.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.example.wmhanaasri.Karyawan.adapter.AktifitasAdapter;

import org.json.JSONException;
import org.json.JSONObject;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private AktifitasAdapter adapter;
    private ArrayList<ListAktivitas> AktifitasArrayList;
    private ImageView imgView;
    SharedPreferences sharedPreferences;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.karyawan_fragment_home, container, false);

        TextView textView = view.findViewById(R.id.tanggal);
        String currentDate = getCurrentDate();
        textView.setText(currentDate);

        LinearLayout layoutJadwal = view.findViewById(R.id.layoutJadwal);
        LinearLayout layoutProfile = view.findViewById(R.id.layoutProfile);
        LinearLayout layoutLogout = view.findViewById(R.id.layoutLogout);


        sharedPreferences = requireActivity().getSharedPreferences("users", Context.MODE_PRIVATE);

        layoutJadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JadwalActivity.class);
                startActivity(intent);
            }
        });

        layoutProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), KaryawanProfileActivity.class);
                startActivity(intent);
            }
        });
//Failed implement hapus token ketika logout
//        layoutLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Buat permintaan logout ke API di sini
//                RequestQueue queue = Volley.newRequestQueue(requireContext());
//                StringRequest stringRequest = new StringRequest(Request.Method.POST, DBConnect.urlLogout,
//                        new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
//                                // Tanggapan dari permintaan logout
//                                try {
//                                    JSONObject jsonResponse = new JSONObject(response);
//                                    int code = jsonResponse.getInt("code");
//                                    if (code == 200) {
//                                        // Logout berhasil, hapus data dari SharedPreferences atau lakukan tindakan lainnya
//                                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                                        editor.putString("apiKey", ""); // Hapus apiKey dari SharedPreferences
//                                        editor.apply();
//                                        Intent intent = new Intent(requireContext(), LoginActivity.class);
//                                        startActivity(intent);
//                                        requireActivity().finish();
//                                    } else {
//                                        // Menampilkan pesan jika logout gagal
//                                        Toast.makeText(requireContext(), "Logout gagal", Toast.LENGTH_SHORT).show();
//                                    }
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                    Toast.makeText(requireContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // Menampilkan pesan jika terjadi kesalahan jaringan
//                        Toast.makeText(requireContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                }) {
//                    @Override
//                    protected Map<String, String> getParams() {
//                        // Mengatur parameter POST yang diperlukan: email dan apiKey
//                        Map<String, String> params = new HashMap<>();
//                        params.put("email", sharedPreferences.getString("email", ""));
//                        params.put("apiKey", sharedPreferences.getString("apiKey", ""));
//                        return params;
//                    }
//                };
//                queue.add(stringRequest);
//            }
//        });



//        View view = inflater.inflate(R.layout.karyawan_fragment_home, container, false);
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

//        imgView = view.findViewById(R.id.btnPresensi);
//        imgView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Buat objek PresensiFragment
//                PresensiFragment presensiFragment = new PresensiFragment();
//
//                // Ganti tampilan fragmen dalam wadah (FrameLayout) dengan fragmen PresensiFragment
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.flFragment, presensiFragment);
//                transaction.addToBackStack(null); // Untuk menambahkan ke back stack
//                transaction.commit();
//            }
//        });

        // mengembalikan view
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