package com.noweldecor.sapins.controller;

import com.noweldecor.sapins.entity.BonDeCommande;
import com.noweldecor.sapins.entity.Client;
import com.noweldecor.sapins.entity.Decoration;
import com.noweldecor.sapins.entity.Sapin;
import com.noweldecor.sapins.repository.BonDeCommandeRepository;
import com.noweldecor.sapins.repository.ClientRepository;
import com.noweldecor.sapins.repository.DecorationRepository;
import com.noweldecor.sapins.repository.SapinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("sapin")
public class SapinController {

    @Autowired
    private SapinRepository sapinRepository;

    @Autowired
    private DecorationRepository decorationRepository;

    @Autowired
    private BonDeCommandeRepository bonDeCommandeRepository;

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/get")
    public Sapin getSapin(@RequestParam Long id) {
        return sapinRepository.findById(id).orElse(null);
    }

    @PostMapping("/create")
    public long createSapin() {
        sapinRepository.save(new Sapin());
        return sapinRepository.count();
    }

    @PostMapping("/addDecoration")
    public boolean addDecoration(@RequestParam Long sapinId, @RequestParam Long decorationId) {
        Optional<Sapin> sapinOptional = sapinRepository.findById(sapinId);
        Optional<Decoration> decorationOptional = decorationRepository.findById(decorationId);

        if (sapinOptional.isEmpty() || decorationOptional.isEmpty()) {
            return false;
        }

        Sapin sapin = sapinOptional.get();
        if (sapin.isVendu()) {
            return false;
        }

        sapin.getDecorations().add(decorationOptional.get());
        sapinRepository.save(sapin);
        return true;
    }

    @PostMapping("/vente")
    public BonDeCommande vendreSapin(@RequestParam Long sapinId, @RequestParam Long clientId) {
        Optional<Sapin> sapinOptional = sapinRepository.findById(sapinId);
        Optional<Client> clientOptional = clientRepository.findById(clientId);

        if (sapinOptional.isEmpty() || clientOptional.isEmpty()) {
            return null;
        }

        Sapin sapin = sapinOptional.get();
        if (sapin.isVendu()) {
            return null;
        }

        sapin.setVendu(true);
        sapinRepository.save(sapin);

        Client client = clientOptional.get();
        int coutTotal = sapin.getDecorations().stream().mapToInt(Decoration::getPrixEnCentime).sum();
        int poidsTotal = sapin.getDecorations().stream().mapToInt(Decoration::getPoidsEnGram).sum();
        client.setPointsFidelite(client.getPointsFidelite() + (coutTotal / 1000)); // 10% arrondi à l'entier inférieur
        clientRepository.save(client);

        BonDeCommande bonDeCommande = BonDeCommande.builder()
                .sapin(sapin)
                .client(client)
                .adresse("Paul Claverie, rue du Bonjour")
                .coutTotal(coutTotal)
                .poidsTotal(poidsTotal)
                .build();

        return bonDeCommandeRepository.save(bonDeCommande);
    }

    @GetMapping("/commande/get")
    public BonDeCommande getCommande(@RequestParam Long id) {
        return bonDeCommandeRepository.findById(id).orElse(null);
    }
}
