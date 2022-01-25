/* elements  : objects of type E
 * structure : linear (because we are using a list)
 * domain    : all rows of type E
 *  There is a default constructor that returns the empty set.
 *  SetInterface();
 */

interface SetInterface<E> {
   void init();
   /*  PRE -
       POST - The set has been initialized to the empty set.
    */
   
   void add(E e);
   /*  PRE - The element may not not already be in the set.
       POST - A new identifier has been added to the set if it did not already exist in the set.
    */
   
   SetInterface<E> union(SetInterface<E> rhs);
   /*  PRE -
       POST - The set containing the union of both sets has been returned.
    */
   
   SetInterface<E> intersection(SetInterface<E> rhs);
   /*  PRE -
       POST - The set containing the intersection of both sets has been returned.
    */
   
   SetInterface<E> complement(SetInterface<E> rhs);
   /*  PRE -
       POST - The set containing the elements from the lhs set that are not in the rhs set.
    */
   
   SetInterface<E> symDiff(SetInterface<E> rhs);
   /*  PRE -
       POST - The set containing the the symmetric difference of both sets has been returned.
    */
   

   SetInterface<E> copy();
   /*  PRE -
       POST - A copy of the set has been returned.
    */
    
    void printSet();
    /*  PRE -
        POST - If the set contains at least one element, then the elements of the set have been printed.
    */
}
