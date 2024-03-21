public class AVLTree {
    private static final int SPACE = 10;
    public TreeNode root;
    public AVLTree() {
        root = null;
    }

    public boolean isTreeEmpty() {
        if (root == null) {
            return true;
        }
        else {
            return false;
        }
    }

    public int height(TreeNode r) {
        if (r == null)
            return -1;
        else {
            int lheight = height(r.left);
            int rheight = height(r.right);

            if (lheight > rheight)
                return (lheight + 1);
            return (rheight + 1);
        }
    }

    public int getBalanceFactor(TreeNode n) {
        if (n == null)
            return -1;
        return height(n.left) - height(n.right);
    }

    public TreeNode rightRotate(TreeNode y) {
        TreeNode x = y.left;
        TreeNode T2 = x.right;

        x.right = y;
        y.left = T2;

        return x;
    }

    public TreeNode leftRotate(TreeNode x) {
        TreeNode y = x.right;
        TreeNode T2 = y.left;

        y.left = x;
        x.right = T2;

        return y;
    }

    public TreeNode insert(TreeNode r, TreeNode new_node) {
        if (r == null) {
            r = new_node;
            System.out.println("Value inserted successfully.\n");
            return r;
        }

        if (new_node.value < r.value) {
            r.left = insert(r.left, new_node);
        }
        else if (new_node.value > r.value) {
            r.right = insert(r.right, new_node);
        }
        else {
            System.out.println("No duplicate values allowed!\n");
            return r;
        }

        int bf = getBalanceFactor(r);
        if (bf > 1 && new_node.value < r.left.value) {
            return rightRotate(r);
        }

        if (bf < -1 && new_node.value > r.right.value) {
            return leftRotate(r);
        }

        if (bf > 1 && new_node.value > r.left.value) {
            r.left = leftRotate(r.left);
            return rightRotate(r);
        }

        if (bf < -1 && new_node.value < r.right.value) {
            r.right = rightRotate(r.right);
            return leftRotate(r);
        }

        return r;
    }

    public TreeNode minValueNode(TreeNode node) {
        TreeNode current = node;
        while (current.left != null) {
            current = current.left;
        }
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

        int bf = getBalanceFactor(r);

        if (bf == 2 && getBalanceFactor(r.left) >= 0)
            return rightRotate(r);
        else if (bf == 2 && getBalanceFactor(r.left) == -1) {
            r.left = leftRotate(r.left);
            return rightRotate(r);
        }

        else if (bf == -1 && getBalanceFactor(r.right) <= 0)
            return leftRotate(r);
        else if (bf == -2 && getBalanceFactor(r.right) == 1) {
            r.right = rightRotate(r.right);
            return leftRotate(r);
        }

        return r;
    }

    public void print2D(TreeNode r, int space) {
        if (r == null)
            return;
        space += SPACE;
        print2D(r.right, space);
        System.out.println("\n");
        for (int i = SPACE; i < space; i++)
            System.out.println(" ");
        System.out.println(r.value + "\n");
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
        for (int i = 0; i <= h; i++)
            printGivenLevel(r, i);
    }

    public TreeNode iterativeSearch(int v) {
        if (root == null)
            return root;
        else {
            TreeNode temp = root;
            while (temp != null) {
                if (v == temp.value)
                    return temp;
                else if (v < temp.value)
                    temp = temp.left;
                else
                    temp = temp.right;
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
}
