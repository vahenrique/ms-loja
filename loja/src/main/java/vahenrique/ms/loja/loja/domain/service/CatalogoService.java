package vahenrique.ms.loja.loja.domain.service;

import java.util.List;
import java.util.UUID;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;
import vahenrique.ms.loja.loja.domain.vo.CatalogoItemVo;

@AllArgsConstructor
@Service
public class CatalogoService {

	private RestTemplate restTemplate;

	public List<CatalogoItemVo> getItensDoCatalogo() {
		return restTemplate.exchange("http://CATALOGO-SERVICE/items", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<CatalogoItemVo>>() {
				}).getBody();
	}

	public CatalogoItemVo getItemDoCatalogo(UUID catalogoItemId) {
		return restTemplate.exchange("http://CATALOGO-SERVICE/items/" + catalogoItemId, HttpMethod.GET, null,
				new ParameterizedTypeReference<CatalogoItemVo>() {
				}).getBody();
	}
}
