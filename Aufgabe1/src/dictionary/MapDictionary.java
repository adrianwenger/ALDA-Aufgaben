/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;

import java.util.Map;
import java.util.Set;

/**
 *
 * @author adwenger
 */
public class MapDictionary<K , V> implements Dictionary<K, V> {

    private final Map<K, V> map;

    public MapDictionary(Map<K, V> map) {
        this.map = map;
    }

    @Override
    public V insert(K key, V value) {
        if(map.put(key, value) == null)
            return value;
        return null;
    }

    @Override
    public V search(K key) {
        return map.get(key);
    }

    @Override
    public V remove(K key) {
        return map.remove(key);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        String sb1;
        Set<K> keys = map.keySet();

        for (K i : keys) {
            sb.append(i).append(" ");
            sb.append(map.get(i)).append("\n");
        }
        sb1 = sb.toString();
        return sb1;
    }
}
