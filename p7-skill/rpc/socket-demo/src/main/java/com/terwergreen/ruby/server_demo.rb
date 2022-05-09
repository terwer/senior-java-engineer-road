require 'java'

java_import 'java.util.concurrent.Executors'
java_import 'java.net.ServerSocket'
java_import 'java.net.SocketInputStream'
java_import 'java.lang.Byte'

# JRuby版socket服务端
#
# https://github.com/jruby/jruby/wiki/JRuby-Reference#Arrays
#
# @name: ServerDemo
# @author: terwer
# @date: 2022-05-09 21:04
class ServerDemo
  def self.main()
    executorService = Executors.newCachedThreadPool()
    serverSocket = ServerSocket.new(9999)
    puts("jbury server is running...")
    while (true)
      socket = serverSocket.accept()
      runnable_lambda_fun = -> { self.handle(socket) }
      executorService.execute(runnable_lambda_fun)
    end
  end

  def self.handle(socket)
    begin
      print("receiced socket here=>")
      puts(socket)
      inputStream = socket.getInputStream()
      b = Java::byte[1024].new
      read = inputStream.read(b)
      puts "received msg from client=> #{b}"

      outputStream = socket.getOutputStream()
      out_bytes = "server msg send from jruby".to_java_bytes
      outputStream.write(out_bytes)
    rescue => e
      socket.close()
      puts "Exception Occurred #{e.class}. Message: #{e.message}. Backtrace:  \n #{e.backtrace.join("\n")}"
      Rails.logger.error "Exception Occurred #{e.class}. Message: #{e.message}. Backtrace:  \n #{e.backtrace.join("\n")}"
    end
  end
end

ServerDemo.main()