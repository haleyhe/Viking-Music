package com.vikings.domain.response;

import com.vikings.domain.Concert;

public class ConcertPageResponse {
    Concert concert;
    public ConcertPageResponse(){
    }
    
    public ConcertPageResponse(Concert concert){
        this.concert = concert;
    }
    
    public Concert getConcert(){
        return concert;
    }
    
    public void setConcert(Concert concert){
        this.concert = concert;
    }
}
