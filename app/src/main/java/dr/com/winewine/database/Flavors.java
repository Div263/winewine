package dr.com.winewine.database;

/**
 * Created by divyaraghavan on 1/18/15.
 */
public class Flavors {
    public int id;
    public String name;
    public int flavorType;

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

    public int getFlavorType() {
        return flavorType;
    }

    public void setFlavorType(int flavorType) {
        this.flavorType = flavorType;
    }

    @Override
    public String toString(){
        return this.name;
    }
}
