Github repo : https://github.com/HaskoliIslandsJG/DistributedSystemsAss08.git

Both cases : cd into the bin directory.

Server : launch rmiregistry

1. Start server using (adjust classpath parameter -cp to let it point to your directory that contains the ds_04 subdirectory that contain the *.class files)
java -cp ./ -Djava.rmi.server.codebase=file:///./ -Djava.security.policy=../policy.txt Server portNumber

2. Start on same machine client using (adjust classpath)

	//For the client sample 
	java -cp ./  -Djava.security.policy=../policy.txt ClientSample localhost
	
	//For the UDP client (with timeout of 3 seconds and 3 tries)
	java -cp ./  -Djava.security.policy=../policy.txt Client localhost portNumber
