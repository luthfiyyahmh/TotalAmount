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

    private String nama;
    private String harga;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nama);
        dest.writeString(this.harga);
    }

    public ItemBarang() {
    }

    protected ItemBarang(Parcel in) {
        this.nama = in.readString();
        this.harga = in.readString();
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