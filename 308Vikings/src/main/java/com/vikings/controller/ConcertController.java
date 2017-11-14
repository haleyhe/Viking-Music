package com.vikings.controller;

import com.vikings.domain.Concert;
import com.vikings.domain.response.ConcertPageResponse;
import com.vikings.manager.ConcertManager;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

public class ConcertController {
 @Autowired 
 ConcertManager concertManager;
 
 @RequestMapping(method=RequestMethod.GET, value="/Artist/getArtist")
 public @ResponseBody Concert getConcert(@RequestParam("id") String id) {
  return concertManager.getConcert(id);
 }
 
 @RequestMapping(method=RequestMethod.GET, value="/Concert/getConcertPageDetails")
 public @ResponseBody ConcertPageResponse getArtistPageDetails(@RequestParam("id") String id){
  Concert concert = concertManager.getConcert(id);
  List <Concert> concerts = concertManager.getConcertsForArtist(id);
  if(concert == null)
    return null;

  return new ConcertPageResponse(concerts);
 }
}
