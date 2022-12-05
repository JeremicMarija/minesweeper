package com.marija.students.controller;

import com.marija.students.dto.FakultetDto;
import com.marija.students.model.Fakultet;
import com.marija.students.service.FakultetService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/fakultet")
public class FakultetRestController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private final FakultetService fakultetService;

    public FakultetRestController(ModelMapper modelMapper, FakultetService fakultetService) {
        this.modelMapper = modelMapper;
        this.fakultetService = fakultetService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Fakultet>> findAll(){

        return ResponseEntity.status(HttpStatus.OK).body(fakultetService.findAll());

    }

    @GetMapping("/get/{maticniBroj}")
    public ResponseEntity<FakultetDto> get(@PathVariable String maticniBroj){

        Fakultet fakultet = fakultetService.getFakultetById(maticniBroj);
        FakultetDto fakultetResponse = modelMapper.map(fakultet, FakultetDto.class);
        fakultetResponse.setMestoId(fakultet.getMesto().getPtt());
        return ResponseEntity.ok().body(fakultetResponse);

    }

    @PostMapping("/save")
    public ResponseEntity<FakultetDto>createFakultet(@Valid @RequestBody FakultetDto fakultetDto){

        Fakultet fakultet = fakultetService.createFakultet(fakultetDto);
        FakultetDto fakultetResponse = modelMapper.map(fakultet, FakultetDto.class);
        fakultetResponse.setMestoId(fakultetDto.getMestoId());
        System.out.println(fakultetResponse.getMestoId());

        return new ResponseEntity<FakultetDto>(fakultetResponse, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<FakultetDto>updateFakultet(@Valid @RequestBody FakultetDto fakultetDto){

        Fakultet fakultet = fakultetService.updateFakultet(fakultetDto);
        FakultetDto fakultetResponse = modelMapper.map(fakultet, FakultetDto.class);
        fakultetResponse.setMestoId(fakultetDto.getMestoId());
        return new ResponseEntity<FakultetDto>(fakultetResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{maticniBroj}")
    public @ResponseBody ResponseEntity<String>delete(@PathVariable String maticniBroj){
        fakultetService.delete(maticniBroj);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted");
    }

}
