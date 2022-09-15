/**
 * <p>An interface representing an ordered set of objects of type
 * <code>E</code>, where <code>E</code> implements the
 * <code>Comparable</code> interface. That is, elements stored in the
 * <code>SimpleSSet</code> can be compared to each other using the a
 * <code>compareTo</code>. This comparison induces a <em>total
 * order</em> <code>&#60;</code> on the elements stored in the set:
 * for every pair of distinct elements in the set <code>x, y</code>,
 * we have either <code>x &#60; y</code> (indicated by
 * <code>x.compareTo(y) &#60; 0</code>) or <code>y &#60; x</code>
 * (indicated by <code>x.compareTo(y) &#62; 0</code>). In addition to
 * the functionality provided by <code>SimpleUSet</code>,
 * <code>SimpleSSet</code> provides access to the smallest and largest
 * elements contained in the set according to the natural ordering on
 * <code>E</code>. Further, the <code>find</code> method differs for
 * <code>SimpleSSet</code> (see below).</p>
 *
 * @see SimpleUSet
 * @see Comparable
 * @author Will Rosenbaum
 */

public interface SimpleSSet<E extends Comparable<E>> extends SimpleUSet<E>  {
    /**
     * <p>Return the smallest element <code>y</code> in the set that
     * is larger than or equal to <code>x</code> in the natural
     * ordering for <code>E</code>, or returns <code>null</code> if no
     * such <code>y</code> is stored in the set. Note that this
     * behavior differs from the behavior of <code>find</code> for
     * <code>SimpleUSet</code>.</p>
     *
     * @param x the element to be searched for
     * @return the smallest element <code>y</code> in the set that is
     * greater than or equal to <code>x</code> in the natural ordering
     * on <code>E</code>, if such an element extists, and
     * <code>null</code> otherwise
     */
    @Override
    E find(E x);

    /**
     * <p>Find and return the smallest element in the set under the
     * natural ordering defined on <code>E</code>, or
     * <code>null</code> if the set is empty.</p>
     *
     * @return the smallest element in the set under the natural
     * ordering defined on <code>E</code>, or <code>null</code> if the
     * set is empty.
     */
    E findMin();

    /**
     * <p>Find and return the largest element in the set under the
     * natural ordering defined on <code>E</code>, or
     * <code>null</code> if the set is empty.</p>
     *
     * @return the largest element in the set under the natural
     * ordering defined on <code>E</code>, or <code>null</code> if the
     * set is empty.
     */
    E findMax();
}
