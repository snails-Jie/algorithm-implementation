### [设计模式：状态模式](http://www.gxitsky.com/2019/10/20/designPatterns-10-State/)
1. 有状态对象：根据不同的状态做出不同的行为 <br/>
  1.1 状态：影响对象行为的一个或多个动态变化的属性 <br/>
#### 模式分析
1.  抽象状态类：一个抽象类来专门表示对象的状态 <br/>
    1.1 对象的具体状态都继承该类，在重写的方法实现自己状态的行为，包括各种状态迁移<br/>
2.  解决思想：把相关**判断逻辑**提取出来，放到一系列的状态类当中
#### 模式结构
1. 抽象状态（State）:抽象状态，用于封装环境对象(Context)中特定状态所对应的行为
2. 具体状态: 实现抽象状态类中的方法，方法里封装自己状态的行为
3. 环境(Context) -- 充当状态管理器角色 <br/>
    3.1 持有一个抽象状态类型的属性用于维护当前状态<br/>
    3.2 定义一个方法，在方法里将与状态相关的操作委托托给当前状态对象来处理<br/>
![状态模式](https://raw.githubusercontent.com/nicky-chen/pic_store/master/20190509163258.png)
  