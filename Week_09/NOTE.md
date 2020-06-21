### 学习笔记

#### 一、动态规划、状态转移方程串讲

##### 关键点：

​	动态规划 和 递归或者分治 没有根本上的区别（关键看有无最优的子结构；

​	拥有共性：找到重复子问题；

​	差异性：最优子结构、中途可以淘汰次优解；



#### 二、不同路径2问题的状态转移方程

```
if(cannotgo[i-1][j-1]){
         dp[i][j] = 0;
    }else{
 				 dp[i][j] = dp[i-1][j] + dp[i][j-1];
         }
```



#### 三、字符串问题总结

##### Atoi代码示例
```java
public int myAtoi(String str) {
    int index = 0, sign = 1, total = 0;
    //1. Empty string
    if(str.length() == 0) return 0;
    //2. Remove Spaces
    while(str.charAt(index) == ' ' && index < str.length())
        index ++;
    //3. Handle signs
    if(str.charAt(index) == '+' || str.charAt(index) == '-'){
        sign = str.charAt(index) == '+' ? 1 : -1;
        index ++;
    }
    
    //4. Convert number and avoid overflow
    while(index < str.length()){
        int digit = str.charAt(index) - '0';
        if(digit < 0 || digit > 9) break;
        //check if total will be overflow after 10 times and add digit
        if(Integer.MAX_VALUE/10 < total ||            
        	Integer.MAX_VALUE/10 == total && Integer.MAX_VALUE %10 < digit)
            return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        total = 10 * total + digit;
        index ++;
    }
    return total * sign;
}
```


