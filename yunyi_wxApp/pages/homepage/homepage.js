// pages/homepage/homepage.js
import Dialog from '../../miniprogram_npm/@vant/weapp/dialog/dialog';

const app = getApp()
const util = require('../../utils/util.js')

Page({

  /**
   * 页面的初始数据
   */
  data: {
    // 导航用
    TabCur: 0,
    MainCur: 0,
    VerticalNavTop: 0,
    list: [],
    load: true,
    // 用户数据用
    user: app.user,
    ownerID: null,
    owner: null,
    visitInfo: null, //存放评论总数，举报总数，点赞总数
    comment: null, //存放主页的评论列表
    pageContent: null,
    likeFlag: 0,
    likeText: "喜欢",
    reportText: "举报",
    userIsOwner: false, //判断登录用户是否为主页主人
    thisCurIndex: 0, 
    blankComment: "" //发送评论后清空
  },

  /**
   * 生命周期函数--监听页面加载
   */
  //加载主页时读取所需要的pageContent,owner,visitinfo,comment
  onLoad: function (e) {
    wx.showLoading({
      title: '加载中...',
      mask: true
    });
    // 垂直导航用
    let list = [{}];
    for (let i = 0; i < 5; i++) {
      list[i] = {};
      list[i].name = i + 1;
      list[i].id = i;
    }
    this.setData({
      list: list,
      listCur: list[0]
    })

    // console.log(e)
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
      header: { 'Content-Type': 'application/x-www-form-urlencoded' },
      data: {
        id: e.ownerID
      },
      method: 'POST',
      success: function (res) {
        var result = res.data;
        console.log(result);
        if (result == undefined) {
          var text = '获取数据失败' + res.data.errMsg;
          wx.showToast({
            title: text,
            icon: '',
            duration: 2000
          });
        } else {
          for (let i = 0; i < 5; i++) {
            list[i].image = result.content[i].image;
            list[i].text = result.content[i].text;
          }
          that.setData({
            owner: result.user,
            visitInfo: result.visitInfo,
            comment: result.comment, 
            pageContent: result.content,
            list: list
          })
        }
      }
    })
  },

  //用户更改头像或模板后刷新数据
  onShow: function () {
    if (this.data.userIsOwner) {
      this.setData({
        "owner.avatar": app.user.avatarUrl,
        "pageContent.modelID": wx.getStorageSync('modelId')
      })
    }
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    wx.hideLoading()
  },

  // 选择tab
  tabSelect(e) {
    this.setData({
      TabCur: e.currentTarget.dataset.id,
      MainCur: e.currentTarget.dataset.id,
      VerticalNavTop: (e.currentTarget.dataset.id - 1) * 50
    })
  },
  VerticalMain(e) {
    let that = this;
    let list = this.data.list;
    let tabHeight = 0;
    if (this.data.load) {
      for (let i = 0; i < list.length; i++) {
        let view = wx.createSelectorQuery().select("#main-" + list[i].id);
        view.fields({
          size: true
        }, data => {
          list[i].top = tabHeight;
          tabHeight = tabHeight + data.height;
          list[i].bottom = tabHeight;
        }).exec();
      }
      that.setData({
        load: false,
        list: list
      })
    }
    let scrollTop = e.detail.scrollTop + 20;
    for (let i = 0; i < list.length; i++) {
      if (scrollTop > list[i].top && scrollTop < list[i].bottom) {
        that.setData({
          VerticalNavTop: (list[i].id - 1) * 50,
          TabCur: list[i].id
        })
        return false
      }
    }
  },

  //评论
  comment: function (e) {
    var that = this;
    var fmData = e.detail.value;
    // 在表单数据中添加其他属性，
    //否则，添加进comment表中除了text字段都会被置为null
    fmData.commenterID = app.user.userID;
    fmData.ownerID = this.data.ownerID;
    fmData.avatarUrl = this.data.user.avatarUrl;
    fmData.commenterName = app.user.nickName;
    fmData.time = util.formatTime(new Date());
    // console.log(fmData);
    wx.request({
      url: app.globalData.reqUrl + 'addComment',
      method: 'POST',
      data: JSON.stringify(fmData),
      header: {
        'Content-Type': 'application/json'
      },
      success: function (res) {
        if (res.data != null && res.data != '') {
          // console.log(JSON.stringify(fmData));
          // console.log(res);
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
            blankComment:""
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
  Like: function () {
    var that = this;
    // likeflag为0则表示未点赞，先改界面数据
    if (this.data.likeFlag == 0) {
      this.setData({
        likeFlag: 1,
        likeText: "已喜欢"
      })
      // 点赞写入到数据库
      wx.request({
        url: app.globalData.reqUrl + 'wxLikeById',
        method: 'POST',
        data: {id: that.data.ownerID},
        header: { 'Content-Type': 'application/x-www-form-urlencoded' },
        success: function (res) {
          that.setData({
            visitInfo: res.data
          })
        }
      })
    }
    // 否则likeflag为1则已点赞，先改界面数据
    else {
      this.setData({
        likeFlag: 0,
        likeText: "喜欢"
      })
      // 取消点赞写入到数据库
      wx.request({
        url: app.globalData.reqUrl + 'wxDecLikeById',
        method: 'POST',
        data: JSON.stringify(that.data.ownerID),
        data: { id: that.data.ownerID },
        header: { 'Content-Type': 'application/x-www-form-urlencoded' },
        success: function (res) {
          that.setData({
            visitInfo: res.data
          })
        }
      })
    }
  }

  // 投诉功能
  ,
  report: function () {
    var that = this;
    Dialog.confirm({
      title: '举报',
      message: '确认举报?'
    }).then(() => {
      this.setData({
        reportText: "已举报"
      })
      wx.request({
        url: app.globalData.reqUrl + 'wxReportById',
        method: 'POST',
        data: { id: that.data.ownerID },
        header: { 'Content-Type': 'application/x-www-form-urlencoded' },
        success: function (res) {
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
    }).catch(() => {
      // on cancel
    });
  }



  // 接受用户图片 
  ,
  pickImage: function (e) {
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
            url: 'https://sm.ms/api/v2/upload',
            filePath: filePath,
            name: 'smfile',
            success: res => {
              //逆向转换JSON字符串后抽取网址
              console.log(res)
              console.log("图片上传成功！")
              console.log(e.target.id)
              console.log(JSON.parse(res.data).data.url)
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
  saveContent: function (e) {
    var that = this;
    var fmData = e.detail.value;
    // console.log(fmData);
    // pageContent中已有了Image1-5的数据，补齐text数据后，存到数据库
    this.data.pageContent[0].text = fmData.text0;
    this.data.pageContent[1].text = fmData.text1;
    this.data.pageContent[2].text = fmData.text2;
    this.data.pageContent[3].text = fmData.text3;
    this.data.pageContent[4].text = fmData.text4;
    // console.log(this.data.pageContent);
    wx.request({
      url: app.globalData.reqUrl + 'wxSaveContent',
      method: 'POST',
      data: JSON.stringify(this.data.pageContent),
      header: {
        'Content-Type': 'application/json'
      },
      success: function (res) {
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
})