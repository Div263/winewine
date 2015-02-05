package dr.com.winewine.database;

/**
 * Created by divyaraghavan on 1/18/15.
 */
public class DishesToFlavors {
    public int id;
    public int dish_id;

    public int getFlavor_id() {
        return flavor_id;
    }

    public void setFlavor_id(int flavor_id) {
        this.flavor_id = flavor_id;
    }

    public int getDish_id() {
        return dish_id;
    }

    public void setDish_id(int dish_id) {
        this.dish_id = dish_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int flavor_id;
}
