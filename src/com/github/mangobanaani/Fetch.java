package com.github.mangobanaani.worklog;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;


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

public class Fetch {

    /**
     * Prettifies XML to more concise and human readable format
     *
     * @param input XML as String to be prettified
     * @param indent intendation to be used
     * @return prettified string
     */
    private static String prettyFormat(String input, int indent) {
        try {
            Source xmlInput = new StreamSource(new StringReader(input));
            StringWriter stringWriter = new StringWriter();
            StreamResult xmlOutput = new StreamResult(stringWriter);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute("indent-number", indent);
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(xmlInput, xmlOutput);
            transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "yes");
            return xmlOutput.getWriter().toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param urlToRead address from which document is fetched
     * @return document as string object
     * @throws Exception
     */
    public static String getHTML(String urlToRead) throws Exception{
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        try (BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
        }
        return result.toString();
    }

    /**
     * prettifies xml to human readable with static intendation of 2
     * @param input document as string
     * @return document as string
     */
    public static String prettyFormat(String input) {
        return prettyFormat(input, 2);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        String xml;                             // local var holding raw xml document
        Worklogs wlogs=new Worklogs();          // list of worklog items
        String jiraURL="https://yoururl.atlassian.net"; // url of jira-tempo from where to fetch
        String dateFrom="2015-01-01";               // get logs after this date
        String dateTo="2015-12-31";                 // get logs until this date
        String tempoAPIToken="00000000-0000-0000-0000-000000000000";    // tempoAPI token, see your installation for correct one
        // form correct url to fetch neccessary logs
        String url=jiraURL+"/plugins/servlet/tempo-getWorklog/?dateFrom="+dateFrom+"&dateTo="+dateTo+"&format=xml&diffOnly=false&tempoApiToken="+tempoAPIToken+"&addBillingInfo=true&addIssueSummary=true&addIssueDetails=true&addWorklogDetails=true&addUserDetails=true";

        String XMLfile="values.xml";            // name of xml file we fetch from the tempo
        String csvfile="values.csv";            // name of file we write the result into

        // fetch the logs from jiraURL in xml format and write to local file

        try {
            xml = getHTML(url);
            PrintWriter writer = new PrintWriter(XMLfile, "UTF-8");
            writer.println(prettyFormat(xml));
            writer.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        // parse the XML and create a list of worklog objects

        try {
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(XMLfile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("worklog");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    Worklog wl= new WorklogBuilder()
                            .setWorklog_id(eElement.getElementsByTagName("worklog_id").item(0).getTextContent())
                            .setIssue_id(eElement.getElementsByTagName("issue_id").item(0).getTextContent())
                            .setIssue_key(eElement.getElementsByTagName("issue_key").item(0).getTextContent())
                            .setHours(eElement.getElementsByTagName("hours").item(0).getTextContent())
                            .setBilled_hours(eElement.getElementsByTagName("billed_hours").item(0).getTextContent())
                            .setWork_date(eElement.getElementsByTagName("work_date").item(0).getTextContent())
                            .setUsername(eElement.getElementsByTagName("username").item(0).getTextContent())
                            .setStaff_id(eElement.getElementsByTagName("staff_id").item(0).getTextContent())
                            .setBilling_key(eElement.getElementsByTagName("billing_key").item(0).getTextContent())
                            .setBilling_attributes(eElement.getElementsByTagName("billing_attributes").item(0).getTextContent())
                            .setActivity_id(eElement.getElementsByTagName("activity_id").item(0).getTextContent())
                            .setActivity_name(eElement.getElementsByTagName("activity_name").item(0).getTextContent())
                            .setWork_description(eElement.getElementsByTagName("work_description").item(0).getTextContent())
                            .setParent_key(eElement.getElementsByTagName("parent_key").item(0).getTextContent())
                            .setReporter(eElement.getElementsByTagName("reporter").item(0).getTextContent())
                            .setExternal_id(eElement.getElementsByTagName("external_id").item(0).getTextContent())
                            .setExternal_tstamp(eElement.getElementsByTagName("external_tstamp").item(0).getTextContent())
                            .setExternal_hours(eElement.getElementsByTagName("external_hours").item(0).getTextContent())
                            .setExternal_tstamp(eElement.getElementsByTagName("external_tstamp").item(0).getTextContent())
                            .setExternal_hours(eElement.getElementsByTagName("external_hours").item(0).getTextContent())
                            .setExternal_result(eElement.getElementsByTagName("external_result").item(0).getTextContent())
                            .setHashvalue(eElement.getElementsByTagName("hash_value").item(0).getTextContent())
                            .setIssue_summary(eElement.getElementsByTagName("issue_summary").item(0).getTextContent())
                            .setFull_name(eElement.getElementsByTagName("full_name").item(0).getTextContent())
                            .setCreated(eElement.getElementsByTagName("created").item(0).getTextContent())
                            .setUpdated(eElement.getElementsByTagName("updated").item(0).getTextContent())
                            .createWorklog();
                    wlogs.add(wl);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // write the worklogs into a csv file

        //print header row
        System.out.println(wlogs.getHeaderRow());
        //and the data
        try {
            Iterator<Worklog> itr = wlogs.get().iterator();
            PrintWriter writer = new PrintWriter(csvfile, "UTF-8");
            writer.println(wlogs.getHeaderRow());
            while (itr.hasNext()) {
                Worklog wl = itr.next();
                    writer.println(wl);
            }
            writer.close();
        }catch(Exception e){e.printStackTrace();}
    }
}


