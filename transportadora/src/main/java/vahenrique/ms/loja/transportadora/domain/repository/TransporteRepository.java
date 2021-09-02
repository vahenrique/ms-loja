package vahenrique.ms.loja.transportadora.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vahenrique.ms.loja.transportadora.domain.model.Transporte;

@Repository
public interface TransporteRepository extends JpaRepository<Transporte, UUID> {

}
