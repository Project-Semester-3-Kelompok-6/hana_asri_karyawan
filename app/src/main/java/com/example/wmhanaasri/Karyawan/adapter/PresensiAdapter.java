package com.example.wmhanaasri.Karyawan.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.wmhanaasri.Karyawan.ListPresensi;
import com.example.wmhanaasri.R;

import java.util.ArrayList;

public class PresensiAdapter extends RecyclerView.Adapter<PresensiAdapter.PresensiViewHolder> {
    private ArrayList<ListPresensi> dataList;

    public PresensiAdapter(ArrayList<ListPresensi> dataList){
        this.dataList = dataList;
    }

    @Override
    public PresensiAdapter.PresensiViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_view_presensi_activity, parent, false);
        return new PresensiAdapter.PresensiViewHolder(view);
    }
    @Override
    public void onBindViewHolder(PresensiAdapter.PresensiViewHolder holder, int position){
        holder.txtNama.setText(dataList.get(position).getNama());
        holder.txtDivisi.setText(dataList.get(position).getDivisi());
        holder.txtStatus.setText(dataList.get(position).getStatus());
        holder.txtJam.setText(dataList.get(position).getJam());
    }
    @Override
    public int getItemCount(){
        return (dataList != null) ? dataList.size() : 0;
    }

    public class PresensiViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNama, txtDivisi, txtStatus, txtJam;

        public PresensiViewHolder(View itemView){
            super(itemView);
            txtNama = (TextView) itemView.findViewById(R.id.txtnamaKaryawan);
            txtDivisi = (TextView) itemView.findViewById(R.id.txtDivisi);
            txtStatus = (TextView) itemView.findViewById(R.id.txtStatus);
            txtJam = (TextView) itemView.findViewById(R.id.jamHadir);
        }
    }
}
