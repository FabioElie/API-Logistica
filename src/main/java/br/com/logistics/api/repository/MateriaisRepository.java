package br.com.logistics.api.repository;

import br.com.logistics.api.entity.Materiais;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MateriaisRepository extends JpaRepository<Materiais, Long> {

    Page<Materiais> findAllByAtivoTrue(Pageable paginacao);



}
