# Capital.com RSI Trading Bot Example

This repository contains an example of a trading bot, demonstrating one of the ways the [Capital.com Open API](https://open-api.capital.com/) can be used. Please note that the aim of the bot is not to generate profit, but to provide a template for strategy building.

## Description

The trading bot strategy is built on the RSI indicator values. The bot opens a long position when RSI is < 30 and a short position when RSI is > 70. It also sets stop-loss and take-profit orders for every trade.

Please note that multiple entries in different directions are not supported by this bot. Therefore, if it receives a signal in the opposite direction, the existing position is closed at market price. It’s highly recommended that you use a separate trading account when testing the bot’s functionality.

## Requirements

Before you begin, ensure you have the following installed on your system:
- JDK 11 or higher
- Maven
- Docker (optional, for containerized deployment)

## Installation and Setup

1. **Clone the repository**:
   ```bash
   git clone https://github.com/capital-com-sv/api-java-samples.git
   cd api-java-samples
   ```

2. **Import the project into your IDE**:
   - Open the project in an IDE like IntelliJ IDEA or Eclipse.
   - Ensure that Maven dependencies are loaded.

3. **Configure your credentials and parameters**:
   - Open the `application.yml` file located in the `src/main/resources` directory.
   - Update the following fields as needed:

     ```yaml
     capital.user:
       login: "your_login_here"
       password: "your_password_here"
       apiKey: "your_api_key_here"
     settings:
       epics: ["NVDA", "TSLA", "GOLD", "SILVER", "NATURALGAS", "OIL_CRUDE"]
       resolutions: ["HOUR"]
       quantityPresents: 5
       quantityManual: 5
       quantityUsingManual: true
       sltpPresents: 2
       sltpPipsFromLowestPrice: 50
       sltpUsingPips: true
       RSIPeriod: 14
       finacc: "your_account_id_here"
     ```

   - **Explanation of parameters**:
     - **`login`, `password`, `apiKey`**: Your credentials for accessing the API.
     - **`epics`**: List of instruments (e.g., "NVDA", "TSLA").
     - **`resolutions`**: Timeframes for analysis (e.g., "HOUR").
     - **`quantityPresents`**: Trade size as a percentage of available account balance.
     - **`quantityManual`**: Fixed trade size.
     - **`sltpPresents`**: Stop-loss/take-profit as a percentage of account balance.
     - **`sltpPipsFromLowestPrice`**: Stop-loss/take-profit in pips.
     - **`RSIPeriod`**: Period for RSI calculation.
     - **`finacc`**: Financial account identifier.

4. **Build the project**:
   ```bash
   mvn clean install
   ```

## How to Run

1. **Run from your IDE**:
   - Locate the `Application.java` file in the `src/main/java/com/capital/api/java/samples` directory.
   - Run the `main` method.

2. **Run from the command line**:
   ```bash
   java -jar target/api-java-samples-1.0-SNAPSHOT.jar
   ```

3. **Run with Docker (optional)**:
   - Build the Docker image:
     ```bash
     docker build -t api-java-samples .
     ```
   - Run the container:
     ```bash
     docker run -e API_KEY="your_api_key_here" -e LOGIN="your_login_here" -e PASSWORD="your_password_here" api-java-samples
     ```

## Postman Collection

For additional testing and API exploration, you can use our Postman collection:
- [Direct Postman Collection](https://github.com/capital-com-sv/capital-api-postman)

## FAQ

For frequently asked questions and troubleshooting, please visit our [API FAQ](https://help.capitalccbah.com/hc/en-us/sections/9623637289746-API).

## Additional Resources

- [Capital.com Open API Documentation](https://open-api.capital.com/)
- Contact [support@capital.com](mailto:support@capital.com) for assistance.
