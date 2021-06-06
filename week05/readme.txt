代码路径

2.（必做）写代码实现 Spring Bean 的装配，方式越多越好（XML、Annotation 都可以）, 提交到 GitHub。
code/src/main/java/com/example/code/class9

8.（必做）给前面课程提供的 Student/Klass/School 实现自动配置和 Starter。
schoolStart 中实现了自动配置，使用 maven install打包。然后放到了另外一个工程：code中，在pom中引入,
测试类:code/src/test/java/com/example/code/class10/MySchoolAutoStarterlTest.java

10.（必做）研究一下 JDBC 接口和数据库连接池，掌握它们的设计和用法：
1）使用 JDBC 原生接口，实现数据库的增删改查操作。
2）使用事务，PrepareStatement 方式，批处理方式，改进上述操作。
3）配置 Hikari 连接池，改进上述操作。提交代码到 GitHub。
code/src/main/java/com/example/code/database/