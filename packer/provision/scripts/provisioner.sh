#!/bin/bash

sudo apt update && sudo apt -y install openjdk-11-jre

# Movemos el script y el jar
sudo mv /tmp/provision/config/practica1.jar /usr/local/bin
sudo mv /tmp/provision/scripts/practica1.sh /usr/local/bin
sudo chmod +x /usr/local/bin/practica1.sh

# Movemos el servicio a systemd
sudo mv /tmp/provision/config/practica1.service /etc/systemd/system/

sudo systemctl daemon-reload
sudo systemctl enable practica1