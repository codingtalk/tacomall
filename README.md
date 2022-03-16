<p align="center">
    <img height="120px" src="http://img.codingtalk.cn/a6Yzd6a1636188780637"/>
</p>
<p align="center">[POWERED BY 码上talk|RC]</p>
<p align="center">
	<a target="_blank" href="https://gitee.com/running-cat/tacomall/blob/master/LICENSE">
		<img src="https://img.shields.io/badge/License-Apache2.0-FA8072.svg" alt="License-Apache2.0"/>
	</a>
    <a target="_blank" href="https://spring.io/projects/spring-boot">
		<img src="https://img.shields.io/badge/springboot-2.4.2-green" alt="springboot-2.4.2"/>
	</a>
    <a target="_blank" href="https://www.oracle.com/java/technologies/downloads/">
		<img src="https://img.shields.io/badge/JDK->=1.8-FF6347" alt="JDK->=1.8"/>
	</a>
     <a target="_blank" href="https://cn.vuejs.org/">
		<img src="https://img.shields.io/badge/vuejs-2.6.10-3CB371" alt="vuejs-2.6.10"/>
	</a>
    <a target="_blank" href="https://uniapp.dcloud.io/">
		<img src="https://img.shields.io/badge/uniapp-latest-008000" alt="uniapp-latest"/>
	</a>
    <a target="_blank" href="https://www.docker.com/">
		<img src="https://img.shields.io/badge/docker-latest-1E90FF" alt="docker-latest"/>
	</a>
</p>
<p align="center">
🌮塔可商城, 一个基于springboot+uniapp+vue3技术栈开发的开源跨平台小程序、管理后台，后端服务的项目，它内置提供了会员分销， 区域代理， 商品零售等功能的新零售电商系统。强大弹性的架构设计，简洁的代码，最新的技术栈，全方面适合不同需求的前端，后端，架构的同学，同时更是企业开发需求的不二选择。开源不易，你的星星是我们持续更新的最大动力！🥳
</p>

```
 __  ___  ___      ___      ___      _   __      ___     // //  
  / /   //   ) ) //   ) ) //   ) ) // ) )  ) ) //   ) ) // //   
 / /   //   / / //       //   / / // / /  / / //   / / // //    
/ /   ((___( ( ((____   ((___/ / // / /  / / ((___( ( // //     

如果项目对您有帮助，不要忘记给项目点个赞fork，你的支持是我们持续更新的最大动力:)

```
### 🐱在线演示
<table border="0">
    <tr>
        <td>微信小程序（微信扫一扫）</td>
        <td>链接</td>
    </tr>
    <tr>
        <td><img title="码上talk" src="https://img.codingtalk.cn/3Y4h5Zz1608078777538" height="150" width="150"/></td>
        <td>
            <p>----->🖥️<a target="_blank" href="http://admin.tacomall.codingtalk.cn">管理后台</a></p>
            <p>----->🧸<a target="_blank" href="http://admin.tacomall.codingtalk.cn">店铺系统</a></p>
            <p>----->🗳️<a target="_blank" href="http://admin.tacomall.codingtalk.cn">收银系统</a></p>
        </td>
    </tr>
</table>

### 🐶社区帮助
<table border="0">
    <tr>
        <td>tacomall QQ交流群</td>
        <td>商务合作</td>
    </tr>
    <tr>
        <td><img title="tacomall QQ交流群" src="https://img.codingtalk.cn/haPkJxc1585147447571" height="200" width="220"/></td>
        <td><img title="码上talk|RC" src="https://img.codingtalk.cn/meJzskn1584540440273" height="200" width="220"/></td>
    </tr>
</table>

### 🐢项目结构
通过项目结构，你将清楚明白你即将入手的是一个怎么样的项目，你可能需要什么，如何快速的把它变成自己的产品。
~~~
塔可商城 TACOMALL      项目
├─── _deploy           部署环境依赖，文件。
├─── _docker_config    docker启动配置文件（redis, rabiitmq为项目必须启动项， mysql可以自行决定）。
├─── tacomall-admin    基于vuejs开发， 管理后台，提供商品管理，会员管理，权限管理，订单管理等。
├─── tacomall-api      基于springboot多模块设计，提供各前端应用接口服务。
├─── taomall-cashier   基于flutter2.0+vue3_vite打包的跨平台收银安装应用，已打通微信支付，建行被扫支付，提供库存出入，盘点，营销活动，代金券设置。
├─── tacomall-ma       uniapp开发的小程序商城。
~~~

### 🙈程序预览
- 以下为程序在手机端的运行预览图（取至部分功能页面）

<table border="0">
    <tr>
        <td>首页</td>
        <td>商品分类</td>
        <td>购物车</td>
        <td>我的</td>
        <td>商品详情</td>
    </tr>
    <tr>
        <td><img title="码上talk" src="https://img.codingtalk.cn/WHjZJJh1586355355657" height="340" width="170"/></td>
        <td><img title="码上talk" src="https://img.codingtalk.cn/MmNmCR21586355392776" height="340" width="170"/></td>
        <td><img title="码上talk" src="https://img.codingtalk.cn/25NbeMD1586355410426" height="340" width="170"/></td>
        <td><img title="码上talk" src="https://img.codingtalk.cn/nrnzKwr1586355431298" height="340" width="170"/></td>
        <td><img title="码上talk" src="https://img.codingtalk.cn/r8yRj251586355442801" height="340" width="170"/></td>
    </tr>
</table>

- 管理后台页面
  
### 🐼快速启动
我们为每个子模块编写了如何快速搭建开发环境，同时将每个项目顺利启动的README.md,以下是说明的快速通道。
- [小程序](https://gitee.com/running-cat/tacomall/blob/master/tacomall-ma/README.md)
- [管理后台]()
- [收银系统]()
- [后台服务](https://gitee.com/running-cat/tacomall/blob/master/tacomall-api/README.md)

### 🦫版权须知

1. 允许个人学习研究使用，支持二次开发，允许商业用途（仅限自运营）。
2. 允许商业用途，但仅限自运营，如果商用必须保留版权信息，望自觉遵守。
3. 不允许对程序代码以任何形式任何目的的再发行或出售，否则将追究侵权者法律责任。


本项目包含的第三方源码和二进制文件之版权信息另行标注。

版权所有Copyright © 2017-2021 By 塔可商城 (https://tacomall.codingtalk.cn) All rights reserved。