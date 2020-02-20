  //index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo')
  },
  //点击微信头像跳转到登录日志，未使用
  bindViewTap: function() {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },

// 获取用户微信信息，暂不使用
  onLoad: function () {
    if (app.globalData.userInfo) {
      this.setData({
        userInfo: app.globalData.userInfo,
        hasUserInfo: true
      })
    } else if (this.data.canIUse){
      // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
      // 所以此处加入 callback 以防止这种情况
      app.userInfoReadyCallback = res => {
        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true
        })
      }
    } else {
      // 在没有 open-type=getUserInfo 版本的兼容处理
      wx.getUserInfo({
        success: res => {
          app.globalData.userInfo = res.userInfo
          this.setData({
            userInfo: res.userInfo,
            hasUserInfo: true
          })
        }
      })
    }
  },

// 获取用户微信信息，暂不使用
  getUserInfo: function(e) {
    console.log(e)
    app.globalData.userInfo = e.detail.userInfo
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true
    })
  },
  //获取手机号
  getPhoneNumber: function (e) {
    console.log(`是否成功调用${e.detail.errMsg}`);
    console.log(`加密算法的初始向量:${e.detail.iv}`);
    console.log(`包括敏感数据在内的完整用户信息的加密数据:${e.detail.encryptedData}`);
  } ,

//登录功能
login:function(e){
  // var that = this;
  // wx.request({
  //   url: app.globalData.reqUrl+'wxLogin',
  //   method: 'POST',
  //   data: JSON.stringify(e.detail.value),
  //   header: { 'Content-Type': 'application/json' },
  //   success: function (res) {
  //     var theUser = res.data;
  //     console.log(res.data);
  //     //判断用户是否被封禁
  //     if (theUser.status == "stop") {
  //       wx.showModal({
  //         title: '登录异常',
  //         content: '您的账户被封禁或者不存在，请联系管理员！',
  //         showCancel: "false"
  //       })
  //     }
  //     else if (theUser == ''){
  //       wx.showModal({
  //         title: '登录失败！',
  //         content: '账号或密码输入有误',
  //         showCancel: "false"
  //       })
  //     }
  //     else if(theUser!= null&&theUser!=''){
  //       app.user.userID = theUser.userID;
  //       app.user.password = theUser.password;
  //       app.user.userName = theUser.name;
  //       app.user.hometown = theUser.hometown;
  //       app.user.avatar = theUser.avatar;
  //       // app.user = theUser;
  //       wx.navigateTo({
  //         url: "../list/list",
  //       })
  //     }
  //   }
  // })
  wx.login({
    success: function (res) {
      if(res.code){
        console.log(res.code);
      }
      else{
        console.log("获取code错误。");
      }
    }
  })
}


})
        