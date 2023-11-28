package com.example.wmhanaasri.Manajer.karyawan;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.wmhanaasri.Connection.DBConnect;
import com.example.wmhanaasri.Manajer.TambahKaryawanFragment;
import com.example.wmhanaasri.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Set;

public class KaryawanFragment extends Fragment {

    private FloatingActionButton btnTambah;

    public KaryawanFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.manajer_fragment_karyawan, container, false);

        btnTambah = view.findViewById(R.id.btnTambahKaryawan);

        btnTambah.setOnClickListener(new View.OnClickListener() {
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
                                    TambahKaryawanFragment tambahKaryawanFragment = new TambahKaryawanFragment();

                                    // Ganti tampilan fragmen dalam wadah (FrameLayout) dengan fragmen TambahKaryawanFragment
                                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                                    transaction.replace(R.id.flFragment, tambahKaryawanFragment);
                                    transaction.addToBackStack(null); // Untuk menambahkan ke back stack
                                    transaction.commit();

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

        return view;
    }
}
