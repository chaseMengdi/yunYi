//app.js
App({
  globalData: {
    userInfo: null,
    // reqUrl: 'http://203.195.156.107:8080/'
    reqUrl: 'http://localhost:8080/'
  },
  user: {
    userID: null,
    province: null,
    city: null,
    nickName: null,
    gender: null,
    avatarUrl: null
  },

  onLaunch: function () {
    wx.checkSession({
      success() {
        console.log("yidenglu")
      },
      fail() {
        wx.navigateTo({
          url: 'index',
        })
      }
    })
    // // 登录
    // wx.login({
    //   success: res => {
    //     // 发送 res.code 到后台换取 openId, sessionKey, unionId
    //   }
    // })
  }
})