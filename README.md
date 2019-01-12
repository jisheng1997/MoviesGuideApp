# movies-guide-app

# 版本更新
* Ver 1.1 by 季晟/陶李玮/韩馥骏/董天元
    * main login/movie/my favorite list/login界面UI设计初步完成 
* Ver 1.2 by 季晟 2019/1/8 6:34
    * main login界面抽屉功能初步实现（不能跳转）
* Ver 1.3 by 季晟 2019/1/8 14:18
    * 实现了main login与favorite界面的切换,页面之间的传值，页面切换的动画（向右滑入） 
* Ver 1.4 by 季晟 2019/1/9 3:46
    * (1) 添加了新依赖 androidTestImplementation 'junit:junit:4.12' androidTestImplementation 'com.android.support.test:rules:1.0.2'
    * (2) 所有Activity的父类BaseActivity新增了三个抽象方法，并重构了MainLoginActivity代码
* Ver 1.5 by 季晟 2019/1/9 4:58
    * (1) 重构了FavoriteActivity代码，增加了返回功能
    * (2) BaseActivity新增了三种页面切换的方法
* Ver 1.6 by 季晟/韩馥骏/陶李玮 2019/1/9 6:30
    * (1) login/register界面UI设计初步完成
    * (2) 新增LoginActivity与RegisterActivity类
* Ver 1.7 by 季晟 2019/1/9 7:32
    * (1) 重构了LoginActivity代码，增加了返回功能
    * (2) 实现了login与register界面的切换，页面切换的动画
* Ver 1.8 by 季晟 2019/1/9 17:00
    * (1) main logout界面UI设计初步完成
    * (2) 实现了main logout与login/register界面的切换
    * (3) 新增slide动画
    * (4) 实现了点击空白处隐藏输入法功能
    * (5) 重构了RegisterActivity代码，增加了返回功能
* Ver 1.9 by 季晟 陶李玮 2019/1/10 0:55
    * (1) search界面UI设计初步完成
    * (2) 新增了SearchActivity类
    * (3) 重构了MovieDetailsActivity代码
    * (4) 实现了状态栏透明与操作栏隐藏的功能(沉浸式体验)
    * (5) 修改了login/register界面UI设计
* Ver 1.10 & Ver1.11 by 季晟/陶李玮/韩馥骏/董天元 2019/1/10 15:19
    * (1) 重写了MovieAdapter和CommentsAdapter类
    * (2) 实现了search框到search界面的页面切换
    * (3) 修改了Movie类
    * (4) Mainlogout类新增了数据库连接，读取数据的方法
    * (5) 实现了特定movie至Moviedetails界面的页面切换
    * (6) 实现了特定movie至Moviedetails界面的传值 
* Ver 1.12 & 1.13 & 1.14 & 1.15 by 季晟/陶李玮/韩馥骏/董天元 2019/1/12 7:13
    * (1) 将9个fragment合并成一个
    * (2) BaseActivity新增了请求数据库数据的方法
    * (3) BaseActivity新增了了数据处理方法(需重写）
    * (4) 修改了部分页面切换动画
    * (5) 修改了侧滑菜单，实现了电影类型的切换
    * (6) 实现了导航栏下划线的监听
    * (7) 完成了数据库输入
    * (8) 实现了注册和登陆功能
    * (9) 修复了若干bug，并献祭了陶李玮十年寿命  <br><br><br>
* Ver 1.16 & 1.17 by 季晟/陶李玮/韩馥骏/董天元 2019/1/12 8:10
    * (1) 此版本为1.12 & 1.13 & 1.14 & 1.15版本整合版
    * (2) 修复了若干bug

## 1.项目总览
* 1.1 项目介绍（Description)
     * We are about to make a movie guide app which can show the users the information of movies by phone with andriod phone.This software is mainly used to serve the people who look for the movies. Users can find the information of movie by searching the      movie's name from the database. Our software can show the basic information, brief description and comments of movies. Otherwise, Our   software can recommand movies to users by different sorts.What’s more, our software has a clear and simple interface. Users can easily learn how to use it at the first time they are using it.
* 1.2 目标人群（Define users/target audience)
     * People who are not able to decide the movie to see because of the lack of information which is helpful to make choice.
* 1.3 项目功能（Product functions）
     * 1.3.1 Login
     * 1.3.2 Registration
     * 1.3.3 Search movies
     * 1.3.4 Recommend movies
     * 1.3.5 Display movie information
     * 1.3.6 Make comments
     * 1.3.7 Favorite list
     * 1.3.8 History list
     * 1.3.9 Sort movies
* 1.4 功能性需求（Functional Requirements)
    * [Functional Requirements](https://github.com/jisheng1997/MoviesGuideApp/blob/master/project/Functional%20Requirements.md)
* 1.5 非功能性需求(Nonfunctional requirements)
    * [Nonfunctional Requirements](https://github.com/jisheng1997/MoviesGuideApp/blob/master/project/Nonfunctional%20Requirements.md)
* 1.6 流程图 Ver1.1 <br>
![流程图](https://github.com/jisheng1997/MoviesGuideApp/blob/master/project/project%20Ver%201.1.png) <br>
* 1.7 项目成员(排名不分先后)
    * [季晟](https://github.com/jisheng1997)      
    * [陶李玮](https://github.com/zoslen)      
    * [韩馥骏](https://github.com/uncleeesky)      
    * [董天元](https://github.com/Zitronen)

## 2.资料地址 <br>
* [git下载地址](https://git-scm.com/download/win) <br>
* [Android studio下将项目代码上传至github包括更新，同步，创建依赖](https://blog.csdn.net/u013309870/article/details/79214030) <br>
* [GitHub上README.md排版样式教程](https://blog.csdn.net/u012067966/article/details/50736647) <br>
* [Intent中的四个重要属性](https://blog.csdn.net/weihan1314/article/details/7973511) <br>
* [Android 命名规范](https://blog.csdn.net/vipzjyno1/article/details/23542617)
* [Android Studio 2.2新增布局——ConstraintLayout完全解析](https://blog.csdn.net/SEU_Calvin/article/details/55522706)
* [Android状态栏微技巧](https://blog.csdn.net/guolin_blog/article/details/51763825)



## 3.《第一行代码》相关<br>
* p98 圆形进度条加载
* p101 确认对话框
* p125 Listview下滑列表——用于展示电影的列表
* P160 主页分类列表
* P415 滑动菜单（抽屉）
* P431 卡片式布局（reccomend）

