package com.foo.pactera.recipefinder.parser;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.foo.pactera.recipefinder.model.Ingredient;
import com.foo.pactera.recipefinder.model.Recipe;
import com.foo.pactera.recipefinder.model.Unit;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.core.JsonToken.END_OBJECT;
import static com.fasterxml.jackson.core.JsonToken.START_OBJECT;
import static com.foo.pactera.recipefinder.model.Unit.*;
import static java.nio.file.Files.readAllBytes;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by yanjuntong on 16/04/15.
 */
public class RecipeParser {

    private static final Logger logger = getLogger(RecipeParser.class);

    public static List<Recipe> produceRecipes(File recipeFile)
    {
        try {
            return produceRecipes(new String(readAllBytes(recipeFile.toPath())));
        }
        catch (IOException e) {
            logger.error(e.getMessage());
        }

        return null;
    }

    public static List<Recipe> produceRecipes(String recipeFile)
    {
        final List<Recipe> recipeList = new ArrayList<Recipe>();

        try {
            JsonParser jsonParser = new JsonFactory().createParser(recipeFile);

            jsonParser.nextToken();
            while (jsonParser.nextToken() == START_OBJECT) {
                jsonParser.nextToken();
                String recipeName = null;
                final List<Ingredient> ingredients = new ArrayList<Ingredient>();
                while (jsonParser.nextToken() != END_OBJECT) {

                    final String fieldName = jsonParser.getCurrentName();
                    if ("name".equals(fieldName)) {
                        recipeName = jsonParser.getText();
                    }
                    else if ("ingredients".equals(fieldName)) {
                        parseIngredients(jsonParser, ingredients);
                    }
                }

                if (recipeName != null) {
                    recipeList.add(new Recipe(recipeName, ingredients));
                }
            }
        }
        catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
        }
        catch (JsonParseException e) {
            logger.error(e.getMessage());
        }
        catch (IOException e) {
            logger.error(e.getMessage());
        }
        return recipeList;
    }

    private static void parseIngredients(JsonParser jsonParser, final List<Ingredient> ingredients) throws IOException {
        jsonParser.nextToken();
        while (jsonParser.nextToken() == START_OBJECT) {
            String item = null;
            int amount = 0;
            Unit unit = null;
            while (jsonParser.nextToken() != END_OBJECT) {
                String namefield = jsonParser.getCurrentName();
                jsonParser.nextToken();
                switch (namefield) {
                    case "item":
                        item = jsonParser.getValueAsString();
                        break;
                    case "unit":
                        unit = valueOf(jsonParser.getValueAsString());
                        break;
                    case "amount":
                        amount = jsonParser.getValueAsInt();
                        break;
                }
            }

            if (amount > 0 && unit != null) {
                ingredients.add(new Ingredient(item, amount, unit, null));
            }
        }
    }
}