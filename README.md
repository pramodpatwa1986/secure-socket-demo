# secure-socket-demo
## execution
* set JAVA/bin and Maven bin in PATH vatiable through command line.
* mvn clean install 
* cd <project_root>/secure-socket-demo/target
*  java -cp secure-socket-demo-0.0.1-SNAPSHOT.jar -Djavax.net.ssl.keyStore=<rootpath>/secure-socket-demo/cert-poc/server.jks -Djavax.net.ssl.keyStorePassword=123456 com.mywork.secure.socket.demo.JavaSSLServer
*  java -cp secure-socket-demo-0.0.1-SNAPSHOT.jar -Djavax.net.ssl.trustStore=<rootpath>/secure-socket-demo/cert-poc/server.jks -Djavax.net.ssl.trustStorePassword=123456 com.mywork.secure.socket.demo.JavaSSLClient
