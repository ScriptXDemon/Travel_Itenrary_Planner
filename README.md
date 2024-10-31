# Travel_Itenrary_Planner

A Java Swing application for creating a travel itinerary, including destinations, travel dates, and budgets. The app integrates weather information for each destination and generates a Google Maps link, providing a comprehensive travel overview.

Features
Add Destinations: Input destination name, travel date, and budget.
Generate Itinerary: View the list of destinations, budget totals, and weather information.
Weather API Integration: Automatically fetches the weather for each destination using the OpenWeather API.
Google Maps Link: Generates a link to Google Maps for each destination.
Clear Itinerary: Reset the itinerary and budget totals.
Getting Started
Prerequisites
Java Development Kit (JDK) 8 or above
Java IDE (e.g., IntelliJ IDEA, Eclipse) or command-line interface
Installation
Clone the repository:
bash
Copy code
git clone https://github.com/yourusername/TravelPlanner.git
Open the project in your preferred IDE or navigate to the project directory in your terminal.
Setup API Key
Sign up on OpenWeather API to obtain a free API key.

Replace the apiKey variable in getWeatherInfo method with your API key.

java
Copy code
String apiKey = "your_openweather_api_key_here";
Running the Application
Compile and run the TravelPlanner class.

bash
Copy code
javac -d . -cp .:json.jar com/example/TravelPlanner.java
java -cp .:json.jar com.example.TravelPlanner
Note: Ensure json.jar is added to your classpath if using JSON parsing.

Alternatively, run the application from your IDE by right-clicking TravelPlanner and selecting "Run".

Usage
Add a Destination: Enter the destination name, travel date, and budget amount, then click "Add Destination".
Generate Itinerary: Once all destinations are added, click "Generate Itinerary" to view:
Destination details
Weather information (retrieved from the API)
Google Maps links for each destination
Clear Itinerary: Use the "Clear Itinerary" button to reset the itinerary and total budget.
Code Structure
TravelPlanner Class:

Implements GUI components, handles adding destinations, and generates itineraries.
generateItinerary(): Fetches weather information and prepares the itinerary display.
getWeatherInfo(String cityName): Calls OpenWeather API for weather data.
Helper Methods:
addDestination(): Adds a new destination.
calculateTotalBudget(): Calculates the total budget from all destinations.
Destination Class: Defines the destination object, with fields for name, date, and budget.

API Integration
The application uses the OpenWeather API to fetch weather details for each destination. You must replace apiKey with your own key to use this feature.

Screenshot

Contributing
Fork this repository.
Create a new branch (git checkout -b feature-branch).
Commit your changes (git commit -m 'Add new feature').
Push to the branch (git push origin feature-branch).
Open a Pull Request.
