import com.example.testproject.Painting;

import java.util.ArrayList;
import java.util.List;

public class FavoritesManager {

    private static FavoritesManager instance;
    ArrayList<Painting> favorites;

    public static FavoritesManager getInstance() {
        if (instance == null) {
            instance = new FavoritesManager();
        }
        return instance;
    }

    public void addFavorite(Painting painting) {
        if (!favorites.contains(painting)) {
            favorites.add(painting);
        }
    }

    public List<Painting> getFavorites() {
        return favorites;
    }
}
