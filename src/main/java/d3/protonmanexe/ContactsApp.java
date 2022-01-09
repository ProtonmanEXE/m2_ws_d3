// Dev.: ProtonmanEXE
// Dev. Notes: 
// this is the initialisation for SpringBootApplication for Day 3 Workshop whereby the
// directory must be specified by user before app can start

package d3.protonmanexe;

import static d3.protonmanexe.Constants.*;

import java.util.List;
import java.io.File;
import java.io.IOException; 

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ContactsApp {

	public static void main(String[] args) throws IOException{
		
		// start of Task 1
		// object initialisation
		ApplicationArguments appArgs = new DefaultApplicationArguments(args);
		List<String> dataDir = appArgs.getOptionValues(OPTION_DATADIR); // this is dataDir option

		// check whether user did input a directory via "--dataDir"
		if (dataDir == null || dataDir.get(0) == null) {
			System.out.println("Error: Directory not specified");
			System.exit(1);
		} else {
		// check whether a directory is created or exist
			File directory = new File(dataDir.get(0));
			if (directory.mkdir() || directory.exists()) {
				// success, directory either exists or is created; no action needed in tbis script
			} else {
				System.out.println("Error: Directory cannot be created");
				System.exit(1);
			}
		}
		// end of Task 1

		SpringApplication.run(ContactsApp.class, args);

	}

}