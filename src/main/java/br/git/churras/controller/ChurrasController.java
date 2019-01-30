package br.git.churras.controller;

import br.git.churras.model.Participante;
import br.git.churras.outModel.ParticipanteOut;
import br.git.churras.repository.Participantes;
import jdk.nashorn.internal.ir.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class ChurrasController {

    @Autowired
    Participantes participantesRepository;

    @GetMapping
    public String novoParticipante(Model md){
        md.addAttribute("novoParticipante", new Participante());
        return "index";
    }

    @PostMapping("/novoParticipante")
    public @ResponseBody String salvarNovoParticipante(@RequestBody Participante participante){
        participantesRepository.save(participante);
        return "{\"resposta\" : \"sucess\"}";
    }

    @GetMapping("/listaParticipantes")
    public @ResponseBody List<String> listaParticipantes(){
        List<String> response = new ArrayList<String>();

        for(Participante participante : participantesRepository.findAll()){
            String data = "{";
            data += " \"nomeParticipante\" : "+" \""+participante.getNomeParticipante()+"\", ";
            data += " \"valorPago\" : "+" \""+participante.getValorPago()+"\", ";
            data += " \"bebida\" : "+" \""+participante.getBebida()+"\", ";
            data += " \"pago\" : "+" \""+participante.getPago()+"\", ";
            data += " \"observacao\" : "+" \""+participante.getObservacao()+"\" ";
            data += "}";

            response.add(data);
        }

        return response;
    }

}
