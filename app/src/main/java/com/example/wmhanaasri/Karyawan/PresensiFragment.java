package com.example.wmhanaasri.Karyawan;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wmhanaasri.R;

import java.util.ArrayList;

import com.example.wmhanaasri.adapter.PresensiAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PresensiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PresensiFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;
    private PresensiAdapter adapter;
    private ArrayList<ListPresensi> PresensiArrayList;

    public PresensiFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PresensiFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PresensiFragment newInstance(String param1, String param2) {
        PresensiFragment fragment = new PresensiFragment();
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
        View view = inflater.inflate(R.layout.fragment_presensi, container, false);
        recyclerView = view.findViewById(R.id.recycle_viewPresensi);

        // Membuat objek ArrayList Aktifitas
        PresensiArrayList = new ArrayList<ListPresensi>();

        // Menambahkan data ke ArrayList Aktifitas
        addData();

        // Membuat dan mengatur adapter
        adapter = new PresensiAdapter(PresensiArrayList);

        // Membuat dan mengatur layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity()); // Gunakan getActivity() karena Anda berada dalam fragmen

        // Mengatur layout manager dan adapter untuk RecyclerView
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        return view;
    }
    void addData(){
        PresensiArrayList = new ArrayList<>();
        PresensiArrayList.add(new ListPresensi("Gilang", "Mobile Developer", "Hadir | ", "09:13"));
        PresensiArrayList.add(new ListPresensi("Gilang", "Mobile Developer", "Hadir | ", "09:13"));
        PresensiArrayList.add(new ListPresensi("Gilang", "Mobile Developer", "Hadir | ", "09:13"));
        PresensiArrayList.add(new ListPresensi("Gilang", "Mobile Developer", "Hadir | ", "09:13"));
        PresensiArrayList.add(new ListPresensi("Gilang", "Mobile Developer", "Hadir | ", "09:13"));

    }
}