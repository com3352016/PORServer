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
	$('#informationButton').click(function(){
        alert('hohoho');
        var date = new Date();
        var year = date.getFullYear();
        var month = date.getMonth();
        var day = date.getDay();
        var gname = $("#sgivenname").val();
        var fname = $("#sfirstname").val();
        var birthdate = $("#sdate").val();
        var phone = "";
        var phone1 = $("#sphone1").val();
        var phone2 = $("#sphone2").val();
        var phone3 = $("#sphone3").val();
//        var wphone = $("#selTelecom").val();
        phone = phone1+phone2+phone3;
        var email = "";
        var mailadd = $("#smail").val();
        var mailtype = $("#selEmail").val();
        email = mailadd+"@"+mailtype;
        var gender = $("#selGender").val();
        var gcode = "";
        if(gender == "Male"){
        	gcode = "M";
        }else if(gender == "Female"){
        	gcode = "F";
        }
    	var request = {
    	  "resourceType": "Patient",
    	  "id" : "example",
//    		  "text": {
//    	    "status": "generated",
//    	    "div": "<div>\n      <table>\n        <tbody>\n          <tr>\n            <td>Name</td>\n            <td>Peter James <b>Chalmers</b> (&quot;Jim&quot;)</td>\n          </tr>\n          <tr>\n            <td>Address</td>\n            <td>534 Erewhon, Pleasantville, Vic, 3999</td>\n          </tr>\n          <tr>\n            <td>Contacts</td>\n            <td>Home: unknown. Work: (03) 5555 6473</td>\n          </tr>\n          <tr>\n            <td>Id</td>\n            <td>MRN: 12345 (Acme Healthcare)</td>\n          </tr>\n        </tbody>\n      </table>\n    </div>"
//    	  },
//    	  "identifier": [
//    	    {
//    	      "use": "offical",
//    	      "label": "MRN",
//    	      "system": "urn:oid:1.2.36.146.595.217.0.1",
//    	      "value": "12345",
//    	      "period": {
//    	        "start": "2001-05-06"
//    	      },
//    	      "assigner": {
//    	        "display": "Acme Healthcare"
//    	      }
//    	    }
//    	  ],
    	  "name": [
    	    {
    	      "use": "official",
    	      "family": [
    	        fname
    	      ],
    	      "given": [
    	        gname
    	      ]
    	    },
    	  ],
    	  "telecom": [
    	    {
    	      "system": "email",
    	      "value" : email,
    	      "use": "home"
    	    },
    	    {
    	      "system": "phone",
    	      "value": phone,
    	      "use": "mobile"
    	    }
    	  ],
    	  "gender": {
    	    "coding": [
    	      {
    	        "system": "http://hl7.org/fhir/v3/AdministrativeGender",
    	        "code": gcode,
    	        "display": gender
    	      }
    	    ]
    	  },
    	  "birthDate": birthdate,
          "deceasedBoolean": false,
//    	  "managingOrganization": {
//    		  "reference": "Organization/1"
//    	  },
    	  "active": true
    	};
    	var alertmsg = "";
    	var nu = 0;
    	if(gname == ""){
    		if(nu > 0){
    			alertmsg+=", ";
    		}
    		alertmsg += "이름";
    		nu++;
    	}
    	if(fname == ""){
    		if(nu > 0){
    			alertmsg+=", ";
    		}
    		alertmsg += "성";
    		nu++;
    	}
    	if(birthdate == ""){
    		if(nu > 0){
    			alertmsg+=", ";
    		}
    		alertmsg += "생년월일";
    		nu++;
    	}
    	if(phone1 == "" || phone2 == "" || phone3 == ""){
    		if(nu > 0){
    			alertmsg+=", ";
    		}
    		alertmsg += "전화번호";
    		nu++;
    	}
    	if(mailadd == ""){
    		if(nu > 0){
    			alertmsg+=", ";
    		}
    		alertmsg += "메일";
    		nu++;
    	}
    	alertmsg+="을 입력해주세요";
    	if(gname != "" && fname != "" && birthdate != "" && phone1 != "" && phone2 != "" && phone3 != "" && mailadd != ""){
    		$.ajax({
    	        type:'POST' ,
    	        url:'http://155.230.118.93:8080/PORServer/rest/test/pinfo',
    	        contentType:"application/json;charset=UTF-8",
    	        data:JSON.stringify(request),
    	        success:function(response){
    	    	    alert(response);
    	            cookie.set('key', response);
    	        }
    	    });
    	}
    	else{
    		alert(alertmsg);
    	}
   });

    $('#documentsubmitButton').click(function(){
         alert('hahahahaha');
//         var uri = new Windows.Foundation.Uri("C:/My_Dev/SampleCRS.xml");
//         var 
         var data = new FormData();
         var filea;
         var reader = new FileReader();
         var file;
//         var qqq = quatio();
//         alert(qqq);
//         var filecontent;
         $.each($("input[name=attachFile]")[0].files, function(i, file) {          
             reader.onload = function(e){
            	 filea = reader.result;
            	 var output = parsing(filea);
            	 var kiki = cookie.get('key');
            	 $.ajax({
                     type:'POST' ,
                     url:'http://155.230.118.93:8080/PORServer/rest/test/DocumentReference',
//                     url:'http://155.230.118.94:9091/fhir/DocumentReference',
                     contentType:'application/json',
                     processData:false,
                     data:kiki+"~"+JSON.stringify(output),
                     success:function(response){
                         alert('success');
                     }
                  });
             };
             reader.readAsText(file);
         });
//         var fileInput = document.getElementById('attachFile');
//         fileInput.addEventListener('change', function(e){file = fileInput.files[0];});
//         reader.onload = function(e){
//        	 filea = reader.result;
//         };
//         reader.readAsText(file);
//         alert(filea);
//         var fstr = serialize(data.forms[0]);
//         $.get('C:/My_Dev/SampleCRS.xml', function(data){filecontent = data;});
         
//         parsing(fstr);
         
         
//         <script type="text/javascript">
//         var filePath = PATH_TO_FILE e.g. http://www.google.com/file.txt
//         xmlhttp = new XMLHttpRequest();
//         xmlhttp.open("GET",filePath,false);
//         xmlhttp.send(null);
//         var fileContent = xmlhttp.responseText;
//         var fileArray = fileContent.split('\n')
//         //Now do whatever you need with the array
//         </script>
    });
    
    $('#documentsearchButton').click(function(){ 
        var format = $("#searchFormat").val();
        var type = $("#searchType").val();
//        var periodbefore = $("#searchPeriodBefore").val();
//        var periodafter = $("#searchPeriodAfter").val();
        var url = "";
        var obj = "";
        var user = cookie.get('key');
        url = 'http://155.230.118.93:8080/PORServer/rest/test/documentReference/_search?user='+user;
//        url = 'http://fhir.healthintersections.com.au/open/DocumentReference/_search?format=CCD&type=Consult';
//        url = 'http://fhir.healthintersections.com.au.open/DocumentReference/_search?subject.given='+format;
        
//        if(periodbefore == null){
//        	url = 'http://155.230.118.93:8080/PORServer/rest/documentReference?format='+format+'&type='+type+'&period:after='+periodafter;
//        }
//        if(periodafter == null){
//        	url = 'http://155.230.118.93:8080/PORServer/rest/documentReference?format='+format+'&type='+type+'&period:after='+periodbefore;
//        }
//        if(periodbefore != null && periodafter != null){
//        	url = 'http://155.230.118.93:8080/PORServer/rest/documentReference?format='+format+'&type='+type+'&period:before='+periodbefore+'&period:after='+periodafter;
//        }
        for(var j = 0 ; j < len ; j++){
			var srow = results.rows.item(j);
			var table = document.getElementById("searTable");
			var row = table.insertRow(j+1);
        	var cell = new Array();
        	var add = results.rows.item(j).id;
        	var splitst = results.rows.item(j).id.split('/');
        	var subst = splitst[1].split('CRS');
        	var ssubst = subst[0].split('015_');
//			alert(provider[i].name);
    		cell[0] = row.insertCell();
            cell[1] = row.insertCell();
            cell[2] = row.insertCell();
            cell[3] = row.insertCell();
            cell[0].innerHTML = '<input type="button" value="제출" id="btn'+j+'"  />';
            var ready = false;
            var btn = document.getElementById("btn"+j);
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
            	var cont = results.rows.item(instring).file;
//            	ready = true;
                var check = function() {
//                    if (ready == true) {
//                   	  alert("callback:");
//                   	  alert("callback:");
                    	var kiki = sessionStorage.getItem("key");
                    	var uurl = 'http://155.230.118.93:8080/PORServer/rest/test/DocumentReference?user='+kiki;
                	 	$.ajax({
            		 	type:'POST' ,
               		url:uurl,
               		contentType:'application/json',
               		processData:false,
               		data:JSON.stringify(request),
               		success:function(response){
                        alert('success');
                     }
                 	});
                         // do what you want with the result variable
                         return;
//                    }
                    setTimeout(check, 1000);
                }
                check();
            } 
            cell[1].innerHTML = results.rows.item(j).type;
            cell[2].innerHTML = results.rows.item(j).format;
            cell[3].innerHTML = ssubst[1];
		}
        $.ajax({
            crossDomain:true,
            type:'GET',
            url:url,
            contentType:'application/json;charset=UTF-8',
            success:function(response){
//            	alert(response);
            	var r = JSON.parse(response);
//            	alert(r.entry.length);
            	var date = new Date();
            	var type;
    			var format;
    			var file;
            	$.each(r.entry, function(key){
            		var info = r.entry[key].resource;
//            		alert(info);
            		var rt = JSON.parse(info);
            		var telecom;
//            		alert(rt.resourceType);
            		if(rt.resourceType == "Patient"){
//            			telecom;
//            			date = "";
            		}else if(rt.resourceType == "DocumentReference"){
            			type = rt.type.coding[0].code;
            			format = rt.format.coding[0].code;
            			alert(type);
            			alert(format);
            		}else if(rt.resourceType == "Binary"){
            			file = rt.content;
            			alert(file);
            		}
            	});
            	var dates = date.getFullYear() + "_" + date.getMonth() + "_" + date.getDate() + "_" + date.getHours() + "_" + date.getMinutes() + "_" + date.getSeconds() + format + type;
        		alert(dates);
//            	track1dstu2parse(response);            	
            }
        });
   });
    $('#searchButton').click(function(){
        var rootURL = $("#selSearchServer").val();
        var name = $("#givenName").val();
        var URI = rootURL+name;
        $("#searchTable tr:not(:first)").remove();
        getSearchHttp(URI);
    });
    
    function getSearchHttp(uri) {
      var xhr = createCORSRequest('GET', uri);
      if(!xhr) {
          //alert('CORS not supported');
          return;
      }
      xhr.onload = function() {
          var text = xhr.responseText;
          //alert('Response from CORS request to ' + uri + ': ' + text);
          var result=document.getElementById("searchResult");
          result.value=formatXml(text);
//          track1parse(text);
          track1dstu2parse(text);
      };
      xhr.onerror = function() {
          //alert('Wakdjfklasjfklasjkldf');
      };
      xhr.send();
  };
  
  function createCORSRequest(method, url){
      var xhr = new XMLHttpRequest();
      if("withCredentials" in xhr) {
          xhr.open(method, url, true);
          //xhr.setRequestHeader("User-Agent", "HAPI-FHIR/0.9-SNAPSHOT (FHIR Client)");
          //alert('withCredentiality');
          xhr.send();
      } else if(typeof XDomainRequest != "undefined") {
          //alert('withoutCredentialityhahaha');
          xhr = new XDomainRequest();
          xhr.open(method, url);
      }
      else {
          xhr = null;
      }
      return xhr;
  };
  function track1dstu2parse(con){
		if(window.DOMParser){
			parser = new DOMParser();
			//alert("parsing");
			var xmlDoc = parser.parseFromString(con, "text/xml");
			var x = xmlDoc.firstChild;
	        var y, z;
			alert("length:"+x.nodeName);
	        var k = 0;
	        for(var i=0;i<x.childNodes.length;i++){
	            var Result = "";
	            if(x.childNodes[i].nodeName == "entry"){
	            	var aa = x.childNodes[0];
	            	alert(aa.nodeName);
	                 y = x.childNodes[i].getAttribute("value");
	            }                    
	        }
		}
		else {
			//alert("fuckyou");
		}
	};
	function parsing(content){
		if(window.DOMParser){
			parser = new DOMParser();
//			var xmlhttp = new XMLHttpRequest();
//			xmlhttp.open("GET", content, false);
//			xmlhttp.send();
//			var xmlDoc = xmlhttp.responseXML;
			var xmlDoc = parser.parseFromString(content, "text/xml");
			var x = xmlDoc.getElementsByTagName("ClinicalDocument");
			var png, pnf, pns, pnp, pbt, pgd, pgc, ptv, ptu, pau, pasal, pas, pac, pap;
			var ang, anf, ans, anp, abt, agd, agc, atv, atu, aau, aasal, aas, aac, aap;
			alert(content);
//			alert(x.length);
			alert(x[0].childNodes.length);
			for(var i=0;i<x[0].childNodes.length;i++){
                if(x[0].childNodes[i].nodeName == "recordTarget"){
                	var nc = x[0].childNodes[i];
                	for(var m = 0 ; m < nc.childNodes.length ; m++){
                		if(nc.childNodes[m].nodeName == "patientRole"){
                			var ncc = nc.childNodes[m];
                			for(var m1 = 0 ; m1 < ncc.childNodes.length ; m1++){
                				if(ncc.childNodes[m1].nodeName == "patient"){
                					var nccc = ncc.childNodes[m1];
                					for(var m2 = 0 ; m2 < nccc.childNodes.length ; m2++){
                						if(nccc.childNodes[m2].nodeName == "name"){
                							var va1 = nccc.childNodes[m2];
                							for(var m3 = 0 ; m3 < va1.childNodes.length ; m3++){
                								if(va1.childNodes[m3].nodeName == "given"){
                									if(va1.childNodes[m3].childNodes.length>0){
                										alert(va1.childNodes[m3].childNodes[0].nodeValue);
                										png = va1.childNodes[m3].childNodes[0].nodeValue;
                									}
                								}
                								else if(va1.childNodes[m3].nodeName == "family"){
                									if(va1.childNodes[m3].childNodes.length>0){
                										alert(va1.childNodes[m3].childNodes[0].nodeValue);
                										pnf = va1.childNodes[m3].childNodes[0].nodeValue;
                									}
                								}
                								else if(va1.childNodes[m3].nodeName == "suffix"){
                									if(va1.childNodes[m3].childNodes.length>0){
                										alert(va1.childNodes[m3].childNodes[0].nodeValue);
                										pns = va1.childNodes[m3].childNodes[0].nodeValue;
                									}
                								}
                								else if(va1.childNodes[m3].nodeName == "prefix"){
                									if(va1.childNodes[m3].childNodes.length>0){
                										alert(va1.childNodes[m3].childNodes[0].nodeValue);
                										pnf = va1.childNodes[m3].childNodes[0].nodeValue;
                									}
                								}
                							}
                						}
                						else if(nccc.childNodes[m2].nodeName == "birthTime"){
                							var va2 = nccc.childNodes[m2];
                							alert(va2.getAttribute("value"));
                							pbt = va2.getAttribute("value");
                						}
                						else if(nccc.childNodes[m2].nodeName == "administrativeGenderCode"){
                							var va3 = nccc.childNodes[m2];
                							alert(va3.getAttribute("displayName"));
                							alert(va3.getAttribute("code"));
                							pgd = va3.getAttribute("displayName");
                							pgc = va3.getAttribute("code");
                						}
                					}
                				}
                				else if(ncc.childNodes[m1].nodeName == "telecom"){
                					alert(ncc.childNodes[m1].getAttribute("value"));
                					alert(ncc.childNodes[m1].getAttribute("use"));
                					ptv = ncc.childNodes[m1].getAttribute("value");
                					ptu = ncc.childNodes[m1].getAttribute("use");
                				}
                				else if(ncc.childNodes[m1].nodeName == "addr"){
                					alert(ncc.childNodes[m1].getAttribute("use"));
                					pau = ncc.childNodes[m1].getAttribute("use");
                					var nccc2 = ncc.childNodes[m1];
                					for(var m4 = 0 ; m4 < nccc2.childNodes.length ; m4++){
                						if(nccc2.childNodes[m4].nodeName == "streetAddressLine"){
                							alert(nccc2.childNodes[m4].childNodes[0].nodeValue);
                							pasal = nccc2.childNodes[m4].childNodes[0].nodeValue;
                						}
                						else if(nccc2.childNodes[m4].nodeName == "state"){
                							alert(nccc2.childNodes[m4].childNodes[0].nodeValue);
                							pas = nccc2.childNodes[m4].childNodes[0].nodeValue;
                						}
                						else if(nccc2.childNodes[m4].nodeName == "city"){
                							alert(nccc2.childNodes[m4].childNodes[0].nodeValue);
                							pac = nccc2.childNodes[m4].childNodes[0].nodeValue;
                						}
                						else if(nccc2.childNodes[m4].nodeName == "postalCode"){
                							alert(nccc2.childNodes[m4].childNodes[0].nodeValue);
                							pap = nccc2.childNodes[m4].childNodes[0].nodeValue;
                						}
                					}
                				}
                			}
                		}
                	}
                }
                else if(x[0].childNodes[i].nodeName == "author"){
                	var nc = x[0].childNodes[i];
                	for(var m = 0 ; m < nc.childNodes.length ; m++){
                		if(nc.childNodes[m].nodeName == "assignedAuthor"){
                			var ncc = nc.childNodes[m];
                			for(var m1 = 0 ; m1 < ncc.childNodes.length ; m1++){
                				if(ncc.childNodes[m1].nodeName == "assignedPerson"){
                					var nccc = ncc.childNodes[m1];
                					for(var m2 = 0 ; m2 < nccc.childNodes.length ; m2++){
                						if(nccc.childNodes[m2].nodeName == "name"){
                							var va1 = nccc.childNodes[m2];
                							for(var m3 = 0 ; m3 < va1.childNodes.length ; m3++){
                								if(va1.childNodes[m3].nodeName == "given"){
                									if(va1.childNodes[m3].childNodes.length>0){
                										alert(va1.childNodes[m3].childNodes[0].nodeValue);
                										ang = va1.childNodes[m3].childNodes[0].nodeValue;
                									}
                								}
                								else if(va1.childNodes[m3].nodeName == "family"){
                									if(va1.childNodes[m3].childNodes.length>0){
                										alert(va1.childNodes[m3].childNodes[0].nodeValue);
                										anf = va1.childNodes[m3].childNodes[0].nodeValue;
                									}
                								}
                								else if(va1.childNodes[m3].nodeName == "suffix"){
                									if(va1.childNodes[m3].childNodes.length>0){
                										alert(va1.childNodes[m3].childNodes[0].nodeValue);
                										ans = va1.childNodes[m3].childNodes[0].nodeValue;
                									}
                								}
                								else if(va1.childNodes[m3].nodeName == "prefix"){
                									if(va1.childNodes[m3].childNodes.length>0){
                										alert(va1.childNodes[m3].childNodes[0].nodeValue);
                										anp = va1.childNodes[m3].childNodes[0].nodeValue;
                									}
                								}
                							}
                						}
                					}
                				}
                				else if(ncc.childNodes[m1].nodeName == "telecom"){
                					alert(ncc.childNodes[m1].getAttribute("value"));
                					alert(ncc.childNodes[m1].getAttribute("use"));
                					atv = ncc.childNodes[m1].getAttribute("value");
                					atu = ncc.childNodes[m1].getAttribute("value");
                				}
                				else if(ncc.childNodes[m1].nodeName == "addr"){
                					alert(ncc.childNodes[m1].getAttribute("use"));
                					aau = ncc.childNodes[m1].getAttribute("use");
                					var nccc2 = ncc.childNodes[m1];
                					for(var m4 = 0 ; m4 < nccc2.childNodes.length ; m4++){
                						if(nccc2.childNodes[m4].nodeName == "streetAddressLine"){
                							alert(nccc2.childNodes[m4].childNodes[0].nodeValue);
                							aasal = nccc2.childNodes[m4].childNodes[0].nodeValue;
                						}
                						else if(nccc2.childNodes[m4].nodeName == "state"){
                							alert(nccc2.childNodes[m4].childNodes[0].nodeValue);
                							aas = nccc2.childNodes[m4].childNodes[0].nodeValue;
                						}
                						else if(nccc2.childNodes[m4].nodeName == "city"){
                							alert(nccc2.childNodes[m4].childNodes[0].nodeValue);
                							aac = nccc2.childNodes[m4].childNodes[0].nodeValue;
                						}
                						else if(nccc2.childNodes[m4].nodeName == "postalCode"){
                							alert(nccc2.childNodes[m4].childNodes[0].nodeValue);
                							aap = nccc2.childNodes[m4].childNodes[0].nodeValue;
                						}
                					}
                				}
                			}
                		}
                	}
                }
			}
			//JSON 파일을 이어붙일 수 있어야 한다는 점
			//예를 들면 Author의 경우 복수가 될 수도 있는데 그런 것들 어떻게 처리할 것인가 논의
			var documentReference = {
					  "resourceType": "DocumentReference",
					  "text": {
					    "status": "generated",
					    "div": "<div>\n      <p>\n        <b>Generated Narrative</b>\n      </p>\n      <p>\n        <b>masterIdentifier</b>: urn:oid:1.3.6.1.4.1.21367.2005.3.7\n      </p>\n      <p>\n        <b>subject</b>: \n        <a href=\"patient-example-xcda.html\">MRN = 12345 (usual); Henry Levin ; Male; birthDate: 24-Sep 1932; active</a>\n      </p>\n      <p>\n        <b>type</b>: \n        <span title=\"Codes: {http://loinc.org 34108-1}\">Outpatient Note</span>\n      </p>\n      <p>\n        <b>author</b>: Sherry Dopplemeyer ; Primary Surgon; Orthopedic, Gerald Smitty ; Attending; Orthopedic\n      </p>\n      <p>\n        <b>created</b>: 24-Dec 2005 9:35\n      </p>\n      <p>\n        <b>indexed</b>: 24-Dec 2005 9:43\n      </p>\n      <p>\n        <b>status</b>: current\n      </p>\n      <p>\n        <b>description</b>: Physical\n      </p>\n      <p>\n        <b>confidentiality</b>: \n        <span title=\"Codes: {http://ihe.net/xds/connectathon/confidentialityCodes 1.3.6.1.4.1.21367.2006.7.101}\">Clinical-Staff</span>\n      </p>\n      <p>\n        <b>primaryLanguage</b>: en-US\n      </p>\n      <p>\n        <b>mimeType</b>: application/hl7-v3+xml\n      </p>\n      <p>\n        <b>size</b>: 3654\n      </p>\n      <p>\n        <b>hash</b>: da39a3ee5e6b4b0d3255bfef95601890afd80709\n      </p>\n      <p>\n        <b>location</b>: \n        <a href=\"http://example.org/xds/mhd/Binary/@07a6483f-732b-461e-86b6-edb665c45510\">http://example.org/xds/mhd/Binary/@07a6483f-732b-461e-86b6-edb665c45510</a>\n      </p>\n      <h3>Contexts</h3>\n      <table class=\"grid\">\n        <tr>\n          <td>\n            <b>Event</b>\n          </td>\n          <td>\n            <b>Period</b>\n          </td>\n          <td>\n            <b>FacilityType</b>\n          </td>\n        </tr>\n        <tr>\n          <td>\n            <span title=\"Codes: {http://ihe.net/xds/connectathon/eventCodes T-D8200}\">Arm</span>\n          </td>\n          <td>23-Dec 2004 8:0 --&gt; 23-Dec 2004 8:1</td>\n          <td>\n            <span title=\"Codes: {http://www.ihe.net/xds/connectathon/healthcareFacilityTypeCodes Outpatient}\">Outpatient</span>\n          </td>\n        </tr>\n      </table>\n    </div>"
					  },
					  "contained": [
					    {
					      "resourceType": "Patient",
					      "id": "a1",
					      "name": [
					               {
					                 "use": "official",
					                 "family": [
					                   pnf
					                 ],
					                 "given": [
					                   png
					                 ],
					               }
					      ],
					      "telecom": [
					                  {
					                    "system": "phone",
					                    "value": ptv,
					                    "use": ptu
					                  }
					      ],
					      "gender": {
					    	    "coding": [
					    	      {
					    	        "system": "http://hl7.org/fhir/v3/AdministrativeGender",
					    	        "code": pgc,
					    	        "display": pgd
					    	      }
					    	    ]
					      },
					      "birthDate": pbt,
					      "address": [
					                  {
					                    "use": pau,
					                    "line": [
					                      pasal
					                    ],
					                    "city": pac,
					                    "state": pas,
					                    "zip": pap
					                  }
					      ]
					    },
					    {
					      "resourceType": "Practitioner",
					      "id": "a2",
					      "name": {
					        "family": [
					          anf
					        ],
					        "given": [
					          ang
					        ],
					        "prefix": [
							  anp
							]
					      },
					      "telecom": [
					                  {
						                    "system": "phone",
						                    "value": atv,
						                    "use": atu
						                  }
						      ],
						      "address": [
						                  {
						                    "use": aau,
						                    "line": [
						                      aasal
						                    ],
						                    "city": aac,
						                    "state": aas,
						                    "zip": aap
						                  }
						      ],
					      "organization": {
					        "display": "Cleveland Clinic"
					      },
					      "role": [
					        {
					          "text": "Attending"
					        }
					      ],
					      "specialty": [
					        {
					          "text": "Orthopedic"
					        }
					      ]
					    },
					    {
					        "resourceType" : "Binary",
					        "id": "a3",
					        "contentType" : "application/xml",
					        "content" : content//base64 암호화필요
					    }
					  ],
					  "masterIdentifier": {
					    "system": "urn:ietf:rfc:3986",
					    "value": "urn:oid:1.3.6.1.4.1.21367.2005.3.7"
					  },
					  "subject": {
						  "reference": "#a1"
					  },
					  "type": {
				          "coding": [{
				              "code": "Summary",
				              "display": "Continuity of Care Document"
				            }]
				        },
				        "format": {
				          "coding": [{
				              "code": "CRS",
				              "display": "Care Record Summary"
				            }]
				        },
					  "author": [
					    {
					      "reference": "#a2"
					    }
					  ],
					  "created": "2005-12-24T09:35:00+11:00",
					  "indexed": "2005-12-24T09:43:41+11:00",
					  "status": "current",
					  "description": "Physical",
					  "confidentiality": [
					    {
					      "coding": [
					        {
					          "system": "http://ihe.net/xds/connectathon/confidentialityCodes",
					          "code": "1.3.6.1.4.1.21367.2006.7.101",
					          "display": "Clinical-Staff"
					        }
					      ]
					    }
					  ],
					  "primaryLanguage": "Kr",
					  "mimeType": "application/hl7-v3+xml",
					  "size": "3654",
					  "hash": "da39a3ee5e6b4b0d3255bfef95601890afd80709",
					  "location": "#a3",
					  "context": {
					    "event": [
					      {
					        "coding": [
					          {
					            "system": "http://ihe.net/xds/connectathon/eventCodes",
					            "code": "T-D8200",
					            "display": "Arm"
					          }
					        ]
					      }
					    ],
					    "period": {
					      "start": "2004-12-23T08:00:00",
					      "end": "2004-12-23T08:01:00"
					    },
					    "facilityType": {
					      "coding": [
					        {
					          "system": "http://www.ihe.net/xds/connectathon/healthcareFacilityTypeCodes",
					          "code": "Outpatient",
					          "display": "Outpatient"
					        }
					      ]
					    }
					  }
					};
			return documentReference;
		}
//            if(itemNodeList.item(0).getChildNodes().item(s).getNodeName().equals("author")){
//                ResourceReference rref = docRef.addAuthor();
//                rref.setReferenceSimple("#a2");
//                Practitioner practi = new Practitioner();
//                practi.setXmlId("a2");
//                Node n = itemNodeList.item(0).getChildNodes().item(s);
//                for(var ss = 0 ; ss < n.getChildNodes().getLength() ; ss++){
//                    if(n.getChildNodes().item(ss).getNodeName().equals("assignedAuthor")){
//                        Node nc = n.getChildNodes().item(ss);
//                        for(var sss = 0 ; sss < nc.getChildNodes().getLength() ; sss++){
//                            if(nc.getChildNodes().item(sss).getNodeName().equals("assignedPerson")){
//                                Node ncc = nc.getChildNodes().item(sss);
//                                String given="", family="", prefix="";
//                                for(var nn = 0 ; nn < ncc.getChildNodes().item(0).getChildNodes().getLength() ; nn++){
//                                   if(ncc.getChildNodes().item(0).getChildNodes().item(nn).getNodeName().equals("given")){
//                                       given = ncc.getChildNodes().item(0).getChildNodes().item(nn).getChildNodes().item(0).getNodeValue();
//                                   }
//                                   if(ncc.getChildNodes().item(0).getChildNodes().item(nn).getNodeName().equals("family")){
//                                       family = ncc.getChildNodes().item(0).getChildNodes().item(nn).getChildNodes().item(0).getNodeValue();
//                                   }
//                                   if(ncc.getChildNodes().item(0).getChildNodes().item(nn).getNodeName().equals("prefix")){
//                                       prefix = ncc.getChildNodes().item(0).getChildNodes().item(nn).getChildNodes().item(0).getNodeValue();
//                                   }
//                                }
//                                HumanName hun = new HumanName();
//                                hun.addFamilySimple(family);
//                                hun.addGivenSimple(given);
//                                hun.addPrefixSimple(prefix);
//                                practi.setName(hun);
//                            }
//                            if(nc.getChildNodes().item(sss).getNodeName().equals("addr")){
//                                String place = ((Element)nc.getChildNodes().item(sss)).getAttribute("use");
//                                Node ncc = nc.getChildNodes().item(sss);
//                                Address add = new Address();
//                                if(place.equals("HP")){
//                                    add.setUseSimple(Address.AddressUse.home);
//                                }else if(place.equals("WP")){
//                                    add.setUseSimple(Address.AddressUse.work);
//                                }
//                                for(var nn = 0 ; nn < ncc.getChildNodes().getLength() ; nn++){
//                                    if(ncc.getChildNodes().item(nn).getNodeName().equals("streetAddressLine")){
//                                        add.addLineSimple(ncc.getChildNodes().item(nn).getChildNodes().item(0).getNodeValue());
//                                    }
//                                    if(ncc.getChildNodes().item(nn).getNodeName().equals("state")){
//                                        add.setStateSimple(ncc.getChildNodes().item(nn).getChildNodes().item(0).getNodeValue());
//                                    }
//                                    if(ncc.getChildNodes().item(nn).getNodeName().equals("city")){
//                                        add.setCitySimple(ncc.getChildNodes().item(nn).getChildNodes().item(0).getNodeValue());
//                                    }
//                                    if(ncc.getChildNodes().item(nn).getNodeName().equals("postalCode")){
//                                        add.setZipSimple(ncc.getChildNodes().item(nn).getChildNodes().item(0).getNodeValue());
//                                    }
//                                }
//                                practi.setAddress(add);
//                            }
//                            if(nc.getChildNodes().item(sss).getNodeName().equals("telecom")){
//                                String tele = ((Element)nc.getChildNodes().item(sss)).getAttribute("value");
//                                String place = ((Element)nc.getChildNodes().item(sss)).getAttribute("use");
//                                Contact cont = practi.addTelecom();
//                                if(place.equals("HP")){
//                                    cont.setUseSimple(Contact.ContactUse.home);
//                                }else if(place.equals("WP")){
//                                    cont.setUseSimple(Contact.ContactUse.work);
//                                }else if(place.equals("MP")){
//                                    cont.setUseSimple(Contact.ContactUse.mobile);
//                                }
//                                cont.setValueSimple(tele);
//                            }
//                        }
//                    }
//                }
//                docRef.getContained().add(practi);
//            }
//            if(itemNodeList.item(0).getChildNodes().item(s).getNodeName().equals("custodian")){
//                
//            }
//            if(itemNodeList.item(0).getChildNodes().item(s).getNodeName().equals("title")){
//               Node resul = itemNodeList.item(0).getChildNodes().item(s);
//               String resulv = resul.getChildNodes().item(0).getNodeValue();
//               if(resulv.equals("진료기록요약지")){
//                   CodeableConcept conce = new CodeableConcept();
//                   Coding concep = conce.addCoding();
//                   concep.setCodeSimple("Summary");
//                   concep.setDisplaySimple(resulv);
//                   UriType u= docRef.addFormatSimple("CRS");
//                   docRef.setType(conce);
//                   System.out.println(docRef.getType().getTextSimple());
//               }
//            }
//        }
	}
});