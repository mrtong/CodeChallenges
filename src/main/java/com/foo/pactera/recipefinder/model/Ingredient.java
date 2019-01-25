package com.foo.pactera.recipefinder.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by yanjuntong on 16/04/15.
 */
public class Ingredient implements Serializable{

    private final String item;

    private final int amount;

    private final Unit unit;

    private final Date useBy;


    public Ingredient(String item, int amount, Unit unit, Date useBy) {
        this.item = item;
        this.amount = amount;
        this.unit = unit;
        this.useBy = useBy;
    }

    public String getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }

    public Unit getUnit() {
        return unit;
    }

    public Date getUseBy() {
        return useBy;
    }
}