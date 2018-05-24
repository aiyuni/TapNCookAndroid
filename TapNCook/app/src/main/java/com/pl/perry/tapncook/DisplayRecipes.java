package com.pl.perry.tapncook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DisplayRecipes extends AppCompatActivity {

    private EditText testText;
    private String recipeInfoResponse;
    private String recipeListResponse;
    private StringBuilder getRecipesUrl = new StringBuilder();

    private String recipeID1;
    private String recipeID2;
    private String recipeID3;
   // private String recipeID4;
    //private String recipeID5;
    private ImageButton recipeImage1;
    private ImageButton recipeImage2;
    private ImageButton recipeImage3;
    private String recipeImageUrl1;
    private String recipeImageUrl2;
    private String recipeImageUrl3;
    private TextView recipeName1;
    private TextView recipeName2;
    private TextView recipeName3;

    private String recipeStringName1;
    private String recipeStringName2;
    private String recipeStringName3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_recipes);
        //Intent intent = getIntent();
        System.out.println("intent is: " + getIntent());
        Bundle bundle = getIntent().getExtras();

        String[] ingredientsList = bundle.getStringArray("userInput");

        //https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/findByIngredients?fillIngredients=false&ingredients=apples%2Cflour%2Csugar&limitLicense=false&number=5&ranking=2
        /*Creates the custom URL */
        getRecipesUrl.append("https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/findByIngredients?fillIngredients=false&ingredients=");
        for (int i = 0; i < ingredientsList.length; i++){
            getRecipesUrl.append(ingredientsList[i]);
            getRecipesUrl.append("%2C");
        }
        getRecipesUrl.append("&limitLicense=false&number=5&ranking=2");

        System.out.println("the url is: " + getRecipesUrl.toString());

        /*API call */
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        Map<String,String> params=new HashMap<String,String>();
        params.put("X-Mashape-Key","r8vXkqjsrjmsh2sYTcnFu4HIyAMGp14tqKvjsnQtsrvyKoKmmb");
        String url = getRecipesUrl.toString();
        params.put("Accept", "application/json");
        System.out.println("params is: " + params);

        JsonArrayRequest jsonArrayRequest = null;  //need to set to null or syntax error

        recipeImage1 = (ImageButton) findViewById(R.id.recipe1); //sets the recipeImage1 to the right imageButton
        recipeImage2 = (ImageButton) findViewById(R.id.recipe2);
        recipeImage3 = (ImageButton) findViewById(R.id.recipe3);

        recipeName1 = findViewById(R.id.recipeName1);
        recipeName2 = findViewById(R.id.recipeName2);
        recipeName3 = findViewById(R.id.recipeName3);


        /*Attempts to make API call*/
        try {
            jsonArrayRequest = new JsonArrayRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                        @Override
                        public void onResponse(JSONArray response) {
                            //mTextView.setText("Response: " + response.toString());
                            System.out.println("The response is: " + response.toString());
                            Log.e("Rest response: ", response.toString());
                            recipeInfoResponse = response.toString();
                            /*try {
                                JSONArray reader = new JSONArray(recipeInfoResponse);
                                //recipeID1 = reader[0].getString("id");
                            }
                            catch (JSONException e){
                                System.out.println("reader had a jsoneexception error...");
                                e.printStackTrace();
                            } */
                            try {

                                JSONObject idObject1 = response.getJSONObject(0);
                                recipeID1 = idObject1.getString("id");
                                recipeName1.setText(idObject1.getString("title"));

                                JSONObject idObject2 = response.getJSONObject(1);
                                recipeID2 = idObject2.getString("id");
                                recipeName2.setText(idObject2.getString("title"));

                                JSONObject idObject3 = response.getJSONObject(2);
                                recipeID3 = idObject3.getString("id");
                                recipeName3.setText(idObject3.getString("title"));
                                /*JSONObject idObject4 = response.getJSONObject(3);
                                recipeID4 = idObject4.getString("id");
                                JSONObject idObject5 = response.getJSONObject(4);
                                recipeID5 = idObject5.getString("id"); */

                                recipeStringName1 = recipeName1.getText().toString();
                                recipeStringName2 = recipeName2.getText().toString();
                                recipeStringName3 = recipeName3.getText().toString();

                                recipeName1.setMovementMethod(new ScrollingMovementMethod());
                                recipeName2.setMovementMethod(new ScrollingMovementMethod());
                                recipeName3.setMovementMethod(new ScrollingMovementMethod());

                                //Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(imageView);
                                System.out.println("is this the image? " + idObject1.getString(("image")));
                                recipeImageUrl1 = idObject1.getString("image");
                                recipeImageUrl2 = idObject2.getString("image");
                                recipeImageUrl3 = idObject3.getString("image");
                                Picasso.get().load(idObject1.getString("image")).into(recipeImage1);  //this changes the image to the proper one
                                Picasso.get().load(idObject2.getString("image")).into(recipeImage2);
                                Picasso.get().load(idObject3.getString("image")).into(recipeImage3);

                                System.out.println("the recipeID inside onResponse is: " + recipeID1);
                            }
                            catch (JSONException e){
                                System.out.println("recipeID1 had a jsoneexception error...");
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("ERROR", "Error occurred ", error);
                            System.out.println("error: " + error.getCause());
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
        }
        catch (Exception e){
            System.out.println("something went wrong");
            System.out.println(e);
        }
        requestQueue.add(jsonArrayRequest);

        System.out.println("hello this does not get printed or does it");
        System.out.println("jsonarrayrequest: " + jsonArrayRequest);

        System.out.println("The recipe response outside try/catch is: " + recipeInfoResponse);

        /*Event trigger codes below*/
        final DisplayRecipes displayRecipes = this;
        View.OnClickListener pressedRecipe1 = new View.OnClickListener() {
            public void onClick(View view){
                System.out.println("Clicked on recipe name: " + recipeName1.getText() + ", ID: " + recipeID1);
                Intent intent = new Intent(displayRecipes, displayRecipeInfo.class);
                intent.putExtra("id", recipeID1);
                intent.putExtra("image", recipeImageUrl1);
                intent.putExtra("name", recipeStringName1);
                startActivity(intent);
            }
        };

        View.OnClickListener pressedRecipe2 = new View.OnClickListener() {
            public void onClick(View view){
                System.out.println("Clicked on recipe name: " + recipeName2.getText() + ", ID: " + recipeID2);
                Intent intent = new Intent(displayRecipes, displayRecipeInfo.class);
                intent.putExtra("id", recipeID2);
                intent.putExtra("image", recipeImageUrl2);
                intent.putExtra("name", recipeStringName2);
                startActivity(intent);
            }
        };

        View.OnClickListener pressedRecipe3 = new View.OnClickListener() {
            public void onClick(View view){
                System.out.println("Clicked on recipe name: " + recipeName3.getText() + ", ID: " + recipeID3);
                Intent intent = new Intent(displayRecipes, displayRecipeInfo.class);
                intent.putExtra("id", recipeID3);
                intent.putExtra("image", recipeImageUrl3);
                intent.putExtra("name", recipeStringName3);
                startActivity(intent);
            }
        };

        recipeImage1.setOnClickListener(pressedRecipe1);
        recipeImage2.setOnClickListener(pressedRecipe2);
        recipeImage3.setOnClickListener(pressedRecipe3);

    }
}
