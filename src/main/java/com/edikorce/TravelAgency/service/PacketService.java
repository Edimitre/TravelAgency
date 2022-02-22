package com.edikorce.TravelAgency.service;

import com.edikorce.TravelAgency.exeption.ContentNotFoundExeption;
import com.edikorce.TravelAgency.model.Packet;
import com.edikorce.TravelAgency.repository.PacketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacketService {
    @Autowired
    private PacketRepository packetRepository;

    public Packet savePacket(Packet packet){
        return packetRepository.save(packet);
    }

    public List<Packet> getAllPackets(){

        List<Packet> allPacketsList = packetRepository.findAll();
        return allPacketsList;
    }

    public List<Packet> saveAllPackets(List<Packet> packetList){

        return packetRepository.saveAll(packetList);
    }

    public Packet getPacketById(Long id) throws ContentNotFoundExeption {
        Optional<Packet> packet = packetRepository.findById(id);

        if (packet.isPresent()){
            return packet.get();
        }else{
            throw new ContentNotFoundExeption("Paketa nuk gjend");
        }
    }

    public void deletePacketById(Long id){

        packetRepository.deleteById(id);
    }

}