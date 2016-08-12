//package restful;
//
//public class backup {
//	package restful;
//
//	import java.io.ByteArrayOutputStream;
//	import java.io.IOException;
//	import java.io.InputStream;
//	import java.io.StringReader;
//	import java.io.StringWriter;
//	import java.io.Writer;
//	import java.sql.Blob;
//	import java.sql.Connection;
//	import java.sql.PreparedStatement;
//	import java.sql.ResultSet;
//	import java.sql.SQLException;
//	import java.text.SimpleDateFormat;
//	import java.util.ArrayList;
//	import java.util.Calendar;
//	import java.util.Date;
//	import java.util.Iterator;
//	import java.util.List;
//
//	import javax.ejb.Stateless;
//	import javax.naming.Context;
//	import javax.naming.InitialContext;
//	import javax.naming.NamingException;
//	import javax.persistence.EntityManager;
//	import javax.persistence.PersistenceContext;
//	import javax.sql.DataSource;
//	import javax.ws.rs.Consumes;
//	import javax.ws.rs.GET;
//	import javax.ws.rs.POST;
//	import javax.ws.rs.Path;
//	import javax.ws.rs.QueryParam;
//	import javax.ws.rs.core.MediaType;
//	import javax.ws.rs.core.Response;
//
//
//	import entity.PatientInfo;
//	import entity.Provider;
//	import entity.Testdb;
//
//	import org.apache.commons.io.IOUtils;
//	import org.apache.commons.codec.binary.*;
//	import org.apache.xml.serialize.OutputFormat;
//	import org.apache.xml.serialize.XMLSerializer;
//	import org.json.simple.JSONArray;
//	import org.json.simple.JSONObject;
//	import org.json.simple.parser.JSONParser;
//	import org.json.simple.parser.ParseException;
//
//	import action.DocumentReferenceService;
//	import action.ProviderParse;
//
//	import org.w3c.dom.Document;
//	import org.xml.sax.InputSource;
//	import org.xml.sax.SAXException;
//
//	import java.io.ByteArrayInputStream;
//	import java.io.ByteArrayOutputStream;
//	import org.apache.xml.serialize.OutputFormat;
//	import org.apache.xml.serialize.XMLSerializer;
//
//	//import com.sun.xml.internal.ws.client.sei.ResponseBuilder;
//
//	import javax.xml.parsers.DocumentBuilder;
//	import javax.xml.parsers.DocumentBuilderFactory;
//	import javax.xml.parsers.ParserConfigurationException;
//	import javax.xml.transform.OutputKeys;
//	import javax.xml.transform.Source;
//	import javax.xml.transform.Transformer;
//	import javax.xml.transform.sax.SAXSource;
//	import javax.xml.transform.sax.SAXTransformerFactory;
//	import javax.xml.transform.stream.StreamResult;
//
//	@Stateless
//	@Path("Fhir")
//	public class restful{
//		@PersistenceContext
//	    private EntityManager em;
//		String telecom;
//		String email;
//		String gender;
//		String family;
//		String given;
//		Long idv;
//		DataSource ds;
//		
//		public restful(){
//			try{
//				Context ctx = new InitialContext();
//				ds = (DataSource) ctx.lookup("java:jboss/datasources/myds");
//			}
//			catch (NamingException e){
//				
//			}
//		}
//		
//		private Document parseXmlFile(String in) {
//		       try {
//		           DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//		           DocumentBuilder db = dbf.newDocumentBuilder();
//		           InputSource is = new InputSource(new StringReader(in));
//		           return db.parse(is);
//		       } catch (ParserConfigurationException e) {
//		           throw new RuntimeException(e);
//		       } catch (SAXException e) {
//		           throw new RuntimeException(e);
//		       } catch (IOException e) {
//		           e.printStackTrace();
//		       }
//		       return null;
//		   }
//		   public String formatar(String unformattedXml) {
//		       try {
//		           Document document = parseXmlFile(unformattedXml);
//
//		           OutputFormat format = new OutputFormat(document);
//		           format.setLineWidth(65);
//		           format.setIndenting(true);
//		           format.setIndent(2);
//		           Writer out = new StringWriter();
//		           XMLSerializer serializer = new XMLSerializer(out, format);
//		           serializer.serialize(document);
//
//		           return out.toString();
//		       } catch (IOException e) {
//		           e.printStackTrace();
//		           return "";
//		       }
//
//		   }
//		
//		@SuppressWarnings("unchecked")
//		@POST
//		@Path("Patient")//patient 리소스 Create 혹은 Update인데... Create로 될 시 이미 있다는 것을 표시해줘야 함
////		@Consumes(MediaType.APPLICATION_JSON)
//		public Response pinfo(String entity) { 
//			JSONParser json = new JSONParser();
//			PatientInfo patient = new PatientInfo();
//			String values = "";
//			JSONObject jsonObj = new JSONObject();
//			JSONObject jsonObject = new JSONObject();
////			JSONArray cell = new JSONArray();
////			JSONObject cellobj = new JSONObject();
//			byte[] bytesave = entity.getBytes();
//			try {
//				patient.setPatient(bytesave);
//				jsonObj = (JSONObject) json.parse(entity);
//				patient.setBirthDate((String)jsonObj.get("birthDate"));
//				JSONArray telecomArray = (JSONArray) jsonObj.get("telecom");
//				jsonObject.put("telecom", telecomArray);
//				for(int i = 0 ; i < telecomArray.size() ; i++){
//					JSONObject obj = (JSONObject) telecomArray.get(i);
//					System.out.println("entered");
//					Iterator itera = obj.keySet().iterator();
//					int devide = 0;
//					while(itera.hasNext()){
//						String key = (String) itera.next();
//						Object value = obj.get(key);
//						System.out.println((String) value);
//						if(key.equals("system")){
//							String v = (String) value;
//							if(v.equals("email")){
//								devide = 1;
//							}
//							else if(v.equals("phone")){
//								devide = 2;
//							}
//						}
//						if(key.equals("value")){
//							System.out.println("entered most");
//							if(devide == 1){
//								this.email = (String) value;
////								patient.setEmail((String) value);
//							}
//							else if(devide == 2){
//								this.telecom = (String) value;
////								patient.setTelecom((String) value);
//							}						
//						}
//					}
//				}
//				JSONObject gender = (JSONObject) jsonObj.get("gender");
//				JSONArray genderArray = (JSONArray) gender.get("coding");
//				jsonObject.put("gender", genderArray);
//				for(int i = 0 ; i < genderArray.size() ; i++){
//					JSONObject obj = (JSONObject) genderArray.get(i);
//					System.out.println("entered");
//					Iterator itera = obj.keySet().iterator();
//					while(itera.hasNext()){
//						String key = (String) itera.next();
//						Object value = obj.get(key);
//						System.out.println((String) value);
//						if(key.equals("display")){
//							System.out.println("entered most");
//							this.gender = (String) value;
//						}
//					}
//				}
//				JSONArray nameArray = (JSONArray) jsonObj.get("name");
//				JSONArray neArr = new JSONArray();
//				JSONArray farr = new JSONArray();
//				JSONArray garr = new JSONArray();
//				JSONObject neOb = new JSONObject();
//				jsonObject.put("name", nameArray);
//				String fname="", gname="";
//				for(int i = 0 ; i < nameArray.size() ; i++){
//					JSONObject obj = (JSONObject) nameArray.get(i);
//					System.out.println("entering");
//					String use = (String) obj.get("use");
//					if(use.equals("official")){
//						JSONArray fArr = (JSONArray) obj.get("family");
//						JSONArray gArr = (JSONArray) obj.get("given");
//						fname = (String) fArr.get(0);
//						gname = (String) gArr.get(0);
//						this.family = fname;
//						this.given = gname;
////						JSONObject fn = new JSONObject();
////						fn.put("family", "fname");
////						farr.add(fn);
////						JSONObject gn = new JSONObject();
////						gn.put("given", "gname");
////						garr.add(gn);
////						patient.setFname(fname);
////						patient.setGname(gname);
//					}
//				}
////				neOb.put("use", "official");
////				neArr.add(farr);
////				neArr.add(garr);
////				neArr.add(neOb);
////				jsonObject.put("name", neArr);
//				if(this.telecom != null && this.email != null){
//					PreparedStatement ps = null;
//					Connection conn = null;
//					ResultSet rs = null;
//					if(ds != null)
//					{
//		                try{
//		                	conn = ds.getConnection();
//		                	ps = conn.prepareStatement("select id from patientinfo where telecom = ? && email = ?");
//		                	ps.setString(1, this.telecom);
//		                	ps.setString(2, this.email);
//		                	rs = ps.executeQuery();
//		                	if(rs.next())
//		                	{
//		                		idv = rs.getLong(1);
////		                		content = IOUtils.toString(cont, "UTF-8");
//		                	}
//		                	else{
//		                		patient.setFname(this.family);
//		    					patient.setGname(this.given);
//		    					patient.setGender(this.gender);
//		    					patient.setEmail(this.email);
//		    					patient.setTelecom(this.telecom);
//		                		em.persist(patient);
//		                		idv = patient.getId();
//		                	}
//		                }
//		                catch(SQLException sqle){
//		                	sqle.printStackTrace();
//		                }
//					}
//				}
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			 
//			JSONArray cell = new JSONArray();
//			JSONObject obj = new JSONObject();
//			jsonObject.put("active", "True");
//			jsonObject.put("deceasedBoolean", "False");
//			jsonObject.put("id", idv);
//			Calendar can = Calendar.getInstance();
//			obj.put("lastUpdated", can.getTime().toLocaleString());
//			obj.put("versionId", patient.getVersion());
////			cell.add(obj);
//			jsonObject.put("meta", obj);
//			jsonObject.put("resourceType", "Patient");
//			String str = jsonObject.toJSONString();
//			System.out.println(str);
////			String retur = patient.getTelecom();//예외처리 필요
//			javax.ws.rs.core.Response.ResponseBuilder re = Response.ok(str, "application/json+fhir");
//			re.status(201);
//			return re.build();
//		}
//		@POST
////		@Consumes(MediaType.APPLICATION_JSON)
//		public Response create(String entity) { 
//			JSONParser json = new JSONParser();
//			String values;
//			String pname = "";
//			String purl = "";
//			Provider provider = new Provider();
//			try {
//				JSONObject jsonObj = (JSONObject) json.parse(entity);
//				Iterator iter = jsonObj.keySet().iterator();
//				while(iter.hasNext()){
//					String key = (String) iter.next();
//					Object value = jsonObj.get(key);
//					if(key.equals("name")){
//						values = (String) value;
//						System.out.println(values);
//						pname = values;
////						provider.setName(values);
//					} else if(key.equals("url")){
//						values = (String) value;
//						System.out.println(values);
//						purl = values;
////						provider.setUrl(values);
//					} else if(key.equals("patient_signin")){
//						values = (String) value;
//						System.out.println(values);
////						provider.setPatient_signin(values);
//					} else if(key.equals("api_search_url")){
//						values = (String) value;
//						System.out.println(values);
////						provider.setApi_search_uri(values);
//					}
//				}
//				if(ds != null)
//				{
//					PreparedStatement ps = null;
//					Connection conn = null;
//					ResultSet rs = null;
//					String cname="";
//	                try{
//	                	conn = ds.getConnection();
//	                	ps = conn.prepareStatement("select name from provider");
////	                	System.out.println("value:"+pname);
////	                	ps.setString(1, pname);
//	                	rs = ps.executeQuery();
//	                	while(rs.next())
//	                	{
//	                		cname = rs.getString(1);
//	                		System.out.println(cname);
//	                		if(pname.equals(cname)){
//	                			int returnmsg = 1;
//	                			System.out.println(returnmsg);
//	                			return Response.status(201).entity(returnmsg).build();
//	                		}
//	                	}
//	                }
//	                catch(SQLException sqle){
//	                	sqle.printStackTrace();
//	                }
//				}
//				provider.setName(pname);
//				provider.setUrl(purl);
//				em.persist(provider);
//				int returnmsg1 = 2;
//				System.out.println(returnmsg1);
//				return Response.status(201).entity(returnmsg1).build();
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				return null;
//			}
//		}
//		
//		@SuppressWarnings("unchecked")
//		@POST
//		@Path("search")
////		@Consumes(MediaType.APPLICATION_JSON)
//		public Response search(String name) { 
//			JSONParser json = new JSONParser();
//			String values="";
//			String namea="";
//			String url="";
////			Provider provider = new Provider();
//			
//			try {
//				JSONObject jsonObj = (JSONObject) json.parse(name);
//				Iterator iter = jsonObj.keySet().iterator();
////				Object value = jsonObj.get("name");
////				values = (String) value;
////				System.out.println(values);
//				while(iter.hasNext()){
//					String key = (String) iter.next();
//					Object value = jsonObj.get(key);
//					values = (String) value;
//					System.out.println(values);
////					if(key.equals("name")){
////						
////					} 
//				}
//				if(ds != null)
//				{
//					PreparedStatement ps = null;
//					Connection conn = null;
//					ResultSet rs = null;
//	                try{
//	                	conn = ds.getConnection();
//	                	ps = conn.prepareStatement("select name, url from provider where name=?");
//	                	System.out.println("value:"+values);
//	                	ps.setString(1, values);
//	                	rs = ps.executeQuery();
//	                	if(rs.next())
//	                	{
//	                		namea = rs.getString(1);
//	                		url = rs.getString(2);
////	                		content = IOUtils.toString(cont, "UTF-8");
//	                	}
//	                }
//	                catch(SQLException sqle){
//	                	sqle.printStackTrace();
//	                }
//				}
//				JSONObject jsonObject = new JSONObject(); 
//				JSONArray cell = new JSONArray();
//				JSONObject obj = new JSONObject();
//				obj.put("name", namea);
//				obj.put("url", url);
//				cell.add(obj);
//				jsonObject.put("proInfo", cell);
//				return Response.status(201).entity(jsonObject).build();
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				return null;
//			}
//		}
//		
//		@SuppressWarnings("unchecked")
//		@POST
//		@Path("DocumentReference")//인증과정을 어떻게 할 것인가?
////		@Consumes(MediaType.APPLICATION_JSON)
//		public Response documentSubmit(@QueryParam("user") String user, String input) throws IOException {
//			Testdb doc = new Testdb();
//			JSONParser json = new JSONParser();
////			System.out.println("yahoo");
//			String[] split = input.split("~");
//			System.out.println(input);
////			doc.setUser(split[0]);
//			doc.setUser(user);
//			try {
//				JSONObject jsonObj = (JSONObject) json.parse(input);
//				JSONArray containArray = (JSONArray) jsonObj.get("entry");
//				for(int i = 0 ; i < containArray.size() ; i++){
//					JSONObject resource = (JSONObject) containArray.get(i);
////					JSONObject obj = (JSONObject) formatArray.get(i);
//					System.out.println("entered");
//					JSONObject reresource = (JSONObject) resource.get("resource");
//					Iterator itera = reresource.keySet().iterator();
//					while(itera.hasNext()){
//						String key = (String) itera.next();
//						Object value = reresource.get(key);
////						System.out.println((String) value);
//						if(key.equals("resourceType")){
//							System.out.println("entered most");
//							String def = (String) value;
//							if(def.equals("Binary")){
//								JSONObject con = (JSONObject) resource.get("resource");
//								Object base64ob = con.get("content");
//								//역시 자질구레한 스트링 저장은 아직은 안하는 것으로
////								String base64bstr = (String) base64ob;
////								byte[] base64b = base64bstr.getBytes();
////								byte[] plainb = Base64.decodeBase64(base64b);
////								String plains = IOUtils.toString(plainb, "UTF-8");
//////								String fplains = formatar(plains);
////								con.remove("content");
////								con.put("content", plains);
//								String kon = (String) con.toString();
//								System.out.println(kon);
//								doc.setContent2(kon.getBytes());
//							} else if(def.equals("DocumentReference")){
//								JSONObject format = (JSONObject) reresource.get("format");
//								JSONArray formatArray = (JSONArray) format.get("coding");
//								Object con = resource.get("resource");
//								String kon = con.toString();
//								doc.setContent(kon.getBytes());
//								for(int i1 = 0 ; i1 < formatArray.size() ; i1++){
//									JSONObject obj = (JSONObject) formatArray.get(i1);
//									Iterator itera1 = obj.keySet().iterator();
//									while(itera1.hasNext()){
//										String keyi = (String) itera1.next();
//										Object valuei = obj.get(keyi);
//										System.out.println((String) valuei);
//										if(keyi.equals("code")){
//											System.out.println("entered most");
//											doc.setFormat((String) valuei);
//										}
//									}
//								}
//								JSONObject type = (JSONObject) reresource.get("type");
//								JSONArray typeArray = (JSONArray) type.get("coding");
//								for(int i1 = 0 ; i1 < typeArray.size() ; i1++){
//									JSONObject obj = (JSONObject) typeArray.get(i1);
//									Iterator itera1 = obj.keySet().iterator();
//									while(itera1.hasNext()){
//										String keyi = (String) itera1.next();
//										Object valuei = obj.get(keyi);
//										System.out.println((String) valuei);
//										if(keyi.equals("code")){
//											System.out.println("entered most");
//											doc.setType((String) valuei);
//										}
//									}
//								}
//							}
////							doc.setFormat((String) value);
//						}
//					}
//				}
////				JSONObject type = (JSONObject) jsonObj.get("type");
////				JSONArray typeArray = (JSONArray) type.get("coding");
////				for(int i = 0 ; i < typeArray.size() ; i++){
////					JSONObject obj = (JSONObject) typeArray.get(i);
////					System.out.println("entered");
////					Iterator itera = obj.keySet().iterator();
////					while(itera.hasNext()){
////						String key = (String) itera.next();
////						Object value = obj.get(key);
////						System.out.println((String) value);
////						if(key.equals("code")){
////							System.out.println("entered most");
////							doc.setType((String) value);
////						}
////					}
////				}
////				JSONObject jsonObj = (JSONObject) json.parse(split[1]);
////				JSONArray containArray = (JSONArray) jsonObj.get("contained");
////				JSONObject format = (JSONObject) jsonObj.get("format");
////				JSONArray formatArray = (JSONArray) format.get("coding");
////				for(int i = 0 ; i < formatArray.size() ; i++){
////					JSONObject obj = (JSONObject) formatArray.get(i);
////					System.out.println("entered");
////					Iterator itera = obj.keySet().iterator();
////					while(itera.hasNext()){
////						String key = (String) itera.next();
////						Object value = obj.get(key);
////						System.out.println((String) value);
////						if(key.equals("code")){
////							System.out.println("entered most");
////							doc.setFormat((String) value);
////						}
////					}
////				}
////				JSONObject type = (JSONObject) jsonObj.get("type");
////				JSONArray typeArray = (JSONArray) type.get("coding");
////				for(int i = 0 ; i < typeArray.size() ; i++){
////					JSONObject obj = (JSONObject) typeArray.get(i);
////					System.out.println("entered");
////					Iterator itera = obj.keySet().iterator();
////					while(itera.hasNext()){
////						String key = (String) itera.next();
////						Object value = obj.get(key);
////						System.out.println((String) value);
////						if(key.equals("code")){
////							System.out.println("entered most");
////							doc.setType((String) value);
////						}
////					}
////				}
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			Calendar ca = Calendar.getInstance();
//			Date dt = ca.getTime();
//			SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
//			doc.setTime(sf.format(dt));
////			doc.setBinary(split[1].getBytes());
//			em.persist(doc);
//			return null;
//		}
//		
//		String cont;
//		String upatient;
//		String binary;
//		String utel;
//		@SuppressWarnings({ "deprecation", "unchecked" })
//		@GET
//		@Path("DocumentReference/_search")//FHIR 규격에 맞게 변환해야 함
////		@Consumes(MediaType.APPLICATION_JSON)
//		public Response documentSearch(@QueryParam("user") String user) throws IOException, ParseException {
////			 @QueryParam("type") String type
////			, @QueryParam("period:before") String before, @QueryParam("period:after") String after
////			System.out.println(format+type);
//			System.out.println(user);
//			ArrayList<String> contarr = new ArrayList();
//			ArrayList<String> binarr = new ArrayList();
////			ArrayList<JSONObject> contobj = new ArrayList();
////			ArrayList<JSONObject> binobj = new ArrayList();
//			String content = "";
//			JSONObject bundleObject = new JSONObject(); 
//			JSONArray cell = new JSONArray();
//			JSONObject obj1 = new JSONObject();
//			bundleObject.put("resourceType", "Bundle");
//			bundleObject.put("id", "bundle-transaction");
//			bundleObject.put("type", "transaction");
//			if(user != null){
//				PreparedStatement ps = null;
//				Connection conn = null;
//				ResultSet rs = null;
//				PreparedStatement ps2 = null;
//				Connection conn2 = null;
//				ResultSet rs2 = null;
//				if(ds != null)
//				{
//	                try{
//	                	conn = ds.getConnection();
//	                	ps2 = conn.prepareStatement("select patient, telecom from patientinfo where id= ?");
//	                	ps2.setString(1, user);
//	                	rs2 = ps2.executeQuery();
//	                	if(rs2.next()){
//	                		upatient = IOUtils.toString(rs2.getBytes(1), "UTF-8");
//	                		utel = rs2.getString(2);
//	                		obj1.put("resource", upatient);
//	                		cell.add(obj1);
//	                		System.out.println(upatient);
//	                	}
//	                	ps = conn.prepareStatement("select content, content2 from testdb where user= ?");
//	                	ps.setString(1, user);
//	                	rs = ps.executeQuery();
//	                	while(rs.next())//여러개 묶어서 보내는 방법 JSON 이용해보자
//	                	{
//	                		cont = IOUtils.toString(rs.getBytes(1));
//	                		binary = IOUtils.toString(rs.getBytes(2));
//	                		JSONParser json = new JSONParser();
//	                		JSONObject con = (JSONObject) json.parse(binary);
//	                		Object base64ob = con.get("content");
//							String base64bstr = (String) base64ob;
//							//db에 스트링으로 저장시킨거 다시 빼내려고 이랬는데 어휴 안돼서 다시 바이트저장으로 갈아탐
////							byte[] plainb = base64bstr.getBytes();
////							byte[] base64b = Base64.encodeBase64(plainb);
////							String base64s = new String(base64b);
////							con.remove("content");
////							con.put("content", base64s);
//							binary = con.toString();
//	                		contarr.add(cont);
////	                		binarr.add(binary);  이거는 binary 콘텐츠
//	                		content = IOUtils.toString(cont.getBytes(), "UTF-8");
//	                		System.out.println("content:"+cont);
////	                		System.out.println("binary:"+binary);
//	                	}	
//	                }
//	                catch(SQLException sqle){
//	                	sqle.printStackTrace();
//	                }
//				}
//			}
////			System.out.println("content:"+contarr.get(0));
////			System.out.println("binary:"+binarr.get(0));
////			System.out.println("content:"+contarr.get(1));
////			System.out.println("binary:"+binarr.get(1));
//			
////			JSONObject obj3 = new JSONObject();
////			
////			obj3.put("resource", binarr.get(0));
////			
////			cell.add(obj3);
//			for(int i = 0 ; i < contarr.size() ; i++){
////				JSONObject obj = new JSONObject();  // 이거는 binary
//				JSONObject obj2 = new JSONObject();
////				JSONArray arr = new JSONArray();
////				arr.add(contarr.get(i));
////				obj.put("resource", binarr.get(i));  // 이거는 binary
////				cell.add(obj);  // 이거는 binary
//				obj2.put("resource", contarr.get(i));
//				cell.add(obj2);
//			}
//			
////			JSONObject obj = new JSONObject();
////			obj.put("resource", contarr.get(0));
////			cell.add(obj);
//			bundleObject.put("entry", cell);
//			String finali = bundleObject.toString();
////			System.out.println(finali);
//			return Response.status(201).entity(finali).build();
//		}
//	}
//
//}
