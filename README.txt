							
			   FAUTEBALL WORLD CUP SIMULATOR

What is it?
-----------

	A software designed to manage the teams and simulate the Fauteball World 
Cup 2018, as a better alternative to the previous software proposed by the SIPolytech
SARL students. This solution lacked an object-oriented approach and because of this,
code maintenability was almost impossible. Also, lots of hardcoded values turned
it into a very adhoc solution, which makes it useless for managing different 
matches and groups.
	We propose an object-oriented and easy to maintain software, with support
for data reading from a plaintext file, which makes the task of data management far
easier and a simulation system that is simples, yet it can produce reasonable outcomes
for the championships.


How do I run it?
----------------

	Extract the files with: 

		jar xf WorldCupSimulator.jar

	This will put all .java files in the current directory. Compile it with:

		javac WorldCup.java

	This will generate a set of .class files in the same directory. After this,
you can run the stuff with:

		java WorldCup testdata.fp 2 -v

	Which will return a match with all the scores of all matches after reading the
teams properties from testdata.fp.
	A brief description of the parameters:

		java WorldCup <Filepath> <NSecondStage> [-v]

	<Filepath>: path to the file with teams and groups descriptions. See below how
		    structure such file.

	<NSecondStage>: The number of teams to pass to the second stage. Must be a positive
			integer N - we choose, after each group stage, the N best teams
			in the stage.

	-v: If it's set, prints the score of each match at each minute (= each timestep).



How do I create a textfile with teams informations?
---------------------------------------------------

	It's not that hard: inside a GROUP there are TEAMS, inside TEAMS there are DOCTOR, 
MANAGER, STRENGTH, PLAYER and GOALKEEPER. Close the teams with ENDTEAM and the groups with
ENDGROUP, ident as you like and it's ready to use. 
	The parameters available to set informations are:

	-name <familyName: String>
	-fname <firstName: String>
	-id <identificator, can ben anything: String>
	-dbirt <day of birth: positive integer>
	-mbirt <month of birth: positive integer>
	-ybirt <year of birth: positive integer>
	-pnum <phone number, can be in any format: string>
	-number <for player's only. Shirt's number: integer>
	-starter <for player's only. Set's whether a player is starter or not: 1 if it is, 0 if it's not>

	Some remarks:

1) After the tags GROUP and TEAM, we must put it's name;
2) Tags order are irrelevant;
3) The parameter ID is the only obligatory one. If omitted, other parameters will be set to default values;
4) GOALKEEPER receives as parameter the ID of one of the previous defined players. It means that
   GOALKEEPER tag must ALWAYS come after we set all the players.
5) STRENGTH receives a positive integer value as parameter. The larger its value is, the smaller is the
   change of a team making a goal. After some tests, we noticed that values between 50 and 150 gives us 
   reasonable scores.

	Take a look at this template, then look at testdata.fp for a real example:

GROUP #groupname
	TEAM #team_A
       		DOCTOR -name #name -fname #first_name -id #id -dbirt #dob -mbirt #mob -ybirt #yob -pnum #phone_number
       		MANAGER -name #name -fname #first_name -id #id -dbirt #dob -mbirt #mob -ybirt #yob -pnum #phone_number
       		STRENGTH #nStrength
       		PLAYER -name #name -fname #first_name -id #id -number #n -starter #value -dbirt #dob -mbirt #mob -ybirt #yob -pnum #phone_number
       		PLAYER -name #name -fname #first_name -id #id -number #n -starter #value -dbirt #dob -mbirt #mob -ybirt #yob -pnum #phone_number
       		PLAYER -name #name -fname #first_name -id #id -number #n -starter #value -dbirt #dob -mbirt #mob -ybirt #yob -pnum #phone_number
       		PLAYER -name #name -fname #first_name -id #id -number #n -starter #value -dbirt #dob -mbirt #mob -ybirt #yob -pnum #phone_number
       		GOALKEEPER #id
	ENDTEAM
ENDGROUP
GROUP #groupname
	TEAM #team_B
	       DOCTOR -name #name -fname #first_name -id #id -dbirt #dob -mbirt #mob -ybirt #yob -pnum #phone_number
	       MANAGER -name #name -fname #first_name -id #id -dbirt #dob -mbirt #mob -ybirt #yob -pnum #phone_number
	       STRENGTH #nStrength
	       PLAYER -name #name -fname #first_name -id #id -number #n -starter #value -dbirt #dob -mbirt #mob -ybirt #yob -pnum #phone_number
	       PLAYER -name #name -fname #first_name -id #id -number #n -starter #value -dbirt #dob -mbirt #mob -ybirt #yob -pnum #phone_number
	       PLAYER -name #name -fname #first_name -id #id -number #n -starter #value -dbirt #dob -mbirt #mob -ybirt #yob -pnum #phone_number
	       PLAYER -name #name -fname #first_name -id #id -number #n -starter #value -dbirt #dob -mbirt #mob -ybirt #yob -pnum #phone_number
	       GOALKEEPER #id
	ENDTEAM
ENDGROUP



Who is this code's author?
--------------------------
	
	GOUVEIA ROCHA Luís Cláudio, third-year Science Informatique student in Polytech Nice-Sophia. One can
contact me at luisclaudiogouveiarocha@gmail.com.

