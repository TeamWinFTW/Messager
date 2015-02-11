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

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    private ListView lv;
    private GoogleCloudMessaging gcm;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("herro pree");


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


    public String postData() {
        // Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://95.45.71.39/CalcFahrCelsius/FarToCel");

        System.out.println("this is before the try");
        try {
            // Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("tempFahr", "-40"));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // Execute HTTP Post Request
            System.out.println("Posting now");
            HttpResponse response = httpclient.execute(httppost);

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
        return "errororoeoeoor";
    }


    private class postInfoTask extends AsyncTask<String,String,String>
    {
        @Override
        protected String doInBackground(String... params) {

            System.out.println("fuck fuck do in background");
            return postData();



        }

        protected void onPostExecute(String result){

        }
    }

}
