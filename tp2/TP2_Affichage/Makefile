JFLAGS=-g
JC=javac
JVM=java

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES=Main.java Affichage.java Affichage2.java

MAIN=Main

default:classes

classes: $(CLASSES:.java=.class)

run:$(MAIN).class
	$(JVM) $(MAIN)

clean:
	rm *.class
