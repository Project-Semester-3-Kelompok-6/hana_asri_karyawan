package com.example.wmhanaasri.Manajer.rekap;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.wmhanaasri.Connection.DBConnect;
import com.example.wmhanaasri.Manajer.rekap.adapter.RVAbsensiRekap;
import com.example.wmhanaasri.Manajer.tugas.adapter.RVTugasManajerAdapter;
import com.example.wmhanaasri.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RekapListAbsensi extends Fragment {

    private SharedPreferences sharedPreferences;

    private RecyclerView recyclerView;
    private RVAbsensiRekap adapter;
    public RekapListAbsensi() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout._manajer_fragment_rekap_list_absensi, container, false);
        sharedPreferences = requireActivity().getSharedPreferences("rekapabsen", Context.MODE_PRIVATE);
        fetchData(requireContext());
        return view;
    }

    public void fetchData(Context context) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, DBConnect.rekapAbsensi,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray dataArray = new JSONArray(response);

                            if (dataArray.length() > 0) {
                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                for (int i = 0; i < dataArray.length(); i++) {
                                    JSONObject obj = dataArray.getJSONObject(i);
                                    editor.putString("AbsensiID" + i, obj.getString("AbsensiID"));
                                    editor.putString("KaryawanID" + i, obj.getString("KaryawanID"));
                                    editor.putString("Tanggal" + i, obj.getString("Tanggal"));
                                    editor.putString("Status" + i, obj.getString("Status"));
                                    editor.putString("Lokasi" + i, obj.getString("Lokasi"));
                                    editor.putString("BuktiFoto" + i, obj.getString("BuktiFoto"));
                                }
                                editor.apply();
                                Toast.makeText(context, "Diperbarui", Toast.LENGTH_SHORT).show();

                                int dataSize = 0;
                                while (sharedPreferences.contains("Tanggal" + dataSize)) {
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
        adapter = new RVAbsensiRekap(requireContext(), dataSize);
        RecyclerView recyclerView = getView().findViewById(R.id.rv_absensiRekap);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);
    }


}