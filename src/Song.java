public class Song {
    /**
     * This class holds the relevant fields and data for a song in the Jukebox
     * class along with getters and setters for those fields. The fields include
     * a String name, a String artists, and an int timesPlayed. The toString and
     * equals methods have been overridden.
     * @author Nick Tibbels nst2038@gmail.com
     * @author Sam Whitney shw9601@rit.edu
     * */


    private String name;
    private String artist;
    private int timesPlayed;


    public Song(String name, String artist) {
        this.name = name;
        this.artist = artist;
    }

    public String getName(){return this.name;}

    public String getArtist(){return this.artist;}

    public int getTimesPlayed(){return this.timesPlayed;}

    public void playSong(){this.timesPlayed++;}

    public String toString(){
        return "Artist: " + artist + ", Song: " + name;
    }

    public boolean equals(Song other){
        return this.getName().equals(other.getName()) && this.getArtist().equals(other.getArtist());
    }


}
