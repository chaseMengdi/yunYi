var type = 1;
const app = getApp();

Page({
  data: {
    background: [
      { url: "https://i.loli.net/2018/12/02/5c03ea880f7cc.png" },
      { url: "https://i.loli.net/2018/12/02/5c03ea88e8f86.png" },
      { url: "https://i.loli.net/2018/12/02/5c03ea89a9a0e.png" }
    ],
    indicatorDots: true,
    vertical: false,
    autoplay: false,
  },
  onShow:function(){
    type = 1;
  },
  onSlideChangeEnd: function (e) {
    type = e.detail.current + 1
  },
  confirmSel: function (e) {
    wx.showModal({
      title: "确认选择",
      content: "您选择的模板序号为：" + type,
      showCancel: true,
      confirmText: "确定",
      success: function (res) {
        console.log('用户选择的模板序号为：' + type)
        if (res.confirm) {
          var that = this;
          wx.request({
            url: app.globalData.reqUrl+'setModel',
            method: 'POST',
            data: JSON.stringify({
              userID:app.user.userID,
              modelID:type
              }),
            header: { 'Content-Type': 'application/json' }  
          });
          //通过缓存变量将模板号传给主页使得其刷新内容
          var modelId = wx.getStorageSync('modelId');
          modelId = type;
          wx.setStorageSync('modelId', modelId);

          wx.navigateBack({
            delta:1
          })
        }
      }
    })
 
  }
})