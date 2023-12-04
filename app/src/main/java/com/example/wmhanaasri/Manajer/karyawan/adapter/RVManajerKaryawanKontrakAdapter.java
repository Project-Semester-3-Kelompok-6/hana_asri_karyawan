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

public class RVManajerKaryawanKontrakAdapter extends RecyclerView.Adapter<RVManajerKaryawanKontrakAdapter.ViewHolder> {

    private final int dataSize;
    private final SharedPreferences sharedPreferences;

    public RVManajerKaryawanKontrakAdapter(Context context, int dataSize) {
        this.dataSize = dataSize;
        sharedPreferences = context.getSharedPreferences("manajerkaryawankontrak", Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public RVManajerKaryawanKontrakAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item_manajer_karyawan_tidak_tetap, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVManajerKaryawanKontrakAdapter.ViewHolder holder, int position) {
        String id = sharedPreferences.getString("UserID" + position, "");
        String nama = sharedPreferences.getString("Nama" + position, "");
        String email = sharedPreferences.getString("Email" + position, "");

        holder.idKontrak.setText(id);
        holder.namaKontrak.setText(nama);
        holder.emailKontrak.setText(email);

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
        public TextView idKontrak;
        public TextView namaKontrak;
        public TextView emailKontrak;
        public CardView cardView;

        public ViewHolder(View view) {
            super(view);
            this.idKontrak = view.findViewById(R.id.tv_userIDManajerKaryawanTidakTetap);
            this.namaKontrak = view.findViewById(R.id.tv_namaManajerKaryawanTidakTetap);
            this.emailKontrak = view.findViewById(R.id.tv_emailManajerKaryawanTidakTetap);
            this.cardView = view.findViewById(R.id.cardViewManajerKaryawanTidakTetap);
        }
    }

}
