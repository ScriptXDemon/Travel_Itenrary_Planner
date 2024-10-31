package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

class Destination {
    String name;
    String date;
    double budget;

    Destination(String name, String date, double budget) {
        this.name = name;
        this.date = date;
        this.budget = budget;
    }

    @Override
    public String toString() {
        return "Destination: " + name + ", Date: " + date + ", Budget: $" + budget;
    }
}

public class TravelPlanner extends JFrame implements ActionListener {
    private final JTextField destinationField;
    private final JTextField dateField;
    private final JTextField budgetField;
    private final JTextArea itineraryArea;
    private final List<Destination> destinations;
    private final JLabel totalBudgetLabel;

    public TravelPlanner() {
        setTitle("Travel Itinerary Planner");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        destinations = new ArrayList<>();

        

        // Top Panel for input fields
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));

        inputPanel.add(new JLabel("Destination:"));
        destinationField = new JTextField();
        inputPanel.add(destinationField);

        inputPanel.add(new JLabel("Date (YYYY-MM-DD):"));
        dateField = new JTextField();
        inputPanel.add(dateField);

        inputPanel.add(new JLabel("Budget:"));
        budgetField = new JTextField();
        inputPanel.add(budgetField);

        JButton addButton = new JButton("Add Destination");
        addButton.addActionListener(this);
        inputPanel.add(addButton);

        totalBudgetLabel = new JLabel("Total Budget: $0.00");
        inputPanel.add(totalBudgetLabel);

        add(inputPanel, BorderLayout.NORTH);

        // Center Panel for itinerary
        itineraryArea = new JTextArea();
        itineraryArea.setEditable(false);
        add(new JScrollPane(itineraryArea), BorderLayout.CENTER);

        // Bottom Panel for buttons
        JPanel buttonPanel = new JPanel();

        JButton generateItineraryButton = new JButton("Generate Itinerary");
        generateItineraryButton.addActionListener(e -> generateItinerary());
        buttonPanel.add(generateItineraryButton);

        JButton clearButton = new JButton("Clear Itinerary");
        clearButton.addActionListener(e -> clearItinerary());
        buttonPanel.add(clearButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String destination = destinationField.getText();
        String date = dateField.getText();
        double budget;

        try {
            budget = Double.parseDouble(budgetField.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid budget amount.");
            return;
        }

        Destination dest = new Destination(destination, date, budget);
        destinations.add(dest);
        itineraryArea.append(dest + "\n");

        double totalBudget = destinations.stream().mapToDouble(d -> d.budget).sum();
        totalBudgetLabel.setText("Total Budget: $" + totalBudget);

        destinationField.setText("");
        dateField.setText("");
        budgetField.setText("");
    }

    private void generateItinerary() {
        StringBuilder itineraryDetails = new StringBuilder();
    
        for (Destination dest : destinations) {
            itineraryDetails.append("Destination: ").append(dest.name).append("\n");
            itineraryDetails.append("Date: ").append(dest.date).append("\n");
            itineraryDetails.append("Budget: $").append(dest.budget).append("\n");
    
            // Step 1: Fetch Weather Information
            String weatherInfo = getWeatherInfo(dest.name);
            itineraryDetails.append("Weather: ").append(weatherInfo).append("\n");
    
            // Step 2: Google Maps Link
            String mapLink = "https://www.google.com/maps/search/?api=1&query=" + dest.name.replace(" ", "+");
            itineraryDetails.append("Map: ").append(mapLink).append("\n\n");
        }
    
        // Show the final itinerary with weather and map links
        JOptionPane.showMessageDialog(this, itineraryDetails.toString(), "Generated Itinerary", JOptionPane.INFORMATION_MESSAGE);
    }
    
    String getWeatherInfo(String cityName) {
        String apiKey = "d0a77c2a51e38acfd49c34de6f477060";
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=" + apiKey + "&units=metric";
    
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
    
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
    
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
    
            // Parse JSON response
            JSONObject jsonResponse = new JSONObject(content.toString());
            double temperature = jsonResponse.getJSONObject("main").getDouble("temp");
            String weatherDescription = jsonResponse.getJSONArray("weather").getJSONObject(0).getString("description");
    
            return temperature + "°C, " + weatherDescription;
    
        } catch (Exception e) {
            e.printStackTrace();
            return "Weather data not available";
        }
    }

    private void clearItinerary() {
        destinations.clear();
        itineraryArea.setText("");
        totalBudgetLabel.setText("Total Budget: $0.00");
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TravelPlanner planner = new TravelPlanner();
            planner.setVisible(true);
        });
    }

    public void addDestination(String name, String date, double budget) {
        destinations.add(new Destination(name, date, budget));
    }

    // Method to get all destinations
    public List<Destination> getDestinations() {
        return destinations;
    }

    // Method to calculate total budget
    public double calculateTotalBudget() {
        return destinations.stream().mapToDouble(d -> d.budget).sum();
    }

    // Dummy weather info method
    public String getDummyWeatherInfo(String cityName) {
        switch (cityName.toLowerCase()) {
            case "paris":
                return "15°C, Cloudy";
            case "new york":
                return "22°C, Sunny";
            case "tokyo":
                return "18°C, Rainy";
            case "sydney":
                return "25°C, Clear";
            default:
                return "20°C, Partly Cloudy";
        }
    }
 }


