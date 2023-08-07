# Overview
- Here are the various funcitonal and requirements that the project seeks to accomplish. Whether relative to a subsystem having to do with tilt functionality, overall output functionality, or overall input functionality, requirements are laid out here for success measuring. Major points of highlight are being able to clearly understand what needs to be tilted, outputs as a result, and other functionality. One note of importance though is that this only has to do with what the software should do. Any hardware issues or needs are not part of this.
# Functional Requirements
1. Overall System/Interface
	1. Tilt-compensated compass shall record session data to an SD card or text file
	2. Tilt-compensated compass shall be stabilized on a level surface.
	3. Tilt-compensated compass shall have capability to record both compensated and uncompensated heading.
2. Various Implementations
 	1. When power is turned on/off LED screen shall accordingly coordinate on/off.
	2. Our data shall react appropriately to the magnet.
	3. Data recorded shall be delimited by spaces or commas.
	4. When titled on x and y, and not z, reading shall be same/similar.
	5. Language shall decode document and display info.
	6. Feature shall show N E S W directions.
	7. Program shall have a linear runtime.
	8. Compass shall not crash when moved super fast.
	9. LED shall light up when running.
3. Axis Diagnostics
	1. Tilt-compensated compass shall record and display roll (x).
	2. Tilt-compensated compass shall record and display pitch (y).
	3. Tilt-compensated compass shall record and display heading (z).
4. Axis Logistics.
	1. When tilted, tilt-compensated compass Z-axis shall alter in value.
 
# Non-Functional Requirements
1. System Expectations
	1. Compass shall be quiet to use.
	2. Calculation of values shall have a response time under 2 seconds.
	3. Compass shall have 5v maximum of power.
	4. Records shall be in a text file for easy parsing of information.
	5. Separate user-input method shall be given conditions and output only the tilt-compensated value.
2. Coding
	1. Program shall be written in Python.
	2. Updates shall be pushed/pulled using GitHub.
	3. Comments throughout code shall explain our logic accurately.
2. Output Logistics
	1. Seven segment displays shall display numbers between 00 and 35.
	2. When calibrating a message shall appear.
	3. When turned on a welcome message shall print.
	4. Compass' user interface shall be simple to read.
3. Portability
	1. Compass shall operate in environments 50-80 degrees Farenheit.
	2. Entire project shall be easily used with a single hand.
	3. Compass shall be under five pounds in total weight.
