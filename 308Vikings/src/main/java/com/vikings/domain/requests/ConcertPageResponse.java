
package com.vikings.domain.requests;

import com.vikings.domain.Concert;
import java.util.List;


public class ConcertPageResponse {
 List<Concert> concerts;
 public ConcertPageResponse(){
 }
 
 public ConcertPageResponse(List<Concert> concerts){
  this.concerts = concerts;
 }
 
 public List<Concert> getConcerts(){
  return concerts;
 }
 
 public void setConcerts(List<Concert> concerts){
  this.concerts = concerts;
 }
}
