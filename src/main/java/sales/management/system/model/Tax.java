package sales.management.system.model;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Tax")
@Table(name = "tax")
@NoArgsConstructor
@Data
@Setter
@SqlResultSetMappings({ //

@SqlResultSetMapping(name = "findRawTaxValuesPerCommodityGroupMapping",
					classes = {@ConstructorResult(targetClass=sales.management.system.dtoResponse.RawTax.class,
					columns = {@ColumnResult(name="commodityGroupId", type=Integer.class),
							   @ColumnResult(name="tax", type=Double.class),
					  
					})} )
	 })
@NamedNativeQueries(value = {
		
		@NamedNativeQuery(name = "findRawTaxValuesPerCommodityGroup", query = ""
				+ "SELECT cg.commodity_groupe_id AS commodityGroupId, t.percentage AS tax "
				+ "FROM commodity_groupe cg "
				+ "JOIN tax t ON (t.commodity_groupe_id = cg.commodity_groupe_id) "
				+ "WHERE t.tax_id = (SELECT MAX(tax_id) "
				+ "			      	 FROM tax t "
				+ "				     WHERE t.valid_from IN (SELECT MAX(t.valid_from) "
				+ "										    FROM tax t "
				+ "										    WHERE (t.valid_from + 0) <= :requestedTime)) AND cg.commodity_groupe_id = :commodityGroupId"
				, resultSetMapping = "findRawTaxValuesPerCommodityGroupMapping")

	 })
public class Tax {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="tax_id", unique = true, nullable = false)
	private int id;

	@Column(precision = 2,length = 15) // 5 or 15 ?
	private double percentage;

	@Column(nullable = false)
	private String validFrom;
	
	//@OneToMany(mappedBy = "tax")
	@ManyToOne
	@JoinColumn(name="commodity_groupe_id")
	private CommodityGroupe commodityGroup;

}
