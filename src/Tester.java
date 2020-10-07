/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author i16006
 */
public class Tester {

    public static void main(String[] args) {
        BST<Integer> bst = new BST<Integer>();
        bst.insert(5);
        bst.insert(14);
        bst.insert(8);
        bst.insert(3);
        bst.insert(11);
        bst.insert(10);
        bst.insert(21);
        System.out.println(bst);
        System.out.println(bst.search(5));
        System.out.println(bst.search(3));
        System.out.println(bst.search(11));
        System.out.println(bst.min());
        System.out.println(bst.max());
        System.out.println(bst.predecesor(5));
    }
}

class BST<T extends Comparable<T>> {

    private Node<T> root;

    public void insert(T data) {
        Node<T> newNode = new Node<T>(data);
        Node<T> curr = this.root;
        Node<T> parent = null;

        if (this.root == null) {
            root = newNode;
        } else {
            while (curr != null) {
                parent = curr;
                if (data.compareTo(curr.info) < 0) {
                    curr = curr.left;
                } else {
                    curr = curr.right;
                }
            }
            newNode.parent = parent;
            if (data.compareTo(parent.info) < 0) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
            }
        }

    }

    public T search(T data) {
        Node<T> result = search(data, this.root);
        if (result == null) {
            return null;
        } else {
            return result.info;
        }

    }

    private Node<T> search(T data, Node<T> curr) {
        if (curr == null) {
            return null;
        } else if (curr.info.compareTo(data) == 0) {
            return curr;
        } else if (curr.info.compareTo(data) > 0) {
            return search(data, curr.left);
        } else {
            return search(data, curr.right);
        }
    }

    public T min() {
        Node<T> result = min(this.root);
        if (result == null) {
            return null;
        } else {
            return result.info;
        }
    }

    private Node<T> min(Node<T> curr) {
        Node<T> temp = curr;
        if (curr == null) {
            return null;
        } else {
            while (temp.left != null) {
                temp = temp.left;
            }
        }
        return temp;
    }

    public T max() {
        Node<T> result = max(this.root);
        if (result == null) {
            return null;
        } else {
            return result.info;
        }
    }

    private Node<T> max(Node<T> curr) {
        Node<T> temp = curr;
        if (temp == null) {
            return null;
        } else {
            while (temp.right != null) {
                temp = temp.right;
            }
        }
        return temp;
    }

    public Node<T> predecesor(Node<T> curr) {
        Node<T> temp = curr;
        if (curr == null) {
            return null;
        }
        if (curr.left != null) {
            return max(curr.left);
        } else {
            temp = curr.parent;
            while (temp != null && curr == temp.left) {
                curr = temp;
                temp = temp.parent;
            }
            return temp;
        }
        /*else if (curr.left == null) {
            if (temp.parent != null) {
                temp = curr.parent;
                if(temp.right==curr){
                    return temp;
                }
                else{
                    return null;
                }
            }
            else{
                return null;
            }
        }
        else{
            temp = curr.left;
            while(temp.right!=null){
                temp = temp.right;
            }
            return temp;
        }*/
    }

    public Node<T> succesor(Node<T> curr) {
        Node<T> temp = curr;
        if (curr == null) {
            return null;
        }
        if (curr.right != null) {
            return min(curr.right);
        } else {
            temp = curr.parent;
            while (temp != null && curr == temp.right) {
                curr = temp;
                temp = temp.parent;
            }
            return temp;
        }
    }

    public boolean delete(T data) {
        Node<T> del = search(data, this.root);
        if (del == null) {
            return false;

        } else {
            delete(del);
            return true;
        }
    }
    
    private void delete(Node<T> del){
        
    }

    @Override
    public String toString() {
        return inorder(this.root);
    }

    public String inorder(Node<T> x) {
        if (x == null) {
            return "";
        }
        if (x.left == null && x.right == null) {
            return x.toString();
        } else {
            return inorder(x.left) + x.toString() + inorder(x.right);
        }
    }

}

class Node<T extends Comparable<T>> {

    T info;
    Node<T> left, right, parent;

    Node(T info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "[" + this.info.toString() + "]";
    }
}
