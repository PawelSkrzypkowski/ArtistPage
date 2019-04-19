package pl.skrzypkowski.shop.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.skrzypkowski.shop.service.OrderService;

@Controller
public class AdminOrderController {

	@Autowired
	private OrderService orderService;
	
	@GetMapping("/admin/order")
	public String index(Model model) {
		model.addAttribute("orders", orderService.findAll());
		return "admin/order_list";
	}
	
	@GetMapping("/admin/order/{id}")
	public String show(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("order", orderService.findOne(id));
		return "admin/order_detail";
	}
	
	@GetMapping("/admin/order/{id}/delete")
	public String delete(@PathVariable("id") Integer id, RedirectAttributes redirect) {
		orderService.delete(id);
		
		redirect.addFlashAttribute("success", 
				new StringBuilder().append("Usunięto zamówienie ").append(id).append(" sukces").toString());
		return "redirect:/admin/order";
	}
	
}
