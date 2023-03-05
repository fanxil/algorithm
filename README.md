# algorithm

## 递归

​	拆分子问题，存在基线和终止条件

​	简洁、反复调用，大量的方法的压栈和出栈。



递归常改造成迭代循环的解法

### 1、力扣70 ：爬楼梯

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

### 1、力扣1 两数之和

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

### 2、力扣88 合并两个有序数组

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

已经有序，这点没有利用上，改用双指针比较



### 3、

### 4、

### 5、

### 6、

### 7、

### 8、

### 9、

### 10、

### 11、

### 12、

### 13、

### 14、

### 15、

### 

### 