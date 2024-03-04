Feature: Finding Doctors With Data Driven Concept
  
  Scenario: Finding Doctors with Location and Doctor Spl
    Given homepage Naviagte
    When user enters location as "<location>"
    And user enters Doctor spl as "<spl>"
    And verify location if location was change enters as "<location>"
    Then user should see the search result page
  
		Examples: 
      | location  |   spl 	| 
      | Bangalore | Dentist |
      | Chennai 	| Dentist |
      | Chennai 	|	Ayurveda|