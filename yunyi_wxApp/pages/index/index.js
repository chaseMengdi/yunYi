//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    userInfo: {},
    hasUserInfo: false,
  },

  //登录功能
  login: function (wxgetuserinfoRes) {
    var wxUserInfo = wxgetuserinfoRes.detail.userInfo;
    // console.log(wxUserInfo);
    wx.login({
      success(wxloginRes) {
        // console.log(wxloginRes.code);
        if (wxloginRes.code) {
          wx.request({
            url: app.globalData.reqUrl + 'wxLogin',
            method: 'POST', 
            header: { 'Content-Type': 'application/x-www-form-urlencoded' },
            data: {
              code: wxloginRes.code,
              nickName: wxUserInfo.nickName,
              gender: wxUserInfo.gender,
              avatarUrl: wxUserInfo.avatarUrl,
              province: wxUserInfo.province,
              city: wxUserInfo.city
            },
            success: function (wxrequestRes) {
              // console.log(wxrequestRes);
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
                app.user.userID = theUser.openId;
                app.user.province = theUser.province;
                app.user.city = theUser.city;
                app.user.nickName = theUser.nickName;
                app.user.gender = theUser.gender;
                app.user.avatarUrl = theUser.avatarUrl;
                // console.log(app.user);
                // 弹出提示框
                wx.showToast({
                  title: '登录成功',
                  icon: 'success',
                  duration: 2000
                });
                wx.switchTab({
                  url: "../list/list",
                })
              }
            }
          })
        } 
        else {
          console.log('wx.login出错，未生成code：' + wxloginRes.errMsg)
        }
      }
    })
  },
})