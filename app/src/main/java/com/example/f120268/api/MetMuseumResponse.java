package com.example.f120268.api;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MetMuseumResponse {
    @SerializedName("objectIDs")
    private List<Integer> objectIDs;
    
    @SerializedName("total")
    private Integer total;

    public List<Integer> getObjectIDs() {
        return objectIDs;
    }

    public Integer getTotal() {
        return total;
    }
}

