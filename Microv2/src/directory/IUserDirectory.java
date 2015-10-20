package directory;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface IUserDirectory {
	
	public void addUser(FinalUser user);
	public void removeUser(FinalUser user);
	public List<FinalUser> lookUpAllUsers();
	public NewsGroupRights lookUpAUserRights(FinalUser user);
	public void updateAUserRights(FinalUser user, NewsGroupRights rights);
}
