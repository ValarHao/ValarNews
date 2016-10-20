package com.valarhao.valarnews.module.zhihu.common.detail.comment;

import java.util.List;

public class DetailShortJson {

    /**
     * "comments":[{"author":"姬行","content":"老哥稳","avatar":"http:\/\/pic3.zhimg.com\/4e4c8f6d8ad5e9013f33711f13e96a7a_im.jpg","time":1476770840,"id":26915595,"likes":0},{"author":"十七岁那年夏","content":"哈哈，标题蛮赞的","avatar":"http:\/\/pic1.zhimg.com\/b7ad878c4_im.jpg","time":1476768404,"id":26915194,"likes":0},{"author":"佛土卡拉LF","content":"凭本事杀的人, 警察叔叔为什么要抓我","avatar":"http:\/\/pic3.zhimg.com\/88f84c07e248f7b2d00cd2eaaa702d2a_im.jpg","time":1476767438,"reply_to":{"content":"凭本事借的钱，为什么要还","status":0,"id":26913907,"author":"泰泰铭康"},"id":26915034,"likes":1},{"author":"ljwandbd","content":"写行花花碌碌，所谓审批人！呵呵呵呵","avatar":"http:\/\/pic3.zhimg.com\/1ae5f682bba916af97aaaddbf193a0f6_im.jpg","time":1476765644,"id":26914732,"likes":1},{"author":"歌木之铁牛传","content":"配图+神态  好评！！！","avatar":"http:\/\/pic4.zhimg.com\/e1f232fceafe183162cfcf5abfd03c23_im.jpg","time":1476763113,"id":26914110,"likes":1},{"author":"samweiren","content":"信贷审查还需要关注行业和资产组合","avatar":"http:\/\/pic3.zhimg.com\/74e5e7b904cd31dcf785f9e141f96d96_im.jpg","time":1476762420,"id":26914000,"likes":1},{"author":"周亦凡","content":"雅人叔舔舔","avatar":"http:\/\/pic1.zhimg.com\/da8e974dc_im.jpg","time":1476762412,"id":26913998,"likes":1},{"author":"昕Mio","content":"我就知道会有人说到配图😂","avatar":"http:\/\/pic2.zhimg.com\/7e0e797d5b3cd376b0f69a6d58dfb2fd_im.jpg","time":1476762118,"id":26913958,"likes":0},{"author":"freeair_sh","content":"配图有何含义？似乎没有类比性吧，国内中小企业以前贷款很难的说，房地产则很容易，这些是遵循所谓业务规则还是其它规则呢？","avatar":"http:\/\/pic1.zhimg.com\/ddb7faae88b6abbd4c31abb1ab916ce4_im.jpg","time":1476762087,"id":26913938,"likes":0},{"author":"泰泰铭康","content":"凭本事借的钱，为什么要还","avatar":"http:\/\/pic3.zhimg.com\/683ea591cd020d13c38934a2aa10bb06_im.jpg","time":1476761844,"id":26913907,"likes":3},{"author":"呵呵","content":"配图好评","avatar":"http:\/\/pic4.zhimg.com\/e6bdce3df_im.jpg","time":1476761771,"id":26913877,"likes":0},{"author":"CoCo_Nuts_小面包","content":"配图赞一个","avatar":"http:\/\/pic3.zhimg.com\/5734322f0d6b440f71476080d8d406b6_im.jpg","time":1476761708,"id":26913865,"likes":0}]
     */

    private List<Comment> comments;

    public List<Comment> getComments() {
        return comments;
    }

    /**
     * "author":"姬行"
     * "content":"老哥稳"
     * "avatar":"http:\/\/pic3.zhimg.com\/4e4c8f6d8ad5e9013f33711f13e96a7a_im.jpg"
     * "time":1476770840
     * "id":26915595
     * "likes":0
     */

    public static class Comment {

        private String author;
        private String content;
        private String avatar;
        private long time;
        private int id;
        private int likes;

        public String getAuthor() {
            return author;
        }

        public String getContent() {
            return content;
        }

        public String getAvatar() {
            return avatar;
        }

        public long getTime() {
            return time;
        }

        public int getId() {
            return id;
        }

        public int getLikes() {
            return likes;
        }
    }
}
