<%@ page contentType="text/html;charset=UTF-8"  %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>web socket demo</title>
</head>
<body>

<select id="onlineUsers" multiple>

</select>

<input type="text" id="msg" />
<input type="button" id="btnSend" value="send"/>
<div id="chatroom">

</div>
<script src="js/jquery-3.3.1.min.js"></script>
<script>
    $(function () {
        //对象实例化出来就建立了连接
        var ws = new WebSocket("ws://localhost:8080/wsspring/chatroom");

        ws.onopen = function(evt) {
            showMessage("connected--");
        };

        ws.onmessage = function(evt) {
            showMessage(evt.data);

        };
        ws.onerror =function (ev) {
            alert('some error错!!--');
        }

        function showMessage(msg) {
            var response = document.getElementById('chatroom');
            var p = document.createElement('p');
            p.style.wordWrap = 'break-word';
            p.appendChild(document.createTextNode(msg));
            response.appendChild(p);
        }
        $("#btnSend").click(function () {
            if(ws.readyState != ws.OPEN) return;
            var msg = $("#msg").val();
            ws.send(msg);
        });
    })

</script>
</body>
</html>