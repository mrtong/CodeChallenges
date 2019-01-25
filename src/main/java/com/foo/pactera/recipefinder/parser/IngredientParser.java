package com.foo.pactera.recipefinder.parser;

/**
 * Created by yanjuntong on 16/04/15.
 */

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.foo.pactera.recipefinder.exceptions.InvalidIngredientFileException;
import com.foo.pactera.recipefinder.model.Ingredient;
import com.foo.pactera.recipefinder.model.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.fasterxml.jackson.dataformat.csv.CsvParser.Feature.WRAP_AS_ARRAY;
import static com.foo.pactera.recipefinder.model.Unit.*;
import static java.util.Calendar.*;


public class IngredientParser {

    private static final Logger logger = LoggerFactory.getLogger(IngredientParser.class);
    private static final String INGREDIENT_ITEM_SEPARATOR = ",";

    public static Map<String, Ingredient> produceIngredients(File ingredientFile) throws InvalidIngredientFileException{
        //The IngredientFile is a csv formatted file
        List<String> ingredientLinesList = readFile(ingredientFile);

        if(ingredientLinesList ==  null || ingredientLinesList.isEmpty()){
            throw new InvalidIngredientFileException("WARNING: The input file is invalid!");
        }
        return produceIngredients(ingredientLinesList);
    }

    private static List<String> readFile(File ingredientFile) {
        final List<String> lineListInIngredientFile = new ArrayList<String>();
        BufferedReader reader = null;
        if (ingredientFile == null) {
            return lineListInIngredientFile;
        }

        try {
            reader = new BufferedReader(new FileReader(ingredientFile));
            String line;

            while ((line = reader.readLine()) != null) {
                lineListInIngredientFile.add(line);
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }

        return lineListInIngredientFile;
    }

    public static Map<String, Ingredient> produceIngredients(List<String> ingredientLines) {
        final Map<String, Ingredient> ingredientMap = new HashMap<String, Ingredient>();

        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        final Calendar calendar = getInstance();

        calendar.set(HOUR_OF_DAY, 0);
        calendar.set(MINUTE, 0);
        calendar.set(SECOND, 0);
        calendar.set(MILLISECOND, 0);

        final Date today = calendar.getTime();

        CsvMapper mapper = new CsvMapper();
        mapper.enable(WRAP_AS_ARRAY);

        for (String ingredientLine : ingredientLines) {
            String[] items = ingredientLine.split(INGREDIENT_ITEM_SEPARATOR);
            Ingredient ingredient = parseIngredient(items, dateFormat);
            addIngredient(ingredient, ingredientMap, today);
        }

        return ingredientMap;
    }


    private static void addIngredient(Ingredient ingredient, Map<String, Ingredient> ingredientMap, Date today) {
        if (ingredient != null && ingredient.getAmount() > 0 && ingredient.getUnit() != null && today.compareTo(ingredient.getUseBy()) <= 0) {
            final Ingredient oldIngredient = ingredientMap.get(ingredient.getItem());
            if (oldIngredient != null) {
                final Date useBy = (oldIngredient.getUseBy().compareTo(ingredient.getUseBy()) < 0) ? oldIngredient.getUseBy() : ingredient.getUseBy();
                final int amount = oldIngredient.getAmount() + ingredient.getAmount();
                ingredient = new Ingredient(oldIngredient.getItem(), amount, oldIngredient.getUnit(), useBy);
            }

            ingredientMap.put(ingredient.getItem(), ingredient);
        }
    }


    private static Ingredient parseIngredient(String[] items, SimpleDateFormat dateFormat) {
        final int ITEM_SIZE = 4;
        Ingredient ingredient = null;
        if (items != null && items.length == ITEM_SIZE) {
            try {
                final String item = items[0];
                final int amount = Integer.parseInt(items[1]);

                final Unit unit = valueOf(items[2]);
                final Date useBy = dateFormat.parse(items[3]);

                ingredient = new Ingredient(item, amount, unit, useBy);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }

        return ingredient;

    }
}
