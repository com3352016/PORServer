package validation;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;



public class SchematronValidator {

//	private static Logger logger = LoggerFactory
//			.getLogger(SchematronValidator.class);

	public String SchematronValidationOutputFlieName(String schematronFileName,
			String iso_svrl_for_xslt2, String xmlFileName,
			String outputFileLocation) {

//		logger.info("schematron file name : " + schematronFileName);
//		logger.info("iso_svrl_for_xslt2 file location : " + iso_svrl_for_xslt2);
//		logger.info("output filelocation : " + outputFileLocation);
//		logger.info("xml file name : " + xmlFileName);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		String now = sdf.format(new Date());

		String standardCompiledXsl = outputFileLocation
				+ "/standardCompiledXsl_" + now + ".xsl";

		this.SaxonXslTransformer(schematronFileName, iso_svrl_for_xslt2,
				standardCompiledXsl);

		String outputFileName = outputFileLocation + "/output_" + now + ".xml";

		this.SaxonXslTransformer(xmlFileName, standardCompiledXsl,
				outputFileName);

		return outputFileName;
	}

//	Output is a file.
//	public String SchematronValidationOutputString(String schematronFileName,
//			String iso_svrl_for_xslt2, String xmlFileName,
//			String outputFileLocation) {
//
//		logger.info("schematron file name : " + schematronFileName);
//		logger.info("iso_svrl_for_xslt2 file location : " + iso_svrl_for_xslt2);
//		logger.info("output filelocation : " + outputFileLocation);
//		logger.info("xml file name : " + xmlFileName);
//
//		String standardCompiledXsl = outputFileLocation
//				+ "/standardCompiledXsl.xsl";
//
//		this.SaxonXslTransformer(schematronFileName, iso_svrl_for_xslt2,
//				standardCompiledXsl);
//
//		String output = this.SaxonXslTransformer(xmlFileName,
//				standardCompiledXsl);
//
//		return output;
//	}
	
	//Output is a result with string type
	public String SchematronValidationOutputString(String schematronFileName, 
			String iso_svrl_for_xslt2, InputStream xml) {

//		logger.info("schematron file name : " + schematronFileName);
//		logger.info("iso_svrl_for_xslt2 file location : " + iso_svrl_for_xslt2);

//		String standardCompiledXsl = "standardCompiledXsl.xsl";
//
//		this.SaxonXslTransformer(schematronFileName, iso_svrl_for_xslt2,
//				standardCompiledXsl);
		
		String standardCompiledXsl = this.SaxonXslTransformer(schematronFileName, iso_svrl_for_xslt2);
		
		InputStream standardCompiledXslInputStream = new ByteArrayInputStream(standardCompiledXsl.getBytes());

		String output = this.SaxonXslTransformer(xml, standardCompiledXslInputStream);

		return output;
	}
	
	private String SaxonXslTransformer(InputStream xml, InputStream xsl) {
		
		String result = "";
		
		try {
			TransformerFactory tfactory = TransformerFactory.newInstance();
			Transformer transformer = tfactory.newTransformer(new StreamSource(
					xsl));

			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			transformer.transform(new StreamSource(xml),
					new StreamResult(out));

			result = out.toString("UTF-8");
			
			// logger.info("result:"+result);


		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;

	}
	
	
	//Output is a file result
	private void SaxonXslTransformer(String xmlFileName, String xslFileName,
			String outputFileName) {

		try {
			TransformerFactory tfactory = TransformerFactory.newInstance();
			Transformer transformer = tfactory.newTransformer(new StreamSource(
					xslFileName));

			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");

//			ByteArrayOutputStream out = new ByteArrayOutputStream();
//			transformer.transform(new StreamSource(xmlFileName),
//					new StreamResult(out));

			// String result = out.toString("UTF-8");
			//
			// logger.info("result:"+result);

			FileOutputStream fos = new FileOutputStream(outputFileName);
			transformer.transform(new StreamSource(xmlFileName),
					new StreamResult(fos));

			fos.flush();
			fos.close();

//			logger.info("\nOutput written to " + outputFileName + "\n");

		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
		
	}

	//Output is String type result 
	private String SaxonXslTransformer(String xmlFileName, String xslFileName) {

		String result = "";

		try {
			TransformerFactory tfactory = TransformerFactory.newInstance();
			Transformer transformer = tfactory.newTransformer(new StreamSource(
					xslFileName));

			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			transformer.transform(new StreamSource(xmlFileName),
					new StreamResult(out));

			result = out.toString("UTF-8");
			//
			// logger.info("result:"+result);

		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}
	
	//Input is InputSream type and Output is String type result
	private String SaxonXslTransformer(InputStream xml, String xslFileName) {
		
//		String result = "";
//
//		try {
//			TransformerFactory tfactory = TransformerFactory.newInstance();
//			Transformer transformer = tfactory.newTransformer(new StreamSource(
//					xslFileName));
//
//			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
//			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
//			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//
//			ByteArrayOutputStream out = new ByteArrayOutputStream();
//			transformer.transform(new StreamSource(xml),
//					new StreamResult(out));
//
//			result = out.toString("UTF-8");
//			//
//			// logger.info("result:"+result);
//
//		} catch (TransformerConfigurationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (TransformerException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return result;
		
		String result = "";
		
		try {
			File xsl = new File(xslFileName);
			InputStream xslInputStream = new FileInputStream(xsl);
			
			result = this.SaxonXslTransformer(xml, xslInputStream);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}

}
