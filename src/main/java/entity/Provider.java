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
public class Provider implements Serializable
{

   @Id
   private @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   Long id = null;
   @Version
   private @Column(name = "version")
   int version = 0;

   @Column
   private String name;

   @Column
   private String url;

   @Column
   private String patient_signin;

   @Column
   private String oauth2_authorize_uri;

   @Column
   private String oauth2_token_uri;

   @Column
   private String api_search_uri;

   @Column
   private String oauth2_registration_uri;

   @Column
   private String oauth2_introspect_uri;

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
         return id.equals(((Provider) that).id);
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

   public String getName()
   {
      return this.name;
   }

   public void setName(final String name)
   {
      this.name = name;
   }

   public String getUrl()
   {
      return this.url;
   }

   public void setUrl(final String url)
   {
      this.url = url;
   }

   public String getPatient_signin()
   {
      return this.patient_signin;
   }

   public void setPatient_signin(final String patient_signin)
   {
      this.patient_signin = patient_signin;
   }

   public String getOauth2_authorize_uri()
   {
      return this.oauth2_authorize_uri;
   }

   public void setOauth2_authorize_uri(final String oauth2_authorize_uri)
   {
      this.oauth2_authorize_uri = oauth2_authorize_uri;
   }

   public String getOauth2_token_uri()
   {
      return this.oauth2_token_uri;
   }

   public void setOauth2_token_uri(final String oauth2_token_uri)
   {
      this.oauth2_token_uri = oauth2_token_uri;
   }

   public String getApi_search_uri()
   {
      return this.api_search_uri;
   }

   public void setApi_search_uri(final String api_search_uri)
   {
      this.api_search_uri = api_search_uri;
   }

   public String getOauth2_registration_uri()
   {
      return this.oauth2_registration_uri;
   }

   public void setOauth2_registration_uri(final String oauth2_registration_uri)
   {
      this.oauth2_registration_uri = oauth2_registration_uri;
   }

   public String getOauth2_introspect_uri()
   {
      return this.oauth2_introspect_uri;
   }

   public void setOauth2_introspect_uri(final String oauth2_introspect_uri)
   {
      this.oauth2_introspect_uri = oauth2_introspect_uri;
   }

   public String toString()
   {
      String result = "";
      if (name != null && !name.trim().isEmpty())
         result += name;
      if (url != null && !url.trim().isEmpty())
         result += " " + url;
      if (patient_signin != null && !patient_signin.trim().isEmpty())
         result += " " + patient_signin;
      if (oauth2_authorize_uri != null && !oauth2_authorize_uri.trim().isEmpty())
         result += " " + oauth2_authorize_uri;
      if (oauth2_token_uri != null && !oauth2_token_uri.trim().isEmpty())
         result += " " + oauth2_token_uri;
      if (api_search_uri != null && !api_search_uri.trim().isEmpty())
         result += " " + api_search_uri;
      if (oauth2_registration_uri != null && !oauth2_registration_uri.trim().isEmpty())
         result += " " + oauth2_registration_uri;
      if (oauth2_introspect_uri != null && !oauth2_introspect_uri.trim().isEmpty())
         result += " " + oauth2_introspect_uri;
      return result;
   }
}