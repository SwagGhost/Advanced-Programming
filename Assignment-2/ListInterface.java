/* elements  : objects of type E
 * structure : linear
 * domain    : The elements in the list are sorted monotonically increasing.
 *             All rows of elements of type E are valid values for a list.
 *             For every non-empty list the reference current is pointing to an
 *             element in the list.
 *
 *  There is a default constructor that returns the empty list.
 */

interface ListInterface<E extends Comparable<E>> {
    
    ListInterface<E> init ();
    /*    precondition  -
     *  postcondition - The list is empty. List-POST has been returned.
     */
    
    boolean isEmpty();
    /*    precondition  -
     *  postcondition - FALSE: The list is not empty.
     *                  TRUE:  the list is empty.
     */
    
    int size();
    /*    precondition  -
     *  postcondition - The number of elements of list has been returned.
     */
    
    ListInterface<E> insert(E d);
    /*    precondition  -
     *  postcondition - Element d has been added to List-PRE.
     *                  current points to the newly added element.
     *                  list-POST has been returned.
     */
    
    E retrieve();
    /*    precondition  - The list is not empty.
     *  postcondition - The value of the current element has been returned.
     */
    
    ListInterface<E> remove();
    /*    precondition  - The list is not empty.
     *  postcondition - The current element of list-PRE is not present in
     *                  list-POST. current-POST points to
     *                  - if list-POST is empty: null
     *                  - if list-POST is not empty and current-PRE was the
     *                    last element of list-PRE:the last element of list-POST
     *                  - otherwise: the element after current-PRE
     *                  list-POST has been returned.
     */
    
    boolean find(E d);
    /*    precondition  -
     *  postcondition - TRUE:  The list contains the element d.
     *                         current-POST points to the first element in list
     *                         that contains the element d.
     *                  FALSE: The list does not contain the element d.
     *                         current-POST points to
     *                         - if list-POST is empty: null
     *                         - if the first element in list > d: first element
     *                         - otherwise: the last element with value < d
     */
    
    boolean goToFirst();
    /*    precondition  -
     *  postcondition - FALSE: The list is empty.
     *                  TRUE:  current points to the first element.
     */
    
    boolean goToLast();
    /*    precondition  -
     *  postcondition - FALSE: The list is empty.
     *                  TRUE:  current points to the last element.
     */
    
    boolean goToNext();
    /*    precondition  -
     *  postcondition - FALSE: The list is empty or
     *                         current points to the last element.
     *                  TRUE:  current-POST points to the element after
     *                         current-PRE.
     */
    
    boolean goToPrevious();
    /*    precondition  -
     *  postcondition - FALSE: The list is empty or
     *                         current points to the first element.
     *                  TRUE:  current-POST points to the element before
     *                         current-PRE.
     */
    
    ListInterface<E> copy();
    /*    precondition  -
     *  postcondition - A copy of the list has been returned.
     */
}