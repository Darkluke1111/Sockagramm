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

public class Client {

  public static void main(String[] args) throws IOException, BadResponseException {
    Client c = new Client();

    File f = new File("test.jpg");
    System.out.println(f.getCanonicalPath());
    c.sendRequest(InetAddress.getByName("134.60.77.151"),7777,(byte) 0,f);
  }


  public void sendRequest(InetAddress address, int port, byte filter, File imageFile) throws BadResponseException {
    try (
        Socket socket = new Socket(address, port);
        OutputStream out = socket.getOutputStream();
        InputStream in = socket.getInputStream();
        FileInputStream fi = new FileInputStream(imageFile)
    ) {

      //send the filter
      out.write(filter);

      //send the file length
      System.out.println(imageFile.length());
      out.write(ByteBuffer.allocate(4).putInt((int)imageFile.length()).array());

      //send the file
      {
        int total = 0;
        int count;
        byte[] buffer = new byte[1024];
        while ((count = fi.read(buffer)) > 0) {
          total += count;
          System.out.println(total);
          out.write(buffer, 0, count);
        }
      }

      out.flush();

      //read response status
      int status = in.read();

      //throw Exception if there was an error
      if (status != 0) {
        throw new BadResponseException();
      }

      //read response file length
      byte[] b = new byte[4];
      in.read(b);
      ByteBuffer bb = ByteBuffer.wrap(b);
      int responseLength = bb.getInt();

      //read file
      File responseFile = new File("converted.jpg");
      responseFile.createNewFile();
      try(FileOutputStream fo = new FileOutputStream(responseFile)) {
        int count;
        byte[] buffer = new byte[1024];
        while ((count = in.read(buffer)) > 0) {
          fo.write(buffer, 0, count);
        }
        fo.flush();
      }


    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
