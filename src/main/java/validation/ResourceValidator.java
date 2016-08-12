package validation;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
//import javax.xml.validation.Validator;

import org.hl7.fhir.instance.formats.XmlComposer;
import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.OperationOutcome;
import org.hl7.fhir.instance.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.instance.model.OperationOutcome.OperationOutcomeIssueComponent;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.api.IBaseBundle;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.jdom.Document;
import org.w3c.dom.Node;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.api.Bundle;
import ca.uhn.fhir.model.api.BundleEntry;
import ca.uhn.fhir.model.api.IResource;
import ca.uhn.fhir.parser.LenientErrorHandler;
import ca.uhn.fhir.parser.StrictErrorHandler;
import ca.uhn.fhir.validation.FhirValidator;
import ca.uhn.fhir.validation.IValidatorModule;
import ca.uhn.fhir.validation.SchemaBaseValidator;
import ca.uhn.fhir.validation.SingleValidationMessage;
import ca.uhn.fhir.validation.ValidationResult;
import ca.uhn.fhir.validation.schematron.SchematronBaseValidator;
import org.hl7.fhir.instance.validation.*;


//하피 라이브러리 벨리데이션 법 발견! JBOSS에 schematron 2.7.1 jar파일 압축해제 해놔야한다
//json=>xml 수동 변환파일 개발필요가 있다
public class ResourceValidator {

	SchematronValidator schematronValidator = new SchematronValidator();
	SchemaValidator schemaValidator = new SchemaValidator();
	
	public String bfvalidating(String input) throws Exception{
		FhirContext ctx2 = FhirContext.forDstu2();
		FhirValidator validator = ctx2.newValidator();
//		IValidatorModule module1 = new SchemaBaseValidator(ctx2);
		IValidatorModule module2 = new SchematronBaseValidator(ctx2);
//		validator.registerValidatorModule(module1);
		validator.registerValidatorModule(module2);
//		validator.setValidateAgainstStandardSchema(true);
//		validator.setValidateAgainstStandardSchematron(true);
		System.out.println("여기까진 되었다?");
		Bundle bundle = new Bundle();
		//json validate
//		try{
//			StrictErrorHandler sh = new StrictErrorHandler();
//	        ctx2.setParserErrorHandler(sh);
//	        bundle = ctx2.newJsonParser().parseBundle(input);
//		} catch(Exception e){
//			return "parseError";
//		}
		//xml change
//		String result = ctx2.newXmlParser().setPrettyPrint(true).encodeBundleToString(bundle);
//		String hed = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
//		String tot = hed+result;
//		ValidationResult vr = validator.validateWithResult(tot);
//		List<SingleValidationMessage> lsvm = vr.getMessages();				        
//		org.w3c.dom.Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
//		Node root;
//		root = document.createElement("ValidationError");
//		root.appendChild(arg0);
//		document.appendChild(root);
		OperationOutcome opoutcome = new OperationOutcome();
		return "validationClear";
		//operationoutcome
//		System.out.println(lsvm.size());
//		if(lsvm.size()==0){
//			return "validationClear";
//		}
//		for(int i=0;i<lsvm.size();i++){
//			OperationOutcomeIssueComponent opcom = opoutcome.addIssue();
//			opcom.addLocationSimple(lsvm.get(i).getLocationString());
//			opcom.setSeveritySimple(IssueSeverity.error);
//			opcom.setDetailsSimple(lsvm.get(i).getMessage());
//			System.out.println(lsvm.get(i).getSeverity());
//			System.out.println(lsvm.get(i).getLocationString());
//			System.out.println(lsvm.get(i).getMessage());
//		}
//		System.out.println(opoutcome.getIssue().size());
		//error return
//		org.hl7.fhir.instance.formats.XmlComposer x2 = new org.hl7.fhir.instance.formats.XmlComposer();
//        String st = x2.composeString(opoutcome, true);
//        System.out.println(st);
//		return st;
	}
	
	
	public String fhirResourceValidation() {

//		String fhirResourceSchema = "./src/main/resources/fhir-all-xsd/fhir-all.xsd";
		String fhirResourceSchematron = "C:/My_Dev/workspace/PORServer/src/main/resources/fhir-all-xsd/fhir-invariants.sch";
		String iso_svrl_for_xslt2 = "C:/My_Dev/workspace/PORServer/src/main/resources/iso-schematron-xslt2/iso_svrl_for_xslt2.xsl";
		String outputFilePath = "C:/My_Dev/workspace/PORServer/src/main/resources/output";
		String fhirResource = "C:/My_Dev/workspace/PORServer/src/main/resources/fhirResources/patient(pat2)_error.xml";

//		logger.info("resource schematron path : " + fhirResourceSchematron);
//		logger.info("iso_svrl_for_xslt2 file path : " + iso_svrl_for_xslt2);
//		logger.info("output File path : " + outputFilePath);
//		logger.info("fhir resource : " + fhirResource);

		schematronValidator.SchematronValidationOutputFlieName(
				fhirResourceSchematron, iso_svrl_for_xslt2, fhirResource,
				outputFilePath);

		return "";

	}

	public String fhirResourceValidation(String resource) {
		String fhirResourceSchema = "C:/My_Dev/workspace/PORServer/src/main/resources/fhir-all-xsd/fhir-all.xsd";
		String fhirResourceSchematron = "C:/My_Dev/workspace/PORServer/src/main/resources/fhir-all-xsd/fhir-invariants.sch";
		String iso_svrl_for_xslt2 = "C:/My_Dev/workspace/PORServer/src/main/resources/iso-schematron-xslt2/iso_svrl_for_xslt2.xsl";
		String outputFilePath = "C:/My_Dev/workspace/PORServer/src/main/resources/output";
		String fhirResource = "C:/My_Dev/workspace/PORServer/src/main/resources/fhirResources/patient(pat2)_error.xml";
		
//		logger.info("FHIR resource XSD file path : " + fhirResourceSchema);
//		logger.info("FHIR resource schematron path : " + fhirResourceSchematron);
//		logger.info("iso_svrl_for_xslt2 file path : " + iso_svrl_for_xslt2);
//		logger.info("output file path : " + outputFilePath);
//		logger.info("fhir resource : " + fhirResource);

		String result = "";
		
		// Convert String type to InputStream for resource
		InputStream resourceInputStream1 = new ByteArrayInputStream(
				resource.getBytes());
		
		// Schema Validation
		String schemaValidationResult = SchemaValidator.validateXMLSchema(
				fhirResourceSchema, resourceInputStream1);

//		logger.info(schemaValidationResult);
		
//		// Convert String type to InputStream for resource
//		InputStream resourceInputStream2 = new ByteArrayInputStream(
//				resource.getBytes());
//
//		// First Schematron Validation
//		String schematronValidationResult = schematronValidator
//				.SchematronValidationOutputString(fhirResourceSchematron,
//						iso_svrl_for_xslt2, resourceInputStream2);
//
//		logger.info(schematronValidationResult);

		if(schemaValidationResult.equalsIgnoreCase("true")) {
			// Second Schematron Validation
			// Convert String type to InputStream for resource
			InputStream resourceInputStream2 = new ByteArrayInputStream(
					resource.getBytes());
			
			String schematronValidationResult = schematronValidator
					.SchematronValidationOutputString(fhirResourceSchematron,
							iso_svrl_for_xslt2, resourceInputStream2);

//			logger.info(schematronValidationResult);
			
			result = schematronValidationResult;
			
			
		} else {
			
//			InputStream resourceInputStream2 = new ByteArrayInputStream(
//					resource.getBytes());
//			
//			String schematronValidationResult = schematronValidator
//					.SchematronValidationOutputString(fhirResourceSchematron,
//							iso_svrl_for_xslt2, resourceInputStream2);

//			logger.info(schematronValidationResult);
			
//			result = schematronValidationResult;
			result = schemaValidationResult;
			
		}
		
		return result;

	}

}
