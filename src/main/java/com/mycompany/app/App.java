package com.mycompany.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.io.FileUtils;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.apache.commons.collections.functors.InvokerTransformer;
// import net.java.dev.jna.Platform; // Optional - may not be available in all environments

import java.io.File;
import java.io.StringReader;

/**
 * Vulnerability Testing Application
 * 
 * This application contains intentionally vulnerable dependencies for testing
 * SCA (Software Composition Analysis) tools and remediation agents.
 */
@SuppressWarnings({"unused", "java:S2629"}) // Suppress warnings for intentional test vulnerabilities
public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("Application starting...");
        
        logger.debug("This is a debug message");
        logger.info("Hello World! - Application is running successfully");
        logger.warn("This is a warning message - for demonstration purposes");
        
        // Demonstrate Commons Lang3 usage
        demonstrateCommonsLang3();
        
        // Demonstrate vulnerability scenarios
        demonstrateVulnerabilityScenarios();
        
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
    
    private static void demonstrateVulnerabilityScenarios() {
        logger.info("=== VULNERABILITY TESTING SCENARIOS ===");
        
        // Scenario 1: Package Upgrade - CVE-2025-48924 in commons-lang3
        demonstratePackageUpgradeScenario();
        
        // Scenario 2: Transitive Dependency - Vulnerable Jackson via Spring
        demonstrateTransitiveDependencyScenario();
        
        // Scenario 3: Removal/Replacement - Unused commons-io 
        demonstrateRemovalScenario();
        
        // Scenario 4: Configuration Mitigation - XXE in dom4j
        demonstrateConfigurationMitigationScenario();
        
        // Scenario 5: Code-Level Mitigation - Deserialization in commons-collections
        demonstrateCodeLevelMitigationScenario();
        
        // Scenario 6: Acceptance/Risk Justification - Platform-specific JNA
        demonstrateRiskAcceptanceScenario();
    }
    
    private static void demonstratePackageUpgradeScenario() {
        logger.info("--- Scenario 1: Package Upgrade Required ---");
        try {
            // CVE-2025-48924: Uncontrolled recursion in ClassUtils.getClass()
            // This could cause StackOverflowError with very long inputs
            String longClassName = "a".repeat(500) + ".TestClass"; // Reduced to avoid excessive memory usage
            logger.info("Testing ClassUtils.getClass with input length: {}", longClassName.length());
            
            // In vulnerable version, this could cause StackOverflowError
            Class<?> clazz = ClassUtils.getClass(longClassName, false);
            logger.info("Result: {}", clazz);
        } catch (ClassNotFoundException e) {
            logger.warn("Expected ClassNotFoundException for testing purposes: {}", e.getMessage());
        } catch (Exception e) {
            logger.warn("Expected exception for testing purposes: {}", e.getMessage());
        }
    }
    
    private static void demonstrateTransitiveDependencyScenario() {
        logger.info("--- Scenario 2: Transitive Dependency Management ---");
        try {
            // Spring Web 5.3.21 brings vulnerable Jackson dependencies
            HttpClientExample httpClient = new HttpClientExample();
            httpClient.demonstrateTransitiveDependencyScenario();
            
            // Simulate usage that would trigger vulnerable Jackson code
            String mockResponse = httpClient.makeHttpRequest("https://api.example.com/data");
            logger.info("HTTP client response: {}", mockResponse);
            
        } catch (NoClassDefFoundError | Exception e) {
            logger.warn("Transitive dependency scenario error: {}", e.getMessage());
        }
    }
    
    private static void demonstrateRemovalScenario() {
        logger.info("--- Scenario 3: Removal/Replacement - Unused Dependency ---");
        try {
            // commons-io 2.6 has CVE-2021-29425 (Path traversal)
            // This dependency is not actually needed and should be removed
            logger.info("commons-io available but unused - candidate for removal");
            logger.info("File utilities available: {}", FileUtils.class.getSimpleName());
        } catch (NoClassDefFoundError | Exception e) {
            logger.warn("Error accessing commons-io: {}", e.getMessage());
        }
    }
    
    private static void demonstrateConfigurationMitigationScenario() {
        logger.info("--- Scenario 4: Configuration Mitigation - XXE Prevention ---");
        try {
            // dom4j 2.1.3 has CVE-2020-10683 (XXE vulnerability)
            // Should be configured to disable external entity processing
            SAXReader reader = new SAXReader();
            logger.info("XML parser available - needs secure configuration");
            
            // This would be vulnerable to XXE attacks without proper configuration
            String safeXml = "<root><data>test</data></root>";
            Document doc = reader.read(new StringReader(safeXml));
            logger.info("Parsed XML root element: {}", doc.getRootElement().getName());
        } catch (NoClassDefFoundError | Exception e) {
            logger.warn("XML parsing error: {}", e.getMessage());
        }
    }
    
    private static void demonstrateCodeLevelMitigationScenario() {
        logger.info("--- Scenario 5: Code-Level Mitigation - Deserialization ---");
        try {
            // commons-collections 3.2.1 has CVE-2015-6420 (Deserialization vulnerability)
            // Code should validate input before using InvokerTransformer
            logger.info("InvokerTransformer available - requires input validation");
            
            // This would be vulnerable without proper input validation
            InvokerTransformer transformer = new InvokerTransformer("toString", null, null);
            logger.info("Transformer created: {}", transformer.getClass().getSimpleName());
        } catch (NoClassDefFoundError | Exception e) {
            logger.warn("Transformer error: {}", e.getMessage());
        }
    }
    
    private static void demonstrateRiskAcceptanceScenario() {
        logger.info("--- Scenario 6: Risk Acceptance - Platform Specific ---");
        try {
            // JNA dependency may have CVE-2023-0639 (Windows-specific vulnerability)
            // Risk can be accepted if running on non-Windows systems
            String osName = System.getProperty("os.name").toLowerCase();
            logger.info("Current OS: {}", osName);
            boolean isWindows = osName.contains("windows");
            logger.info("Is Windows: {}", isWindows);
            
            if (!isWindows) {
                logger.info("CVE-2023-0639 not applicable on this platform - risk accepted");
            } else {
                logger.warn("Running on Windows - CVE-2023-0639 may be applicable");
            }
        } catch (Exception e) {
            logger.warn("Platform detection error: {}", e.getMessage());
        }
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
