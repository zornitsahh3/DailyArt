package com.example.f120268.api;

import android.os.Handler;
import android.os.Looper;
import com.example.f120268.Painting;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PaintingApiHelper {
    
    public interface PaintingCallback {
        void onSuccess(List<Painting> paintings);
        void onError(String error);
    }

    // Fetch random paintings from Metropolitan Museum API
    public static void fetchRandomPaintings(int count, PaintingCallback callback) {
        MetMuseumService service = ApiClient.getService();
        
        // Department 11 is European Paintings
        Call<MetMuseumResponse> call = service.getPaintings(11, true);
        
        call.enqueue(new Callback<MetMuseumResponse>() {
            @Override
            public void onResponse(Call<MetMuseumResponse> call, Response<MetMuseumResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Integer> objectIDs = response.body().getObjectIDs();
                    if (objectIDs != null && !objectIDs.isEmpty()) {
                        // Get random object IDs
                        List<Integer> randomIDs = getRandomIDs(objectIDs, count);
                        fetchArtworkDetails(randomIDs, callback);
                    } else {
                        new Handler(Looper.getMainLooper()).post(() -> 
                            callback.onError("No paintings found"));
                    }
                } else {
                    new Handler(Looper.getMainLooper()).post(() -> 
                        callback.onError("API request failed: " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<MetMuseumResponse> call, Throwable t) {
                new Handler(Looper.getMainLooper()).post(() -> 
                    callback.onError("Network error: " + t.getMessage()));
            }
        });
    }

    private static List<Integer> getRandomIDs(List<Integer> allIDs, int count) {
        List<Integer> randomIDs = new ArrayList<>();
        Random random = new Random();
        int maxCount = Math.min(count, allIDs.size());
        
        for (int i = 0; i < maxCount; i++) {
            int randomIndex = random.nextInt(allIDs.size());
            randomIDs.add(allIDs.get(randomIndex));
        }
        return randomIDs;
    }

    private static void fetchArtworkDetails(List<Integer> objectIDs, PaintingCallback callback) {
        MetMuseumService service = ApiClient.getService();
        List<Painting> paintings = new ArrayList<>();
        final int[] completed = {0};
        final int total = objectIDs.size();

        for (Integer objectID : objectIDs) {
            Call<Artwork> call = service.getObject(objectID);
            call.enqueue(new Callback<Artwork>() {
                @Override
                public void onResponse(Call<Artwork> call, Response<Artwork> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Artwork artwork = response.body();
                        // Only add if it has an image
                        if (artwork.getPrimaryImage() != null && !artwork.getPrimaryImage().isEmpty()) {
                            Painting painting = new Painting(
                                artwork.getTitle(),
                                artwork.getArtistDisplayName(),
                                artwork.getPrimaryImage()
                            );
                            paintings.add(painting);
                        }
                    }
                    
                    completed[0]++;
                    if (completed[0] == total) {
                        new Handler(Looper.getMainLooper()).post(() -> {
                            if (paintings.isEmpty()) {
                                callback.onError("No paintings with images found");
                            } else {
                                callback.onSuccess(paintings);
                            }
                        });
                    }
                }

                @Override
                public void onFailure(Call<Artwork> call, Throwable t) {
                    completed[0]++;
                    if (completed[0] == total) {
                        new Handler(Looper.getMainLooper()).post(() -> {
                            if (paintings.isEmpty()) {
                                callback.onError("Failed to fetch paintings: " + t.getMessage());
                            } else {
                                callback.onSuccess(paintings);
                            }
                        });
                    }
                }
            });
        }
    }
}

