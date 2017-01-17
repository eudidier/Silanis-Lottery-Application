Recruiting Test
Silanis Lottery

This is a Java software program using Spring Shell to implement a lottery.
It has 3 commands “purchase”, “draw” and “winners”.
A player can buy multiple tickets (total of 50 tickets). 
The default player is `eSigneLive` in case we don't have 50 players.

## To build and run the project ##
you need Maven 3:

1.Test the project
  `$>mvn test`

2.Build the project
  `$>mvn package`
  
3.Run the project with spring shell
  `$>java -jar target/SilanisLotteryApplication-0.0.1-SNAPSHOT.jar`

3.To exit the project
  `$>exit`

## To use the commands ##

1.Run command 'help' for assistance. 
  `$>help`

2.Run command 'purchase' with option '--name ABC' to buy a ticket as ABC.
  `$>purchase --name ABC`

3.Run the 'draw' command  to start a draw
  `$>draw`

4.Run the 'winners' command to display the name; ticket number and the price per winner
  `$>winners`
  

