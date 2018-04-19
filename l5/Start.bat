cd Task5
mkdir bin
cd src
javac -d ../bin com/epam/tat/main/*.java
cd ../bin
jar cfvm main.jar ../src/META-INF/MANIFEST.MF .
java -jar main.jar triangle:3 circle:6 square:4 rectangle:5:6 square:16 triangle:5
pause