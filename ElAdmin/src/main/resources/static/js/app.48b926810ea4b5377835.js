webpackJsonp([1],{"0H6K":function(e,t){},"4WSK":function(e,t){},LKZD:function(e,t){},NHnr:function(e,t,r){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var a=r("xd7I"),i={render:function(){var e=this.$createElement,t=this._self._c||e;return t("div",{staticStyle:{height:"100%"},attrs:{id:"app"}},[t("router-view")],1)},staticRenderFns:[]};var n=r("C7Lr")({name:"App"},i,!1,function(e){r("0H6K")},null,null).exports,s=r("ZWLj"),o=r("3cXf"),l=r.n(o),c=r("HzJ8"),u=r.n(c),d=r("cUn4"),m=r.n(d),p=(r("7Adc"),{name:"fill",data:function(){return{cp_id:"",status:"",form_manager:"王果果呦",form_name:"",suitable_subject_disc:"",standard_entry_days:"天",options:[{value:"天",label:"天"},{value:"时",label:"时"},{value:"周",label:"周"},{value:"月",label:"月"}],options2:[{value:"1",label:"低危"},{value:"2",label:"中危"},{value:"3",label:"低中危"},{value:"4",label:"高危"},{value:"5",label:"中高危"},{value:"6",label:"特高危"},{value:"0",label:"无定义"}],severity_level:"",timeMin:"0",timeMax:"0",stage:[{currentMin:"0",currentMax:"0",currentUnit:"天",primary_diagnosi:[],key_orders:{long_term_orders:[],temporary_orders:[],discharge_orders:[],notification:[]},major_nursing_work:[],disease_variation_status:"0",reason:[]}]}},methods:{Contrast:function(e,t){parseInt(t)<parseInt(e)&&(this.timeMax=this.timeMin)},ContrastMax:function(e,t){parseInt(t)<parseInt(e)&&(this.timeMax=this.timeMin)},WatchNumber:function(e,t){e<0&&(this.timeMax="",this.timeMin="")},contrast:function(e,t,r){parseInt(t)<parseInt(e)&&(this.stage[r].currentMax=this.stage[r].currentMin)},contrastMax:function(e,t,r){parseInt(t)<parseInt(e)&&(this.stage[r].currentMax=this.stage[r].currentMin)},watchNumber:function(e,t){e<0&&(this.stage[t].currentMax="",this.stage[t].currentMin="")},addStage:function(){this.stage.push({currentMin:"0",currentMax:"0",currentUnit:"天",primary_diagnosi:[],key_orders:{long_term_orders:[],temporary_orders:[],discharge_orders:[],notification:[]},major_nursing_work:[],disease_variation_status:"0",reason:[]})},remove:function(e){this.stage.splice(e,1)},replacewang:function(e){var t="";this.stage[e].primary_diagnosi.replace(/\s/g,"").split("□").filter(function(e){return""!=e}).forEach(function(e,r){"1"==e.substring(0,1)&&e.split(".").forEach(function(t,r){console.log(e),console.log(t)}),""!=e&&(t=t+(r+1)+"."+e.substring(e.indexOf(".")+1)+"\n")}),this.stage[e].primary_diagnosi=t},replace:function(e){var t=new m.a,r=this.stage[e].primary_diagnosi.replace(/[\r\n]/g,",").replace(/\s/g,"").replace(/\□/g,"").split(","),a="";(r=r.filter(function(e){return""!=r})).forEach(function(e,r){var a=e.indexOf("^");e.slice(0,a).length<=2&&(e=e.substring(a+1)),t.set(r+1,e)});var i=!0,n=!1,s=void 0;try{for(var o,l=u()(t.keys());!(i=(o=l.next()).done);i=!0){var c=o.value;a=a+c+"^"+t.get(c)+"\n"}}catch(e){n=!0,s=e}finally{try{!i&&l.return&&l.return()}finally{if(n)throw s}}this.stage[e].primary_diagnosi=a},replace2:function(e){var t=new m.a,r=this.stage[e].key_orders.long_term_orders.replace(/[\r\n]/g,",").replace(/\s/g,"").replace(/\□/g,"").split(","),a="";(r=r.filter(function(e){return""!=r})).forEach(function(e,r){var a=e.indexOf("^");e.slice(0,a).length<=2&&(e=e.substring(a+1)),t.set(r+1,e)});var i=!0,n=!1,s=void 0;try{for(var o,l=u()(t.keys());!(i=(o=l.next()).done);i=!0){var c=o.value;a=a+c+"^"+t.get(c)+"\n"}}catch(e){n=!0,s=e}finally{try{!i&&l.return&&l.return()}finally{if(n)throw s}}this.stage[e].key_orders.long_term_orders=a},replace3:function(e){var t=new m.a,r="";this.stage[e].key_orders.temporary_orders.replace(/[\r\n]/g,",").replace(/\s/g,"").replace(/\□/g,"").split(",").forEach(function(e,r){var a=e.indexOf("^");e.slice(0,a).length<=2&&(e=e.substring(a+1)),t.set(r+1,e)});var a=!0,i=!1,n=void 0;try{for(var s,o=u()(t.keys());!(a=(s=o.next()).done);a=!0){var l=s.value;r=r+l+"^"+t.get(l)+"\n"}}catch(e){i=!0,n=e}finally{try{!a&&o.return&&o.return()}finally{if(i)throw n}}this.stage[e].key_orders.temporary_orders=r},replace4:function(e){var t=new m.a,r="";this.stage[e].key_orders.discharge_orders.replace(/[\r\n]/g,",").replace(/\s/g,"").replace(/\□/g,"").split(",").forEach(function(e,r){var a=e.indexOf("^");e.slice(0,a).length<=2&&(e=e.substring(a+1)),t.set(r+1,e)});var a=!0,i=!1,n=void 0;try{for(var s,o=u()(t.keys());!(a=(s=o.next()).done);a=!0){var l=s.value;r=r+l+"^"+t.get(l)+"\n"}}catch(e){i=!0,n=e}finally{try{!a&&o.return&&o.return()}finally{if(i)throw n}}this.stage[e].key_orders.discharge_orders=r},replace5:function(e){var t=new m.a,r="";this.stage[e].key_orders.notification.replace(/[\r\n]/g,",").replace(/\s/g,"").replace(/\□/g,"").split(",").forEach(function(e,r){var a=e.indexOf("^");e.slice(0,a).length<=2&&(e=e.substring(a+1)),t.set(r+1,e)});var a=!0,i=!1,n=void 0;try{for(var s,o=u()(t.keys());!(a=(s=o.next()).done);a=!0){var l=s.value;r=r+l+"^"+t.get(l)+"\n"}}catch(e){i=!0,n=e}finally{try{!a&&o.return&&o.return()}finally{if(i)throw n}}this.stage[e].key_orders.notification=r},replace6:function(e){var t=new m.a,r="";this.stage[e].major_nursing_work.replace(/[\r\n]/g,",").replace(/\s/g,"").replace(/\□/g,"").split(",").forEach(function(e,r){var a=e.indexOf("^");e.slice(0,a).length<=2&&(e=e.substring(a+1)),t.set(r+1,e)});var a=!0,i=!1,n=void 0;try{for(var s,o=u()(t.keys());!(a=(s=o.next()).done);a=!0){var l=s.value;r=r+l+"^"+t.get(l)+"\n"}}catch(e){i=!0,n=e}finally{try{!a&&o.return&&o.return()}finally{if(i)throw n}}this.stage[e].major_nursing_work=r},replace7:function(e){var t=new m.a,r="";this.stage[e].reason.replace(/[\r\n]/g,",").replace(/\s/g,"").replace(/\□/g,"").split(",").forEach(function(e,r){var a=e.indexOf("^");e.slice(0,a).length<=2&&(e=e.substring(a+1)),t.set(r+1,e)});var a=!0,i=!1,n=void 0;try{for(var s,o=u()(t.keys());!(a=(s=o.next()).done);a=!0){var l=s.value;r=r+l+"^"+t.get(l)+"\n"}}catch(e){i=!0,n=e}finally{try{!a&&o.return&&o.return()}finally{if(i)throw n}}this.stage[e].reason=r},select:function(){var e=this;""==this.cp_id?this.$message({showClose:!0,message:"请输入正确的ID号",type:"error"}):this.$axios.get("http://192.168.50.78:8001/getExamFormDataById",{params:{cp_id:this.cp_id}}).then(function(t){if(e.status=t.data.status,404==e.status)return e.$message({showClose:!0,message:"请输入正确的ID号",type:"error"}),e.form_name="",e.severity_level="",e.suitable_subject_disc="",e.standard_entry_days="",e.timeMax="",e.timeMin="",void(e.stage=[{currentMin:"0",currentMax:"0",currentUnit:"天",options2:[{value:"天",label:"天"},{value:"时",label:"时"},{value:"周",label:"周"},{value:"月",label:"月"}],primary_diagnosi:[],key_orders:{long_term_orders:[],temporary_orders:[],discharge_orders:[],notification:[]},major_nursing_work:[],disease_variation_status:"",reason:[]}]);e.form_name=t.data.form_name,e.severity_level=t.data.severity_level,e.suitable_subject_disc=t.data.suitable_subject_disc,e.standard_entry_days=t.data.standard_entry_days,e.timeMax=t.data.timeMax,e.timeMin=t.data.timeMin,e.stage=t.data.stage,t.data.stage.forEach(function(t,r){e.stage[r].primary_diagnosi=e.repeat(t.primary_diagnosi),e.stage[r].major_nursing_work=e.repeat(t.major_nursing_work),e.stage[r].reason=e.repeat(t.reason),e.stage[r].key_orders.discharge_orders=e.repeat(t.key_orders.discharge_orders),e.stage[r].key_orders.long_term_orders=e.repeat(t.key_orders.long_term_orders),e.stage[r].key_orders.notification=e.repeat(t.key_orders.notification),e.stage[r].key_orders.temporary_orders=e.repeat(t.key_orders.temporary_orders)})}).catch(function(e){console.log(e)})},repeat:function(e){var t="";return""==e?t:0!=e.length?(e.forEach(function(e,r){""!=e&&(t=0==r?r+1+"^"+e:t+"\n"+(r+1)+"^"+e)}),t):void 0},timeUnit:function(){var e=this;this.stage.forEach(function(t,r){t.currentUnit=e.standard_entry_days})},btn:function(){var e=this,t=l()(this.stage),r=this.form_name,a=this.cp_id;""==r||""==a?this.$alert("务必填写名字以及ID号","请注意",{confirmButtonText:"确定",callback:function(t){e.$message({type:"info",message:"已取消提交"})}}):this.$confirm("请确认是否提交信息？","确认信息",{distinguishCancelAndClose:!0,confirmButtonText:"确定提交",cancelButtonText:"放弃提交"}).then(function(){e.$axios.get("http://192.168.50.78:8001/operationExamFormData",{params:{cp_id:e.cp_id,form_manager:e.form_manager,form_name:e.form_name,suitable_subject_disc:e.suitable_subject_disc,standard_entry_days:e.standard_entry_days,timeMin:e.timeMin,timeMax:e.timeMax,severity_level:e.severity_level,stage:t}}).then(function(t){e.$message({message:"数据提交成功",type:"success"}),window.location.reload()}).catch(function(t){e.$message.error("数据提交失败~"),console.log(t)})}).catch(function(t){e.$message("数据未提交")})}}}),v={render:function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",{staticStyle:{height:"100%"}},[r("el-container",{staticStyle:{height:"100%"}},[r("el-header",{staticStyle:{height:"185px"}},[r("div",{staticStyle:{display:"flex"}},[r("div",{staticStyle:{width:"80%",display:"inline-block","text-align":"left"}},[r("span",{staticStyle:{"line-height":"44px",display:"inline-block",width:"10%","text-align":"center"}},[e._v("路径名称:")]),e._v(" "),r("input",{directives:[{name:"model",rawName:"v-model",value:e.form_name,expression:"form_name"}],staticClass:"input1",staticStyle:{width:"85%"},attrs:{type:"text"},domProps:{value:e.form_name},on:{input:function(t){t.target.composing||(e.form_name=t.target.value)}}})]),e._v(" "),r("el-select",{staticStyle:{width:"10%"},attrs:{placeholder:"请选择路径的危险级别"},model:{value:e.severity_level,callback:function(t){e.severity_level=t},expression:"severity_level"}},e._l(e.options2,function(e){return r("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})}),1)],1),e._v(" "),r("div",{staticClass:"flex",staticStyle:{"text-align":"left"}},[r("div",{staticStyle:{width:"80%",display:"inline-block","text-align":"left"}},[r("span",{staticStyle:{"line-height":"5%",display:"inline-block",width:"10%","text-align":"center"}},[e._v("适用对象:")]),e._v(" "),r("input",{directives:[{name:"model",rawName:"v-model",value:e.suitable_subject_disc,expression:"suitable_subject_disc"}],staticClass:"input1",staticStyle:{width:"85%"},attrs:{type:"text"},domProps:{value:e.suitable_subject_disc},on:{input:function(t){t.target.composing||(e.suitable_subject_disc=t.target.value)}}})])]),e._v(" "),r("div",{staticStyle:{width:"100%"}},[r("div",{staticStyle:{display:"inline-block",width:"45%"}},[r("span",{staticStyle:{"line-height":"5%",display:"inline-block",width:"100px"}},[e._v("临床路径ID")]),e._v(" "),r("input",{directives:[{name:"model",rawName:"v-model",value:e.cp_id,expression:"cp_id"}],staticStyle:{height:"40px","text-align":"center"},attrs:{type:"number"},domProps:{value:e.cp_id},on:{input:function(t){t.target.composing||(e.cp_id=t.target.value)}}}),e._v(" "),r("el-button",{attrs:{type:"warning",round:"",plain:!0},on:{click:e.select}},[e._v("查询")])],1),e._v(" "),r("div",{staticStyle:{display:"inline-block",width:"50%"}},[r("span",[e._v("请选择标准时间段单位:")]),e._v(" "),r("el-select",{staticStyle:{"min-width":"100px"},on:{change:e.timeUnit},model:{value:e.standard_entry_days,callback:function(t){e.standard_entry_days=t},expression:"standard_entry_days"}},e._l(e.options,function(e){return r("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})}),1),e._v(" "),r("input",{directives:[{name:"model",rawName:"v-model",value:e.timeMin,expression:"timeMin"}],staticStyle:{height:"40px","min-width":"100px","text-align":"center"},attrs:{type:"number",onchange:e.WatchNumber(e.timeMin,e.timeMax)},domProps:{value:e.timeMin},on:{blur:function(t){return e.Contrast(e.timeMin,e.timeMax)},input:function(t){t.target.composing||(e.timeMin=t.target.value)}}}),e._v(" "),r("input",{directives:[{name:"model",rawName:"v-model",value:e.timeMax,expression:"timeMax"}],staticStyle:{height:"40px","min-width":"100px","text-align":"center"},attrs:{type:"number",onchange:e.WatchNumber(e.timeMin,e.timeMax)},domProps:{value:e.timeMax},on:{blur:function(t){return e.ContrastMax(e.timeMin,e.timeMax)},input:function(t){t.target.composing||(e.timeMax=t.target.value)}}})],1)])]),e._v(" "),r("el-main",[r("span",[e._v("添加住院阶段")]),e._v(" "),r("el-button",{attrs:{type:"warning",round:""},on:{click:e.addStage}},[e._v("添加")]),e._v(" "),r("el-button",{attrs:{type:"danger",round:""},on:{click:e.btn}},[e._v("提交保存")]),e._v(" "),r("div",{staticStyle:{color:"red"}},[e._v("请注意呦：word文档中的换行请手动去除，谢谢-笔芯")]),e._v(" "),r("div",{staticClass:"container"},e._l(e.stage,function(t,a){return r("div",{key:a,staticClass:"scene-container"},[r("el-button",{attrs:{type:"info",round:""},on:{click:function(t){return e.remove(a)}}},[e._v("删除")]),e._v(" "),r("span",[e._v("住院第:")]),e._v(" "),r("input",{directives:[{name:"model",rawName:"v-model",value:t.currentMin,expression:"stageItem.currentMin"}],staticStyle:{height:"40px","text-align":"center"},attrs:{type:"number",onchange:e.watchNumber(t.currentMin,a)},domProps:{value:t.currentMin},on:{blur:function(r){return e.contrast(t.currentMin,t.currentMax,a)},input:function(r){r.target.composing||e.$set(t,"currentMin",r.target.value)}}}),e._v(" "),r("input",{directives:[{name:"model",rawName:"v-model",value:t.currentMax,expression:"stageItem.currentMax"}],staticStyle:{height:"40px","text-align":"center"},attrs:{type:"number",onchange:e.watchNumber(t.currentMax,a)},domProps:{value:t.currentMax},on:{blur:function(r){return e.contrastMax(t.currentMin,t.currentMax,a)},input:function(r){r.target.composing||e.$set(t,"currentMax",r.target.value)}}}),e._v(" "),r("span",[r("input",{directives:[{name:"model",rawName:"v-model",value:t.currentUnit,expression:"stageItem.currentUnit"}],staticStyle:{height:"40px","text-align":"center"},attrs:{readonly:!0},domProps:{value:t.currentUnit},on:{input:function(r){r.target.composing||e.$set(t,"currentUnit",r.target.value)}}})]),e._v(" "),r("div",[r("div",{staticClass:"table"},[r("div",{staticClass:"table-sun"},[e._v("主要诊疗工作")]),e._v(" "),r("div",{staticStyle:{width:"calc(100% - 50px)",display:"inline-block"}},[r("textarea",{directives:[{name:"model",rawName:"v-model.trim",value:t.primary_diagnosi,expression:"stageItem.primary_diagnosi",modifiers:{trim:!0}}],staticClass:"textarea",staticStyle:{border:"1px solid #ccc"},attrs:{placeholder:"请输入内容"},domProps:{value:t.primary_diagnosi},on:{change:function(t){return e.replace(a)},input:function(r){r.target.composing||e.$set(t,"primary_diagnosi",r.target.value.trim())},blur:function(t){return e.$forceUpdate()}}})])]),e._v(" "),r("div",{staticClass:"table"},[r("div",{staticClass:"table-sun"},[e._v("重点医嘱")]),e._v(" "),r("div",{staticStyle:{width:"calc(100% - 50px)",display:"block"}},[r("div",[r("p",[e._v("长期医嘱")]),e._v(" "),r("textarea",{directives:[{name:"model",rawName:"v-model.trim",value:t.key_orders.long_term_orders,expression:"stageItem.key_orders.long_term_orders",modifiers:{trim:!0}}],staticClass:"textarea",staticStyle:{border:"1px solid #ccc","min-height":"250px"},attrs:{placeholder:"请输入内容"},domProps:{value:t.key_orders.long_term_orders},on:{change:function(t){return e.replace2(a)},input:function(r){r.target.composing||e.$set(t.key_orders,"long_term_orders",r.target.value.trim())},blur:function(t){return e.$forceUpdate()}}})]),e._v(" "),r("div",[r("p",[e._v("临时医嘱")]),e._v(" "),r("textarea",{directives:[{name:"model",rawName:"v-model.trim",value:t.key_orders.temporary_orders,expression:"stageItem.key_orders.temporary_orders",modifiers:{trim:!0}}],staticClass:"textarea",staticStyle:{border:"1px solid #ccc","min-height":"250px"},attrs:{placeholder:"请输入内容"},domProps:{value:t.key_orders.temporary_orders},on:{change:function(t){return e.replace3(a)},input:function(r){r.target.composing||e.$set(t.key_orders,"temporary_orders",r.target.value.trim())},blur:function(t){return e.$forceUpdate()}}})]),e._v(" "),r("div",[r("p",[e._v("出院医嘱")]),e._v(" "),r("textarea",{directives:[{name:"model",rawName:"v-model.trim",value:t.key_orders.discharge_orders,expression:"stageItem.key_orders.discharge_orders",modifiers:{trim:!0}}],staticClass:"textarea",staticStyle:{border:"1px solid #ccc","min-height":"250px"},attrs:{placeholder:"请输入内容"},domProps:{value:t.key_orders.discharge_orders},on:{change:function(t){return e.replace4(a)},input:function(r){r.target.composing||e.$set(t.key_orders,"discharge_orders",r.target.value.trim())},blur:function(t){return e.$forceUpdate()}}})]),e._v(" "),r("div",[r("p",[e._v("重点注意事项")]),e._v(" "),r("textarea",{directives:[{name:"model",rawName:"v-model.trim",value:t.key_orders.notification,expression:"stageItem.key_orders.notification",modifiers:{trim:!0}}],staticClass:"textarea",staticStyle:{border:"1px solid #ccc","min-height":"250px"},attrs:{placeholder:"请输入内容"},domProps:{value:t.key_orders.notification},on:{change:function(t){return e.replace5(a)},input:function(r){r.target.composing||e.$set(t.key_orders,"notification",r.target.value.trim())},blur:function(t){return e.$forceUpdate()}}})])])]),e._v(" "),r("div",{staticClass:"table"},[r("div",{staticClass:"table-sun"},[e._v("主要护理工作")]),e._v(" "),r("div",{staticStyle:{width:"calc(100% - 50px)",display:"inline-block"}},[r("textarea",{directives:[{name:"model",rawName:"v-model.trim",value:t.major_nursing_work,expression:"stageItem.major_nursing_work",modifiers:{trim:!0}}],staticClass:"textarea",staticStyle:{border:"1px solid #ccc"},attrs:{placeholder:"请输入内容"},domProps:{value:t.major_nursing_work},on:{change:function(t){return e.replace6(a)},input:function(r){r.target.composing||e.$set(t,"major_nursing_work",r.target.value.trim())},blur:function(t){return e.$forceUpdate()}}})])]),e._v(" "),r("div",{staticClass:"table"},[r("div",{staticClass:"table-sun"},[e._v("病情变异记录")]),e._v(" "),r("div",{staticStyle:{width:"calc(100% - 50px)",display:"inline-block"}},[r("div",[r("el-radio",{attrs:{label:"0"},model:{value:t.disease_variation_status,callback:function(r){e.$set(t,"disease_variation_status",r)},expression:"stageItem.disease_variation_status"}},[e._v("无")]),e._v(" "),r("el-radio",{attrs:{label:"1"},model:{value:t.disease_variation_status,callback:function(r){e.$set(t,"disease_variation_status",r)},expression:"stageItem.disease_variation_status"}},[e._v("有")])],1),e._v(" "),r("div",{directives:[{name:"show",rawName:"v-show",value:"1"==t.disease_variation_status,expression:"stageItem.disease_variation_status== '1' ?true:false "}],staticStyle:{height:"100%"}},[r("p",[e._v("原因")]),e._v(" "),r("textarea",{directives:[{name:"model",rawName:"v-model.trim",value:t.reason,expression:"stageItem.reason",modifiers:{trim:!0}}],staticClass:"textarea",staticStyle:{"border-bottom":"1px dashed pink",height:"86%"},attrs:{placeholder:"请输入内容"},domProps:{value:t.reason},on:{change:function(t){return e.replace7(a)},input:function(r){r.target.composing||e.$set(t,"reason",r.target.value.trim())},blur:function(t){return e.$forceUpdate()}}})])])])])],1)}),0)],1)],1)],1)},staticRenderFns:[]};var _=r("C7Lr")(p,v,!1,function(e){r("LKZD")},"data-v-70ba8c32",null).exports,g=r("bzuk"),f=r.n(g);r("4WSK");a.default.use(s.a),a.default.use(f.a);var y=new s.a({routes:[{path:"/",name:"fill",component:_}]}),h=r("Muz9"),x=r.n(h);a.default.config.productionTip=!1,a.default.use(f.a),a.default.prototype.$axios=x.a,new a.default({el:"#app",router:y,components:{App:n},template:"<App/>"})}},["NHnr"]);
//# sourceMappingURL=app.48b926810ea4b5377835.js.map