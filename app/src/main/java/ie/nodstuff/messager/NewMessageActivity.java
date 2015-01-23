package ie.nodstuff.messager;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;


public class NewMessageActivity extends ActionBarActivity {

    private Contact recipient;

    private String id="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_message);
        setup();
        setupButton();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_message, menu);
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

    public void sendMessage(){
        //process message
        //send to server

        //for now
        System.out.println("This is in send message");
        EditText e = (EditText)findViewById(R.id.new_text);
        String msg = e.getText().toString();
        Controller.getInstance().addToMessages(new Message(msg, "test"));
        finish();

    }



    public void setupButton(){
        Button b = (Button)findViewById(R.id.send_new);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sendMessage();
                System.out.println("This is in the listener");
            }
        });
    }

    public void setup(){

        Intent i = getIntent();
        recipient = (Contact)i.getSerializableExtra("Contact");

        TextView t = (TextView)findViewById(R.id.recipient);
        t.setText(recipient.getName());

    }



}
