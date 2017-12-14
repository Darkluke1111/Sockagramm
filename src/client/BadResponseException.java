package client;

public class BadResponseException extends Exception {

  public BadResponseException(String msg) {
    super(msg);
  }

  public BadResponseException() {

  }
}
