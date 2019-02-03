package com.foo.amro.utils;

import com.foo.amro.exceptions.FileUtilsException;
import com.foo.amro.pojo.FutureTransactions;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DailySummaryReportService {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(DailySummaryReportService.class);

    private static DailySummaryReportService instance;
    private DailySummaryReportService() {

    }

    public static DailySummaryReportService newInstance() {
        if (instance == null) {
            instance = new DailySummaryReportService();
        }

        return instance;
    }

    public int generateDailyReport(final String inputFileName, final String outOutfileName) throws FileUtilsException {
        final List<FutureTransactions> futureTransactionsList = InputFileReader.readFile(inputFileName);
        if(futureTransactionsList.isEmpty()){
            logger.error("no FutureTransactions can be found.");
            return -1;
        }

        return CsvFileWriter.writeToCSV(futureTransactionsList,outOutfileName);
    }
}
