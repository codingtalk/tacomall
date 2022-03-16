### 项目结构

~~~
tacomall-springboot                             项目
├─api                                           接口模块
│  ├─admin                                      平台管理后台
│  ├─merchant                                   商家管理后台
│  ├─open                                       开放接口
│  ├─portal                                     小程序接口
├─tacomall-job                                  任务调度
│  ├─admin                                      调度接口
│  ├─executor                                   调度执行
├─common                                        公共依赖
│  ├─config                                     全局spring bean
│  ├─entity                                     实体类
│  ├─enumeration                                枚举类
│  ├─mapper                                     mapper层
│  ├─util                                       公共方法
├─generator                                     代码生成
├─pom.xml                                       依赖构建配置
~~~

## 上手指南

我们希望开源的项目能够让每一个人都能够一看就懂，轻松上手，但这并不意味者我们不需要做任何东西，相反，在正式运行项目前，你必须完成以下步骤。

### 环境要求

为了避免运行开发中遇到一些意想不到的问题，我们推荐你安装如下环境。

1. Git
2. JDK1.8+
3. Maven3.5+
4. Mysql5.7+
5. Idea2019.3.3
6. Postman
7. mysql
8. redis
### 安装步骤

通过以下步骤，你将很快看见项目运行起来了！

1. 克隆项目

```
git clone https://gitee.com/running-cat/tacomall-springboot.git
```
2. 配置idea

为了更好的运行项目，我们需要将idea的jdk环境路径配置为我们上面早已安装好的jdk1.8+,同时配置idea默认maven路径为我们上述安装好的maven并配置好国内源。

3. 导入idea

打开idea导入克隆下来的项目

4. maven依赖安装

右键根目录的pom.xml，maven->reimport

5. 导入数据

打开我们的数据库设计工具（pdman）[下载地址](https://gitee.com/robergroup/pdman?_from=gitee_search)。

```
导入项目（_doc/pdman/tacomall.pdman.json）->配置数据库->运行导入mysql
```

初始化了数据库后，为了有数据展示，我们提供了一个简易的[京东爬虫程序](https://gitee.com/running-cat/tacomall-springboot/tree/master/_doc/jd)用于初始化数据库数据

6. 修改数据库配置

在每个接口模块项目中（src/main/resources/application-dev.yml）修改相应的数据库配置。

7. 运行服务

接口有多个模块，彼此相互独立，我们只需要像普通springboot项目一样启动。

## 测试

看到这里，我们认为你已经正确配置启动项目了，接下来你将通过postman看到实际效果。

```
http://localhost:4000/portal/member/wxMalogin
```

##部署

我们提供了docker容器化部署方案，详情请查看[部署](https://gitee.com/running-cat/tacomall-springboot/blob/master/LICENSE)，但并不意味着你不得不选择docker部署，你仍然可以自由选择你喜欢的部署方式进行部署。

## 使用的框架

项目中使用到以下框架（不限于）

| 框架                                   | 说明                     |
| -------------------------------------- | ------------------------ |
| springboot                             | 提供web服务功能          |
| mybatis-plus                           | 提供简化的数据库操作接口 |
| dynamic-datasource-spring-boot-starter | 多数据源解决方案         |
| springfox-swagger2                     | 接口文档                 |
| weixin-java-miniapp                    | 小程序sdk                |

