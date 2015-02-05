package dr.com.winewine;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import dr.com.winewine.database.CuisineType;
import dr.com.winewine.database.DBHelper;
import dr.com.winewine.database.Dishes;


public class DisplayDishesList extends ListActivity {

    private DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_dishes_list);

        Intent intent = getIntent();
        int selection = intent.getIntExtra(DisplayCuisineList.SELECTED_CUISINE_ID,1);

        dbHelper = new DBHelper(this);

        final List<Dishes> values = dbHelper.getDishesList(selection);
        ArrayAdapter<Dishes> adapter = new ArrayAdapter<Dishes>(this,android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);

        ListView listView = getListView();
        listView.setTextFilterEnabled(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                Toast.makeText(getApplicationContext(),
                        ((TextView) view).getText(), Toast.LENGTH_SHORT).show();

                Dishes d = values.get(position);

                if(d.getId() == -1){
                    //Display form
                    Intent intent = new Intent(getApplicationContext(), OthersForm.class);
                    startActivity(intent);

                }
                else{
                    //Display wine list
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_dishes_list, menu);
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
}
