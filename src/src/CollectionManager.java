package src;

import java.util.Scanner;
import java.util.StringTokenizer;
import src.Album.Genre;

/**
 * This class is the UI that contains the run function to interract with album collections

 * @author Divyesh Nemam Baskaran, Viraj Patel
 *
 */

public class CollectionManager {
    /**
     * Runner method for the UI of the program
     */
    public void run() {
        Scanner myObj = new Scanner(System.in);
        Collection lib = new Collection();
        boolean run = true;
        System.out.println("pack.src.Collection Manager Starts Running");
        while(run){
            String input = myObj.nextLine();
            StringTokenizer command =new StringTokenizer(input,",");
            switch(command.nextToken()) {
                case "A":
                    Album alb = new Album(command.nextToken(), command.nextToken(), getGenre(command.nextToken()), new Date(command.nextToken()), true);
                    addComand(alb,lib);
                    break;
                case "D":
                    Album alb2 = new Album(command.nextToken(), command.nextToken());
                    deleteCommand(alb2,lib);
                    break;
                case "L":
                    Album alb3 = new Album(command.nextToken(), command.nextToken());
                    lendingCommand(alb3,lib);
                    break;
                case "R":
                    Album alb4 = new Album(command.nextToken(), command.nextToken());
                    returningCommand(alb4,lib);
                    break;
                case "P":
                    lib.print();
                    break;
                case "PD":
                    lib.printByReleaseDate();
                    break;
                case "PG":
                    lib.printByGenre();
                    break;
                case "Q":
                    run = false;
                    System.out.println("pack.src.Collection Manager terminated.");
                    break;
                default:
                    System.out.println("Invalid command!");
            }
        }
    }

    /**
     * Returns back a genre for the string input
     * @param genreInput
     * @return Genre
     */
    private Album.Genre getGenre(String genreInput){
        genreInput = genreInput.toUpperCase();
        switch(genreInput) {
            case"CLASSICAL":
                return Album.Genre.CLASSICAL;
            case"COUNTRY":
                return Album.Genre.COUNTRY;
            case"JAZZ":
                return Album.Genre.JAZZ;
            case"POP":
                return Album.Genre.POP;
            default:
                return Album.Genre.UNKNOWN;
        }
    }

    /**
     *given an album and a collection input program adds album to that collection
     * @param alb an album input and lib a collection input
     */
    private void addComand(Album alb,Collection lib){
        if (lib.add(alb)) {
            System.out.println(alb.toString() + " >> added.");
        } else {
            System.out.println(alb.toString() + " >> is already in the collection.");
        }
    }

    /**
     *given an album and a collection input program deletes album to that collection
     * @param alb an album input and lib a collection input
     */
    private void deleteCommand(Album alb,Collection lib){
        if (lib.remove(alb)) {
            System.out.println(alb.getTitle()+"::"+alb.getArtist() + " >> deleted.");
        } else {
            System.out.println(alb.getTitle()+"::"+alb.getArtist() + " >> Not in List");
        }
    }

    /**
     *given an album and a collection input program changes lending status of album in that collection
     * @param alb an album input and lib a collection input
     */
    private void lendingCommand(Album alb,Collection lib){
        if (lib.lendingOut(alb)) {
            System.out.println(alb.getTitle()+"::"+alb.getArtist() +  " >> lending out and set to not available.");
        } else {
            System.out.println(alb.getTitle()+"::"+alb.getArtist() + " >> is not Avalible");
        }
    }

    /**
     *given an album and a collection input program changes return status of album in that collection
     * @param alb an album input and lib a collection input
     */
    private void returningCommand(Album alb,Collection lib){
        if (lib.returnAlbum(alb)) {
            System.out.println(alb.getTitle()+"::"+alb.getArtist() + " >> Returned and set to available.");
        } else {
            System.out.println(alb.getTitle()+"::"+alb.getArtist() + " >> Already here");
        }
    }

}
