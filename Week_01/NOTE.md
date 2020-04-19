## 学习笔记

#### 一、收获及问题
听了超哥的讲解，简直湖体灌顶，彻底改变了自己原来做算法题的思路。

以前做算法题遇到困难，总是想着一定是自己哪儿的知识点没有掌握，先去把知识点补一补，自己真的就是在哪里死抠，半天抠不出来一个好的思路。

但是上了超哥的算法课，恍然大悟，动手最重要，短时间想不出来，那么完全可以去借鉴学习别人的知识。

**目前自己的问题**是没有做到每天刷题，赶在周末完成所有一周所有内容，还是比较赶。以后需要更加合理安排自己的工作和生活时间，挤出时间来刷题。




#### 二、知识点
##### 2.1 算法加速
1. 数据结构升维，空间换时间。
2. 跳表（多级索引）；
3. 双指针法
	* 两边向中间收敛（直到两个指针相遇）
	* 快慢指针法（一个快指针先走，另外一个慢指针满足特定条件才走）

##### 2.2 巩固数据结构知识
1. 数组，链表，跳表的基本实现

2. 栈，队列，双端队列，优先队列的实现

   

####三、用add first或add last这套新的API改写Deque的代码
```java
package stackandqueue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 队列练习
 */
public class QueuePractice {

    public static void main(String[] args) {
        // -------------------------------------------------------------
        // 旧代码
        Deque<String> deque = new LinkedList<String>();
        deque.push("a");
        deque.push("b");
        deque.push("c");
        System.out.println(deque);

        String str = deque.peek();
        System.out.println(str);
        System.out.println(deque);

        while (deque.size() > 0) {
            System.out.println(deque.pop());
        }
        System.out.println(deque);
        //---------------------------------------------------------------
        // 新代码，使用add first或者add last改写
        System.out.println("--using new API addFirst--");

        deque.addFirst("a");
        deque.addFirst("b");
        deque.addFirst("c");
        System.out.println(deque);
        while (deque.size() > 0) {
            System.out.println(deque.pop());
        }
        System.out.println(deque);

        System.out.println("--using new API addLast--");
        deque.addLast("a");
        deque.addLast("b");
        deque.addLast("c");
        System.out.println(deque);
        while (deque.size() > 0) {
            System.out.println(deque.pop());
        }
        System.out.println(deque);
    }
}
```
**总结：**addFirst然后pop实现的栈，先进后出；addLast然后pop实现的是队列，先进先出。



####四、代码分析（Java语言）

#####4.1 Queue源码分析
Java中，Queue为接口，提供了如下操作：

```
add      增加一个元索                  如果队列已满，则抛出一个IIIegaISlabEepeplian异常
remove   移除并返回队列头部的元素        如果队列为空，则抛出一个NoSuchElementException异常
element  返回队列头部的元素             如果队列为空，则抛出一个NoSuchElementException异常
offer    添加一个元素并返回true         如果队列已满，则返回false
poll     移除并返问队列头部的元素        如果队列为空，则返回null
peek     返回队列头部的元素             如果队列为空，则返回null
```

##### 4.2 Priority Queue优先队列

优先队列是一种自然排序或者通过构造时提供的comparator排序的无界队列。它不允许为空的元素。

默认排序为自然排序，通过阅读源码我们发现其offer方法添加元素时使用了siftUp方法，自下而上堆化，一直往上跟父节点比较。

```java
		/**
     * Inserts item x at position k, maintaining heap invariant by
     * promoting x up the tree until it is greater than or equal to
     * its parent, or is the root.
     * 在k位置插入项目x，通过向上提升x直到它大于或等于其父节点，或者是根节点，来保持堆不变。
     * To simplify and speed up coercions and comparisons. the
     * Comparable and Comparator versions are separated into different
     * methods that are otherwise identical. (Similarly for siftDown.)
     *
     * @param k the position to fill
     * @param x the item to insert
     */
    private void siftUp(int k, E x) {
        if (comparator != null)
            siftUpUsingComparator(k, x);
        else
            siftUpComparable(k, x);
    }

    @SuppressWarnings("unchecked")
    private void siftUpComparable(int k, E x) {
        Comparable<? super E> key = (Comparable<? super E>) x;
        while (k > 0) {
            // 找到父节点的位置
            // 因为元素是从size+1开始的，所以减1之后再除以2
            int parent = (k - 1) >>> 1;
            // 父节点的值
            Object e = queue[parent];
          	// 比较插入的元素与父节点的值
        		// 如果比父节点大，则跳出循环
        		// 否则交换位置
            if (key.compareTo((E) e) >= 0)
                break;
            // 与父节点交换位置
            queue[k] = e;
            // 现在插入的元素位置移到了父节点的位置
            // 继续与父节点再比较
            k = parent;
        }
        // 最后找到应该插入的位置，放入元素
        queue[k] = key;
    }
```

此处源码分析，插入操作的时间复杂度是O(logn)，与超哥课堂视频中讲的有出入。



我们再来看poll操作，关键点依然在于移除队首元素后，把队尾元素重新调整到堆中，其使用了siftDown方法，如下：

```java
@SuppressWarnings("unchecked")
public E poll() {
    // 如果size为0，说明没有元素
    if (size == 0)
        return null;
    // 弹出元素，元素个数减1
    int s = --size;
    modCount++;
    // 队列首元素
    E result = (E) queue[0];
    // 队列尾元素
    E x = (E) queue[s];
    // 将队列尾元素删除
    queue[s] = null;
    // 如果弹出元素后还有元素
    if (s != 0)
        // 将队列尾元素重新调整到堆列中正确的位置
        siftDown(0, x);
    // 返回弹出的元素
    return result;
}

private void siftDown(int k, E x) {
    // 根据是否有比较器，选择不同的方法
    if (comparator != null)
        siftDownUsingComparator(k, x);
    else
        siftDownComparable(k, x);
}

@SuppressWarnings("unchecked")
private void siftDownComparable(int k, E x) {
    Comparable<? super E> key = (Comparable<? super E>)x;
    // 只需要比较一半就行了，因为叶子节点占了一半的元素
    int half = size >>> 1;        // loop while a non-leaf
    while (k < half) {
        // 寻找子节点的位置，这里加1是因为元素从0号位置开始
        int child = (k << 1) + 1; // assume left child is least
        // 左子节点的值
        Object c = queue[child];
        // 右子节点的位置
        int right = child + 1;
        if (right < size &&
            ((Comparable<? super E>) c).compareTo((E) queue[right]) > 0)
            // 左右节点取其小者
            c = queue[child = right];
        // 如果比子节点都小，则结束
        if (key.compareTo((E) c) <= 0)
            break;
        // 如果比最小的子节点大，则交换位置
        queue[k] = c;
        // 指针移到最小子节点的位置继续往下比较
        k = child;
    }
    // 找到正确的位置，放入元素
    queue[k] = key;
}
```

以上分析了Priority Queue的重要方法的实现，进一步了解了优先队列的算法实现机制。

