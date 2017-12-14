package client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

public class WebClient {

  public static final String SERVER_IP = "134.60.77.151";
  public static final int SERVER_PORT = 7777;

  InetAddress address;
  int port;


  public WebClient(InetAddress address, int port) {
    this.address = address;
    this.port = port;
  }

  public WebClient(String ip, int port) throws UnknownHostException {
    this.address = InetAddress.getByName(ip);
    this.port = port;
  }

  public static void main(String[] args) throws IOException, BadResponseException {
    WebClient c = new WebClient(InetAddress.getByName("134.60.77.151"), 7777);

    File f = new File("test.jpg");
    File f2 = new File("converted.jpg");
    System.out.println(f.getCanonicalPath());
    c.sendRequest((byte) 1, f, f2);
  }


  public void sendRequest(byte filter, File imageFile, File targetFile) throws BadResponseException {
    try (
        Socket socket = new Socket(address, port);
        OutputStream out = socket.getOutputStream();
        InputStream in = socket.getInputStream();
        FileInputStream fi = new FileInputStream(imageFile);
        FileOutputStream fo = new FileOutputStream(targetFile)
    ) {

      //Send Request /////////////////////
      //send the filter
      out.write(filter);

      //send the file length
      System.out.println(imageFile.length());
      out.write(ByteBuffer.allocate(4).putInt((int) imageFile.length()).array());

      //send the file
      connectStreams(fi,out);

      // Read Response ///////////////////
      //read response status
      int status = in.read();

      //throw Exception if there was an error
      if (status != 0) {
        throw new BadResponseException("An Error occured! Error Code: " + status);
      }

      //read response file length
      byte[] b = new byte[4];
      in.read(b);
      ByteBuffer bb = ByteBuffer.wrap(b);
      int responseLength = bb.getInt();

      //save received file file
      connectStreams(in,fo);


    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void connectStreams(InputStream in, OutputStream out) throws IOException {
    int count;
    byte[] buffer = new byte[1024];
    while ((count = in.read(buffer)) > 0) {
      out.write(buffer, 0, count);
    }
    out.flush();
  }
}
