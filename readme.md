#  Table of Contents
1. [General Info](#general-info)
2. [Requirements](#requirements)
3. [Screenshots](#screenshots)
4. [Technologies](#technologies)
5. [Installation](#installation)
6. [How to use?](#how-to-use)
# General Information

Conway's Game of Life is a simple zero-player game that modifies the grid every second under the programmed initial configuration of the game./
User can choose shapes among beehive, blinker, block, glider, toad, and customized shape.

# Requirements

IntelliJ IDEA is required for building the game.\

# Screenshots

This is a screen when user runs the game. \
![start view][ImageSources/readme1]

Whenever user clicks the cell, the game is started. At the bottom-left, it shows x-y coordinate of user selected, and at the bottom-right, it shows the number of frame changed(which is basically seconds after starting game). \ 
![game][ImageSources/readme2]

# Technologies
* Java
* JavaFX 11.0.2


# Installation

$ git clone

1. Open Intellij IDEA, and open workspace of GameOfLife.
2. Add new Gradle configuration.
3. Build Gradle
4. Click Gradle button at the very top-right, and under Tasks/Application, click run. 

# How to use?

Choose a shape that you want to put in the grid at the status bar, and click the cell at the grid. \
First five shapes will be changed immediately after you click the grid; however, the customize button causes pause the game until you click the other buttons at the status bar. This means the game would restart after choosing other buttons and selecting the grid.  \
At the bottom, there are two features: at the bottom-left, it shows x-y coordinate and name of shape that user selected, and at the bottom-right, it shows the number of frame changed(which is basically seconds after starting game). \
Observation the modifying of grid and the features.


