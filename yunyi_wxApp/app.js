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