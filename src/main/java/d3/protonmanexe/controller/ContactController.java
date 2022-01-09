package d3.protonmanexe.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactController {

    @Autowired // to access args which is CLI variable
    private ApplicationArguments appArgs;

    // GET method to return addressbook.html when requested
    @GetMapping("/")
    public String showForm (Model model) {
        model.addAttribute("addressbookobj", new Contact()); // link object "Contact" to html
        return "addressbook"; 
    }

    // start of Task 4
    // GET method to 
    @GetMapping("/contact/{id}")
    public String getContact (Model model, @PathVariable(value="id") String id) {
        ContactHandler handler = new ContactHandler();
        try {
            handler.getContactById(id, model, appArgs);
            return "addressbookresult"; 
        } catch (Exception e) {
            model.addAttribute("msg", "404 - Not found");
            return "contactstatus"; 
        }
    }
    // end of Task 4

    // start of Task 3
    // POST method to 
    @PostMapping("/contact")
    public String getForm (@ModelAttribute Contact c, Model model, HttpServletResponse response) {
        ContactHandler handler = new ContactHandler();
        try {
            handler.saveContact(c, model, appArgs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("msg", response.getStatus() + " - Particulars saved successfully");
        return "contactstatus";
    }
    // end of Task 3

}
