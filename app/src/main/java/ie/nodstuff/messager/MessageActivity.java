package ie.nodstuff.messager;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;


public class MessageActivity extends ActionBarActivity {

    private String sender;
    private String content;
    private Message m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        displayMessage();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_message, menu);
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

    private void displayMessage(){
        Intent i = getIntent();
        m = (Message)i.getSerializableExtra("msg");
        this.sender=m.getSender();
        this.content=m.getMessage();


        TextView source = (TextView)findViewById(R.id.sender);
        TextView text = (TextView)findViewById(R.id.received_box);

        source.setText(sender);
        text.setText(content);

    }

    public void sendMessage(){
        //process message
        //send to server


    }
}
