package com.android.totalamount;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.theartofdev.edmodo.cropper.CropImage;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvBarang;
    private ArrayList<ItemBarang> list = new ArrayList<>();

    //Declare an Image URI, which will be in use later
    Uri mImageUri;
    Bitmap mImageBitmap;
    private RequestQueue mQueue;
    static int now = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvBarang = findViewById(R.id.rv_barang);
        rvBarang.setHasFixedSize(true);
        mQueue = Volley.newRequestQueue(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        setMode(item.getItemId());
        return super.onOptionsItemSelected(item);
    }
    public void setMode(int selectedMode) {
        if (selectedMode == R.id.action_list) {
            CropImage.activity().start(MainActivity.this);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if (resultCode == RESULT_OK) {

                mImageUri = result.getUri();

                // Convert URI ke Bitmap, karna harus bentuk Bitmap kalau mau disimpan ke Storage
                try {
                    mImageBitmap = MediaStore.Images.Media.getBitmap(MainActivity.this.getContentResolver(), mImageUri);
                    Log.d("TesGambar", (mImageBitmap == null ? "Kosong" : "Ada Gambarnya"));
                    jsonParse(mImageBitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception e = result.getError();
                Toast.makeText(this, "Possible error is : " + e, Toast.LENGTH_SHORT).show();
            }
        }
    }

    protected void jsonParse(Bitmap gambar) {

        String url = "https://api.myjson.com/bins/1gjsvy"; //Url API

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            //Disini parsing JSONnya sesuai dengan bentuk JSON
                            String namabrg = response.getString("nama");
                            String hargabrg = response.getString("harga");
                            int jumlah = 1;

                            Log.d("Coro", namabrg +";"+hargabrg);

                            ItemBarang itemBarang = new ItemBarang();
                            itemBarang.setHarga(hargabrg); //Ini buat set ke textview atau layout
                            itemBarang.setNama(namabrg);
                            itemBarang.setJumlah(1);
                            ListBarangAdapter listBarangAdapter = new ListBarangAdapter(list);

                            if(now==0){
                                list.add(itemBarang);
                                rvBarang.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                                rvBarang.setAdapter(listBarangAdapter);
                                now = 1;
                            }else{
                                list.add(itemBarang);
                                Log.d("Coro", String.valueOf(list.size()));
                                listBarangAdapter.notifyDataSetChanged();
                                rvBarang.setAdapter(listBarangAdapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }

    private void showSelectedBarang(ItemBarang barang) {
        Toast.makeText(this, "Kamu memilih " + barang.getNama(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}