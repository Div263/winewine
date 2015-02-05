package dr.com.winewine.database;


/**
 * Created by divyaraghavan on 1/17/15.
 */

public class CuisineType  {
    public String Name;

    public CuisineType(String name){
        this.Name = name;
    }

    @Override
    public String toString(){
        return this.Name;
    }
}
