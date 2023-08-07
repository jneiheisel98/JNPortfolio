import socket
import threading
import os
import time
import struct
import sys
import random
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

#accept connections of clients as they come in




def promptCommandReceiver(connection_socket):
    while True:
        print("\n Instruction is needed")
        prompt = connection_socket.recv(buffer_size).decode('utf-8')
        print ("\n Command received: {}".format(prompt))

        #directing the user to the proper function
        if (prompt) == "LIST":
            list_files()
        elif (prompt) == "RETR":
            retr()
        elif (prompt) == "STOR":
            stor()
        elif(prompt) == "QUIT":
            quit()
            return

        #avoid network troubles /closed connection by resetting the menu 
        prompt = None
 #function quit closes data from the client and then closes the server in that order
def quit():
    connection_socket.close()
    #server_socket.close()
    return

def list_files():
   
    print("Listing files...")
    data_conn_port = int(connection_socket.recv(buffer_size).decode('utf-8'))
    data_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    time.sleep(0.5)
    data_socket.connect((addr[0], int(data_conn_port))) 
    #get the files from the OS
    files = [f for f in os.listdir('.') if os.path.isfile(f)]
    file_string = ""
    for f in files:
        file_string = file_string + f+ "\n"
    data_socket.send(file_string.encode('utf-8'))

    #the files are in a list, so send the list line by line
    data_socket.close()
    return
def retr():
    print("Get the file")
    data_conn_port = int(connection_socket.recv(buffer_size).decode('utf-8')    )
    data_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    data_socket.bind((server_ip, random.randint(1030, 9999)))
    data_socket.connect((addr[0], int(data_conn_port)))
    filename = data_socket.recv(buffer_size).decode('utf-8')
    print(filename)
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
            return
        else: 
            print("sendOther")
            data_socket.send(fileData)
            print(fileData)
            return
    else:
        error = "File not found"
        data_socket.send(error.encode('utf-8'))
    data_socket.close()
    return
def stor():
    data_conn_port = int(connection_socket.recv(buffer_size).decode('utf-8')    )
    data_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    socketPort = random.randint(1030, 9999)
    data_socket.bind((server_ip, socketPort))
    print(data_conn_port)
    print(server_ip)
    print(socketPort)
    data_socket.connect((addr[0], int(data_conn_port)))
    filename = data_socket.recv(buffer_size).decode('utf-8')
    print(filename)
    #data_socket.recv(buffer_size).decode('utf-8')
    print("File copy here..\n")
    extension = filename.split(".")
    print(extension[1])
    if extension[1]== "jpg" or extension[1]== "png" or  extension[1]== "pdf" or extension[1]== "mp4" or  extension[1]== "eps" or extension[1]== "ai":
        content = data_socket.recv(buffer_size)
        #time.sleep(30)
        #content += connection_socket.recv(buffer_size)
    else:
        content = data_socket.recv(buffer_size).decode()
    print(content)
    try:
        #final check
        data_socket.send("1")
        return
    except:
        print ("couldn't get final server confirmation")
    try:
        print("in while loop")
        while content:
            if extension[1]== "jpg" or extension[1]== "png" or  extension[1]== "pdf" or extension[    1]== "mp4" or  extension[1]== "eps" or extension[1]== "ai" or extension[1]== "doc" or extension[1]== "docx" or extension[1]== "ppt" or extension[1]== "pptx":
                with open(filename, "ab") as file:
                    binCont = bytearray(content)
                    file.write(binCont)
                    content = data_socket.recv(buffer_size)
            else:
                with open(filename, "a") as file:
                    file.write(content)
                    content = data_socket.recv(buffer_size).decode()
            

            print ("new content")
            print(content)
        file.close()
    except: 
        if not file.closed:
            file.close
    data_socket.close()
    data_socket.close()
    return
while True:
    connection_socket, addr = server_socket.accept()
    print('Connection acquired with address')
    print(addr)
    threading.Thread(target=promptCommandReceiver, args = (connection_socket,)).start()

