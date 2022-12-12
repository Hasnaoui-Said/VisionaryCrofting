package org.vc.visionarycroftingmvc.util;

import org.vc.visionarycroftingmvc.models.entity.Client;
import org.vc.visionarycroftingmvc.voDTO.ClientDto;

public class TransfertDto {
    public static ClientDto clientToDto(Client client){
        ClientDto clientDto = new ClientDto();
        clientDto.setEmail(client.getEmail());
        clientDto.setPassword(client.getPassword());
        clientDto.setPhone(client.getPhone());
        return clientDto;
    }
    public static Client clientDtoToCleint(ClientDto clientDto){
        Client client = new Client();
        client.setEmail(clientDto.getEmail());
        client.setPassword(clientDto.getPassword());
        client.setPhone(clientDto.getPhone());
        return client;
    }
}
