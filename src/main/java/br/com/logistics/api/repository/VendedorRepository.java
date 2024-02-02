package br.com.logistics.api.repository;

import br.com.logistics.api.entity.Vendedor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendedorRepository extends JpaRepository<Vendedor, Long> {

    Page<Vendedor> findAllByAtivoTrue(Pageable paginacao);



}
