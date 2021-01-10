package sales.management.system.dummyController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sales.management.system.dummy_dto.BussinessPartner;
import sales.management.system.dummy_dto.Item;
import sales.management.system.dummy_dto.ItemDto;
import sales.management.system.dummy_dto.Unit;

@RestController
@RequestMapping("/")
public class DummyController {
	
	public static final ItemDto dto = new ItemDto();
	public static final List<BussinessPartner> partners = new ArrayList<BussinessPartner>();
	
	@PostConstruct
	public void initData() {
		
		 Unit u1 = new Unit();
		 u1.setName("kilogram"); u1.setAbbreviation("kg"); u1.setId(1);
		 
		 Unit u2 = new Unit();
		 u2.setName("liter"); u2.setAbbreviation("L"); u2.setId(2);
		 
		 Unit u3 = new Unit();
		 u3.setName("meter"); u3.setAbbreviation("m"); u3.setId(3);
		 
		 Item i1 = new Item();
		 i1.setId(1); i1.setDescription("some desc for item 1"); i1.setName("Chocholate"); i1.setUnitPrice(22.3); i1.setUnits(Arrays.asList(u1));
		 
		 Item i2 = new Item();
		 i2.setId(2); i2.setDescription("some desc for item 2"); i2.setName("Milk"); i2.setUnitPrice(22.123); i2.setUnits(Arrays.asList(u2));
		 
		 Item i3 = new Item();
		 i3.setId(3); i3.setDescription("some desc for item 3"); i3.setName("Wire"); i3.setUnitPrice(22.123); i3.setUnits(Arrays.asList(u3));
		 
		 dto.setItems(Arrays.asList(i1, i2, i3)); dto.setError(false); dto.setMessage("asf");
		 
		 BussinessPartner partner1 = new BussinessPartner();
		 partner1.setId(1); partner1.setAddress("Trg Dositeja Obradovica 4"); partner1.setEmail("comapany.it@gmail.com"); partner1.setName("Fakultet Company"); partner1.setPhone("0643752311"); partner1.setTaxNumber("123456678");
		 
		 partners.add(partner1);
	}
	
	@RequestMapping(value = "dummy/items", method = RequestMethod.GET)
	public ItemDto getDto() {
		return dto;
	}
	
	@RequestMapping(value = "dummy/partners", method = RequestMethod.GET)
	public List<BussinessPartner> getPartners() {
		return partners;
	}
	
	@RequestMapping(value = "dummy/partner", method = RequestMethod.POST)
	public BussinessPartner addPartner(@RequestBody BussinessPartner newPartenr) {
		//save to database
		int id = partners.stream()
			      .mapToInt(m -> m.getId())
			      .max().orElseThrow(NoSuchElementException::new);
		
		newPartenr.setId(id);
		partners.add(newPartenr);
		System.out.println(partners.size());
		return newPartenr;
	}
	
}
