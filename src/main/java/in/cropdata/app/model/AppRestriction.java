package in.cropdata.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity(name = "app_restrictions")
public class AppRestriction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "RoleID")
    private int roleId;
    
    @Column(name = "ResourceID")
    private int resourceId;

//    @Column(name = "GroupID")
//    private int groupId;

    @Transient
    private String resourceURI;
    
//    @Transient
//    private String group;
    
}
