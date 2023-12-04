package com.example.wmhanaasri.Manajer.tugas.adapter;

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

public class RVTugasManajerAdapter extends RecyclerView.Adapter<RVTugasManajerAdapter.ViewHolder> {

    private final int dataSize;
    private final SharedPreferences sharedPreferences;

    public RVTugasManajerAdapter(Context context, int dataSize) {
        this.dataSize = dataSize;
        sharedPreferences = context.getSharedPreferences("tugasmanajer", Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public RVTugasManajerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item_manajer_tugas, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVTugasManajerAdapter.ViewHolder holder, int position) {
        String judul = sharedPreferences.getString("Judul" + position, "");
        String tanggal = sharedPreferences.getString("Tanggal" + position, "");
        String status = sharedPreferences.getString("Status" + position, "");

        holder.judul.setText(judul);
        holder.tanggal.setText(status);
        holder.status.setText(tanggal);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), judul, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSize;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView judul;
        public TextView tanggal;
        public TextView status;
        public CardView cardView;

        public ViewHolder(View view) {
            super(view);
            this.judul = view.findViewById(R.id.tv_judulTugasManajer);
            this.tanggal = view.findViewById(R.id.tv_tanggalTugasManajer);
            this.status = view.findViewById(R.id.tv_statusTugasManajer);
            this.cardView = view.findViewById(R.id.cardViewTugasManajer);
        }
    }

}
