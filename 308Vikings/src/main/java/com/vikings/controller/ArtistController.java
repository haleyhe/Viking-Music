package com.vikings.controller;

import com.vikings.domain.Artist;
import com.vikings.domain.Concert;
import com.vikings.domain.Song;
import com.vikings.domain.identifier.AlbumIdentifier;
import com.vikings.domain.identifier.ArtistIdentifier;
import com.vikings.domain.response.ArtistPageResponse;
import com.vikings.domain.response.ArtistsResponse;
import com.vikings.manager.ArtistManager;
import com.vikings.manager.AlbumManager;
import com.vikings.manager.ConcertManager;
import com.vikings.manager.PlaylistManager;
import com.vikings.manager.SongManager;
import com.vikings.manager.UserMusicManager;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ArtistController {
    @Autowired
    ArtistManager artistManager;
    
    @Autowired
    AlbumManager albumManager;
    
    @Autowired
    PlaylistManager playlistManager;
    
    @Autowired
    SongManager songManager;
    
    @Autowired
    ConcertManager concertManager;
    
    @Autowired
    UserMusicManager userMusicManager;
    
    @RequestMapping(method=RequestMethod.GET, value="/Artist/getArtist")
    public @ResponseBody Artist getArtist(@RequestParam("id") String id) {
        return artistManager.getArtist(id);
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/Artist/getAllArtists")
    public @ResponseBody ArtistsResponse getAllArtists() {
        return new ArtistsResponse(artistManager.getAllArtists());
    }
 
    @RequestMapping(method=RequestMethod.GET, value="/Artist/getArtistPageDetails")
    public @ResponseBody ArtistPageResponse getArtistPageDetails(@RequestParam("id") String id) {
        Artist artist = artistManager.getArtist(id);
        if(artist == null) {
            return null;
        }
        String name = artist.getName();
        String bio = artist.getBio();
        List<Song> topSongs = songManager.getTopSongsForArtist(id);   
        boolean isFollowing = userMusicManager.isFollowingArtist(artist.toArtistIdentifier());
        List<ArtistIdentifier> relatedArtists = artistManager.getRelatedArtists(id);
        List<AlbumIdentifier> albums = albumManager.getAlbumsForArtist(id);
        List<Concert> concerts = concertManager.getConcertsForArtist(id);
        return new ArtistPageResponse(id, name, bio, isFollowing, topSongs, relatedArtists, albums, concerts);
    }
    
}
