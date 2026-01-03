package com.example.f120268.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MetMuseumService {
    // Get list of object IDs (with department filter for paintings)
    @GET("public/collection/v1/search")
    Call<MetMuseumResponse> searchObjects(
            @Query("q") String query,
            @Query("departmentId") Integer departmentId
    );
    
    // Get specific artwork by object ID
    @GET("public/collection/v1/objects/{objectID}")
    Call<Artwork> getObject(@Path("objectID") Integer objectID);
    
    // Get objects by department (11 is European Paintings)
    @GET("public/collection/v1/search")
    Call<MetMuseumResponse> getPaintings(
            @Query("departmentId") Integer departmentId,
            @Query("hasImages") Boolean hasImages
    );
}

