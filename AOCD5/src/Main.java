import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static Long  mapFunction(Long sourceStart, Long destStart,Long range,Long inputVal){
        System.out.println("mapping function triggerd");
        Long outputVal = inputVal;
        System.out.println("the input value is: "+ inputVal);
        if((inputVal>=sourceStart)&&(inputVal<=(sourceStart+range-1))){
        outputVal = (inputVal-sourceStart)+destStart;
        }
        System.out.println("I have mapped: " + inputVal + " to the value: " + outputVal);
        return outputVal;
    }
    public static void main(String[] args) {
        try {
            File almanacFile = new File("C:\\Users\\Steven\\IdeaProjects\\AOCD5\\src\\AlmanacD5");
            Scanner fileReader = new Scanner(almanacFile);
            Long[] seedsToBePlanted = new Long[20];
            Long[] soilRequired = new Long[20];
            Long[] fertilizerRequired = new Long[20];
            Long[] waterRequired = new Long[20];
            Long[] lightRequired = new Long[20];
            Long[] temperatureRequired = new Long[20];
            Long[] humidityRequired = new Long[20];
            Long[] LocationOnMap = new Long[20];

            //declare all the variables that will remain static through-out itterations of the loop unless manually alterd
            int arrayCounter = 0;
            int posCounter = 0;
            int mapCounter = 0;
            int cycleCounter = 0;
            Long sourceVal = 0L;
            Long destVal = 0L;
            Long rangeVal = 0L;

            while (fileReader.hasNextLine()) {
                String data = fileReader.nextLine(); // get a line of data from the file
                String[] Tokens = data.split("[:\\s]+"); // breaks up the inputs based on colons and white spaces
                for (String Token : Tokens) {
                    // if token is(soil,fert,location etc.),array counter++
                    // The gearbox ;)
                    if(Token.equals("soil-to-fertilizer")){
                        arrayCounter++;
                    }

                    if(Token.equals("fertilizer-to-water")) {
                        arrayCounter++;
                    }

                    if(Token.equals("water-to-light")) {
                        arrayCounter++;
                    }

                    if(Token.equals("light-to-temperature")) {
                        arrayCounter++;
                    }

                    if(Token.equals("temperature-to-humidity")) {
                        arrayCounter++;
                    }

                    if(Token.equals("humidity-to-location")) {
                        arrayCounter++;
                    }


                    System.out.println(arrayCounter);
                    Long tempLong;
                    if ((Tokens.length == 3) && ((Token.charAt(0) <= 'a') || (Token.charAt(0) >= 'z'))) {
                        if (mapCounter == 0) {
                            sourceVal = Long.parseLong(Token);
                            System.out.println("The source value is: " + sourceVal);
                            mapCounter++;
                            System.out.println("map counter = " + mapCounter);
                        } else if (mapCounter == 1) {
                            destVal = Long.parseLong(Token);
                            System.out.println("The destination value is: " + destVal);
                            mapCounter++;
                            System.out.println("map counter = " + mapCounter);
                        } else if (mapCounter == 2) {
                            rangeVal = Long.parseLong(Token);
                            System.out.println("The range value is: " + rangeVal);
                            mapCounter++;
                            System.out.println("map counter = " + mapCounter);
                        }
                    }
                    System.out.println(Token);
                    if (Token.equals("seeds")) {
                        arrayCounter = 0;// this value is used like a gearbox to shift between the arrays
                    }

                        if (Tokens.length>3&&(arrayCounter == 0) && Token.equals("")==false&&Token.equals("seeds")==false&&((Token.charAt(0) <= 'a') || (Token.charAt(0) >= 'z'))) {
                            seedsToBePlanted[posCounter] = Long.parseLong(Token);
                            System.out.println("the seed being placed into the array at the moment is: " + seedsToBePlanted[posCounter]);
                            posCounter++;
                        }

                        if ((mapCounter == 3)) {
                            System.out.println("assigning soil to seeds now");
                            for (int i = 0; i < soilRequired.length; i++) {
                                soilRequired[i] = mapFunction(sourceVal, destVal, rangeVal, seedsToBePlanted[i]);
                                System.out.println("Just mapped, " + seedsToBePlanted[i] + " to the value " + soilRequired[i]);
                            }

                        }
                        if (arrayCounter == 1) {
                            System.out.println("assigning fertilizer to soil now");
                            for (int i = 0; i < fertilizerRequired.length; i++) {
                                fertilizerRequired[i] = mapFunction(sourceVal, destVal, rangeVal, soilRequired[i]);
                                System.out.println("Just mapped, " + soilRequired[i] + " to the value " + fertilizerRequired[i]);
                            }
                        }

                    if (arrayCounter == 2) {
                        System.out.println("assigning water to fertilizer now");
                        for (int i = 0; i < waterRequired.length; i++) {
                            waterRequired[i] = mapFunction(sourceVal, destVal, rangeVal, fertilizerRequired[i]);
                            System.out.println("Just mapped, " + fertilizerRequired[i] + " to the value " + waterRequired[i]);
                        }
                    }

                    if (arrayCounter == 3) {
                        System.out.println("assigning light to water now");
                        for (int i = 0; i < lightRequired.length; i++) {
                            lightRequired[i] = mapFunction(sourceVal, destVal, rangeVal, waterRequired[i]);
                            System.out.println("Just mapped, " + waterRequired[i] + " to the value " + lightRequired[i]);
                        }
                    }

                    if (arrayCounter == 4) {
                        System.out.println("assigning temperature to light now");
                        for (int i = 0; i < temperatureRequired.length; i++) {
                            temperatureRequired[i] = mapFunction(sourceVal, destVal, rangeVal, lightRequired[i]);
                            System.out.println("Just mapped, " + lightRequired[i] + " to the value " + temperatureRequired[i]);
                        }
                    }

                    if (arrayCounter == 5) {
                        System.out.println("assigning humidity to temperature now");
                        for (int i = 0; i < humidityRequired.length; i++) {
                            humidityRequired[i] = mapFunction(sourceVal, destVal, rangeVal, temperatureRequired[i]);
                            System.out.println("Just mapped, " + temperatureRequired[i] + " to the value " + humidityRequired[i]);
                        }
                    }

                    if (arrayCounter == 6) {
                        System.out.println("assigning locations now");
                        for (int i = 0; i < LocationOnMap.length; i++) {
                            LocationOnMap[i] = mapFunction(sourceVal, destVal, rangeVal, humidityRequired[i]);
                            System.out.println("Just mapped, " + humidityRequired[i] + " to the value " + LocationOnMap[i]);
                        }
                    }




                        System.out.println("[space]");
                    }
                mapCounter = 0;
                posCounter = 0;
                }
            for(int i =0;i< seedsToBePlanted.length;i++){
                System.out.println(" seed " + i + " is: " + seedsToBePlanted[i] );
            }
            System.out.println("\n");
            for(int i =0;i< LocationOnMap.length;i++){
                System.out.println(" Location " + i + " is: " + LocationOnMap[i] );
            }

        }catch(FileNotFoundException e){
            System.out.println("There was ane error opening the desired file");
            e.printStackTrace();
        }
    }
}