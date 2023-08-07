import socket
import threading
import os
import time
import struct
import sys
import random
print ("\n Receiver is coming up. \n\n To use FTP, connect a server.")

#basic server info
client_ip = 'localhost'
client_port = 3379
sendRecv_port = 3380
buffer_size = 1024
client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
sendRecv_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
sendRecv_socket.bind((client_ip, sendRecv_port))
sendRecv_socket.listen()

def promptCommandReceiver(connection_socket):
    while True:
        print("\n Instruction is needed")
        prompt = connection_socket.recv(buffer_size).decode('utf-8')
        separator = ' '
        promptList = prompt.split(separator, -1)
        print(promptList)
        print ("\n Command received: {}".format(prompt))
        if len(promptList) == 4:
        	if promptList[0] == "RETR":
        		retr(promptList[1], promptList[2], promptList[3])
        	else:
            		notFound = "Command not recognized; please try again"
            		connection_socket.send(notFound.encode())
    connection_socket.close()
    
def retr(fileName, ip, port):
    print(ip)
    print(port)
    files = [f for f in os.listdir('.') if os.path.isfile(f)]
    isIncluded = 0
    #use code from socket lab
    for f in files:
        print(f)
        if f == fileName:
            isIncluded = 1
    if isIncluded == 1:
        print("included here")
        filename = os.path.join(os.getcwd(), fileName)
        document = open(str(filename),'rb')
        fileData = document.read()     
        
        
        
        if not isinstance(fileData, bytes):
            print ("sendBytes")
            send_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            send_socket.connect((ip, int(port)))
            print(ip)
            print(port)
            send_socket.send(fileData)
        else: 
            print("sendOther")
            send_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            send_socket.connect((ip, int(port)))
            print(ip)
            print(port)
            send_socket.send(fileData)
            print(fileData)
    else:
        error = "File not found"
        client_socket.send(error.encode('utf-8'))
    return
while True:
    connection_socket, addr = sendRecv_socket.accept()
    print('Connection acquired with address')
    print (addr)
    
    address = str(addr).replace("(", "").replace(")", "")
    addressSplit = address.split(",")
    threading.Thread(target=promptCommandReceiver, args = (connection_socket,)).start()
    

