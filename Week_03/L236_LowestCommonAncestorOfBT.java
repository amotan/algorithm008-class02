package divideandconquer;

import javax.swing.tree.TreeNode;

/**
 * 236. 二叉树的最近公共祖先
 */
public class L236_LowestCommonAncestorOfBT {

    private TreeNode ans;

    public L236_LowestCommonAncestorOfBT() {
        this.ans = null;
    }

    /**
     * 深度遍历
     * 从根节点的子树来判断是否包含节点p或者q
     * @param root 根节点二叉树
     * @param p 指定节点p
     * @param q 指定节点q
     * @return
     */
    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {

        // recursion terminatorv
        if (root == null) {
            return false;
        }
        // 判断左子树是否包含p或者q
        boolean lson = dfs(root.left, p, q);

        // 判断右子树是否包含p或者q
        boolean rson = dfs(root.right, p, q);

        // 算法条件
        if ((lson && rson) || ((root.val == p.val || root.val == q.val) && (lson || rson))) {
            ans = root;
        }
        return lson || rson || (root.val == p.val || root.val == q.val);
    }


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        this.dfs(root, p, q);
        return this.ans;
    }

}



class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
