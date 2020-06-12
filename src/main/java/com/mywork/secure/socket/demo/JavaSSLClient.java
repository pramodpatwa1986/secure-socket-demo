package com.mywork.secure.socket.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.KeyStore;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ServerSocketFactory;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import com.sun.net.ssl.KeyManager;

/**
 * @web http://java-buddy.blogspot.com/
 */
public class JavaSSLClient {
    
    static final int port = 8000;

    public static void main(String[] args) {
        
        SSLSocketFactory sslSocketFactory = 
                //(SSLSocketFactory)SSLSocketFactory.getDefault();
        		getClientSocketFactory();
        try {
            Socket socket = sslSocketFactory.createSocket("localhost", port);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            try (BufferedReader bufferedReader = 
                    new BufferedReader(
                            new InputStreamReader(socket.getInputStream()))) {
                Scanner scanner = new Scanner(System.in);
                while(true){
                    System.out.println("Enter something:");
                    String inputLine = scanner.nextLine();
                    if(inputLine.equals("q")){
                        break;
                    }
                    
                    out.println(inputLine);
                    System.out.println(bufferedReader.readLine());
                }
            }
            
        } catch (IOException ex) {
            Logger.getLogger(JavaSSLClient.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
         
    }
    
    private static SSLSocketFactory getClientSocketFactory() {
		SSLSocketFactory ssf = null;
		try {
			// set up key manager to do server authentication
			SSLContext ctx;
			KeyManagerFactory kmf;
			KeyStore ks;
			KeyStore trustStore;
			char[] passphrase = "123456".toCharArray();

			ctx = SSLContext.getInstance("TLS");
			kmf = KeyManagerFactory.getInstance("SunX509");
			ks = KeyStore.getInstance("JKS");

			ks.load(new FileInputStream(
					new File("D:\\project\\eclipse-workspace\\secure-socket-demo\\cert-poc\\client-keystore.jks")), passphrase);
			kmf.init(ks, passphrase);
			
			
			TrustManagerFactory tmFactory = TrustManagerFactory.getInstance("SunX509");
            //Adds the truststore to the factory
			trustStore = KeyStore.getInstance("JKS");

			trustStore.load(new FileInputStream(
					new File("D:\\project\\eclipse-workspace\\secure-socket-demo\\cert-poc\\server.jks")), passphrase);
            tmFactory.init(trustStore);
            //This is passed to the SSLContext init method
            TrustManager[] managers = tmFactory.getTrustManagers();
			
			System.out.println("Keymanagers***** " + kmf.getKeyManagers());
			
			ctx.init(kmf.getKeyManagers(), managers, null);

			ssf = ctx.getSocketFactory();
			
			return ssf;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
}
    
}

