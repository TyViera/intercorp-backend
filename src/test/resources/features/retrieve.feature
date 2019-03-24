Feature: The app responds to any call 

Scenario: User makes call to list operation 
    Given there are 5 client records in database 
    When the user calls to the list operation 
    Then the user receives 5 client records 
    
Scenario: User makes call to create operation 
    Given there are 3 client records in database 
    When the user calls to the create operation 
    And the user calls to the list operation 
    Then the user receives 4 client records 
    
Scenario Outline: User makes call to kpid operation 
    Given there are some clients with the next ages <age_list> 
    When the user calls to the kpid operation 
    And the user calls to the list operation 
    Then the user receives the next values for <average> and <standard_deviation> 
    Examples: 
        |age_list                       |average    |standard_deviation |
        |48,47,23,27,27,33,45,55,56,42  |40.3       |12.009718          |
        |57,30,19,23,25,41,61,27,32,49  |36.4       |14.796396          |
        |50,31,18,36,50,43,43,32,54,34  |39.1       |10.989389          |
        |43,64,50,61,37,43,49,51,61,42  |50.1       |9.255029           |
        |29,61,58,22,26,57,23,62,25,29  |39.2       |17.662264          |
        |36,57,32,44,63,29,44,48,23,54  |43.0       |12.952906          |
        |29,41,52,42,22,54,48,32,19,48  |38.7       |12.499333          |
        |36,56,39,52,61,43,65,54,51,64  |52.1       |10.115445          |
        |32,25,48,30,62,20,25,45,58,24  |36.9       |15.169047          |
        |61,31,19,28,27,44,29,29,36,34  |33.8       |11.554701          |
        
        
        
        
                
