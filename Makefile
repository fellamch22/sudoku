.PHONY: all datamodel clean

all : datamodel 

datamodel :
	cd datamodel && mvn install &&  java -jar ./target/UE_PROJET_SUDOKU.jar

run :

	java -jar UE_PROJET_SUDOKU.jar


clean :
	cd datamodel && mvn clean
