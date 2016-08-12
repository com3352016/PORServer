package action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import entity.Provider;

public class ProviderParse {
	DataSource ds;
	public List<Provider> jsonMake(String name){
		try{
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:jboss/datasources/myds");
		}
		catch (NamingException e){
			
		}
		List<Provider> providers = new ArrayList<Provider>();
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		int index = 0;
		if(ds != null)
		{
            try{
            	conn = ds.getConnection();
				ps = conn.prepareStatement("select name, url, api_search_url, oauth2_authorize_uri, oauth2_introspect_uri, oauth2_registration_uri, oauth2_token_uri, patient_signin from Provider where name= ?");
				ps.setString(1, name);
//				ps.setString(2, url);
                rs = ps.executeQuery();
                while(rs.next())
                {
                	Provider provider = new Provider();
                    provider.setName(rs.getString(1));
                    provider.setUrl(rs.getString(2));
                    provider.setApi_search_uri(rs.getString(3));
                    provider.setOauth2_authorize_uri(rs.getString(4));
                    provider.setOauth2_introspect_uri(rs.getString(5));
                    provider.setOauth2_registration_uri(rs.getString(6));
                    provider.setOauth2_token_uri(rs.getString(7));
                    provider.setPatient_signin(rs.getString(8));
                    providers.add(provider);
                }	
            }
            catch(SQLException sqle){
				sqle.printStackTrace();
			}
		}
		return providers;
	}
}
