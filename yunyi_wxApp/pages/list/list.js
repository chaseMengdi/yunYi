// pages/list/list.js

const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    // 当前用户数据
    user: app.user,
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
    var that = this;
    wx.request({
      // 发起请求，返回所有用户数据
      url: app.globalData.reqUrl + 'wxGetUserAndPageList',
      method: 'GET',
      header: {'Content-Type': 'application/json'},
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

  homeOnly: function(e) {
    var that = this;
    // 老乡list为空才进行请求，避免重复请求
    if (e.detail.value && (that.data.hometownUserList !=null) ) {
      wx.request({
        // 发起请求，返回老乡列表数据
        url: app.globalData.reqUrl + 'wxListByHometown',
        method: 'POST',
        data: {id: that.data.user.userID},
        header: { 'Content-Type': 'application/x-www-form-urlencoded' },
        success: function(res) {
          // console.log("请求成功了");
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
    // 由无筛选滑到看老乡
    else if (e.detail.value == true){
      that.setData({
        userlist: that.data.hometownUserList
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