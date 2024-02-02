package br.com.logistics.api.repository;

import br.com.logistics.api.entity.Caminhao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CaminhaoRepository extends JpaRepository<Caminhao, Long> {


    Page<Caminhao> findAllByAtivoTrue(Pageable paginacao);
}
