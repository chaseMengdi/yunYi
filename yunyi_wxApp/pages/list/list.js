// pages/list/list.js

const app = getApp()


Page({

  /**
   * 页面的初始数据
   */
  data: {
    user: app.user,
    userInfo: app.globalData.userInfo,
    // 目前页面展示的列表
    userlist: [],
    // 所有用户数据
    allUserList: [],
    // 与登录用户同家乡的用户数据
    hometownUserList: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    this.setData({
      userInfo: app.globalData.userInfo,
      user: app.user
    });
    var that = this;
    wx.request({
      // 发起请求，返回所有用户数据
      url: app.globalData.reqUrl + 'wxDescListByLike',
      method: 'GET',
      data: {},
      header: {
        'Content-Type': 'application/json'
      },
      success: function (res) {
        // console.log("list请求成功");
        // console.log(res.data);
        var thelist = res.data;
        if (thelist == null) {
          var toastText = '获取数据失败' + res.data.errMsg;
          wx.showToast({
            title: toastText,
            icon: '',
            duration: 2000 //弹出时间
          })
        } else {
          that.setData({
            allUserList: thelist,
            userlist: thelist
          })
        }
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {
  
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

    }

    //监听‘看老乡’switch组件
    ,
  homeOnly: function(e) {

    var that = this;
    // 由无筛选滑到看老乡
    if (e.detail.value == true) {
      wx.request({
        // 发起请求，返回老乡列表数据
        url: app.globalData.reqUrl + 'wxListByHometown',
        method: 'POST',
        data: JSON.stringify(that.data.user.userID),
        header: {
          'Content-Type': 'application/json'
        },
        success: function(res) {
          console.log("请求成功了");
          console.log(res.data);
          var thelist = res.data;
          if (thelist == null) {
            var toastText = '获取数据失败' + res.data.errMsg;
            wx.showToast({
              title: toastText,
              icon: '',
              duration: 2000 //弹出时间
            })
          } else {
            that.setData({
              hometownUserList: thelist,
              userlist: thelist
            })
          }
        }
      })
    }
    // 由筛选老乡切换到所有
    else {
      that.setData({
        userlist: that.data.allUserList,
      })
    }
  }

})