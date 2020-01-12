This project contains the code for the technical problem given by Intercom.
The distance is calculated using the haversine formula. 
The program is coded using Java with Maven on Eclipse.
Requirements : Java, Maven, Eclipse.

How to install Maven on Eclipse:
1.Open your Eclipse IDE and click Help -> Install New Software…
2.On the opened pop-up, click on the Add button to add a new repository
3.Fill the form with the information below and press Ok
  Name : M2Eclipse
  Location : http://download.eclipse.org/technology/m2e/releases
4.After the Pending finish, select all the Plugins and press Next >
5.Accept the terms of the license agreement and click Finish
6.At the end of the installation, you will be asked to restart your Eclipse. Click Yes to perform the restart.
Apache Maven is now integrated to your IDE.

How to run the code:
1.In eclipse, click on File -> Open Projects from File System and select the IntercomTest directory.
2.The structure is as follows: IntercomTest->src->main->java
3.There are two java files, MainClass and UserObject. 
4.pom.xml, customers.txt and output.txt are in the root directory.
5.In MainClass, there is a method called readTextFile(). Set the path to where customers.txt is saved.
6.MainClass contains the main() method.
7.Run the program.
8.The name and user id of customers within 100km is stored in output.txt. If output.txt doesn't exist it is created. 
  Otherwise, it is overwritten.

Coding Decisions:
1. In readTextFile method, created a variable for path so that it can be easily edited.
2. Maven is used to import json-simple which is used to parse the JSON objects.
3. UserObject is created to store values of successful customers. Even though, latitude and longitude are
   not required to be stored in the ArrayList, it is being stored in case it may be required later on.
4. UserObject has setters and getters. Setters are not being used yet and are created in case it may be required later on.
5. The latitude and longitude of the office are declared final because they are constants and wont change.
6. In distanceFromOffice method, for Haversine's formula, the radius(r) is 6371 because radius of the earth is 6371 km.