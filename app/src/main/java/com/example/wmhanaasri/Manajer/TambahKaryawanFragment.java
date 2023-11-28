package com.example.wmhanaasri.Manajer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.wmhanaasri.Manajer.karyawan.KaryawanFragment;
import com.example.wmhanaasri.R;

public class TambahKaryawanFragment extends Fragment {

    private Button button;
    private EditText editTextNama, editTextEmail, editTextPassword;
    private AutoCompleteTextView dropDivisi; // Tambahkan AutoCompleteTextView di sini

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tambah_karyawan, container, false);

        editTextNama = view.findViewById(R.id.etKaryawan);
        editTextEmail = view.findViewById(R.id.etEmail); // Ubah penamaan variabelnya
        editTextPassword = view.findViewById(R.id.etPassword); // Ubah penamaan variabelnya

        dropDivisi = view.findViewById(R.id.dropDivisi); // Inisialisasi AutoCompleteTextView

        // Ambil data devisi dari SharedPreferences
        SharedPreferences preferences = getActivity().getSharedPreferences("getDevisi", Context.MODE_PRIVATE);
        String idDevisi = preferences.getString("devisi_set", ""); // Ganti default_value_jika_kosong dengan nilai default yang sesuai

        // Inisialisasi Array/List untuk Isi Pilihan Devisi
        String[] arrayPilihanDevisi = idDevisi.split(","); // Sesuaikan jika formatnya berbeda

        // Set Adapter untuk AutoCompleteTextView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, arrayPilihanDevisi);
        dropDivisi.setAdapter(adapter);

        button = view.findViewById(R.id.btnTambahkan);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KaryawanFragment karyawanFragment = new KaryawanFragment();

                // Ganti tampilan fragmen dalam wadah (FrameLayout) dengan fragmen PresensiFragment
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.flFragment, karyawanFragment);
                transaction.addToBackStack(null); // Untuk menambahkan ke back stack
                transaction.commit();
            }
        });

        return view;
    }
}
