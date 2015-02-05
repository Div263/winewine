package dr.com.winewine.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by divyaraghavan on 1/18/15.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static String DB_PATH = "/data/data/dr.com.winewine/databases/";

    private static String DB_NAME = "DB_Wine.db";

    private SQLiteDatabase myDataBase;

    private final Context myContext;

    private static final String CUISINE_LIST_QUERY =  "SELECT * FROM CUISINE_TYPE";
    private static final String DISHES_LIST_QUERY =  "SELECT * from DISHES WHERE CUISINE_ID=";
    private static final String FLAVOR_HEADER_QUERY = "SELECT * FROM FLAVOR_CATEGORIES";
    private static final String FLAVOR_ELEMENTS_QUERY = "SELECT * FROM FLAVORS WHERE Flavor_type=";
    private static final String FLAVOR_TO_WINE_QUERY = "SELECT * FROM WINE_FLAVOR_PAIR WHERE FLAVOR_ID=";
    private static final String WINE_TYPE_QUERY = "SELECT * FROM WINE_TYPES WHERE _id=";

    private static final String CUISINE_TABLE = "CUISINE_TYPE";


    public DBHelper(Context context) {

        super(context, DB_NAME, null, 1);
        this.myContext = context;
    }

    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if(dbExist){
            //do nothing - database already exist
        }else{

            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();

            try {

                copyDataBase();

            } catch (IOException e) {

                throw new Error("Error copying database");

            }
        }

    }
    private boolean checkDataBase(){

        SQLiteDatabase checkDB = null;

        try{
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        }catch(SQLiteException e){

            //database does't exist yet.

        }

        if(checkDB != null){

            checkDB.close();

        }

        return false;
    }

    private void copyDataBase() throws IOException{

        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException {

        //Open the database
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close() {

        if(myDataBase != null)
            myDataBase.close();

        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public List<CuisineType> getCuisineList() {
        List<CuisineType> cuisineList = new ArrayList<CuisineType>();
        SQLiteDatabase sdb = this.getReadableDatabase();
        Cursor cursor = sdb.rawQuery(CUISINE_LIST_QUERY, null);
        if (cursor.moveToFirst()) {
            do {
            CuisineType c = new CuisineType(cursor.getString(1));
                cuisineList.add(c);
            } while (cursor.moveToNext());
        }
        return cuisineList;
    }

    public List<Dishes> getDishesList(int cuisineID){
        List<Dishes> dishesList = new ArrayList<Dishes>();
        SQLiteDatabase sdb = this.getReadableDatabase();
        String query  = DISHES_LIST_QUERY+cuisineID;
        Cursor cursor = sdb.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            do {
                Dishes d = new Dishes();
                d.setId(cursor.getInt(0));
                d.setName(cursor.getString(1));
                dishesList.add(d);
            } while (cursor.moveToNext());
        }

        Dishes d = new Dishes();
        d.setName("Other");
        d.setId(-1);
        dishesList.add(d);
        return dishesList;

    }

    public String getWineType(int wineId){
        String wineTypeName = null;
        SQLiteDatabase sdb = this.getReadableDatabase();
        Cursor cursor = sdb.rawQuery(WINE_TYPE_QUERY+wineId,null);

                if(cursor.moveToFirst()){
                    do{
                        wineTypeName = cursor.getString(1);
                    }while(cursor.moveToNext());
                }
        return wineTypeName;

    }
    public Set<Integer> getWinepair(int flavorId){
        Set<Integer> w = new HashSet<Integer>();
        SQLiteDatabase sdb = this.getReadableDatabase();
        Cursor cursor = sdb.rawQuery(FLAVOR_TO_WINE_QUERY+flavorId,null);

        if(cursor.moveToFirst()){
            do{
                w.add(cursor.getInt(1));
            }while(cursor.moveToNext());
        }
        return w;



    }
    public Map<FlavorCategory,List<Flavors>> getFormElements(){
        Map<FlavorCategory,List<Flavors>> formMap = new HashMap<FlavorCategory, List<Flavors>>();

        SQLiteDatabase sdb = this.getReadableDatabase();
        Cursor cursor = sdb.rawQuery(FLAVOR_HEADER_QUERY,null);

        if (cursor.moveToFirst()) {
            do {
                FlavorCategory flavorCategory = new FlavorCategory();
                List<Flavors> flavors = new ArrayList<Flavors>();


                flavorCategory.setId(cursor.getInt(0));
                flavorCategory.setName(cursor.getString(1));

                String query = FLAVOR_ELEMENTS_QUERY+flavorCategory.getId();
                Cursor cursorChild = sdb.rawQuery(query,null);

                if (cursorChild.moveToNext()){
                    do{
                        Flavors f = new Flavors();
                        f.setId(cursorChild.getInt(0));
                        f.setName(cursorChild.getString(1));
                        f.setFlavorType(cursorChild.getInt(2));
                        flavors.add(f);
                    }while (cursorChild.moveToNext());
                }

                formMap.put(flavorCategory,flavors);

            } while (cursor.moveToNext());


        }

        return formMap;

    }
}
