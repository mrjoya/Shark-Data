/*
* CSD2221 - Individual Programming Assignment
*
* Author: M. Joya
* Task: 3

*/
package Task3;

import java.util.*;
import java.io.*;

public class Task3 {

    //Set public list variable for output of program
    public static List<String> output = new ArrayList<>();

    //Method to try reading the file and catch FileNotFoundException
    private static Scanner parseFile(File file) {

        //Initialize scanner sc
        Scanner sc = null;

        //Try to parse file into scanner or catch FileNotFoundException
        try {
            sc = new Scanner(file);

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }

        return sc;
    }

    //Method to output the program to console and results file
    public static void output(List<String> result, PrintStream ps) {

        //Print all strings after 4th string to console
        for (int i = 4; i < result.size(); i++) {
            System.out.println(result.get(i));
        }

        //Print all strings to results file
        for (int i = 0; i < result.size(); i++) {
            ps.println(result.get(i));
        }

    }

    public static void main(String[] args) {

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

        //Create scanner for user input
        Scanner in = new Scanner(System.in);

        //Initialise FileOutputStream object
        FileOutputStream resultsFile = null;

        try {
            resultsFile = new FileOutputStream("results.txt");
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }

        //Create PrintStream obj
        PrintStream ps = new PrintStream(resultsFile);

        //Output the header
        output.add("==============================================\n   "
                + "CSD2221 Individual Assignment - M. Joya\n==============================================\n\n");
        output.add(
                "                .';\n"
                + "            .-'` .'\n"
                + "          ,`.-'-.`\\\n"
                + "         ; /     '-'\n"
                + "         | \\       ,-,\n"
                + "         \\  '-.__   )_`'._\n"
                + "          '.     ```      ``'--._\n"
                + "         .-' ,      Task 3       `'-.\n"
                + "          '-'`-._           ((   o   )\n"
                + "                 `'--....(`- ,__..--'\n"
                + "                          '-'`\n\n");
        output.add("----------------------------------------------\n\nEnter search string for Latin names: > ");

        //Output to the console first 3 strings of output list
        for (int i = 0; i < 3; i++) {
            System.out.println(output.get(i));
        }

        //Set string variable for user search
        String search = in.next();

        output.add(search);

        output.add("\nYou entered string " + "\"" + search + "\"\n");

        //Envoke sharks list and search to parseSearch method
        parseSearch(sharks, search);

        //Envoke output and PrintStream to output method
        output(output, ps);

        //Close the results.txt file
        ps.close();

    }

    //Method to parse the user string search to the sharks list
    public static void parseSearch(ArrayList<Shark> sharks, String search) {

        List<String> resultSharks = new ArrayList<>();

        output.add("The following matches have been found:-\n");

        //Loop through each shark object
        for (int i = 0; i < sharks.size(); i++) {
            //Store the latin name to currentLatinName as lowercase
            String currentLatinName = sharks.get(i).getLatinName().toLowerCase();

            //Check if currentLatinName contains the search string also in lowercase
            if (currentLatinName.contains(search.toLowerCase())) {

                //Replace any occurences of the search term to uppercase
                String replacedLatinName = currentLatinName.replace(search.toLowerCase(), search.toUpperCase());

                //Return the shark name and latinName highliting in uppercase the search term 
                resultSharks.add(sharks.get(i).getCommonName() + " - Latin name: " + replacedLatinName + "\n");
            }
        }
        
        //Sort the search result sharks alphabetically
        Collections.sort(resultSharks);
        
        //Add all resultSharks to output
        output.addAll(resultSharks);   
    }
}
