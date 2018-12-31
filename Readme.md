# 翻转课堂管理系统

## 目录
1. [主要功能](#main-function)
2. [演示图片](#demo-picture)
3. [项目技术栈](#project-tech-stack)
4. [目录结构](#directory-structure)
5. [任务 & 代码规范](#task-and-code-standard)
6. [项目总结](#project-summary)


## <div id = "main-function" /> 主要功能 

#### 老师

1. 共享分组 / 讨论课
2. 讨论课展示 / 提问打分
3. 课后作业（书面报告）打分

#### 学生

1. 组队管理
2. 讨论课提问
3. 上传 / 下载  发言材料 / 书面报告
4. 查看成绩

#### 管理员

1. 管理（CRUD）老师 / 学生账号

##  <div id = "demo-picture" />演示图片 
详见：

[demo-picture](Demo Picture.md)

## <div id = "project-tech-stack" /> 项目技术栈 

#### 工具

为了避免 / 减少由于工具版本不同可能产生的问题，该项目强制要求使用下列版本。
此外，类库版本以pom.xml文件为标准，这里没有列出。
注：{version} ~ 表示此版本后均可接受。

+ JDK 8
+ Git 2.18 ~    GUI：sourceTree
+ Maven 3.5.4 ~
+ IDE：Idea 2018 ~
+ Mysql：5.7.22 ~
+ Navicat for Mysql / Navicat Premium

#### 类库等其他相关

后端：
+ Springboot 
+ Mybatis
+ Druid
+ Spring Security
+ Springboot-Mail
+ Springboot-Actuator
+ Websocket
+ PageHelper
+ 通用Mapper
+ poi
+ Jackson
+ lombok



前端：

+ freemarker
+ bootstrap、material-kit、jquery、popper
+ stomp

#### MyBatis-Generator / 通用Mapper 的使用

在数据库（领域模型）确定雏形 / 变更之后，使用通用Mapper配合MBG来自动生成entity、mapper、mapper-xml文件，防止人工编写 / 修正时出现错误。
在距离项目交付日期过近时，数据库字段名发生重大变化，使用修改映射的方法临时配合，在之后时间充沛时进行重构。

参考资料：
[springboot集成mybatis及mybatis generator工具使用](https://blog.csdn.net/travellersy/article/details/78620247)
[MyBatis 通用 Mapper4](https://github.com/abel533/Mapper/wiki)

#### 插件（Idea支持）

+ p3c —— 要求通过阿里规约检测，Critical级别及以上必须处理（除非有正当理由，在本项目中只出现了NoOpPasswordEncoder导致Critical，当然，之后可以很方面地切换到BCyptPsswordEncoder等加密器）。Major级别在时间允许的情况尽量处理。
+ lombok —— 要求pojo的字段getter、setter、hashcode、equal、toString方法均使用lombok注解处理；所有类使用日志时必须使用@Slf4j注解。

##  <div id = "directory-structure" />目录结构

#### 代码层

根目录：online.templab.flippedclass （域名反写）
根目录下放置FlippedClassApplication，Application类需要放在最外侧，否则出现找不到页面等异常
原因：spring-boot会自动加载启动类所在包下及其子包下的所有组件

+ common：与业务关联不是紧密联系的，并且复用度高的组件
  + email：发送邮件
  + excel：导入名单
  + multipart：上传 / 下载
  + security：spring-security的配置类
  + websocket：websocket的配置类
+ controller：前端控制器（为freemarker、json api服务）
+ service、service / impl：数据服务层，其实现接口放在service包下的impl包中
+ dao、dao / impl：数据访问层，统领Mapper完成级联更新、删除、增加
+ mapper ：数据访问层，完成该表直接相关的CRUD
+ entity：实体类
+ util：工具类 —— 本项目没有用到
+ constant：常量接口类 —— 本项目没有用到
+ exception：异常类
+ dto：数据传输对象，用于封装多个实体类之间的关系

  
#### 资源目录结构
根目录：resources

+ config：Springboot 配置文件
+ static：静态资源
   + css：
   + js：为本项目编写的js文件
   + fonts：
   + icon：
   + imgs：
   + lib：其他开源类库的js文件
+ mapper：mybatis的映射文件
+ mybatis-generator：MBG（通用Mapper）的配置文件
+ templates：视图模板，存放Freemarker文件
   + admin：管理员相关页面
   + student：学生相关页面
   + teacher：老师相关页面


参考资料：[spring boot 项目开发常用目录结构](https://blog.csdn.net/Auntvt/article/details/80381756)


##  <div id = "task-and-code-standard" /> 任务 & 代码规范 
详见：

[Task & Code Standard](Task & Code Standard.md)


## <div id = "project-summary" />  项目总结 

#### 完成情况
1. 全部功能开发完毕，核心功能点经测试均可行，非核心的功能点测试没有完全覆盖。
2. 在完成初期，每次请求页面的流量过大，导致服务器带宽无法承受。在开启GZip，修正js、css为cdn的资源之后，请求的页面大小从平均1200KB降低到10KB（有图片的页面需要70-100KB）。

#### 缺陷
1. 大部分代码的可读性一般，可复用性较低。
2. 代码的内聚性和耦合度都有待优化。
3. 经阿里规约检测，major级别的提示太多，待解决。