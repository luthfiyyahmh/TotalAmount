package com.android.totalamount;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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
        final ItemBarang itemBarang = listItemBarangs.get(position);

        holder.tvName.setText(itemBarang.getNama());
        holder.tvHarga.setText(itemBarang.getHarga());
        holder.tvjumlah.setText(String.valueOf(itemBarang.getJumlah()));
        holder.btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int jumlah = itemBarang.getJumlah()+1;
                holder.tvjumlah.setText(String.valueOf(jumlah));
                itemBarang.setJumlah(jumlah);
            }
        });

        holder.btnmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int jumlah = itemBarang.getJumlah()-1;
                if(jumlah>=0){
                    holder.tvjumlah.setText(String.valueOf(jumlah));
                    itemBarang.setJumlah(jumlah);
                }
            }
        });
    }

    @Override
    public int getItemCount() {

        return listItemBarangs.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvHarga, tvjumlah;
        ImageButton btnmin, btnplus;
        ListViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvHarga = itemView.findViewById(R.id.tv_item_harga);
            tvjumlah = itemView.findViewById(R.id.jumlah);
            btnmin = itemView.findViewById(R.id.min);
            btnplus = itemView.findViewById(R.id.plus);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(ItemBarang data);
    }
}