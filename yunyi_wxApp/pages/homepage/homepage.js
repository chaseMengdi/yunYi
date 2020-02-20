// pages/homepage/homepage.js
const app = getApp()
const util = require('../../utils/util.js')

Page({

  /**
   * 页面的初始数据
   */
  data: {
    ownerID: null,
    pageContent: {
      image1: 'https://i.loli.net/2018/12/02/5c03e99cd4b38.jpg'
    }, //存放模板号，文字内容，图片路径
    owner: null,
    visitInfo: null, //存放评论总数，举报总数，点赞总数
    comment: null, //存放主页的评论列表
    likeFlag: 0,
    likeUrl: "https://i.loli.net/2018/12/02/5c03e99c328e7.png",
    userIsOwner: false, //判断登录用户是否为主页主人
    thisCurIndex: 0,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  //加载主页时读取所需要的pageContent,owner,visitinfo,comment
  onLoad: function(e) {
    var that = this;
    if (e.ownerID == undefined) {
      return;
    }
    that.setData({
      ownerID: e.ownerID,
    });

    if (app.user.userID == e.ownerID) {
      this.setData({
        userIsOwner: true
      })
    };
    wx.request({
      // 发起请求，读取该用户的主页信息
      url: app.globalData.reqUrl + 'wxGetUpgById',
      data: JSON.stringify(e.ownerID),
      method: 'POST',
      success: function(res) {
        var result = res.data;
        if (result == undefined) {
          var text = '获取数据失败' + res.data.errMsg;
          wx.showToast({
            title: text,
            icon: '',
            duration: 2000
          });
        } else {
          that.setData({
            owner: result.user,
            pageContent: result.content,
            visitInfo: result.visitInfo,
            comment: result.comment
          })
        }
      }
    })
  },

  //用户更改头像或模板后刷新数据
  onShow: function() {
    if (this.data.userIsOwner) {
      this.setData({
        "owner.avatar": app.user.avatar,
        "pageContent.modelID": wx.getStorageSync('modelId')
      })
    }
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */



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
      return {
        title: "美丽的家乡",
        path: 'pages/homepage/homepage'
      }

    }

    //评论
    ,
  comment: function(e) {
      var that = this;
      var fmData = e.detail.value;
      // 在表单数据中添加其他属性，
      //否则，添加进comment表中除了text字段都会被置为null
      fmData.commenterID = app.user.userID;
      fmData.ownerID = this.data.owner.userID;
      fmData.commenterName = app.user.userName;
      fmData.time = util.formatTime(new Date());
      wx.request({
        url: app.globalData.reqUrl + 'addComment',
        method: 'POST',
        data: JSON.stringify(fmData),
        header: {
          'Content-Type': 'application/json'
        },
        success: function(res) {
          if (res.data != null && res.data != '') {
            console.log(JSON.stringify(fmData));
            console.log(res);
            wx.showToast({
              title: '发表成功',
              icon: '',
              duration: 2000
            })

            //将本条评论数据存为新串，在新串后连接已有数据
            var newComment = [fmData];
            that.data.comment = newComment.concat(that.data.comment);
            that.setData({
              comment: that.data.comment,
            });
          } else {
            wx.showToast({
              title: '发表失败',
              icon: '',
              duration: 2000
            })
          }

        }
      })
    }

    //响应点赞，重复点击可取消
    ,
  Like: function() {
      var that = this;
      // likeflag为0则表示未点赞，先改界面数据
      if (this.data.likeFlag == 0) {
        this.setData({
          likeUrl: "https://i.loli.net/2018/12/02/5c03e99c32191.png",
          likeFlag: 1
        })
        // 点赞写入到数据库
        wx.request({
          url: app.globalData.reqUrl + 'wxLikeById',
          method: 'POST',
          data: JSON.stringify(that.data.ownerID),
          header: {
            'Content-Type': 'application/json'
          },
          success: function(res) {
            that.setData({
              visitInfo: res.data
            })
          }
        })
      }
      // 否则likeflag为1则已点赞，先改界面数据
      else {
        this.setData({
          likeUrl: "https://i.loli.net/2018/12/02/5c03e99c328e7.png",
          likeFlag: 0
        })
        // 取消点赞写入到数据库
        wx.request({
          url: app.globalData.reqUrl + 'wxDecLikeById',
          method: 'POST',
          data: JSON.stringify(that.data.ownerID),
          header: {
            'Content-Type': 'application/json'
          },
          success: function(res) {
            that.setData({
              visitInfo: res.data
            })
          }
        })
      }
    }

    // 投诉功能
    ,
  report: function() {
      var that = this;
      wx.request({
        url: app.globalData.reqUrl + 'wxReportById',
        method: 'POST',
        data: JSON.stringify(that.data.ownerID),
        header: {
          'Content-Type': 'application/json'
        },
        success: function(res) {
          wx.showToast({
            title: '举报成功',
            icon: '',
            duration: 2000
          });
          that.setData({
            visitInfo: res.data
          })
        }
      })
    }

    //跳转到选择模板
    ,
  goChooseModel() {
    wx.navigateTo({
      url: '../choose/choose'
    })
  }





  // 接受用户图片 
  ,
  pickImage: function(e) {
      var that = this
      //微信API选择图片
      //如果是用户本人主页才响应点击图片的动作
      if (this.data.userIsOwner) {
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
                console.log(e.target.id)
                // 通过图片组件的Id不同来修改不同的数据
                switch (e.target.id) {
                  case '1':
                    that.setData({
                      "pageContent.image1": JSON.parse(res.data).data.url
                    });
                    break;
                  case '2':
                    that.setData({
                      "pageContent.image2": JSON.parse(res.data).data.url
                    });
                    break;
                  case '3':
                    that.setData({
                      "pageContent.image3": JSON.parse(res.data).data.url
                    });
                    break;
                  case '4':
                    that.setData({
                      "pageContent.image4": JSON.parse(res.data).data.url
                    });
                    break;
                  case '5':
                    that.setData({
                      "pageContent.image5": JSON.parse(res.data).data.url
                    });
                    break;
                }
              }
            })

          }
        })
      }

    }



    //保存修改内容

    ,
  saveContent: function(e) {
      var that = this;
      // 
      var fmData = e.detail.value;
      // pageContent中已有了Image1-5的数据，补齐text数据后，存到数据库
      this.data.pageContent.text1 = fmData.text1;
      this.data.pageContent.text2 = fmData.text2;
      this.data.pageContent.text3 = fmData.text3;
      this.data.pageContent.text4 = fmData.text4;
      this.data.pageContent.text5 = fmData.text5;
      wx.request({
        url: app.globalData.reqUrl + 'wxSaveContent',
        method: 'POST',
        data: JSON.stringify(this.data.pageContent),
        header: {
          'Content-Type': 'application/json'
        },
        success: function(res) {
          var theUser = res.data;
          if (theUser != null && theUser != '') {
            wx.showToast({
              title: '修改成功',
              icon: '',
              duration: 2000
            })
          }

        }
      })
    }

    //跳转到修改个人信息
    ,
  goUpdate: function() {
      if (this.data.userIsOwner) {
        wx.navigateTo({
          url: '../update/update',
        })
      }
    }

    //模板2选择侧边栏
    ,
  switchRightTab: function(e) {
    // 获取item项的id，和数组的下标值
    let index = parseInt(e.target.dataset.index);
    // 把点击到的某一项，设为当前index
    this.setData({
      thisCurIndex: index
    })
  }

})