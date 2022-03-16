## 项目结构

通过项目结构，你将清楚明白你即将入手的是一个怎么样的项目，你可能需要什么，如何快速的把它变成自己的产品。

~~~
tacomall-uniapp                     项目
├─api                               接口模块
├─common                            公共less,js
├─hybrid                            H5页面
├─libs                              js库
├─model                             数据模型
├─pages                             页面
│  ├─account                        个人账号
│  ├─cart                           购物车
│  ├─category                       分类
│  ├─center                         个人中心
│  ├─checkout                       订单确认
│  ├─evaluate                       商品评价
│  ├─goods                          商品详情
│  ├─h5                             webview页面
│  ├─index                          首页
│  ├─login                          登录
│  ├─order                          订单列表
│  ├─search                         搜索
│  ├─transaction                    订单完成
├─platforms                         跨平台页面，样式
├─plugins                           vue插件
├─service                           开放平台服务
├─static                            静态资源
├─store                             全局状态树
├─utils                             工具库
├─wxcomponents                      微信UI库
├─App.vue                           vue根组件
├─config.js                         全局配置
├─main.js                           js入口
├─manifest.json                     uniapp配置
~~~

## 上手指南

我们希望开源的项目能够让每一个人都能够一看就懂，轻松上手，但这并不意味者我们不需要做任何东西，相反，在正式运行项目前，你必须完成以下步骤。

### 环境要求

为了避免运行开发中遇到一些意想不到的问题，我们推荐你安装如下环境。

1. Git
2. Node9+
3. hbuilder
4. 微信开发者工具

### 安装步骤

通过以下步骤，你将很快看见项目运行起来了！

1. 克隆项目

```
git clone https://gitee.com/running-cat/tacomall-uniapp.git
```
2. 配置hbuilder

项目依赖于hbuilderx进行跨平台代码运行编译，在开发前，我们需要确保配置好hbuilder编辑器中的**Node路径**和**微信开发者工具路径和端口**

3. 导入项目

打开hbuilderx，选择导入该项目

4. 更换微信小程序appid

想要开发微信小程序，我们需要拥有属于自己的微信小程序appid。当然，我们可以通过微信小程序测试号获取。

```
manifest.json > mp-weixin > appid
```

5. 安装js依赖

由于项目依赖了第三方库，运行像目前务必执行

```
npm install 
```

## 测试

看到这里，我们认为你已经正确配置启动项目了，接下来你将通过微信开发者工具看到实际效果。

```
运行 > 运行到小程序模拟器 > 微信小程序
```

## 使用的框架

项目中使用到以下框架（不限于）

| 框架   | 说明             |
| ------ | ---------------- |
| vue    | 代码编写基础     |
| vuex   | 提供页面数据共享 |
| uniapp | 跨平台解决方案   |
| loadsh | js工具库         |
| dayjs  | js时间库         |
