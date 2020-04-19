<%@page import="java.util.*"%>
<%@page contentType="text/html; charset=utf-8"%>
<html style="background-color: #e2e2e2;">
<head>
    <meta name="keywords" content="{{ lay.base.keywords }}">
    <meta name="description" content="{{ lay.base.description }}">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>发现 Layui 2020 年度最佳案例</title>
    <link rel="stylesheet" href="../res/layui/css/layui.css">
    <link rel="stylesheet" href="../res/css/global.css">
    <style>
        .header{border-bottom: 1px solid #404553; border-right: 1px solid #404553;}
    </style>
</head>
<body class="fly-full">
<div class="fly-case-header">
    <p class="fly-case-year">PSM</p>
    <a href="/case/{{ year }}/">
        <img class="fly-case-banner" src="../res/images/intro.png" alt="同校任务共享互助平台">
    </a>
    <!--  <div class="fly-case-btn">
        <a href="javascript:;" class="layui-btn layui-btn-big fly-case-active" data-type="push">提交案例</a>
        <a href="" class="layui-btn layui-btn-primary layui-btn-big">我的案例</a>

        <a href="http://fly.layui.com/jie/11996/" target="_blank" style="padding: 0 15px; text-decoration: underline">案例要求</a>
      </div> -->
</div>

<div class="fly-main" style="overflow: hidden;">

    <div class="fly-tab-border fly-case-tab">
    <span>
	  <a href="" class="tab-this">全部</a>
      <a href="">IT/软件</a>
      <a href="">社团</a>
	  <a href="">宠物</a>
	  <a href="">二手物品</a>
	  <a href="">实习</a>
	  <a href="">家教</a>
	  <a href="">考研</a>
	  <a href="">论文</a>
	  <a href="">其他</a>
    </span>
    </div>
    <div class="layui-tab layui-tab-brief">
        <ul class="layui-tab-title">
            <li class="layui-this"><a href="">全部</a></li>
            <li><a href="">按浏览量排行</a></li>
            <li><a href="">按截止日期排行</a></li>
            <li><a href="">按酬金排行</a></li>
        </ul>
    </div>

    <ul class="fly-case-list">
        <li data-id="123">
            <a class="fly-case-img" href="detail.jsp" target="_blank" >
                <img src="../res/images/mission.png" alt="任务">
                <cite class="layui-btn layui-btn-primary layui-btn-small" onclick='goDetail()'>任务详情</cite>
            </a>
            <h2><a href="http://fly.layui.com/" target="_blank">任务</a></h2>
            <p class="fly-case-desc">100r求助帮写一份测试文档，要求参照测试计划（GB8567——88）标准，使用word排版格式，计院学习过软件工程的同学优先。</p>
            <div class="fly-case-info">
                <a href="../user/home.html" class="fly-case-user" target="_blank"><img src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg"></a>
                <p class="layui-elip" style="font-size: 12px;"><span style="color: #666;">ddl：</span> 2020/11/30</p>
                <p>浏览量：<a class="fly-case-nums fly-case-active" href="javascript:;" data-type="showPraise" style=" padding:0 5px; color: #01AAED;">666</a>次</p>
                <button class="layui-btn layui-btn-primary fly-case-active" data-type="praise">收藏</button>
                <!-- <button class="layui-btn  fly-case-active" data-type="praise">已收藏</button> -->
            </div>
        </li>
        <li data-id="123">
            <a class="fly-case-img" href="detail.jsp" target="_blank" >
                <img src="../res/images/mission.png" alt="任务">
                <cite class="layui-btn layui-btn-primary layui-btn-small" onclick='goDetail()'>任务详情</cite>
            </a>
            <h2><a href="http://fly.layui.com/" target="_blank">任务</a></h2>
            <p class="fly-case-desc">100r求助帮写一份测试文档，要求参照测试计划（GB8567——88）标准，使用word排版格式，计院学习过软件工程的同学优先。</p>
            <div class="fly-case-info">
                <a href="../user/home.html" class="fly-case-user" target="_blank"><img src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg"></a>
                <p class="layui-elip" style="font-size: 12px;"><span style="color: #666;">ddl：</span> 2020/11/30</p>
                <p>浏览量：<a class="fly-case-nums fly-case-active" href="javascript:;" data-type="showPraise" style=" padding:0 5px; color: #01AAED;">666</a>次</p>
                <button class="layui-btn  fly-case-active" data-type="praise">已收藏</button>
            </div>
        </li>
        <li data-id="123">
            <a class="fly-case-img" href="detail.jsp" target="_blank" >
                <img src="../res/images/mission.png" alt="任务">
                <cite class="layui-btn layui-btn-primary layui-btn-small" onclick='goDetail()'>任务详情</cite>
            </a>
            <h2><a href="http://fly.layui.com/" target="_blank">任务</a></h2>
            <p class="fly-case-desc">100r求助帮写一份测试文档，要求参照测试计划（GB8567——88）标准，使用word排版格式，计院学习过软件工程的同学优先。</p>
            <div class="fly-case-info">
                <a href="../user/home.html" class="fly-case-user" target="_blank"><img src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg"></a>
                <p class="layui-elip" style="font-size: 12px;"><span style="color: #666;">ddl：</span> 2020/11/30</p>
                <p>浏览量：<a class="fly-case-nums fly-case-active" href="javascript:;" data-type="showPraise" style=" padding:0 5px; color: #01AAED;">666</a>次</p>
                <button class="layui-btn layui-btn-primary fly-case-active" data-type="praise">收藏</button>
                <!-- <button class="layui-btn  fly-case-active" data-type="praise">已收藏</button> -->
            </div>
        </li>
        <li data-id="123">
            <a class="fly-case-img" href="detail.jsp" target="_blank" >
                <img src="../res/images/mission.png" alt="任务">
                <cite class="layui-btn layui-btn-primary layui-btn-small" onclick='goDetail()'>任务详情</cite>
            </a>
            <h2><a href="http://fly.layui.com/" target="_blank">任务</a></h2>
            <p class="fly-case-desc">100r求助帮写一份测试文档，要求参照测试计划（GB8567——88）标准，使用word排版格式，计院学习过软件工程的同学优先。</p>
            <div class="fly-case-info">
                <a href="../user/home.html" class="fly-case-user" target="_blank"><img src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg"></a>
                <p class="layui-elip" style="font-size: 12px;"><span style="color: #666;">ddl：</span> 2020/11/30</p>
                <p>浏览量：<a class="fly-case-nums fly-case-active" href="javascript:;" data-type="showPraise" style=" padding:0 5px; color: #01AAED;">666</a>次</p>
                <button class="layui-btn layui-btn-primary fly-case-active" data-type="praise">收藏</button>
                <!-- <button class="layui-btn  fly-case-active" data-type="praise">已收藏</button> -->
            </div>
        </li>
        <li data-id="123">
            <a class="fly-case-img" href="detail.jsp" target="_blank" >
                <img src="../res/images/mission.png" alt="任务">
                <cite class="layui-btn layui-btn-primary layui-btn-small" onclick='goDetail()'>任务详情</cite>
            </a>
            <h2><a href="http://fly.layui.com/" target="_blank">任务</a></h2>
            <p class="fly-case-desc">100r求助帮写一份测试文档，要求参照测试计划（GB8567——88）标准，使用word排版格式，计院学习过软件工程的同学优先。</p>
            <div class="fly-case-info">
                <a href="../user/home.html" class="fly-case-user" target="_blank"><img src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg"></a>
                <p class="layui-elip" style="font-size: 12px;"><span style="color: #666;">ddl：</span> 2020/11/30</p>
                <p>浏览量：<a class="fly-case-nums fly-case-active" href="javascript:;" data-type="showPraise" style=" padding:0 5px; color: #01AAED;">666</a>次</p>
                <button class="layui-btn layui-btn-primary fly-case-active" data-type="praise">收藏</button>
                <!-- <button class="layui-btn  fly-case-active" data-type="praise">已收藏</button> -->
            </div>
        </li>
        <li data-id="123">
            <a class="fly-case-img" href="detail.jsp" target="_blank" >
                <img src="../res/images/mission.png" alt="任务">
                <cite class="layui-btn layui-btn-primary layui-btn-small" onclick='goDetail()'>任务详情</cite>
            </a>
            <h2><a href="http://fly.layui.com/" target="_blank">任务</a></h2>
            <p class="fly-case-desc">100r求助帮写一份测试文档，要求参照测试计划（GB8567——88）标准，使用word排版格式，计院学习过软件工程的同学优先。</p>
            <div class="fly-case-info">
                <a href="../user/home.html" class="fly-case-user" target="_blank"><img src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg"></a>
                <p class="layui-elip" style="font-size: 12px;"><span style="color: #666;">ddl：</span> 2020/11/30</p>
                <p>浏览量：<a class="fly-case-nums fly-case-active" href="javascript:;" data-type="showPraise" style=" padding:0 5px; color: #01AAED;">666</a>次</p>
                <button class="layui-btn layui-btn-primary fly-case-active" data-type="praise">收藏</button>
                <!-- <button class="layui-btn  fly-case-active" data-type="praise">已收藏</button> -->
            </div>
        </li>
        <li data-id="123">
            <a class="fly-case-img" href="detail.jsp" target="_blank" >
                <img src="../res/images/mission.png" alt="任务">
                <cite class="layui-btn layui-btn-primary layui-btn-small" onclick='goDetail()'>任务详情</cite>
            </a>
            <h2><a href="http://fly.layui.com/" target="_blank">任务</a></h2>
            <p class="fly-case-desc">100r求助帮写一份测试文档，要求参照测试计划（GB8567——88）标准，使用word排版格式，计院学习过软件工程的同学优先。</p>
            <div class="fly-case-info">
                <a href="../user/home.html" class="fly-case-user" target="_blank"><img src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg"></a>
                <p class="layui-elip" style="font-size: 12px;"><span style="color: #666;">ddl：</span> 2020/11/30</p>
                <p>浏览量：<a class="fly-case-nums fly-case-active" href="javascript:;" data-type="showPraise" style=" padding:0 5px; color: #01AAED;">666</a>次</p>
                <button class="layui-btn layui-btn-primary fly-case-active" data-type="praise">收藏</button>
                <!-- <button class="layui-btn  fly-case-active" data-type="praise">已收藏</button> -->
            </div>
        </li>
        <li data-id="123">
            <a class="fly-case-img" href="detail.jsp" target="_blank" >
                <img src="../res/images/mission.png" alt="任务">
                <cite class="layui-btn layui-btn-primary layui-btn-small" onclick='goDetail()'>任务详情</cite>
            </a>
            <h2><a href="http://fly.layui.com/" target="_blank">任务</a></h2>
            <p class="fly-case-desc">100r求助帮写一份测试文档，要求参照测试计划（GB8567——88）标准，使用word排版格式，计院学习过软件工程的同学优先。</p>
            <div class="fly-case-info">
                <a href="../user/home.html" class="fly-case-user" target="_blank"><img src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg"></a>
                <p class="layui-elip" style="font-size: 12px;"><span style="color: #666;">ddl：</span> 2020/11/30</p>
                <p>浏览量：<a class="fly-case-nums fly-case-active" href="javascript:;" data-type="showPraise" style=" padding:0 5px; color: #01AAED;">666</a>次</p>
                <button class="layui-btn layui-btn-primary fly-case-active" data-type="praise">收藏</button>
                <!-- <button class="layui-btn  fly-case-active" data-type="praise">已收藏</button> -->
            </div>
        </li>
    </ul>

    <!-- <blockquote class="layui-elem-quote layui-quote-nm">暂无数据</blockquote> -->

    <div style="text-align: center;">
        <div class="laypage-main">
            <span class="laypage-curr">1</span>
            <a href="">2</a><a href="">3</a>
            <a href="">4</a>
            <a href="">5</a>
            <span>…</span>
            <a href="" class="laypage-last" title="尾页">尾页</a>
            <a href="" class="laypage-next">下一页</a>
        </div>
    </div>
</div>


<script src="../res/layui/layui.js"></script>
<script>
    layui.cache.page = 'case';
    layui.cache.user = {
        username: '游客'
        ,uid: -1
        ,avatar: '../res/images/avatar/00.jpg'
        ,experience: 83
        ,sex: '男'
    };
    layui.config({
        version: "3.0.0"
        ,base: '../res/mods/' //这里实际使用时，建议改成绝对路径
    }).extend({
        fly: 'index'
    }).use('fly');
</script>
</body>
</html>