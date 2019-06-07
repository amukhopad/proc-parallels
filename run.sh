#!/bin/bash

mvn clean package && \
mpirun -np 2 \
java -Djava.library.path=/Users/enginebreaksdown/dev/openmpi/lib:/Users/enginebreaksdown/dev/openmpi/lib \
-cp $HOME/dev/openmpi/lib/mpi.jar:./target/classes \
ua.edu.ukma.amukhopad.parallels.Main
