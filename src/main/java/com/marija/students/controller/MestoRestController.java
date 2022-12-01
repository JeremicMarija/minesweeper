package com.marija.students.controller;

import com.marija.students.model.Mesto;
import com.marija.students.service.MestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/mesto")
public class MestoRestController {

    @Autowired
    private MestoService mestoService;


    public MestoRestController(MestoService mestoService) {
        this.mestoService = mestoService;
    }

    @GetMapping("/all")
    public @ResponseBody ResponseEntity<List<Mesto>> findAll(){

        List<Mesto> mesto = mestoService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(mesto);
    }

    @GetMapping("/get/{ptt}")
    public @ResponseBody ResponseEntity<Mesto> getMesto(@PathVariable Long ptt){

        Optional<Mesto> mesto = mestoService.findByID(ptt);

        if(mesto.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(mesto.get());
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Mesto> save(@Valid @RequestBody Mesto mesto){
        return ResponseEntity.ok(mestoService.createMesto(mesto));
//        return new ResponseEntity<Mesto>(mestoService.createMesto(mesto), HttpStatus.CREATED);
    }

    @PutMapping("/update/{ptt}")
    public ResponseEntity<Mesto> updateMesto(@Valid @PathVariable Long ptt, @RequestBody Mesto mesto){
        return new ResponseEntity<Mesto>(mestoService.updateMesto(mesto, ptt), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{ptt}")
    public @ResponseBody ResponseEntity<String> delete(@PathVariable Long ptt){
        mestoService.delete(ptt);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted");
    }

}
