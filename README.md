# AppBar

通过继承ToolBar制作的简单Actionbar。

### 示例

编码：
![](./screenshot/code.png)
结果：
![](./screenshot/preview.png)  

### 添加依赖

1.在项目外层的build.gradle中添加JitPack仓库

    <span class="hljs-tag">repositories</span> {
        <span class="hljs-tag">maven</span> {
            <span class="hljs-attribute">url</span> <span class="hljs-string">"https://jitpack.io"</span>
        }
    }
    `</pre>

    2.在用到的项目中添加依赖

    <pre>`<span class="hljs-keyword">dependencies</span> {
            <span class="hljs-keyword">compile</span> <span class="hljs-string">'com.github.tianzhijiexian:AppBar:1.0'</span>
    }
    `</pre>

    ### 前言

      Android的actionbar机制让我们需要定义各种style，而且actionbar内部的view都是不能直接获得的，在做一些效果的时候比较不方便。最关键的是actionbar的menu定义和初始化是在activity中的，个人认为xml文件中就应该能做好一切的ui，而activity中只是做UI和事件的连接工作。
      于是，我想自己定义一个actionbar。在做的时候发现自己定义一个actionbar是很困难的，无法照顾到所有需求，什么自动折叠menu这样的特殊操作比较难做。在综合考虑之下，我通过继承ToolBar的方式来做的actionbar。  

    这里分享几个感悟：  

    **1. 为啥actionbar不是放在xml中，而是要用activity来set呢？**  

      因为actionbar这个东西很多页面都需要引用，如果做成单个的控件，那么就需要重复引用多次，不方便。放在activity中的原因是可以将actionbar这种特殊的、整个应用中样子都不太会变的view进行统一管理。但是不好之处就是需要各种style，而且比较难做动画效果。于是官方抛弃了actionbar，而是推出了toolbar。这样的好处就是可以自己灵活管理这个view，在现在这个actionbar都要很多动画的年代，老旧古板的actionbar必然被抛弃。  

    **2. 为啥actionbar的menu要放在一个独立的xml中写？**  

       因为actionbar的menu样子都差不多，之前的设计方案是希望把这些样子基本一样的view做统一管理，一个style定义好了所有menu的样式。但是android又没有给view提供这样的机制，于是就建立了一个menu的xml来把这些menu不同的地方进行编写。至于样式和大小什么的，都不用管了，style文件会帮你搞定。这也是变和不变隔离的思想。但问题又来了，这样做的坏处就是写一个页面很痛苦，首先xml中不能预览actionbar，而且在activity中还需要set一下actionbar。想要写menu还得写一个xml文件，在activity中进行create。最终，写一个menu功能需要动三个文件，很麻烦，大大降低了编码的舒适感。  

    ### 使用方式

    **0. 不混淆toolbar**   

    > -keep class android.support.v7.widget.Toolbar {*;}

    **1. 在主题中设置默认样式（一次设置就搞定）**

    <pre>`

这里的style都是采用的默认style，完全可以自己参考默认style进行定义。

**2. 布局文件**
```XML  