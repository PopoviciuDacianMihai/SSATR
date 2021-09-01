import java.util.ArrayList;

public class Transition {
    public  String transitionName;
    public int executionTime;
    public ArrayList<String> startPlace;
    public ArrayList<String> endPlace;
    private int counter;

    public Transition(String transitionName, int executionTime, ArrayList<String> startPlace, ArrayList<String> endPlace) {
        this.transitionName = transitionName;
        this.executionTime = executionTime;
        this.startPlace = startPlace;
        this.endPlace = endPlace;
    }

    public boolean isExecutable(ArrayList<Place> placesList){
        boolean hasToken = true;

        for (counter = 0; counter < startPlace.size(); counter++){
            if (1 != placesList.get(Integer.parseInt(startPlace.get(counter).replaceAll("[^0-9]", ""))).token){
                hasToken = false;
                break;
            }
        }

        return hasToken;
    }

    public void execute(ArrayList<Place> placesList){
        try{
            for (counter = 0; counter < startPlace.size(); counter++){
                placesList.get(Integer.parseInt(startPlace.get(counter).replaceAll("[^0-9]", ""))).token--;
            }

            for (counter = 0; counter < endPlace.size(); counter++){
                placesList.get(Integer.parseInt(endPlace.get(counter).replaceAll("[^0-9]", ""))).token++;
            }
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
    }
}