## NOTICE

This repository contains the code for the Cat Squirter 5001

## Welcome!
This GitHub repository contains the source code that is used to build an Android app to control a *FIRST* Tech Challenge competition robot.  To use this SDK, download/clone the entire project to your local computer.

## Requirements
To use this Android Studio project, you will need Android Studio 2021.2 (codename Chipmunk) or later.
You will also need all the parts, which reside in following Onshape document: https://cad.onshape.com/documents/899beb2896fd4c74627132d8/w/9ad4ccae9838051736501f06/e/427a60bba9fc7b19c493f38e?renderMode=0&uiState=67b63fc7cf4dc4762e254e49



## Getting Started
If you are new to robotics or new to the *FIRST* Tech Challenge, then you should consider reviewing the [FTC Blocks Tutorial](https://ftc-docs.firstinspires.org/programming_resources/blocks/Blocks-Tutorial.html) to get familiar with how to use the control system:

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[FTC Blocks Online Tutorial](https://ftc-docs.firstinspires.org/programming_resources/blocks/Blocks-Tutorial.html)

Even if you are an advanced Java programmer, it is helpful to start with the [FTC Blocks tutorial](https://ftc-docs.firstinspires.org/programming_resources/blocks/Blocks-Tutorial.html), and then migrate to the [OnBot Java Tool](https://ftc-docs.firstinspires.org/programming_resources/onbot_java/OnBot-Java-Tutorial.html) or to [Android Studio](https://ftc-docs.firstinspires.org/programming_resources/android_studio_java/Android-Studio-Tutorial.html) afterwards.

## Downloading the Project
If you are an Android Studio programmer, there are several ways to download this repo.  Note that if you use the Blocks or OnBot Java Tool to program your robot, then you do not need to download this repository.

* If you are a git user, you can clone the most current version of the repository:

<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;git clone https://github.com/FIRST-Tech-Challenge/FtcRobotController.git</p>

* Or, if you prefer, you can use the "Download Zip" button available through the main repository page.  Downloading the project as a .ZIP file will keep the size of the download manageable.

* You can also download the project folder (as a .zip or .tar.gz archive file) from the Downloads subsection of the [Releases](https://github.com/FIRST-Tech-Challenge/FtcRobotController/releases) page for this repository.

* The Releases page also contains prebuilt APKs.

Once you have downloaded and uncompressed (if needed) your folder, you can use Android Studio to import the folder  ("Import project (Eclipse ADT, Gradle, etc.)").

## Getting Help
### User Documentation and Tutorials
*FIRST* maintains online documentation with information and tutorials on how to use the *FIRST* Tech Challenge software and robot control system.  You can access this documentation using the following link:

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[FIRST Tech Challenge Documentation](https://ftc-docs.firstinspires.org/index.html)

Note that the online documentation is an "evergreen" document that is constantly being updated and edited.  It contains the most current information about the *FIRST* Tech Challenge software and control system.

### Javadoc Reference Material
The Javadoc reference documentation for the FTC SDK is now available online.  Click on the following link to view the FTC SDK Javadoc documentation as a live website:

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[FTC Javadoc Documentation](https://javadoc.io/doc/org.firstinspires.ftc)

### Online User Forum
For technical questions regarding the Control System or the FTC SDK, please visit the FIRST Tech Challenge Community site:

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[FIRST Tech Challenge Community](https://ftc-community.firstinspires.org/)

### Sample OpModes
This project contains a large selection of Sample OpModes (robot code examples) which can be cut and pasted into your /teamcode folder to be used as-is, or modified to suit your team's needs.

Samples Folder: &nbsp;&nbsp; [/FtcRobotController/src/main/java/org/firstinspires/ftc/robotcontroller/external/samples](FtcRobotController/src/main/java/org/firstinspires/ftc/robotcontroller/external/samples)

The readme.md file located in the [/TeamCode/src/main/java/org/firstinspires/ftc/teamcode](TeamCode/src/main/java/org/firstinspires/ftc/teamcode) folder contains an explanation of the sample naming convention, and instructions on how to copy them to your own project space.

# Release Information

## Version 8.2 (20230707-131020)

### Breaking changes
* Non-linear (iterative) Op Modes are no longer allowed to manipulate actuators in their `stop()` method. Attempts to do so will be ignored and logged.
  * When an Op Mode attempts to illegally manipulate an actuator, the Robot Controller will print a log message
    including the text `CANCELLED_FOR_SAFETY`.
  * Additionally, LinearOpModes are no longer able to regain the ability to manipulate actuators by removing their
    thread's interrupt or using another thread.
* Removes support for Android version 6.0 (Marshmallow). The minSdkVersion is now 24.
* Increases the Robocol version.
  * This means an 8.2 or later Robot Controller or Driver Station will not be able to communicate with an 8.1 or earlier Driver Station or Robot Controller.
  * If you forget to update both apps at the same time, an error message will be shown explaining which app is older and should be updated.
* FTC_FieldCoordinateSystemDefinition.pdf has been moved.  It is still in the git history, but has been removed from the git snapshot corresponding with the 8.2 tag.  The official version now lives at [Field Coordinate System](https://ftc-docs.firstinspires.org/field-coordinate-system).
* `LynxUsbDevice.addConfiguredModule()` and `LynxUsbDevice.getConfiguredModule()` have been replaced with `LynxUsbDevice.getOrAddModule()`.

