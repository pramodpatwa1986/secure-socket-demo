# secure-socket-demo
## execution
* set JAVA/bin and Maven bin in PATH vatiable through command line.
* mvn clean install 
* cd <project_root>/secure-socket-demo/target
*  java -cp secure-socket-demo-0.0.1-SNAPSHOT.jar -Djavax.net.ssl.keyStore=<rootpath>/secure-socket-demo/cert-poc/server.jks -Djavax.net.ssl.keyStorePassword=123456 com.mywork.secure.socket.demo.JavaSSLServer
*  java -cp secure-socket-demo-0.0.1-SNAPSHOT.jar -Djavax.net.ssl.trustStore=<rootpath>/secure-socket-demo/cert-poc/server.jks -Djavax.net.ssl.trustStorePassword=123456 com.mywork.secure.socket.demo.JavaSSLClient


References:
https://medium.com/@kevinsimper/how-to-self-sign-ssl-certificate-with-ca-c8a795b6ed46 
https://medium.com/@JavaArchitect/configure-ssl-certificate-with-spring-boot-b707a6055f3 
https://www.makethenmakeinstall.com/2014/05/ssl-client-authentication-step-by-step/


https://stackoverflow.com/questions/13578134/how-to-automate-keystore-generation-using-the-java-keystore-tool-w-o-user-inter
https://gist.github.com/vivekkr12/c74f7ee08593a8c606ed96f4b62a208a
