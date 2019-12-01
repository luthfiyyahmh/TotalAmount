package com.android.totalamount;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListBarangAdapter extends RecyclerView.Adapter<ListBarangAdapter.ListViewHolder> {

    private ArrayList<ItemBarang> listItemBarangs;

    public ListBarangAdapter(ArrayList<ItemBarang> list) {
        this.listItemBarangs = list;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_barang, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        ItemBarang itemBarang = listItemBarangs.get(position);

        holder.tvName.setText(itemBarang.getNama());
        holder.tvHarga.setText(itemBarang.getHarga());
    }

    @Override
    public int getItemCount() {

        return listItemBarangs.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvHarga;
        ListViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvHarga = itemView.findViewById(R.id.tv_item_harga);
        }
    }
}