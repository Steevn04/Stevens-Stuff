import jdk.swing.interop.DropTargetContextWrapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static int numberConstructor(char charArray[][],int row,int col) {
        char numberCollector[] = {'A','A', 'A', 'A', 'A', 'A','A'};
        int startingPoint1 = 2;
        int startingPoint2 = 4;
        numberCollector[3] = charArray[row][col];
        // This will build the number from the center point backwards
        for (int i = col-1;(i >=0) && ((charArray[row][i] >= 48) && (charArray[row][i] <= 57)); i--) {
            if (startingPoint1 >= 0) {
                numberCollector[startingPoint1] = charArray[row][i];
            }
            startingPoint1--;
        }
        // This will build the number from the center point forwards
        for (int i = col+1; (i <= 140) && ((charArray[row][i] >= 48 ) && (charArray[row][i] <= 57)); i++) {
            if (startingPoint2 <= 7) {
                numberCollector[startingPoint2] = charArray[row][i];
            }
            startingPoint2++;
        }

        System.out.println("the number collected so far is: " + numberCollector[0] + numberCollector[1] + numberCollector[2] + numberCollector[3] + numberCollector[4]);
        String strContainingNumber = new String(numberCollector);
        Pattern pattern = Pattern.compile("\\d+"); // Matches one or more digits
        Matcher matcher = pattern.matcher(strContainingNumber);

        if (matcher.find()) {
            String numberStr = matcher.group(); // Extract the matched number as a string
            int numberToBeReturned = Integer.parseInt(numberStr); // Convert the string to an integer
            System.out.println("Extracted number: " + numberToBeReturned);
            return numberToBeReturned;
        }



        return 0;
    }

    public static void replaceNumsWithDots(char charArray[][],int row,int col){
        //This will replace with number in the array with dots from the center backwards
        for(int i = col; (i >=0) && ((charArray[row][i] >= '0') && (charArray[row][i] <= '9'));i--){
        charArray[row][i] = 'A';
        }

        //This will replace with number in the array with dots from the center forwards
        for(int i = col+1; (i <= 140) && ((charArray[row][i] >= '0' )&& (charArray[row][i] <= '9'));i++){
            charArray[row][i] = 'A';
        }

    }


    public static void main(String[] args) {
        try {
            // create all the variables required
            char matrixForFile[][] = new char[141][141]; // the matrix will be responsible for housing all the elements in the file
            int arrayRow = 0; // will be incremented everytime the scanner has extracts a new row
            File inputFile = new File("C:\\Users\\Steven\\IdeaProjects\\AOCD3\\src\\AOCD3data");
            Scanner myReader = new Scanner(inputFile);
            int sumOfPartNums = 0;
            int sumOfGears=0;


            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
                for (int i = 0; i<data.length(); i++) {
                    matrixForFile[arrayRow][i]=data.charAt(i);
                }
                arrayRow ++;
            }
            //once the matrix has now been filled with all the inputs of the file, we will now manipulate this matrix
            for(int i=0;i<140;i++) { // to move across the rows of the matrix
                for (int j = 0; j <140; j++) { // to move through the columns of the matrix
                   //initialise all the number variables used for the following code
                    // regulars
                    int numBelow = 0;
                    int numAbove = 0;
                    int numRight = 0;
                    int numLeft = 0;
                    //diagonal numbers
                    int numTopLeft =0;
                    int numTopRight =0;
                    int numBottomLeft =0;
                    int numBottomRight=0;
                    // counter to determine how many numbers surround a symbol
                    int counter =0;
                    int gearRat = 1;
                    System.out.println(" the row at the moment is: "+ i + " ,the column at the moment is: " +j);
                    System.out.println( matrixForFile[i][j]);


                    // We will first search for a symbol within the matrix
                    if (((33 <= matrixForFile[i][j]) && (matrixForFile[i][j] <= 47)) || ((58 <= matrixForFile[i][j]) && (matrixForFile[i][j] <= 64))) { // check if the current character is a symbol or not
                        if (matrixForFile[i][j] != 46) { // make sure the current symbol is not a full stop (a period)
                            // now we will check for whether a number occupies one of the surrounding 8 spaces (dots)
                            if ((matrixForFile[i + 1][j] >= 48) && (57 >= matrixForFile[i + 1][j])) { // checks for the value directly below the symbol
                                System.out.println("number detected below " + matrixForFile[i+1][j]);
                                numBelow = numberConstructor(matrixForFile,(i+1),j);
                               replaceNumsWithDots(matrixForFile,(i+1),j);
                               counter++;
                            }
                            if ( i>0 &&(matrixForFile[i - 1][j] >= 48) && (57 >= matrixForFile[i - 1][j])) { // checks for the value directly above the symbol
                                System.out.println("number detected above " + matrixForFile[i-1][j]);
                                numAbove = numberConstructor(matrixForFile,(i-1),j);
                                replaceNumsWithDots(matrixForFile,(i-1),j);
                                counter++;

                            }
                            if ((matrixForFile[i][j + 1] >= 48) && (57 >= matrixForFile[i][j + 1])) { //checks for the value directly to the right of the symbol
                                System.out.println("number detected right "+ matrixForFile[i][j+1]);
                                numRight = numberConstructor(matrixForFile,i,(j+1));
                                replaceNumsWithDots(matrixForFile,i,(j+1));
                                counter++;
                            }
                            if ((matrixForFile[i][j - 1] >= 48) && (57 >= matrixForFile[i][j - 1])) {// checks for the value directly to the left of the symbol
                                System.out.println("number detected left "+ matrixForFile[i][j-1]);
                                numLeft = numberConstructor(matrixForFile,i,(j-1));
                                replaceNumsWithDots(matrixForFile,i,(j-1));
                                counter++;
                            }
                            // next we will check the diagonals
                            if ((matrixForFile[i - 1][j - 1] >= 48) && (57 >= matrixForFile[i - 1][j - 1])) {// checks 1 up and left
                                System.out.println("number in top left detected " + matrixForFile[i-1][j-1]);
                                 numTopLeft = numberConstructor(matrixForFile,(i-1),(j-1));
                                replaceNumsWithDots(matrixForFile,(i-1),(j-1));
                                counter++;
                            }
                            if ((matrixForFile[i - 1][j + 1] >= 48) && (57 >= matrixForFile[i - 1][j + 1])) { // checks 1 up and right
                                System.out.println("number in top right detected " + matrixForFile[i-1][j+1]);
                                 numTopRight = numberConstructor(matrixForFile,(i-1),(j+1));
                                replaceNumsWithDots(matrixForFile,(i-1),(j+1));
                                counter++;
                            }
                            if ((matrixForFile[i + 1][j - 1] >= 48) && (57 >= matrixForFile[i + 1][j - 1])) {//checks 1 down and left
                                System.out.println("number in bottom left detected " + matrixForFile[i+1][j-1]);
                                 numBottomLeft = numberConstructor(matrixForFile,(i+1),(j-1));
                                replaceNumsWithDots(matrixForFile,(i+1),(j-1));
                                counter++;
                            }
                            if ((matrixForFile[i + 1][j + 1] >= 48) && (57 >= matrixForFile[i + 1][j + 1])) { //checks 1 down and right
                                System.out.println("number in bottom right detected " + matrixForFile[i+1][j+1]);
                                 numBottomRight = numberConstructor(matrixForFile,(i+1),(j+1));
                                replaceNumsWithDots(matrixForFile,(i+1),(j+1));
                                counter++;
                            }
                        }
                    }
                    int numArray [] = {numAbove,numBelow,numLeft,numRight,numBottomLeft,numBottomRight,numTopLeft,numTopRight};

                    if(counter == 2){
                        for(int p = 0;p < numArray.length;p++){
                            if (numArray[p] > 0){
                                gearRat=gearRat * numArray[p];
                                System.out.println("the gear ratio at the moment is: " + gearRat);
                            }
                        }
                        sumOfGears=sumOfGears + gearRat;
                    }

                    sumOfPartNums =   sumOfPartNums + numBottomLeft + numAbove + numBottomRight + numBelow + numTopLeft + numLeft + numRight + numTopRight;

                    System.out.println( "the numbers I am adding are: " + numBottomLeft +", "+ numAbove + ", "+ numBottomRight + ", "+ numBelow + ", "+ numTopLeft + ", " + numLeft + ", " + numRight + ", "+ numTopRight );
                    System.out.println("the sum of all part numbers at the moment  is: " + sumOfPartNums);
                    System.out.println("the sum of all the gear ratios at the moment is : "+ sumOfGears);
                }
            }

// make sure to check that the number formed by the value in the middle is not the same as one of the corners, we can check the size of the numbers?

            // print out the new array
         for(int i = 0; i <141; i++) {
            for(int j = 0; j < 141;j++){
                System.out.print(matrixForFile[i][j]);
            }
             System.out.println("");

         }
        }catch(FileNotFoundException e){
            System.out.println("There was ane error opening the desired file");
            e.printStackTrace();

        }
    }
}