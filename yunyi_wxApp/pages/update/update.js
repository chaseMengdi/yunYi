    // pages/update/update.js
const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    user: app.user
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

    // 保存修改
    ,
  update: function(e) {
      var that = this;
      var fmData = e.detail.value;
      // 表单数据只包括文本信息，补上头像和id后写入数据库
      fmData.userID = this.data.user.userID;
      fmData.avatar = this.data.user.avatarUrl;
      // 当用户两次输入的密码不同时给出提示
      if (fmData.password0 != fmData.password) {
        wx.showModal({
          title: '更改失败',
          content: '密码不一致',
          showCancel: "false"
        })
      } else {
        wx.request({
          url: app.globalData.reqUrl+'wxUpdate',
          method: 'POST',
          data: JSON.stringify(fmData),
          header: {
            'Content-Type': 'application/json'
          },
          success: function(res) {
            wx.showToast({
              title: '更改成功',
              icon: '',
              duration: 2000
            })
            // 更改成功后更新全局变量user
            app.user.hometown = fmData.hometown;
            app.user.userName = fmData.name;
            app.user.password = fmData.password;
            app.user.avatar = fmData.avatar;
          }
        })
      }

    }

    //上传头像
    ,
  changeAvatar: function() {
    var that = this
    //微信API选择图片
    wx.chooseImage({
      count: 1, // 最多可以选择的图片张数，默认1
      sizeType: ['original', 'compressed'], // original 原图，compressed 压缩图，默认二者都有
      sourceType: ['album', 'camera'], // album 从相册选图，camera 使用相机，默认二者都有
      success: ret => {
        var filePath = ret.tempFilePaths[0];

        console.log("图片临时网址，小程序关闭后将会被销毁：" + filePath);
        //微信API将图片上传到图床
        //返回网络地址
        wx.uploadFile({
          url: 'https://sm.ms/api/upload',
          filePath: filePath,
          name: 'smfile',
          success: res => {
            //逆向转换JSON字符串后抽取网址
            console.log("图片上传成功！")
            console.log(JSON.parse(res.data).data.url)
            that.setData({
              "user.avatar": JSON.parse(res.data).data.url
            })
          }
        })
      }
    })
  }

})