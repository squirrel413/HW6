import java.util.*;

public class Jukebox<E> extends HashSet<E>{

    public class Record<K,V>{
        private K key;
        private V value;

        public Record(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

}
