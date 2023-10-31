package com.lugata_ata.restapi_retrofit;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DoaApiService {
    @GET("doa/{name}")
    Call<DoaModel> getDoa(@Path("name") String doaName);
}
