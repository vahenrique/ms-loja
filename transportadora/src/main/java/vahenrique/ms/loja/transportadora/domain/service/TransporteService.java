package vahenrique.ms.loja.transportadora.domain.service;

import java.util.Arrays;
import java.util.UUID;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;
import vahenrique.ms.loja.transportadora.domain.enumeration.StatusPedido;
import vahenrique.ms.loja.transportadora.domain.enumeration.StatusTransporte;
import vahenrique.ms.loja.transportadora.domain.model.Transporte;
import vahenrique.ms.loja.transportadora.domain.repository.TransporteRepository;
import vahenrique.ms.loja.transportadora.domain.vo.PedidoTransporteVo;

@AllArgsConstructor
@Service
public class TransporteService {

	private TransporteRepository transporteRepository;
	private RestTemplate restTemplate;

	@Transactional
	public Transporte save(Transporte transporte) {
		transporte = transporteRepository.save(transporte);
		atualizarPedido(transporte);
		return transporte;
	}

	private void atualizarPedido(Transporte transporte) {
		if (Arrays.asList(StatusTransporte.ENVIADO, StatusTransporte.ENTREGUE).contains(transporte.getStatus())) {
			PedidoTransporteVo pedidoTransporteVo = new PedidoTransporteVo();
			pedidoTransporteVo.setStatus(StatusPedido.valueOf(transporte.getStatus().toString()));
			restTemplate.exchange(("http://LOJA-SERVICE/pedidotransporte/" + transporte.getPedidoId()), HttpMethod.PUT,
					new HttpEntity<PedidoTransporteVo>(pedidoTransporteVo), PedidoTransporteVo.class);
		}
	}

	@Transactional
	public void delete(UUID id) {
		transporteRepository.deleteById(id);
	}

}
