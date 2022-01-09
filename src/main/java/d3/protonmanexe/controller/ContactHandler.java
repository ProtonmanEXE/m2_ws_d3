package d3.protonmanexe.controller;

import static d3.protonmanexe.Constants.*;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.ui.Model;

public class ContactHandler {

    private final static Logger logging = LoggerFactory.getLogger(ContactHandler.class); // instantiate logger

    public void saveContact (Contact c, Model model, ApplicationArguments appArgs) throws IOException {        
        // variable declaration
        String id = "";
        boolean check = true;

		// object initialisation
        Random r = new Random();
        StringBuffer sb = new StringBuffer(); // use StringBuffer instead of String as StringBuffer is a thread-safe, 
                                              // mutable sequence of characters and is safe for use by multiple threads

        // Task 3a - randomly generate an eight character long hex string
        List<String> dataDir = appArgs.getOptionValues(OPTION_DATADIR);
        File database = new File(dataDir.get(0));
        File[] allFiles = database.listFiles();

        // this while loop ensures there is no duplicate id in the folder
        while (check) { 
            while(sb.length() < ID_LENGTH) { // as StringBuffer is an object and a new instance of the object cannot be created each 
                                             // time it loops, so there is no choice but to use append method to add on to the StringBuffer
                                             // length until it is more than 8; this also means I cannot use not equal to 8 condition
            sb.append(Integer.toHexString(r.nextInt()));
            }
            id = sb.toString().substring(0, ID_LENGTH); // hence, I need to shrink StringBuffer to a length of 8

        // this for loop returns check = true if there is a duplicate id to reiterate the while loop
            for (int i = 0; i < allFiles.length; i++) { 
                // logging.info(i +" File name > " + allFiles[i].getName().replaceFirst("[.][^.]+$", ""));
                // logging.info(i +" id > " + id);
                if (allFiles[i].getName().replaceFirst("[.][^.]+$", "") == id) {
                    check = true; 
                    break;
                } else check = false;
            }
        } 
        logging.info("id > " +id);

        // Task 3b - create save file
        String contactFileName = id + ".txt";
        // logging.info("Save location is " +dataDir.get(0) +"and file name is " +contactFileName);
        File userFile = new File(dataDir.get(0), contactFileName);
        FileWriter fileWriter = new FileWriter(userFile, Charset.forName("utf-8")); // file writer will create a file 

        // Task 3c - write data into file
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println(c.getName());
        printWriter.println(c.getPhoneNo());
        printWriter.println(c.getEmail());
        printWriter.close();
    }

    public void getContactById (String id, Model model, ApplicationArguments appArgs) throws IOException {
        List<String> dataDir = appArgs.getOptionValues(OPTION_DATADIR);

        // object initialisation
        Contact getContact = new Contact();
        List<String> store = new ArrayList<>();

        // find and read file with same name as id
        try {
            Path filePath = new File(dataDir.get(0)+ '\\' + id +".txt").toPath();
            // logging.info("ContactHandler filePath > " + filePath); 
            store = Files.readAllLines(filePath, Charset.forName("utf-8"));
        }catch(IOException e){
            logging.error(e.getMessage());
        }

        // store contact details from file into Contact object
        getContact.setName(store.get(0));
        getContact.setPhoneNo(Integer.parseInt(store.get(1)));
        getContact.setEmail(store.get(2));
        model.addAttribute("yourcontact", getContact); // then add to html page
    }
    
}