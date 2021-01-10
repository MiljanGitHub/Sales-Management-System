package sales.management.system.dummyController;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sales.management.system.dummy_dto.Item;
import sales.management.system.dummy_dto.ItemDto;
import sales.management.system.dummy_dto.Unit;

@RestController
@RequestMapping("/")
public class DummyController {
	
	public static final ItemDto dto = new ItemDto();
	
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
		 
	}
	
	@RequestMapping(value = "dummy/items", method = RequestMethod.GET)
	public ItemDto getDto() {
		return dto;
	}
	
	
}
