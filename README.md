`mybatis` 官方提供了 MyBatis Generator ，可以通过 xml 配置文件的方式使用，例如自己写调用脚本，或者使用 mvn 插件的方式，其实实现起来还是很简单的。虽然简单，但还是不够简单，懒嘛，这不就实现了一个更简单的生成方式，通过 web 页面的方式，填写几个关键的配置参数，选好要生成的数据库表即可。

## 可配置的参数有如下几个

targetRuntime ：MyBatis3、MyBatis3Simple、Ibatis2Java2、Ibatis2Java5，默认为 MyBatis3 

是否取消注释：生成的文件中默认会有注释内容，可以选择是否取消。

targetProject（文件生成目录）：文件最后保存的目录，选择一个本地磁盘上的目录位置。

实体类包名：实体对象的包名。

mapper.xml文件所在目录：xml 文件所在的目录

mapper接口类包名：mapper 接口类的包名

数据库驱动：目前只支持 mysql

数据库连接字符串、数据库用户、数据库用户密码：数据库相关配置

## 启动方式

#### 方式1：

直接下载源码，然后运行 `BuilderApplication` 文件，或者使用 mvn 的 `spring-boot:run` 方式运行

#### 方式2：

下载  [kite-mybatis-builder.jar](https://github.com/huzhicheng/kite-mybatis-builder/releases/download/v1.0/kite-mybats-builder.jar), 运行命令 `java -jar -Dserver.port=[port] kite-mybatis-builder.jar` 或者 `java -jar kite-mybatis-builder.jar ` 默认在 9090 端口运行



## 使用方式

如果运行在默认的 9090 端口，打开浏览器访问 http://localhost:9090 。

1. 默认打开之后，点击“新建项目”。

![mybatis-1](http://hexo.moonkite.cn/mybatis/mybatis-1.png)

2. 在弹出的项目配置界面，填写上面提到的配置参数

   ![mybatis-2](http://hexo.moonkite.cn/mybatis/mybatis-2.png)

3. 选择要生成的表，并可在后面配置实体名称，默认规则是各单词首字母大写。

   ![mybatis-3](http://hexo.moonkite.cn/mybatis/mybatis-3.png)

4. 点击生成按钮，会根据生成结果提示成功或失败。

5. 生成过的项目会在首页列出来，下次如果还需要生成此数据库的表，可以在之前的项目中重新配置选择即可。

   ![mybatis-4](http://hexo.moonkite.cn/mybatis/mybatis-4.png)

   
