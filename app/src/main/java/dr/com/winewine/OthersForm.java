package dr.com.winewine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import dr.com.winewine.Util.FormOutput;
import dr.com.winewine.database.DBHelper;
import dr.com.winewine.database.FlavorCategory;
import dr.com.winewine.database.Flavors;


public class OthersForm extends Activity {
    public final static String SELECTION = "Selection";
    ExpandableListAdapter expandableListAdapter;
    ExpandableListView expandableListView;

    List<String> listHeader;
    TreeMap<String,List<String>> listHashMap;
    TreeMap<String,Flavors> mapFlavors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_others_form);


        expandableListView = (ExpandableListView)findViewById(R.id.lvExp);
        prepareForm();

        expandableListAdapter = new ExpandableListAdapter(this, listHeader, listHashMap);

        // setting list adapter
        expandableListView.setAdapter(expandableListAdapter);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_others_form, menu);
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

    private void prepareForm(){
        DBHelper dbHelper = new DBHelper(this);
        Map<FlavorCategory,List<Flavors>> formMap = dbHelper.getFormElements();

        listHeader = new ArrayList<String>();
        listHashMap = new TreeMap<String, List<String>>();
        mapFlavors = new TreeMap<String, Flavors>();
       for(Map.Entry<FlavorCategory,List<Flavors>> entry : formMap.entrySet() ){

           String flavorCategory = entry.getKey().getName();
           List<String> flavors = new ArrayList<String>();


           for( Flavors f : entry.getValue()){
               flavors.add(f.toString());
               mapFlavors.put(f.getName(),f);
           }

           listHeader.add(flavorCategory);
           listHashMap.put(flavorCategory,flavors);

        }


    }

    public void wineRecommendations(View view){
        Map<String,Boolean> m = FormOutput.getInstance().formOutput;

        ArrayList<Integer> flavorList = new ArrayList<Integer>();

        for(Map.Entry<String,Boolean> entry : m.entrySet()){

            if(entry.getValue() == Boolean.TRUE){
                    Flavors f = mapFlavors.get(entry.getKey());
                    flavorList.add(f.getId());

            }

        }

        Intent intent = new Intent(this, WineList.class);
        intent.putIntegerArrayListExtra(SELECTION,flavorList);
        startActivity(intent);

    }
}
