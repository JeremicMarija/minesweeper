package com.marija.students.controller;

import com.marija.students.dto.MestoDto;
import com.marija.students.model.Mesto;
import com.marija.students.service.MestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/mesta")
public class MestoRestController {

    private final MestoService mestoService;

    @Autowired
    public MestoRestController(MestoService mestoService) {
        this.mestoService = mestoService;
    }

    @PostMapping
    public ResponseEntity<MestoDto> addMesto(@RequestBody final MestoDto mestoDto){
        Mesto mesto = mestoService.addMesto(Mesto.from(mestoDto));
        return new ResponseEntity<>(MestoDto.from(mesto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<MestoDto>> getMesta(){
        List<Mesto> mesta = mestoService.getMesta();
        List<MestoDto> mestoDtos = mesta.stream().map(MestoDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(mestoDtos, HttpStatus.OK);
    }

    @GetMapping(value = "{ptt}")
    public ResponseEntity<MestoDto> getMesta(@PathVariable final Long ptt){
        Mesto mesto = mestoService.getMesto(ptt);
        return new ResponseEntity<>(MestoDto.from(mesto), HttpStatus.OK);
    }

    @DeleteMapping(value = "{ptt}")
    public ResponseEntity<MestoDto> deleteMesto(@PathVariable final Long ptt){
        Mesto mesto = mestoService.deleteMesto(ptt);
        return new ResponseEntity<>(MestoDto.from(mesto), HttpStatus.OK);
    }

    @PutMapping(value = "{ptt}")
    public ResponseEntity<MestoDto> editMesto(@PathVariable final Long ptt, @RequestBody final  MestoDto mestoDto){
        Mesto mesto = mestoService.editMesto(ptt,Mesto.from(mestoDto));
        return new ResponseEntity<>(MestoDto.from(mesto), HttpStatus.OK);
    }

    @PostMapping(value = "{mestoId}/fakulteti/{fakultetId}/add")
    public ResponseEntity<MestoDto> addFakultetToMesto(@PathVariable final Long mestoId, @PathVariable final String fakultetId){
        Mesto mesto = mestoService.addFakultetToMesto(mestoId,fakultetId);
        return new ResponseEntity<>(MestoDto.from(mesto), HttpStatus.OK);
    }

    @DeleteMapping(value = "{mestoId}/fakulteti/{fakultetId}/remove")
    public ResponseEntity<MestoDto> removeFakultetFromMesto(@PathVariable final Long mestoId, @PathVariable final String fakultetId){
        Mesto mesto = mestoService.removeFakultetFromMesto(mestoId,fakultetId);
        return new ResponseEntity<>(MestoDto.from(mesto), HttpStatus.OK);
    }






//    @Autowired
//    private final MestoService mestoService;
//
//    public MestoRestController(MestoService mestoService) {
//        this.mestoService = mestoService;
//    }
//
//    @PostMapping()
//    public ResponseEntity<Mesto>createMesto(@RequestBody Mesto mesto){
//        return new ResponseEntity<Mesto>(mestoService.createMesto(mesto),HttpStatus.CREATED);
//    }
//
//    @GetMapping()
//    public ResponseEntity<List<Mesto>>findAll(){
//        return ResponseEntity.status(HttpStatus.OK).body(mestoService.findAll());
//    }
//    @GetMapping("/{ptt}")
//    public ResponseEntity<Mesto>findByID(@PathVariable("ptt") long ptt){
//        return new ResponseEntity<Mesto>(mestoService.findByID(ptt), HttpStatus.OK);
//    }
//
////    @PutMapping()
////    public ResponseEntity<Mesto> updateMesto(@RequestBody MestoDto mestoDto){
////        return new ResponseEntity<Mesto>(mestoService.updateMesto(mestoDto),HttpStatus.OK);
////    }
//    @PutMapping("/{ptt}")
//    public ResponseEntity<Mesto> updateMesto(@PathVariable("ptt") long ptt, @RequestBody Mesto mesto){
//        return new ResponseEntity<Mesto>(mestoService.updateMesto(mesto,ptt),HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{ptt}")
//    public ResponseEntity<HttpStatus> deleteMesto(@PathVariable("ptt") long ptt){
//        mestoService.deleteById(ptt);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

}
