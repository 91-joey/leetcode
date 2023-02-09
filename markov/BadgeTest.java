package org.example.leetcode.problems.markov;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 勋章图鉴 markdown 格式化
public class BadgeTest {
  public static void main(String[] args) {
    // System.out.println(format(
    //     "算法",
    //     "算法入门勋章",
    //     "算法基础勋章",
    //     "算法进阶勋章",
    //     "![算法入门勋章](https://pic.leetcode-cn.com/1624440092-hwwUxh-%E7%AE%97%E6%B3%95%E5%85%A5%E9%97%A8.png)",
    //     "![算法基础勋章](https://pic.leetcode-cn.com/1624440254-lKLpaI-%E7%AE%97%E6%B3%95%E8%BF%9B%E9%98%B6.png)",
    //     "![算法进阶勋章](https://pic.leetcode-cn.com/1634029684-JvmMuH-%E7%AE%97%E6%B3%95-%E4%BC%9A%E5%91%98%E8%BF%9B%E9%98%B6.png)"));
    
    // System.out.println(format("![数据结构入门勋章](https://pic.leetcode-cn.com/1624439797-WAywKo-%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84%E5%85%A5%E9%97%A8.png)![数据结构基础勋章](https://pic.leetcode-cn.com/1624440017-VmWOdm-%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84%E8%BF%9B%E9%98%B6.png)![数据结构进阶勋章](https://pic.leetcode-cn.com/1634030099-FUrqEJ-%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84-%E4%BC%9A%E5%91%98%E8%BF%9B%E9%98%B6.png)"));
    
    // System.out.println(format("\n" +
    //     "\n" +
    //     "![图论](https://assets.leetcode.cn/aliyun-lc-upload/study_plan/graph/cover)\n" +
    //     "\n" +
    //     "![图论基础勋章](https://pic.leetcode-cn.com/1643519093-lAVVbg-%E5%9B%BE%E8%AE%BA.png)![图论进阶勋章](https://pic.leetcode-cn.com/1643519273-JFondx-%E5%9B%BE%E8%AE%BA-%E4%BC%9A%E5%91%98%E8%BF%9B%E9%98%B6.png)\n" +
    //     "\n" +
    //     "图论"));
    
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
}
