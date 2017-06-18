package com.github.mangobanaani.worklog;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.LocalDate.parse;

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

// builder pattern for worklog creation

public class WorklogBuilder {
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

    public WorklogBuilder setWorklog_id(String worklog_id) {
        this.worklog_id = Integer.parseInt(worklog_id);
        return this;
    }

    public WorklogBuilder setIssue_id(String issue_id) {
        if(issue_id.length()>0) {
            this.issue_id = Integer.parseInt(issue_id);
        }else{
            this.issue_id=0;
        }
        return this;
    }

    public WorklogBuilder setIssue_key(String issue_key) {
        this.issue_key = issue_key;
        return this;
    }

    public WorklogBuilder setHours(String hours) {
        this.hours = Double.parseDouble(hours);
        return this;
    }

    public WorklogBuilder setBilled_hours(String billed_hours) {
        this.billed_hours = Double.parseDouble(billed_hours);
        return this;
    }

    public WorklogBuilder setWork_date(String work_date) {
        // work_date yyyy-mm-dd
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.work_date = parse(work_date, formatter);
        return this;
    }

    public WorklogBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public WorklogBuilder setStaff_id(String staff_id) {
        this.staff_id = staff_id;
        return this;
    }

    public WorklogBuilder setBilling_key(String billing_key) {
        if(billing_key.length()>0){
            this.billing_key = billing_key.replace("account=","");
        }else {
            billing_key = "NA";
        }
        return this;
    }

    public WorklogBuilder setBilling_attributes(String billing_attributes) {
        // rip off possible static 'account=' from billing attributes as unnecessary tagging information
        this.billing_attributes = billing_attributes.replace("account=","");
        return this;
    }

    public WorklogBuilder setActivity_id(String activity_id) {
        if(activity_id.length()>0) {
            String str=activity_id.replace(",","");
            str = cleanString(str);
            this.activity_id = str;
        }else{
            this.activity_id="NA";
        }
        return this;
    }

    public WorklogBuilder setActivity_name(String activity_name) {
        if(activity_name.length()>0) {
            String str=activity_name.replace(",","");
            str = cleanString(str);
            this.activity_name = str;
        }else{
            this.activity_name="NA";
        }
        return this;
    }

    public WorklogBuilder setWork_description(String work_description) {
        // rip off extra commas, not so needed in R - alternative would be to escape all properly
        String str=work_description.replace(",","");
        str = cleanString(str);
        this.work_description = str;
        return this;
    }

    private String cleanString(String str) {
        str=str.replaceAll("(?:\\n|\\r)", ""); // trim out the the string
        return str;
    }

    public WorklogBuilder setParent_key(String parent_key) {
        if(parent_key.length()>0) {
            this.parent_key = parent_key;
        }else{
            this.parent_key="NA";
        }
        return this;
    }

    public WorklogBuilder setReporter(String reporter) {
        if(reporter.length()>0) {
            this.reporter = reporter;
        }else {
            this.reporter="NA";
        }
        return this;
    }

    public WorklogBuilder setExternal_id(String external_id) {
        if(external_id.length()>0){
            this.external_id = Integer.parseInt(external_id);
        }else{
            this.external_id=0;
        }
        return this;
    }

    public WorklogBuilder setExternal_tstamp(String external_tstamp) {
        if(external_tstamp.length()>0) {
            //assume here tstamp format 2017-05-08 16:29:21
            DateTimeFormatter formatter = getDateTimeFormatter();
            this.external_tstamp = LocalDateTime.from(parse(external_tstamp, formatter));
        }else this.external_tstamp = null;
        return this;
    }

    public WorklogBuilder setExternal_hours(String external_hours) {
        if(external_hours.length()>0){
            this.external_hours = Double.parseDouble(external_hours);
        }else{
            this.external_hours=0.0;
        }
        return this;
    }

    public WorklogBuilder setExternal_result(String external_result) {
        if(external_result.length()>0){
            this.external_result = external_result;
        }else{
            this.external_result="NA";
        }
        return this;
    }

    public WorklogBuilder setHashvalue(String hashvalue) {
        if(hashvalue.length()>0){
            this.hashvalue = hashvalue;
        }else{
            this.hashvalue="NA";
        }
        return this;
    }

    public WorklogBuilder setIssue_summary(String issue_summary) {
        if(issue_summary.length()>0){
            this.issue_summary = issue_summary;
        }else{
            this.issue_summary="NA";
        }
        return this;
    }

    public WorklogBuilder setFull_name(String full_name) {
        if(full_name.length()>0){
            this.full_name = full_name;
        }else{
            this.full_name="NA";
        }
        return this;
    }

    public WorklogBuilder setCreated(String created) {
        //created format 2017-05-08 16:29:21
        DateTimeFormatter formatter = getDateTimeFormatter();
        this.created = LocalDateTime.parse(created,formatter);
        return this;
    }

    public WorklogBuilder setUpdated(String updated) {
        //updated format 2017-05-08 16:29:21
        DateTimeFormatter formatter = getDateTimeFormatter();
        this.updated = LocalDateTime.parse(updated, formatter);
        return this;
    }

    @NotNull
    private DateTimeFormatter getDateTimeFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    public Worklog createWorklog() {
        return new Worklog(worklog_id, issue_id, issue_key, hours, billed_hours, work_date, username, staff_id, billing_key, billing_attributes, activity_id, activity_name, work_description, parent_key, reporter, external_id, external_tstamp, external_hours, external_result, hashvalue, issue_summary, full_name, created, updated);
    }
}