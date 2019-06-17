package com.fernandoapps.utils;
import java.net.*;
import java.io.*;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class StreamURLReader {
    private URL pageURL;
    private String strURL;
    private String pageContent;
    private static final Logger LOGGER = Logger.getLogger(StreamURLReader.class.getName());
    public StreamURLReader(String url) throws MalformedURLException {
        this.strURL=url;
        this.pageURL= new  URL(url);
        this.pageContent= "";
    }
    public void getURLCotent() throws URISyntaxException {
        try{
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(pageURL.openStream()));

            String inputLine;
            StringBuilder strBuilder= new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                strBuilder.append(inputLine);

            }
            this.pageContent=strBuilder.toString();
            in.close();
        }catch (IOException ex){
            LOGGER.log(Level.SEVERE,"Error retriving URL content:" + pageURL.toURI().getHost(),ex);
        }catch (Exception ex){
            LOGGER.log(Level.SEVERE,"Error retriving URL content:" + pageURL.toURI().getHost(),ex);
        }

    }

    public String getStrURL() {
        return strURL;
    }

    public void setStrURL(String strURL) {
        this.strURL = strURL;
    }

    public String getPageContent() {
        return pageContent;
    }

    public void setPageContent(String pageContent) {
        this.pageContent = pageContent;
    }

}
