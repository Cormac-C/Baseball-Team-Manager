/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cureton_cormac_baseballculminating;

/**
 * A class which represents a Batter with statistics and advanced statistics to do with batting and plate appearances
 * @author Owner
 */
public class Batter extends Player{
    final String[] advStatNames = {"AVG", "SLG", "OBP", "OPS"};
    //Constructors
    //Without stats   
    /**
     * A constructor which takes a first and last name, number and position and creates a Batter with empty stats and advanced stats
     * @param firstName The first name of this Batter
     * @param lastName The last name of this Batter
     * @param number The jersey number of this Batter
     * @param position The position that this Batter plays
     */
    public Batter(String firstName, String lastName, int number, String position) {
        super(firstName, lastName, number, position);
        stats = new int[11];
        advStats = new double[4];
    }
    
    //With stats
    /**
     * A constructor which takes the first and last name, number, position, and game stats and creates a Batter with empty advanced stats
     * @param firstName The first name of this Batter
     * @param lastName The last name of this Batter
     * @param number The jersey number of this Batter
     * @param position The position that this Batter plays
     * @param stats The game stats of this Batter
     */
    public Batter(String firstName, String lastName, int number, String position, int[] stats) {
        super(firstName, lastName, number, position, stats);
        advStats = new double[4];
    }
   
    //With stats and advStats
    /**
     * A constructor which takes the first and last name, number, position, game stats, and advanced stats and and creates a full Batter
     * @param firstName The first name of this Batter
     * @param lastName The last name of this Batter
     * @param number The jersey number of this Batter
     * @param position The position that this Batter plays
     * @param stats The game stats of this Batter
     * @param advStats The advanced stats of this Batter
     */
    public Batter(String firstName, String lastName, int number, String position, int[] stats, double[] advStats) {
        super(firstName, lastName, number, position, stats, advStats);
    }
    /*
    stats = [gamesPlayed, errors, plateAppearances, singles, doubles, triples, homeRuns, walks,  sacrafices, miscReaches, miscOuts]
              0           1           2               3       4           5       6        7      8               9           10     
    advStats = [avg, slg, obp, ops]
                 0    1    2    3
    */
    
    /**
     * Prints this Batter along with all their calculated advanced stats in a readable format
     */
    public void printWithAdvStats(){
        //double[] advStats = calculateStats();
        System.out.println(this);
        System.out.println("Errors:" + stats[1]);
        System.out.println("AVG: " + UsefulMethods.round(advStats[0], 3));
        System.out.println("SLG: " + UsefulMethods.round(advStats[1], 3));
        System.out.println("OBP: " + UsefulMethods.round(advStats[2], 3));
        System.out.println("OPS: " + UsefulMethods.round(advStats[3], 3));
    }
    
    /**
     * Calculates/Updates the adv stats of this Batter based on games stats
     */
    public void calculateStats(){
        //avg
        advStats[0] = ((stats[3] + stats[4] + stats[5] + stats[6])*1.0) / (stats[2] - stats[7] - stats[8]);
        
        //slg
        advStats[1] = ((stats[3] + stats[4]*2 + stats[5]*3 + stats[6]*4)*1.0) / (stats[2] - stats[7] - stats[8]);

        //obp
        advStats[2] = (stats[3] +stats[4] +stats[5] + stats[6] +stats[7]) / (stats[2]*1.0);
        
        //ops
        advStats[3] = advStats[1] + advStats[2];

    }
    
    /**
     * Adds the events from a game to this Batter's totals in their stats array
     * @param numErrors The number of errors this Batter committed
     * @param plateAppearances The number of plate appearances this Batter had
     * @param singles The number of singles this Batter hit
     * @param doubles The number of doubles this Batter hit
     * @param triples The number of triples this Batter hit
     * @param homeRuns The number of home runs this Batter hit
     * @param walks The number of walks this Batter had
     * @param sacrafices The number of sacrifice flies this Batter had
     * @param miscReaches The number of times this Batter reached base in miscellaneous ways that are not stated above
     * @param miscOuts The number of times this Batter got out in miscellaneous ways that are not stated above
     */
    public void enterGameInfo(int numErrors, int plateAppearances, int singles, int doubles, int triples, int homeRuns, int walks, int sacrafices, int miscReaches, int miscOuts){
        stats[0] ++;
        stats[1] += numErrors;
        stats[2] += plateAppearances;
        stats[3] += singles;
        stats[4] += doubles;
        stats[5] += triples;
        stats[6] += homeRuns;
        stats[7] += walks;
        stats[8] += sacrafices;
        stats[9] += miscReaches;
        stats[10] += miscOuts;
        
        //Update advStats
        calculateStats();
    }

    /**
     * Returns the advanced names of advanced statistics which are constant for all Batters
     * @return The names of advanced statistics
     */
    public String[] getAdvStatNames() {
        return advStatNames;
    }
    
}
