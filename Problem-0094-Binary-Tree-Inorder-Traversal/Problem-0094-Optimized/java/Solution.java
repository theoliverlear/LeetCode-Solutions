//=================================-Imports-==================================
import java.util.ArrayList;
import java.util.List;
//=================================-Solution-=================================
class Solution {
    //-------------------------Inorder-Traversal------------------------------
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> previousNodeNums = this.getTreeTraversal(root);
        return previousNodeNums;
    }
    //-------------------------Get-Tree-Traversal-----------------------------
    public List<Integer> getTreeTraversal(TreeNode node) {
        return this.getTreeTraversal(node, new ArrayList<>());
    }
    //-------------------------Get-Tree-Traversal-----------------------------
    public List<Integer> getTreeTraversal(TreeNode node, List<Integer> previousNodeNums) {
        if (node == null) {
            return previousNodeNums;
        }
        this.getTreeTraversal(node.left, previousNodeNums);
        previousNodeNums.add(node.val);
        this.getTreeTraversal(node.right, previousNodeNums);
        return previousNodeNums;
    }
}
//=============================-LeetCode-Models-==============================

//---------------------------------List-Node----------------------------------
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}