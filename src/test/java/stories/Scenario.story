Narrative:
In order to get addinitional financial promotion
As a buyer
I want to get lower delivery payment or presents if my order sum is high

Scenario: If sum to pay from 10 to 1500 a delivery payment is 35
Given home page http://rozetka.com.ua/
And account with sum to pay from 10 to 1500
When user checkouts
Then the delivery sum is 35

Scenario: If sum to pay from 1500 to 20000 a delivery payment is 0
Given home page http://rozetka.com.ua/
And account with sum to pay from 1500 to 20000
When user checkouts
Then the delivery sum is 0

Scenario:  If sum to pay more or equal 20000 a present should be added to the order
Given home page http://rozetka.com.ua/
And account with sum to pay from 20000 to 30000
When user checkouts
Then a present should be added to the order

Scenario: Delivery with Мист Экспресс to Simferopol cost 50
Given home page http://rozetka.com.ua/
And account with sum to pay from 200 to 5000
When user checkouts with delivery town Simferopol and type 'Mist Express'
Then the delivery sum is 50