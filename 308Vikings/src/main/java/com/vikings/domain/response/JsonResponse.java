/*
 * Generic JSON for a HTTP Response
 */
package com.vikings.domain.response;

public class JsonResponse {
    private boolean success;
    private String error;
    
    public JsonResponse() {
        
    }
    
    public JsonResponse(boolean success) {
        this.success = success;
    }
    
    public JsonResponse(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
    
}
