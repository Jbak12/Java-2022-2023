import java.io.*;
import java.net.*;

public class Connector implements NetConnection {

    @Override
    public void connect(String host, int port) {
        try(Socket socket = new Socket(host, port);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));){
            out.println("Program");
            String sLine;
            int count = 0, result = 0;
            String lookingFor = "";

            while ((sLine = in.readLine()) != null ) {
                if(sLine.equals("EOD")) {
                    break;
                }
                sLine = sLine.replaceAll("[^0-9]", "");
                if(count == 3) {
                    lookingFor = sLine;
                } else if (count > 3){
                    result = lookingFor.equals(sLine) ? result + 1 : result;
                }
                count = count + 1;
            }
            out.println(result);
            in.close();
            out.close();
            socket.close();

        } catch(UnknownHostException ex){
            System.out.println("Couldnt find server: " + ex.getMessage());
        } catch ( ConnectException exc ) {
            System.out.println( "Couldnt connect to " + host );
        } catch(IOException ex){
            System.out.println("i/o stream error: " + ex.getMessage());
        }
    }

    // public static void main(String[] args) {
    //     // System.out.println("haha serwer");
    //     Connector connector = new Connector();
    //     connector.connect("172.30.24.101", 8080);
    // }
}