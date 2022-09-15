//Ali Sbeih

/**
 * An implementation using BST of an interface representing an
 * ordered set of objects of type E, where E implements the
 * Comparable interface.
 */
public class BinarySearchTree<E extends Comparable<E>> implements SimpleSSet<E> {
    public BinarySearchTree<E> root;
    private BinarySearchTree<E> parent;
    public BinarySearchTree<E> right;
    public BinarySearchTree<E> left;
    private E value;
    public int height;

    private int size = 0;

///////////////////////////
    //delete
public int size(){
    if(root!=null)return root.numDescendants()+1;
    return 0;
}
    int i =0;
    int numDescendants(){
        if (i!=0)return i;
        if(left!=null){
            i++;
            i+=    left.numDescendants();
        }
        if(right!=null){
            i++;
            i+=right.numDescendants();
        }
        return i;
    }
    /////////////////////////////
    /**
     * Return the size of the set
     */
//    @Override
//    public int size() {
//        return size;
//    }

    /**
     * Determine if the set is empty
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Add a new element to the set in sorter order. This method first checks if
     * the set already contains an element y that is
     * equal to the element x being added. If no such
     * element is found then x is added to the set, and
     * the value true is returned. If such an element
     * y is found, then the set is not modified and the
     * method returns false.
     */
    @Override
    public boolean add(E x) {
        //start at the root
        BinarySearchTree<E> cur = root;
        //a node to be added if x is not already in the set
        BinarySearchTree<E> nd = new BinarySearchTree<>();
        //loop until x if found or a node containing x is added
        while (true) {
            if (cur != null) {
                if (cur.value.compareTo(x) == 0) return false;
                if (cur.value.compareTo(x) > 0) {
                    //move to the left child node if it exists
                    if (cur.left != null) cur = cur.left;
                        //otherwise, add it as the left child of the node we are at
                    else {
                        // make a new Node to store x
                        nd.value = x;
                        nd.parent = cur;
                        cur.left = nd;
                        nd.height = 0;
                        //update heights of all the new node's ancestors
                        updateHeights(nd);
                        break;
                    }
                }
                if (cur.value.compareTo(x) < 0) {
                    //move to the right child node if it exists
                    if (cur.right != null) cur = cur.right;
                        //otherwise, add it as the right child of the node we are at
                    else {
                        // make a new Node to store x
                        nd.value = x;
                        nd.parent = cur;
                        cur.right = nd;
                        nd.height = 0;
                        //update heights of all the new node's ancestors
                        updateHeights(nd);
                        break;
                    }
                }
            } else break;
        }

        // if we are adding when size is 0, the newly added item is
        // stored at the root
        if (isEmpty() == true) {
            nd.value = x;
            root = nd;
            nd.height = 0;
        }
        ++size;
        return true;
    }

    /**
     * Remove and return an element equal to x from the set.
     * Otherwise, the method returns the value null.
     */
    @Override
    public E remove(E x) {
        //start at the root
        BinarySearchTree<E> cur = root;
        //loop until x is found and remove it and return it or return null
        while (true) {
            if (cur != null) {
                if (cur.value.compareTo(x) == 0) {
                    //check if the node being removed is a leaf
                    if (cur.left == null & cur.right == null) {
                        //check if the leaf is a right child
                        if (cur.parent != null) {
                            if (cur.parent.right == cur) {
                                E value = cur.value;
                                cur.parent.right = null;
                                size--;
                                updateHeights(cur);
                                return value;
                            }
                            //check if the leaf is a left child
                            if (cur.parent.left == cur) {
                                E value = cur.value;
                                cur.parent.left = null;
                                size--;
                                updateHeights(cur);
                                return value;
                            }
                        }
                        //if the node is the root
                        else {
                            E value = cur.value;
                            size--;
                            return value;
                        }
                        //check if the node has a right child
                    } else if (cur.left == null) {
                        E value = cur.value;
                        if (cur.parent != null) {
                            if (cur.parent.right == cur) {
                                cur.parent.right = cur.right;
                                cur.right.parent = cur.parent;
                            }
                            if (cur.parent.left == cur) {
                                cur.parent.left = cur.right;
                                cur.right.parent = cur.parent;
                            }
                        } else {
                            root = cur.right;
                        }
                        size--;
                        updateHeights(cur);
                        return value;
                    }
                    //check if the node has a left child
                    else if (cur.right == null) {
                        E value = cur.value;
                        if (cur.parent != null) {
                            if (cur.parent.right == cur) {
                                cur.parent.right = cur.left;
                                cur.left.parent = cur.parent;
                            }
                            if (cur.parent.left == cur) {
                                cur.parent.left = cur.left;
                                cur.left.parent = cur.parent;
                            }
                        } else {
                            root = cur.left;
                        }
                        size--;
                        updateHeights(cur);
                        return value;
                    }
                    //check if the node has two children
                    else {
                        BinarySearchTree<E> nd = new BinarySearchTree();
                        //get next smaller element below the value of the node we are at
                        nd = cur.left;
                        while (nd.right != null) {
                            nd = nd.right;
                        }
                        E value = cur.value;
                        cur.value = nd.value;
                        if (nd.height == 0) {
                            if (nd.parent == cur) {
                                cur.left = null;
                            } else nd.parent.right = null;
                        } else {
                            if (nd.parent == cur) {
                                nd.parent.left = nd.left;
                            } else {
                                nd.parent.right = nd.left;
                            }
                            nd.left.parent = nd.parent;
                        }
                        size--;
                        updateHeights(cur);
                        return value;
                    }
                }
                //if x is less than the value we are at go to the left
                if (cur.value.compareTo(x) > 0) {
                    if (cur.left != null) cur = cur.left;
                    else {
                        return null;
                    }
                }
                //if x is more than the value we are at go to the right
                if (cur.value.compareTo(x) < 0) {
                    if (cur.right != null) cur = cur.right;
                    else {
                        return null;
                    }
                }
            } else break;
        }
        return null;
    }


    /**
     * Return the smallest element y in the set that
     * is larger than or equal to x in the natural
     * ordering for E, or returns null if no
     * such y is stored in the set
     */
    @Override
    public E find(E x) {
        //start at the root
        BinarySearchTree<E> cur = root;
        //loop until x is found or there are no more nodes to search
        while (true) {
            if (cur != null) {
                if (cur.value.compareTo(x) == 0) return cur.value;
                if (cur.value.compareTo(x) > 0) {
                    if (cur.left != null) cur = cur.left;
                    else {
                        break;
                    }
                }
                if (cur.value.compareTo(x) < 0) {
                    if (cur.right != null) cur = cur.right;
                    else {
                        break;
                    }
                }
            } else break;
        }
        //return element bigger than x
        if (cur.value.compareTo(x) > 0) {
            return cur.value;
        }
        if (cur.parent != null) {
            if (cur.parent.left == cur) return cur.parent.value;
            if (cur.parent.right == cur) {
                //loop until an element bigger than x is found
                while (true) {
                    if (cur.parent != null) {
                        if (cur.parent.right == cur) cur = cur.parent;
                    }
                    if (cur.parent.left == cur) return cur.parent.value;
                    else break;
                }
            }
        }

        return null;
    }

    /**
     * Find and return the smallest element in the set under the
     * natural ordering defined on E, or
     * null if the set is empty.
     */
    @Override
    public E findMin() {
        //start at the root
        BinarySearchTree<E> cur = root;
        //loop until the smallest value going left
        while (true) {
            if (cur != null) {
                if (cur.left != null) cur = cur.left;
                else {
                    return cur.value;
                }
            } else break;
        }
        return null;
    }

    /**
     * Find and return the largest element in the set under the
     * natural ordering defined on E, or
     * null if the set is empty.
     */
    @Override
    public E findMax() {
        //start at the root
        BinarySearchTree<E> cur = root;
        //loop until the biggest value going right
        while (true) {
            if (cur != null) {
                if (cur.right != null) cur = cur.right;
                else {
                    return cur.value;
                }
            } else break;
        }
        return null;
    }


    //returns the height of the BST—i.e., the length of the longest path from the root to a leaf node.
    public int height() {
        if (!isEmpty()) return root.height;
        else return -1;
    }

    public void updateHeights(BinarySearchTree<E> node) {
        //loop from the node we are at all the way to the root updating heights
        while (true) {
            if (node != null) {
                if (node == root) break;
                node = node.parent;
                if (node.right != null & node.left != null) {
                    if (node.right.height >= node.left.height) node.height = node.right.height + 1;
                    else node.height = node.left.height + 1;
                } else if (node.right != null) node.height = node.right.height + 1;
                else if (node.left != null) node.height = node.left.height + 1;
            } else break;
        }
    }

}