<!DOCTYPE html>
<html>
<head>
	<title>WebSocket Client</title>
	<meta charset="utf-8">
</head>
<body>
	<input type="text" id="msg" onkeypress="sendMsg(event)" />
	<input type="button" onclick="sendMsg()" value="发送" />
	<br/>
	<div id="msg_list" style="width:300px,height:1000px,margin:100px 0 0 0 "></div>
	<script type="text/javascript">

		var text = document.getElementById("msg");
		var text_list = document.getElementById("msg_list");

		var websocket=new WebSocket("ws://192.168.3.11:9090/cool/");

		websocket.onopen=function()
		{
			showMsg("WebScoket 开启");
		}

		websocket.onmessage=function(event)
		{
			showMsg(event.data);
		}

		websocket.onclose=function()
		{
			showMsg("WebScoket 关闭");
		}

		function sendMsg(event)
		{	
			if (event != undefined && event.keyCode != 13)
			{
				return;
			}

			websocket.send(text.value);
			text.value="";
		}

		function showMsg(msg)
		{
			text_list.innerHTML += msg + '<br/>'; 
		}
		window.onbeforeunload = function()
		{
			websocket.close();
		}

		text.keypress=function(event) 
		{
			if (event.keyCode==13) 
			{
				sendMsg();
			}
		};
	</script>
</body>
</html>