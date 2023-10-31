package com.lugata_ata.restapi_retrofit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DoaAdapter extends RecyclerView.Adapter<DoaAdapter.DoaViewHolder> {
    private List<DoaModel> doaList;

    public DoaAdapter(List<DoaModel> doaList) {
        this.doaList = doaList;
    }

    @NonNull
    @Override
    public DoaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_doa, parent, false);
        return new DoaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoaViewHolder holder, int position) {
        DoaModel doa = doaList.get(position);
        holder.tvTitle.setText(doa.getDoa());
        holder.tvAyat.setText(doa.getAyat());
        holder.tvLatin.setText(doa.getLatin());
        holder.tvArtinya.setText(doa.getArtinya());
    }

    @Override
    public int getItemCount() {
        return doaList.size();
    }

    public class DoaViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvAyat, tvLatin, tvArtinya;

        public DoaViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvAyat = itemView.findViewById(R.id.tvAyat);
            tvLatin = itemView.findViewById(R.id.tvLatin);
            tvArtinya = itemView.findViewById(R.id.tvArtinya);
        }
    }
}
