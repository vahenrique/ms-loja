package vahenrique.ms.loja.catalogo.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vahenrique.ms.loja.catalogo.domain.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {

}
