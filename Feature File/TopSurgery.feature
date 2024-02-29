Feature: Get all The Top Surgery
  @sanity
  Scenario: Top Popular Surgery Test Case
  	When Click Top Surgery button
    Then Get all Top Popular after result page 
   	And Store the Data into Excel 
      
  