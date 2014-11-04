// TreeDictionary.java
package dictionary;

/**
 * TreeDictionary is a Tree-Map-Dictionary with an own implementation.
 *
 * @author Philipp Schultheiss
 * @version 1.00
 * @param <K>
 * @param <V>
 * @since 2014-10-20
 */
public class TreeDictionary<K extends Comparable<? super K>, V>
        implements Dictionary<K, V> {

    /**
     * The root Entry of the Tree.
     */
    private Entry<K, V> root;

    /**
     * Private Entry-Class to save a key-value-pair.
     */
    private class Entry<K, V> {

        /**
         * the height of the current entry.
         */
        private int height;
        /**
         * The specified key.
         */
        private K key;
        /**
         * The specified value.
         */
        private V value;

        /**
         * Entry at the left.
         */
        private Entry left;
        /**
         * Entry at the right.
         */
        private Entry right;

        /**
         * Public-Constructor.
         *
         * @param key specified key.
         * @param value specified value.
         */
        public Entry(final K key, final V value) {
            this.key = key;
            this.value = value;
            this.height = 0;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * Help-Object for the rotate-Methods.
     *
     * @param <K> to save a key.
     * @param <V> to save a value.
     */
    private static class Node<K, V> {

        /**
         * saved key.
         */
        private K key;
        /**
         * saved value.
         */
        private V value;
    }

    @Override
    public final V insert(final K key, final V value) {
        V old = search(key);
        root = insertR(key, value, root);
        return old;
    }

    /**
     * Recursive help-method for insert.
     *
     * @param key specified key.
     * @param value specified value
     * @param p specified entry
     * @return the Entry.
     */
    private Entry<K, V> insertR(K key, V value, Entry<K, V> p) {
        if (p == null) {
            p = new Entry(key, value);
        } else if (key.compareTo(p.key) < 0) {
            p.left = insertR(key, value, p.left);
        } else if (key.compareTo(p.key) > 0) {
            p.right = insertR(key, value, p.right);
        } else {
            /*
             * Nothing to do here!
             * Old V value is allready saved in the normal method.
             */
        }
        p = balance(p);
        return p;
    }

    @Override
    public final V search(final K key) {
        return searchR(key, root);
    }

    /**
     * Private recursive help-method for search.
     *
     * @param key specified key.
     * @param p specified Entry
     * @return the value to the key.
     */
    private V searchR(final K key, final Entry<K, V> p) {
        if (p == null) {
            return null;
        } else if (key.compareTo(p.key) < 0) {
            return (V) searchR(key, p.left);
        } else if (key.compareTo(p.key) > 0) {
            return (V) searchR(key, p.right);
        } else {
            return p.value;
        }
    }

    @Override
    public final V remove(final K key) {
        V old = search(key);
        if (old == null) {
            return null;
        }
        root = removeR(key, root);
        return old;
    }

    /**
     * Private recurcive help-method for the remove.
     *
     * @param key specified key which should be removed.
     * @param p specified Entry.
     * @return the next Entry
     */
    private Entry<K, V> removeR(K key, Entry<K, V> p) {
        if (p == null) {
            // Nothing to do here!
        } else if (key.compareTo(p.key) < 0) {
            p.left = removeR(key, p.left);
        } else if (key.compareTo(p.key) > 0) {
            p.right = removeR(key, p.right);
        } else {
            if (p.left == null) {
                p = p.right;
            } else if (p.right == null) {
                p = p.left;
            } else {
                Node<K, V> min = new Node<K, V>();
                p.right = getLowestNodeR(p.right, min);
                p.key = min.key;
                p.value = min.value;
            }
        }
        p=balance(p);
        return p;
    }

    /**
     * Help-Method for the remove to search the lowest key in the left tree.
     *
     * @param p specified Entry
     * @param min help object to save the old values
     * @return the next Entry
     */
    private Entry<K, V> getLowestNodeR(Entry<K, V> p, Node<K, V> min) {
        assert p != null;
        if (p.left == null) {
            min.key = p.key;
            min.value = p.value;
            p = p.right;
        } else {
            p.left = getLowestNodeR(p.left, min);
        }
        p = balance(p);
        return p;
    }

    /**
     * Help-Method to balance the tree.
     *
     * @param p the Entry which should be balanced
     * @return the next Entry
     */
    private Entry<K, V> balance(Entry<K, V> p) {
        if (p == null) {
            return null;
        }
        p.height = Math.max(getHeight(p.left), getHeight(p.right)) + 1;
        if (getBalance(p) == -2) {
            if (getBalance(p.left) <= 0) {
                p = rotateRight(p);
            } else {
                p = rotateLeftRight(p);
            }
        } else if (getBalance(p) == 2) {
            if (getBalance(p.right) >= 0) {
                p = rotateLeft(p);
            } else {
                p = rotateRightLeft(p);
            }
        }
        return p;
    }

    /**
     * Help-Getter for the Height of an Entry.
     *
     * @param p specified Entry
     * @return the height as integer or -1 if p is null
     */
    private int getHeight(final Entry<K, V> p) {
        if (p == null) {
            return -1;
        } else {
            return p.height;
        }
    }

    /**
     * Help method to get the value if the entry is balanced or not.
     *
     * @param p specified Entry.
     * @return the heigth as an Integer. 0 if its a leaf.
     */
    private int getBalance(final Entry<K, V> p) {
        if (p == null) {
            return 0;
        } else {
            return getHeight(p.right) - getHeight(p.left);
        }
    }

    /**
     * Help-Method to rotate the Entry right.
     *
     * @param p specified Entry.
     * @return the new specified Entry p.
     */
    private Entry<K, V> rotateRight(final Entry<K, V> p) {
        assert p.left != null;
        Entry<K, V> q = p.left;
        p.left = q.right;
        q.right = p;
        p.height = Math.max(getHeight(p.left), getHeight(p.right)) + 1;
        q.height = Math.max(getHeight(q.left), getHeight(q.right)) + 1;
        return q;
    }

    /**
     * Help-Method to rotate the Entry left.
     *
     * @param p specified Entry
     * @return the new specified Entry p.
     */
    private Entry<K, V> rotateLeft(final Entry<K, V> p) {
        assert p.right != null;
        Entry<K, V> q = p.right;
        p.right = q.left;
        q.left = p;
        p.height = Math.max(getHeight(p.right), getHeight(p.left)) + 1;
        q.height = Math.max(getHeight(q.left), getHeight(q.right)) + 1;
        return q;
    }

    /**
     * Help-Method to rotate left first and then to rotate left.
     *
     * @param p Entry to rotate
     * @return the new specified Entry p.
     */
    private Entry<K, V> rotateLeftRight(final Entry<K, V> p) {
        assert p.left != null;
        p.left = rotateLeft(p.left);
        return rotateRight(p);
    }

    /**
     * Help-Method to rotate right first and then to rotate left.
     *
     * @param p Entry to rotate.
     * @return the new specified Entry p.
     */
    private Entry<K, V> rotateRightLeft(final Entry<K, V> p) {
        assert p.right != null;
        p.right = rotateRight(p.right);
        return rotateLeft(p);
    }

    @Override
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        return toStringR(sb, root).toString();
    }

    /**
     * Recursive Help-Method to create a String from the tree.
     *
     * @param sb StringBuilder to which the methods append.
     * @param p current Entry.
     * @return the StringBuilder
     */
    private StringBuilder toStringR(StringBuilder sb, final Entry<K, V> p) {
        sb.append(p.key).append("  ").append(p.value).append("\n");
        if (p.left != null) {
            sb = toStringR(sb, p.left);
        }
        if (p.right != null) {
            sb = toStringR(sb, p.right);
        }
        return sb;
    }
}
