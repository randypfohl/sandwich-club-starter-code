package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {


        if(!sandwich.getPlaceOfOrigin().isEmpty()){
            ((TextView) findViewById(R.id.origin_tv)).setText(sandwich.getPlaceOfOrigin());
        }
        else {
            findViewById(R.id.origin_tv).setVisibility(View.GONE);
            findViewById(R.id.PlaceOfOriginLabel).setVisibility(View.GONE);
        }


        if(!sandwich.getDescription().isEmpty()){
            ((TextView) findViewById(R.id.description_tv)).setText(sandwich.getDescription());
        }
        else {
            findViewById(R.id.description_tv).setVisibility(View.GONE);
            findViewById(R.id.DescriptionLabel).setVisibility(View.GONE);
        }


        List<String> ingredients = sandwich.getIngredients();
        if(ingredients != null && !ingredients.isEmpty()){

            if(ingredients.size() == 1){
                ((TextView) findViewById(R.id.ingredients_tv)).setText(ingredients.get(0));
            }
            else {
                String ingredientString = "";
                for (String ingredient : sandwich.getIngredients()) {
                    ingredientString += ingredient + "\n";
                }
                ((TextView) findViewById(R.id.ingredients_tv)).setText(ingredientString);
            }
        }
        else {
            findViewById(R.id.ingredients_tv).setVisibility(View.GONE);
            findViewById(R.id.IngredientsLabel).setVisibility(View.GONE);
        }

        List<String> alsoKnownAs = sandwich.getAlsoKnownAs();
        if(alsoKnownAs != null && !alsoKnownAs.isEmpty()){
            String knownAs = "";

            if(alsoKnownAs.size() == 1){
                ((TextView) findViewById(R.id.also_known_tv)).setText(alsoKnownAs.get(0));
            }

            else {
                for (int i = 0; i < alsoKnownAs.size() - 1; i++) {
                    knownAs += alsoKnownAs.get(i) + ",";
                }
                knownAs += " and " + alsoKnownAs.get(alsoKnownAs.size() - 1) + ".";

                ((TextView) findViewById(R.id.also_known_tv)).setText(knownAs);
            }
        }
        else {
            findViewById(R.id.also_known_tv).setVisibility(View.GONE);
            findViewById(R.id.AlsoKnownAsLabel).setVisibility(View.GONE);
        }
    }
}
