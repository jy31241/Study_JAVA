package net.skhu;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesitemRepository extends JpaRepository<Salesitem, Integer> {

	public default List<Salesitem> findAll(Pagination pagination) {
		PageRequest pageRequest = new PageRequest(pagination.getPg() - 1, pagination.getSz(), Sort.Direction.ASC, "id");
		Page<Salesitem> page = this.findAll(pageRequest);
		pagination.setRecordCount((int) page.getTotalElements());
		return page.getContent();
	}
}