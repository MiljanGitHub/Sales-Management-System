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
import javax.persistence.JoinTable;
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
import lombok.experimental.SuperBuilder;


@SqlResultSetMappings({ //


	@SqlResultSetMapping(name = "findPricelistItemsMapping",
			classes = {@ConstructorResult(targetClass=sales.management.system.dtoResponse.RawPricelistItem.class,
			columns = {@ColumnResult(name="commodityId", type=Integer.class),
					   @ColumnResult(name="commodityName", type=String.class),
					   @ColumnResult(name="commodityDescription", type=String.class), 
					   @ColumnResult(name="commodityGroupId", type=Integer.class),
					   @ColumnResult(name="unitId", type=Integer.class),
					   @ColumnResult(name="unitShortName", type=String.class),
					   @ColumnResult(name="unitLongName", type=String.class),
					   @ColumnResult(name="price", type=Double.class),
			})} )
	 })
@NamedNativeQueries(value = {
		
		@NamedNativeQuery(name = "findPricelistItems", query = ""
				+ " SELECT commodity.commodity_id AS commodityId, commodity.name AS commodityName, commodity.description AS commodityDescription, commodity.commodity_group_id AS commodityGroupId, u.unit_id AS unitId, u.short_name AS unitShortName, u.name AS unitLongName, pricelistItem.price AS price "
				+ " FROM pricelist_item_pricelist pricelist_pricelistItem "
				+ " JOIN pricelist_item pricelistItem ON (pricelist_pricelistItem.pricelist_item_id = pricelistItem.pricelistitem_id) "
				+ " JOIN commodity commodity ON (pricelistItem.commodity_id = commodity.commodity_id) "
				+ " JOIN unit u ON (pricelistItem.unit_id = u.unit_id) "
				+ " WHERE pricelist_pricelistItem.pricelist_id = (SELECT MAX(pricelist_id) "
				+ " 								              FROM pricelist cenovnik "
				+ " 											  WHERE cenovnik.valid_from IN (SELECT MAX(cenovnik.valid_from) "
				+ "							  													FROM pricelist cenovnik "
				+ "							  													WHERE (cenovnik.valid_from + 0) <= :requestedTime) "
				+ ") "
				, resultSetMapping = "findPricelistItemsMapping")

	 })
@Entity(name = "PricelistItem")
@Table(name = "pricelist_item")
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class PricelistItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pricelistitem_id", unique = true, nullable = false)
	private int id;

	@Column(precision = 2,nullable = false)
	private double price;

	@ManyToOne
	@JoinColumn(name="commodity_id")
	private Commodity commodity;
	
	@ManyToOne
	@JoinColumn(name="unit_id")
	private Unit unit;
	
	@ManyToMany(cascade = { CascadeType.ALL })
	    @JoinTable(
	        name = "pricelistItem_pricelist",
	        joinColumns = { @JoinColumn(name = "pricelistItem_id") },
	        inverseJoinColumns = { @JoinColumn(name = "pricelist_id") }
	    )
	Set<Pricelist> pricelists=new HashSet<>();

}
