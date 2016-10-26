package com.valarhao.valarnews.module.zhihu.module.section;

import com.valarhao.valarnews.module.zhihu.common.RecyclerItem;

import java.util.List;

public class SectionJson {

    /**
     * "data":[{"description":"看别人的经历，理解自己的生活","id":1,"name":"深夜惊奇","thumbnail":"http:\/\/pic3.zhimg.com\/91125c9aebcab1c84f58ce4f8779551e.jpg"},{"id":2,"thumbnail":"http:\/\/pic4.zhimg.com\/b467184bc46eb0f52a3ac557c054ba83.jpg","name":"瞎扯","description":"随便扯扯，也能很有深度"},{"description":"","id":16,"name":"整点儿新闻","thumbnail":"http:\/\/pic1.zhimg.com\/7bdd5e9182cfd5d4aeaed3fcfb450627.jpg"},{"id":17,"thumbnail":"http:\/\/pic2.zhimg.com\/3b4dc0e103ef2bf0a8b212a374608a6d.jpg","name":"饿了","description":""},{"thumbnail":"http:\/\/pic4.zhimg.com\/e9be478788cd40a754426e8745c4a82f.jpg","description":"","name":"这里是广告","id":19},{"description":"","id":26,"name":"读读日报推荐","thumbnail":"http:\/\/pic2.zhimg.com\/88520f264582a80a7912065b09be38ed.jpg"},{"thumbnail":"http:\/\/pic2.zhimg.com\/6d4b19db75644caf49c34ddc1fbf3251.jpg","description":"","name":"放映机","id":28},{"id":29,"thumbnail":"http:\/\/pic1.zhimg.com\/e9a16859051a5648fdb1d1f9448ddac4.jpg","name":"大误","description":""},{"description":"一个介绍职人的，所","id":33,"name":"《职人介绍所》","thumbnail":"http:\/\/pic2.zhimg.com\/fa5612cedf533de03bf6f1fe89b5b539.jpg"},{"id":34,"thumbnail":"http:\/\/pic4.zhimg.com\/40e37c5c96788a846178394ae44fc78b.jpg","name":"读读日报 24 小时热门","description":""},{"thumbnail":"http:\/\/pic4.zhimg.com\/9cf09b1123933b16a135d5a0efa57a97.jpg","description":"生活无小事","name":"小事","id":35},{"id":36,"thumbnail":"http:\/\/pic4.zhimg.com\/66b875ef0c3a38041870a4e22c6684db.jpg","name":"选个好专业","description":""},{"thumbnail":"http:\/\/pic2.zhimg.com\/7aa79413d88829ebc62056fefd0fd449.jpg","description":"","name":"上个好大学","id":37},{"id":38,"thumbnail":"http:\/\/pic1.zhimg.com\/0a2b7af040e2340a7abf20ad3b30ae30.jpg","name":"知乎好问题","description":""},{"description":"","id":39,"name":"滴滴优步并购案","thumbnail":"http:\/\/pic4.zhimg.com\/69f709f79e86e3476d4e5ab2933c2c1b.jpg"},{"thumbnail":"http:\/\/pic4.zhimg.com\/3239b027eeefa4267d63bf9f16a041cf.jpg","description":"","name":"整点儿奥运新闻","id":40},{"id":41,"thumbnail":"http:\/\/pic1.zhimg.com\/921dd6c5d2477a0fc39fac3049e6f6ac.jpg","name":"16 年 9 月苹果秋季发布会","description":""},{"description":"","id":42,"name":"天宫二号发射","thumbnail":"http:\/\/pic1.zhimg.com\/20382971f48562a73d99988cc1dd4e2c.jpg"},{"description":"","id":43,"name":"发现中国","thumbnail":"http:\/\/pic2.zhimg.com\/833a8ceb41b360e25043cecc843f6575.jpg"},{"thumbnail":"http:\/\/pic3.zhimg.com\/1b9cea03d2f682c5c876f63b39b4797e.jpg","description":"","name":"2016 诺贝尔奖巡礼","id":44}]
     */

    private List<Section> data;

    public List<Section> getSections() {
        return data;
    }

    /**
     * "description":"看别人的经历，理解自己的生活"
     * "id":1
     * "name":"深夜惊奇"
     * "thumbnail":"http:\/\/pic3.zhimg.com\/91125c9aebcab1c84f58ce4f8779551e.jpg"
     */

    public static class Section extends RecyclerItem {

        private String description;
        private int id;
        private String name;
        private String thumbnail;

        public String getDescription() {
            return description;
        }

        @Override
        public int getId() {
            return id;
        }

        @Override
        public String getTitle() {
            return name;
        }

        @Override
        public String getImgLink() {
            return thumbnail;
        }
    }
}
