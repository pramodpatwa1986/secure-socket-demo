package com.mywork.secure.socket.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyStore;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ServerSocketFactory;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;

/**
 * @web http://java-buddy.blogspot.com/
 */
public class JavaSSLServer {
    
    static final int port = 8000;
    
    
    public static void main(String[] args) {
        
        
        //SSLServerSocketFactory sslServerSocketFactory = 
          //      (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();
    	SSLServerSocketFactory sslServerSocketFactory = 
    			(SSLServerSocketFactory)getServerSocketFactory();    	
        try {
            ServerSocket sslServerSocket = 
                    sslServerSocketFactory.createServerSocket(port);
           // ((SSLServerSocket) sslServerSocket).setNeedClientAuth(true);
            System.out.println("SSL ServerSocket started");
            System.out.println(sslServerSocket.toString());
            
            Socket socket = sslServerSocket.accept();
            System.out.println("ServerSocket accepted");
            
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            try (BufferedReader bufferedReader = 
                    new BufferedReader(
                            new InputStreamReader(socket.getInputStream()))) {
                String line;
                while((line = bufferedReader.readLine()) != null){
                    System.out.println(line);
                    out.println(line);
                }
            }
            
            System.out.println("Closed");
            
        } catch (IOException ex) {
            Logger.getLogger(JavaSSLServer.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
    
    private static ServerSocketFactory getServerSocketFactory() {
			SSLServerSocketFactory ssf = null;
			try {
				// set up key manager to do server authentication
				SSLContext ctx;
				KeyManagerFactory kmf;
				KeyStore ks;
				char[] passphrase = "123456".toCharArray();

				ctx = SSLContext.getInstance("TLS");
				kmf = KeyManagerFactory.getInstance("SunX509");
				ks = KeyStore.getInstance("JKS");

				ks.load(new FileInputStream(
						new File("D:\\project\\eclipse-workspace\\secure-socket-demo\\cert-poc\\server.jks")), passphrase);
				kmf.init(ks, passphrase);
				
				ctx.init(kmf.getKeyManagers(), null, null);

				ssf = ctx.getServerSocketFactory();			
				return ssf;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
	}
}
