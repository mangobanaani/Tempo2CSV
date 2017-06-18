package com.github.mangobanaani.worklog;

import com.github.mangobanaani.worklog.Worklog;

import java.util.ArrayList;
import java.util.List;

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

// collection class dealing with worklogs

public class Worklogs {
    private final List<Worklog> worklogs;

    public Worklogs() {
        worklogs = new ArrayList<>();
    }

    public Worklogs(List<Worklog> worklogs) {
        this.worklogs = worklogs;
    }

    public void add(Worklog wl) {
        if(wl.getIssue_id()!=0) {
            this.worklogs.add(wl);
        }
    }

    public List<Worklog> get(){
        return this.worklogs;
    }

    public Worklog getWorklog(int worklog_id){
        for(Worklog wl:worklogs){
            if(wl.getWorklog_id()==worklog_id){
                return wl;
            }
        }
        return null;
    }

    public String getHeaderRow(){ // returns header row if wanted
        return new StringBuilder()
                .append("worklog_id")
                .append(", ").append("issue_id")
                .append(", ").append("issue_key")
                .append(", ").append("hours")
                .append(", ").append("billed_hours")
                .append(", ").append("work_date")
                .append(", ").append("username")
                .append(", ").append("staff_id")
                .append(", ").append("billing_key")
                .append(", ").append("billing_attributes")
                .append(", ").append("activity_id")
                .append(", ").append("activity_name")
                .append(", ").append("work_description")
                .append(", ").append("parent_key")
                .append(", ").append("reporter")
                .append(", ").append("external_id")
                .append(", ").append("external_tstamp")
                .append(", ").append("external_hours")
                .append(", ").append("external_result")
                .append(", ").append("hashvalue")
                .append(", ").append("issue_summary")
                .append(", ").append("full_name")
                .append(", ").append("created")
                .append(", ").append("updated").toString();
    }

}
