//app.js
App({
  globalData: {
    userInfo: null,
    imgUrl: 'https://sm.ms/v2/api/upload',
    // reqUrl: 'http://203.195.156.107:8080/'
    reqUrl: 'http://localhost:8080/'
  },
  user: {
// avatarUrl:"https://wx.qlogo.cn/mmopen/vi_32/vIPZ4FLhrA7IAme13kU0w92ufsqGLx65SuFDWhQOR5iayPu8P7BYeNPc7rBiaj7C3TuGrHbygCXSXq0Dy6jO6bibA/132",
//     city: "深圳",
//     gender: 2,
//     nickName: "终不似当年",
//     province: "广东",
//     userID: "oJy764gYlGTzVO91zROwCjOqtorY"
  },

  onLaunch: function () {
    wx.getSystemInfo({
      success: e => {
        this.globalData.StatusBar = e.statusBarHeight;
        let custom = wx.getMenuButtonBoundingClientRect();
        this.globalData.Custom = custom;
        this.globalData.CustomBar = custom.bottom + custom.top - e.statusBarHeight;
      }
    })
  }
})