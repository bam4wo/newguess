package com.example.guess2;

import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText number;
    int secret;
    private TextView hint;
    private ImageView result;
    int counter;
    private TextView count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        number = findViewById(R.id.ed_number);
        hint = findViewById(R.id.hint);
        result = findViewById(R.id.result);
        count = findViewById(R.id.counter);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });
        reset();
        Log.d("Mainactivity","secret "+secret);
    }
    public void reset(){
       secret = new Random().nextInt(10)+1;
       counter = 0;
       count.setText(String.valueOf(counter));
    }
    public void guess(View view){
        int num = Integer.parseInt(number.getText().toString());
        result.setVisibility(View.VISIBLE);
        result.setAlpha(1.0f);
        counter++;
        count.setText(String.valueOf(counter));
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                reset();
            }
        };
        if(num < secret){
            hint.setText("bigger");
            result.setImageResource(R.drawable.up);
            Toast.makeText(MainActivity.this,"大一點啦笨蛋", Toast.LENGTH_LONG).show();
        }else if(num > secret){
            hint.setText("smaller");
            result.setImageResource(R.drawable.down);
            Toast.makeText(MainActivity.this,"小一點啦笨笨", Toast.LENGTH_LONG).show();
        }else {
            hint.setText("bingo,the secret number is :"+secret);
            result.setImageResource(R.drawable.flower);
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("恭喜")
                    .setMessage("猜對了好棒棒")
                    .setPositiveButton("ok",listener)
                    .show();
        }
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
