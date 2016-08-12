package restful;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.hl7.fhir.instance.model.Address;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.Coding;
import org.hl7.fhir.instance.model.Contact;
import org.hl7.fhir.instance.model.DocumentReference;
import org.hl7.fhir.instance.model.HumanName;
import org.hl7.fhir.instance.model.Patient;
import org.hl7.fhir.instance.model.Practitioner;
import org.hl7.fhir.instance.model.ResourceReference;
import org.hl7.fhir.instance.model.UriType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
//import org.hl7.fhir.instance.client.ClientUtils;
//import org.hl7.fhir.instance.client.EFhirClientException;
//import org.hl7.fhir.instance.formats.ParserBase.ResourceOrFeed;
//import org.hl7.fhir.instance.model.DateTime;
//???

public class TabelMapping {
//	public byte[] CdaToFhir(byte[] input){
//		try {
//			String s1 = IOUtils.toString(input, "UTF-8");
//			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
//			DocumentBuilder db = dbf.newDocumentBuilder();  
//			Document doc = db.parse(s1);
//			doc.getDocumentElement().normalize();  
//			System.out.printf("Root element:%s\n", doc.getDocumentElement().getNodeName());  
//			NodeList itemNodeList = doc.getElementsByTagName("item");
//			for (int s = 0; s < itemNodeList.getLength(); s++) {  
//				   Node itemNode = itemNodeList.item(s);  
//				   if (itemNode.getNodeType() == Node.ELEMENT_NODE) {  
//				    Element itemElement = (Element)itemNode;  
//				    NodeList titleNodeList = itemElement.getElementsByTagName("title");  
//				    Element titleElement = (Element)titleNodeList.item(0);  
//				    NodeList childTitleNodeList = titleElement.getChildNodes();  
//				    System.out.printf("[title : %s]\n", ((Node)childTitleNodeList.item(0)).getNodeValue());  
//				    NodeList linkNodeList = itemElement.getElementsByTagName("link");  
//				    Element linkElement = (Element) linkNodeList.item(0);  
//				    NodeList childLinkNodeList = linkElement.getChildNodes();  
//				    System.out.printf("[link : %s]\n", ((Node)childLinkNodeList.item(0)).getNodeValue());  
//				   }  
//			}
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	public DocumentReference CdaToFhir(byte[] input){
        DocumentReference docRef = new DocumentReference();
        try {
        	//	String s1 = IOUtils.toString(input, "UTF-8");
                InputStream in = new ByteArrayInputStream(input);
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
                DocumentBuilder db = dbf.newDocumentBuilder();  
                Document doc = db.parse(in);
                docRef.setMimeTypeSimple("application/hl7-v3+xml");
                doc.getDocumentElement().normalize();  
                System.out.println("Root element:"+doc.getDocumentElement().getNodeName());  
                NodeList itemNodeList = doc.getElementsByTagName("ClinicalDocument");
                System.out.println(itemNodeList.item(0).getChildNodes().getLength());
                for(int s = 0 ; s < itemNodeList.item(0).getChildNodes().getLength() ; s++){
                    if(itemNodeList.item(0).getChildNodes().item(s).getNodeName().equals("recordTarget")){
                       Node n = itemNodeList.item(0).getChildNodes().item(s); 
                       Node nc = n.getChildNodes().item(1);
                       System.out.println(nc.getNodeName());
                       ResourceReference refv = new ResourceReference();
                       docRef.setSubject(refv);
                       refv.setReferenceSimple("#a1");
                       Patient p = new Patient();
                       p.setXmlId("a1");
                       String given="", family="", suffix="", birthDate="";
                       for(int m = 0 ; m < nc.getChildNodes().getLength() ; m++){
                           if(nc.getChildNodes().item(m).getNodeName().equals("patient")){
                               Node ncc = nc.getChildNodes().item(m);
                               for(int nn = 0 ; nn < ncc.getChildNodes().item(0).getChildNodes().getLength() ; nn++){
                                   if(ncc.getChildNodes().item(0).getChildNodes().item(nn).getNodeName().equals("given")){
                                       given = ncc.getChildNodes().item(0).getChildNodes().item(nn).getChildNodes().item(0).getNodeValue();
                                   }
                                   if(ncc.getChildNodes().item(0).getChildNodes().item(nn).getNodeName().equals("family")){
                                       family = ncc.getChildNodes().item(0).getChildNodes().item(nn).getChildNodes().item(0).getNodeValue();
                                   }
                                   if(ncc.getChildNodes().item(0).getChildNodes().item(nn).getNodeName().equals("suffix")){
                                       suffix = ncc.getChildNodes().item(0).getChildNodes().item(nn).getChildNodes().item(0).getNodeValue();
                                   }
                               }
                               if(ncc.getChildNodes().item(m).getNodeName().equals("birthTime")){
                                   birthDate = ((Element)ncc.getChildNodes().item(m)).getAttribute("value");
                               }
                               if(ncc.getChildNodes().item(m).getNodeName().equals("administrativeGenderCode")){
                                   CodeableConcept cop = new CodeableConcept();
                                   Coding copp = cop.addCoding();
                                   String gender = ((Element)ncc.getChildNodes().item(m)).getAttribute("code");
                                   if(gender.equals("M")){
                                       copp.setCodeSimple(gender);
                                       copp.setDisplaySimple("Male");
                                   }else if(gender.equals("F")){
                                       copp.setCodeSimple(gender);
                                       copp.setDisplaySimple("Female");
                                   }
                                   p.setGender(cop);
                               }
                           }
                           if(nc.getChildNodes().item(m).getNodeName().equals("telecom")){
                               String tele = ((Element)nc.getChildNodes().item(m)).getAttribute("value");
                               String place = ((Element)nc.getChildNodes().item(m)).getAttribute("use");
                               Contact cont = p.addTelecom();
                               if(place.equals("HP")){
                                   cont.setUseSimple(Contact.ContactUse.home);
                               }else if(place.equals("WP")){
                                   cont.setUseSimple(Contact.ContactUse.work);
                               }else if(place.equals("MP")){
                                   cont.setUseSimple(Contact.ContactUse.mobile);
                               }
                               cont.setValueSimple(tele);
                           }
                           if(nc.getChildNodes().item(m).getNodeName().equals("address")){
                               String place = ((Element)nc.getChildNodes().item(m)).getAttribute("use");
                               Node ncc = nc.getChildNodes().item(m);
                               Address add = p.addAddress();
                               if(place.equals("HP")){
                                   add.setUseSimple(Address.AddressUse.home);
                               }else if(place.equals("WP")){
                                   add.setUseSimple(Address.AddressUse.work);
                               }
                               for(int nn = 0 ; nn < ncc.getChildNodes().getLength() ; nn++){
                                   if(ncc.getChildNodes().item(nn).getNodeName().equals("streetAddressLine")){
                                       add.addLineSimple(ncc.getChildNodes().item(nn).getChildNodes().item(0).getNodeValue());
                                   }
                                   if(ncc.getChildNodes().item(nn).getNodeName().equals("state")){
                                       add.setStateSimple(ncc.getChildNodes().item(nn).getChildNodes().item(0).getNodeValue());
                                   }
                                   if(ncc.getChildNodes().item(nn).getNodeName().equals("city")){
                                       add.setCitySimple(ncc.getChildNodes().item(nn).getChildNodes().item(0).getNodeValue());
                                   }
                                   if(ncc.getChildNodes().item(nn).getNodeName().equals("postalCode")){
                                       add.setZipSimple(ncc.getChildNodes().item(nn).getChildNodes().item(0).getNodeValue());
                                   }
                               }
                           }
                       }
                       HumanName hu = p.addName();
                       hu.addFamilySimple(family);
                       hu.addGivenSimple(given);
                       hu.addSuffixSimple(suffix);
//                       DateAndTime dat = new DateAndTime(birthDate);
//                       p.setBirthDateSimple(dat);
                       docRef.getContained().add(p);
                    }
                    if(itemNodeList.item(0).getChildNodes().item(s).getNodeName().equals("author")){
                        ResourceReference rref = docRef.addAuthor();
                        rref.setReferenceSimple("#a2");
                        Practitioner practi = new Practitioner();
                        practi.setXmlId("a2");
                        Node n = itemNodeList.item(0).getChildNodes().item(s);
                        for(int ss = 0 ; ss < n.getChildNodes().getLength() ; ss++){
                            if(n.getChildNodes().item(ss).getNodeName().equals("assignedAuthor")){
                                Node nc = n.getChildNodes().item(ss);
                                for(int sss = 0 ; sss < nc.getChildNodes().getLength() ; sss++){
                                    if(nc.getChildNodes().item(sss).getNodeName().equals("assignedPerson")){
                                        Node ncc = nc.getChildNodes().item(sss);
                                        String given="", family="", prefix="";
                                        for(int nn = 0 ; nn < ncc.getChildNodes().item(0).getChildNodes().getLength() ; nn++){
                                           if(ncc.getChildNodes().item(0).getChildNodes().item(nn).getNodeName().equals("given")){
                                               given = ncc.getChildNodes().item(0).getChildNodes().item(nn).getChildNodes().item(0).getNodeValue();
                                           }
                                           if(ncc.getChildNodes().item(0).getChildNodes().item(nn).getNodeName().equals("family")){
                                               family = ncc.getChildNodes().item(0).getChildNodes().item(nn).getChildNodes().item(0).getNodeValue();
                                           }
                                           if(ncc.getChildNodes().item(0).getChildNodes().item(nn).getNodeName().equals("prefix")){
                                               prefix = ncc.getChildNodes().item(0).getChildNodes().item(nn).getChildNodes().item(0).getNodeValue();
                                           }
                                        }
                                        HumanName hun = new HumanName();
                                        hun.addFamilySimple(family);
                                        hun.addGivenSimple(given);
                                        hun.addPrefixSimple(prefix);
                                        practi.setName(hun);
                                    }
                                    if(nc.getChildNodes().item(sss).getNodeName().equals("addr")){
                                        String place = ((Element)nc.getChildNodes().item(sss)).getAttribute("use");
                                        Node ncc = nc.getChildNodes().item(sss);
                                        Address add = new Address();
                                        if(place.equals("HP")){
                                            add.setUseSimple(Address.AddressUse.home);
                                        }else if(place.equals("WP")){
                                            add.setUseSimple(Address.AddressUse.work);
                                        }
                                        for(int nn = 0 ; nn < ncc.getChildNodes().getLength() ; nn++){
                                            if(ncc.getChildNodes().item(nn).getNodeName().equals("streetAddressLine")){
                                                add.addLineSimple(ncc.getChildNodes().item(nn).getChildNodes().item(0).getNodeValue());
                                            }
                                            if(ncc.getChildNodes().item(nn).getNodeName().equals("state")){
                                                add.setStateSimple(ncc.getChildNodes().item(nn).getChildNodes().item(0).getNodeValue());
                                            }
                                            if(ncc.getChildNodes().item(nn).getNodeName().equals("city")){
                                                add.setCitySimple(ncc.getChildNodes().item(nn).getChildNodes().item(0).getNodeValue());
                                            }
                                            if(ncc.getChildNodes().item(nn).getNodeName().equals("postalCode")){
                                                add.setZipSimple(ncc.getChildNodes().item(nn).getChildNodes().item(0).getNodeValue());
                                            }
                                        }
                                        practi.setAddress(add);
                                    }
                                    if(nc.getChildNodes().item(sss).getNodeName().equals("telecom")){
                                        String tele = ((Element)nc.getChildNodes().item(sss)).getAttribute("value");
                                        String place = ((Element)nc.getChildNodes().item(sss)).getAttribute("use");
                                        Contact cont = practi.addTelecom();
                                        if(place.equals("HP")){
                                            cont.setUseSimple(Contact.ContactUse.home);
                                        }else if(place.equals("WP")){
                                            cont.setUseSimple(Contact.ContactUse.work);
                                        }else if(place.equals("MP")){
                                            cont.setUseSimple(Contact.ContactUse.mobile);
                                        }
                                        cont.setValueSimple(tele);
                                    }
                                }
                            }
                        }
                        docRef.getContained().add(practi);
                    }
                    if(itemNodeList.item(0).getChildNodes().item(s).getNodeName().equals("custodian")){
                        
                    }
                    if(itemNodeList.item(0).getChildNodes().item(s).getNodeName().equals("title")){
                       Node resul = itemNodeList.item(0).getChildNodes().item(s);
                       String resulv = resul.getChildNodes().item(0).getNodeValue();
                       if(resulv.equals("진료기록요약지")){
                           CodeableConcept conce = new CodeableConcept();
                           Coding concep = conce.addCoding();
                           concep.setCodeSimple("Summary");
                           concep.setDisplaySimple(resulv);
                           UriType u= docRef.addFormatSimple("CRS");
                           docRef.setType(conce);
                           System.out.println(docRef.getType().getTextSimple());
                       }
                    }
                }
        } catch (Exception e) {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
        }
        System.out.println(docRef.getAuthor().get(0));
        return docRef;
    }
}
