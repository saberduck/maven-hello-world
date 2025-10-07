package com.mycompany.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Example component that uses Spring Web (Transitive Dependency Scenario)
 * 
 * This demonstrates Scenario 2: Transitive Dependency Upgrade/Management
 * Spring Web 5.3.21 brings vulnerable Jackson dependencies
 */
@SuppressWarnings({"unused"}) // Suppress warnings for intentional test vulnerabilities
public class HttpClientExample {
    private static final Logger logger = LoggerFactory.getLogger(HttpClientExample.class);
    
    private final RestTemplate restTemplate;
    
    public HttpClientExample() {
        this.restTemplate = new RestTemplate();
    }
    
    /**
     * Demonstrates usage of Spring Web which has vulnerable Jackson transitive dependencies
     */
    public void demonstrateTransitiveDependencyScenario() {
        logger.info("--- Scenario 2: Transitive Dependency Management ---");
        
        try {
            // Spring Web uses Jackson for JSON processing
            // The vulnerable Jackson versions are brought in transitively
            logger.info("RestTemplate available: {}", restTemplate.getClass().getSimpleName());
            logger.info("This component uses Spring Web which has vulnerable Jackson dependencies");
            
            // In a real scenario, this might make HTTP calls that process JSON
            // using the vulnerable Jackson libraries
            String exampleJson = "{\"message\":\"This JSON would be processed by vulnerable Jackson\"}";
            logger.info("Example JSON that would use transitive Jackson: {}", exampleJson);
            
            // The vulnerability is in the transitive dependency, not direct usage
            logger.warn("Transitive Jackson vulnerability requires dependency management or Spring upgrade");
            
        } catch (Exception e) {
            logger.error("Error in transitive dependency scenario: {}", e.getMessage());
        }
    }
    
    /**
     * Simulates making HTTP requests that would use vulnerable Jackson for JSON processing
     */
    public String makeHttpRequest(String url) {
        logger.info("Simulating HTTP request to: {}", url);
        
        try {
            // This would use Jackson (transitive dependency) for JSON deserialization
            // In vulnerable version, malicious JSON could exploit deserialization flaws
            
            // For testing purposes, we'll just return a mock response
            String mockJsonResponse = "{\"status\":\"success\",\"data\":\"mock response\"}";
            logger.info("Mock JSON response (would be parsed by vulnerable Jackson): {}", mockJsonResponse);
            
            return mockJsonResponse;
            
        } catch (Exception e) {
            logger.error("HTTP request failed: {}", e.getMessage());
            return "{\"status\":\"error\",\"message\":\"" + e.getMessage() + "\"}";
        }
    }
}