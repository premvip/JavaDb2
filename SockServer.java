package client.server;

import java.net.*;
import java.io.*;
//Simple Server that accepts client connections and echoes back what the client typed.
//Client typically invokes StoreProcedures with the Server 
public class SockServer {
    public static void main(String[] args) throws IOException {
         
        if (args.length != 1) {
            System.err.println("Usage: java SockServer <port number>");
            System.exit(1);
        }
 
        int portNumber = Integer.parseInt(args[0]);
 
        try ( 
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();
            PrintWriter out =
                new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        ) {
         
            String inputLine;
             
            System.out.println("Connection from Client");
            out.println("Conection Established!!");
            while ((inputLine = in.readLine()) != null) {
                   out.println("Server : Echo back :" +inputLine);
                  	System.out.println(inputLine);
                    break;
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
