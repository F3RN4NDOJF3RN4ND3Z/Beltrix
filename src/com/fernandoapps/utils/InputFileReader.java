package com.fernandoapps.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InputFileReader {
    private File file;
    private boolean isReady;
    private static final Logger LOGGER = Logger.getLogger(StreamURLReader.class.getName());
    public InputFileReader(){
        file=null;
        isReady=false;
    }

    public void loadFile(String filename){
        FileReader fr = null;
        BufferedReader br = null;
        isReady=false;
        try {
            file = new File (filename);
            fr = new FileReader (file);
            br = new BufferedReader(fr);
            if(br.ready()) {
                isReady=true;
            }else{
                throw new Exception("File is not ready");
            }

        }catch (FileNotFoundException ex){
            LOGGER.log(Level.SEVERE,"Error file not found file:" + filename,ex);
        }catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error reading the file:" + filename, ex);
        }catch (Exception ex){
                LOGGER.log(Level.SEVERE,"Error reading the file:" + filename,ex);
        }finally {
            try{
                if( null != fr ){
                    fr.close();
                }
            }catch (Exception ex){
                LOGGER.log(Level.SEVERE,"Error validating file:" + filename,ex);
            }
        }
    }

    public boolean isReady() {
        return isReady;
    }

    public ArrayList<String> readLines() {
        ArrayList<String> lines= new ArrayList<String>();
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader (file);

            br = new BufferedReader(fr);
            String line="";
            while ((line = br.readLine()) != null){
                if(!line.isEmpty()){
                    lines.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE,"Error reading lines",e);
        } catch (IOException e){
            LOGGER.log(Level.SEVERE,"Error reading lines",e);
        }finally {
            try{
                if( null != fr ){
                    fr.close();
                }
            }catch (Exception ex){
                LOGGER.log(Level.SEVERE,"Error reading lines" ,ex);
            }
        }

        return lines;
    }
}
