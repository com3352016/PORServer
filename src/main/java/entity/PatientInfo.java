package entity;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import java.lang.Override;

@Entity
public class PatientInfo implements Serializable
{

   @Id
   private @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   Long id = null;
   @Version
   private @Column(name = "version")
   int version = 0;

   @Column
   private byte[] patient;
   
   @Column
   private String birthDate;
   
   @Column
   private String gender;
   
   @Column
   private String fname;
   
   @Column
   private String gname;
   
   @Column
   private String telecom;
   
   @Column
   private String email;
   
   @Column
   private String password;

   public String getPassword() {
	return password;
   }

   public void setPassword(String password) {
	   this.password = password;
   }

public Long getId()
   {
      return this.id;
   }

   public void setId(final Long id)
   {
      this.id = id;
   }

   public int getVersion()
   {
      return this.version;
   }

   public void setVersion(final int version)
   {
      this.version = version;
   }

   @Override
   public boolean equals(Object that)
   {
      if (this == that)
      {
         return true;
      }
      if (that == null)
      {
         return false;
      }
      if (getClass() != that.getClass())
      {
         return false;
      }
      if (id != null)
      {
         return id.equals(((PatientInfo) that).id);
      }
      return super.equals(that);
   }

   @Override
   public int hashCode()
   {
      if (id != null)
      {
         return id.hashCode();
      }
      return super.hashCode();
   }

   public byte[] getPatient() {
	   return patient;
   }

   public void setPatient(byte[] patient) {
	   this.patient = patient;
   }

   public String getBirthDate() {
	   return birthDate;
   }

   public void setBirthDate(String birthDate) {
	   this.birthDate = birthDate;
   }
   
   public String getGender() {
	   return gender;
   }

   public void setGender(String gender) {
	   this.gender = gender;
   }

   public String getFname() {
	   return fname;
   }

   public void setFname(String fname) {
	   this.fname = fname;
   }

   public String getGname() {
	   return gname;
   }

   public void setGname(String gname) {
	   this.gname = gname;
   }

   public String getTelecom() {
	   return telecom;
   }

   public void setTelecom(String telecom) {
	   this.telecom = telecom;
   }

   public String getEmail() {
	   return email;
   }

   public void setEmail(String email) {
	   this.email = email;
   }
}