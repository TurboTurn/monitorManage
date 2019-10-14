<!DOCTYPE html>
<html style="height: 100%">
<head>
    <meta charset="utf-8">
    <div id="container" style="height: 100%"></div>
    <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts.min.js"></script>
    <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-gl/echarts-gl.min.js"></script>
    <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-stat/ecStat.min.js"></script>
    <script type="text/javascript"
            src="http://echarts.baidu.com/gallery/vendors/echarts/extension/dataTool.min.js"></script>
    <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/china.js"></script>
    <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/world.js"></script>
    <!--    <script type="text/javascript" src="https://api.map.baidu.com/api?v=2.0&ak=xfhhaTThl11qYVrqLZii6w8qE5ggnhrY&__ec_v__=20190126"></script>-->
    <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/bmap.min.js"></script>
    <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/simplex.js"></script>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
</head>
<body style="height: 100%; margin: 0">

<script type="text/javascript">
    var dom = document.getElementById("container");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;
    option = {
        tooltip: {
            formatter: "{a} <br/>{b} : {c}%"
        },
        toolbox: {
            feature: {
                restore: {},
                saveAsImage: {}
            }
        },
        series: [
            {
                name: '压力监测',
                type: 'gauge',
                detail: {formatter: '{value} KPa'},
                data: [{value: 0, name: '当前压强'}]
            }
        ]
    };


    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
    /*var ip;
    $.ajax({
        type:"get",
        async:false,//同步执行
        url:"/getIp",
        dataType:"text",
        success:function (data) {
            ip=data;
        }
    });*/
    // alert(ip);
    const ip = window.location.host;
    var websocket = new WebSocket("ws://" + ip + "/gaugeWebsocket");


    //连接成功建立的回调方法
    websocket.onopen = function () {
        console.log("WebSocket连接成功");
    };
    //接收到消息的回调方法
    websocket.onmessage = function (event) {
        option.series[0].data[0].value = event.data;
        myChart.setOption(option, true);
    };
    //连接关闭的回调方法
    websocket.onclose = function () {
        console.log("WebSocket连接关闭");
    };
    //连接发生错误的回调方法
    websocket.onerror = function () {
        alert("WebSocket连接发生错误");
    };
    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        websocket.close();
    };

</script>
</body>
</html>
