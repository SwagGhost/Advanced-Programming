public class Set<E extends Comparable<E>> implements SetInterface<E> {    
    private List<E> list;
    
    public Set () {
    	list = new List<E>();
    }
    
    public void init() {
    	List<E> emptyList = new List<E>();
    	list = emptyList;
    }
    
    public void add(E e) {
    	list.goToFirst();
    	if (! list.find(e)) {
    		list.insert(e);
    	}
    }
    
    public Set<E> union(SetInterface<E> rhs) {
        Set<E> set = (Set<E>) rhs;
        Set<E> union = new Set<E>();
        list.goToFirst();
        for (int i = 0; i < list.size(); i++) {
        	E element = list.retrieve();
        	if (! union.list.find(element)) {
        		union.list.insert(element);
        	}
        	list.goToNext();
        }
        set.list.goToFirst();
        for (int i = 0; i < set.list.size(); i++) {
        	E element = set.list.retrieve();
        	if (! union.list.find(element)) {
        		union.list.insert(element);
        	}
        	set.list.goToNext();
        }
        return union;
    }
    
    public Set<E> intersection(SetInterface<E> rhs) {
        Set<E> set = (Set<E>) rhs;
        Set<E> intersection = new Set<E>();
        if (list.size() > 0 && set.list.size() > 0) {
            list.goToFirst();
            for (int i = 0; i < list.size(); i++) {
            	E element = list.retrieve();
                if (! intersection.list.find(element) && set.list.find(element)) {
                    intersection.list.insert(list.retrieve());
                }
                list.goToNext();
            }
        }
        return intersection;
    }
    
    public Set<E> complement(SetInterface<E> rhs) {
        Set<E> set = (Set<E>) rhs;
        Set<E> complement = new Set<E>();
        list.goToFirst();
        for (int i = 0; i < list.size(); i++) {
        	if (! set.list.find(list.retrieve())) {
        		complement.list.insert(list.retrieve());
        	}
        	list.goToNext();
        }
        return complement;
    }
    
    public Set<E> symDiff(SetInterface<E> rhs) {
        return (this.union(rhs)).complement(this.intersection(rhs));
    }
    
    public Set<E> copy() {
    	Set<E> s = new Set<E>();
    	s.list = list.copy();
    	return s;
    }
    
    public void printSet() {
        if (list.size() > 0) {
            list.goToFirst();
            for (int i = 0; i < list.size(); i++) {
                System.out.printf("%s ", list.retrieve());
                list.goToNext();
            }
        }
    }
}