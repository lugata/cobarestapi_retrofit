package com.lugata_ata.restapi_retrofit;

import com.google.gson.annotations.SerializedName;

public class DoaModel {
    @SerializedName("id")
    private int id;
    @SerializedName("doa")
    private String doa;
    @SerializedName("ayat")
    private String ayat;
    @SerializedName("latin")
    private String latin;
    @SerializedName("artinya")
    private String artinya;

    public int getId() {
        return id;
    }

    public String getDoa() {
        return doa;
    }

    public String getAyat() {
        return ayat;
    }

    public String getLatin() {
        return latin;
    }

    public String getArtinya() {
        return artinya;
    }
}
