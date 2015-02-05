package dr.com.winewine.database;

/**
 * Created by divyaraghavan on 1/18/15.
 */
public class Dishes {
    public int id;
    public String name;
    public int cuisine_id;
    public int wine_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCuisine_id() {
        return cuisine_id;
    }

    public void setCuisine_id(int cuisine_id) {
        this.cuisine_id = cuisine_id;
    }

    public int getWine_id() {
        return wine_id;
    }

    public void setWine_id(int wine_id) {
        this.wine_id = wine_id;
    }
    @Override
    public String toString(){
        return this.name;
    }
}
