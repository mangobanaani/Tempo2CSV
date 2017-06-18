package com.github.mangobanaani.worklog;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 Copyright (c) by mangobanaani 2017.

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>

 */

// concrete worklog implementation

public class Worklog {
    private int worklog_id;
    private int issue_id;
    private String issue_key;
    private Double hours;
    private Double billed_hours;
    private LocalDate work_date;
    private String username;
    private String staff_id;
    private String billing_key;
    private String billing_attributes;
    private String activity_id;
    private String activity_name;
    private String work_description;
    private String parent_key;
    private String reporter;
    private int external_id;
    private LocalDateTime external_tstamp;
    private Double external_hours;
    private String external_result;
    private String hashvalue;
    private String issue_summary;

    private String full_name;
    private LocalDateTime created;
    private LocalDateTime updated;

    public Worklog(int worklog_id, int issue_id, String issue_key, Double hours, Double billed_hours, LocalDate work_date, String username, String staff_id, String billing_key, String billing_attributes, String activity_id, String activity_name, String work_description, String parent_key, String reporter, int external_id, LocalDateTime external_tstamp, Double external_hours, String external_result, String hashvalue, String issue_summary, String full_name, LocalDateTime created, LocalDateTime updated) {
        this.worklog_id = worklog_id;
        this.issue_id = issue_id;
        this.issue_key = issue_key;
        this.hours = hours;
        this.billed_hours = billed_hours;
        this.work_date = work_date;
        this.username = username;
        this.staff_id = staff_id;
        this.billing_key = billing_key;
        this.billing_attributes = billing_attributes;
        this.activity_id = activity_id;
        this.activity_name = activity_name;
        this.work_description = work_description;
        this.parent_key = parent_key;
        this.reporter = reporter;
        this.external_id = external_id;
        this.external_tstamp = external_tstamp;
        this.external_hours = external_hours;
        this.external_result = external_result;
        this.hashvalue = hashvalue;
        this.issue_summary = issue_summary;
        this.full_name = full_name;
        this.created = created;
        this.updated = updated;
    }

    public int getWorklog_id() {
        return worklog_id;
    }

    public int getIssue_id() {
        return issue_id;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(worklog_id)
                .append(", ").append(issue_id)
                .append(", ").append(issue_key)
                .append(", ").append(hours)
                .append(", ").append(billed_hours)
                .append(", ").append(work_date)
                .append(", ").append(username)
                .append(", ").append(staff_id)
                .append(", ").append(billing_key)
                .append(", ").append(billing_attributes)
                .append(", ").append(activity_id)
                .append(", ").append(activity_name)
                .append(", ").append(work_description)
                .append(", ").append(parent_key)
                .append(", ").append(reporter)
                .append(", ").append(external_id)
                .append(", ").append(external_tstamp)
                .append(", ").append(external_hours)
                .append(", ").append(external_result)
                .append(", ").append(hashvalue)
                .append(", ").append(issue_summary)
                .append(", ").append(full_name)
                .append(", ").append(created)
                .append(", ").append(updated).toString();
    }
}
