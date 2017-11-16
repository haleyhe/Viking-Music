package com.vikings.domain.response;

import com.vikings.domain.Concert;
import java.util.List;


public class ConcertsResponse {
    List<Concert> concerts;
    
    public ConcertsResponse() {
    }
    
    public ConcertsResponse(List<Concert> concerts) {
        this.concerts = concerts;
    }
 
    public List<Concert> getConcerts(){
        return concerts;
    }
 
    public void setConcerts(List<Concert> concerts) {
        this.concerts = concerts;   
    }
}
