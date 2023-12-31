package com.example.wmhanaasri.Karyawan.tugas.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wmhanaasri.Karyawan.tugas.UpdateStatusTugas;
import com.example.wmhanaasri.R;

public class RVSedangDikerjakanAdapter extends RecyclerView.Adapter<RVSedangDikerjakanAdapter.ViewHolder> {

    private final int dataSize;
    private final SharedPreferences sharedPreferences;

    public RVSedangDikerjakanAdapter(Context context, int dataSize) {
        this.dataSize = dataSize;
        sharedPreferences = context.getSharedPreferences("tugassedangkaryawan", Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item_karyawan_tugas_task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String judul = sharedPreferences.getString("Judul" + position, "");
        String deskripsi = sharedPreferences.getString("Deskripsi" + position, "");
        String tanggal = sharedPreferences.getString("Tanggal" + position, "");
        String JobID = sharedPreferences.getString("JobID" + position,"");

        holder.judul.setText(judul);
        holder.deskripsi.setText(deskripsi);
        holder.tanggal.setText(tanggal);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Mengirim data judul ke activity baru saat card diklik
                Intent intent = new Intent(view.getContext(), UpdateStatusTugas.class);
                intent.putExtra("JobID", JobID);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSize;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView judul;
        public TextView deskripsi;
        public TextView tanggal;
        public CardView cardView;

        public ViewHolder(View view) {
            super(view);
            this.judul = view.findViewById(R.id.tv_judulTugas);
            this.deskripsi = view.findViewById(R.id.tv_deskripsiTugas);
            this.tanggal = view.findViewById(R.id.tv_tanggalTugas);
            this.cardView = view.findViewById(R.id.cardView);
        }
    }
}

