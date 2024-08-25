import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        try{
            File inputFile = new File("C:\\Users\\Steven\\IdeaProjects\\AOCD4\\src\\AOCD4text");
            Scanner fileReader = new Scanner(inputFile);
            int sumTotalOfPoints = 0;
            String[] Inputs = new String[400]; // array used to house all inputs, will be used in order to be able to back track and look forward
            int [] repeatsPerCard = new int[207];
            int cardNumber = 0; // used to determine the values in the array that need to be assigned
            int totalAmountOfScratchCards = 0;

            for (int i = 0; i<207;i++){ // start by populating the array with 0 values
                repeatsPerCard[i] = 0;
            }



            while(fileReader.hasNextLine()) {
                    // declare all the variables to be used during each iteration of the code
                    int arrayWithNumsOnCard[] = new int[10];
                    int WinningNums[] = new int[25];
                    int ArraySelector = 0; // we will use this to switch between the array containing the nums on the card and the winning numebers
                    int posCounter1 = 0; // position counter for the first array
                    int posCounter2 = 0; // position counter for the second array
                    int similarityIndex = 0; // we will use this to compare how similar the 2 Arrays are, then determine how many points each card has



                    String data = fileReader.nextLine(); //extract the line of text containing the card input

                    System.out.println(data);
                    String[] tokens = data.split("[|:]+"); //split the string into the 3 parts, the game part, the first set of numbers and the second set of numbers
                    for (int i = 1; i < 3; i++) { // Now we will isolate the 2 strings which we are interested in, the ones containing numbers
                        String NumsExctracted = tokens[i];
                        String[] DesiredNums = NumsExctracted.split("\\s+");
                        if (i == 1) { // if it is the first string, we place these numbers into the array for the numbers on the card
                            for (String strNum : DesiredNums) {
                                if (!strNum.isEmpty()) { // Check if the string is not empty
                                    arrayWithNumsOnCard[posCounter1] = Integer.parseInt(strNum);
                                   // System.out.println("the current number being place into the card array is: " + arrayWithNumsOnCard[posCounter1]);
                                    posCounter1++;
                                }
                            }
                        }
                        if (i == 2) { // if it is the second string, we place these numbers into the array containing the winning numbers
                            for (String strNum : DesiredNums) {
                                if (!strNum.isEmpty()) { // Check if the string is not empty
                                    WinningNums[posCounter2] = Integer.parseInt(strNum);
                               //     System.out.println("the current number being place into the winning array is: " + WinningNums[posCounter2]);
                                    posCounter2++;
                                }
                            }
                        }

                    }
                    // now we will compare the two arrays that we have collected
                    for (int i = 0; i < 10; i++) {
                        for (int j = 0; j < 25; j++) {
                            if (arrayWithNumsOnCard[i] == WinningNums[j]) {
                                System.out.println(" the number is common is: " + arrayWithNumsOnCard[i]);
                                similarityIndex++;
                            }
                        }
                    }
                    System.out.println(" the similarity index between these two arrays is: " + similarityIndex);
                    // now do the calculations to figure out the points total
                for(int p = repeatsPerCard[cardNumber];(p>=0)&&cardNumber<207;p--) {
                   // System.out.println("the p value is: " + p);
                    if (similarityIndex > 0) {
                        sumTotalOfPoints = (int) (sumTotalOfPoints + Math.pow(2, similarityIndex - 1));

                        //increment scratch cards total

                        //now we will tally how many of each of the next cards we have won
                        for (int k = 1; k <= similarityIndex; k++) {
                            if ((cardNumber + k) < 207) {
                                repeatsPerCard[cardNumber + k]++; //adds the amount of repeats to the corresponding cards
                            }
                        }
                    }
                   // System.out.println("The total amount of points for all cards at the moment is: " + sumTotalOfPoints);
                    System.out.println("the card number at the moment is: " + cardNumber);
                   // System.out.println("the total amount of scratch cards is: " + totalAmountOfScratchCards);
                    System.out.println("the total amount of repeats for this card is: " +repeatsPerCard[cardNumber]);

                   // for (int i = 0; i < 100; i++) { // start by populating the array with 0 values
                     //   System.out.println("the amount of repeats for card: " + (i + 1) + " is " + repeatsPerCard[i]);
                    //}
                }
                    cardNumber++;
            }
for (int k =0;k<207;k++){
    totalAmountOfScratchCards=totalAmountOfScratchCards + repeatsPerCard[k];
}
            System.out.println("the total amount of crds r: " + totalAmountOfScratchCards+207 );

        }catch(FileNotFoundException e){
            System.out.println("There was ane error opening the desired file");
            e.printStackTrace();
        }
    }
}