package directory;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


public class NewsGroupRights implements Serializable {
	
	
	private boolean readNewsGroup;
	private boolean writeNewsGoup;
	
	
	public NewsGroupRights () {
		this.readNewsGroup = false;
		this.writeNewsGoup = false;
	}
	
	public NewsGroupRights (boolean readNewsGroup, boolean writeNewsGoup) {
		this.readNewsGroup = readNewsGroup;
		this.writeNewsGoup = writeNewsGoup;
	}	
	
	public boolean getReadAccess() {
		return this.readNewsGroup;
	}
	
	public void setReadAccess() {
		this.readNewsGroup = true;
	}
	
	public void unsetReadAccess() {
		this.readNewsGroup = false;
	}
	
	public boolean getWriteAccess() {
		return this.writeNewsGoup;
	}
	
	public void setWriteAccess() {
		this.writeNewsGoup = true;
	}
	
	public void unsetWriteAccess() {
		this.writeNewsGoup = false;
	}

	
}
