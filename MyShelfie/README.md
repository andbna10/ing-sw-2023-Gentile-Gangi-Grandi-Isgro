# IngSw Project - AY 2022-23 - MyShelfie
<img src="https://github.com/andbna10/ing-sw-2023-Gentile-Gangi-Grandi-Isgro/blob/main/MyShelfie/src/main/resources/Title%202000x2000px.png" width=200px height=200 px align="right" />

## Project Description
This project consists of a Java reproduction of the board game [MyShelfie](https://www.craniocreations.it/prodotto/my-shelfie) by Cranio Creations.

## Development Team
- [Andrea Isgrò](https://github.com/andbna10) (andrea.isgro@mail.polimi.it)
- [Francesco Gangi](https://github.com/fgangi) (francescoantonio.gangi@mail.polimi.it)
- [Simone Grandi](https://github.com/BigSim0) (simone1.grandi@mail.polimi.it)
- [Mirko Gentile](https://github.com/shakkd) (mirko.gentile@mail.polimi.it)

## How to play
In order to play a game, you firstly need to start the server.

Then you can connect as a client (whether is cli or gui) and choose if you want to:
* (1) Create a game
* (2) Join a game
* (3) Play in a random lobby

# How to run
For this project, we used JDK 19.
In order to run the game, make sure you have both [Java](https://www.java.com/it/download/) and [Java JDK](https://www.oracle.com/java/technologies/downloads/) installed.<br><br>
You also need the jar file. You can get it by clicking [here](https://github.com/andbna10/ing-sw-2023-Gentile-Gangi-Grandi-Isgro/blob/main/deliverables/final/jar/myshelfie.jar).

## Running server 
You can run a server by typing `java -jar myshelfie.jar -server` in the command prompt.

## Running client
Open the command prompt and choose:
* CLI initialization: `java -jar myshelfie.jar -cli [ip_address]` in order to start a client using command line interface.

* GUI initialization: `java -jar myshelfie.jar -gui [ip_address]` in order to start a client using graphic user interface.

In `[ip_address` you obviously have to enter the IPv4 from the server host.
