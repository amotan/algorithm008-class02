### 学习笔记

---

#### 一、位运算

1.  异或

```
异或：相同为 0，不同为 1。也可用“不进位加法”来理解。
异或操作的一些特点：
x ^ 0 = x
x ^ 1s = ~x // 注意 1s = ~0
x ^ (~x) = 1s
x ^ x = 0
c = a ^ b => a ^ c = b, b ^ c = a 		// 交换两个数
a ^ b ^ c = a ^ (b ^ c) = (a ^ b) ^ c // associative
```

2. 指定位置的位运算
```
1. 将 x 最右边的 n 位清零：x& (~0 << n)
2. 获取 x 的第 n 位值（0 或者 1）： (x >> n) & 1
3. 获取 x 的第 n 位的幂值：x& (1 <<n)
4. 仅将第 n 位置为 1：x | (1 << n)
5. 仅将第 n 位置为 0：x & (~ (1 << n))
6. 将 x 最高位至第 n 位（含）清零：x& ((1 << n) -1)
7. 将第 n 位至第 0 位（含）清零：x& (~ ((1 << (n + 1)) -1))
```

3. 实战位运算要点
```
(a)判断奇偶：
		x % 2 == 1 —> (x & 1) == 1
		x % 2 == 0 —> (x & 1) == 0
(b)x >> 1 —> x / 2. 
		即: x = x / 2; —> x = x >> 1;
			  mid = (left + right) / 2; —> mid = (left + right) >> 1;
(c)X = X & (X-1) 清零最低位的 1
(d)X & -X => 得到最低位的 1 
(e)X & ~X => 0
```



#### 二、布隆过滤器、LRU cache 

​		一个很长的二进制向量和一系列随机映射函数。布隆过滤器可以用于检索一个元素是否在一个集合中。

​		布隆过滤器是一种数据结构，比较巧妙的概率型数据结构（probabilistic data structure），特点是高效地插入和查询，可以用来告诉你 “某样东西一定不存在或者可能存在”。

​		布隆过滤器常用于解决缓存穿透，垃圾邮件识别，集合判重等。相比于传统的 List、Set、Map 等数据结构，它更高效、占用空间更少，但是缺点是其返回的结果是概率性的，而不是确切的。



​		LRU全名是：Least recently use 简单的说就是：最近最少使用。

#### 三、排序算法

1. 冒泡算法

```java

    /**
     * 从小到大
     */
    public void sort() {
        int length = array.length;
        if (length > 0) {
            for (int i = 1; i < length; i++) {
                for (int j = 0; j < length - i; j++) {
                    if (array[j] > array[j + 1]) {
                        int temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                    }
                }
            }
        }
    }

```

2. 选择排序
```java
		//从小到大
    public void selectionSort() {
        int min, temp;
        for (int i = 0; i < array.length; i++) {
            // 初始化未排序序列中最小数据数组下标
            min = i;
            for (int j = i + 1; j < array.length; j++) {
                // 在未排序元素中继续寻找最小元素，并保存其下标
                if (array[j] < array[min]) {
                    min = j;
                }
            }
            // 将未排序列中最小元素放到已排序列末尾
            if (min != i) {
                temp = array[min];
                array[min] = array[i];
                array[i] = temp;
            }
        }
    }

```

3. 快速排序
```java
public static void qSort(int[] arr, int head, int tail) {
        if (head >= tail || arr == null || arr.length <= 1) {
            return;
        }
        int i = head, j = tail, pivot = arr[(head + tail) / 2];
        while (i <= j) {
            while (arr[i] < pivot) {
                ++i;
            }
            while (arr[j] > pivot) {
                --j;
            }
            if (i < j) {
                int t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
                ++i;
                --j;
            } else if (i == j) {
                ++i;
            }
        }
        qSort(arr, head, j);
        qSort(arr, i, tail);
    }
```



4. 堆排序
```java
/**
     * 堆排序的主要入口方法，共两步。
     */
    public void sort() {
        /*
         *  第一步：将数组堆化
         *  beginIndex = 第一个非叶子节点。
         *  从第一个非叶子节点开始即可。无需从最后一个叶子节点开始。
         *  叶子节点可以看作已符合堆要求的节点，根节点就是它自己且自己以下值为最大。
         */
        int len = array.length - 1;
        int beginIndex = (len - 1) >> 1;
        for (int i = beginIndex; i >= 0; i--)
            maxHeapify(i, len);
        /*
         * 第二步：对堆化数据排序
         * 每次都是移出最顶层的根节点A[0]，与最尾部节点位置调换，同时遍历长度 - 1。
         * 然后从新整理被换到根节点的末尾元素，使其符合堆的特性。
         * 直至未排序的堆长度为 0。
         */
        for (int i = len; i > 0; i--) {
            swap(0, i);
            maxHeapify(0, i - 1);
        }
    }

    private void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * 调整索引为 index 处的数据，使其符合堆的特性。
     *
     * @param index 需要堆化处理的数据的索引
     * @param len 未排序的堆（数组）的长度
     */
    private void maxHeapify(int index, int len) {
        int li = (index << 1) + 1; // 左子节点索引
        int ri = li + 1;           // 右子节点索引
        int cMax = li;             // 子节点值最大索引，默认左子节点。
        if (li > len) return;      // 左子节点索引超出计算范围，直接返回。
        if (ri <= len && array[ri] > array[li]) // 先判断左右子节点，哪个较大。
            cMax = ri;
        if (array[cMax] > array[index]) {  //若“<”这是从大到小
            swap(cMax, index);      // 如果父节点被子节点调换，
            maxHeapify(cMax, len);  // 则需要继续判断换下后的父节点是否符合堆的特性。
        }
    }

```