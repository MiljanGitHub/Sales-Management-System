package sales.management.system.dtoResponse;

import lombok.Getter;
import lombok.Setter;
import sales.management.system.model.CommodityGroupe;
import sales.management.system.model.Company;
import sales.management.system.model.Tax;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class GroupDto {

    private int id;
    private String name;
    private Company company;
    private List<TaxDto> taxes;

    public GroupDto(CommodityGroupe commodityGroupe,List<TaxDto> taxDtos){
        this.id=commodityGroupe.getId();
        this.name=commodityGroupe.getName();
        this.company=commodityGroupe.getCompany();
        this.taxes=taxDtos;

    }

//    private List<TaxDto> convertTaxes(Set<Tax> taxes){
//        List<TaxDto> taxDtos=new ArrayList<>();
//        for (Tax tax:taxes) {
//            TaxDto taxDto=new TaxDto(tax);
//            taxDtos.add(taxDto);
//        }
//
//        return taxDtos;
//    }

}
