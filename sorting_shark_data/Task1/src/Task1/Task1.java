/*
* CSD2221 - Individual Programming Assignment
*
* Author: M. Joya
* Task: 1
* 
*/
package Task1;

import java.io.*;
import java.util.*;

public class Task1 {
    
    //Initialize output variable
    public static String output = "";
    
    //Method to try reading the file and catch FileNotFoundException
    private static Scanner parseFile(File file) {
        
        //Initialize scanner sc
        Scanner sc = null;
        
        //Try to parse file into scanner or catch FileNotFoundException
        try{
        sc = new Scanner(file);
   
        }catch(FileNotFoundException ex){
            System.out.println(ex);
        }
        
        //Return scanner
        return sc; 
    }
    
    //Method to output program to console and results file
    public static void output(String result, PrintStream ps){ 
        ps.println(result);
        System.out.println(result);
    }

    public static void main(String[] args){

        //Create arraylist for shark objs
        ArrayList<Shark> sharks = new ArrayList<>();

        //Create file obj
        File file = new File("shark-data.txt");

        //Create scanner assigning parseFile method return
        Scanner sc = parseFile(file);
        
        //Loop through data from file
        while (sc.hasNextLine()) {

            //Set line variable to next line
            String line = sc.nextLine();

            //Split the line to a string array
            String[] fields = line.split(":");

            //Create a shark obj passing fields array
            sharks.add(new Shark(fields));

            //Set fields array to null
            Arrays.fill(fields, null);
        }

        //Close the file
        sc.close();  
        
        //Initialise FileOutputStream object
        FileOutputStream resultsFile = null;
        
        try {
            resultsFile = new FileOutputStream("results.txt");
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
        
        //Create PrintStream obj
        PrintStream ps = new PrintStream(resultsFile);
        
        
        //Output results
        
        output += "==============================================\n   "
                + "CSD2221 Individual Assignment - M. Joya\n==============================================\n\n";
        output +=
                "                .';\n"
                + "            .-'` .'\n"
                + "          ,`.-'-.`\\\n"
                + "         ; /     '-'\n"
                + "         | \\       ,-,\n"
                + "         \\  '-.__   )_`'._\n"
                + "          '.     ```      ``'--._\n"
                + "         .-' ,      Task 1       `'-.\n"
                + "          '-'`-._           ((   o   )\n"
                + "                 `'--....(`- ,__..--'\n"
                + "                          '-'`\n\n";

        //Output the three largest sharks
        threeLrg(sharks);
        
        //Output the three smallest sharks
        threeSml(sharks);
        
        //Output the total number of Latin letters
        latinLetters(sharks);
        
        //Output the total number of unique even and odd Latin words
        latinEvenOdd(sharks);
        
        //Invoke output string and ps to output method
        output(output, ps);
        
        //Close the results.txt file
        ps.close();

    }

    //Method to output three largest sharks by length (cm)
    public static void threeLrg(List<Shark> sharks) {

        //Create lrg array
        List<Shark> lrg = new ArrayList<>();

        //Populate the lrg array with array
        lrg.addAll(sharks);

        //Create temp variable
        Shark temp;

        //Create loop to sort the array
        for (int i = 0; i < lrg.size() - 1 ; i++) {
            for (int j = i + 1; j < lrg.size(); j++) {
                if (lrg.get(i).getMaxLength() < lrg.get(j).getMaxLength()) {
                    temp = lrg.get(j);
                    lrg.set(j,lrg.get(i));
                    lrg.set(i, temp);
                }
            }
        }

        //Output the first three sharks and lengths (cm)
        output += "---------------------------------------------\n|            Three largest sharks           |\n---------------------------------------------\n|                                           |\n";
        for (int k = 0; k < 3; k++) {
            output += " > " + lrg.get(k).getCommonName() + ", Length: " + lrg.get(k).getMaxLength() + " cm \n\n";
        }
        output += "|                                           |\n---------------------------------------------\n";
        
    }

    //Method to output three smallest sharks by length (cm)
    public static void threeSml(List<Shark> sharks) {

        //Create lrg array
        List<Shark> sml = new ArrayList<>();

        //Populate the lrg array with array
        sml.addAll(sharks);

        //Create temp variable
        Shark temp;

        //Create loop to sort the array
        for (int i = 0; i < sml.size() - 1; i++) {
            for (int j = i + 1; j < sml.size(); j++) {
                if (sml.get(i).getMaxLength() > sml.get(j).getMaxLength()) {
                    //Set array[j] to temp and swap the objects
                    temp = sml.get(j);
                    sml.set(j, sml.get(i));
                    sml.set(i, temp);
                }
            }
        }

        //Output the first three sharks and lengths (cm)
        output += "---------------------------------------------\n|           Three smallest sharks           |\n---------------------------------------------\n|                                           |\n";
        for (int k = 0; k < 3; k++) {
            output += "  > " + sml.get(k).getCommonName() + ", Length: " + sml.get(k).getMaxLength() + " cm\n\n";
        }
        output += "|                                           |\n---------------------------------------------\n";

    }

    //Method to output total number of Latin letters without whitespace
    public static void latinLetters(List<Shark> sharks) {

        //Set count variable
        int count = 0;

        //Loop through each index of array
        for (int i = 0; i < sharks.size(); i++) {
            //Loop through chars of latinName of object
            for (int j = 0; j < sharks.get(i).getLatinName().length(); j++) {

                //Check if char at position j is whitespace
                if (Character.isWhitespace(sharks.get(i).getLatinName().charAt(j))) {
                    continue;
                } else {
                    count++;
                }
            }//end for loop
        }//end for loop

        //Output the total number of Latin letters without whitespace
        output += "---------------------------------------------\n| Total number of letters in                |\n| all Latin names: " + count + "                      |\n---------------------------------------------\n";

    }
    
     //Method to create a list of unique latin words
    public static List<String> latinUnique(List<Shark> sharks) {
        //Create a list to store unique latin words
        List<String> unique = new ArrayList<>();

        //Create list to store parts of latin names of sharks
        List<String> parts;

        //Populate the unique list with each part of shark latin name as 1 element of list
        for (Shark sharks1 : sharks) {
            parts = Arrays.asList(sharks1.getLatinName().split(" "));
            unique.addAll(parts);
        }

        //Create string variable to store current word
        String word;

        //Loop through unique list
        for (int j = 0; j < unique.size(); j++) {

            //Set word to latin word in position j 
            word = unique.get(j);

            //Loop through unique list
            for (int i = 0; i < unique.size(); i++) {
                //Check if curernt word is equal to word and is not the element itself and remove it
                if (word.equals(unique.get(i)) && (unique.get(i) != word)) {
                    unique.remove(i);
                }
            }//end of for loop

        }//end of for loop

        //return the unique list
        return unique;
    }

    //Method to output total number of unique Latin words with even and odd number of letters
    public static void latinEvenOdd(List<Shark> sharks) {

        //Create list containing all unique latin words
        List<String> unique = latinUnique(sharks);

        //Create counter variables
        int even = 0;
        int odd = 0;
        int count = 0;

        //Loop through each element of the unique list
        for (int i = 0; i < unique.size(); i++) {
            //Count the number of chars of each word
            for (int j = 0; j < unique.get(i).length(); j++) {
                count += 1;
            }

            //Check if count is divisible by 2 and increment even else increment odd
            if (count % 2 == 0) {
                even++;
            } else {
                odd++;
            }

            count = 0;
        }

        //Output the total number of unique even words in Latin names
        output += "---------------------------------------------\n| "
                + "Total number of unique even words         |\n| in Latin names: " + even + " "
                + "                       |\n---------------------------------------------\n";


        //Output the total number of unique odd words in Latin names
       output += "---------------------------------------------\n| "
                + "Total number of unique odd words          |\n| in Latin names: " + odd + " "
                + "                       |\n---------------------------------------------\n";

        //System.out.println(Arrays.toString(latinNames));
    }
    
}
