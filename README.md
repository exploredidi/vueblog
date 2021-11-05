扫码加微信（微信ID：**woshi1611954377**），备注Vueblog

<img src="F:\QQ数据\MobileFile\mmqrcode1630406831515.png" alt="mmqrcode1630406831515" style="zoom: 50%;" />

Vueblog是一个开源博客管理平台，采用Vue+SpringBoot开发，是本人的第一个开源项目，也是本人第二个动手完成的全栈项目。

# 项目效果图

## 登陆页面

## ![image-20210831194948707](C:\Users\MagicBook\AppData\Roaming\Typora\typora-user-images\image-20210831194948707.png)文章列表

## ![image-20210831195028713](C:\Users\MagicBook\AppData\Roaming\Typora\typora-user-images\image-20210831195028713.png)发表文章

![image-20210831195123807](C:\Users\MagicBook\AppData\Roaming\Typora\typora-user-images\image-20210831195123807.png)



# 技术栈

## 后端技术栈

后端主要采用了：

1.SpringBoot
2.Shiro+jwt
3.MyBatis
4.MySQL+Redis
5.rabbitmq
6.redis

## 前端技术栈

前端主要采用了：

1.Vue
2.axios
3.ElementUI
4.mavon-editor
5.vue-router

还有其他一些琐碎的技术我就不在这里一一列举了。

# 快速运行

1.克隆本项目到本地

```
git@github.com:exploredidi/vueblog.git
```

2.找到All.sql文件在数据库中执行

![image-20210831195314219](C:\Users\MagicBook\AppData\Roaming\Typora\typora-user-images\image-20210831195314219.png)

3.根据自己本地情况修改数据库配置，数据库配置在SpringBoot后端部分的application.yml中，你应该去自定义一下，否则会运行失败。
4.在IntelliJ IDEA中运行vueblog项目，默认端口  8088

**OK，至此，后台服务端就启动成功了**

5.进入到vueblog-vue目录中，

![image-20210831195446601](C:\Users\MagicBook\AppData\Roaming\Typora\typora-user-images\image-20210831195446601.png)

在命令行依次输入如下命令：

```
# 安装依赖
npm install

# 在 localhost:8080 启动项目
npm run serve
```

由于我在vueblog-vue项目中已经配置了端口转发，将数据转发到SpringBoot上，因此项目启动之后，在**浏览器中输入`http://localhost:8088`就可以访问我们的前端项目**了，所有的请求通过端口转发将数据传到SpringBoot中（注意此时不要关闭SpringBoot项目）。

6.最后可以用WebStorm等工具打开vueblog项目，继续开发，开发完成后，当项目要上线时，依然进入到vueblog目录，然后执行如下命令：

```
npm run build
```

该命令执行成功之后，vueblog目录下生成一个dist文件夹，将该文件夹中的两个文件static和index.html拷贝到SpringBoot项目中resources/static/目录下，然后就可以像第4步那样直接访问了。

**步骤5中需要大家对NPM等有一定的使用经验，不熟悉的小伙伴可以先自行搜索学习下，推荐[Vue官方教程](https://cn.vuejs.org/v2/guide/)。**

# 项目依赖

自行查看技术栈

# License

WenJianC

**欢迎小伙伴们star、fork。**

## 其他



![img](F:\QQ数据\MobileFile\mmqrcode1630406831515.png)