package sales.management.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sales.management.system.controller.impl.BussinesPartnerControllerImpl;
import sales.management.system.dtoResponse.Partner;

@Controller
@RequestMapping("bussinesPartner/")
public class BussinesPartnerController {
	
	@Autowired
	private BussinesPartnerControllerImpl bussinesPartnerContollerImpl;
	
	@GetMapping(value = "")
	@ResponseBody
	public List<Partner> findAll(){
		
		List<Partner> response = bussinesPartnerContollerImpl.findAll();
		
		return response;
		
	}
	
	@PostMapping(value = "add")
	@ResponseBody
	public Partner addPartner(@RequestBody Partner newPartner){
		
		Partner response = bussinesPartnerContollerImpl.addPartner(newPartner);
		
		return response;
		
	}

}
