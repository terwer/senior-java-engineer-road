# -*- coding: utf-8 -*-

from java.util.concurrent import Executors
from java.net import ServerSocket
from java.lang import String

import jarray
import sys

# Jython版socket服务端
#
# @name: ServerDemo
# @author: terwer
# @date: 2022-05-09 15:48
class ServerDemo(object):

    @classmethod
    def main(self, args):
        executorService = Executors.newCachedThreadPool()
        serverSocket = ServerSocket(9999)
        print("server is running")
        while True:
            socket = serverSocket.accept()
            # lambda <<argument(s)>> : <<function body>>
            # name_combo = lambda first, last: first + ' ' + last
            # name_combo('Jim','Baker')
            runnable_lambda_fun = lambda: self.handle(socket)
            executorService.execute(runnable_lambda_fun)

    @classmethod
    def handle(self, socket):
        try:
            print("receiced socket here=>")
            print(socket)

            inputStream = socket.getInputStream()
            b = jarray.zeros(1024, "b")
            read = inputStream.read(b)
            in_str = String(b, 0, read)
            print("receice msg from client=>")
            print(in_str)

            outputStream = socket.getOutputStream()
            out_bytes = map(ord, "msg from jython socket server")
            outputStream.write(out_bytes)
        except Exception as e:
            print("An error occured:")
            print(e)
        finally:
            try:
                socket.close()
            except IOError as e:
                print("An error occured:")
                print(e)

if __name__ == "__main__":
    ServerDemo.main(sys.argv)
