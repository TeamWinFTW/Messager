package ie.nodstuff.messager;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Controller.getInstance().getSizeMessages()==0){

            Controller.getInstance().addToMessages(new Message("Tom","Hello this is a test Message"));
            Controller.getInstance().addToMessages(new Message("Bob","Hello this is a test Message 2"));
            Controller.getInstance().addToMessages(new Message("John","Hello this is a test Message 3"));
            Controller.getInstance().addToMessages(new Message("Jim","Hello this is a test Message 4"));


            Controller.getInstance().addToContacts("Jim","1");
            Controller.getInstance().addToContacts("Bob","2");
            Controller.getInstance().addToContacts("Tony","3");


        }


        setupListview();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.new_message) {
            Intent i = new Intent(this, SelectContactActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    private void openMessage(Message m){
        Intent i = new Intent(this, MessageActivity.class);
        i.putExtra("msg",m);
        startActivity(i);
    }

    public void getMessage(){
        //process the message
        //add to arraylist
        //invalidateviews on adapter

    }

    public void setupListview(){
        lv=(ListView)findViewById(R.id.listView);
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, Controller.getInstance().getMessages());
        lv.setAdapter(adapter);
        lv.invalidateViews();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView parent, View v, int position, long id) {

                openMessage(Controller.getInstance().returnMessage(position));
            }
        });


    }



}
