package com.example.wmhanaasri.Manajer.tugas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wmhanaasri.R;

public class TugasManajerSesi extends Fragment {

    public TugasManajerSesi() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout._manajer_fragment_tugas_manajer_sesi, container, false);
    }
}