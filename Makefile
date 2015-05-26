COMPILER = javac
# CCFLAGS  = -ansi -pedantic -Wall
FLAGS  = -classpath acm.jar:.

.PHONY: clean play

all: Breakout.class

Breakout.class: Ball.java Block.java Breakout.java Commons.java Paddle.java
	$(COMPILER) $(FLAGS) Breakout.java

play: Breakout.class
	java $(FLAGS) Breakout

clean:
	rm -f *.class
