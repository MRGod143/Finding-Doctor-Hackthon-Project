Feature: Form Filling 
  @regression
  Scenario: Form Filling Test Case <index>    
  	When Click the Form button 
    When Enter all details "<index>"
    Then update the status in Excel data "<index>"
    
  
		Examples: 
      | index | 
      | 	1		| 
      | 	2		| 
      | 	3		| 
      | 	4		|
      | 	5		|	
      | 	6		| 
      | 	7		| 
      | 	8		| 
      | 	9		|
      | 	10	|	