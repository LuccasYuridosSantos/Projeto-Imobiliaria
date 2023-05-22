package br.gov.sp.fatec.pi.imobiliaria.exception;

public class CustomErrorResponse {
    private String error;
    private String message;

   public CustomErrorResponse(final String error, final String message){
    this.error = error;
    this.message = message;
   }

   public void setError(String error) {
       this.error = error;
   }

   public String getError() {
       return error;
   }

   public String getMessage() {
       return message;
   }

   public void setMessage(String message) {
       this.message = message;
   }
}