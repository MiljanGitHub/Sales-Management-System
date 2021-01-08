package sales.management.system.model;


import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "BussinesYear")
@Table(name = "bussines_year")
@NoArgsConstructor
@Data
@Setter
public class BussinesYear {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="bussines_year_id", unique = true, nullable = false)
	private int id;

	@Column(nullable = false, length = 4)
	private int year;

	@Column(nullable = false)
	private boolean finished; // da li je zakljucena ili ne ako ima bolja rec ubaciti
	
	@ManyToOne
	@JoinColumn(name="company_id")
	private Company company;
	
	@OneToMany(mappedBy = "year")
	private Set<Invoice> invoices;

}
