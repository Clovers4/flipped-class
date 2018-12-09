# 翻转课堂管理系统



## 主要功能

#### 老师

1. 共享分组
2. 共享讨论课
2. 讨论课展示打分
3. 讨论课提问打分
4. 课后作业（书面报告）打分

#### 学生

1. 组队管理
2. 讨论课提问
3. 上传、下载发言材料
4. 上传、下载书面报告
5. 查看成绩

#### 管理员

1. 导入老师名单，创建账号



## 项目技术栈

#### 工具

为了避免 / 减少由于工具版本不同可能产生的问题，该项目强制要求使用下列版本。
此外，类库版本以pom.xml文件为标准，这里没有列出。
注：{version} ~ 表示此版本后均可接受。

+ JDK 8
+ Git 2.18 ~    GUI：sourceTree（Mac推荐）/ totoriseGit（win推荐）
+ Maven 3.5.4 ~
+ IDE：Idea 2018 ~
+ Mysql：5.7.22 ~
+ Tomcat 8.5（可能会采用发布成jar包的形式，即直接采用Springboot中的内置版本）
+ Navicat for Mysql / Navicat Premium

#### 类库等其他相关

后端：
+ Springboot （Spring，SpringMVC，MyBatis）
+ druid
+ Spring Security
+ Websocket
前端：
+ html、css、js
+ freemarker
+ bootstrap、material-kit、stomp、jquery、popper

#### MyBatis-Generator的使用

在第一次迭代中，初期建表完毕之后，采用自动生成的方式构建实体类，DAO接口，Mapper文件。之后对自动生成的文件进行修改，添加相关注释。在领域模型变化之后，不再进行自动生成，仅在原有的基础上进行修改。

参考资料：[springboot集成mybatis及mybatis generator工具使用](https://blog.csdn.net/travellersy/article/details/78620247)

#### 插件（Idea支持）

+ p3c
+ lombok

## 目录结构

#### 代码层

根目录：online.templab.flippedclass （域名反写）
根目录下放置FlippedClassApplication，Application类需要放在最外侧，否则出现找不到页面等异常
原因：spring-boot会自动加载启动类所在包下及其子包下的所有组件

+ config：配置信息类
+ controller：前端控制器（为freemarker、restful api服务）
+ domain：实体类
+ dao：数据访问层
+ service：数据服务层，其实现接口放在service包下的impl包中
+ util：工具类
+ constant：常量接口类
+ exception：异常类
+ dto：数据传输对象，用于封装多个实体类之间的关系
+ form：表单包装对象，用于封装表单提交的数据，即FormBean。

  
#### 资源目录结构
根目录：resources

+ config：配置文件（SSM）
+ static：静态资源
   + css：css文件
   + js：js文件
   + image：图片
+ mapper：mybatis的映射文件
+ mybatis-generator：mybatis-generator的配置文件
+ templates：视图模板，存放Freemarker文件
   + fragment：可重用的页面，如：导航栏，页脚，信息弹框等
   + admin：管理员相关页面
   + user：登录等通用的用户页面
   + student：学生相关页面
   + teacher：老师相关页面
   + test：测试用临时页面



参考资料：[spring boot 项目开发常用目录结构](https://blog.csdn.net/Auntvt/article/details/80381756)



## 代码规范 

#### 通用
1. 强制要求使用P3C插件来规范代码，并通读阿里手册
2. 类，方法，字段均需要写 javadoc 注释，其中类注释需要带上 author 信息
3. 行注释在双划线之后空一格再写，更加美观。如：// 双划线和文字中间有一个空格
4. 未做完的地方写 // TODO：描述，以防缺漏
5. 每个实现类以及DAO接口（MyBatis实现）都要写相应的单元测试

#### Controller层
1. 使用@RequestMapping注解的方法，返回值使用String，即传入参数Model。不使用ModelAndView，因为直接将视图名写到最后一行 return url； 中，更加清晰。

#### Service层
1. 抛出异常给Controller，不要包装成一个含有成功失败标志的Result类中进行返回。


#### DAO层
1. 接口前加上@Mapper和@Component
@Mapper——否则，需要在Application类前加@MapperScan
@Component——为了能够使用@Autowired
2. 增删改分别使用insert、delete、update作为前缀；获取单个用get，多个用list作为前缀。带条件再加上ByXXX，如getById，listByName。获得统计值用count作为前缀。
3. 以增删改查的顺序编写Mapper的XML文件以及DAO接口文件，方便 code review。两个文件的方法顺序应保持一致性。

#### Domain层
1. 在Mybatis-Generator自动生成的基础上修正注释
2. 实体类实现Serializable，serialVersionUID 均用 1L 作为值

#### 单元测试

1.  后端数据库标准未定，单元测试只需保证测试好其方法能否正常调用，达到预期效果

#### 示例

```java
/**
 * 处理所有未捕捉的异常，转发到错误显示页面
 *
 * @author W.K
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 这是一个默认的处理方案，TODO: 之后可能新增一些对应具体原因的转发页面，如：404页面
     * 当出现问题时，转发到错误显示页面，显示具体原因，以及出现问题的访问url
     *
     * @param request
     * @param e
     * @return error页面的名称
     */
    @ExceptionHandler(value = Exception.class)
    public String defaultErrorHandler(Model model, HttpServletRequest request, Exception e) {
        model.addAttribute("exception", e);
        model.addAttribute("url", request.getRequestURL());
        return "error";
    }
}
```



