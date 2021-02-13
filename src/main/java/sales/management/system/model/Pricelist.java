package sales.management.system.model;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Pricelist")
@Table(name = "pricelist")
@NoArgsConstructor
@Getter
@Setter
@SqlResultSetMappings({ //

	@SqlResultSetMapping(name = "findPricelistItemDetailsMapping",
			classes = {@ConstructorResult(targetClass=sales.management.system.dtoResponse.PricelistDetailDto.class,
			columns = {@ColumnResult(name="item", type=String.class),
					   @ColumnResult(name="description", type=String.class),
					   @ColumnResult(name="goods", type=Boolean.class), 
					   @ColumnResult(name="unitPrice", type=Double.class),
					   @ColumnResult(name="unitName", type=String.class),
					   @ColumnResult(name="unitShortName", type=String.class),
					   @ColumnResult(name="commodityId", type=Integer.class),
					   @ColumnResult(name="commodityGroup", type=String.class),
					   @ColumnResult(name="commodityGroupId", type=Integer.class),

			})} )
	 })
@NamedNativeQueries(value = {
		
		@NamedNativeQuery(name = "findPricelistItemDetails", query = ""
				+ "SELECT comm.name AS item, comm.description AS description, comm.goods AS goods, pli.price AS unitPrice, u.name AS unitName, u.short_name AS unitShortName, cg.commodity_groupe_id AS commodityId, cg.name AS commodityGroup, cg.commodity_groupe_id AS commodityGroupId "
				+ "FROM commodity comm "
				+ "JOIN pricelist_item pli ON (comm.commodity_id = pli.commodity_id) "
				+ "JOIN unit u ON (comm.unit_id = u.unit_id) "
				+ "JOIN commodity_groupe cg ON (cg.commodity_groupe_id = comm.commodity_group_id) "
				+ "WHERE pli.pricelistitem_id IN (SELECT plip.pricelist_item_id "
				+ "								  FROM pricelist_item_pricelist plip "
				+ "								  WHERE plip.pricelist_id = :pricelistId)"
				, resultSetMapping = "findPricelistItemDetailsMapping")

	 })
public class Pricelist {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pricelist_id", unique = true, nullable = false)
	private int id;

	@Column(nullable = false)
	private String validFrom;

	@ManyToOne
	@JoinColumn(name="company_id")
	private Company company;
	
//	@OneToMany(mappedBy = "commodity")
//	private Set<PricelistItem> priceListItems;
	
	
	@ManyToMany(mappedBy = "pricelists",cascade = CascadeType.ALL)
	Set<PricelistItem> pricelistItems = new HashSet<>();
	

}
