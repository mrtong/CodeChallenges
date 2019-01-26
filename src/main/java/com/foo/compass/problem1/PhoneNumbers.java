package com.foo.compass.problem1;

import com.foo.compass.problem1.exceptions.EmptyInputPhoneNumberException;
import com.foo.compass.problem1.exceptions.InvalidInputPhoneNumberException;
import com.foo.compass.problem1.exceptions.InvalidLengthOfInputPhoneNumberException;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;
import static java.nio.charset.Charset.defaultCharset;
import static java.nio.file.Files.readAllLines;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Luke Tong on 24-Mar-16.
 */
public class PhoneNumbers {

    private final static String WORDS_EN_FILE_NAME = "wordsEn.txt";

    private final static Map<Integer, String> firstLetterMapper = new HashMap<Integer, String>();

    static {
        firstLetterMapper.put(2, "A");
        firstLetterMapper.put(0, "");
        firstLetterMapper.put(1, "");
        firstLetterMapper.put(3, "D");
        firstLetterMapper.put(4, "G");
        firstLetterMapper.put(5, "J");
        firstLetterMapper.put(6, "M");
        firstLetterMapper.put(7, "P");
        firstLetterMapper.put(8, "T");
        firstLetterMapper.put(9, "W");
    }

    ;
    private static final Logger logger = getLogger(PhoneNumbers.class);

    private List<String> generateCombos(String phoneNumber) throws InvalidInputPhoneNumberException {

        int phoneLength = phoneNumber.length();
        List<String> prefixList = new ArrayList<String>(phoneLength);

        while (phoneNumber.length() > 0) {
            try {

            int digit = parseInt(phoneNumber.substring(0, 1));
            prefixList.add(firstLetterMapper.get(digit));
            phoneNumber = phoneNumber.substring(1);
            } catch (NumberFormatException e) {
                throw new InvalidInputPhoneNumberException();
            }
        }

        return prefixList;
    }

    public static void main(String[] args) throws Exception {
//        String phone = "2345678999";
        String phone = "1234567890";
        PhoneNumbers pn = new PhoneNumbers();
        List<String> myList = pn.generateWordListByPhoneNumber(phone);
        System.out.println(myList);
    }

    public List<String> generateWordListByPhoneNumber(String inputPhoneNumber) throws IOException, EmptyInputPhoneNumberException, InvalidLengthOfInputPhoneNumberException, InvalidInputPhoneNumberException {
        if (inputPhoneNumber == null || inputPhoneNumber.equals("")) {
            throw new EmptyInputPhoneNumberException();
        }

        if (inputPhoneNumber.length() != 10) {
            throw new InvalidLengthOfInputPhoneNumberException();
        }
        final List<String> myList = new ArrayList<String>();

        //need to have the regex or else the path will start with a /
        String pathOfDictionFile = getClass().getClassLoader().getResource(WORDS_EN_FILE_NAME).getFile().replaceFirst("^/(.:/)", "$1");

        List<String> linesList = readAllLines(Paths.get(pathOfDictionFile), defaultCharset());

        List<String> phoneWordsList = generateCombos(inputPhoneNumber);

        if (logger.isInfoEnabled()) {
            logger.debug("phone word list is : " + phoneWordsList);
        }

        for (String phoneWord : phoneWordsList) {
            for (String fileLine : linesList) {
                if (fileLine.startsWith("" + phoneWord.toLowerCase())) {
                    myList.add(fileLine);
                }
            }
        }

        return myList;
    }

}