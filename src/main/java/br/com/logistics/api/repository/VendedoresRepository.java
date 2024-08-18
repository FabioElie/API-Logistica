package br.com.logistics.api.repository;

import br.com.logistics.api.entity.Vendedores;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendedoresRepository extends JpaRepository<Vendedores, Long> {

    Page<Vendedores> findAllByAtivoTrue(Pageable paginacao);



}
