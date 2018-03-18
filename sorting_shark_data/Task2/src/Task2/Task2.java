/*
* CSD2221 - Individual Programming Assignment
*
* Author: M. Joya
* Task: 2
* 
*/
package Task2;

import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class Task2 {
    
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
        
        return sc; 
    }
    
    //Method to output program to console and results file
    public static void output(String result, PrintStream ps){ 
        ps.println(result);
        System.out.println(result);
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
                + "         .-' ,      Task 2       `'-.\n"
                + "          '-'`-._           ((   o   )\n"
                + "                 `'--....(`- ,__..--'\n"
                + "                          '-'`\n\n----------------------------------------------\n\n";

        //Call the listAssoc method passing sharks list
        listAssoc(sharks);
        
        //Invoke output string and ps to output method
        output(output, ps);
        
        //Close the results.txt file
        ps.close();

    }

    //Method to sort the unique oceanic regions alphabetically to a list
    public static List<String> sortRegions(List<Shark> sharks) {
        //Create regions array to store all regions
        List<String> regions = new ArrayList<>();

        //Create list to store each oceanic region of object
        List<String> parts;

        //Populate the regions list with objects oceanic regions
        for (int i = 0; i < sharks.size(); i++) {

            //Seperate each region assigned to object
            parts = Arrays.asList(sharks.get(i).getOceanicRegions().split(","));

            //Add all parts to regions list
            regions.addAll(parts);
        }

        //Remove whitespace around each region
        regions = regions.stream().map(String::trim).collect(Collectors.toList());

        //Create variable to store the current region
        String currentRegion;

        //Loop through the region list
        for (int i = 0; i < regions.size(); i++) {

            //Set the current region to region in position i
            currentRegion = regions.get(i);

            //Loop through the region list
            for (int j = 0; j < regions.size(); j++) {

                //Check if the currentRegion is equal to region j
                if (currentRegion.equals(regions.get(j)) && (regions.get(j) != currentRegion)) {
                    regions.remove(j);
                }
            }

        }

        //Sort the regions in alphabetical order
        Collections.sort(regions);

        //Return trimmedRegions list
        return regions;
    }

    //Method to print out the sharks accociated to each region
    public static void listAssoc(List<Shark> shark) {
        List<String> regions = sortRegions(shark);

        List<String> assocSharks = new ArrayList<>();
        List<String> sharkRegions = new ArrayList<>();
        List<String> parts;

        //Check if region exists in each shark object
        for (int i = 0; i < regions.size(); i++) {
            for (int j = 0; j < shark.size(); j++) {

                //Split the shark obj regions
                parts = Arrays.asList(shark.get(j).getOceanicRegions().split(","));
                parts = parts.stream().map(String::trim).collect(Collectors.toList());

                //Store parts to sharkRegions
                sharkRegions.addAll(parts);
                
                //Check if region exists in sharks obj regions
                if (sharkRegions.contains(regions.get(i))) {
                    assocSharks.add(shark.get(j).getCommonName());
                }

                //Clear the sharkRegions list
                sharkRegions.clear();

            }
            //Sort the sharks in alphabetical order
            Collections.sort(assocSharks);
            
            //Output the region and corresponding sharks
            output += regions.get(i) + " -> " + assocSharks + "\n\n";
            
            //Clear the sharks list
            assocSharks.clear();

        }

    }
}
