#!/bin/bash

cd practica1 && mvn install

cd .. && cp ./practica1/target/*.jar ./packer/provision/config/practica1.jar

cd packer && packer validate ami-practicaS3.json && packer build ami-practicaS3.json