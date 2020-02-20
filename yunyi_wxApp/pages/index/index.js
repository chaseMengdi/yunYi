//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo')
  },

  //登录功能
  login: function (wxgetuserinfoRes) {
    var wxUserInfo = wxgetuserinfoRes.detail.userInfo;
    // console.log(wxUserInfo);
    wx.login({
      success(wxloginRes) {
        // console.log(wxloginRes);
        if (wxloginRes.code) {
          wx.request({
            url: app.globalData.reqUrl + 'wxLogin',
            method: 'POST',
            data: {
              code: wxloginRes.code,
              nickName: wxUserInfo.nickName,
              gender: wxUserInfo.gender,
              avatarUrl: wxUserInfo.avatarUrl,
              province: wxUserInfo.province,
              city: wxUserInfo.city
            },
            success: function (wxrequestRes) {
              console.log(wxrequestRes.data);
              var theUser = wxrequestRes.data;
              //判断用户是否被封禁
              if (theUser.status == "stop") {
                wx.showModal({
                  title: '登录异常',
                  content: '您的账户被封禁或者不存在，请联系管理员！',
                  showCancel: "false"
                })
              }
              else if (theUser != null && theUser != '') {
                app.user.openid = theUser.openid;
                app.user.province = theUser.province;
                app.user.city = theUser.city;
                app.user.nickName = theUser.nickName;
                app.user.avatarUrl = theUser.avatarUrl;
                // 弹出提示框
                wx.showToast({
                  title: '登录成功',
                  icon: 'success',
                  duration: 2000
                });
                // wx.switchTab({
                //   url: "../list/list",
                // })
              }
            }
          })
        } else {
          console.log('wx.login出错，未生成code：' + wxloginRes.errMsg)
        }
      }
    })
  },

  //获取手机号
  // getPhoneNumber: function(e) {
  //   console.log(`是否成功调用${e.detail.errMsg}`);
  //   console.log(`加密算法的初始向量:${e.detail.iv}`);
  //   console.log(`包括敏感数据在内的完整用户信息的加密数据:${e.detail.encryptedData}`);
  // }

})