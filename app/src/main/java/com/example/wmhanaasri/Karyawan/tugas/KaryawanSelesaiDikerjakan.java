package com.example.wmhanaasri.Karyawan.tugas;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.wmhanaasri.Connection.DBConnect;
import com.example.wmhanaasri.Karyawan.tugas.adapter.ListData;
import com.example.wmhanaasri.Karyawan.tugas.adapter.RVSelesaiDikerjakanAdapter;
import com.example.wmhanaasri.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class KaryawanSelesaiDikerjakan extends Fragment {
    private SharedPreferences sharedPreferences;
    private RVSelesaiDikerjakanAdapter adapter;

    public KaryawanSelesaiDikerjakan() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout._karyawan_fragment_karyawan_selesai_dikerjakan, container, false);
        sharedPreferences = requireActivity().getSharedPreferences("tugasselesai", Context.MODE_PRIVATE);

        fetchData(); // Fetch data from server

        return view;
    }

    public void fetchData() {
        RequestQueue queue = Volley.newRequestQueue(requireContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, DBConnect.tugasSelesai,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray dataArray = new JSONArray(response);

                            if (dataArray.length() > 0) {
                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                // Ambil data dari setiap objek JSON dalam array dan simpan dalam SharedPreferences
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
                                Toast.makeText(requireContext(), "Diperbarui", Toast.LENGTH_SHORT).show();

                                // Hitung jumlah data yang disimpan dalam SharedPreferences
                                int dataSize = 0;
                                while (sharedPreferences.contains("Judul" + dataSize)) {
                                    dataSize++;
                                }

                                setupRecyclerView(dataSize); // Setup RecyclerView dengan jumlah data yang ada
                            } else {
                                Toast.makeText(requireContext(), "Tidak ada data yang diterima", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(requireContext(), "Terjadi kesalahan dalam pengolahan data JSON", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(requireContext(), "Gagal melakukan request data dari server", Toast.LENGTH_SHORT).show();
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> paramV = new HashMap<>();
                paramV.put("KaryawanID", "1"); // Ganti dengan ID Karyawan yang sesuai
                return paramV;
            }
        };
        queue.add(stringRequest);
    }

    private void setupRecyclerView(int dataSize) {
        adapter = new RVSelesaiDikerjakanAdapter(requireContext(), dataSize);
        RecyclerView recyclerView = getView().findViewById(R.id.rv_karyawanSelesaiDikerjakan);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);
    }
}

