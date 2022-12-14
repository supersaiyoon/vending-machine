# Team Five GUIs Vending Machine
# Group project for CSC 131
# Aaron Soliz, Brennan Moran, Brian Yoon, Sean Agarrado, Subhan Mehmood

This is the vending machine software created by our group, Team Five GUIs. The goal was to design, develop, and deliver a software product that upgraded the current capabilities of the customer's vending machines. The customer also requested a stand-alone data management tool that enabled the customer to view sale data and other information in real time. The vending machine and data management tool programs were designed to have the capability to run simultaneously.

## Installation

  * Java Development Kit 19 (JDK 19) must be installed on a Windows PC.

  * Please visit Oracle's website to download JDK 19 if it's not installed on your PC:
    https://www.oracle.com/java/technologies/downloads/#jdk19-windows

## Known Bugs and Disclaimers

  * "Thank you" message pops up after customer inputs invalid slot number.
  
  * In the data management tool, the user is able to view a vending machine's online status. When the restocker is using the vending machine, the status of the vending machine will be changed to "offline." If the restocker closes the interface without properly exiting, the offline status will not be updated. The menu will notify the user that the restocker interface needs to be exited properly in order to unlock access to the customer keypad.

## Known Missing "Release-10" Features

  * Restocker interface does not have a GUI. It's a command-line interface instead.

  * Data Management Tool interface does not have a GUI. It's a command-line interface instead.

  * Restocker is unable to make custom notes in the restocker interface.

  * Data Management Tool is unable to view custom notes made by the restocker.

## Execution Instructions

  1. Extract TeamFiveGUIsProject.zip.

  2. Open vending-machine folder.

  3. Execute VendingMachine.bat to open the Restocker/Customer part of the software.

	3.1 Enter 1 for the customer keypad.

	3.2 Enter 2 for the restocker interface.

  4. Execute DataManagementTool.bat to open the Data Management Tool to be used by corporate.

  5. All slot numbers are a combination of one letter (A through E) and one number (1 through 8). For example, slot number B7 exists, but slot F2 does not.

NOTE: There is a backup of software labeled "vending-machine-backup.zip" Please use this if the software from the original ZIP file does not work.
