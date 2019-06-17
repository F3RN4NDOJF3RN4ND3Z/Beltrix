package com.fernandoapps.utils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PatternFinder extends Thread{
    private String url;
    private Writer fw;
    private File outputFile;
    private String index;
    private String pattern;
    private StreamURLReader urlReader;
    private static final Logger LOGGER = Logger.getLogger(PatternFinder.class.getName());

    public PatternFinder(String url,String index, String pattern) throws MalformedURLException {
        this.url=url;
        this.index=index;
        this.pattern=pattern;
        this.urlReader= new StreamURLReader(url);
    }

    public String getParagraphInnerHTML(String text){
        String innerText="";
        int startParagraph=text.indexOf("<p");
        int endParagraph=text.indexOf("</p>");
        System.out.println(startParagraph);
        System.out.println(endParagraph);
        if(startParagraph > 0 && endParagraph > 0){
            return innerText + text.substring(startParagraph ,endParagraph) +
                    getParagraphInnerHTML(text.substring(endParagraph + 3,text.length()-1));
        }
        return innerText;
    }

    public void run() {
        try {
            urlReader.getURLCotent();
            StringBuilder strBuilder= new StringBuilder();
            outputFile= new File("found_in_"+index+".txt");
            fw=new BufferedWriter(new FileWriter(outputFile));
            String innerText=getParagraphInnerHTML(urlReader.getPageContent());
            if (innerText.contains(pattern)){
                strBuilder.append(innerText +"\n");
            }else{

            }


            fw.write(strBuilder.toString());
            System.out.println("File Created");
        } catch(IOException ex) {
            LOGGER.log(Level.SEVERE,"Error creating output file");
        } catch(URISyntaxException ex) {
            LOGGER.log(Level.SEVERE,"Error creating output file");
        } finally{
            try {
                if(fw != null)
                    fw.close();
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE,"Error closing file");
            }
        }

    }



}
