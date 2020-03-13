package in.cropdata.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "app_resource")
public class AppResource {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private int id;
	
	@Column(name = "ParentID", nullable = false, columnDefinition = "int default 0")
	private int parentId;
	
//	@Column(name = "GroupID")
//	private int groupId;
//	
//	@Transient
//	private String groupName;
	
	@Column(name = "ResourceURL")
	private String resourceUrl;
	
	@Column(name = "ResourceName")
	private String resourceName;
	
	@Column(name = "ResourceGroupID")
	private Integer resourceGroupId;
	
	

}
