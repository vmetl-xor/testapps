use 
mvn clean install 
to build the project

then use in target/

java -jar wcounter.jar <fileName> <topN>

to count word frequencies in the given file


=================================================
comments:
I've found small inconsistency in the requirements: the requirements text states 'words are separated by space', which makes 'bells,' != 'bells'. However, the latter shoud be true, as can visible from expected output (bells has 2 counts).
In the solution, comma + space is considered to be delimeter as well.