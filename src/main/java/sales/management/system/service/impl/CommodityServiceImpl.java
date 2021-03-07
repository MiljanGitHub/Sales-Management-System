package sales.management.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sales.management.system.dtoRequest.NewCommodityDto;
import sales.management.system.model.*;
import sales.management.system.repository.*;
import sales.management.system.service.CommodityService;
import sales.management.system.service.CompanyService;

@Service
public class CommodityServiceImpl implements CommodityService{

    @Autowired
    private CommodityRepository commodityRepository;

    @Autowired
    private CommodityGroupeRepository commodityGroupeRepository;

    @Autowired
    private TaxRepository taxRepository;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UnitRepository unitRepository;

    @Override
    public List<Commodity> getAllCommodities() {
        return commodityRepository.findAll();
    }

	@Override
	public Commodity findById(int commodityId) {
		return commodityRepository.getOne(commodityId);
	}

    @Override
    public List<CommodityGroupe> getAllGroups() {
        return commodityGroupeRepository.findAll();
    }

    @Override
    public CommodityGroupe addNewCommodityGroup(String name) {
        CommodityGroupe commodityGroupe=new CommodityGroupe();
        commodityGroupe.setName(name);
        return commodityGroupeRepository.save(commodityGroupe);
    }

    @Override
    public Tax AddNewTaxToGroup(int groupId, double percentage, String validFrom) {
        CommodityGroupe commodityGroupe=commodityGroupeRepository.findById(groupId).get();
        Tax tax=new Tax();
        tax.setCommodityGroup(commodityGroupe);
        tax.setPercentage(percentage);
        tax.setValidFrom(validFrom);
        return taxRepository.save(tax);
    }

    @Override
    public Commodity addNewCommodity(NewCommodityDto newCommodityDto) {
        Commodity commodity=new Commodity();
        commodity.setGoods(newCommodityDto.isGoods());
        commodity.setName(newCommodityDto.getName());
        commodity.setDescription(newCommodityDto.getDescription());
        CommodityGroupe commodityGroupe=commodityGroupeRepository.findById(newCommodityDto.getGroupId()).get();
        Unit unit = unitRepository.findById(newCommodityDto.getUnitId()).get();
        Company company = companyService.findById(1);

        commodity.setCompany(company);
        commodity.setUnit(unit);

        commodity.setCommodityGroupe(commodityGroupe);
//        company.getCommodities().add(commodity);
//        companyRepository.save(company);

        return   commodityRepository.save(commodity);
    }

    @Override
    public List<Unit> getUnits() {
        return unitRepository.findAll();
    }
}
