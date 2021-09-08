package vahenrique.ms.loja.loja.domain.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;
import vahenrique.ms.loja.loja.domain.enumeration.StatusTransporte;
import vahenrique.ms.loja.loja.domain.vo.TransporteVo;

@AllArgsConstructor
@Service
public class TransportadoraService {

	private RestTemplate restTemplate;

	public void enviar(UUID pedidoId, String enderecoDestinatario, String enderecoRemetente) {
		TransporteVo transporteVo = new TransporteVo();
		transporteVo.setPedidoId(pedidoId);
		transporteVo.setEnderecoDestinatario(enderecoDestinatario);
		transporteVo.setEnderecoRemetente(enderecoRemetente);
		transporteVo.setStatus(StatusTransporte.AGUARDANDO);
		restTemplate.postForObject("http://TRANSPORTADORA-SERVICE/transportes", transporteVo, TransporteVo.class);
	}
}
