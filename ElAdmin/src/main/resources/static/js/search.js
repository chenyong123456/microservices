	$(function(){
		$(".search_button").mouseenter(function(){
			$(this).css("background-color","#83837e");
		});
		$(".search_button").mouseleave(function(){
			$(this).css("background-color","#b6b6b6");
		});
		$(".search_button").mousedown(function(){
			$(this).css("background-color","#83837e");
		});
		$(".search_button").mouseup(function(){
			$(this).css("background-color","#b6b6b6");
		});
		$(document).keydown(function (event) {
			if (event.keyCode == 13) {
				$('.search_button').triggerHandler('click');
			}
		});
		var list_name;
		// 第二次点击出现的具体页面
		// ajax请求文件数据交互
		var href = location.href;
		var url = href.split("=")[1];
		var search_font= decodeURI(url);
		if(search_font != "undefined"){
			$.ajax({
				type: "GET", //请求方式
				url: "http://192.168.50.118:8088/pathway_InfoBypathway_name", //地址，就是json文件的请求路径
				//http://192.168.50.4:8088/pathway_InfoBypathway_name http://192.168.50.118:8080/pathway_InfoBypathway_name ./json/json_template-1.json
				data:{list_name:search_font},
				dataType: "json", //数据类型可以为 text xml json  script  jsonp
				error:function (error) {
					console.log(error)
				},
				success: function(list) { //返回的参数就是 action里面所有的有get和set方法的参数
					addList(list);
					if (list.flag == '0') {
						console.log('request exception');
						return;
					}
				},
			});
		}else{
			
		}
		$(".search_button").click(function(){
			search_input=$(".search_text").val();
			if(search_input != ""){
				$.ajax({
					type: "GET", //请求方式
					url: "http://192.168.50.118:8088/pathway_InfoBypathway_name", //地址，就是json文件的请求路径
					//http://192.168.50.4:8088/pathway_InfoBypathway_name http://192.168.50.118:8080/pathway_InfoBypathway_name ./json/json_template-1.json
					data:{list_name:search_input},
					dataType: "json", //数据类型可以为 text xml json  script  jsonp
					error:function (error) {
						console.log(error)
					},
					success: function(list) { //返回的参数就是 action里面所有的有get和set方法的参数
						addList(list);
						if (list.flag == '0') {
							console.log('request exception');
							return;
						}
					},
				});
				// $(".search_text").val("");
			}else{
				
			}
		});
	});
	function addList(list){
		$(".div_list").text("");
		if(list != ""){
			var list;
			for(var i=0;i < list.length;i++){
			$(".div_list").append(`<div class="pathway_font"><a class="div_list_a" href="new_bdbk?list_name=${encodeURI(list[i])}">${list[i]}</a></div>`);
			}
		}
		else{
			$(".div_list").append("<div class='pathway_font'><h2 style='color:skyblue'>"+"不存在这一项哦，请重新输入搜索!"+"<h2></div>");
		}
	}