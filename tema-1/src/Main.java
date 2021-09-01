import org.json.simple.JSONObject;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        PetriNetLoader petriNetLoader = new PetriNetLoader();
        JSONObject jsonObject = petriNetLoader.readJsonFile("PetriNetJSON.json");
        ArrayList<Place> placesList = petriNetLoader.readPlaces(jsonObject);
        ArrayList<Transition> transitionsList = petriNetLoader.readTransitions(jsonObject);
        Simulation simulation = new Simulation();
        simulation.runSimulation(placesList, transitionsList);
    }
}