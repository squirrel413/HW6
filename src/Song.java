public class Song {

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
