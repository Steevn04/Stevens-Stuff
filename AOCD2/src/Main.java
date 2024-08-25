import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static int findTheNumber(String inputString){
        int desiredNum = 0;
        for(int i = 0;i<inputString.length();i++){
            char currentChar = inputString.charAt(i);
            if (Character.isDigit(currentChar)) {
                desiredNum = desiredNum * 10 + Character.getNumericValue(currentChar);
            }
        }
        return desiredNum;
    }
    public static void main(String[] args) {

        try {
            int gameCounter = 0; //checks which game number it is
            int sumOfPossibleGames=0; // sum of all possible games
            int numOfCubes; // the number of the cubes of the specified colour
            int power;
            int sumOfPowers=0;

            ColouredCubes cubeSource = new ColouredCubes();
            File inputFile = new File("C:\\Users\\Steven\\IdeaProjects\\AOCD2\\src\\ColouredCubesdata"); // must import the file
            Scanner myReader = new Scanner(inputFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
                // Split the line based on delimiters , : ;
                String[] tokens = data.split("[:;]+");
                cubeSource.resetCubes();
                for (String token : tokens) {
                    System.out.println(token);
                    String[] segments = token.split("[,]+");
                    for (String segment : segments) {
                        System.out.println(segment);
                        if (segment.contains("Game")) {
                            gameCounter++;
                            cubeSource.resetCubes();
                            cubeSource.gameStatus=0;
                        } else if (segment.contains("red")) {
                            numOfCubes = findTheNumber(segment);
                            cubeSource.lowestReqNumberOfRedCubes(numOfCubes);
                        } else if (segment.contains("green")) {
                            numOfCubes = findTheNumber(segment);
                            cubeSource.lowestReqNumberOfGreenCubes(numOfCubes);
                        } else if (segment.contains("blue")) {
                            numOfCubes = findTheNumber(segment);
                            cubeSource.lowestReqNumberOfBlueCubes(numOfCubes);
                        }
                    }
                }
                power = cubeSource.redCubes * cubeSource.greenCubes * cubeSource.blueCubes;
                sumOfPowers= sumOfPowers + power;
                System.out.println("the lowest number of reds is, " + cubeSource.redCubes+" ,the lowest number of green cubes is, "+cubeSource.greenCubes + " ,the lowest number of blue cubes is, " + cubeSource.blueCubes);
                System.out.println( " the game number at the moment is " + gameCounter);
                System.out.println("the power is " + power);
                System.out.println("the sum of the powers at the moment is: "+ sumOfPowers);
                cubeSource.resetCubes();
                }




        } catch(FileNotFoundException e){
            System.out.println("There was ane error opening the desired file");
            e.printStackTrace();
        }
    }
}