package com.seeding.myclip.runner;

import io.cucumber.core.cli.Main;

public class LaunchApp {
    public static void main(String[] args) {
        String[] cucumberOptions = new String[]{
                "--glue", "com.seeding.myclip.stepDefinition",
                "--plugin", "pretty",
                "--plugin", "html:target/cucumber-reports/cucumber-pretty.html",
                "--plugin", "json:target/cucumber-reports/CucumberTestReport.json",
                "--plugin", "junit:target/cucumber-reports/cucumberReport.xml",
//                        "--plugin", "timeline:target/test-output-thread/",
                "classpath:Features/Seeding.feature",
                "--tags", "@Seeding"
        };
        Main.main(cucumberOptions);
    }
}
