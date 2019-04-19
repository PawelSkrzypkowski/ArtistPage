package pl.skrzypkowski.shop.domain.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import org.hibernate.annotations.Where;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
@Data
public class Cart implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Item> items;

	public Cart() {
        items = new ArrayList<>();
    }

	public int getCount() {
		return items.size();
	}
	
	public int getTotal() {
		int total = 0;
		for (Item e : items) {
			total += e.getSubTotal();
		}
		return total;
	}
	
}