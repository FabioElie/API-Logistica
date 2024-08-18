package br.com.logistics.api.repository;

import br.com.logistics.api.entity.Clientes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientesRepository extends JpaRepository<Clientes, Long> {

    Page<Clientes> findAllByAtivoTrue(Pageable paginacao);



}
