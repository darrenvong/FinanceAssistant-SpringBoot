## Finance Assistant

Initially, this Finance Assistant app was made for the Cloud Computing project (cloudteam6).

### What does the app do?
The core idea of the app is that, given the student's current bank balance together with any other
incomes they may have (from part-time jobs, parental contribution etc), the app - or rather, calculator -
works out how much money they can afford to spend before their next installation of student loan in `x`
weeks time (where `x` is provided by the user). This differs from standard budgeting calculators in that
it asks for the income and outgoings in terms and manners tailored to students; the team believed that
by doing so, it makes the app easier for students to use as they may give these information by simply
reading off their bank statements, leaving the tough number crunching to the app itself.

### The Motivations (for decoupling the app)
I have decided to create this `standalone` branch where the app is decoupled
from the platform, since it doesn't really need the platform to function in the first place,
apart from the fact it was loosely integrated with the platform in order to earn "peanuts"
(currency of the platform for the project). Moreover, the idea behind this app is actually
quite good and useful, and so I thought it'd be cool to make it runnable on its own without
errors caused by the part of the code trying to integrate with a now non-existent peanut bank.

### Building the app
As this has been developed in Eclipse and makes heavy use of the Spring framework,
the easiest way to rebuild this as a runnable *and* deployable war file
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

