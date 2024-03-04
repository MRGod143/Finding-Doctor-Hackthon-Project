Feature: Finding Doctor
  @sanity
  Scenario: Finding Doctors Test Case <index>    
    When enter a location and Doctor Speacialist "<index>"
    When Enters all fliter will show result page "<index>"
    Then print top doctor List in console window
    Then store the Result Status in Excel "<index>"
  
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
      
 