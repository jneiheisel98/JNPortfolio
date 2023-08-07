import socket
import threading
import os
import struct
import random
import time

client_ip = 'localhost'
client_port = 3375
buffer_size = 1024

new_server_port = random.randint(1030,9999)
client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
def connect():
    connection = input('To begin, please type the following: CONNECT <server name/IP Address> <server port>')
    separator = ' '
    connectTemp = connection.split(separator, -1)
    paramOne = "CONNECT"
    if paramOne == connectTemp[0]:
        connectIP = connectTemp[1]
        if (isinstance(connectIP, str)):
                connectIP = str(connectIP)
        connectPort = connectTemp[2]
        client_socket.connect((connectIP, int(connectPort)))
        client_socket.send(paramOne.encode('utf-8'))


def list_files():
    listLabel = "LIST"
    client_socket.send(listLabel.encode('utf-8'))
    time.sleep(2)
    client_socket.send(str(new_server_port).encode('utf-8'))
    new_client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    new_client_socket.bind((client_ip, new_server_port))
    new_client_socket.listen()
    connection_socket, addr = new_client_socket.accept()
    fetchFiles(connection_socket)
    connection_socket.close()
    return
def fetchFiles (connection_socket):
    print("Searching for list of files..\n")

    print(connection_socket.recv(buffer_size).decode('utf-8'))
    try:
        #final check
        connection_socket.send("1")
        return
    except:
        print ("couldn't get final server confirmation")
    connection_socket.close()
    
def retr_files(filename):
    retrLabel = "RETR"
    client_socket.send(retrLabel.encode('utf-8'))
    time.sleep(2)
    client_socket.send(str(new_server_port).encode('utf-8'))
    new_client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    new_client_socket.bind((client_ip, new_server_port))
    new_client_socket.listen()
    connection_socket, addr = new_client_socket.accept()
    retrieve(connection_socket, filename)
    connection_socket.close()
    return    
def retrieve (connection_socket, filename):
    connection_socket.send(str(filename).encode('utf-8'))
    print("File copy here..\n")
    extension = filename.split(".")
    print(extension[1])
    if extension[1]== "jpg" or extension[1]== "png" or  extension[1]== "pdf" or extension[1]== "mp4" or  extension[1]== "eps" or extension[1]== "ai":
        content = connection_socket.recv(buffer_size)
        #time.sleep(30)
        #content += connection_socket.recv(buffer_size)
    else:
        content = connection_socket.recv(buffer_size).decode()
    print(content)
    try:
        #final check
        connection_socket.send("1")
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
                    content = connection_socket.recv(buffer_size)
            else:
                with open(filename, "a") as file:
                    file.write(content)
                    content = connection_socket.recv(buffer_size).decode()
            

            print ("new content")
            print(content)
        file.close()
    except: 
        if not file.closed:
            file.close
    connection_socket.close()
   
def store_file(filename):
    storLabel = "STOR"
    client_socket.send(storLabel.encode('utf-8'))
    time.sleep(2)
    client_socket.send(str(new_server_port).encode('utf-8'))
    print(new_server_port)
    new_client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    new_client_socket.bind((client_ip, new_server_port))
    new_client_socket.listen()
    connection_socket, addr = new_client_socket.accept()
    connection_socket.send(str(filename).encode('utf-8'))
    
    #print(filename)
    files = [f for f in os.listdir('.') if os.path.isfile(f)]
    isIncluded = 0
    for f in files:
        #print(f)
        if f == filename:
            isIncluded = 1
    if isIncluded == 1:
        filename = os.path.join(os.getcwd(), filename)
        document = open(str(filename),'rb')
        fileData = document.read()     
        
        
        
        if not isinstance(fileData, bytes):
            print ("sendBytes")
            connection_socket.send(fileData)
            #return
        else: 
            print("sendOther")
            connection_socket.send(fileData)
            print(fileData)
            #return
    else:
        error = "File not found"
        connection_socket.send(error.encode('utf-8'))
    connection_socket.close()
    return

def quit():
    quitLabel = "QUIT"
    client_socket.send(quitLabel.encode('utf-8'))
    client_socket.recv(buffer_size)
    client_socket.close()
    print("Server connection ended")
    return

#automatic prompting menu for the client
print("\nWelcome to the FTP Client. Call one of the following functions:")
print("\n\nClient is online: Welcome!\n\n")
print("Call one of following connections:\n")
print(" CONNECT  : Connect to server\n")
print ("LIST     : List files \n")
print("RETR     : Retrieve files\n")
print("STOR     : Send files\n")
print("QUIT     :Exit\n")
while True:
    prompt = input("\nEnter a command: ")

    if prompt[:7].upper() == "CONNECT":
        connect()
    elif prompt[:4].upper() == "LIST":
        list_files()
    elif prompt[:4].upper() == "RETR":
        retr_files(prompt[5:])
    elif prompt[:4].upper() == "STOR":
        store_file(prompt[5:])
    elif prompt[:4].upper() == "QUIT":
        quit()
        break
    else:
        print ("Command not recognized; please try again")
client_socket.close()



