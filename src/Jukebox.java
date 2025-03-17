import java.util.*;
import java.io.*;

public class Jukebox<Record> {

    public static final int NO_SIMULATIONS = 50000;
    public Random rand = new Random(0);

    public class Record<K,V>{
        private K key;
        private V value;

        public Record(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private HashSet<Record> readIn(String filename){

    }

    private void runSimulation(){

    }

    public static void main(String[] args){
        if (args.length == 2){
            File recordCase = new File(args[0]);
            new Jukebox<>().runSimulation();
        } else {
            System.out.println("Usage: java Jukebox filename seed");
        }
    }
}
