# Task & Code Standard


## 开发任务

#### Entity
+ Getter、Setter、ToString、HashCode、Equal方法使用lombok注解
+ 基于OOP思想，加入一些关联的类。如：`List<Student> students;` 

#### Mapper
+ 在接口上加上@Component注解
+ 在接口上加上增加自定义方法
+ 在XML上加上对应的sql语句

#### Dao
+ 在接口上增加方法
+ 在实现上进行级联CRUD操作

## 代码规范 

#### 通用
1. 强制要求使用P3C插件来规范代码，并通读阿里手册
2. 类，方法，字段均需要写 javadoc 注释，每个类需要带上@author信息
3. 行注释在双划线之后空一格再写，更加美观。如：// 双划线和文字中间有一个空格
4. 未做完的地方写 `// TODO [人名]：描述。`  以防缺漏

#### Controller层
1. 使用@RequestMapping注解的方法，返回值使用String，即传入参数Model。不使用ModelAndView，因为直接将视图名写到最后一行 return url； 中，更加清晰。

#### Service层
1. 抛出异常给Controller，不要包装成一个含有成功失败标志的Result类中进行返回。

#### DAO层
1. 增删改分别使用insert、delete、update作为前缀；获取单个用get，多个用list作为前缀。带条件再加上ByXXX，如getById，listByName。获得统计值用count作为前缀。

#### Mapper层
2. 以增删改查的顺序编写Mapper的XML文件以及DAO接口文件，方便 code review。两个文件的方法顺序应保持一致性。

#### Domain层
1. 遵循通用即可。

#### 单元测试
1. 原则上每个实现类都要写相应的单元测试，但本项目时间紧张，后期只需保证功能正常运行即可。

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
     * 这是一个默认的处理方案，TODO[wk]: 之后可能新增一些对应具体原因的转发页面，如：404页面
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

