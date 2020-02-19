# yunYi
# 项目介绍
<br>云忆，帮助用户分享家乡与寻找老乡
<br>
<br>小程序端供普通用户使用，可以注册登录、编辑个人信息、寻找老乡、编辑个人主页。
<br>    个人信息维护	用户注册登录，修改个人相关信息
<br>	编辑个人主页	用户为个人主页添加图文、选择模板，以及生成二维码分享
<br>	浏览他人主页	用户通过二维码或者点击进入他人主页，并进行点赞评论投诉相关操作
<br>	找老乡	        用户寻找家乡和自己相同的用户

<br>网页端供管理员使用，查看用户各项信息的统计情况，并对用户及其个人主页做出相关操作。
<br>    登录系统	管理员通过用户名密码登录管理端
<br>	用户管理	管理员搜索、封禁和解封违规用户的帐号
<br>	模板列表	管理员添加和删除用户个人主页的模板
<br>	点赞统计	管理员查看每个用户的点赞信息
<br>	投诉统计	管理员查看被投诉用户的相关信息
<br>	登录日志	管理员查看所有管理员在管理端的登录日志

# 项目结构
## 1、管理端后台
\yunyi\src\main\java\com\memory\yunyi
- controller 后台业务逻辑控制器
- entity 业务实体类定义
- repository 数据访问层，定义数据库操作与SQL语句
- service 接口定义与数据服务层（应该分开的，这里偷了个懒），定义基本操作的接口与实现
## 2、管理端页面
\yunyi\src\main\resources
- static 静态资源
- templates 动态网页
- application.yml JPA与数据库配置参数
## 3、小程序后台
\yunyi\src\main\java\com\memory\yunyi\wxController
- wxCommentController.java 控制评论的增加与显示
- wxLikeController.java 控制数据库点赞数量变化，老乡查询返回结果
- wxUpgController.java 获取用户主页内容，保存主页内容，设定模板号码
- wxUserController.java 获取用户列表，用户注册登录，更新用户个人信息
## 4、小程序页面与业务逻辑
\yunyi_wxApp
- imgs 小程序所需图片
- pages 小程序的各个子页面定义与业务逻辑实现
<br> choose 选择模板页面
<br> homepage 用户个人主页文件夹
<br> index 小程序初始化页面
<br> list 登陆成功后跳转到的用户列表页面
<br> logs 微信自带
<br> model-1 抽象后的模板1
<br> model-2 抽象后的模板2
<br> model3 抽象后的模板3
<br> register 用户注册页面
<br> login 用户更新个人信息页面
- appjs.js 获取微信用户信息，定义全局数据流转
- app.json 声明各个子页面，定义主界面样式
- app.wxss 定义全局具体样式，与子页面样式冲突时，以子页面为准
- project.config 项目配置文件



