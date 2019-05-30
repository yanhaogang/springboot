package com.net.security.bean;

import java.util.List;

public class Orgs {
    private int total;
    private List<Org1> org1s;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Org1> getOrg1s() {
        return org1s;
    }

    public void setOrg1s(List<Org1> org1s) {
        this.org1s = org1s;
    }
    public static class Org1{
        private Integer id;
        private String name;
        private String nickname;
        private String type;
        private List<String> countries;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<String> getCountries() {
            return countries;
        }

        public void setCountries(List<String> countries) {
            this.countries = countries;
        }
    }
}
