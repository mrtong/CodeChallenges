package com.foo.amro.pojo;

import java.io.Serializable;
import java.util.StringJoiner;

public class DailyTransactionSummary implements Serializable {
    public DailyTransactionSummary(
            final String clientInformation,
            final String productInformation,
            final String totalAmount) {
        this.clientInformation=clientInformation;
        this.productInformation=productInformation;
        this.totalTransactionAmount = totalAmount;

    }

    public String getClientInformation() {
        return clientInformation;
    }

    public String getProductInformation() {
        return productInformation;
    }

    public String getTotalTransactionAmount() {
        return totalTransactionAmount;
    }

    private String clientInformation;
    private String productInformation;
    private String totalTransactionAmount;

    @Override
    public String toString(){
        StringJoiner sj = new StringJoiner(",");
        sj.add(clientInformation).add(productInformation).add(totalTransactionAmount);

        return sj.toString();
    }

}
