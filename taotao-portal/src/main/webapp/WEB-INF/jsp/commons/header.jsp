<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="/js/base-v1.js" charset="utf-8"></script>
<!--shortcut start-->
<jsp:include page="shortcut.jsp" />
<!--shortcut end-->
<div id="o-header-2013">
	<div class="w" id="header-2013">
		<div id="logo-2013" class="ld"><a href="/"><b></b><img src="/images/taotao-logo.gif" width="270" height="60" alt="淘淘"></a></div>
		<!--logo end-->
		<div id="search-2013">
			<div class="i-search ld">
				<ul id="shelper" class="hide">
				</ul>
				<div class="form">
				<!-- 点击回车键开始搜索 -->
					<input type="text" class="text" accesskey="s" id="key" autocomplete="off" onkeydown="javascript:if(event.keyCode==13) search('key');">
				<!-- 点击搜索按钮开始搜索 -->
					<input type="button" value="搜索" class="button" onclick="search('key');return false;">
				</div>
			</div>
			<div id="hotwords" ></div>
		</div>
		<!--search end-->
		<div id="my360buy-2013">
			<dl>
				<dt class="ld"><a href="http://localhost:8084/user/showLogin">我的淘淘</a><b></b></dt>
				<dd>
					<div class="loading-style1"><b></b>加载中，请稍候...</div>
				</dd>
			</dl>
		</div>
		<!--my360buy end-->
		<div id="settleup-2013">
			<dl>
				<dt class="ld"><span class="shopping"><span id="shopping-amount"></span></span><a href="/order/order-cart.html" id="settleup-url">去购物车结算</a> <b></b> </dt>
<!-- 				<dd>
					<div class="prompt">
						<div class="loading-style1"><b></b>加载中，请稍候...</div>
					</div>
				</dd>
 -->			</dl>
		</div>
		<!--settleup end-->
	</div>
	<!--header end-->
	<div class="w">
		<div id="nav-2013">
			<div id="categorys-2013" class="categorys-2014">
				<div class="mt ld">
					<h2><a href="http://www.jd.com/allSort.aspx" >全部商品分类<b></b></a></h2>
				</div>
				<div id="_JD_ALLSORT" class="mc">
					<div class="item fore1">
						<span data-split="1">
								<a href="/products/1.html">图书、音像、电子书刊</a>
							
							</span>
					</div>
					<div class="item fore2">
						<span data-split="1">
								<a href="/products/74.html">家用电器</a>
							
							</span>
					</div>
					<div class="item fore3">
						<span data-split="1">
								<a href="/products/161.html">电脑、办公</a>
							
							</span>
					</div>
					<div class="item fore4">
						<span data-split="1">
								<a href="/products/249.html">个护化妆</a>
							
							</span>
					</div>
					<div class="item fore5">
						<span data-split="1">
								<a href="/products/290.html">钟表</a>
							
							</span>
					</div>
					<div class="item fore6">
						<span data-split="1">
								<a href="/products/296.html">母婴</a>
							
							</span>
					</div>
					<div class="item fore7">
						<span data-split="1">
								<a href="/products/378.html">食品饮料、保健食品</a>
							
							</span>
					</div>
					<div class="item fore8">
						<span data-split="1">
								<a href="/products/438.html">汽车用品</a>
							
							</span>
					</div>
					<div class="item fore9">
						<span data-split="1">
								<a href="/products/495.html">玩具乐器</a>
							
							</span>
					</div>
					<div class="item fore10">
						<span data-split="1">
								<a href="/products/558.html">手机</a>
							
							</span>
					</div>
					<div class="item fore11">
						<span data-split="1">
								<a href="/products/580.html">数码</a>
							
							</span>
					</div>
					<div class="item fore12">
						<span data-split="1">
								<a href="/products/633.html">家居家装</a>
							
							</span>
					</div>
					<div class="item fore13">
						<span data-split="1">
								<a href="/products/699.html">厨具</a>
							
							</span>
					</div>
					<div class="item fore14">
						<span data-split="1">
								<a href="/products/749.html">服饰内衣</a>
							</span>
					</div>
					<div class="extra">
						<a href="http://www.jd.com/allSort.aspx">全部商品分类</a>
					</div>
				</div>
			</div>
			<div id="treasure" ></div>
				<ul id="navitems-2013">
					<li class="fore1" id="nav-home" ><a href="/">首页</a></li>
					<li class="fore2" id="nav-fashion" ><a href="http://fashion.jd.com/">服装城</a></li>
					<li class="fore3" id="nav-chaoshi" ><a href="http://channel.jd.com/chaoshi.html">食品</a></li>
					<li class="fore4" id="nav-tuan" ><a href="http://tuan.jd.com/" target="_blank">团购</a></li>
					<li class="fore5" id="nav-auction" ><a href="http://auction.jd.com/">夺宝岛</a></li>
					<li class="fore6" id="nav-shan" ><a href="http://red.jd.com/" target="_blank">闪购</a></li>
					<li class="fore7" id="nav-jinrong" ><a href="http://jr.jd.com/" target="_blank">金融</a></li>
				</ul>
		</div>
	</div>
</div>
<script type="text/javascript">
(function(){if(pageConfig.navId){var object=document.getElementById("nav-"+pageConfig.navId);if(object)object.className+=" curr";}})();
</script>