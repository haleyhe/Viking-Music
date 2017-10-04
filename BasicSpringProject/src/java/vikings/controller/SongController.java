package vikings.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vikings.domain.Song;
import vikings.manager.SongManager;

/**
 * The Controller class takes in a page request, makes the necessary
 * service calls, and populates the Model with the dynamic data.
 * 
 * This gets returned to the Dispatcher Servlet that made the call.
 * Then some magic happens and the result page View is generated
 * with the given Model data.
 */
@Controller
public class SongController {
    
    // The Controller can make calls to the Service class for data.
    // Note the @Autowired annotation, so Spring handles the dependency
    @Autowired
    SongManager songManager;
    
    // The @RequestMapping annotation indicates that any request
    // for a page "/song/{id}", where id is a variable, should be
    // mapped to this handler.
    @RequestMapping(value="/song/{id}")
    public String getSongPage(@PathVariable("id") String id, ModelMap model) {
        // Make the service call for the request
        Song song = songManager.getSong(id);
        
        // Add the result to the model
        model.addAttribute("song", song);
        
        // The return value is the name of the jsp we want the View Resolver
        // to retrieve. There are some other options for how to do this, and
        // other possible return types (like ModelAndView) for a controller method.
        return "song";
    }
    
}
