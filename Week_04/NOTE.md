#### 学习笔记

---

#### 一、深度优先搜索和广度优先搜索
#### 代码模版

1. DFS 代码 - 非递归写法

   ```python
   def DFS(self, tree): 
   if tree.root is None: 
   return [] 
    visited, stack = [], [tree.root] 
   while stack: 
    node = stack.pop() 
    visited.add(node) 
    process (node) 
    nodes = generate_related_nodes(node) 
    stack.push(nodes) 
   # other processing work 
   ...
   ```

   

2.   DFS 代码 - 递归写法

   ```python
   visited = set() 
   def dfs(node, visited): 
    visited.add(node) 
   #process current node here. 
   ...
   for next_node in node.children(): 
   if not next_node in visited: 
    dfs(next node, visited)
   ```

   

3. BFS代码

   ```python
   def BFS(graph, start, end): 
    queue = [] 
    queue.append([start]) 
    visited.add(start) 
   while queue: 
    node = queue.pop() 
    visited.add(node) 
    process(node) 
    nodes = generate_related_nodes(node) 
    queue.push(nodes) 
   # other processing work 
   ...
   ```

   

### 二、二分查找

#### 1. 前提

1. 目标函数单调性（单调递增或者递减）

2. 存在上下界（bounded）

3. 能够通过索引访问（index accessible)

#### 2. 代码模版

```python
left, right = 0, len(array) - 1
while left <= right:
 mid = (left + right) / 2
if array[mid] == target: # find the target!! 
break or return result 
 elif array[mid] < target:
 left = mid + 1
else:
 right = mid - 1
```



### 三、贪心算法

#### 适用贪心算法的场景
简单地说，问题能够分解成子问题来解决，子问题的最优解能递推到最终
问题的最优解。这种子问题最优解称为最优子结构。

贪心算法与**动态规划**的不同在于它对每个子问题的解决方案都做出选择，
不能回退。动态规划则会保存以前的运算结果，并根据以前的结果对当前
进行选择，有回退功能。



### 四、半序数组问题

使用二分查找，寻找一个半有序数组 [4, 5, 6, 7, 0, 1, 2] 中间无序的地方

##### 代码思路：

```python
left, right = 0, len(array) - 1
while left <= right:
 mid = (left + right) / 2

# 如果中间值大于前一位置数的值，并且小于后一位置数的值
if (array[mid]>array[mid-1] && array[mid]<array[mid+1]): 
  # 判断应该向左还是向右进行二分
  if(array[mid-1]>array[mid-2] && array[mid-1]<array[mid]):
    left = mid;
  elif (array[mid+2]>array[mid+1] && array[mid]<array[mid+1]):
    right = mid;
# 情况都不满足，则是无序开始的地方
else:
  return mid;
```



