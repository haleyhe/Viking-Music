package com.vikings.domain.response;

import com.vikings.domain.identifier.AlbumIdentifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RecommendationsResponse {
    Map<String, List<AlbumIdentifier>> recommendations;
    
    public RecommendationsResponse() {
        recommendations = new HashMap<>();
    }
    
    public RecommendationsResponse(Map<String, List<AlbumIdentifier>> recommendations) {
        this.recommendations = recommendations;
    }

    public Map<String, List<AlbumIdentifier>> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(Map<String, List<AlbumIdentifier>> recommendations) {
        this.recommendations = recommendations;
    }
}
