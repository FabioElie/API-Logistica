package br.com.logistics.api.repository;

import br.com.logistics.api.entity.Material;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<Material, Long> {

    Page<Material> findAllByAtivoTrue(Pageable paginacao);



}
