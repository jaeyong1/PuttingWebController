echo "-----------------------------"
echo "  JAVA Complier Version (1.8.xxxx)"
echo "-----------------------------"
echo $(javac -version)
echo $(rm bin/ -rf)
echo $(mkdir bin)
echo $(rm release/ -rf)
echo $(mkdir release)
echo $(rm RPI_Client_* -rf)
echo ""
echo "-----------------------------"
echo "  JAVA BUILD (.java)"
echo `pwd`
echo "-----------------------------"
echo "=== java source files ==="
echo $(find -name "*.java")
echo $(find -name "*.java" > "sources.txt")
echo $(javac -d ./bin @sources.txt -cp ./lib/org.eclipse.paho.client.mqttv3_1.1.0.jar:./lib/jython-standalone-2.7.0.jar:.)
echo "=== complied classes ==="
echo $(find ./bin -name "*.class")
echo $(rm sources.txt)
echo ""
echo "-----------------------------"
echo "  Packaging JAR (release/)"
echo "-----------------------------"
echo $(cp lib/org.eclipse.paho.client.mqttv3_1.1.0.jar release/)
echo $(cp lib/jython-standalone-2.7.0.jar release/)
echo $(cp src/*.py release/)
echo $(jar cvfm release/RPi_FieldController.jar Manifest.txt -C bin/ .)
echo ""
echo ""
echo "-----------------------------"
echo "  RUN (release/)"
echo "-----------------------------"
echo "To run.."
echo "java -cp org.eclipse.paho.client.mqttv3_1.1.0.jar:jython-standalone-2.7.0.jar:RPi_FieldController.jar com.jyp.rpi.RPi_FieldController"
echo ""
