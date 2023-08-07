import socket
import threading
import os
import time
import struct
import sys
import random

#compile/interpretation success message
print ("\n Client is coming up. \n\n To use FTP, connect a client.")

#basic server info
client_ip = 'localhost'
client_port = random.randint(3390,9999)
sendRecv_port = random.randint(3390,9999)
explore_port = random.randint(3390,9999)
buffer_size = 1024
client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
sendRecv_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
sendRecv_socket.bind((client_ip, sendRecv_port))
sendRecv_socket.listen()
explore_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
recv_socket =  socket.socket(socket.AF_INET, socket.SOCK_STREAM)
recv_socket.bind((client_ip, explore_port))
recv_socket.listen()


print("\nClient is up")
def retr(fileName):
    files = [f for f in os.listdir('.') if os.path.isfile(f)]
    isIncluded = 0
    #use code from socket lab
    for f in files:
        print(f)
        if f == filename:
            isIncluded = 1
    if isIncluded == 1:
        print("included here")
        filename = os.path.join(os.getcwd(), filename)
        document = open(str(filename),'rb')
        fileData = document.read()     
        
        
        
        if not isinstance(fileData, bytes):
            print ("sendBytes")
            data_socket.send(fileData)
        else: 
            print("sendOther")
            data_socket.send(fileData)
            print(fileData)
    else:
        error = "File not found"
        data_socket.send(error.encode('utf-8'))
    return
def quit():
    quitLabel = "QUIT"
    client_socket.send(quitLabel.encode('utf-8'))
    client_socket.recv(buffer_size)
    client_socket.close()
    print("Server connection ended")
    return
def search(term):
    searchLabel = "SEARCH "+ term + ":" + str(explore_port)
    client_socket.send(searchLabel.encode('utf-8'))
    connected_socket, addr = explore_socket.accept()
    nextSteps = connected_socket.recv(buffer_size).decode()
    
    print(nextSteps)
    
def connect():
    connection = input('To begin, please type the following: CONNECT <server name/IP Address> <server port>')
    separator = ' '
    connectTemp = connection.split(separator, -1)
    print(connectTemp)
    print("\n")
    paramOne = "CONNECT" 
    #+ connectTemp[3] + " " + connectTemp[4]
    print(paramOne)
    if paramOne == connectTemp[0]:
        connectIP = connectTemp[1]
        if (isinstance(connectIP, str)):
                connectIP = str(connectIP)      
        connectPort = connectTemp[2]
        client_socket.connect((connectIP, int(connectPort)))
        time.sleep(4)
       
        params = "CONNECT" 
        client_socket.send(params.encode('utf-8'))
        time.sleep(4)
        ack = client_socket.recv(buffer_size).decode()
        print(ack)
        
        prompt = input("\nEnter Username, speed, internet medium: ")
        prompt+= " "+str("3378")
        client_socket.send(prompt.encode())
        time.sleep(4)
        filePrompt = client_socket.recv(buffer_size).decode()
        print(filePrompt)
        desc = input("\nEnter description file name: ")
        files = [f for f in os.listdir('.') if os.path.isfile(f)]
        isIncluded = 0
        #use code from socket lab
        for f in files:
            print(f)
            if f == desc:
               isIncluded = 1
        if isIncluded == 1:
            print("included here")
            filename = os.path.join(os.getcwd(), desc)
            document = open(str(desc),'rb')
            fileData = document.read()     
            nullString = "END"
            if not isinstance(fileData, bytes):
                print ("sendBytes")
                client_socket.send(fileData.encode())
                time.sleep(3)
                client_socket.send(nullString.encode())
            else: 
                print("sendOther")
                client_socket.send(fileData)
                time.sleep(3)
                client_socket.send(nullString.encode())
                print(fileData)
        else:
            error = "File not found"
            client_socket.send(error.encode('utf-8'))
    
        return
def get(otherIP, otherPort, fileName):
    explore_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    explore_socket.connect((otherIP, int(otherPort)))
    time.sleep(4)
    sendState = "RETR "+ fileName + " " + str(client_ip) + " " + str(explore_port)
    explore_socket.send(sendState.encode())
    extension = fileName.split(".")
    filename = fileName
    connected_socket, addr = recv_socket.accept()
    content = connected_socket.recv(buffer_size)
    print(content)
    try:
        #final check
        print("in final check")
        explore_socket.send("1")
        return
    except:
        print ("couldn't get final server confirmation")
    try:
        while content:
            if extension[1]== "jpg" or extension[1]== "png" or  extension[1]== "pdf" or extension[    1]== "mp4" or  extension[1]== "eps" or extension[1]== "ai" or extension[1]== "doc" or extension[1]== "docx" or extension[1]== "ppt" or extension[1]== "pptx":
                with open(filename, "ab") as file:
                    
                    binCont = bytearray(content)
                    file.write(binCont)
                    content = connected_socket.recv(buffer_size)
            else:
                with open(filename, "a") as file:
                    content = content.decode()
                    file.write(content)
                    content = connected_socket.recv(buffer_size)
            file.close()
    except: 
        if not file.closed:
            file.close
    connected_socket.close()
        
#automatic prompting menu for the client
print("\nWelcome to the FTP Client. Call one of the following functions:")
print("\n\nClient is online: Welcome!\n\n")
print("Call one of following connections:\n")
print(" CONNECT  : Connect to server\n")
print ("GET     : GET files from remote server \n")
print("RETR     : Retrieve file matches\n")
print("QUIT     :Exit\n")
while True:
    prompt = input("\nEnter a command: ")

    if prompt[:7].upper() == "CONNECT":
        connect()
    elif prompt[:4].upper() == "RETR":
        search(prompt[5:])
    elif prompt[:4].upper() == "QUIT":
        quit()
        break
    elif prompt[:3].upper() == "GET":
        splitPrompt = prompt.split()
        get(splitPrompt[1], splitPrompt[2], splitPrompt[3])
    else:
        print ("Command not recognized; please try again")
client_socket.close()
    
def promptCommandReceiver(connection_socket):
    while True:
        print("\n Instruction is needed")
        prompt = connection_socket.recv(buffer_size).decode('utf-8')
        separator = ' '
        promptList = prompt.split(separator, -1)
        print(promptList)
        print ("\n Command received: {}".format(prompt))
        if len(promptList) == 2:
            	if promptList[0] == "RETR":
            	    retr(promptList[1])
            	else:
                    notFound = "Command not recognized; please try again"
                    connection_socket.send(notFound.encode())
    connection_socket.close()
