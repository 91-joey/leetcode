package org.example.leetcode.problems.markov;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 勋章图鉴 markdown 格式化
public class BadgeTest {
  public static void main(String[] args) throws IOException {
    // region 学习计划广场
    // xueXiJiHuaGuangChang();
    // endregion
    
    format3("[\uD83C\uDFC6 第 331 场力扣周赛94.7K5 天前](https://leetcode.cn/circle/discuss/2Gdn7F/)[\uD83D\uDE3A 第 97 场力扣夜喵双周赛44.3K6 天前](https://leetcode.cn/circle/discuss/F06Kf0/)[\uD83C\uDFC6 第 330 场力扣周赛86.4K12 天前](https://leetcode.cn/circle/discuss/LWLEFc/)[\uD83C\uDFC6 第 329 场力扣周赛82.8K19 天前](https://leetcode.cn/circle/discuss/Kclgr5/)[\uD83D\uDE3A 第 96 场力扣夜喵双周赛43.1K20 天前](https://leetcode.cn/circle/discuss/33ZnsL/)[\uD83D\uDC30 卷“兔”重来，新春彩蛋等你来猜 ～ \uD83E\uDDE796K25 天前](https://leetcode.cn/circle/discuss/4bccdS/)[\uD83C\uDFC6 第 328 场力扣周赛55.7K1 个月前](https://leetcode.cn/circle/discuss/IAQlrp/)[\uD83C\uDFC6 第 327 场力扣周赛85K1 个月前](https://leetcode.cn/circle/discuss/jy4qll/)[\uD83D\uDE3A 第 95 场力扣夜喵双周赛43.1K1 个月前](https://leetcode.cn/circle/discuss/tfOW0k/)[\uD83C\uDFC6 第 326 场力扣周赛43.9K1 个月前](https://leetcode.cn/circle/discuss/TeTCFl/)[❤️ 一起来传递热气 ｜ 2022 特别感谢377.1K1 个月前](https://leetcode.cn/circle/discuss/0ZxsN6/)[力扣年度总结 ｜2022 硬实力 Code 出来！4350.6K1 个月前](https://leetcode.cn/circle/discuss/OVc5on/)[\uD83C\uDFC6 第 325 场力扣周赛34.3K2 个月前](https://leetcode.cn/circle/discuss/RmydJj/)[\uD83D\uDE3A 第 94 场力扣夜喵双周赛43.2K2 个月前](https://leetcode.cn/circle/discuss/YeBDQY/)[\uD83C\uDFC6 第 324 场力扣周赛42.8K2 个月前](https://leetcode.cn/circle/discuss/FRzp3e/)[\uD83C\uDFC6 第 323 场力扣周赛44.3K2 个月前](https://leetcode.cn/circle/discuss/qSroZL/)[\uD83D\uDE3A 第 93 场力扣夜喵双周赛104.2K2 个月前](https://leetcode.cn/circle/discuss/xgnLKJ/)[2022 程序员年终测试 | 10 道题测下你的「全栈攻城狮」指数多高97372 个月前](https://leetcode.cn/circle/discuss/N93QRq/)[\uD83C\uDFC6 第 322 场力扣周赛105.4K2 个月前](https://leetcode.cn/circle/discuss/T0eOvC/)[\uD83C\uDFC6 第 321 场力扣周赛75.2K2 个月前](https://leetcode.cn/circle/discuss/D1fgh9/)[\uD83D\uDE3A 第 92 场力扣夜喵双周赛52.9K2 个月前](https://leetcode.cn/circle/discuss/YBhcDT/)[\uD83C\uDFC6 第 320 场力扣周赛65.8K3 个月前](https://leetcode.cn/circle/discuss/bb9NEI/)[\uD83C\uDFC6 第 319 场力扣周赛65.7K3 个月前](https://leetcode.cn/circle/discuss/rYjnBt/)[\uD83D\uDE3A 第 91 场力扣夜喵双周赛44.1K3 个月前](https://leetcode.cn/circle/discuss/PWU4gi/)[\uD83C\uDFC6 第 318 场力扣周赛76.2K3 个月前](https://leetcode.cn/circle/discuss/HhkSf4/)[\uD83C\uDFC6 第 317 场力扣周赛66K3 个月前](https://leetcode.cn/circle/discuss/7A4iOi/)[\uD83D\uDE3A 第 90 场力扣夜喵双周赛43.4K3 个月前](https://leetcode.cn/circle/discuss/fL5HMZ/)[从零开始学算法：链表0674 个月前](https://leetcode.cn/circle/discuss/R49Z9Q/)[\uD83C\uDFC6 第 316 场力扣周赛89.5K4 个月前](https://leetcode.cn/circle/discuss/uO4WuN/)[\uD83C\uDFC6 第 315 场力扣周赛107.3K4 个月前](https://leetcode.cn/circle/discuss/RWOETn/)[\uD83D\uDE3A 第 89 场力扣夜喵双周赛34.4K4 个月前](https://leetcode.cn/circle/discuss/7RMPmn/)[「1024 · 马尔可夫链」活动规则1212.2K4 个月前](https://leetcode.cn/circle/discuss/JmX3M6/)[1024｜题库答题页 3.0 版本更新公告416.3K4 个月前](https://leetcode.cn/circle/discuss/58tYtc/)[1024 游戏攻略｜马尔可夫链，挑战不可能的运算16569K4 个月前](https://leetcode.cn/circle/discuss/OV9VUd/)[\uD83C\uDFC6 第 314 场力扣周赛116.1K4 个月前](https://leetcode.cn/circle/discuss/YNdXJT/)[\uD83C\uDFC6 力扣杯秋赛 - 战队赛讨论帖 LCCUP'22146.6K4 个月前](https://leetcode.cn/circle/discuss/FjxrUR/)[\uD83C\uDFC6 第 313 场力扣周赛56.9K4 个月前](https://leetcode.cn/circle/discuss/GDTqlB/)[\uD83D\uDE3A 第 88 场力扣夜喵双周赛65.1K4 个月前](https://leetcode.cn/circle/discuss/xoQe66/)[\uD83C\uDFC6 第 312 场力扣周赛108.7K5 个月前](https://leetcode.cn/circle/discuss/f2jB1Y/)[\uD83C\uDFC6 力扣杯秋赛 - 个人赛讨论帖 LCCUP'22198.1K5 个月前](https://leetcode.cn/circle/discuss/XP89Tp/)[\uD83C\uDFC6 第 311 场力扣周赛88.6K5 个月前](https://leetcode.cn/circle/discuss/svIPMw/)[\uD83D\uDE3A 第 87 场力扣夜喵双周赛65.4K5 个月前](https://leetcode.cn/circle/discuss/aylrop/)[\uD83C\uDFC6 第 310 场力扣周赛137.7K5 个月前](https://leetcode.cn/circle/discuss/s55Iwu/)[相约在高新｜数字经济算法编程大赛，欢迎参赛，入职杭州滨江！25.2K5 个月前](https://leetcode.cn/circle/discuss/VrDFIu/)[\uD83C\uDFC6 第 309 场力扣周赛610.2K5 个月前](https://leetcode.cn/circle/discuss/aro81T/)[\uD83D\uDE3A 第 86 场力扣夜喵双周赛66.4K5 个月前](https://leetcode.cn/circle/discuss/DYCjco/)[共码未来｜2022 Google 谷歌开发者大会现场限量门票开放报名中！103.6K5 个月前](https://leetcode.cn/circle/discuss/n8ZvJf/)[\uD83C\uDFC6 第 308 场力扣周赛68.4K5 个月前](https://leetcode.cn/circle/discuss/VGhL2Q/)[\uD83C\uDFC6 第 307 场力扣周赛710.1K6 个月前](https://leetcode.cn/circle/discuss/t6hE2C/)[\uD83D\uDE3A 第 85 场力扣夜喵双周赛45.6K6 个月前](https://leetcode.cn/circle/discuss/oZvJdG/)[\uD83C\uDFC6 第 306 场力扣周赛59.4K6 个月前](https://leetcode.cn/circle/discuss/U0Jf2X/)[2022 力扣秋招季｜码上来，刷新你的潜力01286 个月前](https://leetcode.cn/circle/discuss/Np0a0o/)[\uD83C\uDFC6 第 305 场力扣周赛910.9K6 个月前](https://leetcode.cn/circle/discuss/SdU3cY/)[\uD83D\uDE3A 第 84 场力扣夜喵双周赛76.5K6 个月前](https://leetcode.cn/circle/discuss/sT9OXv/)[\uD83C\uDFC6 第 304 场力扣周赛710.4K6 个月前](https://leetcode.cn/circle/discuss/uPC575/)[\uD83C\uDFC6 第 303 场力扣周赛58.6K7 个月前](https://leetcode.cn/circle/discuss/sWfXxC/)[\uD83D\uDE3A 第 83 场力扣夜喵双周赛27.1K7 个月前](https://leetcode.cn/circle/discuss/6jXNsy/)[\uD83C\uDFC6 第 302 场力扣周赛49.5K7 个月前](https://leetcode.cn/circle/discuss/BBxY1r/)[\uD83C\uDFC6 第 301 场力扣周赛811.3K7 个月前](https://leetcode.cn/circle/discuss/5t4W96/)[\uD83D\uDE3A 第 82 场力扣夜喵双周赛76.1K7 个月前](https://leetcode.cn/circle/discuss/TEQm0c/)[\uD83C\uDFC6 第 300 场力扣周赛118.5K7 个月前](https://leetcode.cn/circle/discuss/S2zplk/)[顺丰科技智慧物流校园技术挑战赛09157 个月前](https://leetcode.cn/circle/discuss/e1ldMR/)[LeetCode 公开笔试已启动！117.5K7 个月前](https://leetcode.cn/circle/discuss/pVZX4t/)[\uD83C\uDFC6 第 299 场力扣周赛118.5K7 个月前](https://leetcode.cn/circle/discuss/dDO6rQ/)[\uD83D\uDE3A 第 81 场力扣夜喵双周赛34.8K8 个月前](https://leetcode.cn/circle/discuss/Yj3lGZ/)[高手过招，放「码」出击 ｜杭州未来科技城 X 力扣编程大赛集结倒计时，「职」等你来2013.4K8 个月前](https://leetcode.cn/circle/discuss/3p5v9B/)[\uD83C\uDFC6 第 298 场力扣周赛79.3K8 个月前](https://leetcode.cn/circle/discuss/qovhgl/)[\uD83C\uDFC6 第 297 场力扣周赛88.6K8 个月前](https://leetcode.cn/circle/discuss/4GGKMb/)[\uD83D\uDE3A 第 80 场力扣夜喵双周赛45K8 个月前](https://leetcode.cn/circle/discuss/zEDpCN/)[\uD83C\uDFC6 第 296 场力扣周赛67.4K8 个月前](https://leetcode.cn/circle/discuss/twBVIo/)[LeetTalk 力扣说｜专访飞步科技系统平台负责人陈琰41.3K8 个月前](https://leetcode.cn/circle/discuss/O2yog9/)[\uD83C\uDFC6 第 295 场力扣周赛89.1K8 个月前](https://leetcode.cn/circle/discuss/E0MOm0/)[\uD83D\uDE3A 第 79 场力扣夜喵双周赛47.3K8 个月前](https://leetcode.cn/circle/discuss/17WFoy/)[5 月｜力扣线上公开课｜LeetLive 职场 100 米56.2K8 个月前](https://leetcode.cn/circle/discuss/J91hK5/)[\uD83C\uDFC6 第 294 场力扣周赛610.4K9 个月前](https://leetcode.cn/circle/discuss/sEESxJ/)[「力扣创作者」招募啦！！！82.3K9 个月前](https://leetcode.cn/circle/discuss/GV3Cja/)[\uD83C\uDFC6 第 293 场力扣周赛66.9K9 个月前](https://leetcode.cn/circle/discuss/0R1qMl/)[\uD83D\uDE3A 第 78 场力扣夜喵双周赛11.3K9 个月前](https://leetcode.cn/circle/discuss/DWGNjG/)[\uD83C\uDFC6 第 292 场力扣周赛1116.5K9 个月前](https://leetcode.cn/circle/discuss/GL2IJv/)[\uD83C\uDFC6 第 291 场力扣周赛58.3K9 个月前](https://leetcode.cn/circle/discuss/nf6j8j/)[\uD83D\uDE3A 第 77 场力扣夜喵双周赛65.7K9 个月前](https://leetcode.cn/circle/discuss/kmcKUJ/)[连续出击！「每日 1 题」从点亮火苗开始66.2K10 个月前](https://leetcode.cn/circle/discuss/ZNgGmq/)[\uD83C\uDFC6 第 290 场力扣周赛1410.3K10 个月前](https://leetcode.cn/circle/discuss/F9IBSj/)[\uD83C\uDFC6 力扣杯 春赛 - 战队赛讨论帖 LCCUP '2254.6K10 个月前](https://leetcode.cn/circle/discuss/CDwC2j/)[\uD83C\uDFC6 第 289 场力扣周赛48.5K10 个月前](https://leetcode.cn/circle/discuss/XxY039/)[\uD83D\uDE3A 第 76 场力扣夜喵双周赛24.7K10 个月前](https://leetcode.cn/circle/discuss/OHEEAI/)[\uD83C\uDFC6 力扣杯 春赛-个人赛讨论帖 LCCUP'2266.9K10 个月前](https://leetcode.cn/circle/discuss/pmrqnx/)[力扣&禅道｜《2021 年 IT 行业项目管理调查报告》重磅发布！334810 个月前](https://leetcode.cn/circle/discuss/zed59C/)[\uD83C\uDFC6 第 288 场力扣周赛69.4K10 个月前](https://leetcode.cn/circle/discuss/prAHWT/)[力扣春招季「早间阅读」汇总51.1K10 个月前](https://leetcode.cn/circle/discuss/moLwDQ/)[2022 力扣春招季刷题 PK ｜每日押题 第 28 天166810 个月前](https://leetcode.cn/circle/discuss/3QJuhF/)[2022 力扣春招季刷题 PK ｜每日押题 第 27 天058610 个月前](https://leetcode.cn/circle/discuss/jbQ1Ar/)[2022 力扣春招季刷题 PK ｜每日押题 第 26 天141710 个月前](https://leetcode.cn/circle/discuss/q6n6GO/)[2022 力扣春招季刷题 PK ｜每日押题 第 25 天042310 个月前](https://leetcode.cn/circle/discuss/sCWRuh/)[2022 力扣春招季刷题 PK ｜每日押题 第 24 天157810 个月前](https://leetcode.cn/circle/discuss/ZgbMDL/)[\uD83C\uDFC6 第 287 场力扣周赛814.1K10 个月前](https://leetcode.cn/circle/discuss/wYDWdz/)[\uD83D\uDE3A 第 75 场力扣夜喵双周赛18.3K10 个月前](https://leetcode.cn/circle/discuss/gkiO22/)[汇丰 HR 即将空降评论区，欢迎提出你的求职疑问｜力扣企业互动专场43.6K10 个月前](https://leetcode.cn/circle/discuss/Af2iOy/)[2022 力扣春招季｜图森未来，无人驾驶的先驱者03.6K10 个月前](https://leetcode.cn/circle/discuss/vtinfS/)[大华 HR 即将空降评论区，欢迎来提出你的求职疑问｜力扣企业互动专场22.1K10 个月前](https://leetcode.cn/circle/discuss/eT2CPc/)[2022 力扣春招季刷题 PK ｜每日押题 第 23 天025310 个月前](https://leetcode.cn/circle/discuss/zAI7Th/)[2022 力扣春招季刷题 PK ｜每日押题 第 22 天049410 个月前](https://leetcode.cn/circle/discuss/cfVk8T/)[2022 力扣春招季刷题 PK ｜每日押题 第 21 天047610 个月前](https://leetcode.cn/circle/discuss/DMx4m0/)[力扣企业互动专场｜禾赛科技，做「机器人的眼睛」42.4K10 个月前](https://leetcode.cn/circle/discuss/Qh3NiY/)[2022 力扣春招季刷题 PK ｜每日押题 第 20 天034510 个月前](https://leetcode.cn/circle/discuss/rhZTMo/)[2022 力扣春招季刷题 PK ｜每日押题 第 19 天055110 个月前](https://leetcode.cn/circle/discuss/Y2ttxW/)[2022 力扣春招季刷题 PK ｜每日押题 第 18 天070310 个月前](https://leetcode.cn/circle/discuss/Xsw5Hw/)[2022 力扣春招季刷题 PK ｜每日押题 第 17 天050110 个月前](https://leetcode.cn/circle/discuss/joIapE/)[力扣企业互动专场｜招商银行信用卡中心，欢迎投递\uD83D\uDC4F72.8K10 个月前](https://leetcode.cn/circle/discuss/F2DMxx/)[力扣企业互动专场｜地平线，人工智能计算平台的全球领导者63.8K10 个月前](https://leetcode.cn/circle/discuss/I9riGU/)[\uD83C\uDFC6 第 286 场力扣周赛611.9K10 个月前](https://leetcode.cn/circle/discuss/LRecna/)[2022 力扣春招季刷题 PK ｜每日押题 第 16 天14631 年前](https://leetcode.cn/circle/discuss/15WwBG/)[2022 力扣春招季刷题 PK ｜每日押题 第 15 天05321 年前](https://leetcode.cn/circle/discuss/mgd2tZ/)[2022 力扣春招季刷题 PK ｜每日押题 第 14 天03611 年前](https://leetcode.cn/circle/discuss/xavm6F/)[2022 力扣春招季刷题 PK ｜每日押题 第 13 天05291 年前](https://leetcode.cn/circle/discuss/NW3nOc/)[2022 力扣春招季｜走近恒生新力量12.9K1 年前](https://leetcode.cn/circle/discuss/pvHvL7/)[2022 力扣春招季｜趋势科技，到底是什么科技？33.1K1 年前](https://leetcode.cn/circle/discuss/ZXyo4p/)[2022 力扣春招季刷题 PK ｜每日押题 第 12 天01.3K1 年前](https://leetcode.cn/circle/discuss/EWTOxl/)[2022 力扣春招季刷题 PK ｜每日押题 第 11 天11.5K1 年前](https://leetcode.cn/circle/discuss/I7q8Fo/)[2022 力扣春招季刷题 PK ｜每日押题 第 10 天08961 年前](https://leetcode.cn/circle/discuss/QB1N5R/)[八股文 ｜ 前端通关手册：JavaScript72K1 年前](https://leetcode.cn/circle/discuss/GBG0V9/)[八股文 ｜ Java 面试突击21.4K1 年前](https://leetcode.cn/circle/discuss/Xe3KGN/)[2022 力扣春招季刷题 PK ｜每日押题 第 9 天19251 年前](https://leetcode.cn/circle/discuss/M4CAKk/)[八股文 ｜ C++ 面试突击264.8K1 年前](https://leetcode.cn/circle/discuss/cQIVro/)[八股文 ｜ 数据库知识手册152.5K1 年前](https://leetcode.cn/circle/discuss/4j1Cv5/)[\uD83C\uDFC6 第 285 场力扣周赛611.1K1 年前](https://leetcode.cn/circle/discuss/P2iS4B/)[\uD83D\uDE3A 第 74 场力扣夜喵双周赛812K1 年前](https://leetcode.cn/circle/discuss/bdMSIh/)[八股文 ｜ 计算机网络面试突击123.7K1 年前](https://leetcode.cn/circle/discuss/KEgIx9/)[2022 力扣春招季刷题 PK ｜每日押题 第 8 天31.3K1 年前](https://leetcode.cn/circle/discuss/I8pibv/)[2022 力扣春招季刷题 PK ｜每日押题 第 7 天19621 年前](https://leetcode.cn/circle/discuss/X1OO8j/)[力扣春招季 ｜ 「个人主页」全 “心” 发布176.8K1 年前](https://leetcode.cn/circle/discuss/xa3iVR/)[2022 力扣春招季刷题 PK ｜每日押题 第 6 天21.2K1 年前](https://leetcode.cn/circle/discuss/drIypz/)[2022 力扣春招季刷题 PK ｜每日押题 第 5 天01K1 年前](https://leetcode.cn/circle/discuss/F8QKrV/)[2022 力扣春招季｜和安恒信息一起，在这个春天成为网络安全的守护者！23.3K1 年前](https://leetcode.cn/circle/discuss/c5MmFW/)[2022 力扣春招季｜招商银行 · 招银网络科技：招手梦想，银接未来14.4K1 年前](https://leetcode.cn/circle/discuss/WkZvLA/)[2022 春招季力扣企业互动专场｜走近海康威视，未来视不可挡36.5K1 年前](https://leetcode.cn/circle/discuss/u4Dwky/)[3 月｜力扣线上公开课｜LeetLive 春招开讲！15.2K1 年前](https://leetcode.cn/circle/discuss/PajibH/)[2022 力扣春招季刷题 PK ｜每日押题 第 4 天31.3K1 年前](https://leetcode.cn/circle/discuss/iw3C6J/)[2022 力扣春招季刷题 PK ｜每日押题 第 3 天11.6K1 年前](https://leetcode.cn/circle/discuss/bEKSRl/)[2022 力扣春招季刷题 PK ｜每日押题 第 2 天11.8K1 年前](https://leetcode.cn/circle/discuss/Mb4JqC/)[2022 力扣春招季刷题 PK ｜每日押题 第 1 天25.4K1 年前](https://leetcode.cn/circle/discuss/enVkir/)[\uD83C\uDFC6 第 284 场力扣周赛917K1 年前](https://leetcode.cn/circle/discuss/3PMerp/)[2022 力扣春招季｜走近蚂蚁集团 ，让数字生活触手可及86.1K1 年前](https://leetcode.cn/circle/discuss/qa21O5/)[力扣春招季 ｜ 刷题 PK 攻略66.2K1 年前](https://leetcode.cn/circle/discuss/pQHyKH/)[「内卷二进制」小队，集结！｜2022 力扣春招季297.8K1 年前](https://leetcode.cn/circle/discuss/ajMGGB/)[「伪躺平集合」小队，集结！｜2022 力扣春招季179K1 年前](https://leetcode.cn/circle/discuss/SsucoG/)[「假装摆烂堆」小队，集结！｜2022 力扣春招季4713.6K1 年前](https://leetcode.cn/circle/discuss/m7XJbx/)[「超残酷变换」小队，集结！｜2022 力扣春招季174.5K1 年前](https://leetcode.cn/circle/discuss/IGSvrT/)[「闷骚多线程」小队，集结！｜2022 力扣春招季446.5K1 年前](https://leetcode.cn/circle/discuss/JOY0tA/)[\uD83C\uDFC6 第 283 场力扣周赛211.4K1 年前](https://leetcode.cn/circle/discuss/c8HUDh/)[\uD83D\uDE3A 第 73 场力扣夜喵双周赛18.3K1 年前](https://leetcode.cn/circle/discuss/9WhoB7/)[2022 力扣春招季｜科学备战，offer 手到擒来3332K1 年前](https://leetcode.cn/circle/discuss/iulaqR/)[有奖征文｜2022 力扣春招季征文活动赛区52.8K1 年前](https://leetcode.cn/circle/discuss/n7m0Rb/)[2022 力扣春招季活动规则1536.1K1 年前](https://leetcode.cn/circle/discuss/VTL4K5/)[\uD83C\uDF81 面经锦囊｜春招人 人手必备！173K1 年前](https://leetcode.cn/circle/discuss/JXGvtD/)[2022 力扣春招季｜走近 vivo ，共享手机的人文之悦97.2K1 年前](https://leetcode.cn/circle/discuss/3pEgIj/)[\uD83C\uDFC6 第 282 场力扣周赛410.9K1 年前](https://leetcode.cn/circle/discuss/eU8bPl/)[活动预告｜2022 春招季，一起来为自己的战队名字投票吧！01031 年前](https://leetcode.cn/circle/discuss/ZtbVmQ/)[\uD83C\uDF81 面经锦囊｜春招人人手必备！03121 年前](https://leetcode.cn/circle/discuss/xD5Kfr/)[\uD83C\uDF81面经锦囊｜春招人人手必备！081 年前](https://leetcode.cn/circle/discuss/Pm0urc/)[\uD83C\uDFC6 第 281 场力扣周赛1015K1 年前](https://leetcode.cn/circle/discuss/76BnS1/)[\uD83D\uDE3A 第 72 场力扣夜喵双周赛68.8K1 年前](https://leetcode.cn/circle/discuss/dinFl3/)[力扣漫画小剧场｜一个有点狠的程序员......51.2K1 年前](https://leetcode.cn/circle/discuss/XOvnNT/)[活动预告｜2022 春招季，能量补给站 - LeetLive 春招开讲！159.7K1 年前](https://leetcode.cn/circle/discuss/MDdl4Z/)[活动预告｜2022 春招季，一起来为自己的战队名字投票吧！32101 年前](https://leetcode.cn/circle/discuss/LJbS8W/)[\uD83C\uDFC6 第 280 场力扣周赛811.8K1 年前](https://leetcode.cn/circle/discuss/oA57vT/)[\uD83C\uDFC6 第 279 场力扣周赛1813.7K1 年前](https://leetcode.cn/circle/discuss/wSJV1A/)[\uD83D\uDE3A 第 71 场力扣夜喵双周赛16.9K1 年前](https://leetcode.cn/circle/discuss/QkPiSo/)[\uD83C\uDFC6 第 278 场力扣周赛68.3K1 年前](https://leetcode.cn/circle/discuss/UECpIR/)[\uD83D\uDC2F内有萌虎，小心慎点\uD83E\uDDE71912.9K1 年前](https://leetcode.cn/circle/discuss/IwxOhN/)[\uD83C\uDFC6 第 277 场力扣周赛410.9K1 年前](https://leetcode.cn/circle/discuss/S7rn0B/)[\uD83D\uDE3A 第 70 场力扣夜喵双周赛26.7K1 年前](https://leetcode.cn/circle/discuss/brv5Kn/)[\uD83C\uDFC6 第 276 场力扣周赛48.5K1 年前](https://leetcode.cn/circle/discuss/iozPw7/)[\uD83C\uDFC6 第 275 场力扣周赛17.8K1 年前](https://leetcode.cn/circle/discuss/9TMMfX/)[\uD83D\uDE3A 第 69 场力扣夜喵双周赛65.8K1 年前](https://leetcode.cn/circle/discuss/IqKOXp/)[\uD83C\uDFC6 第 274 场力扣周赛27.8K1 年前](https://leetcode.cn/circle/discuss/PS7Stz/)[\uD83C\uDFC6你的力扣 2021 年度报告待查收\uD83D\uDCE9！3226.2K1 年前](https://leetcode.cn/circle/discuss/OWvlnD/)[力扣 LeetCode ❤️ 腾讯 SaaS 加速器08801 年前](https://leetcode.cn/circle/discuss/5v4YnD/)[\uD83C\uDFC6 第 273 场力扣周赛57.8K1 年前](https://leetcode.cn/circle/discuss/NS4Y2j/)[\uD83D\uDE3A 第 68 场力扣夜喵双周赛07.6K1 年前](https://leetcode.cn/circle/discuss/TNI0Rm/)[快戳我！力扣邀你一起跨年倒计时！02001 年前](https://leetcode.cn/circle/discuss/UFjHSq/)[快戳我！力扣邀你一起跨年倒计时！4621.5K1 年前](https://leetcode.cn/circle/discuss/9qUDFV/)[2022 一起来组队刷题吧！力扣新年自习室，限时开启～[自习室已满员，但我们承诺，下次一定\\]147.6K1 年前](https://leetcode.cn/circle/discuss/uwE7QB/)[\uD83C\uDFC6 第 272 场力扣周赛109.3K1 年前](https://leetcode.cn/circle/discuss/s1k590/)[\uD83C\uDFC6 第 271 场力扣周赛27.3K1 年前](https://leetcode.cn/circle/discuss/MRfrww/)[\uD83D\uDE3A 第 67 场力扣夜喵双周赛57.9K1 年前](https://leetcode.cn/circle/discuss/i3yewh/)[\uD83C\uDFC6 第 270 场力扣周赛611.2K1 年前](https://leetcode.cn/circle/discuss/PrR8pS/)[\uD83C\uDFC6 第 269 场力扣周赛99.6K1 年前](https://leetcode.cn/circle/discuss/S0NvzF/)[\uD83D\uDE3A 第 66 场力扣夜喵双周赛45.8K1 年前](https://leetcode.cn/circle/discuss/jPIdc4/)[\uD83C\uDFC6 第 268 场力扣周赛710K1 年前](https://leetcode.cn/circle/discuss/sfeav2/)[过算法难关，拿心动 Offer！78.3K1 年前](https://leetcode.cn/circle/discuss/ND4RiX/)[\uD83C\uDFC6 第 267 场力扣周赛67.3K1 年前](https://leetcode.cn/circle/discuss/8ZKXOF/)[\uD83D\uDE3A 第 65 场力扣夜喵双周赛16.3K1 年前](https://leetcode.cn/circle/discuss/cj4dO9/)[\uD83D\uDCAA\uD83C\uDFFB备战春招｜秋招复盘斩获大厂 offer 的制胜秘籍2010.7K1 年前](https://leetcode.cn/circle/discuss/hM22jE/)[\uD83C\uDFC6 第 266 场力扣周赛57.5K1 年前](https://leetcode.cn/circle/discuss/JXlHA8/)[\uD83C\uDFC6 第 265 场力扣周赛39.4K1 年前](https://leetcode.cn/circle/discuss/ANhvKQ/)[\uD83D\uDE3A 第 64 场力扣夜喵双周赛26.3K1 年前](https://leetcode.cn/circle/discuss/HveixS/)[数据库知识手册01661 年前](https://leetcode.cn/circle/discuss/kRJ828/)[\uD83C\uDFC6 第 264 场力扣周赛37K1 年前](https://leetcode.cn/circle/discuss/NZT0w3/)[\uD83C\uDF81 1024 程序员节 —— 「 人生重开模拟 」补完计划 \uD83C\uDF89516K1 年前](https://leetcode.cn/circle/discuss/1gLEYo/)[1024 力扣会员节｜极客狂欢 「 码 」上开启3322.4K1 年前](https://leetcode.cn/circle/discuss/B0TtLX/)[\uD83C\uDFC6 第 263 场力扣周赛38.9K1 年前](https://leetcode.cn/circle/discuss/89Uy1V/)[\uD83D\uDE3A 第 63 场力扣夜喵双周赛36.4K1 年前](https://leetcode.cn/circle/discuss/kMf7yn/)[\uD83C\uDFC6 第 262 场力扣周赛88.8K1 年前](https://leetcode.cn/circle/discuss/2ELXyY/)[\uD83C\uDFC6 第 261 场力扣周赛35.7K1 年前](https://leetcode.cn/circle/discuss/KxNBfh/)[\uD83D\uDE3A 第 62 场力扣夜喵双周赛15.6K1 年前](https://leetcode.cn/circle/discuss/YiDO1x/)[2021 力扣秋招季｜美团「帮大家吃得更好，生活更好」01.2K1 年前](https://leetcode.cn/circle/discuss/PLr6aC/)[2021 力扣秋招季｜京东「成为全球最值得信赖的企业」21.6K1 年前](https://leetcode.cn/circle/discuss/yN8L8M/)[2021 力扣秋招季｜贝壳找房「服务三亿家庭的品质居住」42.7K1 年前](https://leetcode.cn/circle/discuss/mT4Lv1/)[2021 力扣秋招季｜Shopee「每一个你都能掀起波澜」12.4K1 年前](https://leetcode.cn/circle/discuss/Z93QEp/)[\uD83C\uDFC6 第 260 场力扣周赛86.5K1 年前](https://leetcode.cn/circle/discuss/uxwWwd/)[\uD83C\uDFC6 力扣杯 秋赛-战队赛讨论帖 LCCUP ‘2156.7K1 年前](https://leetcode.cn/circle/discuss/sLFkxv/)[2021 力扣秋招季｜商汤 SenseTime「坚持原创，让AI引领人类进步！」12K1 年前](https://leetcode.cn/circle/discuss/lgXTW4/)[2021 力扣秋招季｜西安葡萄城「赋能开发者」74.5K1 年前](https://leetcode.cn/circle/discuss/vqxGsi/)[2021 力扣秋招季｜恒生电子「让金融变简单」23K1 年前](https://leetcode.cn/circle/discuss/sMxHMx/)[2021 力扣秋招季｜大华股份「让社会更安全，让生活更智能」32K1 年前](https://leetcode.cn/circle/discuss/nHysMh/)[\uD83C\uDFC6 第 259 场力扣周赛17.6K1 年前](https://leetcode.cn/circle/discuss/pmjXoM/)[\uD83D\uDE3A 第 61 场力扣夜喵双周赛14.4K1 年前](https://leetcode.cn/circle/discuss/Uo2tRO/)[「求职日记」｜全套力扣周边带回家！104.3K1 年前](https://leetcode.cn/circle/discuss/i3zrmy/)[\uD83C\uDFAC 对话“教主”楼天城：编程天才与创业老板，哪个才是真实的他？| 程序员故事第八期4812.1K1 年前](https://leetcode.cn/circle/discuss/GZugPl/)[2021 力扣秋招季｜Offer 无惧平庸，实力 Code 出来！2027.1K1 年前](https://leetcode.cn/circle/discuss/yYPuaA/)[2021 力扣秋招季｜小红书「分享和发现世界的精彩」107.1K1 年前](https://leetcode.cn/circle/discuss/GtqkE4/)[2021 力扣秋招季｜字节跳动「和优秀的人，做有挑战的事」63.5K1 年前](https://leetcode.cn/circle/discuss/RBsaK9/)[2021 力扣秋招季｜bilibili「和我们一起生产快乐！」2210.6K1 年前](https://leetcode.cn/circle/discuss/4rscmW/)[\uD83C\uDFC6 第 258 场力扣周赛69K1 年前](https://leetcode.cn/circle/discuss/qfdpX8/)[\uD83C\uDFC6 力扣杯 秋赛-个人赛讨论帖 LCCUP ‘2167.5K1 年前](https://leetcode.cn/circle/discuss/SQBKGb/)[\uD83D\uDCF1 力扣「面试官版」全新发布，开启技术面试新体验~91.5K1 年前](https://leetcode.cn/circle/discuss/NmSyZa/)[\uD83D\uDD25楼教主喊你看直播！力扣杯独家经验今晚首次分享！95.7K1 年前](https://leetcode.cn/circle/discuss/fRMod1/)[\uD83C\uDFC6 第 257 场力扣周赛48.4K1 年前](https://leetcode.cn/circle/discuss/ktLQXn/)[\uD83D\uDE3A 第 60 场力扣夜喵双周赛04.9K1 年前](https://leetcode.cn/circle/discuss/ZJvAkq/)[\uD83D\uDCF8 想成为力扣校园代言人？「校园之星」等你出道！102.6K1 年前](https://leetcode.cn/circle/discuss/TZc8DX/)[\uD83C\uDFC6 第 256 场力扣周赛17.7K1 年前](https://leetcode.cn/circle/discuss/be6FyL/)[\uD83C\uDFC6 第 255 场力扣周赛49K1 年前](https://leetcode.cn/circle/discuss/zrXSTP/)[\uD83D\uDE3A 第 59 场力扣夜喵双周赛66.4K1 年前](https://leetcode.cn/circle/discuss/paT4GG/)[招商银行｜力扣秋招内推专区 \uD83D\uDCE261.9K1 年前](https://leetcode.cn/circle/discuss/N5lxbm/)[华为｜力扣秋招内推专区 \uD83D\uDCE26218.1K1 年前](https://leetcode.cn/circle/discuss/4GtVxy/)[网易｜力扣秋招内推专区 \uD83D\uDCE262.7K1 年前](https://leetcode.cn/circle/discuss/v9sYys/)[米哈游｜力扣秋招内推专区 \uD83D\uDCE2121.8K1 年前](https://leetcode.cn/circle/discuss/N02bRO/)[携程｜力扣秋招内推专区 \uD83D\uDCE242.1K1 年前](https://leetcode.cn/circle/discuss/YsI4I4/)[字节跳动｜力扣秋招内推专区 \uD83D\uDCE23012.2K1 年前](https://leetcode.cn/circle/discuss/dIHVyu/)[美团｜力扣秋招内推专区 \uD83D\uDCE2107.3K1 年前](https://leetcode.cn/circle/discuss/pHcV3b/)[\uD83C\uDFC6 第 254 场力扣周赛59.1K1 年前](https://leetcode.cn/circle/discuss/xzKfhg/)[\uD83C\uDFC6 力扣杯进行时\uD83D\uDD25你参加了吗？1518.5K1 年前](https://leetcode.cn/circle/discuss/bzwAEJ/)[公告｜力扣官网遭恶意攻击情况声明8025.6K1 年前](https://leetcode.cn/circle/discuss/B5ts6s/)[\uD83C\uDFC6 第 253 场力扣周赛48.3K2 年前](https://leetcode.cn/circle/discuss/avaR15/)[\uD83D\uDE3A 第 58 场力扣夜喵双周赛05K2 年前](https://leetcode.cn/circle/discuss/9suuWs/)[\uD83C\uDFC6 第 252 场力扣周赛26.5K2 年前](https://leetcode.cn/circle/discuss/pAXboA/)[对话 ACM 选手张晴川：非科班出身打竞赛、进微软是怎样一种体验？| 程序员故事第六期249.2K2 年前](https://leetcode.cn/circle/discuss/rf8NnP/)[\uD83C\uDFC6 第 251 场力扣周赛48.6K2 年前](https://leetcode.cn/circle/discuss/zesmlZ/)[\uD83D\uDC31第 57 场力扣夜喵双周赛64.4K2 年前](https://leetcode.cn/circle/discuss/pbqHVB/)[\uD83C\uDFC6 第 250 场力扣周赛08.3K2 年前](https://leetcode.cn/circle/discuss/8o9q5h/)[\uD83C\uDFC6 第 249 场力扣周赛57.6K2 年前](https://leetcode.cn/circle/discuss/U8tCN1/)[\uD83D\uDC31 第 56 场力扣夜喵双周赛05.2K2 年前](https://leetcode.cn/circle/discuss/KASKbW/)[\uD83C\uDFAC VEX 世锦赛冠军是怎样炼成的 | 程序员故事第五期434.6K2 年前](https://leetcode.cn/circle/discuss/h8JSEz/)[\uD83C\uDFC6 第 248 场力扣周赛143.1K2 年前](https://leetcode.cn/circle/discuss/gxll1S/)[\uD83C\uDFAC 双非无 ACM 竞赛经验，我是如何拿到字节 Offer 的？【彩蛋版】程序员故事第四期169.2K2 年前](https://leetcode.cn/circle/discuss/L8Arqq/)[\uD83C\uDFAC 程序员故事第三期：平平无奇打工人靠什么升职加薪？算法工程师晋升心得分享37002 年前](https://leetcode.cn/circle/discuss/CMQ2Wv/)[\uD83C\uDFAC 程序员故事第二期：想当一名算法竞赛选手需要什么水平？45602 年前](https://leetcode.cn/circle/discuss/hQEMgq/)[\uD83C\uDFAC 程序员故事第一期：百万播放博主的程序媛为什么会选择裸辞回国？49412 年前](https://leetcode.cn/circle/discuss/RPSRmx/)[\uD83C\uDFC6 第 247 场力扣周赛65.9K2 年前](https://leetcode.cn/circle/discuss/6LY3tn/)[\uD83D\uDC31 第 55 场夜喵双周赛25.9K2 年前](https://leetcode.cn/circle/discuss/SwtoM2/)[\uD83D\uDE97 LeetCamp 算法集训营开课！暑期弯道超车，这个夏天燃起来168.6K2 年前](https://leetcode.cn/circle/discuss/9mV516/)[\uD83C\uDFC6 第 246 场力扣周赛35.9K2 年前](https://leetcode.cn/circle/discuss/gKg6Bb/)[「微爱思扣 以 code 会友」专场竞赛03K2 年前](https://leetcode.cn/circle/discuss/PrMLYc/)[\uD83C\uDF87 夏日大作战！程序员如何度过一个高质量的暑假？169.6K2 年前](https://leetcode.cn/circle/discuss/6X0t6m/)[\uD83C\uDFC6 第 245 场力扣周赛16.1K2 年前](https://leetcode.cn/circle/discuss/BNdR4T/)[\uD83D\uDC31 第 54 场夜喵双周赛23.6K2 年前](https://leetcode.cn/circle/discuss/EJ1sMC/)[\uD83D\uDCE2 力扣题库页持续优化中，期待您的反馈！1419.8K2 年前](https://leetcode.cn/circle/discuss/Vp1j4A/)[\uD83C\uDFC6 第 244 场力扣周赛441.9K2 年前](https://leetcode.cn/circle/discuss/pUUZqK/)[高频面试题 ｜进 IT 名企需要刷多少道算法题？2813.3K2 年前](https://leetcode.cn/circle/discuss/Ie4zbP/)[\uD83C\uDFC6 第 243 场力扣周赛56.4K2 年前](https://leetcode.cn/circle/discuss/ZWw2ds/)[\uD83D\uDC31 第 53 场力扣双周赛44.8K2 年前](https://leetcode.cn/circle/discuss/DV7tEb/)[\uD83C\uDFC6 第 242 场力扣周赛77.3K2 年前](https://leetcode.cn/circle/discuss/icqulQ/)[秋招面试突击 ：「算法充电包」\uD83D\uDD0B 助你高效提升55.8K2 年前](https://leetcode.cn/circle/discuss/7BsCde/)[\uD83D\uDD25 App 也能敲代码，让你无时无处「码不停题」，iPadOS 有惊喜！6311.4K2 年前](https://leetcode.cn/circle/discuss/KU643r/)[\uD83D\uDC1E 你有 BUG 我来帮 - 力扣互动答疑季1811.5K2 年前](https://leetcode.cn/circle/discuss/xtliW6/)[\uD83C\uDFC6 第 241 场力扣周赛46K2 年前](https://leetcode.cn/circle/discuss/COaQbh/)[\uD83D\uDC31 第 52 场夜喵双周赛35.3K2 年前](https://leetcode.cn/circle/discuss/fPd2AU/)[递归和分治精讲01252 年前](https://leetcode.cn/circle/discuss/7JxsyZ/)[\uD83C\uDFC6 第 240 场力扣周赛36.7K2 年前](https://leetcode.cn/circle/discuss/7QlTIV/)[\uD83C\uDFC6 第 239 场力扣周赛76K2 年前](https://leetcode.cn/circle/discuss/FLkX52/)[\uD83D\uDC31 第 51 场夜喵双周赛55.3K2 年前](https://leetcode.cn/circle/discuss/MAuekp/)[\uD83C\uDFC6 第 238 场力扣周赛46.5K2 年前](https://leetcode.cn/circle/discuss/zwlesb/)[\uD83D\uDCDA 开启世界读书日「限时阅读」，让你的时间更有价值！2818.7K2 年前](https://leetcode.cn/circle/discuss/12QtuI/)[\uD83C\uDFC6 第 237 场力扣周赛29.3K2 年前](https://leetcode.cn/circle/discuss/GKzVUp/)[\uD83D\uDC31 第 50 场夜喵双周赛25.5K2 年前](https://leetcode.cn/circle/discuss/oCBeGc/)[\uD83C\uDFC6 第 236 场力扣周赛57.9K2 年前](https://leetcode.cn/circle/discuss/mSC2JS/)[\uD83C\uDFC6 LCCUP ‘21 力扣杯春季编程大赛 - 战队赛38.4K2 年前](https://leetcode.cn/circle/discuss/CMSZlB/)[\uD83C\uDFC6 LCCUP ‘21 力扣杯春季编程大赛 - 个人赛712K2 年前](https://leetcode.cn/circle/discuss/bnnNog/)[\uD83C\uDFC6 第 235 场力扣周赛55.8K2 年前](https://leetcode.cn/circle/discuss/4dT5t6/)[\uD83D\uDC31 第 49 场夜喵双周赛37.1K2 年前](https://leetcode.cn/circle/discuss/IenTmw/)[\uD83D\uDECD 东南亚电商新星需要怎样的工程师？走近 Shopee87.7K2 年前](https://leetcode.cn/circle/discuss/zfMhrP/)[\uD83D\uDCFA 力扣杯前瞻直播来袭！往届冠军 Zerotrac 首次出镜分享64.7K2 年前](https://leetcode.cn/circle/discuss/B70CMO/)[\uD83C\uDFC6 第 234 场力扣周赛37K2 年前](https://leetcode.cn/circle/discuss/qw50NJ/)[\uD83C\uDFC6 第 233 场力扣周赛07K2 年前](https://leetcode.cn/circle/discuss/RAHcuH/)[\uD83D\uDC31 第 48 场夜喵双周赛05.6K2 年前](https://leetcode.cn/circle/discuss/k0epQJ/)[\uD83D\uDE9B 亲手打造无人驾驶系统是怎样一种体验？图森未来大揭秘 \uD83D\uDCF82610.3K2 年前](https://leetcode.cn/circle/discuss/wHJ5oa/)[\uD83C\uDF99️2021 技术人求职记第 1 期 | ✈️ 我要上推荐1218K2 年前](https://leetcode.cn/circle/discuss/k568iT/)[如何在「意见反馈」中向我们提出建议和反馈？63.1K2 年前](https://leetcode.cn/circle/discuss/okSijB/)[如何在「技术交流」中发布一篇帖子？603.7K2 年前](https://leetcode.cn/circle/discuss/doRn4M/)[如何在「职场与内推」中发布一篇帖子？355.6K2 年前](https://leetcode.cn/circle/discuss/b1mQd9/)[如何在「求职面试」中发布一篇帖子？10722.1K2 年前](https://leetcode.cn/circle/discuss/Yz1tD0/)[\uD83C\uDFC6 第 232 场力扣周赛18.2K2 年前](https://leetcode.cn/circle/discuss/VDhTB1/)[\uD83C\uDFC6 LCCUP '21 力扣杯邀你出道上岸拿 Offer ！3321.2K2 年前](https://leetcode.cn/circle/discuss/ySM1TG/)[\uD83C\uDFC6 第 231 场力扣周赛26.9K2 年前](https://leetcode.cn/circle/discuss/YSVet8/)[\uD83D\uDC31 第 47 场夜喵双周赛25K2 年前](https://leetcode.cn/circle/discuss/JygU8U/)[\uD83C\uDFC6 第 230 场力扣周赛46.9K2 年前](https://leetcode.cn/circle/discuss/9IeW8r/)[\uD83C\uDFC6 第 229 场力扣周赛47.2K2 年前](https://leetcode.cn/circle/discuss/e1GgP2/)[\uD83D\uDC31 第 46 场夜喵双周赛03.5K2 年前](https://leetcode.cn/circle/discuss/cRFew6/)[面试突击\uD83D\uDC49 把知识装进口袋\uD83D\uDCF1春招知识补完计划启动99.8K2 年前](https://leetcode.cn/circle/discuss/nMOpBb/)[\uD83C\uDF89 力扣官方交流群成立 \uD83E\uDD29 和扣友们一起开启春招冲刺135.9K2 年前](https://leetcode.cn/circle/discuss/AlG2gS/)[\uD83C\uDFC6 第 228 场力扣周赛25.3K2 年前](https://leetcode.cn/circle/discuss/l3HxwJ/)[\uD83C\uDFC6 第 227 场力扣周赛38.2K2 年前](https://leetcode.cn/circle/discuss/Mz3SVD/)[\uD83D\uDC31 第 45 场夜喵双周赛63.1K2 年前](https://leetcode.cn/circle/discuss/bhlHS0/)[给 \uD83D\uDC02 牛年加码 - 程序员如何硬核过春节？\uD83C\uDF891610.7K2 年前](https://leetcode.cn/circle/discuss/0RPGkB/)[朋克打工人队 - 【见证你的 New Year 牛业成长】2614.8K2 年前](https://leetcode.cn/circle/discuss/Blco1U/)[硬核在校生队 -【见证你的 New Year 牛业成长】3020.3K2 年前](https://leetcode.cn/circle/discuss/llnDMt/)[\uD83C\uDFC6 第 226 场力扣周赛27.4K2 年前](https://leetcode.cn/circle/discuss/eGjGvo/)[2021 春招冲刺攻略02002 年前](https://leetcode.cn/circle/discuss/hKrITL/)[\uD83C\uDFC6 第 225 场力扣周赛87.1K2 年前](https://leetcode.cn/circle/discuss/s1F28R/)[\uD83D\uDC31 第 44 场夜喵双周赛24.5K2 年前](https://leetcode.cn/circle/discuss/VusLoK/)[\uD83C\uDFC6 第 224 场力扣周赛78.6K2 年前](https://leetcode.cn/circle/discuss/8m9raf/)[✅ 春招学习计划正式开启！向着 Dream Offer 打卡吧！14456.4K2 年前](https://leetcode.cn/circle/discuss/iUZEHo/)[\uD83D\uDC8E 力扣「声望等级」正式上线，快来查看升级攻略！150118.8K2 年前](https://leetcode.cn/circle/discuss/urLPnC/)[\uD83D\uDCDA《网络是怎样连接的》打卡阅读全新 LeetBook 赢奖励 \uD83C\uDF81127.5K2 年前](https://leetcode.cn/circle/discuss/11Cmaw/)[\uD83C\uDFC6 第 223 场力扣周赛65.3K2 年前](https://leetcode.cn/circle/discuss/aozkiz/)[\uD83D\uDC31 第 43 场夜喵双周赛53.8K2 年前](https://leetcode.cn/circle/discuss/GhIDBi/)[\uD83C\uDFC6 第 222 场力扣周赛55.3K2 年前](https://leetcode.cn/circle/discuss/u5Fn3P/)[\uD83D\uDCF1 力扣 App 2.0 全新来袭 - \uD83C\uDD95 新功能、新体验、喜迎 2021，快来体验！7221.6K2 年前](https://leetcode.cn/circle/discuss/CHVlSh/)[\uD83C\uDFC6 第 221 场力扣周赛14.9K2 年前](https://leetcode.cn/circle/discuss/KyAmtm/)[\uD83D\uDC31 第 42 场夜喵双周赛03.3K2 年前](https://leetcode.cn/circle/discuss/4eOyfU/)[\uD83D\uDCD6 跨年趣题讨论：Q69 蓝白歌会54.4K2 年前](https://leetcode.cn/circle/discuss/Af4TSb/)[✌️跨年趣题讨论：Q53 同数包夹44.1K2 年前](https://leetcode.cn/circle/discuss/FxkmqT/)[\uD83C\uDF88跨年趣题讨论：Q49 欲速则不达66.5K2 年前](https://leetcode.cn/circle/discuss/11mBnL/)[\uD83C\uDF1F 跨年趣题讨论：Q40 优雅的 IP 地址75.6K2 年前](https://leetcode.cn/circle/discuss/YmmSCq/)[✌️ 跨年趣题讨论：Q30 用插线板制作章鱼脚状线路44.4K2 年前](https://leetcode.cn/circle/discuss/SCX9Iu/)[\uD83D\uDCAB 跨年趣题讨论：Q26 高效的立体停车场45.7K2 年前](https://leetcode.cn/circle/discuss/ZhsB58/)[\uD83C\uDF8A 与力扣共同成长的 2020 - \uD83D\uDCDD 力扣年度报告来了！6326.8K2 年前](https://leetcode.cn/circle/discuss/gXFPWC/)[\uD83C\uDF85 跨年趣题讨论：Q18 水果酥饼日109.1K2 年前](https://leetcode.cn/circle/discuss/amUE3E/)[\uD83C\uDF84 跨年趣题讨论：Q04 切分木棒1815.1K2 年前](https://leetcode.cn/circle/discuss/QGsghu/)[力扣跨年仪式 2020 ➡️ 2021 正式开始，邀你一起倒计时！4722.1K2 年前](https://leetcode.cn/circle/discuss/wBAVQK/)[\uD83C\uDFC6 第 220 场力扣周赛45.1K2 年前](https://leetcode.cn/circle/discuss/bXh8VU/)[\uD83D\uDCE2 力扣首页持续优化中，期待您的反馈！426.1K2 年前](https://leetcode.cn/circle/discuss/H7E5TS/)[\uD83D\uDCE3 力扣全新 App 即将上线，现邀请大家前来体验！154.8K2 年前](https://leetcode.cn/circle/discuss/SMx5dj/)[\uD83D\uDCE3 力扣全新 App 即将上线，现邀请大家前来体验！01192 年前](https://leetcode.cn/circle/discuss/JZlFI9/)[\uD83C\uDFC6 第 219 场力扣周赛18.2K2 年前](https://leetcode.cn/circle/discuss/Ai4j3P/)[\uD83D\uDC31 第 41 场夜喵双周赛03.9K2 年前](https://leetcode.cn/circle/discuss/xkhbgF/)[\uD83C\uDFC6 第 218 场力扣周赛17.2K2 年前](https://leetcode.cn/circle/discuss/1EGrS1/)[\uD83C\uDFC6 第 217 场力扣周赛06.1K2 年前](https://leetcode.cn/circle/discuss/QFjRKm/)[\uD83D\uDC31 第 40 场夜喵双周赛03.7K2 年前](https://leetcode.cn/circle/discuss/u1CyG4/)[力扣竞赛新规则 （2020 年 12 月开始执行）815.9K2 年前](https://leetcode.cn/circle/discuss/KAGX07/)[\uD83C\uDFC6 第 216 场力扣周赛07.5K2 年前](https://leetcode.cn/circle/discuss/QQX7fN/)[\uD83C\uDFC6 第 215 场力扣周赛18.2K2 年前](https://leetcode.cn/circle/discuss/tJl9h4/)[\uD83D\uDC31 第 39 场夜喵双周赛04.4K2 年前](https://leetcode.cn/circle/discuss/KDCdc0/)[\uD83C\uDFC6 力扣竞赛 - 勋章及成就规则1824.5K2 年前](https://leetcode.cn/circle/discuss/0fKGDu/)[\uD83C\uDFC6 第 214 场力扣周赛35.4K2 年前](https://leetcode.cn/circle/discuss/7LgKox/)[\uD83D\uDC4D【我要上推荐】- 力扣官方推荐指南2911.6K2 年前](https://leetcode.cn/circle/discuss/44MmDd/)[11·11 \uD83C\uDF89 助你愿望 11 实现 - 程序员的 \uD83D\uDC97 心愿清单上都有什么？1422K2 年前](https://leetcode.cn/circle/discuss/PpRqs0/)[\uD83C\uDFC6 第 213 场力扣周赛37.1K2 年前](https://leetcode.cn/circle/discuss/MTKiUq/)[\uD83D\uDC31 第 38 场夜喵双周赛03.7K2 年前](https://leetcode.cn/circle/discuss/Ax9Q0F/)[\uD83C\uDFC6 第 212 场力扣周赛54.9K2 年前](https://leetcode.cn/circle/discuss/QFRzuS/)[\uD83D\uDD79 1024 程序员的奇幻冒险103.5K2 年前](https://leetcode.cn/circle/discuss/Prs00I/)[1024 程序员的奇幻冒险01722 年前](https://leetcode.cn/circle/discuss/NuP0T8/)[\uD83C\uDF81 1024 程序员节—— 来一场奇幻冒险吧 \uD83C\uDFC4\u200D3017.6K2 年前](https://leetcode.cn/circle/discuss/HvFuG4/)[\uD83C\uDFC6 第 211 场力扣周赛05.9K2 年前](https://leetcode.cn/circle/discuss/luvHfG/)[\uD83D\uDC31 第 37 场夜喵双周赛03.4K2 年前](https://leetcode.cn/circle/discuss/dhIc9J/)[\uD83C\uDFC6 第 210 场力扣周赛04K2 年前](https://leetcode.cn/circle/discuss/SKRyGk/)[\uD83C\uDFC6 第 209 场力扣周赛04.1K2 年前](https://leetcode.cn/circle/discuss/SiembA/)[\uD83D\uDC31 第 36 场夜喵双周赛02.7K2 年前](https://leetcode.cn/circle/discuss/XvDf8n/)[\uD83C\uDFC6 第 208 场力扣周赛04.5K2 年前](https://leetcode.cn/circle/discuss/EWSQbj/)[\uD83C\uDFC6 第 207 场力扣周赛03.6K2 年前](https://leetcode.cn/circle/discuss/aPDXbV/)[\uD83D\uDC31 第 35 场夜喵双周赛03.3K2 年前](https://leetcode.cn/circle/discuss/CwhuIe/)[\uD83C\uDFC6 LCCUP ‘20 力扣杯 秋季编程大赛 - 战队赛09.2K2 年前](https://leetcode.cn/circle/discuss/MwNNcS/)[\uD83C\uDFC6 第 206 场力扣周赛04.5K2 年前](https://leetcode.cn/circle/discuss/kxAwVx/)[\uD83C\uDFC6 LCCUP ‘20 力扣杯 秋季编程大赛 - 个人赛114.6K2 年前](https://leetcode.cn/circle/discuss/dIOa7J/)[\uD83C\uDFC6 第 205 场力扣周赛04.2K2 年前](https://leetcode.cn/circle/discuss/L3RDdn/)[\uD83D\uDC31 第 34 场夜喵双周赛02.9K2 年前](https://leetcode.cn/circle/discuss/FSdcYt/)[\uD83C\uDFC6 第 204 场力扣周赛04K2 年前](https://leetcode.cn/circle/discuss/AowY9C/)[\uD83C\uDFC6 第 203 场力扣周赛05.7K2 年前](https://leetcode.cn/circle/discuss/Y8gx1Y/)[\uD83D\uDC31 第 33 场夜喵双周赛04.8K2 年前](https://leetcode.cn/circle/discuss/DBgVnO/)[\uD83C\uDFC6 第 202 场力扣周赛05.8K2 年前](https://leetcode.cn/circle/discuss/y52mDW/)[力扣02002 年前](https://leetcode.cn/circle/discuss/D3OdAn/)[\uD83C\uDFC6 第 201 场力扣周赛05.4K3 年前](https://leetcode.cn/circle/discuss/tZvpdV/)[\uD83D\uDC31 第 32 场夜喵双周赛04K3 年前](https://leetcode.cn/circle/discuss/QwybIk/)[\uD83C\uDFC6 第 200 场力扣周赛05.4K3 年前](https://leetcode.cn/circle/discuss/eW3EJc/)[算法与面试技巧精讲03963 年前](https://leetcode.cn/circle/discuss/UY54UN/)[高频算法实战03783 年前](https://leetcode.cn/circle/discuss/1AJNla/)[七章刷完数据结构01.7K3 年前](https://leetcode.cn/circle/discuss/GyUIcV/)[哈希表11.7K3 年前](https://leetcode.cn/circle/discuss/ItLt4K/)[队列 & 栈41.9K3 年前](https://leetcode.cn/circle/discuss/3yf65n/)[递归07193 年前](https://leetcode.cn/circle/discuss/P7vPVE/)[咕果11.1K3 年前](https://leetcode.cn/circle/discuss/91KVmH/)[数组和字符串03.1K3 年前](https://leetcode.cn/circle/discuss/pzqvgU/)[机器学习 10101.5K3 年前](https://leetcode.cn/circle/discuss/24aEXP/)[链表02.2K3 年前](https://leetcode.cn/circle/discuss/jm0unm/)[查找表类算法05073 年前](https://leetcode.cn/circle/discuss/2bKB3D/)[哈希表09653 年前](https://leetcode.cn/circle/discuss/xosQui/)[腾讯02.2K3 年前](https://leetcode.cn/circle/discuss/Txafm7/)[数组类算法01.4K3 年前](https://leetcode.cn/circle/discuss/Mb1F8g/)[二分查找01.8K3 年前](https://leetcode.cn/circle/discuss/1CyR9L/)[二叉树12.5K3 年前](https://leetcode.cn/circle/discuss/AK3hF6/)[2020 名企高频面试题02K3 年前](https://leetcode.cn/circle/discuss/1nbi4R/)[二叉搜索树04943 年前](https://leetcode.cn/circle/discuss/k9Hvh3/)[算法面试题汇总02K3 年前](https://leetcode.cn/circle/discuss/3dI99Z/)[初级算法310.2K3 年前](https://leetcode.cn/circle/discuss/gcUFRj/)[中级算法02K3 年前](https://leetcode.cn/circle/discuss/GM9MB4/)[Teambition05333 年前](https://leetcode.cn/circle/discuss/tF1XGw/)[N 叉树05593 年前](https://leetcode.cn/circle/discuss/fGXZxb/)[前缀树06623 年前](https://leetcode.cn/circle/discuss/rR22XO/)[高级算法01.6K3 年前](https://leetcode.cn/circle/discuss/IicrdZ/)[\uD83C\uDFC6 第 199 场力扣周赛07.7K3 年前](https://leetcode.cn/circle/discuss/bsAenv/)[\uD83D\uDC31 第 31 场夜喵双周赛04.8K3 年前](https://leetcode.cn/circle/discuss/1duQDV/)[\uD83C\uDFC6 第 198 场力扣周赛010K3 年前](https://leetcode.cn/circle/discuss/TgHsJg/)[\uD83C\uDFC6 第 197 场力扣周赛07.6K3 年前](https://leetcode.cn/circle/discuss/84GbFt/)[\uD83D\uDC31 第 30 场夜喵双周赛04.7K3 年前](https://leetcode.cn/circle/discuss/ntQvPc/)[\uD83C\uDFC6 第 196 场力扣周赛08K3 年前](https://leetcode.cn/circle/discuss/ii99Zv/)[\uD83C\uDFC6 第 195 场力扣周赛06.7K3 年前](https://leetcode.cn/circle/discuss/CgqXdK/)[\uD83D\uDC31 第 29 场夜喵双周赛05.1K3 年前](https://leetcode.cn/circle/discuss/zPlu04/)[\uD83D\uDCE3 力扣 Dark Side 「黑暗面」Beta 测试中，特邀扣友们前来体验！427.1K3 年前](https://leetcode.cn/circle/discuss/1yq9cm/)[\uD83C\uDFC6 第 194 场力扣周赛09K3 年前](https://leetcode.cn/circle/discuss/rzgsas/)[\uD83C\uDFC6 第 193 场力扣周赛05.4K3 年前](https://leetcode.cn/circle/discuss/YocVPD/)[\uD83D\uDC31 第 28 场夜喵双周赛03.4K3 年前](https://leetcode.cn/circle/discuss/R8bevT/)[\uD83C\uDFC6 第 192 场力扣周赛05.2K3 年前](https://leetcode.cn/circle/discuss/xNJrTZ/)[\uD83C\uDFC6 第 191 场力扣周赛05.5K3 年前](https://leetcode.cn/circle/discuss/bWuMtO/)[\uD83D\uDC31 第 27 场夜喵双周赛03.1K3 年前](https://leetcode.cn/circle/discuss/ySZgfl/)[\uD83C\uDFC6 第 190 场力扣周赛06.3K3 年前](https://leetcode.cn/circle/discuss/dY8kDv/)[\uD83C\uDFC6 第 189 场力扣周赛05.4K3 年前](https://leetcode.cn/circle/discuss/Z2oiVE/)[\uD83D\uDC31 第 26 场夜喵双周赛01K3 年前](https://leetcode.cn/circle/discuss/YL6EOM/)[\uD83C\uDFC6 第 188 场力扣周赛03K3 年前](https://leetcode.cn/circle/discuss/ofydWp/)[\uD83C\uDFC6 第 187 场力扣周赛04.7K3 年前](https://leetcode.cn/circle/discuss/0Ougok/)[\uD83D\uDC31 第 25 场夜喵双周赛05K3 年前](https://leetcode.cn/circle/discuss/yowQKf/)[\uD83C\uDFC6 第 186 场力扣周赛04.2K3 年前](https://leetcode.cn/circle/discuss/tKCZG9/)[\uD83C\uDFC6 第 185 场力扣周赛06.7K3 年前](https://leetcode.cn/circle/discuss/bU6UlA/)[\uD83D\uDC31 第 24 场夜喵双周赛04.6K3 年前](https://leetcode.cn/circle/discuss/S8wgA3/)[\uD83C\uDFC6 第 184 场力扣周赛06.7K3 年前](https://leetcode.cn/circle/discuss/FoTPDt/)[\uD83C\uDFC6 第 183 场力扣周赛05.5K3 年前](https://leetcode.cn/circle/discuss/DyKK75/)[\uD83D\uDC31 第 23 场夜喵双周赛06.2K3 年前](https://leetcode.cn/circle/discuss/rZQfQh/)[\uD83C\uDFC6 第 182 场力扣周赛06.4K3 年前](https://leetcode.cn/circle/discuss/nu0NvC/)[\uD83C\uDFC6 第 181 场力扣周赛07.8K3 年前](https://leetcode.cn/circle/discuss/mV63u3/)[\uD83D\uDC31 第 22 场夜喵双周赛03.7K3 年前](https://leetcode.cn/circle/discuss/p8VxZf/)[\uD83D\uDCE3 通知：全新「竞赛排名和竞赛规则」上线啦！154K3 年前](https://leetcode.cn/circle/article/HKbjrw/)[\uD83C\uDFC6 第 180 场力扣周赛07K3 年前](https://leetcode.cn/circle/discuss/aX9cCs/)[\uD83C\uDFC6 第 179 场力扣周赛07.4K3 年前](https://leetcode.cn/circle/discuss/5HNw3j/)[\uD83D\uDC31 第 21 场夜喵双周赛05K3 年前](https://leetcode.cn/circle/discuss/6QA1zX/)[\uD83E\uDDD1\u200D\uD83D\uDCBB 力扣（LeetCode）题目 / 题解快速反馈通道开始试行！714.1K3 年前](https://leetcode.cn/circle/discuss/kSYiVL/)[\uD83C\uDFC6 第 178 场力扣周赛05.6K3 年前](https://leetcode.cn/circle/discuss/y0Hu6V/)[「每日 1 题」12 月热题来袭！177197K3 年前](https://leetcode.cn/circle/article/9EZfRE/)[\uD83D\uDCE3 通知：全新「周赛评分算法」最终方案378.1K3 年前](https://leetcode.cn/circle/article/neTUV4/)[\uD83C\uDFC6 第 177 场力扣周赛06.5K3 年前](https://leetcode.cn/circle/discuss/8iTNwb/)[\uD83D\uDC31 第 20 场夜喵双周赛04.4K3 年前](https://leetcode.cn/circle/discuss/Vo9HwK/)[\uD83C\uDFC6 第 176 场力扣周赛08.9K3 年前](https://leetcode.cn/circle/discuss/0gO5RS/)[关于周赛175事故声明158983 年前](https://leetcode.cn/circle/article/jsmr7U/)[\uD83C\uDFC6 第 175 场力扣周赛011.6K3 年前](https://leetcode.cn/circle/discuss/CU8vzT/)[\uD83D\uDC31 第 19 场夜喵双周赛04.7K3 年前](https://leetcode.cn/circle/discuss/5qOjw7/)[\uD83C\uDFC6 第 174 场力扣周赛07.8K3 年前](https://leetcode.cn/circle/discuss/lEfEkb/)[关于第 18 场双周赛封禁说明422.5K3 年前](https://leetcode.cn/circle/article/X9LIm0/)[\uD83C\uDFC6 第 173 场力扣周赛05.2K3 年前](https://leetcode.cn/circle/discuss/gdAKNv/)[\uD83D\uDC31 第 18 场夜喵双周赛02.9K3 年前](https://leetcode.cn/circle/discuss/2toZmW/)[\uD83C\uDFC6【可能是己亥年最有趣的比赛】题解讨论区02.8K3 年前](https://leetcode.cn/circle/discuss/P05J24/)[\uD83C\uDFC6【可能是己亥年最有趣的比赛】— 狼人杀模拟器131.4K3 年前](https://leetcode.cn/circle/article/EJf9yW/)[\uD83C\uDFC6【可能是己亥年最有趣的比赛】— 找出隐藏信息33313 年前](https://leetcode.cn/circle/article/jGX56Z/)[\uD83C\uDFC6【可能是己亥年最有趣的比赛】— 分苹果64793 年前](https://leetcode.cn/circle/article/3fyQhk/)[\uD83C\uDFC6【可能是己亥年最有趣的比赛】— 理琥珀珠21923 年前](https://leetcode.cn/circle/article/sOCM81/)[\uD83C\uDFC6【可能是己亥年最有趣的比赛】— SyM 的 \uD83D\uDD1152053 年前](https://leetcode.cn/circle/article/O3wrrf/)[\uD83C\uDFC6【可能是己亥年最有趣的比赛】— 小胖子的日常63333 年前](https://leetcode.cn/circle/article/KJRw4A/)[\uD83C\uDFC6【可能是己亥年最有趣的比赛】最终排名&获奖名单公布03.1K3 年前](https://leetcode.cn/circle/discuss/3SCzdi/)[\uD83C\uDFC6 第 172 场力扣周赛04.6K3 年前](https://leetcode.cn/circle/discuss/HcSRiW/)[\uD83C\uDFC6 第 171 场力扣周赛05.4K3 年前](https://leetcode.cn/circle/discuss/FagUOB/)[\uD83D\uDC31 第 17 场夜喵双周赛05.3K3 年前](https://leetcode.cn/circle/discuss/B1yhtj/)[\uD83C\uDFC6 第 170 场力扣周赛05.5K3 年前](https://leetcode.cn/circle/discuss/BlV9CE/)[\uD83D\uDCF1力扣 App「代码编辑器」现邀请内测中014.7K3 年前](https://leetcode.cn/circle/discuss/cxdRAm/)[力扣 App 编辑器使用建议反馈08.6K3 年前](https://leetcode.cn/circle/discuss/EWYRWI/)[\uD83C\uDFC6 第 169 场力扣周赛07K3 年前](https://leetcode.cn/circle/discuss/eD8JIb/)[\uD83D\uDC31 第 16 场夜喵双周赛02.8K3 年前](https://leetcode.cn/circle/discuss/oX8qwZ/)[\uD83C\uDFC6 第 168 场力扣周赛07K3 年前](https://leetcode.cn/circle/discuss/LNAmr6/)[\uD83D\uDCE3 全新「周赛评分算法」即将上线，现邀扣友们前来出谋划策！115.4K3 年前](https://leetcode.cn/circle/discuss/v6WloU/)[\uD83C\uDFC6 第 167 场力扣周赛05.2K3 年前](https://leetcode.cn/circle/discuss/mLljrr/)[\uD83D\uDC31 第 15 场夜喵双周赛03.6K3 年前](https://leetcode.cn/circle/discuss/7DepuT/)[\uD83C\uDFC6 第 166 场力扣周赛08.8K3 年前](https://leetcode.cn/circle/discuss/FpOkuW/)[\uD83C\uDFC6 第 165 场力扣周赛06.1K3 年前](https://leetcode.cn/circle/discuss/qFIvLJ/)[\uD83D\uDC31 第 14 场夜喵双周赛03.9K3 年前](https://leetcode.cn/circle/discuss/X7ktq3/)[\uD83C\uDFC6 第 164 场力扣周赛07.6K3 年前](https://leetcode.cn/circle/discuss/tXbeBV/)[\uD83C\uDFC6 第 163 场力扣周赛06.4K3 年前](https://leetcode.cn/circle/discuss/1cNREg/)[\uD83D\uDC31 第 13 场夜喵双周赛04.2K3 年前](https://leetcode.cn/circle/discuss/4bcTK1/)[\uD83D\uDCE3 全新「力扣 App」即将上线，现邀请大家前来内测！027.8K3 年前](https://leetcode.cn/circle/discuss/RXmKqr/)[\uD83C\uDFC6 第 162 场力扣周赛09.2K3 年前](https://leetcode.cn/circle/discuss/89bFUC/)[\uD83C\uDFC6 第 161 场力扣周赛011.1K3 年前](https://leetcode.cn/circle/discuss/JTOxPC/)[\uD83D\uDC31 第 12 场夜喵双周赛05.2K3 年前](https://leetcode.cn/circle/discuss/oWdw06/)[\uD83C\uDFC6 第 160 场力扣周赛07.7K3 年前](https://leetcode.cn/circle/discuss/LPwegn/)[\uD83C\uDFC6 第 159 场力扣周赛09.3K3 年前](https://leetcode.cn/circle/discuss/xBh6mp/)[\uD83D\uDC31 力扣第 11 场夜喵双周赛05.7K3 年前](https://leetcode.cn/circle/discuss/lFgVuL/)[\uD83C\uDF81【活动】1024 程序员节 \uD83D\uDCBB —— 分享我的技术生活022K3 年前](https://leetcode.cn/circle/discuss/rcN3aA/)[\uD83C\uDFC6 第 158 场力扣周赛010.1K3 年前](https://leetcode.cn/circle/discuss/5eIzXx/)[\uD83C\uDFC6 第 157 场力扣周赛010K3 年前](https://leetcode.cn/circle/discuss/x0KbRs/)[\uD83D\uDC31 力扣第 10 场夜喵双周赛04.7K3 年前](https://leetcode.cn/circle/discuss/CxzuBr/)[\uD83C\uDFC6 迎国庆，力扣第 156 场周赛 \uD83C\uDDE8\uD83C\uDDF3010.7K3 年前](https://leetcode.cn/circle/discuss/KzqZdU/)[\uD83C\uDFC62019 力扣杯 - 全国秋季编程大赛020.8K3 年前](https://leetcode.cn/circle/discuss/1I3IVg/)[\uD83C\uDFC6 第 155 场力扣周赛06.2K3 年前](https://leetcode.cn/circle/discuss/KXLDmR/)[\uD83D\uDC31 力扣第 9 场夜喵双周赛03.9K3 年前](https://leetcode.cn/circle/discuss/8rEAqa/)[\uD83C\uDFC6 第 154 场力扣中秋周赛05.4K3 年前](https://leetcode.cn/circle/discuss/0znlIe/)[\uD83C\uDFC6 第 153 场力扣周赛08.4K3 年前](https://leetcode.cn/circle/discuss/OOTHdm/)[\uD83D\uDC31 第 8 场力扣夜喵双周赛02.9K3 年前](https://leetcode.cn/circle/discuss/KguRWD/)[\uD83C\uDFC6 第 152 场力扣周赛08.5K3 年前](https://leetcode.cn/circle/discuss/vyEcfT/)[2020 年算法 / 数据分析面试数学考点梳理（1）222.1K3 年前](https://leetcode.cn/circle/article/8OjRym/)[\uD83C\uDFC6 第 151 场力扣周赛05.5K3 年前](https://leetcode.cn/circle/discuss/EALjMh/)[\uD83D\uDC31 第 7 场力扣夜喵双周赛01.6K3 年前](https://leetcode.cn/circle/discuss/cw4Pv8/)[\uD83C\uDFC6 第 150 场力扣周赛04.5K3 年前](https://leetcode.cn/circle/discuss/F4QdLq/)[「力扣编辑器」使用说明15222.1K3 年前](https://leetcode.cn/circle/article/hipGkf/)[\uD83C\uDFC6 第 149 场力扣周赛05.1K3 年前](https://leetcode.cn/circle/discuss/XM4cOJ/)[\uD83D\uDC31 第 6 场力扣夜猫专场双周赛02.4K3 年前](https://leetcode.cn/circle/discuss/KdHibB/)[\uD83C\uDF81 力扣圈子「你来写，我来送」优质内容征集活动013.5K3 年前](https://leetcode.cn/circle/discuss/piSVGI/)[图解 PCA - Principal Components Analysis187884 年前](https://leetcode.cn/circle/article/XSCxme/)[大家都是如何刷 LeetCode 的？2874.7K4 年前](https://leetcode.cn/circle/discuss/nRXySQ/)[2019 年算法面试梳理782.8K4 年前](https://leetcode.cn/circle/article/Jp0kFM/)[BFS 与 DFS 到底哪个更快？26.7K4 年前](https://leetcode.cn/circle/discuss/0dNgad/)[如何判断链表中是否存在环？09.7K4 年前](https://leetcode.cn/circle/discuss/L2XbRB/)[交流｜阻塞非阻塞与同步异步的区别是什么？024.6K4 年前](https://leetcode.cn/circle/discuss/uHGOZo/)[请实现一个「短域名」的系统设计117.7K4 年前](https://leetcode.cn/circle/discuss/EkCOT9/)\n");
    
    // contests.forEach(System.out::println);
    // others.forEach(System.out::println);
    
    // for (String s : others) {
    //   if (webLinksContainKeyString(s.substring(s.indexOf("(") + 1, s.length() - 1))) {
    //     System.out.println(s);
    //   }
    // }
    
    for (int i = 1; i <= 12; i++) {
      daka(String.format("%02d", i));
    }
  }
  
  private static void xueXiJiHuaGuangChang() {
    System.out.println(format("\n" +
        "![SQL](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/sql/cover)\n" +
        "\n" +
        "![SQL 入门勋章](https://pic.leetcode-cn.com/1647314028-bOxZiz-SQL-%E5%85%A5%E9%97%A8.png)![SQL 基础勋章](https://pic.leetcode-cn.com/1647316202-niszmr-SQL-%E4%BC%9A%E5%91%98.png)![SQL 进阶勋章](https://pic.leetcode-cn.com/1647324238-KKXIrL-SQL-%E4%BC%9A%E5%91%98%E8%BF%9B%E9%98%B6.png)\n" +
        "\n" +
        "SQL"));
    System.out.println(format("\n" +
        "![算法](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/algorithms/cover)\n" +
        "\n" +
        "![算法入门勋章](https://pic.leetcode-cn.com/1624440092-hwwUxh-%E7%AE%97%E6%B3%95%E5%85%A5%E9%97%A8.png)![算法基础勋章](https://pic.leetcode-cn.com/1624440254-lKLpaI-%E7%AE%97%E6%B3%95%E8%BF%9B%E9%98%B6.png)![算法进阶勋章](https://pic.leetcode-cn.com/1634029684-JvmMuH-%E7%AE%97%E6%B3%95-%E4%BC%9A%E5%91%98%E8%BF%9B%E9%98%B6.png)\n" +
        "\n" +
        "算法"));
    System.out.println(format("\n" +
        "\n" +
        "![数据结构](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/data-structures/cover)\n" +
        "\n" +
        "![数据结构入门勋章](https://pic.leetcode-cn.com/1624439797-WAywKo-%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84%E5%85%A5%E9%97%A8.png)![数据结构基础勋章](https://pic.leetcode-cn.com/1624440017-VmWOdm-%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84%E8%BF%9B%E9%98%B6.png)![数据结构进阶勋章](https://pic.leetcode-cn.com/1634030099-FUrqEJ-%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84-%E4%BC%9A%E5%91%98%E8%BF%9B%E9%98%B6.png)\n" +
        "\n" +
        "数据结构"));
    System.out.println(format("![二分查找](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/binary-search/cover)\n" +
        "\n" +
        "![二分查找入门勋章](https://pic.leetcode-cn.com/1646971218-zTxwlc-%E4%BA%8C%E5%88%86%E6%9F%A5%E6%89%BE_%E5%85%A5%E9%97%A8.png)![二分查找基础勋章](https://pic.leetcode-cn.com/1646985175-bgDvpB-%E4%BA%8C%E5%88%86%E6%9F%A5%E6%89%BE_%E5%9F%BA%E7%A1%80.png)![二分查找进阶勋章](https://pic.leetcode-cn.com/1646993027-TJzyWg-%E4%BA%8C%E5%88%86%E6%9F%A5%E6%89%BE_%E4%BC%9A%E5%91%98%E8%BF%9B%E9%98%B6.png)\n" +
        "\n" +
        "二分查找"));
    System.out.println(format("\n" +
        "\n" +
        "![图论](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/graph/cover)\n" +
        "\n" +
        "![图论基础勋章](https://pic.leetcode-cn.com/1643519093-lAVVbg-%E5%9B%BE%E8%AE%BA.png)![图论进阶勋章](https://pic.leetcode-cn.com/1643519273-JFondx-%E5%9B%BE%E8%AE%BA-%E4%BC%9A%E5%91%98%E8%BF%9B%E9%98%B6.png)\n" +
        "\n" +
        "图论"));
    
    System.out.println(format("\n" +
        "![编程能力](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/programming-skills/cover)\n" +
        "\n" +
        "![编程能力入门勋章](https://pic.leetcode-cn.com/1643518963-IUKePd-%E7%BC%96%E7%A8%8B%E8%83%BD%E5%8A%9B_%E5%85%A5%E9%97%A8.png)![编程能力基础勋章](https://pic.leetcode-cn.com/1645694718-lJspbC-%E7%BC%96%E7%A8%8B%E8%83%BD%E5%8A%9B_%E5%9F%BA%E7%A1%80.png)![编程能力进阶勋章](https://pic.leetcode-cn.com/1647311422-gqQChW-%E7%BC%96%E7%A8%8B%E8%83%BD%E5%8A%9B_%E4%BC%9A%E5%91%98%E8%BF%9B%E9%98%B6.png)\n" +
        "\n" +
        "编程能力"));
    System.out.println(format("\n" +
        "![动态规划](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/dynamic-programming/cover)\n" +
        "\n" +
        "![动态规划入门勋章](https://pic.leetcode-cn.com/1624249311-Htoqng-DP%20%E5%85%A5%E9%97%A8.png)![动态规划基础勋章 plus](https://pic.leetcode-cn.com/1624439533-PERUrO-DP%20%E4%BC%9A%E5%91%98%E4%B8%93%E4%BA%AB.png)![动态规划进阶勋章](https://pic.leetcode-cn.com/1637234327-fSPeGT-DP-%E4%BC%9A%E5%91%98%E8%BF%9B%E9%98%B6.png)![动态规划大师勋章](https://pic.leetcode-cn.com/1637234514-HadpPS-DP-%E4%BC%9A%E5%91%98%E5%A4%A7%E5%B8%88.png)\n" +
        "\n" +
        "动态规划\n"));
    
    
    System.out.println(format(""));
    System.out.println(format(""));
    System.out.println(format(""));
    System.out.println(format("\n" +
        "    ![「职」击岗位](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/zhijigangwei/cover)\n" +
        "  \n" +
        "    ![高频面试题精选](https://pic.leetcode-cn.com/1662721118-rCGYdG-%E9%AB%98%E9%A2%91%E9%9D%A2%E8%AF%95.png)![前端岗位](https://pic.leetcode-cn.com/1662718856-kOmSBf-%E5%89%8D%E7%AB%AF%E5%B2%97.png)![后端岗位](https://pic.leetcode-cn.com/1662718985-eNYqkJ-%E5%90%8E%E7%AB%AF%E5%B2%97.png)![算法岗位](https://pic.leetcode-cn.com/1662719125-FTvWkA-%E7%AE%97%E6%B3%95%E5%B2%97.png)\n" +
        "\n" +
        "「职」击岗位"));
    System.out.println(format("\n" +
        "  \n" +
        "    ![名企直通车](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/zhitongche/cover)\n" +
        "  \n" +
        "    ![网易](https://pic.leetcode-cn.com/1658477887-DlGFzV-%E7%BD%91%E6%98%93.png)![京东](https://pic.leetcode-cn.com/1658480860-AptYgd-%E4%BA%AC%E4%B8%9C.png)![阿里](https://pic.leetcode-cn.com/1658481668-fbpnxW-%E9%98%BF%E9%87%8C.png)![哔哩哔哩](https://pic.leetcode-cn.com/1658481812-HZQNcG-%E5%93%94%E5%93%A9%E5%93%94%E5%93%A9.png)\n" +
        "  \n" +
        "    名企直通车"));
    System.out.println(format("\n" +
        "  \n" +
        "    ![LeetCode 75](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/leetcode_75/cover)\n" +
        "  \n" +
        "    ![Level 1](https://pic.leetcode-cn.com/1655895090-gFjOGi-%E5%85%A5%E9%97%A8.png)![Level 2](https://pic.leetcode-cn.com/1655895410-Cabtfk-%E5%9F%BA%E7%A1%80.png)![Level 3](https://pic.leetcode-cn.com/1655895607-KuKZZU-%E4%BC%9A%E5%91%98.png)\n" +
        "  \n" +
        "    LeetCode 75"));
    System.out.println(format("\n" +
        "  \n" +
        "    ![力扣杯竞赛真题集](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/lccup/cover)\n" +
        "  \n" +
        "    ![2022 秋赛学习勋章](https://pic.leetcode.cn/1670326888-ZpuDsK-Lccup%20Fall%2022.png)![2021 秋赛学习勋章](https://pic.leetcode-cn.com/1636108551-QmAeKj-LCCUP%202021%20Fall.png)![2022 春赛学习勋章](https://pic.leetcode.cn/1670326518-cWQjBX-Lccup%20Spring%2022.png)![2021 春赛学习勋章](https://pic.leetcode-cn.com/1628563616-dzfRWK-Frame%20837.png)\n" +
        "  \n" +
        "    力扣杯竞赛真题集"));
    System.out.println(format("\n" +
        "  \n" +
        "    ![剑指 Offer](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/lcof/cover)\n" +
        "  \n" +
        "    ![剑指 Offer 学习勋章](https://pic.leetcode-cn.com/1626948358-etuPTd-%E5%89%91%E6%8C%87%20Offer%20I.png)![剑指 Offer II 学习勋章](https://pic.leetcode-cn.com/1626948535-rgTSxm-%E5%89%91%E6%8C%87%20Offer%20II.png)\n" +
        "  \n" +
        "    剑指 Offer"));
    System.out.println(format("\n" +
        "  \n" +
        "    ![高效制胜](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/efficient-winning/cover)\n" +
        "  \n" +
        "    ![高效制胜学习勋章](https://pic.leetcode-cn.com/1626254770-iQukZQ-%E9%AB%98%E6%95%88%E5%88%B6%E8%83%9C.png)\n" +
        "  \n" +
        "    高效制胜"));
    
    
    System.out.println(format("![力扣杯竞赛真题集](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/lccup/cover)\n" +
        "![2022 秋赛学习勋章](https://pic.leetcode.cn/1670326888-ZpuDsK-Lccup%20Fall%2022.png)\n" +
        "![2021 秋赛学习勋章](https://pic.leetcode-cn.com/1636108551-QmAeKj-LCCUP%202021%20Fall.png)\n" +
        "![2022 春赛学习勋章](https://pic.leetcode.cn/1670326518-cWQjBX-Lccup%20Spring%2022.png)\n" +
        "![2021 春赛学习勋章](https://pic.leetcode-cn.com/1628563616-dzfRWK-Frame%20837.png)\n" +
        "![2020 秋赛学习勋章](https://pic.leetcode-cn.com/1628563462-ckUlNC-Frame%20836.png)\n" +
        "![2020 春赛学习勋章](https://pic.leetcode-cn.com/1628563334-xsMeVh-Frame%20835.png)\n" +
        "![2019 秋赛学习勋章](https://pic.leetcode-cn.com/1628563079-QbLAsf-Frame%20834.png)\n" +
        "力扣杯竞赛真题集"));
    
    
    format2("![思爱普当季高频面试题](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/7d_siaipu/cover)\n" +
        "\n" +
        "![2022 第 4 季度思爱普面试真题](https://pic.leetcode.cn/1668066272-hZyJot-%E6%80%9D%E7%88%B1%E6%99%AE-1.png)\n" +
        "\n" +
        "思爱普当季高频面试题\n" +
        "\n" +
        "![推特当季高频面试题](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/7d_twitter/cover)\n" +
        "\n" +
        "![2022 第 4 季度推特面试真题](https://pic.leetcode.cn/1668066201-JZUZFl-twitter.png)\n" +
        "\n" +
        "推特当季高频面试题\n" +
        "\n" +
        "![三星当季高频面试题](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/7d_sanxing/cover)\n" +
        "\n" +
        "![2022 第 4 季度三星面试真题](https://pic.leetcode.cn/1668066138-cTwtkT-%E4%B8%89%E6%98%9F-1.png)\n" +
        "\n" +
        "三星当季高频面试题\n" +
        "\n" +
        "![eBay 当季高频面试题](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/7d_ebay/cover)\n" +
        "\n" +
        "![2022 第 4 季度 eBay 面试真题](https://pic.leetcode.cn/1668066004-LTCpYc-ebay-1.png)\n" +
        "\n" +
        "eBay 当季高频面试题\n" +
        "\n" +
        "![Intel 当季高频面试题](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/7d_intel/cover)\n" +
        "\n" +
        "![2022 第 4 季度 Intel 面试真题](https://pic.leetcode.cn/1668065937-iEQTAx-intel.png)\n" +
        "\n" +
        "Intel 当季高频面试题\n" +
        "\n" +
        "![彭博当季高频面试题](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/7d_pengbo/cover)\n" +
        "\n" +
        "![2022 第 4 季度彭博面试真题](https://pic.leetcode.cn/1668066080-BeCcCn-%E5%BD%AD%E5%8D%9A-1.png)\n" +
        "\n" +
        "彭博当季高频面试题\n" +
        "\n" +
        "![Coupang 当季高频面试题](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/7d_coupang/cover)\n" +
        "\n" +
        "![2022 第 4 季度 Coupang 面试真题](https://pic.leetcode.cn/1668065832-qdmLtc-coupang-1.png)\n" +
        "\n" +
        "Coupang 当季高频面试题\n" +
        "\n" +
        "![Airbnb 当季高频面试题](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/7d_airbnb/cover)\n" +
        "\n" +
        "![2022 第 4 季度 Airbnb 面试真题](https://pic.leetcode.cn/1668065761-MPyovK-airbnb-1.png)\n" +
        "\n" +
        "Airbnb 当季高频面试题\n" +
        "\n" +
        "![网易游戏当季高频面试题](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/7d_wangyiyouxi/cover)\n" +
        "\n" +
        "![2022 第 4 季度网易游戏面试真题](https://pic.leetcode.cn/1668065569-bHDtvD-%E7%BD%91%E6%98%93%E6%B8%B8%E6%88%8F-1.png)\n" +
        "\n" +
        "网易游戏当季高频面试题\n" +
        "\n" +
        "![小红书当季高频面试题](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/7d_xiaohonshu/cover)\n" +
        "\n" +
        "![2022 第 4 季度小红书面试真题](https://pic.leetcode.cn/1668065645-MboKOd-%E5%B0%8F%E7%BA%A2%E4%B9%A6-1.png)\n" +
        "\n" +
        "小红书当季高频面试题\n" +
        "\n" +
        "![Zoom 当季高频面试题](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/7d_zoom/cover)\n" +
        "\n" +
        "![2022 第 4 季度 Zoom 面试真题](https://pic.leetcode.cn/1668065713-ZLLkLH-zoom-1.png)\n" +
        "\n" +
        "Zoom 当季高频面试题\n" +
        "\n" +
        "![商汤当季高频面试题](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/7d_shangtang/cover)\n" +
        "\n" +
        "![2022 第 4 季度商汤面试真题](https://pic.leetcode.cn/1668065485-oryJBD-%E5%95%86%E6%B1%A4-1.png)\n" +
        "\n" +
        "商汤当季高频面试题\n" +
        "\n" +
        "![猿辅导当季高频面试题](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/7d_yuanfudao/cover)\n" +
        "\n" +
        "![2022 第 4 季度猿辅导面试真题](https://pic.leetcode.cn/1668065399-Hcekbi-%E7%8C%BF%E8%BE%85%E5%AF%BC-1.png)\n" +
        "\n" +
        "猿辅导当季高频面试题\n" +
        "\n" +
        "![今日头条当季高频面试题](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/7d_jinritoutiao/cover)\n" +
        "\n" +
        "![2022 第 4 季度今日头条面试真题](https://pic.leetcode.cn/1668064998-UHGejI-%E4%BB%8A%E6%97%A5%E5%A4%B4%E6%9D%A1-1.png)\n" +
        "\n" +
        "今日头条当季高频面试题\n" +
        "\n" +
        "![hulu 当季高频面试题](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/7d_hulu/cover)\n" +
        "\n" +
        "![2022 第 4 季度 hulu 面试真题](https://pic.leetcode.cn/1668065068-lhVnCi-hulu-1.png)\n" +
        "\n" +
        "hulu 当季高频面试题\n" +
        "\n" +
        "![蚂蚁当季高频面试题](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/7d_mayi/cover)\n" +
        "\n" +
        "![2022 第 4 季度蚂蚁面试真题](https://pic.leetcode.cn/1668065125-ZoRBSi-%E8%9A%82%E8%9A%81-1.png)\n" +
        "\n" +
        "蚂蚁当季高频面试题\n" +
        "\n" +
        "![搜狗当季高频面试题](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/7d_sougou/cover)\n" +
        "\n" +
        "![2022 第 4 季度搜狗面试真题](https://pic.leetcode.cn/1668064929-zbCYTW-%E6%90%9C%E7%8B%97.png)\n" +
        "\n" +
        "搜狗当季高频面试题\n" +
        "\n" +
        "![搜狐当季高频面试题](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/7d_souhu/cover)\n" +
        "\n" +
        "![2022 第 4 季度搜狐面试真题](https://pic.leetcode.cn/1668065274-RjEYmv-%E6%90%9C%E7%8B%90-1.png)\n" +
        "\n" +
        "搜狐当季高频面试题\n" +
        "\n" +
        "![小马智行当季高频面试题](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/7d_xiaomazhixing/cover)\n" +
        "\n" +
        "![2022 第 4 季度小马智行面试真题](https://pic.leetcode.cn/1668064756-sMbbRC-%E5%B0%8F%E9%A9%AC%E6%99%BA%E8%A1%8C-1.png)\n" +
        "\n" +
        "小马智行当季高频面试题\n" +
        "\n" +
        "![新浪当季高频面试题](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/7d_xinlang/cover)\n" +
        "\n" +
        "![2022 第 4 季度新浪面试真题](https://pic.leetcode.cn/1668064642-dClgBL-%E6%96%B0%E6%B5%AA-1.png)\n" +
        "\n" +
        "新浪当季高频面试题\n" +
        "\n" +
        "![谷歌当季高频面试题](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/7d_google/cover)\n" +
        "\n" +
        "![2022 第 4 季度谷歌面试真题](https://pic.leetcode.cn/1668064537-vDeBwn-Google.png)\n" +
        "\n" +
        "谷歌当季高频面试题\n" +
        "\n" +
        "![英伟达当季高频面试题](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/7d_yingweida/cover)\n" +
        "\n" +
        "![2022 第 4 季度英伟达面试真题](https://pic.leetcode.cn/1668064847-sgajWo-%E8%8B%B1%E4%BC%9F%E8%BE%BE-1.png)\n" +
        "\n" +
        "英伟达当季高频面试题\n" +
        "\n" +
        "![亚马逊当季高频面试题](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/7d_yamaxun/cover)\n" +
        "\n" +
        "![2022 第 4 季度亚马逊面试真题](https://pic.leetcode.cn/1668064373-AswPpD-amazon-1.png)\n" +
        "\n" +
        "亚马逊当季高频面试题\n" +
        "\n" +
        "![facebook 当季高频面试题](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/7d_facebook/cover)\n" +
        "\n" +
        "![2022 第 4 季度 facebook 面试真题](https://pic.leetcode.cn/1668063301-ChSXXj-Facebook-1.png)\n" +
        "\n" +
        "facebook 当季高频面试题\n" +
        "\n" +
        "![爱奇艺当季高频面试题](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/7d_aiqiyi/cover)\n" +
        "\n" +
        "![2022 第 4 季度爱奇艺面试真题](https://pic.leetcode.cn/1668063172-DQXrtk-%E7%88%B1%E5%A5%87%E8%89%BA-1.png)\n" +
        "\n" +
        "爱奇艺当季高频面试题\n" +
        "\n" +
        "![奇虎 360 当季高频面试题](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/7d_qihu/cover)\n" +
        "\n" +
        "![2022 第 4 季度奇虎 360 面试真题](https://pic.leetcode.cn/1667551687-LiSgoO-360.png)\n" +
        "\n" +
        "奇虎 360 当季高频面试题\n" +
        "\n" +
        "![Shopee 当季高频面试题](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/7d_shopee/cover)\n" +
        "\n" +
        "![2022 第 4 季度 Shopee 面试真题](https://pic.leetcode.cn/1667551614-CXpNWa-shopee.png)\n" +
        "\n" +
        "Shopee 当季高频面试题\n" +
        "\n" +
        "![微软当季高频面试题](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/7d_weiruan/cover)\n" +
        "\n" +
        "![2022 第 4 季度微软面试真题](https://pic.leetcode.cn/1667551525-ttElkx-%E5%BE%AE%E8%BD%AF.png)\n" +
        "\n" +
        "微软当季高频面试题\n" +
        "\n" +
        "![滴滴当季高频面试题](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/7d_didi/cover)\n" +
        "\n" +
        "![2022 第 4 季度滴滴面试真题](https://pic.leetcode.cn/1667551430-slsyZl-%E6%BB%B4%E6%BB%B4.png)\n" +
        "\n" +
        "滴滴当季高频面试题\n" +
        "\n" +
        "![深信服当季高频面试题](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/7d_shenxinfu/cover)\n" +
        "\n" +
        "![2022 第 4 季度深信服面试真题](https://pic.leetcode.cn/1667551337-rkLDiE-%E6%B7%B1%E4%BF%A1%E6%9C%8D.png)\n" +
        "\n" +
        "深信服当季高频面试题\n" +
        "\n" +
        "![百度当季高频面试题](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/7d_baidu/cover)\n" +
        "\n" +
        "![2022 第 4 季度百度面试真题](https://pic.leetcode.cn/1667551208-IMhYPT-%E7%99%BE%E5%BA%A6.png)\n" +
        "\n" +
        "百度当季高频面试题\n" +
        "\n" +
        "![特斯拉当季高频面试题](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/7d_tesila/cover)\n" +
        "\n" +
        "![2022 第 4 季度特斯拉面试真题](https://pic.leetcode.cn/1667551099-cjEUSo-%E7%89%B9%E6%96%AF%E6%8B%89.png)\n" +
        "\n" +
        "特斯拉当季高频面试题\n" +
        "\n" +
        "![美团当季高频面试题](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/7d_meituan/cover)\n" +
        "\n" +
        "![2022 第 4 季度美团面试真题](https://pic.leetcode.cn/1667551023-xBuMVf-%E7%BE%8E%E5%9B%A2.png)\n" +
        "\n" +
        "美团当季高频面试题\n" +
        "\n" +
        "![携程当季高频面试题](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/7d_xiecheng/cover)\n" +
        "\n" +
        "![2022 第 4 季度携程面试真题](https://pic.leetcode.cn/1667550916-WcFUWp-%E6%90%BA%E7%A8%8B.png)\n" +
        "\n" +
        "携程当季高频面试题\n" +
        "\n" +
        "![拼多多当季高频面试题](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/7d_pinduoduo/cover)\n" +
        "\n" +
        "![2022 第 4 季度拼多多面试真题](https://pic.leetcode.cn/1667550835-TvwDfb-%E6%8B%BC%E5%A4%9A%E5%A4%9A.png)\n" +
        "\n" +
        "拼多多当季高频面试题\n" +
        "\n" +
        "![招商银行当季高频面试题](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/7d_zhaoshangyinhang/cover)\n" +
        "\n" +
        "![2022 第 4 季度招商银行面试真题](https://pic.leetcode.cn/1667550438-BCJAff-%E6%8B%9B%E8%A1%8C.png)\n" +
        "\n" +
        "招商银行当季高频面试题\n" +
        "\n" +
        "![OPPO 当季高频面试题](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/7d_oppo/cover)\n" +
        "\n" +
        "![2022 第 4 季度 oppo 面试真题](https://pic.leetcode.cn/1667550311-XrOjVl-OPPO.png)\n" +
        "\n" +
        "OPPO 当季高频面试题\n" +
        "\n" +
        "![vivo 当季高频面试题](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/7d_vivo/cover)\n" +
        "\n" +
        "![2022 第 4 季度 vivo 面试真题](https://pic.leetcode.cn/1667550211-INaShw-vivo.png)\n" +
        "\n" +
        "vivo 当季高频面试题\n" +
        "\n" +
        "![快手当季高频面试题](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/7d_kuaishou/cover)\n" +
        "\n" +
        "![2022 第 4 季度快手面试真题](https://pic.leetcode.cn/1667550120-XYSvYi-%E5%BF%AB%E6%89%8B.png)\n" +
        "\n" +
        "快手当季高频面试题\n" +
        "\n" +
        "![大疆当季高频面试题](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/7d_dajiang/cover)\n" +
        "\n" +
        "![2022 第 4 季度大疆面试真题](https://pic.leetcode.cn/1667549919-yozmAw-%E5%A4%A7%E7%96%86.png)\n" +
        "\n" +
        "大疆当季高频面试题\n" +
        "\n" +
        "![蔚来当季高频面试题](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/7d_weilai/cover)\n" +
        "\n" +
        "![2022 第 4 季度蔚来面试真题](https://pic.leetcode.cn/1667549095-HyZNFz-%E8%94%9A%E6%9D%A5.png)\n" +
        "\n" +
        "蔚来当季高频面试题\n" +
        "\n" +
        "![bilibili 当季高频面试题](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/7d_bilibili/cover)\n" +
        "\n" +
        "![2022 第 4 季度 bilibili 面试真题](https://pic.leetcode.cn/1667548694-xzyedz-bilibili.png)\n" +
        "\n" +
        "bilibili 当季高频面试题\n" +
        "\n" +
        "![字节跳动当季高频面试题](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/7d_zijie/cover)\n" +
        "\n" +
        "![2022 第 4 季度字节面试真题](https://pic.leetcode.cn/1667548461-LLmmzz-%E5%AD%97%E8%8A%82.png)\n" +
        "\n" +
        "字节跳动当季高频面试题\n" +
        "\n" +
        "![miHoYo 当季高频面试题](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/7d_mihoyo/cover)\n" +
        "\n" +
        "![2022 第 4 季度 miHoYo 面试真题](https://pic.leetcode.cn/1667548333-DdbYFX-mihoyou.png)\n" +
        "\n" +
        "miHoYo 当季高频面试题\n" +
        "\n" +
        "![京东当季高频面试题](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/7d_jingdon/cover)\n" +
        "\n" +
        "![2022 第 4 季度京东面试真题](https://pic.leetcode.cn/1667548242-ycteGM-%E4%BA%AC%E4%B8%9C.png)\n" +
        "\n" +
        "京东当季高频面试题\n" +
        "\n" +
        "![小米当季高频面试题](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/7d_xiaomi/cover)\n" +
        "\n" +
        "![2022 第 4 季度小米面试真题](https://pic.leetcode.cn/1667548067-IMjxzT-%E5%B0%8F%E7%B1%B3.png)\n" +
        "\n" +
        "小米当季高频面试题\n" +
        "\n" +
        "![网易当季高频面试题](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/7d_wangyi/cover)\n" +
        "\n" +
        "![2022 第 4 季度网易面试真题](https://pic.leetcode.cn/1667547981-MWhwSd-%E7%BD%91%E6%98%93.png)\n" +
        "\n" +
        "网易当季高频面试题\n" +
        "\n" +
        "![腾讯当季高频面试题](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/7d_tengxun/cover)\n" +
        "\n" +
        "![2022 第 4 季度腾讯面试真题](https://pic.leetcode.cn/1667547813-DVOrTN-%E8%85%BE%E8%AE%AF.png)\n" +
        "\n" +
        "腾讯当季高频面试题\n" +
        "\n" +
        "![阿里巴巴当季高频面试题](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/alibaba/cover)\n" +
        "\n" +
        "![2022 第 4 季度阿里巴巴面试真题](https://pic.leetcode.cn/1667544883-JNTBYN-%E9%98%BF%E9%87%8C%E5%B7%B4%E5%B7%B4.png)\n" +
        "\n" +
        "阿里巴巴当季高频面试题\n" +
        "\n" +
        "![华为当季高频面试题](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/7d_huawei/cover)\n" +
        "\n" +
        "![2022 第 4 季度华为面试真题](https://pic.leetcode.cn/1667544543-RykGxh-%E5%8D%8E%E4%B8%BA.png)\n" +
        "\n" +
        "华为当季高频面试题\n" +
        "\n" +
        "![Teambition](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/teambition/cover)\n" +
        "\n" +
        "![Teambition 往年笔试真题](https://pic.leetcode-cn.com/1663903607-RdbYTY-Teambition%20%E6%AD%A3%E9%9D%A2.png)\n" +
        "\n" +
        "Teambition\n" +
        "\n" +
        "![网易有道](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/youdao/cover)\n" +
        "\n" +
        "![2022 网易有道秋招学习勋章](https://pic.leetcode-cn.com/1662629431-jVFExG-%E5%A4%A7.png)\n" +
        "\n" +
        "网易有道\n" +
        "\n" +
        "![飞步无人驾驶](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/feibu/cover)\n" +
        "\n" +
        "![2022 飞步科技秋招学习勋章](https://pic.leetcode-cn.com/1661397670-aXAQgO-%E9%A3%9E%E6%AD%A5%E7%A7%91%E6%8A%80%20%E6%AD%A3.png)\n" +
        "\n" +
        "飞步无人驾驶\n" +
        "\n" +
        "![滴滴](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/didi/cover)\n" +
        "\n" +
        "![滴滴历届编程真题](https://pic.leetcode-cn.com/1661756075-yFeJcU-%E6%BB%B4%E6%BB%B4%E6%AD%A3.png)\n" +
        "\n" +
        "滴滴\n" +
        "\n" +
        "![完美世界](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/perfectworld/cover)\n" +
        "\n" +
        "![2022 完美世界秋招学习勋章](https://pic.leetcode-cn.com/1661241849-ueBXri-%E5%AE%8C%E7%BE%8E%E4%B8%96%E7%95%8C%20%E6%AD%A3.png)\n" +
        "\n" +
        "完美世界\n" +
        "\n" +
        "![搜狐](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/sohu/cover)\n" +
        "\n" +
        "![2022 搜狐秋招学习勋章](https://pic.leetcode-cn.com/1661242373-QaagXV-%E6%90%9C%E7%8B%90%20%E6%AD%A3.png)\n" +
        "\n" +
        "搜狐\n" +
        "\n" +
        "![Shopee Code League 真题精选](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/shopee/cover)\n" +
        "\n" +
        "![Shopee Code League 真题精选勋章](https://pic.leetcode-cn.com/1650530268-bexBhy-Shopee%20%E6%AD%A3%E9%9D%A2.png)\n" +
        "\n" +
        "Shopee Code League 真题精选\n" +
        "\n" +
        "![图森未来](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/tusmiple/cover)\n" +
        "\n" +
        "![图森未来编程练习题 2022](https://pic.leetcode-cn.com/1650019966-RoNifI-%E5%9B%BE%E6%A3%AE%E6%9C%AA%E6%9D%A5%E6%AD%A3.png)\n" +
        "\n" +
        "图森未来\n" +
        "\n" +
        "![招商银行信用卡](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/cmbchina-cc/cover)\n" +
        "\n" +
        "![2022 编程练习题勋章](https://pic.leetcode-cn.com/1648899268-iYzLgR-%E6%8B%9B%E5%95%86%E9%93%B6%E8%A1%8C%E6%AD%A3.png)\n" +
        "\n" +
        "招商银行信用卡\n" +
        "\n" +
        "![贝壳找房](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/ke/cover)\n" +
        "\n" +
        "![2021 贝壳找房学习勋章](https://pic.leetcode-cn.com/1628743411-UVmzzS-%E8%B4%9D%E5%A3%B3%E6%AD%A3.png)\n" +
        "\n" +
        "贝壳找房\n" +
        "\n" +
        "![小马智行 Pony.ai](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/ponyai/cover)\n" +
        "\n" +
        "![2021 小马智行 Pony.ai 学习勋章](https://pic.leetcode-cn.com/1628740963-atkQlp-%E5%B0%8F%E9%A9%AC%E6%99%BA%E8%A1%8C.png)\n" +
        "\n" +
        "小马智行 Pony.ai\n" +
        "\n" +
        "![美团校园招聘笔试真题](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/meituan/cover)\n" +
        "\n" +
        "![2021 届美团秋招学习勋章](https://pic.leetcode-cn.com/1628565318-HKsuom-%E7%BE%8E%E5%9B%A2%E6%AD%A3.png)![2022 美团秋招学习勋章](https://pic.leetcode-cn.com/1661508355-kxzfQV-%E7%BE%8E%E5%9B%A2%E6%AD%A3.png)\n" +
        "\n" +
        "美团校园招聘笔试真题");
  }
  
  public static String format(String catagory, List<String> t, List<String> img) {
    StringBuilder sb = new StringBuilder();
    sb.append(String.format("""
        #### %s
        """, catagory));
    for (int i = 0; i < t.size(); i += 4) {
      String t1 = t.get(i);
      String t2 = i + 1 < t.size() ? t.get(i + 1) : "";
      String t3 = i + 2 < t.size() ? t.get(i + 2) : "";
      String t4 = i + 3 < t.size() ? t.get(i + 3) : "";
      String img1 = img.get(i);
      String img2 = i + 1 < t.size() ? img.get(i + 1) : "";
      String img3 = i + 2 < t.size() ? img.get(i + 2) : "";
      String img4 = i + 3 < t.size() ? img.get(i + 3) : "";
      sb.append(String.format("""
          
          | %s | %s | %s | %s |
          | :---: | :---:| :---: | :---: |
          | %s | %s | %s | %s |
          """, t1, t2, t3, t4, img1, img2, img3, img4));
    }
    return sb.toString();
  }
  
  public static String format(String catagory, String t1, String t2, String t3, String t4, String img1, String img2, String img3, String img4) {
    return String.format("""
        #### %s
        | %s | %s | %s | %s |
        | :---: | :---:| :---: | :---: |
        | %s | %s | %s | %s |""", catagory, t1, t2, t3, t4, img1, img2, img3, img4);
  }
  
  public static String format(String catagory, String t1, String t2, String t3, String img1, String img2, String img3) {
    return String.format("""
        #### %s
        | %s | %s | %s |
        | :---: | :---:| :---: |
        | %s | %s | %s |""", catagory, t1, t2, t3, img1, img2, img3);
  }
  
  
  public static String format(String catagory, String t1, String t2, String img1, String img2) {
    return String.format("""
        #### %s
        | %s | %s |
        | :---: | :---:|
        | %s | %s |""", catagory, t1, t2, img1, img2);
  }
  
  public static String format(String s) {
    s = s.replaceAll("\n", "").replaceAll(" ", "");
    String ans = "";
    ArrayList<String> t = new ArrayList<>();
    ArrayList<String> img = new ArrayList<>();
    String[] split = s.split("\\)");
    for (int i = 1; i < split.length - 1; i++) {
      String s1 = split[i];
      t.add(s1.substring(2, s1.lastIndexOf("]")));
      img.add(s1 + ")");
    }
    return format(split[split.length - 1], t, img);
  }
  
  public static void format2(String s) {
    s = s.replaceAll("\n", "").replaceAll(" ", "");
    
    Matcher matcher = Pattern.compile("(![^!)]+\\))+[^!]+").matcher(s);
    while (matcher.find())
      System.out.println(format(matcher.group()));
  }
  
  static List<String> contests = new ArrayList<>();
  static List<String> others = new ArrayList<>();
  
  public static void format3(String s) {
    s = s.replaceAll("\n", "").replaceAll(" ", "");
    
    Matcher matcher = Pattern.compile("\\[[^\\[\\]]+\\]\\([^\\(\\)]+\\)").matcher(s);
    while (matcher.find()) {
      if (matcher.group().contains("周赛")) {
        contests.add(matcher.group());
      } else {
        others.add(matcher.group());
      }
    }
  }
  
  /**
   * @Description: 在网页中查询一段字符串
   * @Author: yuhua
   * @Date: 2018/12/12
   */
  public static boolean webLinksContainKeyString(String webLinks) throws IOException {
    boolean flag = false;
    InputStream inputStream = null;
    InputStreamReader inputStreamReader = null;
    BufferedReader bufferedReader = null;
    try {
      URL url = new URL(webLinks);
      inputStream = url.openStream();
      inputStreamReader = new InputStreamReader(inputStream);
      bufferedReader = new BufferedReader(inputStreamReader);
      String finalStr = "start:";
      String str;
      while ((str = bufferedReader.readLine()) != null) {
        finalStr += str;
      }
      if (finalStr.contains("勋章")) {
        flag = true;
      } else {
        return flag;
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      bufferedReader.close();
      inputStreamReader.close();
      inputStream.close();
    }
    return flag;
  }
  
  public static void daka(String mo) {
    System.out.printf("| ![image.png](https://assets.leetcode-cn.com/medals/2020-%s.png){:width=400} | ![image.png](https://assets.leetcode-cn.com/medals/2021/lg/2021-%s.png){:width=400} | ![image.png](https://assets.leetcode-cn.com/medals/2022/lg/2022-%s.png){:width=400} |  |%n", mo, mo, mo);
  }
}
