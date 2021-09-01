import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Simulation {
    int timer = 0;

    public Simulation() {
    }

    public void runSimulation(ArrayList<Place> placesList, ArrayList<Transition> transitionList){
        boolean timeInc;
        boolean netChanged;
        boolean ended = false;

        try {
            FileWriter fileWriter = new FileWriter("Out.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write("T:" + 0 + " ");

            for (int counter = 0; counter < placesList.size(); counter++){
                bufferedWriter.write(placesList.get(counter).placeName + "=" + placesList.get(counter).token + " ");
            }

            bufferedWriter.newLine();

            while (!ended){
                timeInc = false;
                netChanged = false;

                for (int counter = 0; counter < transitionList.size(); counter++){
                    if(transitionList.get(counter).isExecutable(placesList) && (0 == transitionList.get(counter).executionTime)){
                        transitionList.get(counter).execute(placesList);
                        bufferedWriter.write("T:" + timer + " ");
                        for (int counter1 = 0; counter1 < placesList.size(); counter1++){
                            bufferedWriter.write(placesList.get(counter1).placeName + "=" + placesList.get(counter1).token + " ");
                        }
                        bufferedWriter.newLine();
                        netChanged = true;
                    }
                }

                for (int counter = 0; counter < transitionList.size(); counter++){
                    if(transitionList.get(counter).isExecutable(placesList) && (transitionList.get(counter).executionTime > 0)){
                        transitionList.get(counter).executionTime--;
                        timeInc = true;
                    }

                }

                if(!netChanged & timeInc){
                    bufferedWriter.write("T:" + timer + " ");
                    for (int counter1 = 0; counter1 < placesList.size(); counter1++){
                        bufferedWriter.write(placesList.get(counter1).placeName + "=" + placesList.get(counter1).token + " ");
                    }
                    bufferedWriter.newLine();
                }

                timer++;
                Thread.sleep(10);

                if(!netChanged & !timeInc){
                    ended = true;
                }
            }

            bufferedWriter.close();
        } catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
    }
}