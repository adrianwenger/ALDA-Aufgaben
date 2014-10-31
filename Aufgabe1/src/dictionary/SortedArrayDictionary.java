/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;

/**
 *
 * @author adwenger
 * @param <K>
 * @param <V>
 */
public class SortedArrayDictionary<K extends Comparable<? super K>, V> implements Dictionary<K, V> {

    private static class Entry<K, V> {

        K key;
        V value;

        Entry(K k, V v) {
            key = k;
            value = v;
        }
    ;
    }
    private static final int DEF_CAPACITY = 16;
    private int size;
    private Entry<K, V>[] data;

    @SuppressWarnings("unchecked")
    public SortedArrayDictionary() {
        this.size = 0;
        this.data = new Entry[DEF_CAPACITY];
    }

    @SuppressWarnings("unchecked")
    private void ensureCapacity(int newCapacity) {
        if (newCapacity < size) {
            return;
        }
        Entry[] old = data;
        data = new Entry[newCapacity];
        System.arraycopy(old, 0, data, 0, size);
    }
//------------------------------------------------------------------------------

    @Override
    public V search(K key) {
        int i = searchKey(key);
        if (i >= 0) {
            return data[i].value;
        } else {
            return null;
        }
    }

    private int searchKey(K key) {
        int li = 0;
        int re = size - 1;
        while (re >= li) {
            int m = (li + re) / 2;
            if (key.compareTo(data[m].key) < 0) {
                re = m - 1;
            } else if (key.compareTo(data[m].key) > 0) {
                li = m + 1;
            } else {
                return m; // key gefunden
            }
        }
        return -1; // key nicht gefunden
    }
//----------------------------------------------------------------------------

    @Override
    public V insert(K key, V value) {
        int i = searchKey(key);
        // Vorhandener Eintrag wird überschrieben:
        if (i != -1) {
            V r = data[i].value;
            data[i].value = value;
            return null;
        }
        // Neueintrag:
        if (data.length == size) {
            ensureCapacity(2 * size);
        }
        int j = size - 1;
        while (j >= 0 && key.compareTo(data[j].key) < 0) {
            data[j + 1] = data[j];
            j--;
        }
        data[j + 1] = new Entry<>(key, value);
        size++;
        return data[j +1].value;
    }
//----------------------------------------------------------------------------

    @Override
    public V remove(K key) {
        int i = searchKey(key);
        if (i == -1) {
            return null;
        }
        // Datensatz loeschen und Lücke schließen
        V r = data[i].value;
        for (int j = i; j < size - 1; j++) {
            data[j] = data[j + 1];
        }
        data[--size] = null;
        return r;
    }
    //----------------------------------------------------------------------------

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        String sb1;

        for (int i = 0; i < size; i++) {
//            System.out.println(data[i].key + " " + data[i].value);
            sb.append(data[i].key).append(" ").append(data[i].value).append("\n");
        }
        sb1 = sb.toString();
        return sb1;
    }
}
