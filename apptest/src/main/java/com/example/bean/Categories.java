package com.example.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/23/023.
 */

//1.类里出现的成员变量的命名要与JSON字符串里的key一致，若不一致不会报错，不能找到同名key去给他赋值
//2.若bean里多写了字段也不会报错，只是找不到key去赋值
//3.若bean里少写了字段也不会报错，接收不到相应字段的值
public class Categories {

    /**
     * retcode : 200
     * data : [
     * {"id":10000,"title":"新闻","type":1,"children":[
     *
     * {"id":10007,"title":"北京","type":1,"url":"/10007/list_1.json"},
     * {"id":10006,"title":"中国","type":1,"url":"/10006/list_1.json"},
     * {"id":10008,"title":"国际","type":1,"url":"/10008/list_1.json"},
     * {"id":10010,"title":"体育","type":1,"url":"/10010/list_1.json"},
     * {"id":10091,"title":"生活","type":1,"url":"/10091/list_1.json"},
     * {"id":10012,"title":"旅游","type":1,"url":"/10012/list_1.json"},
     * {"id":10095,"title":"科技","type":1,"url":"/10095/list_1.json"},
     * {"id":10009,"title":"军事","type":1,"url":"/10009/list_1.json"},
     * {"id":10093,"title":"时尚","type":1,"url":"/10093/list_1.json"},
     * {"id":10011,"title":"财经","type":1,"url":"/10011/list_1.json"},
     * {"id":10094,"title":"育儿","type":1,"url":"/10094/list_1.json"},
     * {"id":10105,"title":"汽车","type":1,"url":"/10105/list_1.json"}]},
     *
     * {"id":10002,"title":"专题","type":10,"url":"/10006/list_1.json","url1":"/10007/list1_1.json"},
     * {"id":10003,"title":"组图","type":2,"url":"/10008/list_1.json"},
     * {"id":10004,"title":"互动","type":3,"excurl":"","dayurl":"","weekurl":""}]
     *
     * extend : [10007,10006,10008,10014,10012,10091,10009,10010,10095]
     */

    public int retcode;
    /**
     * id : 10000
     * title : 新闻
     * type : 1
     * children : [
     * {"id":10007,"title":"北京","type":1,"url":"/10007/list_1.json"},
     * {"id":10006,"title":"中国","type":1,"url":"/10006/list_1.json"},
     * {"id":10008,"title":"国际","type":1,"url":"/10008/list_1.json"},
     * {"id":10010,"title":"体育","type":1,"url":"/10010/list_1.json"},
     * {"id":10091,"title":"生活","type":1,"url":"/10091/list_1.json"},
     * {"id":10012,"title":"旅游","type":1,"url":"/10012/list_1.json"},
     * {"id":10095,"title":"科技","type":1,"url":"/10095/list_1.json"},
     * {"id":10009,"title":"军事","type":1,"url":"/10009/list_1.json"},
     * {"id":10093,"title":"时尚","type":1,"url":"/10093/list_1.json"},
     * {"id":10011,"title":"财经","type":1,"url":"/10011/list_1.json"},
     * {"id":10094,"title":"育儿","type":1,"url":"/10094/list_1.json"},
     * {"id":10105,"title":"汽车","type":1,"url":"/10105/list_1.json"}]
     */

    public List<DataBean> data;
    public List<Integer> extend;

    public static class DataBean {
        public int id;
        public String title;
        public int type;
        /**
         * id : 10007
         * title : 北京
         * type : 1
         * url : /10007/list_1.json
         */

        public List<ChildrenBean> children;

        public static class ChildrenBean {
            public int id;
            public String title;
            public int type;
            public String url;
        }
    }
}
