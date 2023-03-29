##nacos 安装注意点
- 1 准备环境  nacos2.0.4 安装包（注意与pom 引入的版本包对应，尽量用2.1 版本以上）， mysql数据库环境
- 2 导入数据库信息，新建库名：nacos， 导入表信息 nacos-mysql.sql
- 3 修改配置 conf/application.properties 
   - 1、开启自定义 mysql 持久化，
      - 1、 #打开注释即可 默认nacos内置的mysql
        `spring.datasource.platform=mysql `
      - 2、可能错误 mysql： 
         - 2-1 No DataSource set：mysql密码问题
         ```
           #mysql_native_password作为MySQL 5.6/5.7的默认密码插件，优点是支持challenge-response（挑战应答方式），这是非常快的验证机制，
           #无需在网络中发送实际密码，并且不需要加密的连接，但是不够安全。而使用caching_sha2_password插件的客户端，连接到服务器时，不会使用明文密码，
           #密码传输是如何进行的取决于是否使用安全连接或RSA对密码加密，更加安全可靠。
             
           alter user bisal@'%' identified with mysql_native_password by 'bisal';
           flush privileges;
         ```
        
- 4  进入 bin 目录，启动 
    ```
    # standalone 单机启动;  cluster 集群启动
    sh startup.sh -m standalone;
    ```

- 5 浏览器访问 localhost:8848/nacos 用户名/密码: nacos/nacos

**常见错误**
+ 1、 failed to req API:/nacos/v1/ns/instance after
  ```
  eg： shell  [[: not found。 
  解决： 启动时按 bash startup.sh -m standalone 启动，sh 与 bash 有区别 
          
  ```