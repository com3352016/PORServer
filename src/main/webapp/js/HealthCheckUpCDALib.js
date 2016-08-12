/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var parser;

function getUrlAddress(con){
//	alert('hihkha');
//	alert(con.childNodes[0].nodeName);
	var ind = 0;
	var x = con.childNodes[0];
	for(var i=0;i<x.childNodes.length;i++){
		if(x.childNodes[i].nodeName == "entry"){
			var y = x.childNodes[i].childNodes[1].childNodes[1];
//			alert(y.nodeName);
			if(y.nodeName == "DocumentReference"){
				for(var j=0;j<y.childNodes.length;j++){
					if(y.childNodes[j].nodeName == "content"){
						var z = y.childNodes[j];
						for(var k=0;k<z.childNodes.length;k++){
							if(z.childNodes[k].nodeName == "url"){
								var result = z.childNodes[k].getAttribute("value");
								var table = document.getElementById("seaeTable");
				           		var row = table.insertRow(ind+1);
				           	    var cell = new Array();
				           	    cell[0] = row.insertCell();
				           	    cell[1] = row.insertCell();
				           	    cell[2] = row.insertCell();
				           	    cell[2].innerHTML = ind+1;
				           	    cell[1].innerHTML = result;
				           	    cell[0].innerHTML = '<input type="button" value="획득" id="btn'+(ind+1)+'"  />';
				                var btn = document.getElementById("btn"+(ind+1));
				                btn.onclick = function(event){
				                	if(!event){
				                		event = window.event;
				                		alert("hahahakdjf");
				                	}
				                	var el = (event.target || event.srcElement);
				                	alert("id?"+el.id);
				                	var instring = el.id.substring(3,4);
				                	alert(instring);
				                	instring *= 1;
				                	var uurl = table.rows[instring].cells[1].innerHTML;
				                	alert(uurl);
				                	$.ajax({
				                		crossDomain:true,
			                		 	type:'GET',
			                		 	url:uurl,
			                		 	contentType:'application/xml+fhir',
			                		 	success:function(response){
			                		 		var en = response.childNodes[0];
			                		 		alert(en.nodeName);
			                		 		var content;
			                		 		for(var ii=0 ; ii < en.childNodes.length ; ii++){
			                		 			if(en.childNodes[ii].nodeName == "content"){
			                		 				content = en.childNodes[ii].getAttribute("value");
			                		 				alert(content);
			                		 			}
			                		 		}
			                		 		sessionStorage.setItem("content", content);
			                		 		location.href = 'Content.html';
			                		 	}
				                	});
				                };
				                ind++;
							}
						}
					}
				}
			}
		}
	}
}

function HealthCheckupCDAParse(content){
	alert('parsing begin');
}