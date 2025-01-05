# Author: Phuong Anh
# Date: 25/12/2024
# Description: Seeding tool for Myclip app

@SmokeScenario
Feature: Seeding tool for Myclip app

  @Seeding
  Scenario: Open app on device
    Given Run tool seeding
    And Close app