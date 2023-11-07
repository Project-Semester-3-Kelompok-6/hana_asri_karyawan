package com.example.wmhanaasri.Karyawan;

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

import com.example.wmhanaasri.R;

import java.util.ArrayList;

import com.example.wmhanaasri.Karyawan.adapter.AktifitasAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RekapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RekapFragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Spinner spinner;
    private SpinnerAdapter adapter;
    private RecyclerView recyclerView;
    private AktifitasAdapter adapterRecycler;
    private ArrayList<ListAktivitas> AktifitasArrayList;

    public RekapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RekapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RekapFragment newInstance(String param1, String param2) {
        RekapFragment fragment = new RekapFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_rekap, container, false);
        spinner = view.findViewById(R.id.spinnerRekap);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(requireActivity(), android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.add("Tugas");
        adapter.add("Presensi");


        spinner.setAdapter(adapter); // Mengatur adapter ke Spinner
        spinner.setSelection(0); // Memilih item "Presensi" sebagai item default

        //recycler view
        recyclerView = view.findViewById(R.id.recycle_viewHome);

        // Membuat objek ArrayList Aktifitas
        AktifitasArrayList = new ArrayList<ListAktivitas>();

        // Menambahkan data ke ArrayList Aktifitas
        addData();

        // Membuat dan mengatur adapter
        adapterRecycler = new AktifitasAdapter(AktifitasArrayList);

        // Membuat dan mengatur layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity()); // Gunakan getActivity() karena Anda berada dalam fragmen

        // Mengatur layout manager dan adapter untuk RecyclerView
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterRecycler);

        return view;
    }

    void addData() {
        AktifitasArrayList = new ArrayList<>();
        AktifitasArrayList.add(new ListAktivitas("Upload Menu Baru", "Gilang", "14 Oktober 2023"));
        AktifitasArrayList.add(new ListAktivitas("Upload Menu Baru", "Gilang", "14 Oktober 2023"));
        AktifitasArrayList.add(new ListAktivitas("Upload Menu Baru", "Gilang", "14 Oktober 2023"));
        AktifitasArrayList.add(new ListAktivitas("Restok Bahan", "Rizqi", "15 Oktober 2023"));
        AktifitasArrayList.add(new ListAktivitas("Restok Bahan", "Rizqi", "15 Oktober 2023"));
    }
}