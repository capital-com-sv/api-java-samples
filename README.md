# Capital.com RSI trading bot example
This repository contains an example of a trading bot, demonstrating one of the ways the [Capital.com Open API](https://open-api.capital.com/) can be used. Please note that the aim of the bot is not to generate profit, but to provide a template for strategy building.

# Description
The trading bot strategy is built on the RSI indicator values. The bot opens a long position when RSI is < 30 and a short position when RSI is > 70. It also sets stop-loss and take-profit values equidistant from the current price of the instrument.

Please note that multiple entries in different directions are not supported by this bot. Therefore, if it receives a signal in the opposite direction, the existing position is closed at market price. It’s highly recommended that you use a separate trading account when testing the bot’s functionality.

# Values to set
When you start using the bot, the following values should be set:
-  Financial account
-  Instrument(s): multiple values are allowed
-  Quantity: can be set manually or as a percentage of the available account balance
-  Stop-loss/take profit: can be set in pips OR as a percentage of the available account balance
-  Timeframes.

## How to build
```bash
git clone <URL>/api-java-samples
cd api-java-samples
mvn package
java -jar api-java-samples-0.0.1-SNAPSHOT.jar
```
or
```bash
docker build -t java-api-samples .
docker run -dp 3000:3000 java-api-samples
```
