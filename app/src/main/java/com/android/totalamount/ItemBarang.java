package com.android.totalamount;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemBarang implements Parcelable {

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    private String nama;
    private String harga;
    private int jumlah;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nama);
        dest.writeString(this.harga);
        dest.writeInt(this.jumlah);
    }

    public ItemBarang() {
    }

    protected ItemBarang(Parcel in) {
        this.nama = in.readString();
        this.harga = in.readString();
        this.jumlah = in.readInt();
    }

    public static final Creator<ItemBarang> CREATOR = new Creator<ItemBarang>() {
        @Override
        public ItemBarang createFromParcel(Parcel source) {
            return new ItemBarang(source);
        }

        @Override
        public ItemBarang[] newArray(int size) {
            return new ItemBarang[size];
        }
    };
}