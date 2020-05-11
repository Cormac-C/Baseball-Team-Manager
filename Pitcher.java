/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cureton_cormac_baseballculminating;

/**
 * A class which represents a Pitcher with statistics and advanced statistics to do with pitching and batters faced
 * @author Owner
 */
public class Pitcher extends Player{
    final String[] advStatNames = {"ERA", "WHIP", "IP/G"};
    //Constructors
    //Without stats
    /**
     * A constructor which takes a first and last name, number and position and creates a Pitcher with empty stats and advanced stats
     * @param firstName The first name of this Pitcher
     * @param lastName The last name of this Pitcher
     * @param number THe jersey number of this Pitcher
     * @param position The position that this Pitcher plays
     */
    public Pitcher(String firstName, String lastName, int number, String position) {
        super(firstName, lastName, number, position);
        stats = new int[9];
        advStats = new double[3];
    }

    //With stats
    /**
     * A constructor which takes the first and last name, number, position, and game stats and creates a Pitcher with empty advanced stats
     * @param firstName The first name of this Pitcher
     * @param lastName The last name of this Pitcher
     * @param number The jersey number of this Pitcher
     * @param position The position that this Pitcher plays
     * @param stats The game stats of this Pitcher
     */
    public Pitcher(String firstName, String lastName, int number, String position, int[] stats) {
        super(firstName, lastName, number, position, stats);
        advStats = new double[3];
    }
      
    //With stats and advStats
    /**
     * A constructor which takes the first and last name, number, position, game stats, and advanced stats and and creates a full Pitcher
     * @param firstName The first name of this Pitcher
     * @param lastName The last name of this Pitcher
     * @param number The jersey number of this Pitcher
     * @param position The position that this Pitcher plays
     * @param stats The game stats of this Pitcher
     * @param advStats The advanced stats of this Pitcher
     */
    public Pitcher(String firstName, String lastName, int number, String position, int[] stats, double[] advStats) {
        super(firstName, lastName, number, position, stats, advStats);
    }
    /*
    stats = [gamesPlayed, errors, outsGotten, battersFaced, runsAllowed, earnedRunsAllowed, strikeouts, walks, hits]
                  0         1         2               3               4           5             6       7       8                         
    advStats = [era, whip, inningsPerGame]
                  0   1       2
    */
    
    /**
     * Prints this Pitcher along with all their calculated advanced stats in a readable format
     */
    public void printWithAdvStats(){
        System.out.println(this);
        System.out.println("Errors: " + stats[1]);
        System.out.println("ERA: " + UsefulMethods.round(advStats[0], 3));
        System.out.println("WHIP: " + UsefulMethods.round(advStats[1], 3));
        System.out.println("IP/G: " + UsefulMethods.round(advStats[2], 3));       
    }
    
    /**
     * Calculates/Updates the adv stats of this Pitcher based on games stats
     */
    public void calculateStats(){
        //era
        advStats[0] = ((double)(stats[5]) / (stats[2]/3.0)) * 9;
        
        //whip
        advStats[1] = (double)(stats[7] + stats[8]) / (stats[2] / 3.0);
        
        //IP/g
        advStats[2] = (double)((stats[2])/3.0)/stats[0];      
    }
    
    /**
     * Add the events from a game to this Pitcher's totals in their stats arrays
     * @param errors The number of errors this Pitcher committed
     * @param outsGotten The number of outs this Pitcher got
     * @param battersFaced The number of batters this Pitcher faced
     * @param runsAllowed The number of runs this Pitcher allowed
     * @param earnedRunsAllowed The number of earned runs this Pitcher allowed
     * @param strikeouts The number of strikeouts this Pitcher got
     * @param walks The number of walks this Pitcher allowed
     * @param hits The number of hits this Pitcher allowed
     */
    public void enterGameInfo(int errors, int outsGotten, int battersFaced, int runsAllowed, int earnedRunsAllowed, int strikeouts, int walks, int hits){
        stats[0] ++;
        stats[1] += errors;
        stats[2] += outsGotten;
        stats[3] += battersFaced;
        stats[4] += runsAllowed;
        stats[5] += earnedRunsAllowed;
        stats[6] += strikeouts;
        stats[7] += walks;
        stats[8] += hits;
        
        calculateStats();
    }

    /**
     * Returns the advanced names of advanced statistics which are constant for all Pitchers
     * @return The names of advanced statistics
     */
    public String[] getAdvStatNames() {
        return advStatNames;
    }
    
    
}
