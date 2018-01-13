package gy.lotteryticket.model;

import java.util.List;

/**
 * Created by Finder丶畅畅 on 2018/1/13 06:24
 * QQ群481606175
 */

public class LHCResult {

    /**
     * id : 70
     * name : lhc
     * title : 香港六合彩
     * data_ftime : 300
     * num : 1
     * dataGroup : [{"name":"自选不中","dataTitle":[{"name":"自选不中","dataContent":[{"id":"709805","name":"5","code":"","played_groupid":"98","odds":"2.170","rebate":"0.000","ruleFun":"LHCZH","minMoney":"1","maxMoney":"100000"},{"id":"709806","name":"6","code":"","played_groupid":"98","odds":"2.630","rebate":"0.000","ruleFun":"LHCZH","minMoney":"1","maxMoney":"100000"},{"id":"709807","name":"7","code":"","played_groupid":"98","odds":"3.180","rebate":"0.000","ruleFun":"LHCZH","minMoney":"1","maxMoney":"100000"},{"id":"709808","name":"8","code":"","played_groupid":"98","odds":"3.720","rebate":"0.000","ruleFun":"LHCZH","minMoney":"1","maxMoney":"100000"},{"id":"709809","name":"9","code":"","played_groupid":"98","odds":"4.500","rebate":"0.000","ruleFun":"LHCZH","minMoney":"1","maxMoney":"100000"},{"id":"709810","name":"10","code":"","played_groupid":"98","odds":"5.580","rebate":"0.000","ruleFun":"LHCZH","minMoney":"1","maxMoney":"100000"},{"id":"709811","name":"11","code":"","played_groupid":"98","odds":"6.800","rebate":"0.000","ruleFun":"LHCZH","minMoney":"1","maxMoney":"100000"},{"id":"709812","name":"12","code":"","played_groupid":"98","odds":"8.500","rebate":"0.000","ruleFun":"LHCZH","minMoney":"1","maxMoney":"100000"}]}]}]
     */

    private String id;
    private String name;
    private String title;
    private String num;
    private List<DataGroupBean> dataGroup;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public List<DataGroupBean> getDataGroup() {
        return dataGroup;
    }

    public void setDataGroup(List<DataGroupBean> dataGroup) {
        this.dataGroup = dataGroup;
    }

    public static class DataGroupBean {
        /**
         * name : 自选不中
         * dataTitle : [{"name":"自选不中","dataContent":[{"id":"709805","name":"5","code":"","played_groupid":"98","odds":"2.170","rebate":"0.000","ruleFun":"LHCZH","minMoney":"1","maxMoney":"100000"},{"id":"709806","name":"6","code":"","played_groupid":"98","odds":"2.630","rebate":"0.000","ruleFun":"LHCZH","minMoney":"1","maxMoney":"100000"},{"id":"709807","name":"7","code":"","played_groupid":"98","odds":"3.180","rebate":"0.000","ruleFun":"LHCZH","minMoney":"1","maxMoney":"100000"},{"id":"709808","name":"8","code":"","played_groupid":"98","odds":"3.720","rebate":"0.000","ruleFun":"LHCZH","minMoney":"1","maxMoney":"100000"},{"id":"709809","name":"9","code":"","played_groupid":"98","odds":"4.500","rebate":"0.000","ruleFun":"LHCZH","minMoney":"1","maxMoney":"100000"},{"id":"709810","name":"10","code":"","played_groupid":"98","odds":"5.580","rebate":"0.000","ruleFun":"LHCZH","minMoney":"1","maxMoney":"100000"},{"id":"709811","name":"11","code":"","played_groupid":"98","odds":"6.800","rebate":"0.000","ruleFun":"LHCZH","minMoney":"1","maxMoney":"100000"},{"id":"709812","name":"12","code":"","played_groupid":"98","odds":"8.500","rebate":"0.000","ruleFun":"LHCZH","minMoney":"1","maxMoney":"100000"}]}]
         */

        private String name;
        private List<DataTitleBean> dataTitle;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<DataTitleBean> getDataTitle() {
            return dataTitle;
        }

        public void setDataTitle(List<DataTitleBean> dataTitle) {
            this.dataTitle = dataTitle;
        }

        public static class DataTitleBean {
            /**
             * name : 自选不中
             * dataContent : [{"id":"709805","name":"5","code":"","played_groupid":"98","odds":"2.170","rebate":"0.000","ruleFun":"LHCZH","minMoney":"1","maxMoney":"100000"},{"id":"709806","name":"6","code":"","played_groupid":"98","odds":"2.630","rebate":"0.000","ruleFun":"LHCZH","minMoney":"1","maxMoney":"100000"},{"id":"709807","name":"7","code":"","played_groupid":"98","odds":"3.180","rebate":"0.000","ruleFun":"LHCZH","minMoney":"1","maxMoney":"100000"},{"id":"709808","name":"8","code":"","played_groupid":"98","odds":"3.720","rebate":"0.000","ruleFun":"LHCZH","minMoney":"1","maxMoney":"100000"},{"id":"709809","name":"9","code":"","played_groupid":"98","odds":"4.500","rebate":"0.000","ruleFun":"LHCZH","minMoney":"1","maxMoney":"100000"},{"id":"709810","name":"10","code":"","played_groupid":"98","odds":"5.580","rebate":"0.000","ruleFun":"LHCZH","minMoney":"1","maxMoney":"100000"},{"id":"709811","name":"11","code":"","played_groupid":"98","odds":"6.800","rebate":"0.000","ruleFun":"LHCZH","minMoney":"1","maxMoney":"100000"},{"id":"709812","name":"12","code":"","played_groupid":"98","odds":"8.500","rebate":"0.000","ruleFun":"LHCZH","minMoney":"1","maxMoney":"100000"}]
             */

            private String name;
            private List<XZModel> dataContent;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<XZModel> getDataContent() {
                return dataContent;
            }

            public void setDataContent(List<XZModel> dataContent) {
                this.dataContent = dataContent;
            }
        }
    }
}
