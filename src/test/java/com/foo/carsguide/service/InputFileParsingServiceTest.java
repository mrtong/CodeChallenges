package com.foo.carsguide.service;

import com.foo.carsguide.service.exception.LinesListEmptyException;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class InputFileParsingServiceTest {
    private InputFileParsingService inputFileParsingService;
    private List<String> linesList = new ArrayList<String>();
    private List<String> expectedOutputList = new ArrayList<String>();
    private List<String> expectedParentOutputList = new ArrayList<String>();
    private static final String OUTPUT_FILE1 = "c:\\Temp\\output1.txt";
    private static final String OUTPUT_FILE2 = "c:\\Temp\\output2.txt";

    @Before
    public void setUp() throws Exception {

        inputFileParsingService = new InputFileParsingService();

        linesList.add("00001,01000,100,2015-01-01,2");
        linesList.add("00001,01000,101,2015-01-01,4");
        linesList.add("00001,01000,102,2015-01-01,7");
        linesList.add("00001,01000,100,2015-01-02,5");
        linesList.add("00001,01000,101,2015-01-02,1");
        linesList.add("00001,01000,102,2015-01-02,0");
        linesList.add("00002,null,200,2015-01-01,10");

        expectedOutputList.add("00002,200,2015-01-31,10");
        expectedOutputList.add("00001,102,2015-01-31,7");
        expectedOutputList.add("00001,101,2015-01-31,5");
        expectedOutputList.add("00001,100,2015-01-31,7");

        expectedParentOutputList.add("01000,00001,2015-01-31,19");

    }

    @Test
    public void shouldParseListAndGenerateOutPutList() throws ParseException, LinesListEmptyException {

        List<String> actualOutPutList = inputFileParsingService.getMonthlyDealerTotal(linesList);
        assertThat(actualOutPutList, is(expectedOutputList));
    }

    @Test
    public void shoudlGenerateListFromInputFileByLine() throws IOException {
        List<String> lines = inputFileParsingService.generateListFromInputFileByLine("c:\\Temp\\Carsguide.txt");
        assertNotNull(lines);
        assertThat(lines.size(), is(7));

    }

    @Test(expected = IOException.class)
    public void shouldRaiseExceptionWhenFileIsInvalid() throws IOException {
        inputFileParsingService.generateListFromInputFileByLine("");
    }

    @Test
    public void shouldParseEachLineForDealerTotalReport() throws ParseException, LinesListEmptyException {
        inputFileParsingService.separateEachLineToDifferentItemsForDealerTotalReport(linesList);
        assertThat(inputFileParsingService.getListingIdSet(), notNullValue());
        assertThat(inputFileParsingService.getListingIdPageViewsMultiMap(), notNullValue());

        assertThat(inputFileParsingService.getListingIdSet().size(), not(0));
        assertThat(inputFileParsingService.getListingIdPageViewsMultiMap().size(), not(0));

    }

    @Test
    public void shouldGenerateOutPutFileFromList() throws IOException, LinesListEmptyException {
        inputFileParsingService.generateMonthlyDealerTotalsFileFromList(expectedOutputList);
        File file = new File(OUTPUT_FILE1);
        assertTrue(file.exists());
    }

    @Test
    public void shouldGenerateOutPutFileFromListEvenWhenOutputListIsEmpty() throws IOException, LinesListEmptyException {
        inputFileParsingService.generateMonthlyDealerTotalsFileFromList(new ArrayList<String>());
        File file = new File(OUTPUT_FILE1);
        assertTrue(file.exists());
    }

    @Test(expected = LinesListEmptyException.class)
    public void shouldRaiseExceptionWhenOutputListIsNull() throws IOException, LinesListEmptyException {
        inputFileParsingService.generateMonthlyDealerTotalsFileFromList(null);
    }


    @Test
    public void shouldParseListAndGenerateParentOutPutList() throws ParseException, LinesListEmptyException {

        List<String> actualOutPutList = inputFileParsingService.getParentMonthlyDealerTotal(linesList);
        assertThat(actualOutPutList, is(expectedParentOutputList));
    }

    @Test
    public void shouldParseEachLineForParentDealerTotalReport() throws ParseException, LinesListEmptyException {
        inputFileParsingService.separateEachLineToDifferentItemsForParentDealerTotalReport(linesList);
        assertThat(inputFileParsingService.getParentDealerIdAndDealerIdMap(), notNullValue());

    }

    @Test
    public void shouldGenerateParentOutPutFileFromList() throws IOException, LinesListEmptyException {
        inputFileParsingService.generateMonthlyDealerTotalsFileFromList(expectedOutputList);
        File file = new File(OUTPUT_FILE2);
        assertTrue(file.exists());
    }

    @Test
    public void shouldGenerateParentOutPutFileFromListEvenWhenOutputListIsEmpty() throws IOException, LinesListEmptyException {
        inputFileParsingService.generateParentMonthlyDealerTotalsFileFromList(new ArrayList<String>());
        File file = new File(OUTPUT_FILE2);
        assertTrue(file.exists());
    }

    @Test(expected = LinesListEmptyException.class)
    public void shouldRaiseExceptionWhenParentOutputListIsNull() throws IOException, LinesListEmptyException {
        inputFileParsingService.generateParentMonthlyDealerTotalsFileFromList(null);
    }

}
