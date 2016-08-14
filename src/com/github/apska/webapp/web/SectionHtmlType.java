package com.github.apska.webapp.web;

import com.github.apska.webapp.model.MultyTextSection;
import com.github.apska.webapp.model.Section;
import com.github.apska.webapp.model.SectionType;
import com.github.apska.webapp.model.TextSection;

import java.util.Collections;

import static com.github.apska.webapp.web.HtmlUtil.input;
import static com.github.apska.webapp.web.HtmlUtil.textArea;

public enum SectionHtmlType {
    TEXT {
        @Override
        public String toHtml(Section section, SectionType type) {
            return input(type.name(), section == null ? "" : ((TextSection) section).getValue());
        }

        @Override
        public TextSection createSection(String value) {
            return new TextSection(value);
        }
    },
    MULTI_TEXT {
        @Override
        public String toHtml(Section section, SectionType type) {
            return textArea(type.name(), section == null ? Collections.singletonList("") :((MultyTextSection) section).getValues());
        }

        @Override
        public MultyTextSection createSection(String values) {
            return new MultyTextSection(values.split("\\n"));
        }
    },
    ORGANIZATION {
        @Override
        public String toHtml(Section section, SectionType type) {
            return section.toString();
        }

        @Override
        public Section createSection(String value) {
            throw new UnsupportedOperationException();
        }
    };

    public abstract String toHtml(Section section, SectionType type);

    public abstract Section createSection(String value);
}
