package com.net.security.bean;

import java.util.List;

public class Indexs3 {
    private int total;
    private List<Index3> index3s;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Index3> getIndex3s() {
        return index3s;
    }

    public void setIndex3s(List<Index3> index3s) {
        this.index3s = index3s;
    }

    public static class Index3 extends Index{
        private int id1;
        private String name1;
        private String nickname1;
        private int id2;
        private String name2;
        private String nickname2;

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

        public int getId2() {
            return id2;
        }

        public void setId2(int id2) {
            this.id2 = id2;
        }

        public String getName2() {
            return name2;
        }

        public void setName2(String name2) {
            this.name2 = name2;
        }

        public String getNickname2() {
            return nickname2;
        }

        public void setNickname2(String nickname2) {
            this.nickname2 = nickname2;
        }
    }
}
