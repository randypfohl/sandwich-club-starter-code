package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    /**
     * @param json
     * @return
     *
    {
    "name":{
    "mainName":"Chivito",
    "alsoKnownAs":[
    ]
    },
    "placeOfOrigin":"Uruguay",
    "description":"Chivito is a national dish of Uruguay, It is a thin slice of tender cooked beef steak (churrasco), with mozzarella, tomatoes, mayonnaise, black or green olives, and commonly also bacon, fried or hard-boiled eggs and ham, served as a sandwich in a bun, often accompanied by French fried potatoes. Other ingredients, such as red beets, peas, grilled or pan-fried red peppers, and slices of cucumber, may be added.",
    "image":"https://upload.wikimedia.org/wikipedia/commons/4/48/Chivito1.jpg",
    "ingredients":[
    "Bun",
    "Churrasco beef",
    "Bacon",
    "Fried or hard-boiled eggs",
    "Ham",
    "Black or green olives",
    "Mozzarella",
    "Tomatoes",
    "Mayonnaise"
    ]
    }
     */


    public static Sandwich parseSandwichJson(String json) {
        System.out.println(json);
        Sandwich sandwich = new Sandwich();

        try {
            JSONObject jsonObject = new JSONObject(json);

            sandwich.setMainName(jsonObject.getJSONObject("name").getString("mainName"));

            JSONArray arr = jsonObject.getJSONObject("name").getJSONArray("alsoKnownAs");
            List<String> altList= new ArrayList<>();
            for(int i=0; i<arr.length() ; i++){
                altList.add(arr.getString(i));
            }
            sandwich.setAlsoKnownAs(altList);

            sandwich.setPlaceOfOrigin(jsonObject.getString("placeOfOrigin"));

            sandwich.setDescription(jsonObject.getString("description"));

            sandwich.setImage(jsonObject.getString("image"));

            JSONArray ingredients = jsonObject.getJSONArray("ingredients");
            List<String> ingredientList= new ArrayList<>();
            for(int i=0; i<arr.length() ; i++){
                ingredientList.add(ingredients.getString(i));
            }
            sandwich.setIngredients(ingredientList);

        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return sandwich;
    }
}
