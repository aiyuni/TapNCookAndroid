package com.example.perry.tapncook;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText userInput;
    private ImageButton fruitButton;
    private ImageButton veggieButton;
    private ImageButton meatButton;
    private ImageButton dairyButton;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        fruitButton = (ImageButton) findViewById(R.id.imageButton);
        veggieButton = (ImageButton) findViewById(R.id.imageButton2);
        dairyButton = (ImageButton) findViewById(R.id.imageButton3);
        meatButton = (ImageButton) findViewById(R.id.imageButton4);

        System.out.println("Fruit Button is: " + fruitButton);

        View.OnClickListener pressedFruitButton = new View.OnClickListener() {
            public void onClick(View view){
                System.out.println("Clicked fruit button!");

                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(MainActivity.this, fruitButton);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.menu_fruits, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(MainActivity.this,"You Clicked : " + item.getTitle(),Toast.LENGTH_SHORT).show();
                        System.out.println("Clicked on item: " + item.getTitle());
                        return true;
                    }
                });

                popup.show();//showing popup menu
            }
        };

        View.OnClickListener pressedVeggieButton = new View.OnClickListener() {
            public void onClick(View view){
                System.out.println("Clicked fruit button!");

                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(MainActivity.this, veggieButton);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.menu_veggie, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(MainActivity.this,"You Clicked : " + item.getTitle(),Toast.LENGTH_SHORT).show();
                        System.out.println("Clicked on item: " + item.getTitle());
                        return true;
                    }
                });

                popup.show();//showing popup menu
            }
        };

        View.OnClickListener pressedDairyButton = new View.OnClickListener() {
            public void onClick(View view){
                System.out.println("Clicked fruit button!");

                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(MainActivity.this, dairyButton);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.menu_dairy, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(MainActivity.this,"You Clicked : " + item.getTitle(),Toast.LENGTH_SHORT).show();
                        System.out.println("Clicked on item: " + item.getTitle());
                        return true;
                    }
                });

                popup.show();//showing popup menu
            }
        };

        View.OnClickListener pressedMeatButton = new View.OnClickListener() {
            public void onClick(View view){
                System.out.println("Clicked fruit button!");

                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(MainActivity.this, meatButton);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.menu_meat, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(MainActivity.this,"You Clicked : " + item.getTitle(),Toast.LENGTH_SHORT).show();
                        System.out.println("Clicked on item: " + item.getTitle());
                        return true;
                    }
                });

                popup.show();//showing popup menu
            }
        };


        fruitButton.setOnClickListener(pressedFruitButton);
        veggieButton.setOnClickListener(pressedVeggieButton);
        meatButton.setOnClickListener(pressedMeatButton);
        dairyButton.setOnClickListener(pressedDairyButton);


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
