// pages/homepage/homepage.js
import Dialog from '../../miniprogram_npm/@vant/weapp/dialog/dialog';

const app = getApp()
const util = require('../../utils/util.js')

Page({

  /**
   * 页面的初始数据
   */
  data: {
    // 聊天框随输入法弹起用
    InputBottom: 0,
    // 垂直导航用
    TabCur: 0,
    MainCur: 0,
    VerticalNavTop: 0,
    list: [],
    // 存储用户数据用
    user: null, //登录用户信息
    pageContent: null, //更新本页内容：image1-5，text1-5
    ownerID: null,
    owner: null, //本页用户信息
    visitInfo: null, //存放评论总数，举报总数，点赞总数
    comment: null, //存放主页的评论列表
    // 判断用flag及组件内文字
    load: true, //页面加载完毕判断flag
    userIsOwner: false, //判断登录用户是否为主页主人
    likeFlag: 0, //判断是否已喜欢,
    reportFlag: 0,  //判断是否已经举报
    likeText: "点赞", //喜欢按钮文字，点赞/已点赞
    reportText: "举报", //举报按钮文字，举报/已举报
    blankComment: "" //评论text内value，发送评论后清空
  },

  /**
   * 生命周期函数--监听页面加载
   */
  //加载主页时读取所需要的pageContent,owner,visitinfo,comment
  onLoad: function (e) {
    // 页面内容未加载完成前先显示加载中弹窗
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
        console.log("该用户的主页信息获取成功");
        console.log(result);
        if (result == undefined) {
          var text = '获取数据失败' + res.data.errMsg;
          wx.showToast({
            title: text,
            icon: '',
            duration: 2000
          });
        } else {
          list[0].image = result.content.image1;
          list[0].text = result.content.text1;
          list[1].image = result.content.image2;
          list[1].text = result.content.text2;
          list[2].image = result.content.image3;
          list[2].text = result.content.text3;
          list[3].image = result.content.image4;
          list[3].text = result.content.text4;
          list[4].image = result.content.image5;
          list[4].text = result.content.text5;
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
    this.setData({
      user: app.user
    });
    if (this.data.userIsOwner) {
      this.setData({
        "owner.avatar": app.user.avatarUrl,
        "owner.nickName": app.user.nickName
      })
    }
    // console.log(this.data.user)
    // console.log(this.data.owner)
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    // 页面渲染完成后隐藏“加载中”弹窗
    wx.hideLoading()
  },

  // 垂直导航选择tab
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
            blankComment: ""
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
  },

  //响应点赞，重复点击可取消
  Like: function () {
    var that = this;
    // likeflag为0则表示未点赞，先改界面数据
    if (this.data.likeFlag == 0) {
      this.setData({
        likeFlag: 1,
        likeText: "已点赞"
      })
      // 点赞写入到数据库
      wx.request({
        url: app.globalData.reqUrl + 'wxLikeById',
        method: 'POST',
        data: { id: that.data.ownerID },
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
  },

  // 举报功能
  report: function () {
    var that = this;
    Dialog.confirm({
      title: '举报',
      message: '确认举报?'
    }).then(() => {
      this.setData({
        reportFlag: 1,
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
      // console.log("取消举报")
    });
  },

  // 接受用户图片 
  pickImage: function (e) {
    var that = this
    //微信API选择图片
    //如果是用户本人主页才响应点击图片的动作
    if (this.data.userIsOwner) {
      wx.chooseImage({
        count: 1, // 最多可以选择的图片张数，默认1
        sizeType: ['original', 'compressed'], // original 原图，compressed 压缩图，默认二者都有
        sourceType: ['album', 'camera'], // album 从相册选图，camera 使用相机，默认二者都有
        success: res => {
          var filePath = res.tempFilePaths[0];
          /**
           * 测试上传图片临时网址后显示
           */
          // console.log("图片临时网址，小程序关闭后将会被销毁：" + filePath);
          // var listImage = "list[" + (e.target.id) + "].image"
          // // 传来的参数e.target.id是字符，先转换成数值型，最后加成一个字符串"pageContent.image1"
          // var pageContentImg = "pageContent.image" + (parseInt(e.target.id) + 1)
          // console.log(pageContentImg)
          // that.setData({
          //   [listImage]: filePath,
          //   [pageContentImg]: filePath
          // });
          // console.log(that.data.list[0].image)
          // console.log(that.data.pageContent.image1)
          /**
           * 微信API将图片上传到图床
           * 返回网络地址
           */
          wx.uploadFile({
            url: app.globalData.imageUrl + "uploadImg",
            filePath: filePath,
            name: 'file',
            success: res => {
              // console.log("当前图片id" + e.target.id + "图片网络地址：" + res.data)
              // list和pageContent都要修改
              var listImage = "list[" + e.target.id + "].image"
              var pageContentImg = "pageContent.image" + (parseInt(e.target.id) + 1)
              that.setData({
                [listImage]: res.data,
                [pageContentImg]: res.data
              });
            }
          })
        }
      })
    }
  },

  // 删除已上传图片
  delImg: function (e) {
    console.log(e.target)//有时候点中了，这个函数也运行了，但是传来的id是空的，迷惑啊
    wx.showModal({
      title: '删除图片',
      content: '确认删除这张回忆?',
      success: res => {
        if (res.confirm) {
          var listImage = "list[" + e.target.id + "].image"
          var pageContentImg = "pageContent.image" + (parseInt(e.target.id) + 1)
          this.setData({
            [listImage]: null,
            [pageContentImg]: null
          })
        }
      }
    })
    /**
     * 点确认也是取消，不知道啥bug
     */
    // var that = this;
    // Dialog.confirm({
    //   title: '删除图片',
    //   message: '确认删除这张回忆?'
    // }).then(() => {
    //   var listImage = "list[" + e.target.id + "].image"
    //   var pageContentImg = "pageContent.image" + (parseInt(e.target.id) + 1)
    //   that.setData({
    //     [listImage]: null,
    //     [pageContentImg]: null
    //   })
    //   console.log("list"+that.data.list[0].image)
    //   console.log("list"+that.data.pageContent.image1)
    // }).catch(() => {
    //   // on cancel
    //   console.log("取消"+that.data.list[0].image)
    // });
  },

  //保存修改内容
  saveContent: function (e) {
    var that = this;
    var fmData = e.detail.value;
    // console.log(fmData);
    // pageContent中已有了Image1-5的数据，补齐text数据后，存到数据库
    this.data.pageContent.text1 = fmData.text1;
    this.data.pageContent.text2 = fmData.text2;
    this.data.pageContent.text3 = fmData.text3;
    this.data.pageContent.text4 = fmData.text4;
    this.data.pageContent.text5 = fmData.text5;
    // console.log(this.data.pageContent);
    wx.request({
      url: app.globalData.reqUrl + 'wxSaveContent',
      method: 'POST',
      data: JSON.stringify(this.data.pageContent),
      header: { 'Content-Type': 'application/json' },
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
  },

  // 聊天框随输入法弹起
  InputFocus(e) {
    this.setData({
      InputBottom: e.detail.height
    })
  },
  InputBlur(e) {
    this.setData({
      InputBottom: 0
    })
  },
})