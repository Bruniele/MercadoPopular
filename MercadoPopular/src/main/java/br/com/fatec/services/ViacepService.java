/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.services;

import br.com.fatec.model.Endereco;
import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author sanoj
 */
public class ViacepService {
    public Endereco getEndereco(String cep) throws IOException {
        Endereco endereco = null;

        HttpGet request = new HttpGet("https://viacep.com.br/ws/" + cep + "/json/");

        try ( CloseableHttpClient httpClient = HttpClientBuilder.create().disableRedirectHandling().build();  CloseableHttpResponse response = httpClient.execute(request)) {

            HttpEntity entity = response.getEntity();

            if (entity != null) {
                String result = EntityUtils.toString(entity);

                Gson gson = new Gson();
                endereco = gson.fromJson(result, Endereco.class);
            }
        }
        return endereco;
    }
}
