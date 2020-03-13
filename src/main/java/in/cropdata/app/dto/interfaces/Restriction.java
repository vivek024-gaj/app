package in.cropdata.app.dto.interfaces;

//interface to get only required data
public interface Restriction{
    public int getID();
    public int getRoleID();
    public int getResourceID();
    public int getResourceGroupID();
    public int getGroupID();
    public String getRoleName();
    public String getParentResource();
    public String getResourceName();
    public String getResourceURL();
    public String getResourceGroupName();
//    public String getGroupName();
}//Restriction