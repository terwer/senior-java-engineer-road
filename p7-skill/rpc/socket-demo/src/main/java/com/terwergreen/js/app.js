// https://github.com/oracle/graaljs/blob/master/docs/user/FAQ.md

const Executors = Java.type("java.util.concurrent.Executors")
const ServerSocket = Java.type("java.net.ServerSocket")
const Byte = Java.type("java.lang.Byte")
const String = Java.type("java.lang.String")

const serverSocket = new ServerSocket(9999)
console.log("Node.js版socket服务器已启动.")

while (true) {
    const socket = serverSocket.accept()
    handle(socket)
}

function handle(socket) {
    try {
        console.log("处理客户端链接")

        const inputStream = socket.getInputStream()
        var b = java.lang.reflect.Array.newInstance(java.lang.Byte.TYPE, 1024)
        const read = inputStream.read(b)
        const in_bytes = new String(b, 0, read)
        console.log("接收到来着客户端的消息", in_bytes)

        let outputSream = socket.getOutputStream()
        // TODO:中文乱码，NodeJS的Bug，https://juejin.cn/post/6981797254705184798
        const out_bytes = Buffer.from("msg from graaljs socket server", "utf8")
        outputSream.write(out_bytes)
    } catch (e) {
        console.log("系统异常:", e)
    } finally {
        if (null != socket) {
            socket.close()
        }
    }
}
