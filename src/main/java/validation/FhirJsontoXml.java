package validation;

import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.hl7.fhir.instance.formats.JsonComposer;
import org.hl7.fhir.instance.formats.ResourceOrFeed;
import org.hl7.fhir.instance.formats.XmlComposer;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.validation.FhirValidator;
import ca.uhn.fhir.validation.SingleValidationMessage;
import ca.uhn.fhir.validation.ValidationResult;



public class FhirJsontoXml {
	
	public String JtX(String input) throws TransformerException{
		try {
			
			Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			Node root;
			JSONParser json = new JSONParser();
			System.out.println(input);
//			JSONObject docrefresource = new JSONObject();
			JSONObject jsonObj = (JSONObject) json.parse(input);
			System.out.println(jsonObj.size());
			Iterator mainitera = jsonObj.keySet().iterator();
			Object rtvalue = jsonObj.get("resourceType");
			System.out.println(rtvalue);
			root = document.createElement(rtvalue.toString());
			document.appendChild(root);
			while(mainitera.hasNext()){
				String key = (String) mainitera.next();
				if(key.equals("resourceType")){
					
				} else if(key.equals("entry")){
					JSONArray containArray = (JSONArray) jsonObj.get("entry");
					for(int i = 0 ; i < containArray.size() ; i++){
						JSONObject resource = (JSONObject) containArray.get(i);
						//각 Array마다 element, resource 생성부
						Element subel = document.createElement(key);
						root.appendChild(subel);
						Element subsubel = document.createElement("resource");
						subel.appendChild(subsubel);
						//jsonObj 리소스 부분 가져오기
						JSONObject reresource = (JSONObject) resource.get("resource");
						Object subtitle = reresource.get("resourceType");
						System.out.println(subtitle.toString());
						if(subtitle.toString().equals("Binary")){
							Iterator itera = reresource.keySet().iterator();
							while(itera.hasNext()){
								String inkey = (String) itera.next();
								Object value = reresource.get(inkey);
								JSONObject cont = (JSONObject) value;
								if(cont.size()>0){
									System.out.println(inkey);
								}
								JSONArray cona = (JSONArray) value;
								if(inkey.equals("resourceType")){
									
								} else{
									Element undernod = document.createElement(inkey);
									undernod.setAttribute("value", value.toString());
//									subtitel.appendChild(undernod);
								}
							}
						} else if(subtitle.toString().equals("DocumentReference")){
							
						} else{
							
						}
//						Element subtitel = document.createElement(subtitle.toString());
//						subsubel.appendChild(subtitel);
//						Iterator itera = reresource.keySet().iterator();
//						while(itera.hasNext()){
//							String inkey = (String) itera.next();
//							Object value = reresource.get(inkey);
//							JSONObject cont = (JSONObject) value;
//							if(cont.size()>0){
//								System.out.println(inkey);
//							}
//							JSONArray cona = (JSONArray) value;
//							if(inkey.equals("resourceType")){
//								
//							} else{
//								Element undernod = document.createElement(inkey);
//								undernod.setAttribute("value", value.toString());
//								subtitel.appendChild(undernod);
//							}
//						}
					}
				} else {
					Object rsvalue = jsonObj.get(key);
					System.out.println(rsvalue);
					Element subel = document.createElement(key);
					subel.setAttribute("value", rsvalue.toString());
					root.appendChild(subel);
				}
			}
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(document), new StreamResult(writer));
			String output = writer.getBuffer().toString();
			System.out.println("결과물은:"+output);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
