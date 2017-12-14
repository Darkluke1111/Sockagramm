package client;

public class BadResponseException extends Exception {
  int status;

  public BadResponseException(String msg, int status) {
    super(msg);
    this.status = status;
  }

  public BadResponseException(int status) {
    this.status = status;
  }
}
