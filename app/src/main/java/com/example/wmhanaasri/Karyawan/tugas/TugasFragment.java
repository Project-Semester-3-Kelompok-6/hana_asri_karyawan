package com.example.wmhanaasri.Karyawan.tugas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;

import com.example.wmhanaasri.ListAktivitas;
import com.example.wmhanaasri.R;

import java.util.ArrayList;

import com.example.wmhanaasri.Karyawan.adapter.AktifitasAdapter;

public class TugasFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.karyawan_fragment_tugas, container, false);

        return view;
    }
}