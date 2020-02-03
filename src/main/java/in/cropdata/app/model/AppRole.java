package in.cropdata.app.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import in.cropdata.app.dto.interfaces.Restriction;
import lombok.Data;

@Data
@Entity(name = "app_roles")
public class AppRole {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "Description")
	private String description;
	
	@Transient
	private List<Restriction> restrictions = new ArrayList<>();
	
}
