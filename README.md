jwsur2
======

Java Web Services: Up and Running, 2e

For convenience, the sample code from the book is divided into chapters, with one ZIP file per chapter. Each ZIP contains 
a Readme document with an overview of the contents. Per ZIP file there is an Ant script to deploy sample services to Tomcat or 
an equivalent web server such as Jetty; a deployed WAR file, which includes source code for convenience, also is provided 
for most of the services. On the client side, most of the code examples either are in self-contained, 
executable JAR files or have an Ant script to build and execute. 

The Ant script for deploying services (the file is build.xml) requires minor editing: one line needs to be changed
to specify the install directory for your Tomcat, Jetty, or equivalent server. The script itself includes 
documentation about what needs to be changed.

Best of luck with the code. Make it better.

mgk

______
A "Mavenized" version of the code exists on the branch 'jwsur2-maven'. It started as a contribution from a reader of the book and is not actively maintained by Martin Kalin. Although very similar, it is not an exact replica of the main branch. The code is a work-in-progress and is solely meant for educational purposes; the author of the code assumes no responsibility for it's correctness, completeness or quality. The same usage agreements apply to the Maven branch as the main one.

<dl>
  <dt>System Requirements for the Maven branch:</dt>
  <dd>Java 1.7.0 or above</dd>
  <dd>Maven 3.0 or above</dd>
</dl>

The Maven branch uses embedded Jetty for deployment.
