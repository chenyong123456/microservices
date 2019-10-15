// 页面缩放特效
function pd() {
	if ($(window).width() < 1000) {
		$(".page").width(1000);
	} else {
		$(".page").width($("body").width());
	}
	// 背景图片大小
	var left = $(".page_color").width() - 1920;
	$(".page_div>div").css("background-position", left / 2 + "px 0px");
}
pd();
$(window).resize(function() {
	pd();
});


//让元素可以编辑
function startEdit() {
	var element = document.getElementById("editableDiv");
	element.contentEditable = true;
}
//让元素恢复正常状态
function stopEdit() {
	var element = document.getElementById("editableDiv");
	element.contentEditable = false;
	//显示出编辑后的内容
	alert("当前内容是：" + element.innerHTML);
}
// 右下角导航
$(function() {
	// 搜索框按钮样式
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
	// 绑定回车
	$(document).keydown(function (event) {
		if (event.keyCode == 13) {
			$('.search_button').triggerHandler('click');
		}
	});
	// 右下角导航栏的固定
	document.body.onscroll = function(){ 
		var scrollTop = document.documentElement.scrollTop || document.body.scrollTop; 
		$(".pathway_navs").css("top",scrollTop+350);
	}
		// 第二次点击出现的具体页面
		// ajax请求文件数据交互
		$(".search_button").click(function(){
			search_name=$(".search_text").val();//视图渲染界面的输入框中的搜索
			if(search_name != ""){
				// $(".search_text").val("");
				window.location.href="http://192.168.50.118:8088/aa/new_bdbk?list_name="+encodeURI(search_name);
			}else{
				
			}
		});
		var p_name;
		var href = location.href;
		var url = href.split("=")[1];
		var search_text = decodeURI(url);
		// 具体值的Ajax请求
		$.ajax({
			type: "GET", //请求方式
			url: "http://192.168.50.118:8088/pathway_InfoBy_Only_pathway_name", //地址，就是json文件的请求路径
			//http://192.168.50.4:8088/pathway_InfoBypathway_name http://192.168.50.118:8080/pathway_InfoBypathway_name ./json/json_template-1.json
			data:{p_name:search_text},
			dataType: "json", //数据类型可以为 text xml json  script  jsonp
			error:function (error) {
			},
			success: function(data) { //返回的参数就是 action里面所有的有get和set方法的参数
				addBox(data); //实参传值
				$(".pathway_nav ol").css("display","block");
				if (data.flag == '0') {
					console.log('request exception');
					return;
				}
			},
		});
});

function data_transfer(min,max){
	time = "";
	if(min>=0 ){
		time_u ="时";
		var t_max = max/24;
		var t_min = min/24;
		if(t_min == t_max){
			if(t_max <= 2){
				time = max  + "时";
			}
			else if(t_max <= 14 && t_max > 2){
				if(max%24 != 0){
					time = max + "时";
				}
				else{
					time = t_max  + "天";
				}
				
			}
			else if(t_max <= 56 && t_max > 14){
				if(max%168 != 0){
					time = t_max  + "天";
				}else{
					time = t_max  + "周";
				}
			}
			else{
				if(t_max % 672 != 0){
					time = t_max  + "周";
				}
				else{
					time = t_max  + "月";
				}
			}
		}
		else{
			if(t_max <= 2){
				time =min + "-" + max + "时";
			}
			else if(t_max <= 14 && t_max > 2){
				if(max%24 != 0){
					time = min + "-" + max +"时";
				}else{
					time =t_min + "-" + t_max  + "天";
				}
			}
			else if(t_max <= 56 && t_max > 14){
				if(max%168 != 0){
					time = t_min + "-" + t_max +"天";
				}else{
					time =t_min + "-" + t_max  + "周";
				}
			}
			else{
				if(t_max % 672 != 0){
					time =t_min + "-" + t_max  + "周";
				}
				else{
					time =t_min + "-" + t_max  + "月";
				}
				
			}
		}
	}		
	else{
		time ="";
	}
	return time;
} 

function addBox(data) {
	// console.log(data["treatment_days"])
	// 临时路径适用对象
	var suitable_subject_disc = data["suitable_subject_disc"].split("。");
	for (var i = 0; i < suitable_subject_disc.length; i++) {
		$(".suitable_subject_disc_tl").append("<div class='pathway_font'>" + suitable_subject_disc[i] + "</div>");
	}
	// 诊断依据
	var diagnosis = data["diagnosis"].split("。");
	for (var i = 0; i < diagnosis.length; i++) {
		$(".diagnosis_tl").append("<div class='pathway_font'>" + diagnosis[i] + "</div>")
	}
	console.log(data["treatment_choice"])
	for (var key in data) {
		// 临时路径名字
		$('h1[data-key="' + key + '"]').text(data[key] + '临床路径');
		
		// 第二部分treatment_choice下面的二级目录 及ref/scenario等
		for (var key01 in data["treatment_choice"]) {
			$('div[data-key="' + 'treatment_choice_' + key01 + '"]').text(data["treatment_choice"][key01]);
		}
	}

	// 住院流程名称
	$(".pathway_name_lc").text('初治' + data["pathway_name"] + '住院流程');

	//第二部分treatment_choice num控制 治疗方案的选择
	if (data["treatment_choice"]["scenario"]["num"] <= 0) {
		
	} else {
		var num = data["treatment_choice"]["scenario"]["num"];
		for (var i = 0; i < num; i++) {
			var arr = "";
			for (var j = 0; j < data["treatment_choice"]["scenario"]["id_" + i].length; j++) {
				arr += `<li>
						${data["treatment_choice"]["scenario"]["id_" + i][j]}
					</li>`;
			}
			$(".treatment_choice_arr").append(
				"<ol class='arr_ol'>" + arr + "</ol>"
			);
			//第二种方式 
			// $(".treatment_choice_" + i).text(data["treatment_choice"]["scenario"]["id_" + i]);
		}
	}

	// 第三部分 treatment_days 标准住院时间
	if (data["treatment_days"]["scenario"]["num"] == 1) {
		if (data["treatment_days"]["scenario"]["id_0"]["max"] > 0) {
			if (data["treatment_days"]["scenario"]["id_0"]["tag_name"] != "") {
				$('h3[data-key="treatment_days_name"]').text('标准住院日：' + data["treatment_days"]["scenario"]["id_0"]["tag_name"] +'：' + data_transfer(data["treatment_days"]["scenario"]["id_0"]["min"],data["treatment_days"]["scenario"]["id_0"]["max"]));
			} else {
				$('h3[data-key="treatment_days_name"]').text('标准住院日：' + data_transfer(data["treatment_days"]["scenario"]["id_0"]["min"],data["treatment_days"]["scenario"]["id_0"]["max"]));
			}
		} else {

		}
	} else if (data["treatment_days"]["scenario"]["num"] <= 0) {

	} else {
		var num = data["treatment_days"]["scenario"]["num"];
		$('h3[data-key="treatment_days_name"]').text('标准住院日：')
		for (var i = 0; i < num; i++) {
			if (data["treatment_days"]["scenario"]["id_" + i]["max"] > 0) {
				$(".treatment_days").append(
					"<div class='pathway_font treatment_days_" + i + "'></div>"
				);
				if (data["treatment_days"]["scenario"]["id_" + i]["tag_name"] != "") {
					$(".treatment_days_" + i).text(data["treatment_days"]["scenario"]["id_" + i]["tag_name"] + '：' + data_transfer(data["treatment_days"]["scenario"]["id_" + i]["min"],data["treatment_days"]["scenario"]["id_" + i]["max"]));
				} else {
					$(".treatment_days_" + i).text(data_transfer(data["treatment_days"]["scenario"]["id_" + i]["min"],data["treatment_days"]["scenario"]["id_" + i]["max"]));
				}
			} else {

			}
		}
	}

	// 第四部分 treatment_entry_standard 进入路径标准
	if (data["treatment_entry_standard"]["num"] >= 1) {
		var num = data["treatment_entry_standard"]["num"];
		for (var i = 0; i < num; i++) {
			var t = i + 1;
			var tl = '' + t + '. ';
			$(".treatment_entry_standard_tl").append(
				"<div class='pathway_font tre atment_entry_standard_" + i + "'>" + [tl] + data["treatment_entry_standard"]["id_" +i] + "</div>"
			);
		}
	} else {

	}

	// 第五部分 prep_treatment_common 术前检查
	var prep_treatment_common_arr = 0;
	if (data["prep_treatment_common"]["scenario"]["num"] == 1) {
		if (data["prep_treatment_common"]["scenario"]["id_0"]["duration"]["max"] > 0) {
			$('h3[data-key="prep_treatment_common_name"]').text(data["type"] + '前检查：' + data_transfer(data["prep_treatment_common"]["scenario"]["id_0"]["duration"]["min"],data["prep_treatment_common"]["scenario"]["id_0"]["duration"]["max"]));
		} else {
			$('h3[data-key="prep_treatment_common_name"]').text(data["type"] + '前检查：');
		}


		// 第一个为必需的检查项目
		if (data["prep_treatment_common"]["scenario"]["id_0"]["obligatory_exam"] != "") {
			prep_treatment_common_arr++;
			$(".prep_treatment_common_arr").append("&nbsp; &nbsp; &nbsp;" + prep_treatment_common_arr + ".&nbsp; 必需的检查项目");
			for (var j = 0; j < data["prep_treatment_common"]["scenario"]["id_0"]["obligatory_exam"].length; j++) {
				$(".prep_treatment_common_arr").append("<div class='pathway_tl2 prep_treatment_common_" + j + "'>" + ' &nbsp; ' + prep_treatment_common_arr + '.' + [j + 1] + '.' + '&nbsp; ' + data["prep_treatment_common"]["scenario"]["id_0"]["obligatory_exam"][j] + "</div>"
				);
			}
		} else {

		}

		// 第二个为根据患者病情可选择的检查项目
		if (data["prep_treatment_common"]["scenario"]["id_0"]["optional_exam"] != "") {
			prep_treatment_common_arr++;
			$(".prep_treatment_common_arr").append("&nbsp; &nbsp; &nbsp;" + prep_treatment_common_arr + ".&nbsp; 根据患者病情可选择的检查项目");
			for (var j = 0; j < data["prep_treatment_common"]["scenario"]["id_0"]["optional_exam"].length; j++) {
				$(".prep_treatment_common_arr").append("<div class='pathway_tl2 prep_treatment_common_" + j + "'>" + ' &nbsp; ' + prep_treatment_common_arr + '.' + [j + 1] + '.' + '&nbsp; ' + data["prep_treatment_common"]["scenario"]["id_0"]["optional_exam"][j] + "</div>"
				);
			}
		} else {

		}

		// 第三个为必要的告知信息
		if (data["prep_treatment_common"]["scenario"]["id_0"]["notification"] != "") {
			prep_treatment_common_arr++;
			$(".prep_treatment_common_arr").append("&nbsp; &nbsp; &nbsp;" + prep_treatment_common_arr + ".&nbsp; 必要的告知信息");
			for (var j = 0; j < data["prep_treatment_common"]["scenario"]["id_0"]["notification"].length; j++) {
				$(".prep_treatment_common_arr").append("<div class='pathway_tl2 prep_treatment_common_" + j + "'>" + ' &nbsp; ' +
					prep_treatment_common_arr + '.' + [j + 1] + '.' + '&nbsp; ' + data["prep_treatment_common"]["scenario"]["id_0"]["notification"][j] + "</div>"
				);
			}
		} else {

		}

	} else if (data["prep_treatment_common"]["scenario"]["num"] > 1) {
		$('h3[data-key="prep_treatment_common_name"]').text('术前准备：');
		var num = data["prep_treatment_common"]["scenario"]["num"];
		for (var i = 0; i < num; i++) {
			if (data["prep_treatment_common"]["scenario"]["id_" + i]["duration"]["max"] > 0) {
				$(".prep_treatment_common_arr").append("<div class='pathway_tl prep_treatment_common_" + i + "'>" + data_transfer(data["prep_treatment_common"]["scenario"]["id_" + i]["duration"]["min"],data["prep_treatment_common"]["scenario"]["id_" + i]["duration"]["max"])+"</div>"
				);
			} else {

			}
			prep_treatment_common_arr = 0;
			// 第一个为必需的检查项目
			if (data["prep_treatment_common"]["scenario"]["id_" + i]["obligatory_exam"] != "") {
				prep_treatment_common_arr++;
				$(".prep_treatment_common_arr").append("&nbsp; &nbsp; &nbsp;" + prep_treatment_common_arr + ".&nbsp; 必需的检查项目");
				for (var j = 0; j < data["prep_treatment_common"]["scenario"]["id_" + i]["obligatory_exam"].length; j++) {
					$(".prep_treatment_common_arr").append("<div class='pathway_tl2 prep_treatment_common_" + j + "'>" + ' &nbsp; ' + prep_treatment_common_arr + '.' + [j + 1] + '.' + '&nbsp; ' + data["prep_treatment_common"]["scenario"]["id_" + i]["obligatory_exam"][j] + "</div>"
					);
				}

			} else {

			}
			// 第二个为根据患者病情可选择的检查项目
			if (data["prep_treatment_common"]["scenario"]["id_" + i]["optional_exam"] != "") {
				prep_treatment_common_arr++;
				$(".prep_treatment_common_arr").append("&nbsp; &nbsp; &nbsp;" + prep_treatment_common_arr +
					".&nbsp; 根据患者病情可选择的检查项目");
				for (var j = 0; j < data["prep_treatment_common"]["scenario"]["id_" + i]["optional_exam"].length; j++) {
					$(".prep_treatment_common_arr").append("<div class='pathway_tl2 prep_treatment_common_" + j + "'>" + ' &nbsp; ' +
						prep_treatment_common_arr + '.' + [j + 1] + '.' + '&nbsp; ' + data["prep_treatment_common"]["scenario"]["id_" + i]["optional_exam"][j] + "</div>"
					);
				}
			} else {

			}

			// 第三个为必要的告知信息
			if (data["prep_treatment_common"]["scenario"]["id_" + i]["notification"] != "") {
				prep_treatment_common_arr++;
				$(".prep_treatment_common_arr").append("&nbsp; &nbsp; &nbsp;" + prep_treatment_common_arr + ".&nbsp; 必要的告知信息");
				for (var j = 0; j < data["prep_treatment_common"]["scenario"]["id_" + i]["notification"].length; j++) {
					$(".prep_treatment_common_arr").append("<div class='pathway_tl2 prep_treatment_common_" + j + "'>" + ' &nbsp; ' +
						prep_treatment_common_arr + '.' + [j + 1] + '.' + '&nbsp; ' + data["prep_treatment_common"]["scenario"]["id_" +i]["notification"][j] + "</div>"
					);
				}
			} else {

			}

		}
	} else {

	}

	

	//第六部分 prep_treatment_drug_usage 正式的医学处理前药物使用 药物的选择时机
	var prep_treatment_drug_usage_change = 0;
	// 第一个为 抗菌药使用
	if (data["prep_treatment_drug_usage"]["antibio_usage"]["num"] >= 1) {
		prep_treatment_drug_usage_change++;
		$(".prep_treatment_drug_usage_arr").append("&nbsp; &nbsp; &nbsp;" + prep_treatment_drug_usage_change + ".&nbsp; 抗菌药使用");
		if (data["prep_treatment_drug_usage"]["antibio_usage"]["num"]["id_0"] != "") {
			for (var j = 0; j < data["prep_treatment_drug_usage"]["antibio_usage"]["id_0"].length; j++) {
				$(".prep_treatment_drug_usage_arr").append("<div class='pathway_tl2 prep_treatment_drug_usage_" + j + "'>" + ' &nbsp; ' + prep_treatment_drug_usage_change + '.' + [j + 1] + '.' + '&nbsp; ' + data["prep_treatment_drug_usage"]["antibio_usage"]["id_0"][j] + "</div>"
				);
			}
		} else {}
	} else {
	
	}
	// 第二节麻醉药的使用
	if (data["prep_treatment_drug_usage"]["anaesthetic_usage"]["num"] >= 1) {
		prep_treatment_drug_usage_change++;
		$(".prep_treatment_drug_usage_arr").append("&nbsp; &nbsp; &nbsp;" + prep_treatment_drug_usage_change + ".&nbsp; 麻醉药使用");
		if (data["prep_treatment_drug_usage"]["anaesthetic_usage"]["num"]["id_0"] != "") {
			for (var j = 0; j < data["prep_treatment_drug_usage"]["anaesthetic_usage"]["id_0"].length; j++) {
				$(".prep_treatment_drug_usage_arr").append("<div class='pathway_tl2 prep_treatment_drug_usage_" + j + "'>" + ' &nbsp; ' + prep_treatment_drug_usage_change + '.' + [j + 1] + '.' + '&nbsp; ' + data["prep_treatment_drug_usage"]["anaesthetic_usage"]["id_0"][j] + "</div>"
				);
			}
		} else {}
	} else {
	
	}
	
// 第三节其他药物的使用
	if (data["prep_treatment_drug_usage"]["otherdrugs_usage"]["num"] >= 1) {
		prep_treatment_drug_usage_change++;
		$(".prep_treatment_drug_usage_arr").append("&nbsp; &nbsp; &nbsp;" + prep_treatment_drug_usage_change + ".&nbsp; 其他药物使用");
		if (data["prep_treatment_drug_usage"]["otherdrugs_usage"]["num"]["id_0"] != "") {
			for (var j = 0; j < data["prep_treatment_drug_usage"]["otherdrugs_usage"]["id_0"].length; j++) {
				$(".prep_treatment_drug_usage_arr").append("<div class='pathway_tl2 prep_treatment_drug_usage_" + j + "'>" + ' &nbsp; ' + prep_treatment_drug_usage_change + '.' + [j + 1] + '.' + '&nbsp; ' + data["prep_treatment_drug_usage"]["otherdrugs_usage"]["id_0"][j] + "</div>"
				);
			}
		} else {}
	} else {
	
	}
	
	// 第七部分 prep_treatment_extension 手术前额外准备工作
	var prep_treatment_extension = 0;
	if (data["prep_treatment_extension"]["num"] >= 1) {
		var num = data["prep_treatment_extension"]["num"];
		for (var i = 0; i < num; i++) {
			prep_treatment_extension++;
			$(".prep_treatment_extension_arr").append("&nbsp; &nbsp; &nbsp;" + prep_treatment_extension + ".&nbsp; " + data[
				"prep_treatment_extension"]["id_" + i]["content"]);
			for (var j = 0; j < data["prep_treatment_extension"]["id_" + i]["content_item"].length; j++) {
				$(".prep_treatment_extension_arr").append("<div class='pathway_tl2 prep_treatment_common_" + j + "'>" + ' &nbsp; ' +
					prep_treatment_extension + '.' + [j + 1] + '.' + '&nbsp; ' + data["prep_treatment_extension"]["id_" + i]["content_item"][j] + "</div>"
				);
			}
		}
	} else {

	}

	// 第九部分 drug_usage $(type)时的药物使用 在treatment内
	var treatment = 0,
		treatment_change = 0,
		drug_usage = 0;
		if (data["drug_usage"]["antibio_usage"]["num"] != 0 || data["drug_usage"]["anaesthetic_usage"]["num"] != 0 || data["drug_usage"]["otherdrugs_usage"]["num"] != 0) {
			drug_usage++;
			$(".treatment_arr").append("&nbsp; &nbsp; &nbsp;" + drug_usage + ".&nbsp; " + data["type"] + "时的药物使用");
			
			// 第一个为 抗菌药使用
			if (data["drug_usage"]["antibio_usage"]["num"] >= 1) {
				treatment_change++;
				treatment++;
				$(".treatment_arr").append("<div>" + "&nbsp; &nbsp; &nbsp; &nbsp;" + treatment_change + '.' + treatment + ".&nbsp; " +data["type"] + "时抗菌药使用" + "</div>");
				if (data["drug_usage"]["antibio_usage"]["num"]["id_0"] != "") {
		
					for (var j = 0; j < data["drug_usage"]["antibio_usage"]["id_0"].length; j++) {
						$(".treatment_arr").append("<div class='pathway_tl3 drug_usage" + j + "'>" + ' &nbsp;' + treatment_change + '.' + treatment + '.' + [j + 1] + '.' + '&nbsp; ' + data["drug_usage"]["antibio_usage"]["id_0"][j] + "</div>"
						);
					}
				} else {}
			} else {
		
			}
		
			// 第二个为麻醉药使用情况
			if (data["drug_usage"]["anaesthetic_usage"]["num"] >= 1) {
				treatment_change++;
				treatment++;
				$(".treatment_arr").append("<div>" + "&nbsp; &nbsp; &nbsp; &nbsp;" + treatment_change + '.' + treatment + ".&nbsp; " +
					data["type"] + "时麻醉药使用" + "</div>");
				if (data["drug_usage"]["anaesthetic_usage"]["num"]["id_0"] != "") {
		
					for (var j = 0; j < data["drug_usage"]["anaesthetic_usage"]["id_0"].length; j++) {
						$(".treatment_arr").append("<div class='pathway_tl3 drug_usage" + j + "'>" + ' &nbsp;' + treatment_change + '.' + treatment + '.' + [j + 1] + '.' + '&nbsp; ' + data["drug_usage"]["anaesthetic_usage"]["id_0"][j] + "</div>"
						);
					}
				} else {}
			} else {
		
			}
		
			// 第三个为其他药物的使用情况
			if (data["drug_usage"]["otherdrugs_usage"]["num"] >= 1) {
				treatment_change++;
				treatment++;
				$(".treatment_arr").append("<div>" + "&nbsp; &nbsp; &nbsp; &nbsp;" + treatment_change + '.' + treatment + ".&nbsp; " +
					data["type"] + "其他药物使用" + "</div>");
				if (data["drug_usage"]["otherdrugs_usage"]["num"]["id_0"] != "") {
					for (var j = 0; j < data["drug_usage"]["otherdrugs_usage"]["id_0"].length; j++) {
						$(".treatment_arr").append("<div class='pathway_tl3 drug_usage" + j + "'>" + ' &nbsp;' + treatment_change + '.' + treatment + '.' + [j + 1] + '.' + '&nbsp; ' + data["drug_usage"]["otherdrugs_usage"]["id_0"][j] + "</div>"
						);
					}
				} else {}
			} else {
		
			}
		
		} else {
		
		}
		
	// 第八部分 treatment $(type)手术日/时
	if (data["treatment"]["duration"]["max"] > 0) {
		$('h3[data-key="treatment_name"]').text(data["type"] + '日为：入院' + data_transfer(data["treatment"]["duration"]["min"],data["treatment"]["duration"]["max"]));
	}else if(data["treatment"]["scenario"]["scenario_num"] >= 1 || data["drug_usage"]["antibio_usage"]["num"] >= 1 || data["drug_usage"]["anaesthetic_usage"]["num"] >= 1 || data["drug_usage"]["otherdrugs_usage"]["num"] >= 1){
		if(data["treatment"]["scenario"]["scenario_id_0"]["obligatory_exam"] == "" && data["treatment"]["scenario"]["scenario_id_0"]["optional_exam"] == "" && data["treatment"]["scenario"]["scenario_id_0"]["notification"] == "" && data["treatment"]["scenario"]["scenario_id_0"]["treatment_plan"]["ref"] == "" && data["treatment"]["scenario"]["scenario_id_0"]["treatment_plan"]["num"] <= 0){
			
		}else{
			$('h3[data-key="treatment_name"]').text(data["type"] + '时');
		}
	}
	if (data["treatment"]["scenario"]["scenario_num"] >= 1) {
		var num = data["treatment"]["scenario"]["scenario_num"];
		for (var i = 0; i < num; i++) {
			treatment = drug_usage;

			// ref 依据
			if (data["treatment"]["scenario"]["scenario_id_" + i]["treatment_plan"]["ref"] != "") {
				$(".treatment_arr").append("<div class='pathway_font'>" + data["treatment"]["scenario"]["scenario_id_" + i]["treatment_plan"]["ref"] + "</div>");
			} else {

			}
			if (data["treatment"]["scenario"]["scenario_id_" + i]["treatment_plan"]["num"] >= 1) {
				treatment_change++;
				var num2 = data["treatment"]["scenario"]["scenario_id_" + i]["treatment_plan"]["num"];
				treatment++;
				$(".treatment_arr").append("<div class='pathway_font'>" + treatment + '.&nbsp; ' + data["type"] + '时治疗方案' +
					"</div>");
					for (var j = 0; j < num2; j++) {
						// id_i下的content的小标题
						if (data["treatment"]["scenario"]["scenario_id_" + i]["treatment_plan"]["id_" + j]["content"] != "") {
							$(".treatment_arr").append("<div class='pathway_tl2'>" + '&nbsp; ' + treatment + '.' + [j + 1] + '.&nbsp; ' + data["treatment"]["scenario"]["scenario_id_" + i]["treatment_plan"]["id_" + j]["content"] + "</div>")
						} else {

						}
						// id_i下的content_item的数组
						if (data["treatment"]["scenario"]["scenario_id_" + i]["treatment_plan"]["id_" + j]["content_item"] != "") {

							for (var k = 0; k < data["treatment"]["scenario"]["scenario_id_" + i]["treatment_plan"]["id_" + j]["content_item"]
								.length; k++) {
								$(".treatment_arr").append("<div class='pathway_tl3'>" + '&nbsp;' + treatment + '.' + [j + 1] + '.' + [k + 1] +'.&nbsp; ' + data["treatment"]["scenario"]["scenario_id_" + i]["treatment_plan"]["id_" + j]["content_item"][k] + "</div>")
							}
						} else {

						}
					}
				} else {
				
				}
				// 必须的检查项目
				if (data["treatment"]["scenario"]["scenario_id_" + i]["obligatory_exam"] != "") {
					
					treatment++;
					$(".treatment_arr").append("<div>" + "&nbsp; &nbsp; &nbsp;" + treatment + ".&nbsp; 必须的检查项目" + "</div>");

					for (var j = 0; j < data["treatment"]["scenario"]["scenario_id_" + i]["obligatory_exam"].length; j++) {
						$(".treatment_arr").append("<div class='pathway_tl2 treatment_arr_" + j + "'>" + ' &nbsp; ' + treatment + '.' + [j + 1] + '.' + '&nbsp; ' + data["treatment"]["scenario"]["scenario_id_" + i]["obligatory_exam"][j] + "</div>");
					}
				} else {

				}
				// 根据病情可选的检查项目
				if (data["treatment"]["scenario"]["scenario_id_" + i]["optional_exam"] != "") {
					treatment++;
					$(".treatment_arr").append("<div>" + "&nbsp; &nbsp; &nbsp;" + treatment + ".&nbsp; 根据病情可选的检查项目" + "</div>");

					for (var j = 0; j < data["treatment"]["scenario"]["scenario_id_" + i]["optional_exam"].length; j++) {

						$(".treatment_arr").append("<div class='pathway_tl2 treatment_arr_" + j + "'>" + ' &nbsp; ' + treatment + '.' + [j + 1] + '.' + '&nbsp; ' + data["treatment"]["scenario"]["scenario_id_" + i]["optional_exam"][j] + "</div>");
					}
				} else {

				}
				// 必要的告知信息
				if (data["treatment"]["scenario"]["scenario_id_" + i]["notification"] != "") {
					treatment++;
					$(".treatment_arr").append("<div>" + "&nbsp; &nbsp; &nbsp;" + treatment + ".&nbsp; 必要的告知信息" + "</div>");
					for (var j = 0; j < data["treatment"]["scenario"]["scenario_id_" + i]["notification"].length; j++) {

						$(".treatment_arr").append("<div class='pathway_tl2 treatment_arr_" + j + "'>" + ' &nbsp; ' + treatment + '.' + [j + 1] + '.' + '&nbsp; ' + data["treatment"]["scenario"]["scenario_id_" + i]["notification"][j] + "</div>");

					}
				} else {

				}
		}
	} else {}

	// 第十部分 after_medical_treatment $(type)后住院恢复
	console.log(data["after_medical_treatment"])
	console.log(data["after_treatment_drug_usage"])
	if (data["after_medical_treatment"]["duration"]["max"] > 0) {
		if(data["type"] == "化疗"){
			$('h3[data-key="after_medical_treatment_name"]').text(data["type"] + '以及'+ data["type"] + '后治疗：' + data_transfer(data["after_medical_treatment"]["duration"]["min"],data["after_medical_treatment"]["duration"]["max"]));
		}else{
			$('h3[data-key="after_medical_treatment_name"]').text(data["type"] + '后住院恢复：' + data_transfer(data["after_medical_treatment"]["duration"]["min"],data["after_medical_treatment"]["duration"]["max"]));
		}
	}
	else if (data["after_medical_treatment"]["scenario"]["num"] >= 1 || data["after_treatment_drug_usage"]["antibio_usage"]["num"] >= 1 || data["after_treatment_drug_usage"]["anaesthetic_usage"]["num"] >= 1 || data["after_treatment_drug_usage"]["otherdrugs_usage"]["num"] >= 1) {
		if(data["type"] == "化疗"){
			$('h3[data-key="after_medical_treatment_name"]').text(data["type"] + '以及'+ data["type"] + '后治疗：');
		}else{
			$('h3[data-key="after_medical_treatment_name"]').text(data["type"] + '后住院恢复：');
		}
	}
	var after_medical_treatment_change = 0;
	if (data["after_medical_treatment"]["scenario"]["num"] >= 1) {
		var num = data["after_medical_treatment"]["scenario"]["num"];
		for (var i = 0; i < num; i++) {
			// 第一个为 必须的检查项目
			after_medical_treatment_change = 0;
			if (data["after_medical_treatment"]["scenario"]["id_" + i]["obligatory_exam"] != "") {
				after_medical_treatment_change++;
				$(".after_medical_treatment_arr").append("&nbsp; &nbsp; &nbsp;" + after_medical_treatment_change +".&nbsp; 必须的检查项目");
				for (var j = 0; j < data["after_medical_treatment"]["scenario"]["id_" + i]["obligatory_exam"].length; j++) {
					$(".after_medical_treatment_arr").append("<div class='pathway_tl2 after_medical_treatment_" + j + "'>" +' &nbsp; ' + after_medical_treatment_change + '.' + [j + 1] + '.' + '&nbsp; ' + data["after_medical_treatment"]["scenario"]["id_" + i]["obligatory_exam"][j] + "</div>"
					);
				}
			} else {

			}
			//第二个为根据患者病情可选择复查部分检查项目
			if (data["after_medical_treatment"]["scenario"]["id_" + i]["optional_exam"] != "") {
				after_medical_treatment_change++;
				$(".after_medical_treatment_arr").append("&nbsp; &nbsp; &nbsp;" + after_medical_treatment_change +".&nbsp; 根据患者病情可选择复查部分检查项目");
				for (var j = 0; j < data["after_medical_treatment"]["scenario"]["id_" + i]["optional_exam"].length; j++) {
					$(".after_medical_treatment_arr").append("<div class='pathway_tl2 after_medical_treatment_" + j + "'>" +' &nbsp; ' + after_medical_treatment_change + '.' + [j + 1] + '.' + '&nbsp; ' + data["after_medical_treatment"]["scenario"]["id_" + i]["optional_exam"][j] + "</div>"
					);
				}
			} else {

			}
			//第三个为术后恢复需要的治疗项目
			if (data["after_medical_treatment"]["scenario"]["id_" + i]["recovery_plan"] != "") {
				after_medical_treatment_change++;
				$(".after_medical_treatment_arr").append("&nbsp; &nbsp; &nbsp;" + after_medical_treatment_change +".&nbsp; 术后恢复需要的治疗项目");
				for (var j = 0; j < data["after_medical_treatment"]["scenario"]["id_" + i]["recovery_plan"].length; j++) {
					$(".after_medical_treatment_arr").append("<div class='pathway_tl2 after_medical_treatment_" + j + "'>" +' &nbsp; ' + after_medical_treatment_change + '.' + [j + 1] + '.' + '&nbsp; ' + data["after_medical_treatment"]["scenario"]["id_" + i]["recovery_plan"][j] + "</div>"
					);
				}
			} else {

			}
			
			// 第十一部分 after_treatment_drug_usage  $(type)后药物使用
			// 第一个为 抗菌药使用
			if (data["after_treatment_drug_usage"]["antibio_usage"]["num"] >= 1) {
				after_medical_treatment_change++;
				$(".after_medical_treatment_arr").append("&nbsp; &nbsp; &nbsp;" + after_medical_treatment_change + ".&nbsp; 抗菌药使用");
				if (data["after_treatment_drug_usage"]["antibio_usage"]["num"]["id_0"] != "") {
					for (var j = 0; j < data["after_treatment_drug_usage"]["antibio_usage"]["id_0"].length; j++) {
						$(".after_medical_treatment_arr").append("<div class='pathway_tl2 after_treatment_drug_usage_" + j + "'>" +' &nbsp; ' + after_medical_treatment_change + '.' + [j + 1] + '.' + '&nbsp; ' + data["after_treatment_drug_usage"]["antibio_usage"]["id_0"][j] + "</div>"
						);
					}
				} else {}
			} else {
			
			}
			// 第二个为麻醉药物的使用
			if (data["after_treatment_drug_usage"]["anaesthetic_usage"]["num"] >= 1) {
				after_medical_treatment_change++;
				if (data["after_treatment_drug_usage"]["anaesthetic_usage"]["num"]["id_0"] != "") {
					$(".after_medical_treatment_arr").append("&nbsp; &nbsp; &nbsp;" + after_medical_treatment_change +".&nbsp; 麻醉药物的使用");
					for (var j = 0; j < data["after_treatment_drug_usage"]["anaesthetic_usage"]["id_0"].length; j++) {
						$(".after_medical_treatment_arr").append("<div class='pathway_tl2 after_treatment_drug_usage_" + j + "'>" +' &nbsp; ' + after_medical_treatment_change + '.' + [j + 1] + '.' + '&nbsp; ' + data["after_treatment_drug_usage"]["anaesthetic_usage"]["id_0"][j] + "</div>"
						);
					}
				} else {
				
				}
			} else {
				
			}
			// 第三个为其他药物的使用
			if (data["after_treatment_drug_usage"]["otherdrugs_usage"]["num"] >= 1) {
				after_medical_treatment_change++;
				if (data["after_treatment_drug_usage"]["otherdrugs_usage"]["num"]["id_0"] != "") {
					$(".after_medical_treatment_arr").append("&nbsp; &nbsp; &nbsp;" + after_medical_treatment_change +".&nbsp; 其他药物的使用");
					for (var j = 0; j < data["after_treatment_drug_usage"]["otherdrugs_usage"]["id_0"].length; j++) {
						$(".after_medical_treatment_arr").append("<div class='pathway_tl2 after_treatment_drug_usage_" + j + "'>" +' &nbsp; ' + after_medical_treatment_change + '.' + [j + 1] + '.' + '&nbsp; ' + data["after_treatment_drug_usage"]["otherdrugs_usage"]["id_0"][j] + "</div>"
						);
					}
				} else {
			
				}
			} else {
			
			}
		}
	} else {
		// 第十一部分 after_treatment_drug_usage $(type)后药物使用重复2
		// 第一个为 抗菌药使用
		if (data["after_treatment_drug_usage"]["antibio_usage"]["num"] >= 1) {
			after_medical_treatment_change++;
			$(".after_medical_treatment_arr").append("&nbsp; &nbsp; &nbsp;" + after_medical_treatment_change + ".&nbsp; 抗菌药使用");
			if (data["after_treatment_drug_usage"]["antibio_usage"]["num"]["id_0"] != "") {
				for (var j = 0; j < data["after_treatment_drug_usage"]["antibio_usage"]["id_0"].length; j++) {
					$(".after_medical_treatment_arr").append("<div class='pathway_tl2 after_treatment_drug_usage_" + j + "'>" +' &nbsp; ' + after_medical_treatment_change + '.' + [j + 1] + '.' + '&nbsp; ' + data["after_treatment_drug_usage"]["antibio_usage"]["id_0"][j] + "</div>"
					);
				}
			} else {}
		} else {

		}
		// 第二个为麻醉药物的使用
		if (data["after_treatment_drug_usage"]["anaesthetic_usage"]["num"] >= 1) {
			after_medical_treatment_change++;
			if (data["after_treatment_drug_usage"]["anaesthetic_usage"]["num"]["id_0"] != "") {
				$(".after_medical_treatment_arr").append("&nbsp; &nbsp; &nbsp;" + after_medical_treatment_change +".&nbsp; 麻醉药物的使用");
				for (var j = 0; j < data["after_treatment_drug_usage"]["anaesthetic_usage"]["id_0"].length; j++) {
					$(".after_medical_treatment_arr").append("<div class='pathway_tl2 after_treatment_drug_usage_" + j + "'>" +' &nbsp; ' + after_medical_treatment_change + '.' + [j + 1] + '.' + '&nbsp; ' + data["after_treatment_drug_usage"]["anaesthetic_usage"]["id_0"][j] + "</div>"
					);
				}
			} else {

			}
		} else {

		}
		// 第三个为其他药物的使用
		if (data["after_treatment_drug_usage"]["otherdrugs_usage"]["num"] >= 1) {
			after_medical_treatment_change++;
			if (data["after_treatment_drug_usage"]["otherdrugs_usage"]["num"]["id_0"] != "") {
				$(".after_medical_treatment_arr").append("&nbsp; &nbsp; &nbsp;" + after_medical_treatment_change +".&nbsp; 其他药物的使用");
				for (var j = 0; j < data["after_treatment_drug_usage"]["otherdrugs_usage"]["id_0"].length; j++) {
					$(".after_medical_treatment_arr").append("<div class='pathway_tl2 after_treatment_drug_usage_" + j + "'>" +' &nbsp; ' + after_medical_treatment_change + '.' + [j + 1] + '.' + '&nbsp; ' + data["after_treatment_drug_usage"]["otherdrugs_usage"]["id_0"][j] + "</div>"
					);
				}
			} else {

			}
		} else {

		}
	}

	// 第十二部份 discharge_criteria 出院标准
	var discharge_criteria_arr = "";
	for (var j = 0; j < data["discharge_criteria"].length; j++) {
		discharge_criteria_arr += `<li>
			${data["discharge_criteria"][j]}
		</li>`;
	}
	$(".discharge_criteria_tl").append(
		"<ol class='arr_ol'>" + discharge_criteria_arr + "</ol>"
	);
	// 第十三部份 other_notice 变异及其分析
	var other_notice_arr = "";
	for (var j = 0; j < data["other_notice"].length; j++) {
		other_notice_arr += `<li>
			${data["other_notice"][j]}
		</li>`;
	}
	$(".other_notice_tl").append(
		"<ol class='arr_ol'>" + other_notice_arr + "</ol>"
	);

	//自己添加的三级标题项处理
	var title = {
		"suitable_subject_disc_name": "适用对象",
		"diagnosis_name": "诊断依据",
		"prep_treatment_extension_name": "前额外准备工作",
		"treatment_choice_name": "治疗方案",
		"treatment_entry_standard_name": "进入路径标准",
		"prep_treatment_drug_usage_name": "药物选择与使用时期",
		"discharge_criteria_name": "出院标准",
		"other_notice_name": "变异及原因分析"
	}

	// 适用对象标题判断
	if (data["suitable_subject_disc"] != "") {
		$('h3[data-key="suitable_subject_disc_name"]').append("<div>" + title["suitable_subject_disc_name"] + "</div>");
	} else {

	}
	// 诊断依据判断
	if (data["diagnosis"] != "") {
		$('h3[data-key="diagnosis_name"]').append("<div>" + title["diagnosis_name"] + "</div>");
	} else {

	}
	// 治疗方案的选择判断
	if (data["treatment_choice"]["ref"] != "" || data["treatment_choice"]["scenario"]["num"] != 0) {
		$('h3[data-key="treatment_choice_name"]').append("<div>" + title["treatment_choice_name"] + "</div>");
	} else {

	}
	// 进入路径标准判断
	if (data["treatment_entry_standard"]["num"] != 0) {
		$('h3[data-key="treatment_entry_standard_name"]').append("<div>" + title["treatment_entry_standard_name"] + "</div>");
	} else {

	}

	// 药物选择与使用时期判断
	if(data["drug_use_period"] == 1){
		if (data["prep_treatment_drug_usage"]["antibio_usage"]["num"] != 0 || data["prep_treatment_drug_usage"]["anaesthetic_usage"]["num"] != 0 || data["prep_treatment_drug_usage"]["otherdrugs_usage"]["num"] != 0) {
			$('h3[data-key="prep_treatment_drug_usage_name"]').append("<div>" + '术前' + title["prep_treatment_drug_usage_name"] + "</div>");
		} else {
			
		}
	}else{
		if (data["prep_treatment_drug_usage"]["antibio_usage"]["num"] != 0 || data["prep_treatment_drug_usage"]["anaesthetic_usage"]["num"] != 0 || data["prep_treatment_drug_usage"]["otherdrugs_usage"]["num"] != 0) {
			$('h3[data-key="prep_treatment_drug_usage_name"]').append("<div>" + title["prep_treatment_drug_usage_name"] + "</div>");
		} else {
			
		}
	}
		
	// 前额外准备工作
	if (data["prep_treatment_extension"]["num"] != 0) {
		$('h3[data-key="prep_treatment_extension_name"]').append("<div>" + data["type"] + title[
			"prep_treatment_extension_name"] + "</div>");
	} else {

	}

	// 出院标准判断
	if (data["discharge_criteria"] != "") {
		$('h3[data-key="discharge_criteria_name"]').append("<div>" + title["discharge_criteria_name"] + "</div>");
	} else {

	}

	// 变异及原因分析判断
	if (data["other_notice"] != "") {
		$('h3[data-key="other_notice_name"]').append("<div>" + title["other_notice_name"] + "</div>");
	} else {

	}
	// 编辑按钮添加
	var write = "编辑";
	var h3 = $("h3");
	var a = $(".pathway_tl_a");
	var split = "";
	for (var i = 0; i < h3.length; i++) {
		split = h3.eq(i).text().split("：")[0];
		// a标签内容的添加
// 		if (h3.eq(i).text() != "") {
// 			a.eq(i).text(write);
// 		} else {
// 			a.eq(i).text("");
// 		}
		// 导航内li内容的添加
		if(h3.eq(i).text() != ""){
			$(".pathway_cbl").append("<li class='pathway_cbl_li'><span class='pathway_cbl_span'>"+split+"</span></li>");
		}
		else{
			$(".pathway_cbl").eq(i).append();
		}
		
	}
	
	
$(function(){
	// 主题目录点击跳转
	$(".pathway_nav>.pathway_cbl>li").click(function() {
		$(window).scrollTop($(".content>div:eq(" + ($(this).index() + 1) + ")").offset().top);
	});
	// 右下角点击跳转
	$(".pathway_navs>.pathway_cbl>li").click(function() {
		$(window).scrollTop($(".content>div:eq(" + ($(this).index() + 1) + ")").offset().top);
	});
	
});
}