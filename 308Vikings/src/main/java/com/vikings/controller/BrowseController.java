package com.vikings.controller;

import com.vikings.domain.Concert;
import com.vikings.domain.Song;
import com.vikings.domain.identifier.AlbumIdentifier;
import com.vikings.domain.identifier.ArtistIdentifier;
import com.vikings.domain.identifier.PlaylistIdentifier;
import com.vikings.domain.response.ConcertsResponse;
import com.vikings.domain.response.SearchResponse;
import com.vikings.domain.response.SongsResponse;
import com.vikings.manager.AlbumManager;
import com.vikings.manager.ArtistManager;
import com.vikings.manager.ConcertManager;
import com.vikings.manager.PlaylistManager;
import com.vikings.manager.SongManager;
import com.vikings.manager.UserMusicManager;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller for Browse Page and general browsing-related actions,
 * such as searching.
 */
@Controller
public class BrowseController {
    
    @Autowired
    SongManager songManager;
    
    @Autowired
    AlbumManager albumManager;
    
    @Autowired
    ArtistManager artistManager;
    
    @Autowired
    PlaylistManager playlistManager;
    
    @Autowired
    UserMusicManager userMusicManager;
    
    @Autowired
    ConcertManager concertManager;
    
    /**
     * Returns search results for the given query.
     * @param query
     *  Search query.
     * @param limit
     *  Number of results to return per type (optional).
     * @return 
     *  Response object containing songs, albums, artists, and playlists that match
     *  the given query.
     */
    @RequestMapping(method=RequestMethod.GET, value="/Browse/search")
    public @ResponseBody SearchResponse search(@RequestParam("query") String query,
                                               @RequestParam(value="limit", required=false) Integer limit) {
        if (query.replaceAll("\\s","").length() > 0) {
            List<Song> songs = songManager.search(query, limit);
            List<AlbumIdentifier> albums = albumManager.search(query, limit);
            List<ArtistIdentifier> artists = artistManager.search(query, limit);
            List<PlaylistIdentifier> playlists = playlistManager.search(query, limit);
            return new SearchResponse(songs, albums, artists, playlists);
        } else {
            return new SearchResponse();
        }
    }
    
    /**
     * Returns an ordered list of the most-played songs on the service.
     * @return 
     *  SongsReponse containing a list of songs.
     */
    @RequestMapping(method=RequestMethod.GET, value="/Browse/getTopSongs")
    public @ResponseBody SongsResponse getTopSongs() {
        return new SongsResponse(songManager.getTopSongs());
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/Browse/getRecommendedConcerts")
    public @ResponseBody ConcertsResponse getRecommendedConcerts() {
        Set<ArtistIdentifier> favoriteArtists = userMusicManager.getFavoriteArtistsForSessionUser();
        if (favoriteArtists != null) {
            if (favoriteArtists.size() > 0) {
                List<Concert> concerts = concertManager.getConcertsForArtists(favoriteArtists);
                return new ConcertsResponse(concerts);
            }
        }
        return new ConcertsResponse();
    }
}
