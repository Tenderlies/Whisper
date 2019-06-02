<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Flowplayer流视屏</title>
<link href="http://vjs.zencdn.net/5.20.1/video-js.css" rel="stylesheet">
<script src="http://vjs.zencdn.net/5.20.1/video.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/videojs-contrib-hls/5.12.2/videojs-contrib-hls.js"
	type="text/javascript"></script>
<style>
body {
	font: 12px "Myriad Pro", "Lucida Grande", sans-serif;
	text-align: center;
	padding-top: 10px;
}

.flowplayer {
	width: 80%;
	padding-bottom: 10px;
}
</style>
</head>
<body>
	<div class="flowplayer">
		<video id="videodemo"
			class="video-js vjs-default-skin vjs-big-play-centered" controls
			preload="auto" width="980" height="628"
			data-setup='{"controls": true, "autoplay": false, "preload": "auto" }'>
			<source src="rtmp://127.0.0.1:1935/live/" type='rtmp/flv' />
			<!--  <source src="rtmp://live.hkstv.hk.lxdns.com/live/hks" type='rtmp/flv' />
	<source src="http://ivi.bupt.edu.cn/hls/cctv1hd.m3u8"
			type='application/x-mpegURL' />--> </video>
	</div>

</body>
</html>