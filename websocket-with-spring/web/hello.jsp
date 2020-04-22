<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>web socket demo</title>
</head>
<body>


<script>
    //对象实例化出来就建立了连接
    var ws = new WebSocket("ws://localhost:8080/wsspring/hello");

    ws.onopen = function(evt) {
        console.log(evt);
        console.log("Connection open ...");
        ws.send("Hello WebSockets ");
        console.log("send completed---");
    };

    ws.onmessage = function(evt) {
        console.log( "Received Message: " + evt.data);
        ws.close();
    };

    ws.onclose = function(evt) {
        console.log(evt);
        console.log("Connection closed.");
    };
    ws.onerror =function (ev) {
        console.log("--error--");
        console.log(ev);
    }
</script>
</body>
</html>