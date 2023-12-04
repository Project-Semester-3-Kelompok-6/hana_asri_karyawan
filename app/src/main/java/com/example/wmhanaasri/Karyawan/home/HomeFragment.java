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
import com.example.wmhanaasri.Karyawan.home.adapter.RVHomeKaryawan;
import com.example.wmhanaasri.Karyawan.tugas.TugasFragment;
import com.example.wmhanaasri.ListAktivitas;
import com.example.wmhanaasri.Login.LoginActivity;
import com.example.wmhanaasri.Manajer.rekap.adapter.RVTugasRekap;
import com.example.wmhanaasri.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.example.wmhanaasri.Karyawan.adapter.AktifitasAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private RVHomeKaryawan adapter;
    private ArrayList<ListAktivitas> AktifitasArrayList;
    private ImageView imgView;
    private TextView textViewNama,textViewJabatan;
    SharedPreferences sharedPreferencesHome;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.karyawan_fragment_home, container, false);

        TextView textView = view.findViewById(R.id.tanggal);
        textViewNama = view.findViewById(R.id.tv_user);
        textViewJabatan = view.findViewById(R.id.jabatan);
        sharedPreferencesHome = requireActivity().getSharedPreferences("homekaryawan", Context.MODE_PRIVATE);
        fetchData(requireContext());

        SharedPreferences preferences = requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String nama = preferences.getString("nama", "");
        String jabatan = preferences.getString("jabatan", "");
        textViewNama.setText(nama);
        textViewJabatan.setText(jabatan);


        LinearLayout layoutJadwal = view.findViewById(R.id.layoutJadwal);
        LinearLayout layoutProfile = view.findViewById(R.id.layoutProfile);
        LinearLayout layoutLogout = view.findViewById(R.id.layoutLogout);


//        sharedPreferencesHome = requireActivity().getSharedPreferences("users", Context.MODE_PRIVATE);

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
                if (getActivity() instanceof KaryawanMainActivity) {
                    ((KaryawanMainActivity) getActivity()).switchToTugasFragment();
                }
            }
        });

        layoutLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), LoginActivity.class);
                startActivity(intent);
                requireActivity().finish();
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




        return view;
    }

    public void fetchData(Context context) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, DBConnect.rekapTugas,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray dataArray = new JSONArray(response);

                            if (dataArray.length() > 0) {
                                SharedPreferences.Editor editor = sharedPreferencesHome.edit();

                                for (int i = 0; i < dataArray.length(); i++) {
                                    JSONObject obj = dataArray.getJSONObject(i);
                                    editor.putString("JobID" + i, obj.getString("JobID"));
                                    editor.putString("Judul" + i, obj.getString("Judul"));
                                    editor.putString("Deskripsi" + i, obj.getString("Deskripsi"));
                                    editor.putString("DevisiID" + i, obj.getString("DevisiID"));
                                    editor.putString("KaryawanID" + i, obj.getString("KaryawanID"));
                                    editor.putString("Tanggal" + i, obj.getString("Tanggal"));
                                    editor.putString("Status" + i, obj.getString("Status"));
                                    editor.putString("BuktiFoto" + i, obj.getString("BuktiFoto"));
                                }
                                editor.apply();
                                Toast.makeText(context, "Diperbarui", Toast.LENGTH_SHORT).show();

                                int dataSize = 0;
                                while (sharedPreferencesHome.contains("Judul" + dataSize)) {
                                    dataSize++;
                                }

                                setupRecyclerView(dataSize);
                            } else {
                                Toast.makeText(context, "Tidak ada data yang diterima", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "Terjadi kesalahan dalam pengolahan data JSON", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(context, "Gagal melakukan request data dari server", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
    }

    private void setupRecyclerView(int dataSize) {
        adapter = new RVHomeKaryawan(requireContext(), dataSize);
        RecyclerView recyclerView = getView().findViewById(R.id.recycle_viewHome);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);
    }


}