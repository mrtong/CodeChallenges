package com.foo.pactera.recipefinder;

import com.foo.pactera.recipefinder.exceptions.InvalidIngredientFileException;
import com.foo.pactera.recipefinder.model.Ingredient;
import com.foo.pactera.recipefinder.model.Recipe;

import java.io.File;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import static com.foo.pactera.recipefinder.parser.IngredientParser.produceIngredients;
import static com.foo.pactera.recipefinder.parser.RecipeParser.produceRecipes;
import static java.util.Calendar.*;
import static org.apache.commons.lang3.StringUtils.isEmpty;
public class RecipeFinder {
    private final long today;

    private final static String NO_RECIPE_FOUND = "Order Takeout";

    public RecipeFinder() {
        final Calendar calendar = getInstance();

        calendar.set(HOUR, 0);
        calendar.set(MINUTE, 0);
        calendar.set(SECOND, 0);
        today = calendar.getTimeInMillis();
    }

    public String findRecipeInIngredientItems(List<String> ingredientItems, String recipeText) {

        if (isEmptyList(ingredientItems) || isEmpty(recipeText)) {
            return NO_RECIPE_FOUND;
        }
        final Map<String, Ingredient> ingredientMap = produceIngredients(ingredientItems);
        final List<Recipe> recipeList = produceRecipes(recipeText);
        return findRecipe(ingredientMap, recipeList);

    }

    private boolean isEmptyList(List<? extends Object> list) {
        return list == null || list.isEmpty();
    }

    public String findRecipeInFridge(String fridgeCsvFile, String recipeFile) {
        if (isEmpty(fridgeCsvFile)|| isEmpty(recipeFile)) {
            return NO_RECIPE_FOUND;
        }
        Map<String, Ingredient> ingredientMap;

        try {
            ingredientMap = produceIngredients(new File(fridgeCsvFile));
            final List<Recipe> recipes = produceRecipes(new File(recipeFile));
            return findRecipe(ingredientMap, recipes);

        } catch (InvalidIngredientFileException e) {
            return NO_RECIPE_FOUND;
        }

    }

    /**
     * @return Name of recipe if found, otherwise return NO_RECIPE_FOUND
     */
    private String findRecipe(Map<String, Ingredient> ingredientMap, List<Recipe> recipeList) {
        if (ingredientMap ==null || ingredientMap.isEmpty()|| recipeList == null || recipeList.isEmpty()) {
            return NO_RECIPE_FOUND;
        }

        MatchResult result = null;
        for (Recipe recipe : recipeList) {
            result = match(ingredientMap, recipe, result);
        }

        if (result == null || result.getRecipe() == null) {
            return NO_RECIPE_FOUND;
        }

        return result.getRecipe().getName();
    }

    /**
     * @param ingredientMap Map
     * @param recipe Recipe
     * @param currentResult MatchResult
     * @return The Best match result
     */
    private MatchResult match(Map<String, Ingredient> ingredientMap, Recipe recipe, MatchResult currentResult) {
        if (recipe == null || ingredientMap == null) {
            return currentResult;
        }

        final MatchResult newResult = match(ingredientMap, recipe);
        return compareCurrentResultAgainstNewResult(currentResult, newResult);
    }

    /**
     * @param currentMatchResult MatchResult
     * @param newMatchResult MatchResult
     * @return the closest use-by items recipe
     */
    private MatchResult compareCurrentResultAgainstNewResult(MatchResult currentMatchResult, MatchResult newMatchResult) {
        if (newMatchResult == null
                || newMatchResult.getExpiredTimes() == null
                || newMatchResult.getExpiredTimes().isEmpty()) {
            return currentMatchResult;
        }

        if (currentMatchResult == null) {
            return newMatchResult;
        }

        List<Long> currentExpiredTimes = currentMatchResult.getExpiredTimes();
        List<Long> newExpiredTimes = newMatchResult.getExpiredTimes();

        int size = (currentExpiredTimes.size() < newExpiredTimes.size()) ? currentExpiredTimes.size() : newExpiredTimes.size();

        for (int i = 0; i < size; i++) {
            if (currentExpiredTimes.get(i) < newExpiredTimes.get(i)) {
                return currentMatchResult;
            }
            else if (currentExpiredTimes.get(i) > newExpiredTimes.get(i)) {
                return newMatchResult;
            }
        }

        return (currentExpiredTimes.size() > newExpiredTimes.size()) ? currentMatchResult : newMatchResult;
    }

    /**
     * @param ingredientMap Map
     * @param recipe Recipe
     * @return The found MatchResult, if not found then return null
     */
    private MatchResult match(Map<String, Ingredient> ingredientMap, Recipe recipe) {
        if (recipe == null || ingredientMap == null) {
            return null;
        }

        final MatchResult matchResult = new MatchResult(recipe);

        for (Ingredient recipeIngredient : recipe.getIngredients()) {
            Ingredient ingredient = ingredientMap.get(recipeIngredient.getItem());
            if (ingredient == null || ingredient.getAmount() < recipeIngredient.getAmount() || ingredient.getUnit() != recipeIngredient.getUnit()) {
                return null;
            }

            matchResult.addExpiredTime(ingredient.getUseBy().getTime() - today);
        }

        matchResult.sortExpiredTimes();

        return matchResult;
    }

    public static void main(String[] args){

        if (args == null || args.length < 2) {
            System.out.println("Invalid argument: fridge Csv file name and given recipe file name are required as arguments");
            return;
        }

        String result = new RecipeFinder().findRecipeInFridge(args[0], args[1]);
        System.out.println("\n\nRecipe for tonight is : " + result);
    }
}