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

public class RVManajerKaryawanAdapter extends RecyclerView.Adapter<RVManajerKaryawanAdapter.ViewHolder> {

    private final int dataSize;
    private final SharedPreferences sharedPreferences;

    public RVManajerKaryawanAdapter(Context context, int dataSize) {
        this.dataSize = dataSize;
        sharedPreferences = context.getSharedPreferences("manajerkaryawantetap", Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item_manajer_karyawan_tetap, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String id = sharedPreferences.getString("UserID" + position, "");
        String nama = sharedPreferences.getString("Nama" + position, "");
        String email = sharedPreferences.getString("Email" + position, "");

        holder.id.setText(id);
        holder.nama.setText(nama);
        holder.email.setText(email);

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
        public TextView id;
        public TextView nama;
        public TextView email;
        public CardView cardView;

        public ViewHolder(View view) {
            super(view);
            this.id = view.findViewById(R.id.tv_userIDManajerKaryawanTetap);
            this.nama = view.findViewById(R.id.tv_namaManajerKaryawanTetap);
            this.email = view.findViewById(R.id.tv_emailManajerKaryawanTetap);
            this.cardView = view.findViewById(R.id.cardViewManajerKaryawanTetap);
        }
    }
}
