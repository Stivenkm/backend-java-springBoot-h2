/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.Servicio;

import com.example.demo.Modelo.Client;
import com.example.demo.Repositorio.ClientRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author stive
 */
@Service
public class ClientServicio {
    @Autowired
    private ClientRepositorio clientRepositorio;

    public List<Client> getAll() {
        return clientRepositorio.getAll();
    }

    public Optional<Client> getClient(int clientId) {
        return clientRepositorio.getClient(clientId);
    }

    public Client save(Client client) {
        if (client.getIdClient() == null) {
            return clientRepositorio.save(client);
        } else {
            Optional<Client> client1 = clientRepositorio.getClient(client.getIdClient());
            if (client1.isEmpty()) {
                return clientRepositorio.save(client);
            } else {
                return client;
            }
        }
    }

    public boolean deleteClient(int clientId) {
        Boolean d = getClient(clientId).map(client
                -> {
            clientRepositorio.delete(client);
            return true;
        }).orElse(false);
        return d;
    }
}
