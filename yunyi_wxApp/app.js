//app.js
App({
  globalData: {
    userInfo: null,
    imageUrl: 'http://203.195.156.107:8080/',
    // reqUrl: 'http://203.195.156.107:8080/'
    reqUrl: 'http://localhost:8080/'
  },
  user: {
// avatarUrl:"http://203.195.156.107:8080/upload/1583211714460wx3c26f5c92a626a3f.o6zAJs8pxpkTZgP34-rJUb-gdBLs.tUdN39vMJi7P0b026daa4d7fbdfe52615a1a9e1e376d.jpg",
//     city: "深圳",
//     gender: 2,
//     nickName: "盛夏的清凉",
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