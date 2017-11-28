package com.jzg.erp.appraiser.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zealjiang on 2016/8/5 09:49.
 * Email: zealjiang@126.com
 */
public class CarStyle extends BaseObject implements Serializable {

    /**
     * {
     "success": true,
     "status": 100,
     "msg": "成功",
     "YearGroupList": [
     {
     "Year": "2004",
     "StyleList": [
     {
     "Id": "3632",
     "Name": "3.0L 自动",
     "Year": "2004",
     "NowMsrp": "58.80",
     "FullName": "阿尔法·罗密欧166 2004款 3.0L 自动",
     "MinYEAR": "2003-06",
     "MaxYEAR": "2016-07"
     },
     {
     "Id": "10000818",
     "Name": "2.0T 手动 Spark",
     "Year": "2004",
     "NowMsrp": "30.00",
     "FullName": "阿尔法·罗密欧166 2004款 2.0T 手动 Spark",
     "MinYEAR": "2003-06",
     "MaxYEAR": "2016-07"
     }
     ]
     }
     ]
     }
     */

    private List<YearGroupListBean> YearGroupList;

    public List<YearGroupListBean> getYearGroupList() {
        return YearGroupList;
    }

    public void setYearGroupList(List<YearGroupListBean> YearGroupList) {
        this.YearGroupList = YearGroupList;
    }

    public static class YearGroupListBean {
        private String Year;
        /**
         * Id : 3632
         * Name : 3.0L 自动
         * Year : 2004
         * NowMsrp : 58.80
         * FullName : 阿尔法·罗密欧166 2004款 3.0L 自动
         * MinYEAR : 2003-06
         * MaxYEAR : 2016-07
         */

        private List<StyleListBean> StyleList;

        public String getYear() {
            return Year;
        }

        public void setYear(String Year) {
            this.Year = Year;
        }

        public List<StyleListBean> getStyleList() {
            return StyleList;
        }

        public void setStyleList(List<StyleListBean> StyleList) {
            this.StyleList = StyleList;
        }

        public static class StyleListBean {
            private String Id;
            private String Name;
            private String Year;
            private String NowMsrp;
            private String FullName;
            private String MinYEAR;
            private String MaxYEAR;

            public String getId() {
                return Id;
            }

            public void setId(String Id) {
                this.Id = Id;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public String getYear() {
                return Year;
            }

            public void setYear(String Year) {
                this.Year = Year;
            }

            public String getNowMsrp() {
                return NowMsrp;
            }

            public void setNowMsrp(String NowMsrp) {
                this.NowMsrp = NowMsrp;
            }

            public String getFullName() {
                return FullName;
            }

            public void setFullName(String FullName) {
                this.FullName = FullName;
            }

            public String getMinYEAR() {
                return MinYEAR;
            }

            public void setMinYEAR(String MinYEAR) {
                this.MinYEAR = MinYEAR;
            }

            public String getMaxYEAR() {
                return MaxYEAR;
            }

            public void setMaxYEAR(String MaxYEAR) {
                this.MaxYEAR = MaxYEAR;
            }
        }
    }

}
