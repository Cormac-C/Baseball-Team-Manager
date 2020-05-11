/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cureton_cormac_baseballculminating;

import java.util.*;
import java.io.*;

/**
 * The runner class, handles the menus and all facets of user interaction with the roster
 * @author Owner
 */
public class Cureton_Cormac_BaseballCulminating {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {       
        Scanner input = new Scanner(System.in);
        Roster team = new Roster("Bloor Bears");
        mainMenu(input, team);
    }
    
    /**
     * The main menu which lets the user choose what action they want to take
     * @param input The scanner through which the program gets input from the user 
     * @param team The roster that the user is interacting with
     */
    public static void mainMenu (Scanner input, Roster team){
        System.out.println("1. View a sorted roster");
        System.out.println("2. See an individual stats");
        System.out.println("3. Add a player");
        System.out.println("4. Enter a game");
        System.out.println("5. View a suggested batting lineup");
        System.out.println("6. View a suggested starting rotation");
        System.out.println("7. Load a roster");
        System.out.println("8. Save the roster");
        System.out.println("9. Quit");
        int choice = UsefulMethods.getInt(input, "What would you like to do?", 1, 9);
        switch(choice){
            case 1:
                rosterMenu(input, team);
                System.out.println("");
                mainMenu(input, team);
                break;
            case 2:
                //pick a player
                if(team.getRosterList().size() > 0){
                    for(int i = 0; i < team.getRosterList().size(); i ++){
                        System.out.println((i+1) + ". " + team.getRosterList().get(i));
                    }
                    choice = UsefulMethods.getInt(input, "Which player would you like to look at in more detail", 1, team.getRosterList().size());
                    team.getRosterList().get(choice-1).printWithAdvStats();
                }else{
                    System.out.println("Please add some players to your roster in order to view their stats");
                }
                System.out.println("");
                mainMenu(input, team);
                break;
            case 3:
                //add a player method
                addPlayer(input, team);
                System.out.println("");
                mainMenu(input, team);
                break;
            case 4:
                for(Player a : team.getRosterList()){
                    choice = UsefulMethods.getInt(input, "Did " + a.getFirstName() + " " + a.getLastName() + " participate in this game? (1 for yes, 2 for no)", 1, 2);
                    if(choice == 1){
                        enterGameForAPlayer(input, a);
                    }
                }
                team.calculateAllStats();
                System.out.println("");
                mainMenu(input, team);
                break;
            case 5:
                if(team.justBatters().length < 9){
                    System.out.println("There are not enough batters to form a batting lineup");
                }else{
                    System.out.println("Here is you suggested lineup");
                    team.lineUp();
                }
                System.out.println("");
                mainMenu(input, team);
                break;
            case 6:
                if(team.justPitchers().length < 5){
                    System.out.println("There are not enough pitchers to form a rotation");
                }else{
                    team.rotation();
                }
                System.out.println("");
                mainMenu(input, team);
                break;
            case 7:
                System.out.println("What is the filename of the roster you want to read");
                String fileLocation = input.nextLine();
                team.loadRoster(fileLocation);
                team.calculateAllStats();
                System.out.println("");
                mainMenu(input, team);
                break;
            case 8:
                System.out.println("What is the filename where you would like to save this roster");                
                fileLocation = input.nextLine();
                team.saveRoster(fileLocation);
                System.out.println("");
                mainMenu(input, team);
                break;
            case 9:
                //Let the program run through
                break;
                
        }
        
    }
    
    /**
     * Offers the user team variety of ways to see the roster of players on this team displayed
     * @param input The scanner through which the program gets input from the user 
     * @param team The roster that the user is interacting with
     */
    public static void rosterMenu(Scanner input, Roster team){
        System.out.println("1. Sort Roster by Name");
        System.out.println("2. Sort roster by Jersey Number");
        System.out.println("3. Sort roster by a Statistic");
        int choice = UsefulMethods.getInt(input, "How would you like to see the roster sorted", 1, 3);
        switch(choice){
            case 1:
                team.printSortedRoster(1);
                break;
            case 2:
                team.printSortedRoster(2);
                break;
            case 3:
                System.out.println("1. Batters");
                System.out.println("2. Pitchers");
                choice = UsefulMethods.getInt(input, "Which type of player would you like to see sorted by stat", 1, 2);
                switch(choice){
                    case 1:
                        System.out.println("1. AVG");
                        System.out.println("2. SLG");
                        System.out.println("3. OBP");
                        System.out.println("4. OPS");
                        team.printStatSortedRoster(1, UsefulMethods.getInt(input, "What stat would you like to see the batters sorted by", 1, 4)-1);
                        
                        break;
                    case 2:
                        System.out.println("1. ERA");
                        System.out.println("2. WHIP");
                        System.out.println("3. IP/G");
                        team.printStatSortedRoster(2, UsefulMethods.getInt(input, "What stat would you like to see the pitchers sorted by", 1, 3)-1);
                        break;
                }
                break;
                
        }
    }
    
    /**
     * Guides the user through entering all the relevant information for a single player, whether a batter or pitcher
     * @param input The scanner through which the program gets input from the user 
     * @param p The player who's stats are being updated
     */
    public static void enterGameForAPlayer(Scanner input, Player p){
        if (p instanceof Batter){
            int errorsComitted = UsefulMethods.getInt(input, "How many errors did " + p.getLastName() + " commit?", 0, 100);
            int plateAppearances = UsefulMethods.getInt(input, "How many plate appearances did they have", 0, 15);
            int singles = UsefulMethods.getInt(input, "How many singles did they have", 0, plateAppearances);
            int doubles = UsefulMethods.getInt(input, "How many doubles did they have", 0, plateAppearances-singles);
            int triples = UsefulMethods.getInt(input, "How many triples did they have", 0, plateAppearances-singles-doubles);
            int homeRuns = UsefulMethods.getInt(input, "How many home runs did they have", 0, plateAppearances-singles-doubles-triples);
            int walks = UsefulMethods.getInt(input, "How many walks did they have", 0, plateAppearances-singles-doubles-triples-homeRuns);
            int sacrafices = UsefulMethods.getInt(input, "How many sacrafice flies did they have", 0, plateAppearances-singles-doubles-triples-homeRuns-walks);
            int miscReaches = UsefulMethods.getInt(input, "How many other times did they reach base in other ways", 0, plateAppearances-singles-doubles-triples-homeRuns-walks-sacrafices);
            int miscOuts = plateAppearances-singles-doubles-triples-homeRuns-walks-sacrafices - miscReaches;
            ((Batter)p).enterGameInfo(errorsComitted, plateAppearances, singles, doubles, triples, homeRuns, walks, sacrafices, miscReaches, miscOuts);
            
            
        }else if (p instanceof Pitcher){
            int errorsComitted = UsefulMethods.getInt(input, "How many errors did " + p.getLastName() + " commit?", 0, 100);
            int outsGotten = UsefulMethods.getInt(input, "How many outs did they get", 0, 27);
            int battersFaced = UsefulMethods.getInt(input, "How many batters did they face:", outsGotten, 100);
            int runsAllowed = UsefulMethods.getInt(input, "How many runs did they allow", 0, 100);
            int earnedRunsAllowed = UsefulMethods.getInt(input, "How many of those runs were earned", 0, runsAllowed);
            int strikeouts = UsefulMethods.getInt(input, "How many strikeouts did they have", 0, outsGotten);
            int walks = UsefulMethods.getInt(input, "How many walks did they allow", 0, battersFaced-outsGotten);
            int hits = UsefulMethods.getInt(input, "How many hits did they allow", 0, battersFaced-outsGotten-walks);
            ((Pitcher) p).enterGameInfo(errorsComitted, outsGotten, battersFaced, runsAllowed, earnedRunsAllowed, strikeouts, walks, hits);
        }
    }
    
    /**
     * Guides the user through entering all the necessary information to add a new player to this roster
     * @param input The scanner through which the program gets input from the user 
     * @param team The roster that the user is interacting with
     */
    public static void addPlayer(Scanner input, Roster team){
        System.out.println("1. Batter");
        System.out.println("2. Pitcher");
        int choice = UsefulMethods.getInt(input, "What type of player do you want to make", 1, 2);
        System.out.println("What is your player's last name");
        String lastName = input.nextLine();
        System.out.println("What is your player's first name");
        String firstName = input.nextLine();
        int number = UsefulMethods.getInt(input, ("What number will " + firstName + " " + lastName + " have?"), 0, 99);
        System.out.println("What is your player's position");
        String position = input.nextLine();
        switch (choice){
            case 1:
                    team.getRosterList().add(new Batter(firstName, lastName, number, position));                    
                break;
            case 2:
                    team.getRosterList().add(new Pitcher(firstName, lastName, number, position));
                break;
        }
        
    }
}
