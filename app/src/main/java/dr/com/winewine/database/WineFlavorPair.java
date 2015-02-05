package dr.com.winewine.database;

/**
 * Created by divyaraghavan on 1/18/15.
 */
public class WineFlavorPair {
    public int id;
    public int wine_id;
    public int flavor_id;
    public int rank;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWine_id() {
        return wine_id;
    }

    public void setWine_id(int wine_id) {
        this.wine_id = wine_id;
    }

    public int getFlavor_id() {
        return flavor_id;
    }

    public void setFlavor_id(int flavor_id) {
        this.flavor_id = flavor_id;
    }
    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
