package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("unused")
public class AppTest {
    
    private TravelPlanner travelPlanner;

    @Before
    public void setUp() {
        travelPlanner = new TravelPlanner();
    }

    @Test
    public void testAddDestination() {
        travelPlanner.addDestination("Paris", "2024-11-10", 1200.0);
        List<Destination> destinations = travelPlanner.getDestinations();
        
        // Check if the destination is added correctly
        assertEquals(1, destinations.size());
        assertEquals("Paris", destinations.get(0).name);
        assertEquals("2024-11-10", destinations.get(0).date);
        assertEquals(1200.0, destinations.get(0).budget, 0.001);
    }

    @Test
    public void testTotalBudgetCalculation() {
        travelPlanner.addDestination("Paris", "2024-11-10", 1200.0);
        travelPlanner.addDestination("Tokyo", "2024-11-15", 1500.0);

        // Check if the total budget is calculated correctly
        double totalBudget = travelPlanner.calculateTotalBudget();
        assertEquals(2700.0, totalBudget, 0.001);
    }

    @Test
    public void testGetDummyWeatherInfo() {
        String weatherParis = travelPlanner.getDummyWeatherInfo("Paris");
        String weatherTokyo = travelPlanner.getDummyWeatherInfo("Tokyo");
        
        // Verify that the dummy weather information is correct
        assertEquals("15°C, Cloudy", weatherParis);
        assertEquals("18°C, Rainy", weatherTokyo);
    }
}
