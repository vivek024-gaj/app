/**
 * 
 */
package in.cropdata.app.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * @author Vivek Gajbhiye - Cropdata
 *
 * 01-Feb-2020
 */
@Data
public class NavData {

    String name;
    String url;
    String icon;
    List<NavData> children =  new ArrayList<NavData>();
}
