import socket
import threading
import os
import time
import struct
import sys
import random


serverIP = []
serverPort = []
description = []
username = []
connectionSpeed = []
medium = []

#compile/interpretation success message
print ("\n Server is coming up. \n\n To use FTP, connect a client.")

#basic server info
server_ip = 'localhost'
server_port = 3375
buffer_size = 1024

#get server ready to accept a client
server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server_socket.bind((server_ip, server_port))

server_socket.listen()

print('The server is ready to receive')
def upload():
    time.sleep(2.5)
    server_socket.recv(buffer_size).decode()
    print(content)
    try:
        #final check
        connection_socket.send("1")
        return
    except:
        print ("couldn't get final server confirmation")
    try:
        while content:
            with open(filename, "a") as file:
                file.write(str(content))
                content = server_socket.recv(buffer_size).decode('utf-8')
                file.close()
    except: 
        if not file.closed:
            file.close
	##make it so that the file is at the index.
#accept connections of clients as they come in
def promptCommandReceiver(connection_socket):
    while True:
        print("\n Instruction is needed")
        prompt = connection_socket.recv(buffer_size).decode('utf-8')
        separator = ' '
        promptList = prompt.split(separator, -1)
        print(promptList)
        print ("\n Command received: {}".format(prompt))
	
        #directing the user to the proper function
        if len(promptList) != 3:
            if len(promptList) == 2:
            	if promptList[0] == "SEARCH":
            	    search(promptList[1])
            	    #say message of command not found
            elif len(promptList) == 1:
                   if promptList[0] == "QUIT":
                       quit()
                       return
                   elif promptList[0] == "CONNECT":
                       ack = "Connected. Put in username, speed, and internet medium"
                       time.sleep(3)
                       connection_socket.send(ack.encode())
                       index = len(serverIP)
                       serverIP.insert(index, addr[1])
                       print("connection address")
                       print(addr[0])
                       
                       
                       inWork = connection_socket.recv(buffer_size).decode()
                       inWorkParts = inWork.split()
                       username.insert(index, inWorkParts[0])
                       connectionSpeed.insert(index, inWorkParts[1])
                       medium.insert(index, inWorkParts[2])
                       serverPort.insert(index, addr[1])
                       print(inWorkParts)
                       print(username)
                       print(connectionSpeed)
                       print(medium)
                       print(serverPort)
                      
                       ack = "Upload file descriptions"
                       connection_socket.send(ack.encode())
                       time.sleep(5)
                       desc = connection_socket.recv(buffer_size).decode()
                       temp = ""
                       while temp != "END":
                           temp = connection_socket.recv(buffer_size).decode()
                           desc += temp
                           #print(desc)
                           #print(temp)
                           #print("here1")
                           
                       else:
                           #print("here2")    
                           #print(desc)
                           #print("here3")
                           description.insert(index, desc)
                       print("end")
def quit():
    index = serverPort.index(addr[1])
    serverIP.pop(index)
    serverPort.pop(index)
    description.pop(index)
    username.pop(index)
    connectionSpeed.pop(index)
    medium.pop(index)
    connection_socket.close()
    return
def search(term):
    params = term.split(':')
    time.sleep(2)
    counter = 0
    print(term)
    for d in description:
        tempDesc = d.upper()
        tempTerm = params[0].upper()
        result = tempDesc.find(tempTerm)
        #print(result)
        if tempDesc.find(tempTerm) != -1:
            serverGotIP = serverIP[counter]
            serverGotPort = serverPort[counter]
            data = tempDesc.split(";")
            count = 0
            fileName = ""
            returnState = ""
            #print(data)
            for dat in data:
                #print("find data")
                if dat.find(tempTerm) != -1 and count !=0 and count %2 !=0:
                    fileName = data[count-1]
                    returnState = returnState + str(serverGotIP) + " " + str(serverGotPort) + " " + fileName        
                    print(returnState)
                    
                count = count + 1
            send_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            print(server_ip)
            print(params[1])                    
            send_socket.connect((server_ip, int(params[1])))
            print(server_ip)
            print(params[1])
            send_socket.send(returnState.encode())   
    #return nothing if nothing is found
    '''if not 
    notFound = "File not found"    
    connection_socket.send(notFound.encode())
    '''
while True:
    connection_socket, addr = server_socket.accept()
    print('Connection acquired with address')
    print (addr)
     
    threading.Thread(target=promptCommandReceiver, args = (connection_socket,)).start()

