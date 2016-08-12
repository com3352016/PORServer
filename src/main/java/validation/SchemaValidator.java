package validation;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class SchemaValidator {



//	public static void main(String[] args) {
//
//		// String xsdPath = "./src/main/resources/fhir-all-xsd/patient.xsd";
//		String xmlPath = "./src/main/resources/fhirResources/patient(pat2)_error.xml";
//
//		// String xsdPath = "./src/main/resources/output/patient.xsd";
//		String xsdPath = "./src/main/resources/fhir-all-xsd/fhir-all.xsd";
//
//		logger.info(validateXMLSchema(xsdPath, xmlPath) + "");
//	}

	public static String validateXMLSchema(String xsdPath, String xmlPath) {

		try {
			SchemaFactory factory = SchemaFactory
					.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(new File(xsdPath));
			Validator validator = schema.newValidator();

			ByteArrayOutputStream out = new ByteArrayOutputStream();

			validator.validate(new StreamSource(new File(xmlPath)),
					new StreamResult(out));
			String result = out.toString("UTF-8");

			

		} catch (SAXParseException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
//			logger.info(e.toString());
//			logger.info(e.getColumnNumber()+e.getLineNumber()+e.getLocalizedMessage());
			
			String errorMessage = "line Number: "+e.getLineNumber()+", Column Number : " + e.getColumnNumber() +", Error Message: " + e.getLocalizedMessage();
			
//			logger.info(errorMessage);
			
			return errorMessage;
			
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "internal Error";
		} 

		return "true";

	}
	
	public static String validateXMLSchema(String xsdPath, InputStream xml) {
		
		try {
			SchemaFactory factory = SchemaFactory
					.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(new File(xsdPath));
			Validator validator = schema.newValidator();

			ByteArrayOutputStream out = new ByteArrayOutputStream();

			validator.validate(new StreamSource(xml),
					new StreamResult(out));
			String result = out.toString("UTF-8");

			

		} catch (SAXParseException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
//			logger.info(e.toString());
//			logger.info(e.getColumnNumber()+e.getLineNumber()+e.getLocalizedMessage());
			
			String errorMessage = "line Number: "+e.getLineNumber()+", Column Number : " + e.getColumnNumber() +", Error Message: " + e.getLocalizedMessage();
			
//			logger.info(errorMessage);
			
			return errorMessage;
			
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "internal Error";
		} 

		return "true";
	}

}
