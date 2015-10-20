package directory;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class FinalUser implements Serializable {
	
	@Id
	private String userName;
	private NewsGroupRights newsGroupRights;
	
	public FinalUser () {}
	
	public FinalUser (String userName) {
		this.userName = userName;
		this.newsGroupRights = new NewsGroupRights();
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public void updateUserName(String userName) {
		this.userName = userName;
	}
	
	public NewsGroupRights getUserRights() {
		return this.newsGroupRights;
	}
	
	public void setUserRights(NewsGroupRights newsGroupRights) {
		this.newsGroupRights = newsGroupRights;
	}
}
