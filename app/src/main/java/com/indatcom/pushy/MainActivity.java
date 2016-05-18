package com.indatcom.pushy;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.ClipboardManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.indatcom.pushy.Asyn_Class.PushNotificationsAsync;

import java.util.concurrent.ExecutionException;

import me.pushy.sdk.Pushy;

public class MainActivity extends AppCompatActivity {

    Button btnAce;
    String key;
    TextView key_nueva;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Pushy.listen(this);

        try {
             key = new PushNotificationsAsync(MainActivity.this).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        btnAce = (Button)findViewById(R.id.btnAce);
        key_nueva = (TextView)findViewById(R.id.prueba);
        btnAce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Tu key: " + key, Toast.LENGTH_SHORT).show();
                ClipboardManager cm = (ClipboardManager)MainActivity.this.getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(key);
                Toast.makeText(MainActivity.this, "Copiado al clipboard", Toast.LENGTH_SHORT).show();
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
