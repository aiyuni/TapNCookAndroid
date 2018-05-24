package com.pl.perry.tapncook;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText userInput;
    private ImageButton fruitButton;
    private ImageButton veggieButton;
    private ImageButton meatButton;
    private ImageButton dairyButton;
    private EditText search;
    private TextView textView;
    private TextView basket;
    private ArrayList<String> ingredientsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("bai", "hello");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*The submit icon event*/
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        final MainActivity mainActivity = this;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        //.setAction("Action", null).show();
                Intent intent = new Intent(mainActivity, DisplayRecipes.class);
                String[] ingredientsArray = ingredientsList.toArray(new String[ingredientsList.size()]);
                intent.putExtra("userInput", ingredientsArray);
                ingredientsList.clear();
                basket.setText("Basket: ");
                startActivity(intent);
            }
        });

        fruitButton = (ImageButton) findViewById(R.id.imageButton);
        veggieButton = (ImageButton) findViewById(R.id.imageButton2);
        dairyButton = (ImageButton) findViewById(R.id.imageButton3);
        meatButton = (ImageButton) findViewById(R.id.imageButton4);
        basket = (TextView) findViewById(R.id.basketText);
        search = findViewById(R.id.search);

        System.out.println("Fruit Button is: " + fruitButton);

        search.setOnKeyListener(new View.OnKeyListener() {
                                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                                        if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                                            basket.append(search.getText() + ", ");
                                            Toast.makeText(MainActivity.this, search.getText() + " has been added to basket", Toast.LENGTH_LONG).show();
                                            System.out.println(search.getText());
                                            ingredientsList.add(search.getText().toString());
                                            search.setText("");

                                        }
                                        return true;
                                    }
                                });

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
                        //Toast.makeText(MainActivity.this,"You Clicked : " + item.getTitle(),Toast.LENGTH_SHORT).show();
                        System.out.println("Clicked on item: " + item.getTitle());
                        basket.append(item.getTitle() + ", ");
                        ingredientsList.add(item.getTitle().toString());
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
                        //Toast.makeText(MainActivity.this,"You Clicked : " + item.getTitle(),Toast.LENGTH_SHORT).show();
                        System.out.println("Clicked on item: " + item.getTitle());
                        basket.append(item.getTitle() + ", ");
                        ingredientsList.add(item.getTitle().toString());
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
                        //Toast.makeText(MainActivity.this,"You Clicked : " + item.getTitle(),Toast.LENGTH_SHORT).show();
                        System.out.println("Clicked on item: " + item.getTitle());
                        ingredientsList.add(item.getTitle().toString());
                        basket.append(item.getTitle() + ", ");
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
                       // Toast.makeText(MainActivity.this,"You Clicked : " + item.getTitle(),Toast.LENGTH_SHORT).show();
                        System.out.println("Clicked on item: " + item.getTitle());
                        basket.append(item.getTitle() + ", ");
                        ingredientsList.add(item.getTitle().toString());
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

        /*RequestQueue requestQueue = Volley.newRequestQueue(this);
        Map<String,String> params=new HashMap<String,String>();
        params.put("X-Mashape-Key","r8vXkqjsrjmsh2sYTcnFu4HIyAMGp14tqKvjsnQtsrvyKoKmmb");
        String url = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/479101/information?includeNutrition=false";
        params.put("Accept", "application/json");
        System.out.println("params is: " + params);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, new JSONObject(params), new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response){
                        //mTextView.setText("Response: " + response.toString());
                        System.out.println("The response is: " + response.toString());
                        Log.e("Rest response: " , response.toString());
                        String testString = response.toString();
                        System.out.println("testString: " + testString);

                        try{
                            JSONObject parser= new JSONObject(testString);
                            String info = parser.getString("dairyFree");
                            System.out.println("info is: " + info);
                        }
                        catch (JSONException e) {
                            System.out.println("exception while parsing");
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("ERROR", "Error occurred ", error);
                        System.out.println("error: " + error);
                        // TODO: Handle error

                    }
                }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<>();
                    headers.put("X-Mashape-Key", "r8vXkqjsrjmsh2sYTcnFu4HIyAMGp14tqKvjsnQtsrvyKoKmmb");
                    headers.put("Accept", "application/json");
                    System.out.println("headers is: " + headers);
                    return headers;
                }
        };
        System.out.println("JSON request object: " + jsonObjectRequest);
        System.out.println("Json body: " + jsonObjectRequest.getBody());
        System.out.println("Json response: " + jsonObjectRequest.toString());
        //System.out.println("Json body: " + jsonObjectRequest.getHeaders());
        requestQueue.add(jsonObjectRequest);
        System.out.println("requetqueue: " + requestQueue);
        */
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
