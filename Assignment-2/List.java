class List<E extends Comparable<E>> implements ListInterface<E> {
    private Node first,
                 last,
                 current;
    int numberOfElements;
    
    private class Node {
        E data;
        Node prior,
             next;
        
        public Node(E data, Node prior, Node next) {
            this.data = data;
            this.prior = prior;
            this.next = next;
        }
        
        public Node(E data) {
            this(data, null, null);
        }
    }
    
    public List () {
        first = last = current = null;
        numberOfElements = 0;
    }
    
    public boolean isEmpty () {
        return size() == 0;
    }
    
    public List<E> init () {
        first = last = current = null;
        numberOfElements = 0;
        return this;
    }
    
    public List<E> copy () {
        List<E> copyList = new List<E>();
        goToFirst();
        for (int i = 0; i < size(); i++) {
            copyList.insert(retrieve());
            goToNext();
        }
        return this;
    }
    
    public int size () {
        return numberOfElements;
    }
    
    public List<E> insert (E d) {
        if (isEmpty()) { // List is empty.
            current = new Node(d);
            first = current;
            last = current;
        }
        else if (size() == 1) { // List contains one element.
            current = first;
            if (d.compareTo(first.data) > 0) { // Element is the last.
                last = new Node(d, current, null);
                current = last.prior.next = last;
            }
            else { // Element is the first.
                first = new Node(d, null, first);
                current = first.next.prior = first;
            }
        }
        else {
            goToFirst();
            while (d.compareTo(current.data) > 0 && current.next != null) {
                goToNext();
            }
            if (current.prior == null) { // Element is the first.
                current = first = new Node(d, null, first);
                current.next.prior = current;
            }
            else if (current.prior != null && current.next != null) { // Element is in the middle.
                current = new Node(d, current.prior, current);
                current.prior.next = current;
                current.next.prior = current;
            }
            else if (current.next == null) { // Element is in the last.
                current.next = new Node(d, current, null);
                last = current = current.next;
            }
        }
        numberOfElements += 1;
        return this;
    }
    
    public E retrieve () {
        return current.data;
    }
    
    public List<E> remove () {
        if (first.next == null) { // Remove the only one:
            return init();
        }
        if (current.prior == null) { // Remove the first one.
            first.next.prior = null;
            current = first = first.next;
        }
        else if (current.prior != null && current.next != null) { // Remove element in the middle.
            Node k = current.next;
            current.prior.next = current.next;
            current.next.prior = current.prior;
            current = k;
        }
        else if (current.next == null) { // Remove the last one.
            current.prior.next = null;
            current = last = last.prior;
        }
        numberOfElements -= 1;
        return this;
    }
    
    public boolean find (E d) {
        Node k = first;
        while (k != null) {
            if (d.compareTo(k.data) == 0) {
                return true;
            }
            k = k.next;
        }
        return false;
    }
    
    public boolean goToFirst () {
        if (isEmpty()) {
            return false;
        }
        current = first;
        return true;
    }
    
    public boolean goToLast () {
        if (isEmpty()) {
            return false;
        }
        current = last;
        return true;
    }
    
    public boolean goToNext () {
        if (isEmpty() || current.next == null) {
            return false;
        }
        current = current.next;
        return true;
    }
    
    public boolean goToPrevious() {
        if (isEmpty() || current.prior == null) {
            return false;
        }
        current = current.prior;
        return true;
    }
}