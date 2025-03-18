import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;

public class Jukebox<Record> {

    /**
     * This class handles running a simulation of playing a random song from a
     * jukebox of some number of songs and counting how many songs must be played
     * before a duplicate song is played.
     * @author Nick Tibbels nst2038@gmail.com
     * @author Sam Whitney shw9601@rit.edu
     * */

    public static final int NO_SIMULATIONS = 50000;
    public Scanner sc;
    public ArrayList<Integer> songKeys = new ArrayList<>();

    private HashMap<Integer,Song> readIn(String filename){
        /**
         * This helper function creates a HashMap of songs given by the
         * filename. This also updates an internal list songKeys that is
         * used to randomly select a song to be played.
         * */
        HashMap<Integer,Song> songs = new HashMap<>();
        try{
            File songData = new File(filename);
            sc = new Scanner(songData);
            while(sc.hasNext()){
                String line = sc.nextLine();
                String[] temp = line.split("<SEP>", 4);
                Song song = new Song(temp[2], temp[3]);
                this.songKeys.add(song.hashCode());
                songs.put(song.hashCode(),song);
            }
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        return songs;
    }

    private Song getMostPlayedSong(HashMap<Integer,Song> songs){
        /**
         * This helper function walks over the given HashMap of songs and returns
         * the song that has the largest amount of timesPlayed.
         * */
        int most = 0;
        int mostkey = 0;
        for (int key : songKeys){
            if (songs.get(key).getTimesPlayed() >= most){
                most = songs.get(key).getTimesPlayed();
                mostkey = key;
            }
        }
        return songs.get(mostkey);
    }

    private ArrayList<String> getSongsByKey(HashMap<Integer,Song> songs, int check){
        /**
         * This song returns an alphabetized ArrayList of songs from an artist
         * associated with the given key.
         * */
        ArrayList<String> toReturn = new ArrayList<String>();
        String artistToFind = songs.get(check).getArtist();
        for (int key : songKeys){
            if (songs.get(key).getArtist().equals(artistToFind)){
                toReturn.add(songs.get(key).getName());
            }
        }
        toReturn.sort(Comparator.naturalOrder());
        return toReturn;
    }

    private void runSimulation(String filename, int seed){
        /**
         * This method runs the simulation of playing songs until a duplicate is played.
         * Takes input from a text file specified by filename and runs the random number
         * generation off of the given seed.
         * */
        //Initialization of Simulation Parameters
        HashMap<Integer, Song> records = readIn(filename);
        int counter = 0;
        Random rand = new Random(seed);
        System.out.println("Jukebox of " + records.size() + " songs starts rockin!");
        System.out.println("Printing first 5 songs played...");
        int totalSongsPlayed = 0;

        //Running the simulation
        long start = System.currentTimeMillis();
        for (int i = 1; i <= NO_SIMULATIONS; i++){
            List<Song> tracker = new ArrayList<>();
            boolean condition = true;
            while (condition){
                int index = rand.nextInt(this.songKeys.size());
                int songKey = this.songKeys.get(index);
                if (counter != 5){
                    System.out.println("    " + records.get(songKey).toString());
                    counter++;
                }
                if (tracker.contains(records.get(songKey))){
                    condition = false;
                } else {
                    records.get(songKey).playSong();
                    tracker.add(records.get(songKey));
                    totalSongsPlayed += 1;
                }
            } //end of while
        } //end of for loop
        long end = System.currentTimeMillis();
        Song mostPlayedSong = getMostPlayedSong(records);
        System.out.println("Time elapsed: " + Math.divideExact((end-start),1000) + " seconds passed");
        System.out.println("Total songs played: " + totalSongsPlayed);
        System.out.println("Average number of songs played until duplicate: " + totalSongsPlayed / NO_SIMULATIONS);
        System.out.println("Most played song: " + mostPlayedSong.toString());
        System.out.println("All songs alphabetically by " + mostPlayedSong.getArtist() + ":");
        for (String s : getSongsByKey(records, mostPlayedSong.hashCode())){
            System.out.println(s);
        }
    }

    public static void main(String[] args){
        if (args.length == 2){
            new Jukebox<>().runSimulation(args[0], Integer.parseInt(args[1]));
        } else {
            System.out.println("Usage: java Jukebox filename seed");
        }
    }
}
