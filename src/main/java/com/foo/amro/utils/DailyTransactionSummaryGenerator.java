package com.foo.amro.utils;

import com.foo.amro.pojo.DailyTransactionSummary;
import com.foo.amro.pojo.FutureTransactions;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.apache.commons.collections4.CollectionUtils.isEmpty;

public class DailyTransactionSummaryGenerator {

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(DailyTransactionSummaryGenerator.class);

    public static List<DailyTransactionSummary> futureTrx2DailyTrxSummary(final List<FutureTransactions> futureTransactionsList) {
        logger.debug("In futureTrx2DailyTrxSummary now...");
        final List<DailyTransactionSummary> dailyTransactionSummaryList = new ArrayList<>();
        if(isEmpty(futureTransactionsList)){
            logger.debug("futureTransactionsList is empty.");
            return dailyTransactionSummaryList;
        }


        for (FutureTransactions futureTransactions : futureTransactionsList) {
            String clientInformation = generateClientInformation(futureTransactions);
            String productInformation = generateProductInformation(futureTransactions);
            String totalAmount = generateTrxAmount(futureTransactions);
            dailyTransactionSummaryList.add(new DailyTransactionSummary(clientInformation, productInformation, totalAmount));
        }

        Collections.sort(dailyTransactionSummaryList, (d1,d2)->
                d1.getProductInformation().compareToIgnoreCase(d2.getProductInformation())
        );

        logger.debug("futureTrx2DailyTrxSummary was successful,we are coming back.");
        return dailyTransactionSummaryList;

    }

    private static String generateClientInformation(final FutureTransactions futureTransactions) {
        final StringBuilder clientInformation = new StringBuilder();
        clientInformation.append(futureTransactions.getClientType())
                .append(futureTransactions.getClientNumber())
                .append(futureTransactions.getAccountNumber())
                .append(futureTransactions.getSubAccountNumber());

        return clientInformation.toString();
    }

    private static String generateProductInformation(final FutureTransactions futureTransactions) {
        StringBuilder productInformation = new StringBuilder();
        productInformation.append(futureTransactions.getExchangeCode())
                .append(futureTransactions.getProductGroupCode())
                .append(futureTransactions.getSymbol())
                .append(futureTransactions.getExpireDate());
        return productInformation.toString();
    }

    private static String generateTrxAmount(final FutureTransactions futureTransactions) {
        final Integer trxAmount = Integer.parseInt(futureTransactions.getQuantityLong()) - Integer.parseInt(futureTransactions.getQuantityShort());
        return trxAmount.toString();
    }
}
