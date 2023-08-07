# Overview
- Here are the various funcitonal and non-functional requirements that our project accomplished. They are relative to a subsystem having to do with tilt functionality, overall output functionality, or overall input functionality. Requirements are laid out here for success measuring now that we are completed. Our major points of highlight are being clearly understanding the tilt mathematics, outputting to a display, and other functionality such as our button input. 

# Functional Requirements
1. Overall System/Interface
	1. Tilt-compensated compass shall record session data to a text file
	2. Tilt-compensated compass shall be stabilized on a level surface.
	3. Tilt-compensated compass shall have capability to record compensated heading.
2. Various Implementations
 	1. When button is pressed once LED screen shall begin displaying data.
 	2. When button is pressed twice data collection shall stop.
	2. Our data shall react appropriately to the magnet.
	3. Data recorded shall be delimited by spaces or commas.
	4. When titled on x and y, and not z, reading shall be same/similar.
	5. Language shall decode document and display info.
	6. Feature shall display header directions.
	7. Feature shall display pitch directions.
	8. Feature shall display roll directions.
	9. Program shall have the smallest runtime complexity possible.
	10. Compass shall not crash when moved super fast.
	11. LCD shall light up when running.
3. Axis Diagnostics
	1. Tilt-compensated compass shall record roll (x).
	2. Tilt-compensated compass shall record pitch (y).
	3. Tilt-compensated compass shall record heading (z).
 
# Non-Functional Requirements
1. System Expectations
	1. Compass shall be quiet to use.
	2. Calculation of values shall have a response time under 2 seconds.
	3. Compass shall have 5v maximum of power.
	4. Records shall be in a text file for easy parsing of information.
2. Coding
	1. Updates shall be pushed/pulled using GitHub.
	2. Comments throughout code shall explain our logic accurately.
	3. Code will follow proper readable syntax.
	4. It shall be able to work with any Xsens IMU device.
	5. It shall interface with additional operations. 
2. Output Logistics
	1. LCD display shall display all information needed.
	3. Compass' user interface shall be simple to read.
	4. Display shall fit in a 16x2 or larger dimension screen.
3. Portability
	1. Compass shall operate in environments 50-80 degrees Farenheit.
	2. Entire project shall be easily used with a single hand.
	3. Compass shall be under ten pounds in total weight.

# Artifact Hyperlinks
- We imported various photos that contributed towards development.
1. [Button](https://github.com/cooperalanmyers/GVSU-CIS350-Automotive/blob/master/artifacts/ButtonArtifact.jpg)
2. [Display](https://github.com/cooperalanmyers/GVSU-CIS350-Automotive/blob/master/artifacts/DisplayArtifact.png)
3. [Imu](https://github.com/cooperalanmyers/GVSU-CIS350-Automotive/blob/master/artifacts/IMUArtifact.png)
4. [Math Library](https://github.com/cooperalanmyers/GVSU-CIS350-Automotive/blob/master/artifacts/MathLibraryArtifact.png)

