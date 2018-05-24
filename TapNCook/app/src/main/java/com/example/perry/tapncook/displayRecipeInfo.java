package com.example.perry.tapncook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class displayRecipeInfo extends AppCompatActivity {

    String recipeID;
    String recipeName;
    String imageURL;

    TextView title;
    ImageView image;

    StringBuilder ingredientsList = new StringBuilder();
    ArrayList<String> stepsList = new ArrayList<String>();

    TextView ingredientsText;
    TextView stepsText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_recipe_info);
        //Intent intent = getIntent();
        System.out.println("intent is: " + getIntent());
        Bundle bundle = getIntent().getExtras();

        recipeID = bundle.getString("id");
        recipeName = bundle.getString("name");
        imageURL = bundle.getString("image");

        //  recipeName1 = findViewById(R.id.recipeName1);
        title = findViewById(R.id.recipeTitle);
        image = findViewById(R.id.recipeImage);
        ingredientsText = findViewById(R.id.ingredientsList);
        stepsText = findViewById(R.id.instructions);

        title.setText(recipeName);
        Picasso.get().load(imageURL).into(image);

        /*Obtain the custom request URL based on the recipe ID*/
        StringBuilder requestUrl = new StringBuilder();
        requestUrl.append("https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/");
        requestUrl.append(recipeID);
        requestUrl.append("/analyzedInstructions?stepBreakdown=true");
        String url = requestUrl.toString();

        System.out.println("custom getrecipeinfo url is: " + url);
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/324694/analyzedInstructions?stepBreakdown=true

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response){
                        //mTextView.setText("Response: " + response.toString());
                        System.out.println("The response is: " + response.toString());
                        Log.e("Rest response: " , response.toString());
                        String testString = response.toString();
                        System.out.println("testString: " + testString);

                        try{
                            JSONObject mainObject = response.getJSONObject(0);
                            System.out.println("testObject is: " + mainObject.getString("steps"));
                            JSONArray stepsArray = new JSONArray(mainObject.getString("steps"));
                            JSONObject stepsObject = stepsArray.getJSONObject(0);
                            System.out.println("inside stepsObject is: " + stepsObject.getString("step"));

                            //to get ingredients, need to go inside stepsObject!*/
                            JSONArray ingredientsArray = new JSONArray(stepsObject.getString("ingredients"));
                            JSONObject ingredientsObject = ingredientsArray.getJSONObject(0);
                            System.out.println("inside ingredientsObject is: " + ingredientsObject.getString("name"));

                            /*Appends each ingredient to the ingredientsList string*/
                            JSONObject ingredient;
                            for (int i = 0; i < ingredientsArray.length(); i++){
                                ingredient = ingredientsArray.getJSONObject(i);

                                if (ingredientsList.toString().contains(ingredient.getString("name"))){
                                   System.out.println("Duplicate ingredient");
                                }
                                else {
                                    ingredientsList.append(ingredient.getString("name"));
                                }
                                if (i < ingredientsArray.length() - 1){
                                    ingredientsList.append(", ");
                                }
                            }

                            JSONObject step;
                            for (int i = 0; i < stepsArray.length(); i++){
                                step = stepsArray.getJSONObject(i);
                                stepsList.add("Step " + i + 1 + ": " + step.getString("step"));
                            }

                            System.out.println("ingredientsList is : " + ingredientsList);
                            System.out.println("stepsList is: " + stepsList);

                            //Updates the ingredients ViewText
                            ingredientsText.setText(ingredientsList);

                            //Updates the instructions ViewText
                            for (int i = 0; i<stepsList.size(); i++){
                                stepsText.append(stepsList.get(i));
                                stepsText.append("\n \n");
                            }

                        }
                        catch (JSONException e) {
                            System.out.println("exception while parsing");
                            e.printStackTrace();
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

        requestQueue.add(jsonArrayRequest);

        //https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/479101/information?includeNutrition=false"
    }
}
