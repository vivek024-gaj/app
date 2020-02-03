package in.cropdata.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "app_resource_groups")
public class AppResourceGroup {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private int id;
	
	@Column(name = "ResourceGroupName")
	private String resourceGroupName;
	
	@Column(name = "MenuPlacement")
	private int menuPlacement;

}
