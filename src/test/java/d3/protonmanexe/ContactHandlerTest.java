package d3.protonmanexe;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import d3.protonmanexe.controller.Contact;
import d3.protonmanexe.controller.ContactHandler;

@SpringBootTest
class ContactHandlerTest {

	@Test
	void contextLoads() {
	}

	@Test
    public void testsaveContact(Model model) throws IOException {
        // object initialisation
        ApplicationArguments appArgs = new DefaultApplicationArguments("--dataDir=C:\\db\\m2wsd3");
        Contact c = new Contact("Ian Teoh", "a@hotmail.com", 12345678);
        ContactHandler handler = new ContactHandler();
        
        // test
        handler.saveContact(c, model, appArgs);
        // // String id = c.getId();
        // File userFile = new File("C:\\db\\m2wsd3", id + ".txt");
        // // assertTrue(userFile.exists());
        // System.out.println("Test was a success!");
    }

}