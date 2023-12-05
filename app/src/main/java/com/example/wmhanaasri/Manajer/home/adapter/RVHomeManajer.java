package com.example.wmhanaasri.Manajer.home.adapter;

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

import com.example.wmhanaasri.Karyawan.home.adapter.RVHomeKaryawan;
import com.example.wmhanaasri.R;

public class RVHomeManajer extends RecyclerView.Adapter<RVHomeManajer.ViewHolder> {

    private final int dataSize;
    private final SharedPreferences sharedPreferences;

    public RVHomeManajer(Context context, int dataSize) {
        this.dataSize = dataSize;
        sharedPreferences = context.getSharedPreferences("homemanajer", Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item_manajer_home, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        {
            String judul = sharedPreferences.getString("Judul" + position, "");
            String deskripsi = sharedPreferences.getString("Deskripsi" + position, "");
            String tanggal = sharedPreferences.getString("Tanggal" + position, "");

            holder.judulHome.setText(judul);
            holder.deskripsiHome.setText(deskripsi);
            holder.tanggalHome.setText(tanggal);

            holder.cardViewHome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), judul, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return dataSize;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView judulHome;
        public TextView deskripsiHome;
        public TextView tanggalHome;
        public CardView cardViewHome;

        public ViewHolder(View view) {
            super(view);
            this.judulHome = view.findViewById(R.id.tv_judulManajerHome);
            this.deskripsiHome = view.findViewById(R.id.tv_deskripsiManajerHome);
            this.tanggalHome = view.findViewById(R.id.tv_tanggalManajerHome);
            this.cardViewHome = view.findViewById(R.id.cardViewManajerHome);
        }
    }
}
