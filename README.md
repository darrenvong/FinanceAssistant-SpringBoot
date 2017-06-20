## Finance Assistant

Initially, this Finance Assistant app was made for the Cloud Computing project (cloudteam6).

### The Motivations
I have decided to create this `standalone` branch where the app is decoupled
from the platform, since it doesn't really need the platform to function in the first place,
apart from the fact it was loosely integrated with the platform in order to earn "peanuts"
(currency of the platform for the project). Moreover, the idea behind this app is actually
quite good and useful, and so I thought it'd be cool to make it runnable on its own without
those random errors due to integrating with the peanut bank of a platform that doesn't exist anymore in this context.

### Building the app
As this has been developed in Eclipse and makes heavy use of the Spring framework,
the easiest way to rebuild this as a runnable and deployable war file
(assuming you have Java installed) is:
* First, install the Spring Tool Suite (STS) plugin for Eclipse
* Once you have STS, restart Eclipse for it to work
* Import this repository as an existing Maven project
* Build the war using the integrated Maven tools in Eclipse by right clicking project, then
select **Run As** > **Maven build...**
* Type in "clean package" as the build **goals** in that field, then click OK to build!

### Running the app
Since the built app comes with all dependencies (with a Tomcat server embedded!), you can simply run it on the command line by typing `java -jar FinanceAssistant-2.0.0.war`
(or whatever you named your war file). Wait for the embedded Tomcat server to fire up,
and voila, you can access the app by typing `localhost:8080` in your browser!

