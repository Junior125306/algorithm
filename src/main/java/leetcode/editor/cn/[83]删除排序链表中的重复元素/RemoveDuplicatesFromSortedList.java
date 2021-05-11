package junior.leetcode.editor.cn;
import leetcode.editor.cn.utils.ListNode;
import org.junit.Test;
//存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素 只出现一次 。
//
// 返回同样按升序排列的结果链表。
//
//
//
// 示例 1：
//
//
//输入：head = [1,1,2]
//输出：[1,2]
//
//
// 示例 2：
//
//
//输入：head = [1,1,2,3,3]
//输出：[1,2,3]
//
//
//
//
// 提示：
//
//
// 链表中节点数目在范围 [0, 300] 内
// -100 <= Node.val <= 100
// 题目数据保证链表已经按升序排列
//
// Related Topics 链表
// 👍 573 👎 0


public class RemoveDuplicatesFromSortedList{

    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return head; // []
        if (head.next == null) return head;// [2]

        ListNode p = head; // head.val 为链表中的第一个节点的元素值
        while (p.next != null) {

            if (p.val == p.next.val) {

                p.next = p.next.next;
            } else {

                p = p.next;
            }
        }
        return head;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


    @Test
    public void usedTest() {
        System.out.println();
    }

}

