package com.valarhao.valarnews.module.zhihu.module.daily;

import com.valarhao.valarnews.module.zhihu.common.RecyclerItem;

import java.util.List;

public class DailyJson {

    /**
     * "date":"20160929"
     * "stories":[{"images":["http:\/\/pic2.zhimg.com\/f7fab4320a8157d56e7e81daae4bd079.jpg"],"type":0,"id":8838991,"ga_prefix":"092922","title":"小事 · 数着钱，看着人生"},{"images":["http:\/\/pic4.zhimg.com\/a6cdada72aeac4050111c2fdb1d38683.jpg"],"type":0,"id":8839001,"ga_prefix":"092921","title":"有哪些适合一个人看的文艺片？"},{"images":["http:\/\/pic2.zhimg.com\/50f7d64cba9305ea756940615fca6935.jpg"],"type":0,"id":8842258,"ga_prefix":"092920","title":"橱柜五花八门的材质，业内人士是这样介绍的"},{"title":"发现中国 · 成都好玩的那么多，担心你会错过这几个","ga_prefix":"092919","images":["http:\/\/pic3.zhimg.com\/5c3f3a1e3b00ca7a82ef40c3e0017e42.jpg"],"multipic":true,"type":0,"id":8842264},{"images":["http:\/\/pic3.zhimg.com\/c53e14af5aa6a8dcc4e887262ef496ce.jpg"],"type":0,"id":8835201,"ga_prefix":"092918","title":"看看身边就能发现，艺术和商业其实分不开"},{"images":["http:\/\/pic2.zhimg.com\/8b38cca512a407e4f4f25062d1f585b1.jpg"],"type":0,"id":8841847,"ga_prefix":"092917","title":"知乎好问题 ·  使用公共 Wi-Fi 上网如何保障安全？"},{"images":["http:\/\/pic1.zhimg.com\/a7bfa339eaf13b6dd1c83f81dd33e0d0.jpg"],"type":0,"id":8841646,"ga_prefix":"092916","title":"不爱大鱼大肉，简简单单来几道素菜，爽口又舒心"},{"images":["http:\/\/pic4.zhimg.com\/425e3a719fde5454b4dc9a0b0af49367.jpg"],"type":0,"id":8835499,"ga_prefix":"092915","title":"赛车中怎样实现弯道超车？"},{"images":["http:\/\/pic4.zhimg.com\/1c44c4af6cf88ea89871b9cd7da867db.jpg"],"type":0,"id":8841137,"ga_prefix":"092914","title":"直播辩论很热闹，但这有助于选出更好的政客吗？"},{"images":["http:\/\/pic1.zhimg.com\/3f60fef6b1de2872d809c8d69b0c4508.jpg"],"type":0,"id":8841172,"ga_prefix":"092913","title":"这个宝宝一出生，就带着三个父母的基因"},{"images":["http:\/\/pic1.zhimg.com\/9f544146c122b49ab4d331d03d8d5bc4.jpg"],"type":0,"id":8840704,"ga_prefix":"092912","title":"大误 · 年轻人，我觉得你名字不错"},{"images":["http:\/\/pic3.zhimg.com\/e2d6f74ee113f2bb881f3becf46888ba.jpg"],"type":0,"id":8840085,"ga_prefix":"092911","title":"不提前准备，你的钱很可能让你的老年生活有点难过"},{"images":["http:\/\/pic4.zhimg.com\/c35bc0cbb6de0facda514c9992f00b8f.jpg"],"type":0,"id":8840152,"ga_prefix":"092910","title":"救命的气囊何时弹出，背后是一套复杂的系统"},{"images":["http:\/\/pic1.zhimg.com\/7887978714bbe85966ab9bc77d9adc70.jpg"],"type":0,"id":8839832,"ga_prefix":"092909","title":"龙生龙凤生凤，老鼠的儿子会打洞，「本能」就是这么来的"},{"images":["http:\/\/pic3.zhimg.com\/2639c8ea5bcce0c5335928835f228152.jpg"],"type":0,"id":8840055,"ga_prefix":"092908","title":"手机电量就是不够，电池技术究竟难在哪儿？"},{"images":["http:\/\/pic3.zhimg.com\/b0fb78ff08a1e71932c70e34728c1506.jpg"],"type":0,"id":8839953,"ga_prefix":"092907","title":"殖民火星？伊隆 · 马斯克给出了全套方案"},{"images":["http:\/\/pic4.zhimg.com\/b48eb7d81ec728c0255b3c08c85a9e33.jpg"],"type":0,"id":8839978,"ga_prefix":"092907","title":"幻数，「一个神奇的数字」"},{"images":["http:\/\/pic4.zhimg.com\/3b92010c378e5b705a28f8522ed2b89f.jpg"],"type":0,"id":8836277,"ga_prefix":"092907","title":"普通人如何了解靠谱的医学知识？"},{"images":["http:\/\/pic2.zhimg.com\/92e146ba437a1cfe3f36281b72c867ed.jpg"],"type":0,"id":8840005,"ga_prefix":"092907","title":"读读日报 24 小时热门 TOP 5 · 一百万，两个月，去火星"},{"images":["http:\/\/pic3.zhimg.com\/ce0ff92a588071b8a20c35af8ec3e182.jpg"],"type":0,"id":8832725,"ga_prefix":"092906","title":"瞎扯 · 如何正确地吐槽"}]}
     */

    private String date;
    private List<Daily> stories;

    public String getDate() {
        return date;
    }

    public List<Daily> getDailies() {
        return stories;
    }

    public static class Daily extends RecyclerItem {

        /**
         * "images":["http:\/\/pic2.zhimg.com\/50f7d64cba9305ea756940615fca6935.jpg"]
         * "type":0
         * "id":8842258
         * "ga_prefix":"092920"
         * "title":"橱柜五花八门的材质，业内人士是这样介绍的"}
         */

        private List<String> images;
        private int type;
        private int id;
        private int ga_prefix;
        private String title;

        @Override
        public String getImgLink() {
            return images.get(0);
        }

        public int getType() {
            return type;
        }

        @Override
        public int getId() {
            return id;
        }

        public int getGaPrefix() {
            return ga_prefix;
        }

        @Override
        public String getTitle() {
            return title;
        }
    }
}
