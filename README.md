# baseline-checker

针对Windows Server 2008 R2的基线安全检测系统，后台使用SpringBoot搭建，客户端用python编写。

## java-server

后台管理系统使用SpringBoot框架搭建，整合thymeleaf，实现前后端分离；使用Shiro实现RBAC权限管理；使用Mybatis作为持久层框架，使用pageHelper实现分页；前端使用echarts组件进行可视化展示；使用定时任务，定时监听，使用多线程接收不同客户端的数据，将数据处理后存入数据库。

## py-client

客户端的脚本版本；通过WMI模块、安全配置文件、注册表、运行cmd等收集信息，并与数据库中的规则对比，进行主机的基线核查，使用socket将数据处理并封装后发送给服务端。

## py-client-gui

客户端的gui版本。

