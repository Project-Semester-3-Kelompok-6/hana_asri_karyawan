package com.example.wmhanaasri.Karyawan;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.wmhanaasri.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class AbsensiFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.karyawan_fragment_absensi, viewGroup, false);

        // You can add any further initialization or UI setup code here

        return view;
    }
}