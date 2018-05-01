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
    public static final String JSON_NAME_KEY = "name";
    public static final String JSON_MAIN_NAME_KEY = "mainName";
    public static final String JSON_ALSO_KNOWN_AS_KEY = "alsoKnownAs";
    public static final String JSON_PLACE_OF_ORIGIN_KEY = "placeOfOrigin";
    public static final String JSON_DESCRIPTION_KEY = "description";
    public static final String JSON_IMAGE_KEY = "image";
    public static final String JSON_INGREDIENTS_KEY = "ingredients";


    public static Sandwich parseSandwichJson(String json) {
        System.out.println(json);
        Sandwich sandwich = new Sandwich();

        try {
            JSONObject jsonObject = new JSONObject(json);

            if(jsonObject.getJSONObject(JSON_NAME_KEY).has(JSON_MAIN_NAME_KEY))
                sandwich.setMainName(jsonObject.getJSONObject(JSON_NAME_KEY).optString(JSON_MAIN_NAME_KEY));

            if(jsonObject.getJSONObject(JSON_NAME_KEY).has(JSON_ALSO_KNOWN_AS_KEY)) {
                JSONArray arr = jsonObject.getJSONObject(JSON_NAME_KEY).getJSONArray(JSON_ALSO_KNOWN_AS_KEY);
                List<String> altList = new ArrayList<>();
                for (int i = 0; i < arr.length(); i++) {
                    altList.add(arr.getString(i));
                }
                sandwich.setAlsoKnownAs(altList);
            }

            if(jsonObject.has(JSON_PLACE_OF_ORIGIN_KEY))
                sandwich.setPlaceOfOrigin(jsonObject.optString(JSON_PLACE_OF_ORIGIN_KEY));

            if(jsonObject.has(JSON_DESCRIPTION_KEY))
                sandwich.setDescription(jsonObject.optString(JSON_DESCRIPTION_KEY));

            if(jsonObject.has(JSON_IMAGE_KEY))
                sandwich.setImage(jsonObject.optString(JSON_IMAGE_KEY));

            if(jsonObject.has(JSON_INGREDIENTS_KEY)) {
                JSONArray ingredients = jsonObject.getJSONArray(JSON_INGREDIENTS_KEY);
                List<String> ingredientList = new ArrayList<>();
                for (int i = 0; i < ingredients.length(); i++) {
                    ingredientList.add(ingredients.getString(i));
                }
                sandwich.setIngredients(ingredientList);
            }

        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return sandwich;
    }
}
