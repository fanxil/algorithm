# algorithm：

参照：https://www.bilibili.com/video/BV1nP411F7vW?p=14&spm_id_from=pageDriver&vd_source=b6de31c8c04ee157a689d9ecf02a39c1

## 一：递归==循环

​	拆分子问题，存在基线和终止条件

​	简洁、反复调用，大量的方法的压栈和出栈。



递归常改造成迭代循环的解法

### 1、力扣[70. 爬楼梯](https://leetcode.cn/problems/climbing-stairs/) 

​	假设你正在爬楼梯。需要 `n` 阶你才能到达楼顶。

​	每次你可以爬 `1` 或 `2` 个台阶。你有多少种不同的方法可以爬到楼顶呢？

#### 	思路：fn = fn-1 + fn-2

```
import java.util.*;
class Solution {
    Map<Integer ,Integer> numMap = new HashMap<Integer ,Integer>();
    public int climbStairs(int n) {
        
        if(n == 1) {return 1;}
        if(n == 2) {return 2;}
        if(null != numMap.get(n)){
           return numMap.get(n);
        }
        int result = climbStairs(n -1) + climbStairs(n -2);
        numMap.put(n ,result);
        return result;
    }
}
```

改为循环

```
	public int climbStairs(int n) {
        if(n == 1) {return 1;}
        if(n == 2) {return 2;}
        int result = 0;
        int pre = 2;
        int prepre = 1;
        for(int i = 3 ; i <= n ; i ++){
            result = pre + prepre;
            prepre = pre;
            pre = result;
        }
        
        return result;
    }
```

### 2、剑指offer10 斐波那契数列

大家都知道斐波那契数列，现在要求输入一个正整数 n ，请你输出斐波那契数列的第 n 项。

斐波那契数列是一个满足 fib(x)=\left\{ \begin{array}{rcl} 1 & {x=1,2}\\ fib(x-1)+fib(x-2) &{x>2}\\ \end{array} \right.*f**i**b*(*x*)={1*f**i**b*(*x*−1)+*f**i**b*(*x*−2)*x*=1,2*x*>2 的数列

数据范围：1\leq n\leq 401≤*n*≤40

要求：空间复杂度 O(1)*O*(1)，时间复杂度 O(n)*O*(*n*) ，本题也有时间复杂度 O(logn)*O*(*l**o**g**n*) 的解法



## 数组

### 1、力扣[1. 两数之和](https://leetcode.cn/problems/two-sum/) （空间换时间）

```
import java.util.*;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> storeMap = new HashMap<>();

        for(int i = 0 ; i < nums.length ; i ++){
            int x = target - nums[i];
            if(storeMap.get(x) != null){
                return new int[]{storeMap.get(x) ,i  };
            }
            storeMap.put(nums[i] , i);
        }
        return new int[2];
    }
}
```

### 2、力扣[88. 合并两个有序数组](https://leetcode.cn/problems/merge-sorted-array/) （双指针）

给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。

请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。

注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。

```
class Solution {
	//利用Arrays的快速排序
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        
        for(int i = 0 ; i < n ; i++){
            nums1[m + i] = nums2[i];
        }
        Arrays.sort(nums1);
    }
}

```

已经有序，这点没有利用上，改用双指针比较 ：时间复杂度：O(m + n) 、空间复杂度：O(m + n) 增加了临时数组大小

```
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] temp = new int[n + m];//可以不要它
        int mIndex = 0;
        int nIndex = 0;
        for(int i = 0 ; i < n + m ; i++){
            if(mIndex > m - 1){
                temp[i] = nums2[nIndex++];
                continue;
            }
            if(nIndex > n - 1){
                temp[i] = nums1[mIndex++];
                continue;
            }
            if(nums1[mIndex] >= nums2[nIndex]){
                temp[i] = nums2[nIndex++];
                continue;
            }
            if(nums1[mIndex] < nums2[nIndex]){
                temp[i] = nums1[mIndex++];
                continue;
            }
        }

        for(int i = 0 ; i < n + m ; i++){
            nums1[i] = temp[i];
        }
    }

```

第一个数组，已有n+m数组大小，没有利用上，改用双指针倒序比较

```
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int k = m + n;
        int num1Index = m - 1;
        int num2Index = n -1;
        for(int i = k - 1; i >= 0 ; i--){
            if(num2Index < 0){
                nums1[i] = nums1[num1Index--];
            }else if(num1Index < 0){
                nums1[i] = nums2[num2Index--];
            }else if(nums1[num1Index] >= nums2[num2Index]){
                nums1[i] = nums1[num1Index--];
            }else if(nums1[num1Index] < nums2[num2Index]){
                nums1[i] = nums2[num2Index--];
            }
            
        }

    }
```

### 3、力扣[283. 移动零](https://leetcode.cn/problems/move-zeroes/) （双指针）

给定一个数组 `nums`，编写一个函数将所有 `0` 移动到数组的末尾，同时保持非零元素的相对顺序。

**请注意** ，必须在不复制数组的情况下原地对数组进行操作。

使用双指针算法

```
class Solution {
    public void moveZeroes(int[] nums) {

        int i = 0;
        for(int j = 0 ; j < nums.length ; j ++){
            if(nums[j] != 0){
                nums[i++] = nums[j];
            }
        }
        while(i < nums.length){
            nums[i++] = 0;
        }

    }
}
```

### 4、力扣[448. 找到所有数组中消失的数字](https://leetcode.cn/problems/find-all-numbers-disappeared-in-an-array/) 下标标识

给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。

```
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> results = new ArrayList<>();
        for (int num : nums) {
            int v = num > 0 ? num : -num;
            nums[v-1] = nums[v-1] < 0 ? nums[v-1] : -nums[v-1];
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                results.add(i + 1);
            }
        }
        return results;
    }
}
//第二种：>n 标识已存在
class Solution {
    //num[i]最大值是n
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> results = new ArrayList<>();
        int n = nums.length;
        for (int num : nums) {
            int index = (num - 1) % n;
            nums[index] += n;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= n) {
                results.add(i + 1);
            }
        }
        return results;
    }
}
```

### 5、

### 6、

### 7、

### 8、

### 9、

### 10、

## 二：链表（指针、双指针、递归等解法）

### 1、力扣[21. 合并两个有序链表](https://leetcode.cn/problems/merge-two-sorted-lists/) 循环双指针 或 递归

将两个升序链表合并为一个新的 **升序** 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 

循环 + 双指针 时间复杂度：O（m+n） 空间复杂度：

```
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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        //防御性代码
        if(list1 == null) return list2;
        if(list2 == null) return list1;
        ListNode result = new ListNode(0);
        ListNode temp = result;
        while(list1 != null || list2 != null){
            if(list1 == null){
                temp.next = list2;
                break;
            }
            if(list2 == null){
                temp.next = list1;
                break;
            }
            if(list1.val < list2.val){
                temp.next = list1;
                list1 = list1.next;
            }else{
                temp.next = list2;
                list2 = list2.next;
            }
            temp = temp.next;
        }
        return result.next;
    }
}
```

递归

```

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        //防御性代码
        if(list1 == null) return list2;
        if(list2 == null) return list1;
        if(list1.val < list2.val){
            list1.next = mergeTwoLists(list1.next , list2);
            return list1;
        }else {
            list2.next = mergeTwoLists(list1 , list2.next);
            return list2;
        }
    }
```

### 2、力扣[83. 删除排序链表中的重复元素](https://leetcode.cn/problems/remove-duplicates-from-sorted-list/) 单指针循环  或  递归

给定一个已排序的链表的头 `head` ， *删除所有重复的元素，使每个元素只出现一次* 。返回 *已排序的链表* 。

单指针循环

```
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null){
            return head;
        }
        if(head.next == null){
            return head;
        }
        ListNode temp = head;
        while(temp.next != null){
            if(temp.val == temp.next.val){
                temp.next = temp.next.next;
            }else{
                temp = temp.next;
            }
        }
        return head;
    }
}
```

递归

```
	public ListNode deleteDuplicates(ListNode head) {
        if(head == null){
            return head;
        }
        if(head.next == null){
            return head;
        }
        head.next = deleteDuplicates(head.next);
        return head.val == head.next.val ? head.next : head;
    }
```

### 3、力扣[141. 环形链表](https://leetcode.cn/problems/linked-list-cycle/)  所有环形链表可用快慢指针解决

给你一个链表的头节点 head ，判断链表中是否有环。

如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。

如果链表中存在环 ，则返回 true 。 否则，返回 false 。

利用Map解决

```
import java.util.*;
public class Solution {
    public boolean hasCycle(ListNode head) {
        Map<ListNode,Integer> map = new HashMap<>();

        ListNode temp = head;
        while(true){
            if(temp == null){
                return false;
            }
            map.put(temp , temp.val);
            if(temp.next != null && map.containsKey(temp.next)){
                return true;
            }
            temp = temp.next;
        }
    }
}
```

进阶:  弗洛伊德解法：双指针解法（快慢指针，有相遇return true）    空间复杂度O(1)

```
	public boolean hasCycle(ListNode head) {
        Map<ListNode,Integer> map = new HashMap<>();
        ListNode lowNode = head;
        ListNode fastNode = head;
        while(true){
            if(fastNode == null || fastNode.next == null || fastNode.next.next == null){
                return false;
            }
            lowNode = lowNode.next;
            fastNode = fastNode.next.next;
            if(lowNode == fastNode){
                return true;
            }
        }
    }
```

### 4、力扣[142. 环形链表 II](https://leetcode.cn/problems/linked-list-cycle-ii/)  快慢指针第二次相遇就是入环节点

给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。

如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。

不允许修改 链表。

```
	public ListNode detectCycle(ListNode head) {
        Map<ListNode,Integer> map = new HashMap<>();
        ListNode lowNode = head;
        ListNode fastNode = head;
        boolean exitLoop = false;
        while(true){
            if(fastNode == null || fastNode.next == null || fastNode.next.next == null){
                exitLoop = false; 
                break;
            }
            lowNode = lowNode.next;
            fastNode = fastNode.next.next;
            if(lowNode == fastNode){
                exitLoop = true;
                break;
            }
        }
        if(!exitLoop){
            return null;
        }

        lowNode = head;
        while(lowNode != fastNode){
            lowNode = lowNode.next;
            fastNode = fastNode.next;
        }
        return lowNode;
    }
```

### 5、力扣 [160. 相交链表 ](https://leetcode.cn/problems/intersection-of-two-linked-lists/) 复杂度 `O(m + n)` 、仅用 `O(1)` 内存

给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。

图示两个链表在节点 c1 开始相交：

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_statement.png)

暴力循环：先while a.next 嵌套b.next  时间复杂度 O(m * n)  空间O(1)

空间换时间：HashMap 时间复杂度 O(m + n)  空间O(m)或O(n)

双指针解法(满足进阶)：一起移动 时间复杂度 O(2m + 2n)  空间O(1)

```
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null){
            return null;
        }
        int loopA = 0;
        int loopB = 0;
        ListNode pA = headA;
        ListNode pB = headB;
        while(pA != pB){
            if(pA == null){
                loopA += 1;
            }
            if(pB == null){
                loopB += 1;
            }
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
            if(loopA > 1 && loopB > 1){
                return null;
            }
        }
        return pA;
    }
}
```

**进阶：**你能否设计一个时间复杂度 `O(n)` 、仅用 `O(1)` 内存的解决方案？

链表长度差值，取巧，代码复杂：时间复杂度 O(m + n + m /n +x)  空间O(1)

```

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null){
            return null;
        }
        int numA = 0;
        int numB = 0;
        ListNode pA = headA;
        ListNode pB = headB;
        while(pA != null){
            numA += 1;
            pA = pA.next;
        }
        while(pB != null){
            numB += 1;
            pB = pB.next;
        }
        pA = headA;
        pB = headB;
        if(numA > numB){
            for(int i = 0 ; i < numA - numB ; i ++){
                pA = pA.next;
            }
        }else{
            for(int i = 0 ; i < numB - numA ; i ++){
                pB = pB.next;
            }
        }
        while(pA != null){
            if(pA == pB){
                return pA;
            }
            pA = pA.next;
            pB = pB.next;
        }
        return null;
    }
```

### 6、力扣[206. 反转链表](https://leetcode.cn/problems/reverse-linked-list/) 找规律，全靠想象

给你单链表的头节点 `head` ，请你反转链表，并返回反转后的链表。

![img](https://assets.leetcode.com/uploads/2021/02/19/rev1ex1.jpg)

```
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode preNode = null;
        ListNode now = head;
        while(now != null){
            ListNode next = now.next;

            now.next = preNode;
            preNode = now;
            
            now = next;
        }

        return preNode;

    }
}
```

### 7、力扣[234. 回文链表](https://leetcode.cn/problems/palindrome-linked-list/) 双指针：快慢指针 利用反转链表

给你一个单链表的头节点 `head` ，请你判断该链表是否为回文链表。如果是，返回 `true` ；否则，返回 `false` 。

![img](https://assets.leetcode.com/uploads/2021/03/03/pal1linked-list.jpg)

```
输入：head = [1,2,2,1]
输出：true
```

```
class Solution {
    public boolean isPalindrome(ListNode head) {
        if(head == null){
            return false;
        }

        ListNode slowNode = head;
        ListNode fastNode = head;
        while(fastNode != null && fastNode.next != null){
            slowNode = slowNode.next;
            fastNode = fastNode.next.next;
        }
        slowNode = reverse(slowNode);
        fastNode = head;

        while(slowNode != null){
            if(slowNode.val != fastNode.val){
                return false;
            }
            slowNode = slowNode.next;
            fastNode = fastNode.next;
        }

        return true;
    }

    //反转
    public ListNode reverse(ListNode head){
        if(head == null){
            return head;
        }
        ListNode preNode = null;

        ListNode temp = head;
        while(temp != null){
            ListNode next = temp.next;
            temp.next = preNode;
            preNode = temp;
            temp = next;
        }
        return preNode;

    }
}
```



### 8、力扣[876. 链表的中间结点](https://leetcode.cn/problems/middle-of-the-linked-list/) 双指针：快慢指针 判断奇偶（未手写）

给你单链表的头结点 `head` ，请你找出并返回链表的中间结点。

如果有两个中间结点，则返回第二个中间结点。

![img](https://assets.leetcode.com/uploads/2021/07/23/lc-midlist1.jpg)

```
输入：head = [1,2,3,4,5]
输出：[3,4,5]
解释：链表只有一个中间结点，值为 3 。
```



### 9、剑指69  [**NC69链表中倒数最后k个结点**](https://www.nowcoder.com/practice/886370fe658f41b498d40fb34ae76ff9?tpId=196&tqId=37100&rp=1&ru=/exam/oj&qru=/exam/oj&sourceUrl=%2Fexam%2Foj%3Fpage%3D1%26pageSize%3D50%26search%3D%25E9%2593%25BE%25E8%25A1%25A8%25E4%25B8%25AD%26tab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D196&difficulty=undefined&judgeStatus=undefined&tags=&title=%E9%93%BE%E8%A1%A8%E4%B8%AD) 

输入一个长度为 n 的链表，设链表中的元素的值为 ai ，返回该链表中倒数第k个节点。

如果该链表长度小于k，请返回一个长度为 0 的链表。

要求：空间复杂度 O(n)，时间复杂度 O(n)

进阶：空间复杂度 O(1)，时间复杂度 O(n)

例如输入{1,2,3,4,5},2时，对应的链表结构如下图所示：

![img](https://uploadfiles.nowcoder.com/images/20211105/423483716_1636084313645/5407F55227804F31F5C5D73558596F2C)

其中蓝色部分为该链表的最后2个结点，所以返回倒数第2个结点（也即结点值为4的结点）即可，系统会打印后面所有的节点来比较。

## 示例1

输入：

```
{1,2,3,4,5},2
```

返回值：

```
{4,5}
```

利用Map处理，逻辑简单 时间：O(2n)  空间：O(n)

利用n - k + 1处理，逻辑简单 时间：O(2n)  空间：O(1)

利用双指针：快慢指针  时间：O(n)  空间：O(1)

```
import java.util.*;

/*
 * public class ListNode {
 *   int val;
 *   ListNode next = null;
 *   public ListNode(int val) {
 *     this.val = val;
 *   }
 * }
 */

public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 
     * @param pHead ListNode类 
     * @param k int整型 
     * @return ListNode类
     */
    public ListNode FindKthToTail (ListNode pHead, int k) {
        // write code here
    }
}
```



### 10、

## 三：栈与队列

### 1、力扣[232. 用栈实现队列](https://leetcode.cn/problems/implement-queue-using-stacks/) 

请你仅使用两个栈实现先入先出队列。队列应当支持一般队列支持的所有操作（push、pop、peek、empty）：

实现 MyQueue 类：

void push(int x) 将元素 x 推到队列的末尾
int pop() 从队列的开头移除并返回元素
int peek() 返回队列开头的元素
boolean empty() 如果队列为空，返回 true ；否则，返回 false
说明：

你 只能 使用标准的栈操作 —— 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。

```
输入：
["MyQueue", "push", "push", "peek", "pop", "empty"]
[[], [1], [2], [], [], []]
输出：
[null, null, null, 1, 1, false]

解释：
MyQueue myQueue = new MyQueue();
myQueue.push(1); // queue is: [1]
myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
myQueue.peek(); // return 1
myQueue.pop(); // return 1, queue is [2]
myQueue.empty(); // return false
```





### 2、力扣[394. 字符串解码](https://leetcode.cn/problems/decode-string/)  按规律压栈出栈（后入先出）

给定一个经过编码的字符串，返回它解码后的字符串。

编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。

你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。

此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。

```
输入：s = "3[a]2[bc]"
输出："aaabcbc"
```

### 3、

### 4、

### 5、

### 6、

### 7、

### 8、

### 9、

### 10、

## 四：树 ： 树的度、节点间关系、树的深度

二叉树：完全二叉树：左下标=2k+1 右下标：2(k+1) 最大非叶子节点下标：（n/2）-1

### 1、力扣[94. 二叉树的中序遍历](https://leetcode.cn/problems/binary-tree-inorder-traversal/) 

给定一个二叉树的根节点 `root` ，返回 *它的 **中序** 遍历* 。

![img](https://assets.leetcode.com/uploads/2020/09/15/inorder_1.jpg)

```
输入：root = [1,null,2,3]
输出：[1,3,2]
```

### 2、力扣[144. 二叉树的前序遍历](https://leetcode.cn/problems/binary-tree-preorder-traversal/) 

给你二叉树的根节点 `root` ，返回它节点值的 **前序** 遍历。

![img](https://assets.leetcode.com/uploads/2020/09/15/inorder_1.jpg)

```
输入：root = [1,null,2,3]
输出：[1,2,3]
```



### 3、力扣[145. 二叉树的后序遍历](https://leetcode.cn/problems/binary-tree-postorder-traversal/) 

给你一棵二叉树的根节点 `root` ，返回其节点值的 **后序遍历** 。

![img](https://assets.leetcode.com/uploads/2020/08/28/pre1.jpg)

```
输入：root = [1,null,2,3]
输出：[3,2,1]
```





### 4、力扣[110. 平衡(对称)二叉树](https://leetcode.cn/problems/balanced-binary-tree/) 

给定一个二叉树，判断它是否是高度平衡的二叉树。

本题中，一棵高度平衡二叉树定义为：

> 一个二叉树*每个节点* 的左右两个子树的高度差的绝对值不超过 1 。

![img](https://assets.leetcode.com/uploads/2020/10/06/balance_1.jpg)

```
输入：root = [3,9,20,null,null,15,7]
输出：true
```



### 5、力扣[104. 二叉树的最大深度](https://leetcode.cn/problems/maximum-depth-of-binary-tree/) 

给定一个二叉树，找出其最大深度。

二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。

说明: 叶子节点是指没有子节点的节点。

示例：
给定二叉树 [3,9,20,null,null,15,7]，

```
    3
   / \
  9  20
    /  \
   15   7
```

返回它的最大深度 3 。



### 6、力扣[110. 平衡二叉树](https://leetcode.cn/problems/balanced-binary-tree/) 

给定一个二叉树，判断它是否是高度平衡的二叉树。

本题中，一棵高度平衡二叉树定义为：

> 一个二叉树*每个节点* 的左右两个子树的高度差的绝对值不超过 1 。

![img](https://assets.leetcode.com/uploads/2020/10/06/balance_1.jpg)

```
输入：root = [3,9,20,null,null,15,7]
输出：true
```





### 7、力扣[226. 翻转二叉树](https://leetcode.cn/problems/invert-binary-tree/) 

给你一棵二叉树的根节点 `root` ，翻转这棵二叉树，并返回其根节点。

![img](https://assets.leetcode.com/uploads/2021/03/14/invert1-tree.jpg)

```
输入：root = [4,2,7,1,3,6,9]
输出：[4,7,2,9,6,3,1]
```



### 8、

### 9、

### 10、

## 五：十大排序算法

### 1、冒泡

### 2、选择

### 3、插入

### 4、快速

### 5、希尔

### 6、归并

### 7、堆

### 8、计数排序

### 9、桶排序

### 10、基数排序

### 11、力扣[912. 排序数组](https://leetcode.cn/problems/sort-an-array/) 

### 12、力扣[704. 二分查找](https://leetcode.cn/problems/binary-search/) 

### 13、总结

## 六：第二期：基础进阶

### 1、

### 2、

### 3、

### 4、

### 5、

### 6、

### 7、

### 8、

### 9、

### 10、

## 七：

### 1、

### 2、

### 3、

### 4、

### 5、

### 6、

### 7、

### 8、

### 9、

### 10、

## 八：

### 1、

### 2、

### 3、

### 4、

### 5、

### 6、

### 7、

### 8、

### 9、

### 10、

## 九：

### 1、

### 2、

### 3、

### 4、

### 5、

### 6、

### 7、

### 8、

### 9、

### 10、

## 十：

### 1、

### 2、

### 3、

### 4、

### 5、

### 6、

### 7、

### 8、

### 9、

### 10、





### 

### 