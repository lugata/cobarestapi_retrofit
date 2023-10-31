package com.lugata_ata.restapi_retrofit;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    private EditText etSearchDoa;
    private RecyclerView rvDoaList;
    private DoaAdapter doaAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etSearchDoa = findViewById(R.id.etSearchDoa);
        Button btnSearch = findViewById(R.id.btnSearch);
        rvDoaList = findViewById(R.id.rvDoaList);
        rvDoaList.setLayoutManager(new LinearLayoutManager(this));


        btnSearch.setOnClickListener(view -> searchDoa());
    }


    private void searchDoa() {
        String doaName = etSearchDoa.getText().toString().trim();
        if (doaName.isEmpty()) {
            Toast.makeText(this, "Masukkan nama doa terlebih dahulu", Toast.LENGTH_SHORT).show();
            return;
        }

        // Show a progress dialog
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Fetching data...");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://doa-doa-api-ahmadramadhan.fly.dev/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DoaApiService apiService = retrofit.create(DoaApiService.class);

        Call<DoaModel> call = apiService.getDoa(doaName);
        call.enqueue(new Callback<DoaModel>() {
            @Override
            public void onResponse(@NonNull Call<DoaModel> call, @NonNull Response<DoaModel> response) {
                // Dismiss the progress dialog when the response is received
                progressDialog.dismiss();

                if (response.isSuccessful()) {
                    DoaModel doa = response.body();
                    List<DoaModel> doaList = new ArrayList<>();
                    doaList.add(doa);
                    doaAdapter = new DoaAdapter(doaList);
                    rvDoaList.setAdapter(doaAdapter);
                } else {
                    Toast.makeText(MainActivity.this, "Doa tidak ditemukan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<DoaModel> call, @NonNull Throwable t) {
                // Dismiss the progress dialog on failure
                progressDialog.dismiss();

                Toast.makeText(MainActivity.this, "Terjadi kesalahan: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
