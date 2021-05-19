package junior.leetcode.editor.cn;
import org.junit.Test;
//找出数组中重复的数字。
//
//
//在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请
//找出数组中任意一个重复的数字。
//
// 示例 1：
//
// 输入：
//[2, 3, 1, 0, 2, 5, 3]
//输出：2 或 3
//
//
//
//
// 限制：
//
// 2 <= n <= 100000
// Related Topics 数组 哈希表
// 👍 420 👎 0


public class ShuZuZhongZhongFuDeShuZiLcof{

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findRepeatNumber(int[] nums) {
        int i = 0;
        int temp;
       while (i < nums.length){
           if (nums[i] == i){
               i ++;
               continue;
           }
           if(nums[nums[i]] == nums[i]) return nums[i];
           temp = nums[i];
           nums[i] = nums[temp];
           nums[temp] = temp;
       }
        return -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


    @Test
    public void usedTest() {
        int[] arr = {1, 2, 3, 0, 3, 4};
        int[] brr = {0, 1, 2, 3, 4, 5};
        Solution solution = new Solution();
        System.out.println(solution.findRepeatNumber(arr));
    }

}

