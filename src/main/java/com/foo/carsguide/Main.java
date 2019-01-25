package com.foo.carsguide;

import com.foo.carsguide.service.InputFileParsingService;
import com.foo.carsguide.service.exception.LinesListEmptyException;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by Luke Tong on 14-Apr-16.
 */
public class Main {
    public Main(){}

    public Main(String inputFileName) throws IOException, ParseException, LinesListEmptyException {

        InputFileParsingService inputFileParsingService =  new InputFileParsingService();
        List<String> linesList = inputFileParsingService.generateListFromInputFileByLine(inputFileName);
        List<String> monthlyDealerTotal = inputFileParsingService.getMonthlyDealerTotal(linesList);
        inputFileParsingService.generateMonthlyDealerTotalsFileFromList(monthlyDealerTotal);

        List<String> parentMonthlyDealerTotal = inputFileParsingService.getParentMonthlyDealerTotal(linesList);
        inputFileParsingService.generateParentMonthlyDealerTotalsFileFromList(parentMonthlyDealerTotal);


    }

    public static void main(String[] args) throws Exception, LinesListEmptyException {
//        if(args.length!=2){
//            throw new Exception();
//        }
//
//        final String inputFilePath = args[0];
//        final String inputFileName = args[1];
//        final StringBuilder inputFile = new StringBuilder(inputFilePath).append(inputFileName);

        final String inputFile = "c:\\Temp\\Carsguide.txt";
        new Main(inputFile);
    }
}
