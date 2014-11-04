/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;

import java.util.LinkedList;
import java.util.ArrayList;

/**
 *
 * @author adwenger
 * @param <K>
 * @param <V>
 */
public class HashDictionary<K, V> implements Dictionary<K, V> {

    private final int size = 160001;
    //generate LinkedList which can store Entry key and values
    private final LinkedList<Entry<K, V>> lList[] = new LinkedList[size];
    //generate ArrayList which can store K keys
    private final ArrayList<K> table = new ArrayList<>();

    private static class Entry<K, V> {

        private final K key;
        private final V value;

        public Entry(K k, V v) {
            this.key = k;
            this.value = v;
        }

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }
    }

    @Override
    public V search(K key) {
        //generate hash
        int hash = hashEval(key.hashCode() % size);
        
        if (lList[hash] != null) {
            // key[Hash] already stored, return value
            LinkedList<Entry<K, V>> list = lList[hash];
            V val = null;
            for (Entry e : list) {
                if (e.getKey().equals(key)) {
                    val = (V) e.getValue();
                }
            }
            return val;
        }
        // key not found, return null
        return null;
    }

    @Override
    public V remove(K key) {
        //generate hash
        int hash = hashEval(key.hashCode() % size);
        // key[Hash] already stored, return value
        if (lList[hash] != null) {
            for(int i = 0; i < lList[hash].size(); i++) {
                if (lList[hash].get(i).key.equals(key)) {
                    Entry<K,V> temp = lList[hash].get(i);
                    lList[hash].remove(i);
                    return temp.value;
                }
            }
        }
        // key[Hash] not found, remove null
        return null;
    }

    @Override
    public V insert(K key, V value) {
        //generate hash
        int hash = hashEval(hashEval(key.hashCode() % size));

        table.add(key);
        //no hash Entry 
        if (lList[hash] == null) {
            //Add lList Entry
            lList[hash] = new LinkedList<>();
        }
        LinkedList<Entry<K, V>> list = lList[hash];
        // in same hash index there can be a LinkedList storing multiple key/value entrys
        for (Entry e : list) {
            if (e.getKey().equals(key)) {
                list.remove(e);
                table.remove(key);
                break;
            }
        }
        list.add(new Entry(key, value));
        return value;
    }

    
    public int hashEval(int hash) {
        if(hash <0)
            return -hash;
        return hash;
    }
}
