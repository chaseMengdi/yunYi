// pages/mypage/mypage.js

const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    user: []
  },

  // 保存修改
  update: function(e) {
    //补全所需的3个参数
    // e.detail.value.openId=this.data.user.userID;
    // if (e.detail.value.avatarUrl !== undefined  ){
    //   this.data.user.avatarUrl = e.detail.value.avatarUrl;
    // }
    if (e.detail.value.nickName !== undefined  ) {
      this.data.user.nickName = e.detail.value.nickName ;
    }
    var that = this;
    console.log("传送数据" + JSON.stringify(that.data.user))
    wx.request({
      url: app.globalData.reqUrl + 'wxUpdate',
      method: 'POST',
      data: JSON.stringify(that.data.user),
      header: {'Content-Type': 'application/json'},
      success: function(res) {
        wx.showToast({
          title: '更改成功',
          icon: '',
          duration: 2000
        })
        // 更改成功后更新全局变量user
        app.user = that.data.user;
      }
    })
  },

  // 选择上传头像
  ChooseImage() {
    let that = this;
    wx.chooseImage({
      count: 1, //默认9
      sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
      sourceType: ['album', 'camera'], // album 从相册选图，camera 使用相机，默认二者都有
      success: (res) => {
        var filePath = res.tempFilePaths[0];
        console.log(filePath)
        that.setData({
          "user.avatarUrl": filePath
        })
        wx.uploadFile({
          url: app.globalData.imgUrl,
          filePath: filePath,
          name: 'file',
          success: res => {
            console.log(res),
              that.setData({
                "user.avatarUrl": res.data
              })
          }
        })
      }
    });
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      user: app.user
    });
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
})