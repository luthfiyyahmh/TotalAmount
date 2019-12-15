package com.android.totalamount;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static com.android.totalamount.MainActivity.totalharga;

public class ListBarangAdapter extends RecyclerView.Adapter<ListBarangAdapter.ListViewHolder> {

    private ArrayList<ItemBarang> listItemBarangs;
    private Context mContext;


    public ListBarangAdapter(ArrayList<ItemBarang> list, Context context) {
        this.listItemBarangs = list;
        mContext = context;
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
                int hargatemp = Integer.parseInt(itemBarang.getHarga());
                int total = jumlah*hargatemp;
                int totalsebelumya = (jumlah-1)*hargatemp;
                totalharga = totalharga + total - totalsebelumya;
                Log.d("Total", String.valueOf(totalharga));
                Intent intent = new Intent("custom-message");
                intent.putExtra("jumlah",totalharga);
                LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
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
                int hargatemp = Integer.parseInt(itemBarang.getHarga());
                int total = jumlah*hargatemp;
                int totalsebelumya = (jumlah-1)*hargatemp;
                totalharga = totalharga + total - totalsebelumya;
                Log.d("Total", String.valueOf(totalharga));
                Intent intent = new Intent("custom-message");
                intent.putExtra("jumlah",totalharga);
                LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
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