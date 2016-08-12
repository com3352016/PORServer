/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function idgen(){
    var id;
    return id;
}
$(document).ready(function(){
    $('#servicesubmitButton').click(function(){
         alert('hahahahaha');
         var name = $("#servicename").val();
         var url = $("#serviceurl").val();
         var alertmsg = "서비스 ";
         var nu = 0;
//         var patientSubmit = $("#patientsubmit").val();
//         var apiurl = $("#serviceapiurl").val();
         var request = {
             name : name,
             url : url
//             patient_signin : patientSubmit,
//             api_search_url : apiurl
         };
//         var request = {
//             name : 'name',
//             url : 'url',
//             patient_signin : 'patientSubmit',
//             oauth2_registration_uri : 'oauthReg',
//             oauth2_authorize_uri : 'oauthAutho',
//             oauth2_token_uri : 'oauthToken',
//             oauth2_introspect_uri : 'oauthIntro',
//             api_search_url : 'apiurl'
//         };
         if(name==""){
        	 if(nu > 0){
     			alertmsg+=", ";
     		 }
        	 alertmsg+="이름";
        	 nu++;
         }
         if(url==""){
        	 if(nu > 0){
      			alertmsg+=", ";
      		 }
        	 alertmsg+="주소";
        	 nu++;
         }
         if(name!="" && url!=""){
        	 $.ajax({
                 crossDomain:true,
                 type:'POST' ,
                 url:'http://155.230.118.93:8080/PORServer/rest/test',
                 contentType:'application/json;charset=UTF-8',
                 data:JSON.stringify(request),
                 success:function(response){
                 	if(response == 1){
                 		alert("이미 등록된 서비스 이름입니다.");
                 	} else if(response == 2){
                 		alert("서비스가 등록되었습니다.");
                 	}
                 }
            }); 
         }
         else{
        	 alertmsg += "를 입력해주세요.";
        	 alert(alertmsg);
         }
    });
    $('#servicesearchButton').click(function(){ 
        var name = $("#servicesearchName").val();
//        var url = $("#servicesearchUrl").val();
        $("#searchTable tr:not(:first)").remove();
        var table = document.getElementById("searchTable");
        var request = {
            name : name,
        };
        $.ajax({
            crossDomain:true,
            type:'POST',
            url:'http://155.230.118.93:8080/PORServer/rest/test/search',
            contentType:'application/json;charset=UTF-8',
            data:JSON.stringify(request),
            dataType:'json',
            success: response_json
        });
        function response_json(json){
        	var provider = json.proInfo;
        	for(var i=0 ; i< provider.length ; i++){
        		var row = table.insertRow();
            	var cell = new Array();
        		alert(provider[i].name);
        		cell[0] = row.insertCell();
                cell[1] = row.insertCell();
                cell[2] = row.insertCell();
                cell[3] = row.insertCell();
                cell[0].innerHTML = provider[i].name;
                cell[1].innerHTML = provider[i].url;
                cell[2].innerHTML = provider[i].api_search_uri;
                cell[3].innerHTML = provider[i].patient_signin;
        	}
        }
   });
    
    function track1parse(con){
    	if(window.DOMParser){
    		parser = new DOMParser();
    		//alert("parsing");
    		var xmlDoc = parser.parseFromString(con, "text/xml");
    		var x = xmlDoc.getElementsByTagName("entry");
    		//alert("length:"+x.length);
                    for(var i=0;i<x.length;i++){
                        var y = xmlDoc.getElementsByTagName("entry")[i].childNodes.length;
//                        //alert(y);
                        for(var j=0;j<y;j++){
                            var type = xmlDoc.getElementsByTagName("entry")[i].childNodes[j].nodeName;
                            //alert(type);
                            if(type=="content"){
                                var p = xmlDoc.getElementsByTagName("entry")[i].childNodes[j].childNodes[0];
                                //alert(p.nodeName);
                                var plen = xmlDoc.getElementsByTagName("entry")[i].childNodes[j].childNodes[0].childNodes.length;
                                //alert(plen);
                                var Result = "";
                                for(var k=0;k<plen;k++){
                                    Result = Result + " " + track1patientTraversal(p.childNodes[k]);
                                }
                                var table = document.getElementById("searchTable");
                                var row = table.insertRow(-1);
                                var cell = new Array();
                                cell[0] = row.insertCell(-1);
                                var ind = i+1;
                                row.className = "rowm";
                                cell[0].className = "menu";
                                cell[0].innerHTML="entry:"+ind;
                                var contentrow = table.insertRow(-1);
//                                contentrow.className = "content"+ind;
                                var contentcell = new Array();
                                contentcell[0] = contentrow.insertCell(-1);
                                contentcell[0].innerHTML=Result;
                                contentcell[0].className = "content";
//                                contentrow.style.visibility="hidden";
                                contentcell[0].style.display="none";
                                row.onclick=function(){
//                                    var cell = this.getElementsByTagName("td")[0];
                                    var rowIndex = this.rowIndex;
                                    //alert(rowIndex);
                                    var tablei = document.getElementById("searchTable");
                                    var tablelen = tablei.rows.length;
                                    for(var q = 2 ; q < tablelen ; q = q+2){
                                        var cells = tablei.rows[q].cells[0];
                                        cells.style.display="none";
                                    }
                                    var cell = tablei.rows[rowIndex+1].cells[0];
                                    var id = cell.innerHTML;
                                    //alert("id:" + id);
                                    cell.style.display="block";
                                };
//                                cell[0].onclick=function(){
//                                    //alert('click');
//                                    contentcell[0].style.display="block";
//                                }
                            }
                        }            
                    }
    	}
    	else {
    		//alert("fuckyou");
    	}
    };
});