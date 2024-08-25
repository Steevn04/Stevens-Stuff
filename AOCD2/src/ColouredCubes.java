public class ColouredCubes {
// set the amount of cubes initially available
     int redCubes =0;
     int greenCubes =0;
     int blueCubes =0;

     // create a variable for detecting whether the game works or not (variable for game status)
    int gameStatus = 0; // 0 for a working game,1 for a false game
    public void subtractRedCubes(int amountToSubtract){
        if(amountToSubtract>redCubes){
            gameStatus=1;
            System.out.println("this game dont work!");
        }
    }

    public void lowestReqNumberOfRedCubes(int numberToBeChecked){
        if(redCubes<numberToBeChecked){
            redCubes = numberToBeChecked;
        }
    }

    public void lowestReqNumberOfGreenCubes(int numberToBeChecked){
        if(greenCubes<numberToBeChecked){
            greenCubes = numberToBeChecked;
        }
    }

    public void lowestReqNumberOfBlueCubes(int numberToBeChecked){
        if(blueCubes<numberToBeChecked){
            blueCubes = numberToBeChecked;
        }
    }

    public void subtractGreenCubes(int amountToSubtract){
        if(amountToSubtract>greenCubes){
            gameStatus=1;
            System.out.println("this game dont work!");
        }
    }

    public void subtractBlueCubes(int amountToSubtract){
        if(amountToSubtract>blueCubes){
            gameStatus=1;
            System.out.println("this game dont work!");
        }
    }



    public void resetCubes(){ // restock all the cubes once the game has been checked
         redCubes =0;
         greenCubes =0;
         blueCubes =0;
    }
}

