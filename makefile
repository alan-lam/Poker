#https://www.cs.swarthmore.edu/~newhall/unixhelp/javamakefiles.html

JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	GameManager.java \
	Card.java \
	Table.java \
	Cpu.java \
	Player.java \
	HandRanker.java \
	Deck.java \

default: classes
classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
