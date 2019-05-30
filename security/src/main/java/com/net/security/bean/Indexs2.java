package com.net.security.bean;

import java.util.List;

public class Indexs2 {
    private int total;
    private List<Index2> index2List;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Index2> getIndex2List() {
        return index2List;
    }

    public void setIndex2List(List<Index2> index2List) {
        this.index2List = index2List;
    }

    public static class Index2 extends Index{
        private int id1;
        private String name1;

        public int getId1() {
            return id1;
        }

        public void setId1(int id1) {
            this.id1 = id1;
        }

        public String getName1() {
            return name1;
        }

        public void setName1(String name1) {
            this.name1 = name1;
        }

        public String getNickname1() {
            return nickname1;
        }

        public void setNickname1(String nickname1) {
            this.nickname1 = nickname1;
        }

        private String nickname1;
    }
}
