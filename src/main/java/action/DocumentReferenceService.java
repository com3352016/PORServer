package action;

import java.text.ParseException;

import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.Coding;
import org.hl7.fhir.instance.model.DateAndTime;
import org.hl7.fhir.instance.model.DateTimeType;
import org.hl7.fhir.instance.model.DocumentReference;
import org.hl7.fhir.instance.model.DocumentReference.DocumentReferenceStatus;
import org.hl7.fhir.instance.model.Identifier;
import org.hl7.fhir.instance.model.Narrative;
import org.hl7.fhir.instance.model.ResourceReference;
import org.hl7.fhir.instance.model.UriType;

public class DocumentReferenceService {
	public void documentRef() throws ParseException{
		//Patient Information
		
		//Document Information
		DocumentReference ref = new DocumentReference();
		//contained
		
		//masterIdentifier
		Identifier idf = new Identifier();
		idf.setSystemSimple("urn:ietf:rfc:3986");
		idf.setValueSimple("urn:oid:1.3.6.1.4.1.21367.2005.3.7");
		ref.setMasterIdentifier(idf);
		//subject
		ResourceReference Rref = new ResourceReference();
		Rref.setReferenceSimple("Patient/xcda");
		ref.setSubject(Rref);
		//type
		CodeableConcept cocep = new CodeableConcept();
		Coding codi = cocep.addCoding();
		codi.setCodeSimple("CRS");
		codi.setDisplaySimple("Clinical Summary Note");
		ref.setType(cocep);
		//author
		ResourceReference Rauth = ref.addAuthor();
		Rauth.setReferenceSimple("#a1");
		//creation time
		DateAndTime dt = new DateAndTime("2005-12-24T09:35:00+11:00");
		ref.setCreatedSimple(dt);
		//indexed time(값 수정해야 함)
		DateAndTime dti = new DateAndTime("2005-12-24T09:43:41+11:00");
		ref.setIndexedSimple(dti);
		//status
		ref.setStatusSimple(DocumentReferenceStatus.current);
		//description
		ref.setDescriptionSimple("Physical");
		//primaryLanguage
		ref.setPrimaryLanguageSimple("KR");
		//mimeType
		ref.setMimeTypeSimple("application/xml");
		//location
		UriType u = new UriType();
		u.setValue("value");
		ref.setLocation(u);
	}
}
