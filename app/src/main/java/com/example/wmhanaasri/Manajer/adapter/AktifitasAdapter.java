package com.example.wmhanaasri.Manajer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.wmhanaasri.ListAktivitas;
import com.example.wmhanaasri.R;

import java.util.ArrayList;

public class AktifitasAdapter extends RecyclerView.Adapter<AktifitasAdapter.AktifitasViewHolder> {
    private ArrayList<ListAktivitas>dataList;

    public AktifitasAdapter(ArrayList<ListAktivitas> dataList){
        this.dataList = dataList;
    }

    @Override
    public AktifitasViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycle_view_home_activity, parent, false);
        return new AktifitasViewHolder(view);
    }
    @Override
    public void onBindViewHolder(AktifitasViewHolder holder, int position){
        holder.txtJudul.setText(dataList.get(position).getJudul());
        holder.txtNama.setText(dataList.get(position).getNama());
        holder.txtTenggat.setText(dataList.get(position).getTenggat());
    }
    @Override
    public int getItemCount(){
        return (dataList != null) ? dataList.size() : 0;
    }

    public class AktifitasViewHolder extends RecyclerView.ViewHolder{
        private TextView txtJudul, txtNama, txtTenggat;

        public AktifitasViewHolder(View itemView){
            super(itemView);
            txtJudul = (TextView) itemView.findViewById(R.id.judulTugas);
            txtNama = (TextView) itemView.findViewById(R.id.txtnamaKaryawan);
            txtTenggat = (TextView) itemView.findViewById(R.id.tenggat);
        }
    }
}

