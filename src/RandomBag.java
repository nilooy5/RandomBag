import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomBag<Item> {

    private int N;
    private Node<Item> first;

    private class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    public RandomBag() {
        first = null;
        N = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return N;
    }

    public void add(Item item) {
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        N++;
    }

    public Iterator<Item> iterator()  {
        return new ListIterator<Item>(first);
    }

    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;
        private Item[] array;
        private int count;

        public ListIterator(Node<Item> first) {
            count = N;
            current = first;
            array = (Item[]) new Object[N];

            for ( int i = 0; i < N ; i++){
                array[i] = current.item;
                current = current.next;
            }
        }

        public boolean hasNext()  { return count > 0;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();

            int random = StdRandom.uniform(count);
            Item item = array[random];
            array[random] = array[ count - 1];
            array[ count - 1] = null;

            count--;
            return item;
        }
    }

    public static void main(String[] args) {
        RandomBag<String> bag = new RandomBag<String>();
        /*while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            bag.add(item);
        }*/
        bag.add("niloy");
        bag.add("munira");
        bag.add("niloy1");
        bag.add("munira1");
        bag.add("niloy2");
        bag.add("munira2");

        StdOut.println("size of bag = " + bag.size());
        StdOut.println("- Random iteration -");
        Iterator<String> itr = bag.iterator();
        while ( itr.hasNext() ) {
            StdOut.println(itr.next());
        }
    }
}