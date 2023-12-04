package com.example.wmhanaasri.Manajer.karyawan.adapter;

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

import com.example.wmhanaasri.R;

public class RVDevisiManajer extends RecyclerView.Adapter<RVDevisiManajer.ViewHolder> {

    private final int dataSize;
    private final SharedPreferences sharedPreferences;

    public RVDevisiManajer(Context context, int dataSize) {
        this.dataSize = dataSize;
        sharedPreferences = context.getSharedPreferences("devisimanajer", Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item_manajer_devisi, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String idDevisi = sharedPreferences.getString("DevisiID" + position, "");
        String namaDevisi = sharedPreferences.getString("NamaDevisi" + position, "");

        holder.idDevisi.setText(idDevisi);
        holder.namaDevisi.setText(namaDevisi);

        holder.cardViewDevisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), namaDevisi, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSize;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView idDevisi;
        public TextView namaDevisi;
        public CardView cardViewDevisi;

        public ViewHolder(View view) {
            super(view);
            this.idDevisi = view.findViewById(R.id.tv_devisiIDManajer);
            this.namaDevisi = view.findViewById(R.id.tv_namaDevisiManajer);
            this.cardViewDevisi = view.findViewById(R.id.cardViewDevisiManajer);
        }
    }

}
