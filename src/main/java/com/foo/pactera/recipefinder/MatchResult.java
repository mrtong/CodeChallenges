package com.foo.pactera.recipefinder;

import com.foo.pactera.recipefinder.model.Recipe;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.sort;

public class MatchResult {

    private final Recipe recipe;

    private final List<Long> expiredTimes = new ArrayList<Long>();

    public MatchResult(Recipe recipe) {
        this.recipe = recipe;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public List<Long> getExpiredTimes() {
        return expiredTimes;
    }

    public void addExpiredTime(Long expiredTime) {
        this.expiredTimes.add(expiredTime);
    }

    public void sortExpiredTimes() {
        sort(expiredTimes);
    }
}
