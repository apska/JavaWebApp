package com.github.apska.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.List;

/**
 * Created by APS2
 * on 09.02.2016
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization {
    private Link link;
    private List<Period> periods;

    public Organization() {
    }

    public Organization(Link link, List<Period> periods) {
        this.link = link;
        this.periods = periods;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Period implements Serializable {
        static final long serialVersionUID = 1L;

        public static final LocalDate NOW = LocalDate.of(3000, 1,1);

        private LocalDate startDates;
        private LocalDate endDates;
        private String position;
        private String content;

        public Period() {
        }

        public Period(int startYear, Month startMonth, int endYear, Month endMonth, String position, String content){
            this(LocalDate.of(startYear, startMonth, 1), LocalDate.of(endYear, endMonth, 1), position, content);
        }

        public Period(LocalDate startDates, LocalDate endDates, String content, String position) {
            this.startDates = startDates;
            this.content = content;
            this.position = position;
            this.endDates = endDates;
        }
    }
}
