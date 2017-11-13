package com.vikings.controller;

import com.vikings.domain.Artist;
import com.vikings.domain.Song;
import com.vikings.domain.identifier.AlbumIdentifier;
import com.vikings.domain.identifier.ArtistIdentifier;
import com.vikings.domain.requests.ArtistPageResponse;
import com.vikings.manager.ArtistManager;
import com.vikings.manager.AlbumManager;
import com.vikings.manager.PlaylistManager;
import com.vikings.manager.SongManager;
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
 
 @RequestMapping(method=RequestMethod.GET, value="/Artist/getArtist")
 public @ResponseBody Artist getArtist(@RequestParam("id") String id) {
  return artistManager.getArtist(id);
 }
 
 @RequestMapping(method=RequestMethod.GET, value="/Artist/getArtistPageDetails")
 public @ResponseBody ArtistPageResponse getArtistPageDetails(@RequestParam("id") String id){
  Artist artist = artistManager.getArtist(id);
  if(artist == null)
    return null;
  
  List<Song> topSongs = songManager.getTopSongsForArtist(id);   
  List<ArtistIdentifier> relatedArtists = artistManager.getRelatedArtists(id);
  List<AlbumIdentifier> albums = albumManager.getAlbumsForArtist(id);
  return new ArtistPageResponse(topSongs, relatedArtists, albums);
 }

}
