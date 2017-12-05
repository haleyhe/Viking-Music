package com.vikings.controller;

import com.vikings.domain.Concert;
import com.vikings.domain.Song;
import com.vikings.domain.identifier.AlbumIdentifier;
import com.vikings.domain.identifier.ArtistIdentifier;
import com.vikings.domain.identifier.PlaylistIdentifier;
import com.vikings.domain.response.ConcertsResponse;
import com.vikings.domain.response.PlaylistsResponse;
import com.vikings.domain.response.RecommendationsResponse;
import com.vikings.domain.response.SearchResponse;
import com.vikings.domain.response.SongsResponse;
import com.vikings.manager.AlbumManager;
import com.vikings.manager.ArtistManager;
import com.vikings.manager.ConcertManager;
import com.vikings.manager.PlaylistManager;
import com.vikings.manager.SongManager;
import com.vikings.manager.UserMusicManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    
    @RequestMapping(method=RequestMethod.GET, value="/Browse/search/songs")
    public @ResponseBody SearchResponse searchSongs(@RequestParam("query") String query,
                                               @RequestParam(value="limit", required=false) Integer limit) {
        SearchResponse searchResults = new SearchResponse();
        if (query.replaceAll("\\s","").length() > 0) {
            List<Song> songs = songManager.search(query, limit);
            searchResults.setSongs(songs);
        }
        return searchResults;
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/Browse/search/albums")
    public @ResponseBody SearchResponse searchAlbums(@RequestParam("query") String query,
                                               @RequestParam(value="limit", required=false) Integer limit) {
        SearchResponse searchResults = new SearchResponse();
        if (query.replaceAll("\\s","").length() > 0) {
            List<AlbumIdentifier> albums = albumManager.search(query, limit);
            searchResults.setAlbums(albums);
        }
        return searchResults;
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/Browse/search/artists")
    public @ResponseBody SearchResponse searchArtists(@RequestParam("query") String query,
                                               @RequestParam(value="limit", required=false) Integer limit) {
        SearchResponse searchResults = new SearchResponse();
        if (query.replaceAll("\\s","").length() > 0) {
            List<ArtistIdentifier> artists = artistManager.search(query, limit);
            searchResults.setArtists(artists);
        }
        return searchResults;
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/Browse/search/playlists")
    public @ResponseBody SearchResponse searchPlaylists(@RequestParam("query") String query,
                                               @RequestParam(value="limit", required=false) Integer limit) {
        SearchResponse searchResults = new SearchResponse();
        if (query.replaceAll("\\s","").length() > 0) {
           List<PlaylistIdentifier> playlists = playlistManager.search(query, limit);
            searchResults.setPlaylists(playlists);
        }
        return searchResults;
    }
    
    /**
     * Returns an ordered list of the most-played songs on the service.
     * @return 
     *  SongsReponse containing a list of songs.
     */
    @RequestMapping(method=RequestMethod.GET, value="/Browse/getTopSongs")
    public @ResponseBody SongsResponse getTopSongs() {
        SongsResponse songResp = new SongsResponse(songManager.getTopSongs());
        HashMap<String,Boolean> saved = userMusicManager.findSavedSongList(songResp.getSongs());
        songResp.setSavedSongs(saved);
        return songResp;
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/Browse/getRecommendedConcerts")
    public @ResponseBody ConcertsResponse getRecommendedConcerts() {
        List<ArtistIdentifier> favoriteArtists = userMusicManager.getFavoriteArtistsForSessionUser();
        if (favoriteArtists != null) {
            if (favoriteArtists.size() > 0) {
                List<Concert> concerts = concertManager.getConcertsForArtists(favoriteArtists);
                return new ConcertsResponse(concerts);
            }
        }
        return new ConcertsResponse();
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/Browse/getRecommendations")
    public @ResponseBody RecommendationsResponse getRecommendations(@RequestParam("numSets") int numSets) {
        List<ArtistIdentifier> favoriteArtists = userMusicManager.getFavoriteArtistsForSessionUser();
        Map<String, List<AlbumIdentifier>> recommendations = new HashMap<>();
        if (favoriteArtists != null) {
            if (favoriteArtists.size() > 0) {
                for (int i = 0; i < numSets && i < favoriteArtists.size(); i++) {
                    ArtistIdentifier artist = favoriteArtists.get(i);
                    List<ArtistIdentifier> related = artistManager.getRelatedArtists(artist.getId());
                    if (related.size() > 0) {
                        List<AlbumIdentifier> recommendedAlbums = albumManager.getAlbumsForArtist(related.get(0).getId());
                        recommendations.put(artist.getName(), recommendedAlbums);
                    }
                }
            }
        }
        return new RecommendationsResponse(recommendations);
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/Browse/getGenrePlaylists")
    public @ResponseBody PlaylistsResponse getAdminCuratedPlaylists() {
        return new PlaylistsResponse(playlistManager.getAdminCuratedPlaylists());
    }
}
