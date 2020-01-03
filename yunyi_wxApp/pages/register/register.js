// pages/register/register.js

const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {

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


    // 用户注册

    ,
  reg: function(e) {
    var that = this;
    var fmData = e.detail.value;
    // 当有必填字段未填入信息时提示注册失败
    if (fmData.userID == '' || fmData.password == '' || fmData.password0 == '' || fmData.hometown == '' || fmData.name == '') {
      wx.showModal({
        title: '注册失败',
        content: '必填字段请不要留空',
        showCancel: "false"
      })
    }
    // 当用户两次输入的密码不同时给出提示
    else if (fmData.password0 != fmData.password) {
      wx.showModal({
        title: '注册失败',
        content: '密码不一致',
        showCancel: "false"
      })
    }
    // 用户输入了有效信息
    else
      wx.request({
        url: app.globalData.reqUrl+'wxRegister',
        method: 'POST',
        data: JSON.stringify(fmData),
        header: {
          'Content-Type': 'application/json'
        },
        success: function(res) {
          var theUser = res.data;
          // 用户id无冲突则提示注册成功
          if (theUser != null && theUser != '') {
            wx.showToast({
              title: '注册成功',
              icon: '',
              duration: 2000
            })
          }
          // id已存在
          else {
            wx.showToast({
              title: '用户id已存在',
              icon: '',
              duration: 2000
            })
          }
        }
      })
  }



})