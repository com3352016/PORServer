//package entity;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Version;
//
//@Entity
//public class RDocumentReference {
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "id", updatable = false, nullable = false)
//	private Long id;
//	@Version
//	@Column(name = "version")
//	private int version;
//
//	@Column
//    private byte[] binary;
//	
//	@Column
//    private String user;
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public byte[] getBinary() {
//		return binary;
//	}
//
//	public void setBinary(byte[] binary) {
//		this.binary = binary;
//	}
//
//	public String getUser() {
//		return user;
//	}
//
//	public void setUser(String user) {
//		this.user = user;
//	}
//}
