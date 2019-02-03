package com.foo.amro.utils;

import com.foo.amro.pojo.FutureTransactions;
import org.slf4j.LoggerFactory;

public class FutureTransactionsGenerator {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(FutureTransactionsGenerator.class);

    public static FutureTransactions parseRecord(final String oneLine, int lineNumber) {

        if (oneLine == null || oneLine.length() != 176) {
            logger.error("Line number {} within input file is an invalid one.", lineNumber);
            FutureTransactions futureTransactions = new FutureTransactions();
            futureTransactions.setEmptyTransaction(true);
            return futureTransactions;
        }

        String clientType = oneLine.substring(3, 7).trim();
        String clientNumber = oneLine.substring(7, 11).trim();
        String accountNumber = oneLine.substring(11, 15).trim();
        String subAccountNumber = oneLine.substring(15, 19).trim();
        String exchangeCode = oneLine.substring(27, 31).trim();
        String productGroupCode = oneLine.substring(25, 27).trim();
        String symbol = oneLine.substring(31, 37).trim();
        String expireDate = oneLine.substring(37, 45).trim();
        String quantityLong = oneLine.substring(52, 62).trim();
        String quantityShort = oneLine.substring(63, 73).trim();

        return new FutureTransactions(
                clientType,
                clientNumber,
                accountNumber,
                subAccountNumber,
                exchangeCode,
                productGroupCode,
                symbol,
                expireDate,
                quantityLong,
                quantityShort

        );
    }
}
