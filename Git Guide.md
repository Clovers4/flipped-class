# Git Guide


## 多人开发步骤

#### 准备步骤
1. git clone git@github.com:future-oad/flipped-class.git
2. git checkout develop

#### 平时的开发流程

以下步骤，commit、checkout、创建、删除分支这些命令可以在GUI（如sourceTree中完成，效率更高），其他命令（如rebase、merge）均在命令行中进行，因为这样可控性更高，而且更清晰，熟练了之后效率更高。

1. 打开git bash
2. git checkout -b feature-xxx develop    # 从develop分支新建并检出feature分支
3. 在idea里进行开发，完成自己的任务，不断地commit   # 请继续参考下文的 commit log 标准
4. git checkout develop    # 切换回develop分支
5. git pull origin develop    # 更新远端代码，看develop分支是否有更新
若没有更新，则一定没有冲突，因此跳至第11步
若有更新，可能会发生冲突。（在多人修改同个文件时会冲突）
6. git checkout feature-xxx    # 切换回feature分支
7. git rebase develop    # 合并develop分支到feature分支，并解决冲突。
若无冲突，则跳到第10步；
若有冲突，命令行中会提示有哪些文件冲突了，然后回到idea，会发现有一些文件的名字变红了，这时候进入文件，解决冲突。
8. git add .    # 解决完冲突之后执行add操作
9. git rebase --continue    # 继续刚才的rebase操作，若仍有冲突，则重复第8-9步
10. git checkout develop    # 切换回develop分支
11. git merge --no-ff feature-xxx   # 合并feature分支到develop分支（无冲突）
此时在命令行中进行时，会出现一个提示你输入Commit log界面。
参考vim操作：按i，进行输入；按esc，退出输入模式；按：，进入命令模式，输入wq，回车，完成commit。
12. git push origin develop   # 推送develop分支到远端
13. git branch -d feature-xxx   # 删除原来进行的feature分支
14. 等待下一次分配任务，重新回到第1步


参考资料：
[图文详解如何利用Git+Github进行团队协作开发](https://www.cnblogs.com/yhaing/p/8473746.html)

## Github PR流程
1. fork 组长的repo。
2. clone 自己的repo。git clone git@github.com:xxx/xxx.git
3. remote 添加组长的repo。git remote add upstream https://github.com/xxx/xxx.git
4. 这一步骤与上面的多人开发步骤相同，只不过origin其实是自己的仓库。
5. git pull upstream   // 这里可能组长的repo有更新，因此可能有冲突等等，参考上面的方式解决
6. 去Github创建PR

参考资料：
[在GitHub创建和处理PR](https://boxueio.com/series/git-essential/ebook/459)
[向github的开源项目提交PR的步骤](https://blog.csdn.net/u010857876/article/details/79035876)
[git学习--GitHub上如何进行PR(Pull Request)操作](https://blog.csdn.net/qq_33429968/article/details/62219783)

## Commit log 标准

#### 要求
要求大家多次commit，不要修改很多文件然后一次commit

#### 实例
1. [feat] 新增了用户登录页面
2. [fix] 修复了user页面的url跳转不正确的bug

#### 规范
[type] description

注意 description 与 [type] 之间有一个空格
type：commit的类型

+ feat: 新功能
+ fix: 修复问题
+ docs: 修改文档
+ refactor: 重构代码 
+ test: 增加修改测试用例

参考资料：
[Git Commit Log的小型团队最佳实践](https://segmentfault.com/a/1190000015434246)

## 未上传的文件

1. src/main/resources/mybatis-generator/init.propeties未上传。示例如下：
```propeties
# 根据数据库中的表生成对应的实体类、mapper、xml文件
# project : 实体类 和 Mapper类的位置 ; resources: xml文件的位置
project=D:/mabatis-generator/java
resources=D:/mabatis-generator/resources
#project =src/main/java
#resources=src/main/resources
#
# jdbc
jdbc_driver=com.mysql.jdbc.Driver
jdbc_url=jdbc:mysql://127.0.0.1:3306/flipped_class?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8&useSSL=false
jdbc_user=你的账号
jdbc_password=你的密码
```
2. src/main/resources/config/application-*（dev、pro、test）未上传。示例如下：
```propeties
#
server.port=8080
#
# 数据源配置 - Druid采用默认配置
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/flipped_class?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8&useSSL=false
spring.datasource.username=你的账号
spring.datasource.password=你的密码
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
#
# mail
spring.mail.host=smtp.163.com
spring.mail.username=你的邮箱
spring.mail.password=你的授权码
spring.mail.default-encoding=UTF-8
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.starttls.required=true
#
# actuator TODO: 和spring security 配合还需要一些配置(可能是类),CORS可能要配置
management.server.port=9999
management.endpoints.web.exposure.include=*
#
# mybatis test
logging.level.online.templab.flippedclass.mapper=debug
```