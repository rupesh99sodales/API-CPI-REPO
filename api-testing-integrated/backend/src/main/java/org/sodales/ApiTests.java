package org.sodales;

import model.ApiTestCase;
import reader.ExcelReader;
import runner.ApiTestRunner;
import org.sodales.LogCollector;

import java.time.LocalDateTime;
import java.util.List;

public class ApiTests {

    private static String excelPath = "src/main/resources/api_test_data.xlsx";

    public static void setExcelPath(String path) {
        if (path != null && !path.isBlank()) {
            excelPath = path;
        }
    }

    public static String getExcelPath() {
        return excelPath;
    }

    public static void main(String[] args) {
        LocalDateTime testcaseexecutionstarttime = LocalDateTime.now();

        List<ApiTestCase> tests = ExcelReader.readTestCases(excelPath);

        System.out.println("Execution of the Test cases has been started..");
        System.out.println("Execution Start time: " + testcaseexecutionstarttime);
        System.out.println("Using Excel file: " + excelPath);

        for (ApiTestCase test : tests) {
            if (test.skip.equalsIgnoreCase("yes")) {
                System.out.println("skipping the test " + test.testName);
            } else {
                LogCollector.log("Currently Executing: " + test.testName);
                ApiTestRunner.run(test);
            }
        }

        LogCollector.log("Execution of the Test cases has been completed..");
        LocalDateTime testcaseexecutionstoptime = LocalDateTime.now();
       LogCollector.log("Execution Stop time: " + testcaseexecutionstoptime);
    }
}