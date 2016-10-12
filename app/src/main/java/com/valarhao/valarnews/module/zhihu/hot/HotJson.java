package com.valarhao.valarnews.module.zhihu.hot;

import java.util.List;

public class HotJson {

    /**
     * "recent":[{"news_id":8868640,"url":"http:\/\/news-at.zhihu.com\/api\/2\/news\/8868640","thumbnail":"http:\/\/pic2.zhimg.com\/7f61d2478faaca89728b255618be9eb5.jpg","title":"做好心理准备，未来的银行里可能没几人上班了"},{"news_id":8869064,"url":"http:\/\/news-at.zhihu.com\/api\/2\/news\/8869064","thumbnail":"http:\/\/pic4.zhimg.com\/aab112152dc92f904c087f755e56a58b.jpg","title":"瞎扯 · 如何正确地吐槽"},{"news_id":8872005,"url":"http:\/\/news-at.zhihu.com\/api\/2\/news\/8872005","thumbnail":"http:\/\/pic4.zhimg.com\/b467184bc46eb0f52a3ac557c054ba83.jpg","title":"瞎扯 · 如何正确地吐槽"},{"news_id":8867998,"url":"http:\/\/news-at.zhihu.com\/api\/2\/news\/8867998","thumbnail":"http:\/\/pic3.zhimg.com\/7ee07063ed9f5f8b8f1c97cbd21ffc82.jpg","title":"人肉搜索了一下自己，发现网络世界真的毫无隐私"},{"news_id":8868930,"url":"http:\/\/news-at.zhihu.com\/api\/2\/news\/8868930","thumbnail":"http:\/\/pic4.zhimg.com\/4854ca8aa59dff0eb52cb7daf0aa03b3.jpg","title":"小事 · 不许碰，全都不许碰"},{"news_id":8725019,"url":"http:\/\/news-at.zhihu.com\/api\/2\/news\/8725019","thumbnail":"http:\/\/pic4.zhimg.com\/9cf09b1123933b16a135d5a0efa57a97.jpg","title":"小事 · 两个小时，赚了七千块"},{"news_id":8867031,"url":"http:\/\/news-at.zhihu.com\/api\/2\/news\/8867031","thumbnail":"http:\/\/pic1.zhimg.com\/b5f805f17931d8fae90aa1a19f3c89bc.jpg","title":"大误 · 嗯，这逻辑没毛病"},{"news_id":8872012,"url":"http:\/\/news-at.zhihu.com\/api\/2\/news\/8872012","thumbnail":"http:\/\/pic4.zhimg.com\/5a73c43438c2ef691caaab1710534f23.jpg","title":"哪怕不认识车标的人也会一眼看出，这车肯定很贵"},{"news_id":8869410,"url":"http:\/\/news-at.zhihu.com\/api\/2\/news\/8869410","thumbnail":"http:\/\/pic2.zhimg.com\/f52e55e27c969f73c831b3eb83c4e2a1.jpg","title":"读读日报 24 小时热门 TOP 5 · 他是金庸世界里最会聊天的人"},{"news_id":8869116,"url":"http:\/\/news-at.zhihu.com\/api\/2\/news\/8869116","thumbnail":"http:\/\/pic4.zhimg.com\/afe5e88b3a3ac40074c4702d503a7f2b.jpg","title":"大误 · 我就是一司机，你给我这么多选择是要干嘛？"},{"news_id":8874639,"url":"http:\/\/news-at.zhihu.com\/api\/2\/news\/8874639","thumbnail":"http:\/\/pic1.zhimg.com\/9cc35541f2e6875f140e0725f8d0ca18.jpg","title":"小事 · 爸妈反对我嫁他"},{"news_id":8874128,"url":"http:\/\/news-at.zhihu.com\/api\/2\/news\/8874128","thumbnail":"http:\/\/pic4.zhimg.com\/60e99b008709261e961e6a1b2626da6b.jpg","title":"瞎扯 · 如何正确地吐槽"},{"news_id":8867581,"url":"http:\/\/news-at.zhihu.com\/api\/2\/news\/8867581","thumbnail":"http:\/\/pic1.zhimg.com\/bf442e216c84fd9df1dc73dff5303f3c.jpg","title":"网恋、外遇、恋母、女优组合起来，居然是小清新？"},{"news_id":8871233,"url":"http:\/\/news-at.zhihu.com\/api\/2\/news\/8871233","thumbnail":"http:\/\/pic4.zhimg.com\/dca3ebceb9b95600b9add00389c1e90b.jpg","title":"又限购，买不到房子怎么办？"},{"news_id":8867907,"url":"http:\/\/news-at.zhihu.com\/api\/2\/news\/8867907","thumbnail":"http:\/\/pic4.zhimg.com\/c8e5b7f75a003535aa356c90be92641f.jpg","title":"除了能假装在美国，长期日夜颠倒的身体会怎样？"},{"news_id":8870875,"url":"http:\/\/news-at.zhihu.com\/api\/2\/news\/8870875","thumbnail":"http:\/\/pic3.zhimg.com\/ad16b07a04069c064b25917ffa137d0a.jpg","title":"从现在开始，多做一些「不为什么」的事"},{"news_id":8868229,"url":"http:\/\/news-at.zhihu.com\/api\/2\/news\/8868229","thumbnail":"http:\/\/pic4.zhimg.com\/60c4b7eefc275efb18d05bec5172aedb.jpg","title":"知乎好问题 · 如何高情商地说服别人所持观点是伪科学？"},{"news_id":8864208,"url":"http:\/\/news-at.zhihu.com\/api\/2\/news\/8864208","thumbnail":"http:\/\/pic2.zhimg.com\/ac0ba5878eb791c03b81d000777fd6c5.jpg","title":"别的超级英雄都是成年人，为什么蜘蛛侠就是个孩子？"},{"news_id":8865759,"url":"http:\/\/news-at.zhihu.com\/api\/2\/news\/8865759","thumbnail":"http:\/\/pic2.zhimg.com\/2cbb2ff078ac711a135087b0ce5b25a1.jpg","title":"性侵犯儿童的人被终生打上标签，是不是矫枉过正了？"},{"news_id":8628638,"url":"http:\/\/news-at.zhihu.com\/api\/2\/news\/8628638","thumbnail":"http:\/\/pic1.zhimg.com\/e9a16859051a5648fdb1d1f9448ddac4.jpg","title":"大误 · 我很中意你，不如我们交换一下人格？"}]
     */

    private List<Hot> recent;

    public List<Hot> getHots() {
        return recent;
    }

    /**
     * "news_id":8869064
     * "url":"http:\/\/news-at.zhihu.com\/api\/2\/news\/8869064"
     * "thumbnail":"http:\/\/pic4.zhimg.com\/aab112152dc92f904c087f755e56a58b.jpg"
     * "title":"瞎扯 · 如何正确地吐槽"
     */

    public static class Hot {

        private int news_id;
        private String url;
        private String thumbnail;
        private String title;

        private boolean readState;

        public int getNewsId() {
            return news_id;
        }

        public String getUrl() {
            return url;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public String getTitle() {
            return title;
        }

        public void setReadState(boolean readState) {
            this.readState = readState;
        }

        public boolean getReadState() {
            return readState;
        }
    }
}
