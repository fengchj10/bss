
  
  **技术栈**
  
  * 后端： SpringBoot2.x + Mybatis
  * 前端： Vue.JS2.x + ElementUI
  
  **测试环境**
  
  * IDEA + SpringBoot-2.0.5
  
  **启动说明**
  
  * 本地运行
  
    * 启动前，配置好 [application.yml](bss\src\main\resources\config\application.yml) 中连接数据库的用户名和密码。
  
    *  启动前，需要初始化数据库，建表SQL语句放在：[/db/sys_schema.sql](bss\sys_schema.sql)。具体的建表和建库语句请仔细看SQL文件。
  
    *  本地运行时候可以打开配置文件中的
      ```
      .
      ├──
      ├──#server:
      ├──#  port: 8080
      ├──#  servlet:
      ├──#    context-path: /bss
       ```
    * PS：本地运行可以打包成jar，也可以打包成war。
    
    * 配置完成后，运行位于 `src/main/com/angevin/`下的SpringbootApplication中的main方法，访问 `http://localhost:8080/bss` 进行API测试。
  
    *  默认用户：admin，PWD：Nqsky1130
  
  * 部署到mdm的环境
    * 注释掉tomcat的配置信息
       ```
        .
        ├──
        ├──#server:
        ├──#  port: 8080
        ├──#  servlet:
        ├──#    context-path: /bss
         ```
    *   连接mdm数据库，执行数据库初始化脚本，建表SQL语句放在：[/db/sys_schema.sql](bss\sys_schema.sql)。
    
    *   修改配置 [application.yml](bss\src\main\resources\config\application.yml) 中连接数据库的用户名和密码。
    
    *   打包成war包放到tomcat下的webapp文件夹(目前mdm环境的tomcat有热部署功能，不用重启tomcat，会自动部署，看日志)
    
    *   配置nginx配置文件: 
    
        * 配置http:
            ```
            server{
                listen 80;
                ......
                #添加一下配置
                 location /bss{
                      proxy_pass http://127.0.0.1:8080/bss;
                 }  
            }
            ```
        *  配置https:
         
            ```
            server{
                listen 443;
                ......
                #添加一下配置
                 location /bss{
                      proxy_pass http://127.0.0.1:8080/bss;
                 }  
            }
            ```
    * 重启nginx 或者 重新载入nginx配置信息 
        `/usr/local/nginx/sbin/nginx -s reload`        