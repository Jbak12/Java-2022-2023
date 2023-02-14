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
            int count = 0;
            int result = 0;
            String lookingFor = "";

            while ((sLine = in.readLine()) != null ) {
                if(sLine.equals("EOD")) {
                    break;
                }
                System.out.println(Integer.toString(count) + " " + sLine);
                sLine = sLine.replaceAll("[^0-9]", "");
                if(count == 3) {
                    lookingFor = sLine;
                }
                if (count > 3){
                    result = lookingFor.equals(sLine) ? result + 1 : result;
                }
                count = count + 1;
            }
            out.println(result);
            // // System.out.println("Znaleziono " + result + " liczby " + lookingFor);

            // while((sLine = in.readLine()) != null){
            //     System.out.println("PO PROGRAMIE: " + sLine);
            // }

            in.close();
            out.close();
            socket.close();


        } catch(UnknownHostException ex){
            System.out.println("Server not found: " + ex.getMessage());
        }catch(IOException ex){
            System.out.println("I/O error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        // System.out.println("siema siema");
        Connector connector = new Connector();
        connector.connect("172.30.24.101", 8080);
    }
}