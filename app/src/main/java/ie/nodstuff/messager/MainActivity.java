package ie.nodstuff.messager;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    private ListView lv;
    private GoogleCloudMessaging gcm;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        gcm=gcm.getInstance(this);
        registerBackground();

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


    private void registerBackground() {
        new AsyncTask() {

            @Override
            protected Object doInBackground(Object[] params) {
                String msg;
                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(getApplicationContext());
                    }
                    id = gcm.register("826578094192");
                    msg = "Device registered, registration id=" + id;

                    // You should send the registration ID to your server over HTTP,
                    // so it can use GCM/HTTP or CCS to send messages to your app.

                    // For this demo: we don't need to send it because the device
                    // will send upstream messages to a server that echo back the message
                    // using the 'from' address in the message.

                    // Save the regid - no need to register again.
                    //setRegistrationId(context, regid);
                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();
                }

                System.out.println(msg);
                return msg;
            }

        }.execute(null,null,null);
    }

}
