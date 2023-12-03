package com.example.wmhanaasri.Karyawan.absensi.adapter;

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

import com.example.wmhanaasri.Karyawan.izin.adapter.RVListIzinAdapter;
import com.example.wmhanaasri.R;
import com.squareup.picasso.Picasso;

public class RVAbsensiAdapter extends RecyclerView.Adapter<RVAbsensiAdapter.ViewHolder> {

    private final int dataSize;
    private final SharedPreferences sharedPreferences;

    public RVAbsensiAdapter(Context context, int dataSize) {
        this.dataSize = dataSize;
        sharedPreferences = context.getSharedPreferences("listabsen", Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public RVAbsensiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item_karyawan_absensi, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVAbsensiAdapter.ViewHolder holder, int position) {
        String tanggalAbsensi = sharedPreferences.getString("Tanggal" + position, "");
        String status = sharedPreferences.getString("Status" + position, "");
        String buktiFoto = sharedPreferences.getString("BuktiFoto" + position, "");
        String images = BaseUrl + buktiFoto;

        holder.tanggalAbsensi.setText(tanggalAbsensi);
        holder.status.setText(status);
        Picasso.get().load(images).into(holder.buktiFoto);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), tanggalAbsensi, Toast.LENGTH_SHORT).show();
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
        public TextView tanggalAbsensi;
        public TextView status;
        public ImageView buktiFoto;
        public CardView cardView;

        public ViewHolder(View view) {
            super(view);
            this.tanggalAbsensi = view.findViewById(R.id.tv_TanggalAbsensi);
            this.status = view.findViewById(R.id.tv_statusAbsensi);
            this.buktiFoto = view.findViewById(R.id.iv_buktiFotoAbsensi);
            this.cardView = view.findViewById(R.id.cardViewListAbsensi);
        }
    }
}
