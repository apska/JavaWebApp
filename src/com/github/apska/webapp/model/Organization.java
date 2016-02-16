package com.github.apska.webapp.model;

import java.util.Date;
import java.util.List;

/**
 * Created by APS2
 * on 09.02.2016
 */
public class Organization {
    private Link link;
    private List<Period> periods;

    public static class Period {
        private Date startDates;
        private Date endDates;
        private String position;
        private String content;

        public Period() {
        }

        public Period(Date startDates, String content, String position, Date endDates) {
            this.startDates = startDates;
            this.content = content;
            this.position = position;
            this.endDates = endDates;
        }
    }
}
