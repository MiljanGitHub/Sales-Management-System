package sales.management.system.dtoResponse;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Data
@Setter
@SuperBuilder
public class Partner {
	
	
	private int id;
	private String name;
	private String address;
	private String phone;
	private String email;
	private String taxNumber;
	
	public Partner(int id, String name, String address, String phone, String email, String taxNumber) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.taxNumber = taxNumber;
	}
	
	
	
}
