/**
 * QuestionNode class represents a node in a binary tree about yes/no questions.
 * Each node has a left child node ("yes") and a right one ("no").
 * @author Hazel Zhou
 * @version 1.0 (12/4/23)
 */

public class QuestionNode {

    /** The data stored in the node. */
    public String data;

    /** The left child node. */
    public QuestionNode left;

    /** The right child node. */
    public QuestionNode right;

    /**
     * Constructs a QuestionNode given data and no left or right children.
     *
     * @param data the data stored in this node
     */
    public QuestionNode(String data) {
        this(data, null, null);
    }

    /**
     * Constructs a QuestionNode given data, left child, and right child.
     *
     * @param data the data stored in this node
     * @param left the left child node
     * @param right the right child node
     */
    public QuestionNode(String data, QuestionNode left, QuestionNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}
