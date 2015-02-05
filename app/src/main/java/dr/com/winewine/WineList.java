package dr.com.winewine;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dr.com.winewine.database.CuisineType;
import dr.com.winewine.database.DBHelper;


public class WineList extends ListActivity {

    List<Set<Integer>> setWine;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine_list);

        Intent intent = getIntent();
        ArrayList<Integer> flavorSelection = intent.getIntegerArrayListExtra(OthersForm.SELECTION);
        db = new DBHelper(this);

        setWine = new ArrayList<Set<Integer>>();
        for(Integer i : flavorSelection){
            Set<Integer> s = db.getWinepair(i);
            setWine.add(s);
        }

        List<String> Wines = getCombination();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, Wines);
        setListAdapter(adapter);

        ListView listView = getListView();
        listView.setTextFilterEnabled(true);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_wine_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    List<String> getCombination(){
        if(setWine == null){
            System.out.println("Wine list is null");
            return null;
        }

        List<String> listWineType = new ArrayList<String>();

        Set<Integer> intersection = new HashSet<Integer>(setWine.get(0));
        for(int i = 1; i < setWine.size();i++){
            intersection.retainAll(setWine.get(i));
        }
        //TODO: Handle intersection is null

        for(Integer i : intersection){
                listWineType.add(db.getWineType(i));
        }

        return listWineType;


    }
}
