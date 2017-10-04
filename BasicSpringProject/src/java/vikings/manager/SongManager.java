package vikings.manager;

import org.springframework.stereotype.Service;
import vikings.domain.Song;

/**
 * This class would contain business logic for actions related to Songs.
 * Each method could have multiple DAO requests.
 * Note that the DAO has been omitted for simplicity.
 *
 * Notice the @Service annotation for gluing everything together with Spring.
 */
@Service
public class SongManager {
    
    public Song getSong(String songId) {
        // You would make a database call using the DAO here, but for now let's
        // just return dummy data
        Song result = new Song();
        if (songId.equals("123")) {
            result.setId("123");
            result.setName("My First Song");
            result.setArtist("Artist One");
        } else if (songId.equals("456")) {
            result.setId("456");
            result.setName("My Second Song");
            result.setArtist("Artist Two");
        }
        
        return result;
    }
    
}
