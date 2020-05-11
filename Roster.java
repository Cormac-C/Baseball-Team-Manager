/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cureton_cormac_baseballculminating;
import java.util.*;
import java.io.*;
/**
 * 
 * @author Owner
 */
public class Roster {
    private String teamName;
    private ArrayList<Player> rosterList;

    /**
     * A simple constructor which creates an empty team with a given name
     * @param teamName The String which will be this Roster's name
     */
    public Roster(String teamName) {
        this.teamName = teamName;
        rosterList = new ArrayList();
    }

    /**
     * A simple constructor which creates an team with a given name and list of Players
     * @param teamName The String which will be this Roster's name
     * @param rosterList The list of Players that make up this Roster
     */
    public Roster(String teamName, ArrayList<Player> rosterList) {
        this.teamName = teamName;
        this.rosterList = rosterList;
    }       

    /**
     * Returns a string with the team's name and each all the Player's on it formatted nicely
     * @return A String containing the team name and all the Player's that make up the roster
     */
    @Override
    public String toString() {
        String output = teamName + ":\n";
        for(Player a : rosterList){
            output += a.toString() + "\n";
        }
        return output;
    }
    
    /**
     * Takes a given Player and adds them to the rosterList of this Roster
     * @param a The Player to be added
     */
    public void addPlayer(Player a){
        rosterList.add(a);
    }
    
    /**
     * Isolates all the Batters in this rosterList into an array
     * @return An array of all the Batters in this array
     */
    public Player[] justBatters(){
        ArrayList<Player> batters = new ArrayList();
        for(Player a : rosterList){
            if(a instanceof Batter){
                batters.add(a);
            }
        }
        Player[] array = new Player[batters.size()];
        return batters.toArray(array);
    }
    
    /**
     * Isolates all the Pitchers in this rosterList into an array
     * @return An array of all the Pitchers in this array
     */
    public Player[] justPitchers(){
        ArrayList<Player> pitchers = new ArrayList();
        for(Player a : rosterList){
            if(a instanceof Pitcher){
                pitchers.add(a);
            }
        }
        Player[] array = new Player[pitchers.size()];
        return pitchers.toArray(array);
    }
    
    /**
     * Completely prints this Roster sorted alphabetically or numerically
     * @param sortType An integer of either 1 or 2. 1 indicating alphabetical sorting, 2 indicating numerical sorting
     */
    public void printSortedRoster(int sortType){//1: alphabetical, 2: numerical
        Player[] array = new Player[rosterList.size()];
        String sortName = "";
        rosterList.toArray(array);        
        if(sortType == 1){
            sortAlphabetical(array);
            sortName = "name alphabetically";
        }else if(sortType == 2){
            sortNumerical(array);
            sortName = "number";
        }else{
            System.out.println("Invalid sorting method");
        }
        System.out.println(this.teamName + " sorted by " + sortName);
        for(Player a : array){
            System.out.println(a);
        }
    }
    
    /**
     * Prints either the Batters or Pitchers of this roster sorted based on a particular stat
     * @param playerType An integer indicating what part of this Roster is being sorted and printed. 1 indicates Batters, 2 indicates Pitchers
     * @param statIndex The index of the stat that the Player's are being sorted based off in the advStats array
     */
    public void printStatSortedRoster(int playerType, int statIndex){//1: batter, 2: pitcher
        Player[] array;
        if(playerType == 1){
            array = justBatters();
        }else if (playerType == 2){
            array = justPitchers();
        }else{
            System.out.println("Invalid playerType");
            array = new Player[0];
        }
        sortStat(array, statIndex);
        //if it's a batter then the better hitters have higher stats so flip the array
        if(playerType == 1){
            flip(array);
        }       
        System.out.println(this.teamName + " sorted by " + array[0].getAdvStatNames()[statIndex]);
        for(Player a : array){
            System.out.println("" + UsefulMethods.round(a.getAdvStats()[statIndex], 3) + ": " + a);
        }
    }
    
    //Insertion sort
    /**
     * Sorts a given array of Players alphabetically using the insertion sort algorithm
     * @param array The array to be sorted
     */
    public static void sortAlphabetical(Player[] array){
        for(int i = 1; i < array.length; i ++){
            for (int j = i-1; j >= 0; j--) {
                if(array[i].compareTo(array[j]) < 0){
                    swap(array, i, j);
                    i = j;
                }else{
                    break;
                }
            }
            //System.out.println(Arrays.toString(array));
        }
    }
    
    /**
     * Sorts a given array of Players numerically based on their jersey numbers using the insertion sort algorithm
     * @param array The array to be sorted
     */
    public static void sortNumerical(Player[] array){
        for(int i = 1; i < array.length; i ++){
            for (int j = i-1; j >= 0; j--) {
                if(array[i].compareToNumber(array[j]) < 0){
                    swap(array, i, j);
                    i = j;
                }else{
                    break;
                }
            }
            //System.out.println(Arrays.toString(array));
        }
    }
    
    /**
     * Sorts a given array of one subclass of Players numerically based off a given stat using the insertion sort algorithm
     * @param array The array of Players to be sorted
     * @param statIndex THe index of the stat that the sorting will be based on
     */
    public static void sortStat(Player[] array, int statIndex){ //array must be all the same subclass
        for(int i = 1; i < array.length; i ++){
            for (int j = i-1; j >= 0; j--) {
                if(array[i].compareToStat(array[j], statIndex) < 0){
                    swap(array, i, j);
                    i = j;
                }else{
                    break;
                }
            }
            //System.out.println(Arrays.toString(array));
        }
    }
    
    /**
     * Sorts a given list of one subclass of Players numerically based off a given stat using the insertion sort algorithm
     * @param array The list of PLayers to be sorted
     * @param statIndex THe index of the stat that the sorting will be based on
     */
    public static void sortStat(ArrayList<Player> array, int statIndex){ //array must be all the same subclass
        //System.out.println(array);
        for(int i = 1; i < array.size(); i ++){
            //System.out.println(i);
            for (int j = i-1; j >= 0; j--) {
                if(array.get(i).compareToStat(array.get(j), statIndex) < 0){
                    swap(array, i, j);
                    i = j;
                }else{
                    break;
                }
            }
            //System.out.println(Arrays.toString(array));
        }
    }
    
    /**
     * Prints a batting lineup of Batters from this roster, ordered based off of their advStats
     */
    public void lineUp(){
        ArrayList<Player> availablePlayers = new ArrayList();
        availablePlayers.addAll(Arrays.asList(justBatters()));
        //System.out.println(availablePlayers);
        //1. HIghest obp
        sortStat(availablePlayers, 2);
        //System.out.println("sort stat completed");
        System.out.println("1. " + availablePlayers.get(availablePlayers.size()-1));
        availablePlayers.remove(availablePlayers.size()-1);
        //2.HIghest avg
        sortStat(availablePlayers, 0);
        System.out.println("2. " + availablePlayers.get(availablePlayers.size()-1));
        availablePlayers.remove(availablePlayers.size()-1);
        //3. HIgh OPs
        sortStat(availablePlayers, 3);
        System.out.println("3. " + availablePlayers.get(availablePlayers.size()-1));
        availablePlayers.remove(availablePlayers.size()-1);
        //4.
        sortStat(availablePlayers, 1);
        System.out.println("4. " + availablePlayers.get(availablePlayers.size()-1));
        availablePlayers.remove(availablePlayers.size()-1);
        //5-9 HIghest reamining avg
        sortStat(availablePlayers, 0);
        for(int i = 0; i < availablePlayers.size(); i ++){
            System.out.println((i + 5) + ". " +availablePlayers.get(availablePlayers.size()-1-i));
        }
    }
    
    /**
     * Prints a starting lineup of Pitchers from this roster, ordered based off of their advStats
     */
    public void rotation(){
        ArrayList<Player> availablePlayers = new ArrayList();
        availablePlayers.addAll(Arrays.asList(justPitchers()));
        //take the top 5 ip/g pitchers
        sortStat(availablePlayers, 2);
        for(int i = availablePlayers.size()-6; i >= 0 ; i --){
            availablePlayers.remove(i);
        }
        sortStat(availablePlayers, 0);
        for(int i = 0; i < availablePlayers.size(); i ++){
            System.out.println((i + 1) + ". " +availablePlayers.get(i));
        }
    }
        
    /**
     * Switches two items in an array
     * @param array The array where the two items are found
     * @param index1 The index of the first item to be switched
     * @param index2 THe index of the second item to be switched
     */
    public static void swap(Player[] array, int index1, int index2){
        if(index1 != index2){
            Player value1 = array[index1];
            array[index1] = array[index2];
            array[index2] = value1;
        }
    }
    
    /**
     * Switches two items in a list
     * @param array The list where the two items are found
     * @param index1 The index of the first item to be switched
     * @param index2 The index of the second item to be switched
     */
    public static void swap(ArrayList<Player> array, int index1, int index2){
        if(index1 != index2){
            Player value1 = array.get(index1);
            array.remove(index1);
            array.add(index1, array.get(index2));
            array.remove(index2);
            array.add(index2, value1);
        }
    }
    
    /**
     * reverses the order of items in an array
     * @param array The array to be flipped
     */
    public static void flip (Player[] array){
        for(int i = 0; i < array.length/2; i ++){
            swap(array, i, array.length-1-i);
        }
    }
    
    /**
     * Writes the team name and contents of the rosterList of this Roster into a csv file at a given address
     * @param fileAddress The address where the file should be written
     */
    public void saveRoster (String fileAddress){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileAddress));
            writer.write(teamName + "\n");
            for(Player a : rosterList){
                writer.write(a.prepForSave() + "\n");
            }
            writer.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Reads all the information for a complete Roster out of a csv file at a given address
     * @param fileAddress The address where the file should be read from
     */
    public void loadRoster(String fileAddress){
        ArrayList<String[]> file = new ArrayList();
        try{
            //BufferedReader reader = new BufferedReader(new FileReader(fileAddress));
            File f = new File(fileAddress);
            Scanner input = new Scanner(f);
            //input.nextLine();
            //System.out.println("vbdjsk");
            teamName = input.nextLine();
            while(input.hasNext()){
                String playerText = input.nextLine();
                //System.out.println(playerText);
                file.add(playerText.split(", "));
            }
            
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        System.out.println("All Players Read");
        for (int i = 0; i < file.size(); i++) {
            String[] playerInfo = file.get(i);
            if(playerInfo.length == 15){ //Batter
                int[] stats = new int[11];
                //System.out.println(Arrays.toString(playerInfo));
                for(int j = 0; j < 11; j ++){
                    stats[j] = Integer.parseInt(playerInfo[j+4]);
                }
                rosterList.add(new Batter(playerInfo[2], playerInfo[1], Integer.parseInt(playerInfo[0]), playerInfo[3], stats));
            }else if(playerInfo.length == 13){ //Pitcher
                int[] stats = new int[9];
                for(int j = 0; j < 9; j ++){
                    stats[j] = Integer.parseInt(playerInfo[j+4]);
                }
                rosterList.add(new Pitcher(playerInfo[2], playerInfo[1], Integer.parseInt(playerInfo[0]), playerInfo[3], stats));
            }
        }
    }

    /**
     * Forces all Players in this Roster to recalculate their advStats
     */
    public void calculateAllStats(){
        for(Player a : rosterList){
            a.calculateStats();
        }
    }

    /**
     * Returns the list of all the Players that make up this Roster
     * @return THe list of all the Players that make up this Roster
     */
    public ArrayList<Player> getRosterList() {
        return rosterList;
    }
}
