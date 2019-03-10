package com.utopple.code.klondike;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Iterator;


public class MainActivity extends AppCompatActivity {
	public static boolean DEBUG_FLAG = false;
	private static boolean BEGUN = false;

	Table table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


		table = new Table(this);
      	table.draw();

		Log.d("Nooooooooo", "onCreate: TURNED SCREEN DOOM");

		// DEBUG Button

		if(!DEBUG_FLAG){
			findViewById(R.id.fab).setVisibility(View.GONE);

			findViewById(R.id.loc_talon).setBackgroundColor(0x00000000);
			findViewById(R.id.loc_waste).setBackgroundColor(0x00000000);
			findViewById(R.id.loc_tableaus).setBackgroundColor(0x00000000);
			findViewById(R.id.loc_foundations).setBackgroundColor(0x00000000);

		}

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
				Iterator<CardLayout> iter;

                Log.d("TABLEAUS","---------------------------------------------------------------------------");
				for(int i=0; i<7; i++){
					Log.d("TABLEAUS","COL"+i);
					// Prints all the tableau set up
					iter = table.getTableaus()[i].iterator();
					while(iter.hasNext()){
						Card current = iter.next().getCard();
						Log.d("TABLEAUS", ""+current.toString()+"::"+current.isFaceUp());
					}
				}

				Log.d("FOUNDATIONS","---------------------------------------------------------------------------");
				for(int i=0; i<4; i++){
					Log.d("FOUNDATIONS","FOUNDATION"+i);
					// Prints all the tableau set up
					iter = table.getFoundations()[i].iterator();
					while(iter.hasNext()){
						Card current = iter.next().getCard();
						Log.d("FOUNDATIONS", ""+current.toString()+"::"+current.isFaceUp());
					}
				}

				Log.d("TALON","---------------------------------------------------------------------------");
				iter = table.getTalon().iterator();
				while(iter.hasNext()){
					Card current = iter.next().getCard();
					Log.d("TALON", ""+current.toString()+"::"+current.isFaceUp());
				}

				Log.d("WASTE","---------------------------------------------------------------------------");
				iter = table.getWaste().iterator();
				while(iter.hasNext()){
					Card current = iter.next().getCard();
					Log.d("WASTE", ""+current.toString()+"::"+current.isFaceUp());
				}
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
