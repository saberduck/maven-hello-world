package com.mycompany.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.ArrayUtils;

/**
 * Hello world!
 */
public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("Application starting...");
        
        logger.debug("This is a debug message");
        logger.info("Hello World! - Application is running successfully");
        logger.warn("This is a warning message - for demonstration purposes");
        
        // Demonstrate Commons Lang3 usage
        demonstrateCommonsLang3();
        
        // Simulate some processing
        processData("Sample Data");
        
        logger.info("Application finished successfully");
    }
    
    private static void demonstrateCommonsLang3() {
        logger.info("Demonstrating Apache Commons Lang3 utilities:");
        
        // StringUtils examples
        String testString = "  Hello Commons Lang3!  ";
        logger.info("Original string: '{}'", testString);
        logger.info("Trimmed to null: '{}'", StringUtils.trimToNull(testString));
        logger.info("Is blank: {}", StringUtils.isBlank(""));
        logger.info("Is not empty: {}", StringUtils.isNotEmpty(testString));
        logger.info("Capitalized: '{}'", StringUtils.capitalize("hello world"));
        logger.info("Reversed: '{}'", StringUtils.reverse("Hello World"));
        
        // NumberUtils examples
        String numberString = "123.45";
        logger.info("String '{}' to double: {}", numberString, NumberUtils.toDouble(numberString));
        logger.info("Is '{}' a number: {}", numberString, NumberUtils.isCreatable(numberString));
        logger.info("Max of (10, 20, 15): {}", NumberUtils.max(10, 20, 15));
        
        // RandomStringUtils examples
        String randomAlphabetic = RandomStringUtils.randomAlphabetic(8);
        String randomNumeric = RandomStringUtils.randomNumeric(5);
        logger.info("Random alphabetic string (8 chars): '{}'", randomAlphabetic);
        logger.info("Random numeric string (5 digits): '{}'", randomNumeric);
        
        // ArrayUtils examples
        int[] numbers = {1, 2, 3, 4, 5};
        int[] moreNumbers = ArrayUtils.add(numbers, 6);
        logger.info("Original array: {}", ArrayUtils.toString(numbers));
        logger.info("Array after adding 6: {}", ArrayUtils.toString(moreNumbers));
        logger.info("Array contains 3: {}", ArrayUtils.contains(numbers, 3));
        
        String[] fruits = {"apple", "banana", "orange"};
        logger.info("Joined fruits: '{}'", StringUtils.join(fruits, ", "));
    }
    
    private static void processData(String data) {
        logger.debug("Processing data: {}", data);
        
        try {
            // Simulate some work
            Thread.sleep(100);
            logger.info("Successfully processed data: {}", data);
        } catch (InterruptedException e) {
            logger.error("Error processing data: {}", data, e);
            Thread.currentThread().interrupt();
        }
    }
}
