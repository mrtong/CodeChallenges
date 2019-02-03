package com.foo.amro.utils;

import com.foo.amro.exceptions.FileUtilsException;
import com.foo.amro.pojo.FutureTransactions;
import org.apache.commons.io.LineIterator;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.io.FileUtils.lineIterator;

public class InputFileReader {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(InputFileReader.class);

    public static List<FutureTransactions> readFile(final String inputFileName) throws FileUtilsException {
        logger.debug("In readFile now.");
        final List<FutureTransactions> futureTransactionsList = new ArrayList<>();

        LineIterator lt = null;
        try {
            //apache common io is used here instead of normal Java8 stuff.
            //to avoid putting the entire file in memory
            // this will also result in pretty conservative memory consumption numbers, I believe.

            lt = lineIterator(new File(inputFileName), StandardCharsets.UTF_8.name());
            int lineNumber = 0;
            while (lt.hasNext()) {
                final String oneLine = lt.nextLine();
                lineNumber++;
                final FutureTransactions futureTransactions = FutureTransactionsGenerator.parseRecord(oneLine, lineNumber);
                if (!futureTransactions.isEmptyTransaction()) {

                    futureTransactionsList.add(futureTransactions);
                }
            }

        } catch (IOException | NullPointerException e) {
            throw new FileUtilsException("Unable to parse the input file");
        } finally {
            LineIterator.closeQuietly(lt);
        }
        logger.debug("readFile was successful, now we go back.");
        return futureTransactionsList;
    }

}
