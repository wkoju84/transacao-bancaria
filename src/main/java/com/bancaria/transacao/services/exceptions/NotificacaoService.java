package com.bancaria.transacao.services.exceptions;

import com.bancaria.transacao.entities.Cliente;
import com.bancaria.transacao.entities.Empresa;
import com.bancaria.transacao.entities.Transacao;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificacaoService {

    private final RestTemplate restTemplate;

    public NotificacaoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void enviarNotificacao(Empresa empresa, Cliente cliente, Transacao transacao) {
        // Implementar lógica de envio de notificação (e-mail, SMS, etc.)

        // Simular envio de callback para a empresa usando webhook.site
        String webhookUrl = "https://webhook.site/2b40ed6d-b5f9-48f6-a131-d050139be7d9";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        String body = String.format("{\"empresaId\": \"%d\", \"clienteId\": \"%d\", " +
                        "\"valor\": \"%s\", \"tipoTransacao\": \"%s\"}",
                empresa.getId(), cliente.getId(), transacao.getValor(), transacao.getTipoTransacao());

        HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(webhookUrl, HttpMethod.POST,
                    requestEntity, String.class);
            System.out.println("Callback enviado com sucesso: " + response.getStatusCode());
        } catch (Exception e) {
            System.err.println("Erro ao enviar callback: " + e.getMessage());
        }
    }
}

