COMPILER = javac
# CCFLAGS  = -ansi -pedantic -Wall
FLAGS  = -classpath acm.jar:.

all: Breakout

Breakout:
	$(COMPILER) $(FLAGS) Breakout.java

clean:
	rm -f *.class
