import com.fernandoapps.utils.PatternFinder;
import com.fernandoapps.utils.StreamURLReader;
import com.fernandoapps.utils.InputFileReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());


    public static void main(String[] args) {
        String fileInput="";
        String pattern="";
        boolean validOption=false;
        String option="0";
        System.out.println("--------------------Welcome to the WebPatterSearch----------------------------");
        System.out.println("This programs reads a file with web URLs and find in them one of the following patterns: ");
        System.out.println("All Refference to a hash tag");
        System.out.println("All Refference to a twitter account");
        System.out.println("Any Proper name");
        System.out.println("Please Follow the instruction Bellow.");
        System.out.println("--------------------Welcome to the WebPatterSearch----------------------------");
        Scanner reader = new Scanner(System.in);
        InputFileReader inputFileReader = new InputFileReader();
        while (!inputFileReader.isReady()){
            System.out.println("Enter the path to the file to read or leave it empty to use the test file: ");
            fileInput = reader.nextLine();
            if(fileInput.isEmpty()){
             fileInput=ClassLoader.getSystemResource("test.txt").getPath();
            }
            inputFileReader.loadFile(fileInput);
        }
        while (!validOption) {
            System.out.println("Choose the option to find: ");
            System.out.println("1.All Refference to a hash tag");
            System.out.println("2.All Refference to a twitter account");
            System.out.println("3.Any Proper name");
            option = reader.nextLine();

            switch (option) {
                case "1":
                    System.out.println("Enter patter of the has tag: ");
                    pattern = reader.nextLine();
                    validOption=true;
                    break;
                case "2":
                    System.out.println("Enter patter of the twitter account: ");
                    pattern = reader.nextLine();
                    validOption=true;
                    break;
                case "3":
                    System.out.println("Enter patter of the name: ");
                    pattern = reader.nextLine();
                    validOption=true;
                    break;
                default:
                    System.out.println("No valid Option.");
                    validOption=false;
                    break;
            }
        }
        reader.close();
        System.out.println("File Input:" + fileInput);
        System.out.println("File Option:" + option);
        System.out.println("File Option:" + pattern);

        ArrayList<String> urls=inputFileReader.readLines();
        int index=0;
        try{
            for (String url:urls) {
                index++;
                PatternFinder pf= new PatternFinder(url,Integer.toString(index),pattern);
                pf.start();
            }
        }catch (MalformedURLException ex){
            LOGGER.log(Level.SEVERE,"Error parsing site", ex);
        }

    }



    public void WebPatterSearcher(){
        try {
            StreamURLReader urlReader= new StreamURLReader("https://www.boston.com");
            urlReader.getURLCotent();
        } catch (MalformedURLException e) {
            LOGGER.log(Level.SEVERE,"Error in the excution of WebPatterSearch",e);
        } catch (URISyntaxException e) {
            LOGGER.log(Level.SEVERE,"Error in the excution of WebPatterSearch",e);
        }
    }
}
