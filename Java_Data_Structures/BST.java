public class BST {
    public TreeNode root;
    private static final int SPACE = 10;
    BST() {
        root = null;
    }

    public boolean isEmpty() {
        if (root == null) {
            return true;
        }
        else {
            return false;
        }
    }

    public void insertNode(TreeNode new_node) {
        if (root == null) {
            root = new_node;
            System.out.println("Value inserted as root node.\n");
        }
        else {
            TreeNode temp = root;
            while (temp != null) {
                if (new_node.value == temp.value) {
                    System.out.println("Value already exists. Insert another value.\n");
                    return;
                }
                else if ((new_node.value < temp.value) && (temp.left == null)) {
                    System.out.println("Value inseerted to the left.\n");
                    break;
                }
                else if (new_node.value < temp.value) {
                    temp = temp.left;
                }
                else if ((new_node.value > temp.value) && (temp.right == null)) {
                    temp.right = new_node;
                    System.out.println("Value inserted to the right.\n");
                    break;
                }
                else {
                    temp = temp.right;
                }
            }
        }
    }

    TreeNode insertRecursie(TreeNode r, TreeNode new_node) {
        if (r == null) {
            r = new_node;
            System.out.println("Insertion successful.\n");
            return r;
        }

        if (new_node.value < r.value) {
            r.left = insertRecursie(r.left, new_node);
        }
        else if (new_node.value > r.value) {
            r.right = insertRecursie(r.right, new_node);
        }
        else {
            System.out.println("No duplicate values allowed!\n");
            return r;
        }
        return r;
    }

    public void print2D(TreeNode r, int space) {
        if (r == null)
            return;
        space += SPACE;
        print2D(r.right, space);
        System.out.println("\n");
        for (int i = SPACE; i < space; i++) {
            System.out.println(" ");
        }
        System.out.println(r.value + " ");
        print2D(r.left, space);
    }

    public void printPreorder(TreeNode r) {
        if (r == null)
            return;
        System.out.println(r.value + " ");
        printPreorder(r.left);
        printPreorder(r.right);
    }

    public void printInorder(TreeNode r) {
        if (r == null)
            return;
        printInorder(r.left);
        System.out.println(r.value + " ");
        printInorder(r.right);
    }

    public void printPostorder(TreeNode r) {
        if (r == null)
            return;
        printPostorder(r.left);
        printPostorder(r.right);
        System.out.println(r.value + " ");
    }

    public TreeNode iterativeSearch(int v) {
        if (root == null)
            return root;
        else {
            TreeNode temp = root;
            while (temp != null) {
                if (v == temp.value) {
                    return temp;
                }
                else if (v < temp.value) {
                    temp = temp.left;
                }
                else {
                    temp = temp.right;
                }
            }
            return null;

        }
    }

    public TreeNode recursiveSearch(TreeNode r, int val) {
        if (r == null || r.value == val)
            return r;

        else if (val < r.value)
            return recursiveSearch(r.left, val);

        else
            return recursiveSearch(r.right, val);
    }

    public int height(TreeNode r) {
        if (r == null)
            return -1;
        else {
            int lheight = height(r.left);
            int rheight = height(r.right);

            if (lheight > rheight)
                return (lheight + 1);
            else return (rheight + 1);
        }
    }

    public void printGivenLevel(TreeNode r, int level) {
        if (r == null)
            return;
        else if (level == 0)
            System.out.println(r.value + " ");
        else {
            printGivenLevel(r.left, level);
            printGivenLevel(r.right, level);
        }
    }

    public void printLevelOrderBFS(TreeNode r) {
        int h = height(r);
        for (int i = 0; i <= h; i++) {
            printGivenLevel(r, i);
        }
    }

    public TreeNode minValueNode(TreeNode node) {
        TreeNode current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    public TreeNode deleteNode(TreeNode r, int v) {
        if (r == null)
            return null;
        else if (v < r.value)
            r.left = deleteNode(r.left, v);
        else if (v > r.value)
            r.right = deleteNode(r.right, v);
        else {
            if (r.left == null) {
                TreeNode temp = r.right;
                r = null;
                return temp;
            }
            else if (r.right == null) {
                TreeNode temp = r.left;
                r = null;
                return temp;
            }
            else {
                TreeNode temp = minValueNode(r.right);
                r.value = temp.value;
                r.right = deleteNode(r.right, temp.value);
            }
        }
        return r;
    }
}
