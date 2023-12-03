package com.example.wmhanaasri.Karyawan.izin.adapter;

import static com.example.wmhanaasri.Connection.DBConnect.BaseUrl;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wmhanaasri.Karyawan.tugas.adapter.RVSelesaiDikerjakanAdapter;
import com.example.wmhanaasri.R;
import com.squareup.picasso.Picasso;

public class RVListIzinAdapter extends RecyclerView.Adapter<RVListIzinAdapter.ViewHolder> {
    private final int dataSize;
    private final SharedPreferences sharedPreferences;

    public RVListIzinAdapter(Context context, int dataSize) {
        this.dataSize = dataSize;
        sharedPreferences = context.getSharedPreferences("listizin", Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item_karyawan_perizinan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String tanggal = sharedPreferences.getString("Tanggal" + position, "");
        String status = sharedPreferences.getString("Alasan" + position, "");
        String buktiFoto = sharedPreferences.getString("BuktiDokumen" + position, "");
        String images = BaseUrl + buktiFoto;

        holder.tanggal.setText(tanggal);
        holder.alasan.setText(status);
        Picasso.get().load(images).into(holder.buktiFoto);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), tanggal, Toast.LENGTH_SHORT).show();
            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Oke", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSize;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tanggal;
        public TextView alasan;
        public ImageView buktiFoto;
        public CardView cardView;

        public ViewHolder(View view) {
            super(view);
            this.tanggal = view.findViewById(R.id.tv_tanggalList);
            this.alasan = view.findViewById(R.id.tv_alasanList);
            this.buktiFoto = view.findViewById(R.id.iv_buktiFoto);
            this.cardView = view.findViewById(R.id.cardViewListPerizinan);
        }
    }
}
