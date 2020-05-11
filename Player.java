/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cureton_cormac_baseballculminating;

/**
 * An abstract class which represents a Player with a full name, number, position, stats, and advanced stats calculated from the stats
 * @author Owner
 */
public abstract class Player implements Comparable<Player>{
    String firstName;
    String lastName;
    int number;
    String position;
    int[] stats;
    double[] advStats;
    //No Stats
    /**
     * A constructor which takes a first and last name, number and position and creates a player with empty stats and advanced stats
     * @param firstName The first name of this Player
     * @param lastName The last name of this player
     * @param number The jersey number of this player
     * @param position The position that this player plays
     */
    public Player(String firstName, String lastName, int number, String position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.position = position;
        
    }
    //With Stats
    /**
     * A constructor which takes the first and last name, number, position, and game stats and creates a player with empty advanced stats
     * @param firstName The first name of this Player
     * @param lastName The last name of this Player
     * @param number The jersey number of this Player
     * @param position The position that this Player plays
     * @param stats The game stats of this Player
     */
    public Player(String firstName, String lastName, int number, String position, int[] stats) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.position = position;
        this.stats = stats;
    }
    
    //With Stats and advStats
    /**
     * A constructor which takes the first and last name, number, position, game stats, and advanced stats and and creates a full player
     * @param firstName The first name of this Player
     * @param lastName The last name of this Player
     * @param number The jersey number of this Player
     * @param position The position that this Player plays
     * @param stats The game stats of this Player
     * @param advStats The advanced stats of this Player
     */
    public Player(String firstName, String lastName, int number, String position, int[] stats, double[] advStats) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.position = position;
        this.stats = stats;
        this.advStats = advStats;
    }

    /**
     * Formats this Player's full name, number and position into a String
     * @return A String containing this Player's basic information
     */
    @Override
    public String toString() {
        return number + ", " + lastName + ", " + firstName + ", " + position;
    }
    //Compares lastname alphabetically
    /**
     * Compares this player to another based on a lexigraphical comparison of their last names
     * @param a The Player to be compared to
     * @return An integer, 0 if the two Players are equal in terms of their last names, a positive value if this player is greater in terms of their last names, and a negative value if this player is lesser in terms of their last names
     */
    @Override
    public int compareTo(Player a){
        return this.lastName.compareTo(a.getLastName());
    }
    
    /**
     * Compares this player to another based on a numerical comparison of their jersey numbers
     * @param a The Player to be compared to
     * @return An integer, 0 if the two Players have the same jersey number, a positive value if this Player has a greater jersey number, and a negative value if this Player has a lesser jersey number
     */
    public int compareToNumber(Player a){
        return this.number - a.getNumber();
    }
    
    /**
     * Compares this player to another based on a numerical comparison of a particular advanced stat
     * @param a The Player to be compared to 
     * @param statIndex The index of the stat to be used to compare within the advStats array
     * @return An integer An integer, 0 if the two Players have the same given stat, a positive value if this Player has a greater given stat, and a negative value if this Player has a lesser given stat
     */
    public double compareToStat(Player a, int statIndex){
        return (this.advStats[statIndex]) - ((a.getAdvStats())[statIndex]);
    }
    
    /**
     * Formats all of this Player's identification information and game statistics into a comma separated String
     * @return A comma separated String containing all of this Player's identification information and game statistics
     */
    public String prepForSave (){
        String output = "" + number + ", " + lastName + ", " + firstName + ", " + position;
        for (int a : stats){
            output += ", " + a;
        }
        return output;
    }
    
    /**
     * Prints this Player along with all their calculated advanced stats in a readable format
     */
    abstract void printWithAdvStats();
    
    /**
     * Calculates/Updates the adv stats of this Player based on games stats
     */
    abstract void calculateStats();  
    
    /**
     * Returns the array of names of advanced statistical categories
     * @return The array of names of advanced statistical categories
     */
    abstract String[] getAdvStatNames();

    /**
     * Returns the first name of this Player
     * @return The first name of this Player
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of this Player to a given String
     * @param firstName The new first name for this Player
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the last name of this Player
     * @return THe last name of this Player
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of this Player to a given String
     * @param lastName The new last name for this Player
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the number of this Player
     * @return The number of this Player
     */
    public int getNumber() {
        return number;
    }

    /**
     * Sets the number of this Player to a given integer
     * @param number The new number for this Player
     */
    public void setNumber(int number) {
        this.number = number;
    }
    
    /**
     * Returns the position of this Player
     * @return The position of this Player
     */
    public String getPosition() {
        return position;
    }

    /**
     * Sets the position of this PLayer to a given String
     * @param position The new position for this Player
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * Returns the array of statistics for this Player
     * @return The array of statistics for this Player
     */
    public int[] getStats() {
        return stats;
    }

    /**
     * Sets the statistics for this Player to a given array 
     * @param stats The new stats for this Player
     */
    public void setStats(int[] stats) {
        this.stats = stats;
    }

    /**
     * Returns the advanced statistics for this Player to a given Array
     * @return The array of advanced statistics for this Player
     */
    public double[] getAdvStats() {
        return advStats;
    }

    /**
     * Sets the advanced statistics for this Player to a given array
     * @param advStats The new stats for this Player
     */
    public void setAdvStats(double[] advStats) {
        this.advStats = advStats;
    }   
}
