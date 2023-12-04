package com.example.wmhanaasri.Karyawan.home.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wmhanaasri.Manajer.tugas.adapter.RVTugasManajerSesi;
import com.example.wmhanaasri.R;

public class RVSesiKaryawan extends RecyclerView.Adapter<RVSesiKaryawan.ViewHolder> {

    private final int dataSize;
    private final SharedPreferences sharedPreferences;

    public RVSesiKaryawan(Context context, int dataSize) {
        this.dataSize = dataSize;
        sharedPreferences = context.getSharedPreferences("sesikaryawan", Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item_karyawan_sesi, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String nama = sharedPreferences.getString("namaSesi" + position, "");
        String deskripsi = sharedPreferences.getString("deskripsi" + position, "");
        String awal = sharedPreferences.getString("awalSesi" + position, "");
        String akhir = sharedPreferences.getString("akhirSesi" + position, "");

        holder.namaSesi.setText(nama);
        holder.deskripsiSesi.setText(deskripsi);
        holder.waktuSesi.setText(awal + "-" + akhir);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), nama, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataSize;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView namaSesi;
        public TextView deskripsiSesi;
        public TextView waktuSesi;
        public CardView cardView;

        public ViewHolder(View view) {
            super(view);
            this.namaSesi = view.findViewById(R.id.tv_namaSesiKaryawan);
            this.deskripsiSesi = view.findViewById(R.id.tv_deskripsiSesiKaryawan);
            this.waktuSesi = view.findViewById(R.id.tv_waktuSesiKaryawan);
            this.cardView = view.findViewById(R.id.cardViewSesiKaryawan);
        }
    }
}
