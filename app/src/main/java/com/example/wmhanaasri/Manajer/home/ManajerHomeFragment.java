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
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.wmhanaasri.Connection.DBConnect;
import com.example.wmhanaasri.Karyawan.home.KaryawanMainActivity;
import com.example.wmhanaasri.Karyawan.home.adapter.RVHomeKaryawan;
import com.example.wmhanaasri.ListAktivitas;
import com.example.wmhanaasri.Login.LoginActivity;
import com.example.wmhanaasri.Manajer.AktifitasAdapter;
import com.example.wmhanaasri.Manajer.MainActivity;
import com.example.wmhanaasri.Manajer.PresensiFragment;
import com.example.wmhanaasri.Manajer.home.adapter.RVHomeManajer;
import com.example.wmhanaasri.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ManajerHomeFragment extends Fragment {
    private RecyclerView recyclerView;

    private ArrayList<ListAktivitas> AktifitasArrayList;
    private ImageView imgView,btnTugas,btnProfile;
    private TextView textView;
    private TextView textViewNamaManajer,textViewJabatanManajer;
    SharedPreferences sharedPreferencesHomeManajer;
    private RVHomeManajer adapter;


    public ManajerHomeFragment() {
        // Required empty public constructor
    }



    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.manajer_fragment_home, container, false);
        TextView textViewtanggal = view.findViewById(R.id.tanggalManajer);
        textViewNamaManajer = view.findViewById(R.id.tv_userManajer);
        textViewJabatanManajer = view.findViewById(R.id.jabatanManajer);

        //atur nama dan jabatan
        SharedPreferences preferences = requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String nama = preferences.getString("nama", "");
        String jabatan = preferences.getString("jabatan", "");
        textViewNamaManajer.setText(nama);
        textViewJabatanManajer.setText(jabatan);
        sharedPreferencesHomeManajer = requireActivity().getSharedPreferences("homemanajer", Context.MODE_PRIVATE);
        fetchData(requireContext());


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

                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).switchToRekapFragment();
                }
//                // Buat objek PresensiFragment
//                PresensiFragment presensiFragment = new PresensiFragment();
//
//                // Ganti tampilan fragmen dalam wadah (FrameLayout) dengan fragmen PresensiFragment
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.flFragment, presensiFragment);
//                transaction.addToBackStack(null); // Untuk menambahkan ke back stack
//                transaction.commit();
            }
        });

        btnTugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).switchToRekapFragment();
                }
            }
        });

        // view
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
                                SharedPreferences.Editor editor = sharedPreferencesHomeManajer.edit();

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
                                while (sharedPreferencesHomeManajer.contains("Judul" + dataSize)) {
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
        adapter = new RVHomeManajer(requireContext(), dataSize);
        RecyclerView recyclerView = getView().findViewById(R.id.recycle_viewHome);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);
    }


}