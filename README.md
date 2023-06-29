# IngSw Project - AY 2022-23 - My Shelfie
<img src="src/main/resources/Title%202000x2000px.png" width=200px height=200 px align="right"/>

## Project Description
This project consists of a Java reproduction of the board game [My Shelfie](https://www.craniocreations.it/prodotto/my-shelfie) by Cranio Creations.

## Development Team
- [Andrea Isgrò](https://github.com/andbna10) (andrea.isgro@mail.polimi.it)
- [Francesco Gangi](https://github.com/fgangi) (francescoantonio.gangi@mail.polimi.it)
- [Simone Grandi](https://github.com/BigSim0) (simone1.grandi@mail.polimi.it)
- [Mirko Gentile](https://github.com/shakkd) (mirko.gentile@mail.polimi.it)

## Implemented Features
| Feature | Status |
|:-----------------------|:------------------------------------:|
| Basic Game Rules |[![GREEN](https://via.placeholder.com/15/00f000/00f000)](#)|
| Complete Game Rules | [![GREEN](https://via.placeholder.com/15/00f000/00f000)](#) |
| Socket | [![GREEN](https://via.placeholder.com/15/00f000/00f000)](#) |
| CLI | [![GREEN](https://via.placeholder.com/15/00f000/00f000)](#) |
| GUI | [![GREEN](https://via.placeholder.com/15/00f000/00f000)](#) |
| Multiple Games | [![GREEN](https://via.placeholder.com/15/00f000/00f000)](#)|

## How to play
In order to play a game, you firstly need to host a server.

Then you can connect as a client (whether it be CLI or GUI) and choose if you want to:
* (1) Create a game (manually create a game with an automatically assigned ID)
* (2) Join a game (manually join a specified lobby using the ID given from the lobby owner)
* (3) ***Play in a random lobby*** (THIS IS THE DEFAULT METHOD, following game requirements)

# How to run JAR file
For this project, we used JDK 19.
In order to run the game, make sure you have both [Java](https://www.java.com/it/download/) and [Java JDK](https://www.oracle.com/java/technologies/downloads/) installed.<br><br>
You also need the jar file. You can get it by clicking [here](https://github.com/andbna10/ing-sw-2023-Gentile-Gangi-Grandi-Isgro/blob/main/deliverables/final/jar/myshelfie.jar).

## Running server 
You can run a server by typing `java -jar myshelfie.jar -server` in the command prompt.

## Running client
The client runs in `1920x1080` resolution.
Open the command prompt and choose:
* CLI initialization: `java -jar myshelfie.jar -cli [ip_address]` in order to start a client using command line interface.

* GUI initialization: `java -jar myshelfie.jar -gui [ip_address]` in order to start a client using graphic user interface.
<br>

As `[ip_address]` you have to enter the IPv4 address from server host ***without square brackets*** (e.g. `java -jar myshelfie.jar -cli 192.168.1.1`).

If you are trying to connect to a server hosting from a different network, you will need the **public** IP address from the host.

If you are trying to connect to a server hosting on a LAN, you will need the **local** IP address from the host.
