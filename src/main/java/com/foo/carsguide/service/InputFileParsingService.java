package com.foo.carsguide.service;

import com.foo.carsguide.service.exception.LinesListEmptyException;
import org.apache.commons.collections.MultiHashMap;
import org.apache.commons.collections.MultiMap;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Luke Tong on 14-Apr-16.
 */
public class InputFileParsingService {

    private static final String MONTHLY_DEALER_TOTLA_FILE_HEADER = "dealer_id,listing_id,date,sum_pageview";
    private static final String PARENT_MONTHLY_DEALER_TOTLA_FILE_HEADER = "dealer_id,child_dealer_id,date,sum_pageview";
    private static final String MONTHLY_DEALER_TOTLA_FILE = "c:\\Temp\\output1.txt";
    private static final int DEALER_ID = 0;
    private static final int PARENT_DEALER_ID = 1;
    private static final int LISTING_ID = 2;
    private static final int EVENT_DATE = 3;
    private static final int PAGE_VIEWS = 4;
    private static final String PARENT_MONTHLY_DEALER_TOTAL_FILE = "c:\\Temp\\output2.txt";
    private static final int HEADER = 0;


    private MultiMap listingIdPageViewsMultiMap = new MultiHashMap();
    private MultiMap parentDealerIDPageViewsMultiMap = new MultiHashMap();

    private Map<String, String> listingIdDealerIdMap = new TreeMap<String, String>();
    private Map<String, String> parentDealerIdAndDealerIdMap = new HashMap<String, String>();


    private Set<String> listingIdSet = new HashSet<String>();

    private String lastDateOfTheMonth;

    public MultiMap getListingIdPageViewsMultiMap() {
        return listingIdPageViewsMultiMap;
    }

    public Map<String, String> getListingIdDealerIdMap() {
        return listingIdDealerIdMap;
    }

    public Map<String, String> getParentDealerIdAndDealerIdMap() {
        return parentDealerIdAndDealerIdMap;
    }

    public Set<String> getListingIdSet() {
        return listingIdSet;
    }

    public InputFileParsingService() {
    }

    public List<String> generateListFromInputFileByLine(String inputFileWithFullPath) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(inputFileWithFullPath));
        List<String> lines = new ArrayList<String>();

        String sCurrentLine;
        while ((sCurrentLine = br.readLine()) != null) {
            lines.add(sCurrentLine);
        }
        lines.remove(HEADER);
        return lines;
    }

    public void generateMonthlyDealerTotalsFileFromList(List<String> monthlyDealerTotalsList) throws IOException, LinesListEmptyException {

        File file = new File(MONTHLY_DEALER_TOTLA_FILE);
        if (!file.exists()) {
            file.createNewFile();
        }

        if (monthlyDealerTotalsList == null) {
            throw new LinesListEmptyException();
        }

        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(MONTHLY_DEALER_TOTLA_FILE_HEADER);
        bw.newLine();
        for (String line : monthlyDealerTotalsList) {

            bw.write(line);
            bw.newLine();
        }
        bw.close();

    }

    public List<String> getMonthlyDealerTotal(List<String> linesList) throws ParseException, LinesListEmptyException {
        separateEachLineToDifferentItemsForDealerTotalReport(linesList);
        Map<String, Integer> listingIdSumPageViewsMap = generateListingIdSumPageViewsMap(getListingIdPageViewsMultiMap(), getListingIdSet());
        List<String> monthlyDealerTotal = generateMonthlyDealerTotalList(getListingIdDealerIdMap(), listingIdSumPageViewsMap);
        return monthlyDealerTotal;
    }

    protected void separateEachLineToDifferentItemsForDealerTotalReport(List<String> linesList) throws ParseException, LinesListEmptyException {
        if (linesList == null) {
            throw new LinesListEmptyException();
        }

        for (String aNewLine : linesList) {
            String[] result = aNewLine.split(",");

            String dealerId = result[DEALER_ID];
            String listingId = result[LISTING_ID];
            String eventDate = result[EVENT_DATE];
            String pageViews = result[PAGE_VIEWS];

            lastDateOfTheMonth = getLastDateOfTheMonthFromEventDate(eventDate);

            listingIdPageViewsMultiMap.put(listingId, pageViews);

            if (listingIdDealerIdMap.get(listingId) == null) {
                listingIdDealerIdMap.put(listingId, dealerId);
            }

            listingIdSet.add(listingId);
        }
    }

    private String getLastDateOfTheMonthFromEventDate(String eventDate) throws ParseException {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sdf.parse(eventDate));

        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DATE, -1);

        Date lastDayOfMonth = calendar.getTime();

        return sdf.format(lastDayOfMonth);
    }

    private Map<String, Integer> generateListingIdSumPageViewsMap(MultiMap listingIdPageViewsMultiMap, Set<String> listingIdSet) {
        Iterator iter = listingIdSet.iterator();
        Map<String, Integer> listingIdSumPageViewsMap = new HashMap<String, Integer>();
        while (iter.hasNext()) {
            String listingId = (String) iter.next();
            List<String> pageViewTimesList = (List<String>) listingIdPageViewsMultiMap.get(listingId);
            int sumPageViewTimes = 0;

            for (String pageViewTimes : pageViewTimesList) {
                sumPageViewTimes = sumPageViewTimes + new Integer(pageViewTimes);
            }

            listingIdSumPageViewsMap.put(listingId, sumPageViewTimes);

        }
        return listingIdSumPageViewsMap;
    }

    private List<String> generateMonthlyDealerTotalList(Map<String, String> listingIdDealerIdMap, Map<String, Integer> listingIdSumPageViewsMap) {
        List<String> lines = new ArrayList<String>();
        Iterator listingIdDealerIdIterator = listingIdDealerIdMap.keySet().iterator();
        while (listingIdDealerIdIterator.hasNext()) {
            String listingId = (String) listingIdDealerIdIterator.next();
            String dealerId = listingIdDealerIdMap.get(listingId);
            Integer sumPageViews = listingIdSumPageViewsMap.get(listingId);

            StringBuilder linesBuilder = new StringBuilder("").append(dealerId).append(",")
                    .append(listingId).append(",")
                    .append(lastDateOfTheMonth).append(",")
                    .append(sumPageViews);
            lines.add(linesBuilder.toString());
        }

        return lines;

    }
//Parent Monthly Dealer Total

    public List<String> getParentMonthlyDealerTotal(List<String> linesList) throws ParseException, LinesListEmptyException {
        separateEachLineToDifferentItemsForParentDealerTotalReport(linesList);
        List<String> parentMonthlyDealerTotal = generateMonthlyParentDealerTotalList();
        return parentMonthlyDealerTotal;
    }

    protected void separateEachLineToDifferentItemsForParentDealerTotalReport(List<String> linesList) throws ParseException, LinesListEmptyException {
        if (linesList == null) {
            throw new LinesListEmptyException();
        }

        for (String aNewLine : linesList) {
            String[] result = aNewLine.split(",");

            String dealerId = result[DEALER_ID];
            String parentDealerID = result[PARENT_DEALER_ID];
            String eventDate = result[EVENT_DATE];
            String pageViews = result[PAGE_VIEWS];

            lastDateOfTheMonth = getLastDateOfTheMonthFromEventDate(eventDate);
            if (!"null".equalsIgnoreCase(parentDealerID)) {

                parentDealerIDPageViewsMultiMap.put(parentDealerID, pageViews);
            }

            if (parentDealerIdAndDealerIdMap.get(dealerId) == null) {
                parentDealerIdAndDealerIdMap.put(parentDealerID, dealerId);
            }

        }
    }

    private List<String> generateMonthlyParentDealerTotalList() {
        List<String> lines = new ArrayList<String>();

        Iterator parentDealerIDIterator = parentDealerIDPageViewsMultiMap.keySet().iterator();
        while (parentDealerIDIterator.hasNext()) {
            String parentDealerID = (String) parentDealerIDIterator.next();
            List<String> pageViewTimesList = (List<String>) parentDealerIDPageViewsMultiMap.get(parentDealerID);
            int sumPageViewTimes = 0;

            for (String pageViewTimes : pageViewTimesList) {
                sumPageViewTimes = sumPageViewTimes + new Integer(pageViewTimes);
            }

            String childDealerID = parentDealerIdAndDealerIdMap.get(parentDealerID);

            StringBuilder linesBuilder = new StringBuilder("").append(parentDealerID).append(",")
                    .append(childDealerID).append(",")
                    .append(lastDateOfTheMonth).append(",")
                    .append(sumPageViewTimes);

            lines.add(linesBuilder.toString());
        }

        return lines;

    }


    public void generateParentMonthlyDealerTotalsFileFromList(List<String> parentMonthlyDealerTotalsList) throws IOException, LinesListEmptyException {
        File file = new File(PARENT_MONTHLY_DEALER_TOTAL_FILE);
        if (!file.exists()) {
            file.createNewFile();
        }

        if (parentMonthlyDealerTotalsList == null) {
            throw new LinesListEmptyException();
        }

        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(PARENT_MONTHLY_DEALER_TOTLA_FILE_HEADER);
        bw.newLine();
        for (String line : parentMonthlyDealerTotalsList) {

            bw.write(line);
            bw.newLine();
        }
        bw.close();
    }
}
