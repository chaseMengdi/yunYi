/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : yunyi

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2018-11-29 22:39:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `adminid` int(11) NOT NULL,
  `admin_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`adminid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('123', '爱国者', '123456');
INSERT INTO `admin` VALUES ('2', 'c.x.', '222');
INSERT INTO `admin` VALUES ('1', 'hwx', '111');

-- ----------------------------
-- Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `cmtid` int(11) NOT NULL AUTO_INCREMENT,
  `commenterid` varchar(255) DEFAULT NULL,
  `ownerid` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `commenter_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cmtid`)
) ENGINE=MyISAM AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('12', '1365480990', '274736996', '光谷步行街确实挺繁华的~', '2018/07/09 15:34:10', '长风');
INSERT INTO `comment` VALUES ('11', '1365480990', '401129798', '襄阳牛肉面看起来很好吃啊，下次去你可要请我吃啊！', '2018/07/09 15:33:16', '长风');
INSERT INTO `comment` VALUES ('13', '1365480990', '445637026', '热干面确实挺好吃的，就是有点干。。', '2018/07/09 15:34:37', '长风');
INSERT INTO `comment` VALUES ('14', '401129798', '401129798', '没问题，下次请你吃个够，再带你在襄阳玩一玩', '2018/07/09 15:36:19', '胡火锅');
INSERT INTO `comment` VALUES ('15', '401129798', '274736996', '汉口里确实很有历史韵味。', '2018/07/09 15:37:33', '胡火锅');
INSERT INTO `comment` VALUES ('16', '401129798', '445637026', '我还没有去过海洋世界呢。', '2018/07/09 15:37:56', '胡火锅');
INSERT INTO `comment` VALUES ('17', '401129798', '1365480990', '江汉路步行街真不是盖的，上次我差点腿都走断了。', '2018/07/09 15:38:45', '胡火锅');
INSERT INTO `comment` VALUES ('18', '274736996', '401129798', '哈哈，你和诸葛亮应该挺熟的吧~', '2018/07/09 15:40:15', 'c.x.');
INSERT INTO `comment` VALUES ('19', '274736996', '445637026', '武汉科技馆有很多有趣的展馆，挺好玩的。', '2018/07/09 15:40:52', 'c.x.');
INSERT INTO `comment` VALUES ('20', '4780494', '4780494', '欢迎大家来潜江游玩！', '2018/07/09 15:55:06', '--！');
INSERT INTO `comment` VALUES ('26', '274736996', '401129798', '想去玩', '2018/07/10 08:53:29', 'c.x.');
INSERT INTO `comment` VALUES ('24', '1018952520', '401129798', '牛肉面，好吃！', '2018/07/09 19:47:40', '爱国者');
INSERT INTO `comment` VALUES ('28', '1018952520', '4780494', '你的家乡看起来很漂亮！', '2018/07/11 17:01:19', '爱国者~');

-- ----------------------------
-- Table structure for `hibernate_sequence`
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES ('30');

-- ----------------------------
-- Table structure for `log`
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `logid` int(11) NOT NULL AUTO_INCREMENT,
  `adminid` int(11) DEFAULT NULL,
  `admin_name` varchar(255) DEFAULT NULL,
  `log_time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`logid`)
) ENGINE=MyISAM AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of log
-- ----------------------------
INSERT INTO `log` VALUES ('1', '1', 'hwx', '2018-06-28 09:25:23');
INSERT INTO `log` VALUES ('2', '1', 'hwx', '2018-06-28 09:30:44');
INSERT INTO `log` VALUES ('3', '1', 'hwx', '2018-06-28 09:33:53');
INSERT INTO `log` VALUES ('4', '1', 'hwx', '2018-06-28 09:37:12');
INSERT INTO `log` VALUES ('5', '1', 'hwx', '2018-06-28 09:45:14');
INSERT INTO `log` VALUES ('6', '1', 'hwx', '2018-06-28 09:47:26');
INSERT INTO `log` VALUES ('7', '1', 'hwx', '2018-06-28 10:08:06');
INSERT INTO `log` VALUES ('8', '1', 'hwx', '2018-06-28 10:16:19');
INSERT INTO `log` VALUES ('9', '1', 'hwx', '2018-06-28 10:19:54');
INSERT INTO `log` VALUES ('10', '1', 'hwx', '2018-06-28 10:21:08');
INSERT INTO `log` VALUES ('11', '1', 'hwx', '2018-06-28 10:48:59');
INSERT INTO `log` VALUES ('12', '2', 'c.x.', '2018-06-28 10:52:14');
INSERT INTO `log` VALUES ('13', '2', 'c.x.', '2018-06-28 11:12:58');
INSERT INTO `log` VALUES ('14', '1', 'hwx', '2018-06-28 11:21:37');
INSERT INTO `log` VALUES ('15', '1', 'hwx', '2018-06-28 11:23:30');
INSERT INTO `log` VALUES ('16', '2', 'c.x.', '2018-06-28 11:36:04');
INSERT INTO `log` VALUES ('17', '1', 'hwx', '2018-06-28 11:37:25');
INSERT INTO `log` VALUES ('18', '1', 'hwx', '2018-06-28 11:46:09');
INSERT INTO `log` VALUES ('19', '1', 'hwx', '2018-06-28 11:47:33');
INSERT INTO `log` VALUES ('20', '123', '爱国者', '2018-07-02 15:09:22');
INSERT INTO `log` VALUES ('21', '123', '爱国者', '2018-07-02 15:10:23');
INSERT INTO `log` VALUES ('22', '123', '爱国者', '2018-07-02 15:14:15');
INSERT INTO `log` VALUES ('23', '123', '爱国者', '2018-07-02 15:15:36');
INSERT INTO `log` VALUES ('24', '123', '爱国者', '2018-07-02 15:44:51');
INSERT INTO `log` VALUES ('25', '123', '爱国者', '2018-07-04 15:11:58');
INSERT INTO `log` VALUES ('26', '123', '爱国者', '2018-07-04 16:46:29');
INSERT INTO `log` VALUES ('27', '123', '爱国者', '2018-07-05 11:26:01');
INSERT INTO `log` VALUES ('28', '123', '爱国者', '2018-07-08 13:27:28');
INSERT INTO `log` VALUES ('29', '123', '爱国者', '2018-07-08 13:28:21');
INSERT INTO `log` VALUES ('30', '123', '爱国者', '2018-07-09 19:56:09');
INSERT INTO `log` VALUES ('31', '123', '爱国者', '2018-07-09 19:57:07');
INSERT INTO `log` VALUES ('32', '123', '爱国者', '2018-07-09 19:58:24');
INSERT INTO `log` VALUES ('33', '123', '爱国者', '2018-07-09 20:03:15');
INSERT INTO `log` VALUES ('34', '123', '爱国者', '2018-07-09 20:05:16');
INSERT INTO `log` VALUES ('35', '123', '爱国者', '2018-07-09 20:07:22');
INSERT INTO `log` VALUES ('36', '123', '爱国者', '2018-07-09 20:07:36');
INSERT INTO `log` VALUES ('37', '123', '爱国者', '2018-07-09 20:09:35');
INSERT INTO `log` VALUES ('38', '123', '爱国者', '2018-07-09 20:21:38');
INSERT INTO `log` VALUES ('39', '123', '爱国者', '2018-07-09 20:21:59');
INSERT INTO `log` VALUES ('40', '123', '爱国者', '2018-07-09 20:28:53');
INSERT INTO `log` VALUES ('41', '123', '爱国者', '2018-07-10 08:48:08');
INSERT INTO `log` VALUES ('42', '123', '爱国者', '2018-07-11 14:41:38');
INSERT INTO `log` VALUES ('43', '123', '爱国者', '2018-07-11 14:41:45');
INSERT INTO `log` VALUES ('44', '123', '爱国者', '2018-07-11 14:44:56');
INSERT INTO `log` VALUES ('45', '123', '爱国者', '2018-07-11 14:45:26');
INSERT INTO `log` VALUES ('46', '123', '爱国者', '2018-07-11 17:03:53');
INSERT INTO `log` VALUES ('47', '1', 'hwx', '2018-11-29 22:23:48');
INSERT INTO `log` VALUES ('48', '1', 'hwx', '2018-11-29 22:24:40');

-- ----------------------------
-- Table structure for `page_model`
-- ----------------------------
DROP TABLE IF EXISTS `page_model`;
CREATE TABLE `page_model` (
  `page_modelid` int(11) NOT NULL,
  `page_model_name` varchar(255) DEFAULT NULL,
  `picture_link` varchar(255) DEFAULT NULL,
  `usage_amount` int(11) DEFAULT NULL,
  PRIMARY KEY (`page_modelid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of page_model
-- ----------------------------
INSERT INTO `page_model` VALUES ('1', '模板1', 'model1.png', '1');
INSERT INTO `page_model` VALUES ('2', '模板2', 'model2.png', '3');
INSERT INTO `page_model` VALUES ('3', '模板3', 'model3.png', '2');

-- ----------------------------
-- Table structure for `process`
-- ----------------------------
DROP TABLE IF EXISTS `process`;
CREATE TABLE `process` (
  `id` int(11) NOT NULL,
  `adminid` int(11) DEFAULT NULL,
  `reason_num` varchar(255) DEFAULT NULL,
  `ttime` varchar(255) DEFAULT NULL,
  `userid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of process
-- ----------------------------
INSERT INTO `process` VALUES ('25', '123', '涉及国家相关政策', '2018-7-9-20:31:43', '401129798');
INSERT INTO `process` VALUES ('29', '123', '辱骂他人', '2018-7-11-17:4:58', '445637026');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `open_id` varchar(255) NOT NULL,
  `province` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `nick_name` varchar(255) DEFAULT NULL,
  `gender` int(1) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `avatar_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`open_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('445637026', '湖北', '武汉', 'one piece',  '1', 'normal', 'https://i.loli.net/2018/07/09/5b430b46b3a63.jpg');
INSERT INTO `user` VALUES ('274736996', '湖北', '武汉', 'c.x.',  '1', 'normal', 'https://i.loli.net/2018/07/09/5b4306db1389a.jpg');
INSERT INTO `user` VALUES ('401129798', '湖北', '襄阳', '胡火锅', '1', 'normal', 'https://i.loli.net/2018/07/09/5b4308d3acf3b.jpg');
INSERT INTO `user` VALUES ('1365480990', '湖北', '武汉', '长风',  '1', 'normal', 'https://i.loli.net/2018/07/09/5b430f7a2f429.jpg');
INSERT INTO `user` VALUES ('4780494', '湖北', '潜江', '--！',  '1', 'normal', 'https://i.loli.net/2018/07/09/5b4312bea28d1.jpg');
INSERT INTO `user` VALUES ('1018952520', '河北', '保定', '爱国者~',  '1', 'normal', 'https://i.loli.net/2018/07/09/5b434bd66b939.jpg');

-- ----------------------------
-- Table structure for `user_page_content`
-- ----------------------------
DROP TABLE IF EXISTS `user_page_content`;
CREATE TABLE `user_page_content` (
  `userid` varchar(255) NOT NULL,
  `image1` varchar(255) DEFAULT NULL,
  `image2` varchar(255) DEFAULT NULL,
  `image3` varchar(255) DEFAULT NULL,
  `modelid` int(11) DEFAULT NULL,
  `text1` varchar(255) DEFAULT NULL,
  `text2` varchar(255) DEFAULT NULL,
  `text3` varchar(255) DEFAULT NULL,
  `image4` varchar(255) DEFAULT NULL,
  `image5` varchar(255) DEFAULT NULL,
  `text4` varchar(255) DEFAULT NULL,
  `text5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_page_content
-- ----------------------------
INSERT INTO `user_page_content` VALUES ('401129798', 'https://i.loli.net/2018/07/09/5b43093dbec18.jpg', 'https://i.loli.net/2018/07/09/5b430a38c9e2a.jpg', 'https://i.loli.net/2018/07/11/5b45a7fb96eb2.jpg', '3', '襄阳特色牛肉面，牛杂面，牛油面是襄阳人最喜欢的早餐，它特点是这一辣二麻三鲜，味道可口，回味悠长，久食不厌。很多襄阳人如果三两天不吃牛油面，感觉口中无味，食欲大减。', '湖北省襄阳城墙位于现襄阳市襄城区境内，起初筑于汉初（具体年代无考），屡经整修（从城墙砖上有“ 邓 襄阳古城墙一禺城”，“太平国”、“岳”、“光绪”等字样可证），略呈正方形。', '澳门赌场现在推出线上版本，等你来体验~~', 'https://i.loli.net/2018/07/09/5b430ab588c37.jpg', 'https://i.loli.net/2018/07/09/5b430aee362d0.jpg', '襄阳郊外约15公里的隆中风景区，是传说中三国时期诸葛亮躬耕务农的隐居地，这里也是“三顾茅庐”、“隆中对策”等故事的发生地。', '习家池，又名高阳池，位于湖北襄阳城南约五公里的凤凰山南麓，是东汉初年襄阳侯习郁的私家园林，延存至今已有近2000年的历史。');
INSERT INTO `user_page_content` VALUES ('274736996', 'https://i.loli.net/2018/07/09/5b430773eaedc.jpeg', 'https://i.loli.net/2018/07/09/5b4307efb16f9.jpg', 'https://i.loli.net/2018/07/09/5b430819af3c6.jpg', '1', '汉口里以汉口开埠百年为脉络，复原晚清民初汉正街、大夹街、长堤街、花楼街代表性建筑、文化、商业形态，展现老汉口生活方式，同样是民国风情，相比楚河汉街的速成感，汉口里多了更多历史的沉淀和厚重感。', '光谷步行街外连世界城广场，是集购物消费、餐饮娱乐、旅游观光、休闲健身、商务办公、酒店居住于一体的、多功能、全业态、复合型超级商业步行街。', '武汉大学，简称“武大”，是一所中国著名的综合研究型大学,也是近代中国建立最早的国立大学。1893年，湖广总督张之洞上奏清政府设立自强学堂片，由此揭开了近代中国高等教育的序幕。', 'https://i.loli.net/2018/07/09/5b43085fec0f6.jpg', 'https://i.loli.net/2018/07/09/5b4308a2cce53.png', '黄鹤楼位于湖北省武汉市长江南岸的武昌蛇山之巅，濒临万里长江，是国家5A级旅游景区，自古享有“天下江山第一楼“和”天下绝景“之称。黄鹤楼是武汉市标志性建筑，与晴川阁、古琴台并称“武汉三大名胜”。', '马鞍山森林公园地处湖北省武汉市东郊，南界珞瑜东路，北濒东湖，东止九峰港，西临喻家湖，属于国家重点风景名胜区--东湖生态旅游风景区的吹笛景区。');
INSERT INTO `user_page_content` VALUES ('445637026', '', 'https://i.loli.net/2018/07/09/5b430c15bc8d6.jpg', 'https://i.loli.net/2018/07/09/5b430c6c4d179.jpg', '2', '热干面是中国十大面条之一，是湖北武汉最出名的小吃之一，有多种做法。以油、盐、芝麻酱、色拉油、香油、细香葱、大蒜子、量卤水汁、生抽为辅助材料。其色泽黄而油润，味道鲜美。', '作为全国科普教育基地、先进科技馆、国家级4A级旅游景区，武汉科技馆一直致力于面向社会公众传播和普及科学文化知识。', '武汉海昌极地海洋世界是海昌集团在华中地区建设的首个海洋世界，是第五座涵盖极地与海洋概念的大型主题公园，位于湖北省武汉市东西湖区。', 'https://i.loli.net/2018/07/09/5b430ca35e442.jpg', 'https://i.loli.net/2018/07/09/5b430cd0cbf92.jpg', '户部巷位于武汉市武昌区最繁华的司门口，东靠十里长街，西临浩瀚长江，南枕“天下江山第一楼”黄鹤楼，北接都府堤红色景区 ，是一处由名街名楼名景名江环绕而成的美食天堂。', '昙华林，位于湖北省武汉市武昌区西部，毗邻湖北中医药大学武昌校区，地处城墙内的花园山北麓与螃蟹岬南麓之间，随两山并行呈东西走向。');
INSERT INTO `user_page_content` VALUES ('1365480990', 'https://i.loli.net/2018/07/09/5b430d7ee2907.jpg', 'https://i.loli.net/2018/07/09/5b430dd49ac41.jpg', 'https://i.loli.net/2018/07/09/5b430e92c2997.jpg', '2', '江汉路步行街有“天下第一步行街”的美誉，位于湖北省武汉市汉口中心地带，是武汉著名的百年商业老街，也是“武汉二十世纪建筑博物馆”。', '华中科技大学是拥有国家实验室和国家大科学中心的四所大学之一，入选《Nature》评出的“中国十大科研机构”，被称作“新中国高等教育发展的缩影”。', '武汉东湖绿道位于武汉市东湖风景区内，是国内首条城区内5A级旅游景区绿道。东湖绿道全长101.98公里，宽6米，串联起东湖磨山、听涛、落雁、渔光、喻家湖五大景区。', 'https://i.loli.net/2018/07/09/5b430ec9dbbc3.jpg', 'https://i.loli.net/2018/07/09/5b430f51462a5.jpg', '辛亥革命博物馆位于武汉市武昌区阅马场首义广场南侧，是首义文化区的核心建筑，与武昌起义军政府旧址（红楼）、孙中山铜像、黄兴拜将台纪念', '江汉关博物馆是以收藏、保护、研究和展示与武汉近现代历史相关的各类文物、文献及实物为主，为社会及其发展服务的非营利性永久机构。');
INSERT INTO `user_page_content` VALUES ('4780494', 'https://i.loli.net/2018/07/09/5b4312ea9136e.jpg', 'https://i.loli.net/2018/07/09/5b431361d47fb.jpg', 'https://i.loli.net/2018/07/09/5b4313d9cfb8c.jpg', '2', '潜江国家森林公园座落于中国明星市──湖北省江汉地区潜江市的南城区，地处美丽富饶的江汉平原腹地，是1991年林业部批建的国家森林公园。潜江森林公园总面积100公顷。', '潜江油焖大虾制作过程的关键就在“油焖”二字上，用于烧制此菜的油每菜式约需七至八两，焖烧的时间在半小时以上。如此焖烧而成的大虾，色泽鲜艳耐看，香辣鲜各味俱全。', '返玩湖是湖北省潜江市境内最具有人文景观和水乡园林特色的集旅游、度假、休闲、垂钓、观赏于一体的风景区之一。', 'https://i.loli.net/2018/07/09/5b43141b310a4.jpg', 'https://i.loli.net/2018/07/09/5b431474929a5.jpg', '潜江市曹禺公园建设主要是利用马昌湖水林资源，将城市建设与城市防洪有机结合起来，建成以纪念曹禺为主，集休闲、旅游、娱乐、科普教育为一体的开敞式水景公园。', '江汉平原皮影的雕镂艺术，源于潜江的“汤格”和“郭格”，尤以图案精细、人物造型逼真与国内其它地区皮影造型比较，其工艺精细、造型生动、圆润舒展、以大见长，形成了独特的艺术风格。');
INSERT INTO `user_page_content` VALUES ('1018952520', 'https://i.loli.net/2018/07/11/5b45c7946bf18.jpg', null, null, '3', '东湖绿道', '', '', null, null, 'test', '');

-- ----------------------------
-- Table structure for `visit_info`
-- ----------------------------
DROP TABLE IF EXISTS `visit_info`;
CREATE TABLE `visit_info` (
  `userid` varchar(255) NOT NULL,
  `like_num` int(11) unsigned zerofill DEFAULT NULL,
  `report_num` int(11) unsigned zerofill DEFAULT NULL,
  `visit_num` int(11) unsigned zerofill DEFAULT NULL,
  `user_open_id` varchar(255) DEFAULT NULL,
  `comment_num` int(11) unsigned zerofill DEFAULT NULL,
  PRIMARY KEY (`userid`),
  KEY `FKivcerrflynuopsmeucykyd6li` (`user_open_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of visit_info
-- ----------------------------
INSERT INTO `visit_info` VALUES ('401129798', '00000000006', '00000000009', '00000000000', '401129798', '00000000008');
INSERT INTO `visit_info` VALUES ('274736996', '00000000005', '00000000000', '00000000000', '274736996', '00000000002');
INSERT INTO `visit_info` VALUES ('445637026', '00000000002', '00000000000', '00000000000', '445637026', '00000000003');
INSERT INTO `visit_info` VALUES ('1365480990', '00000000002', '00000000000', '00000000000', '1365480990', '00000000001');
INSERT INTO `visit_info` VALUES ('4780494', '00000000004', '00000000001', '00000000000', '4780494', '00000000002');
INSERT INTO `visit_info` VALUES ('1018952520', '00000000000', '00000000000', '00000000000', '1018952520', '00000000000');
INSERT INTO `visit_info` VALUES ('oJy764pnMqBJ98hE0aWU_Rz5UYYw', '00000000000', '00000000000', '00000000000', 'oJy764pnMqBJ98hE0aWU_Rz5UYYw', '00000000000');