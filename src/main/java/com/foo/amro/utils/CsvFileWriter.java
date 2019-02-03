package com.foo.amro.utils;

import com.foo.amro.exceptions.FileUtilsException;
import com.foo.amro.pojo.DailyTransactionSummary;
import com.foo.amro.pojo.FutureTransactions;
import com.opencsv.CSVWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CsvFileWriter {

    private static Logger logger = LoggerFactory.getLogger(CsvFileWriter.class);

    public static int writeToCSV(
            final List<FutureTransactions> futureTransactionsList,
            final String outputFileName) throws FileUtilsException {
        logger.debug("In writeToCSV now...");

        final Path path;
        try {
            path = Paths.get(outputFileName);
            final List<DailyTransactionSummary> dailyTransactionSummaryList
                    = DailyTransactionSummaryGenerator.futureTrx2DailyTrxSummary(futureTransactionsList);
            final List<String[]> allContents = dailyTransactionSummaryList2StringArrayList(dailyTransactionSummaryList);

            csvWriterAll(allContents, path);

        } catch (final Exception e) {
            throw new FileUtilsException("Unable to parse the output.csv file");
        }

        logger.debug("writeToCSV was successful, now we go back.");
        return 0;
    }

    private static void csvWriterAll(List<String[]> stringArray, Path path) throws Exception {
        CSVWriter writer = new CSVWriter(new FileWriter(path.toString()));
        writer.writeAll(stringArray);
        writer.close();
    }

    private static List<String[]> dailyTransactionSummaryList2StringArrayList(final List<DailyTransactionSummary> dailyTransactionSummaryList) {
        List<String[]> result = new ArrayList<>();
        for (DailyTransactionSummary dailyTransactionSummary : dailyTransactionSummaryList) {
            String[] oneLine = new String[]{
                    dailyTransactionSummary.getClientInformation(),
                    dailyTransactionSummary.getProductInformation(),
                    dailyTransactionSummary.getTotalTransactionAmount()
            };
            result.add(oneLine);
        }
        return result;
    }
}
