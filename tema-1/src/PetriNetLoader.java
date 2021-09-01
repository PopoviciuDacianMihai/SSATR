import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class PetriNetLoader {
    public PetriNetLoader() {
    }

    public JSONObject readJsonFile(String fileName){
        JSONParser parser = new JSONParser();
        JSONObject jsonObj = null;

        try {
            Object obj = parser.parse(new FileReader(fileName));
            jsonObj = (JSONObject) obj;
        }catch (Exception e){
            e.printStackTrace();
        }

        return jsonObj;
    }

    public ArrayList<Place> readPlaces(JSONObject jsonObj){
        ArrayList<Place> placesList = new ArrayList<Place>();
        JSONArray placesArray = (JSONArray) jsonObj.get("places");
        Iterator<JSONObject> iteratorP = placesArray.iterator();

        while (iteratorP.hasNext()){
            JSONObject place = iteratorP.next();
            String placeName = (String) place.get("placeName");
            int token = Integer.parseInt((String) place.get("token"));
            placesList.add(new Place(placeName, token));
        }

        return placesList;
    }

    public ArrayList<Transition> readTransitions(JSONObject jsonObject){
        ArrayList<Transition> transitionsList = new ArrayList<Transition>();
        JSONArray transitionsArray = (JSONArray) jsonObject.get("transitions");
        Iterator<JSONObject> iteratorT = transitionsArray.iterator();
        Random r = new Random();
        int execTime;

        while (iteratorT.hasNext()) {
            JSONObject transition = iteratorT.next();
            String transitionName = (String) transition.get("transitionName");
            ArrayList<Integer> executionTimes = new ArrayList<Integer>();

            try {
                JSONArray time = (JSONArray) transition.get("executionTime");
                Iterator<String> iteratorTime = time.iterator();

                while (iteratorTime.hasNext()) {
                    Integer t = Integer.parseInt(iteratorTime.next());
                    executionTimes.add(t);
                }
            } catch (Exception e){

            }

            try {
                Integer t = Integer.parseInt((String) transition.get("executionTime"));
                executionTimes.add(t);
            } catch (Exception e){

            }

            if (1 == executionTimes.size()){
                execTime = executionTimes.get(0);
            }
            else {
                execTime = r.nextInt(executionTimes.get(1) - executionTimes.get(0)) + executionTimes.get(0);
            }

            ArrayList<String> startPlace = new ArrayList<String>();

            try {
                JSONArray startP = (JSONArray) transition.get("startPlace");
                Iterator<String> iteratorStartPlace = startP.iterator();
                while (iteratorStartPlace.hasNext()){
                    String start = (String) iteratorStartPlace.next();
                    startPlace.add(start);
                }
            } catch (Exception e){

            }

            try {
                String start = (String) transition.get("startPlace");
                startPlace.add(start);
            } catch (Exception e){

            }

            ArrayList<String> endPlace = new ArrayList<String>();

            try {
                JSONArray endP = (JSONArray) transition.get("endPlace");
                Iterator<String> iteratorEndPlace = endP.iterator();
                while (iteratorEndPlace.hasNext()){
                    String end = (String) iteratorEndPlace.next();
                    endPlace.add(end);
                }
            } catch (Exception e){

            }

            try {
                String end = (String) transition.get("endPlace");
                endPlace.add(end);
            } catch (Exception e){

            }

            transitionsList.add(new Transition(transitionName, execTime, startPlace, endPlace));
        }

        return transitionsList;
    }
}