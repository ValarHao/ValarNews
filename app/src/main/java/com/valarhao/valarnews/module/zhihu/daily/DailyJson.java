package com.valarhao.valarnews.module.zhihu.daily;

import java.util.List;

public class DailyJson {

    private String date;
    private List<Story> stories;

    public String getDate() {
        return date;
    }

    public List<Story> getStories() {
        return stories;
    }

    public static class Story {

        private List<String> images;
        private int type;
        private int id;
        private int ga_prefix;
        private String title;

        private boolean readState;

        public List<String> getImages() {
            return images;
        }

        public int getType() {
            return type;
        }

        public int getId() {
            return id;
        }

        public int getGaPrefix() {
            return ga_prefix;
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
