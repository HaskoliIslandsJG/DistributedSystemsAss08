Github repo : https://github.com/jeje42/HI-Distributed-Systems.git

Both cases : cd into the bin directory.

1. Start server using (adjust classpath parameter -cp to let it point to your directory that contains the ds_04 subdirectory that contain the *.class files)
java -cp ./ -Djava.rmi.server.codebase=file:///./ -Djava.security.policy=../policy.txt Server portNumber

2. Start on same machine client using (adjust classpath)
java -cp ./  -Djava.security.policy=../policy.txt ClientSample localhost


### Answer for part 2 :	###

If no server is running, we can see the client does not react.
This is what was expected using a UDP-based communication where the communication only takes place from the sender to the receiver.
The sender does not receives any confirmation whatever the message has been received or not.

