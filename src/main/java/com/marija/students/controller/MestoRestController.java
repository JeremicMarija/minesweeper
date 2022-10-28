package com.marija.students.controller;

import com.marija.students.model.Mesto;
import com.marija.students.service.MestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/mesto")
public class MestoRestController {

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
