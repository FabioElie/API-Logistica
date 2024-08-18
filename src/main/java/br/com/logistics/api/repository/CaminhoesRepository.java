package br.com.logistics.api.repository;

import br.com.logistics.api.entity.Caminhoes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CaminhoesRepository extends JpaRepository<Caminhoes, Long> {


    Page<Caminhoes> findAllByAtivoTrue(Pageable paginacao);
}
