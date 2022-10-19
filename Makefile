
default:
	mvn clean package
	mvn -q exec:java

c:
	mvn clean package

r:
	mvn -q exec:java


