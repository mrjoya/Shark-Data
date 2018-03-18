/*
* CSD2221 - Individual Programming Assignment
*
* Author: M. Joya
* Task: 1
* 
* Shark class
 */
package Task1;

public class Shark {

    //Shark data fields
    private String commonName;
    private String latinName;
    private double maxLength;
    private double maxDepth;
    private String maxYoung;
    private int globalPresence;
    private String oceanicRegions;

    //no-arg constructor
    public Shark() {
    }

    public Shark(String[] fields) {
        this.commonName = fields[0];
        this.latinName = fields[1];
        this.maxLength = Double.parseDouble(fields[2]);
        this.maxDepth = Double.parseDouble(fields[3]);
        this.maxYoung = fields[4];
        this.globalPresence = Integer.parseInt(fields[5]);
        this.oceanicRegions = fields[6];

    }

    @Override
    public String toString() {
        return "Common Name: " + this.commonName + "\nLatin Name: " + this.latinName
                + "\nMax Length: " + this.maxLength + "\nMax Depth: " + this.maxDepth + "\nMax Young: " + this.maxYoung
                + "\nGlobal Presence: " + this.globalPresence + "\nOceanic Regions: " + this.oceanicRegions;
    }

    /* Shark object getters */
    
    //Get shark common name
    public String getCommonName() {
        return this.commonName;
    }

    //Get shark Latin name
    public String getLatinName() {
        return this.latinName;
    }

    //Get shark max length
    public double getMaxLength() {
        return this.maxLength;
    }

    //Get shark max depth
    public double getMaxDepth() {
        return this.maxDepth;
    }

    //Get shark max young
    public String getMaxYoung() {
        return this.maxYoung;
    }

    //Get shark global presence
    public int getGlobalPresence() {
        return this.globalPresence;
    }

    //Get shark oceanic regions
    public String getOceanicRegions() {
        return this.oceanicRegions;
    }

}
