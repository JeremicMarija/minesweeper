package com.marija.students.controller;

import com.marija.students.dto.FakultetDto;
import com.marija.students.model.Fakultet;
import org.modelmapper.ModelMapper;
import com.marija.students.service.FakultetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/fakultet")
public class FakultetRestController {

    private FakultetService fakultetService;

    @Autowired
    public FakultetRestController(FakultetService fakultetService) {
        this.fakultetService = fakultetService;
    }

    @PostMapping
    public ResponseEntity<FakultetDto>addFakultet(@RequestBody final FakultetDto fakultetDto){
        Fakultet fakultet = fakultetService.addFakultet(Fakultet.from(fakultetDto));
        return new ResponseEntity<>(FakultetDto.from(fakultet), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<FakultetDto>> getFaklteti(){
        List<Fakultet> fakulteti = fakultetService.getFakulteti();
        List<FakultetDto> fakultetDtos = fakulteti.stream().map(FakultetDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(fakultetDtos, HttpStatus.OK);
    }
    @GetMapping(value = "{id}")
    public ResponseEntity<FakultetDto> getFakultet(@PathVariable final String maticniBroj){
        Fakultet fakultet = fakultetService.getFakultet(maticniBroj);
        return new ResponseEntity<>(FakultetDto.from(fakultet), HttpStatus.OK);
    }



    //OLD CODE
    //    @Autowired
//    private ModelMapper modelMapper;
//
//    @Autowired
//    private FakultetService fakultetService;
//
//    public FakultetRestController(FakultetService fakultetService) {
//        this.fakultetService = fakultetService;
//    }
//
//    @GetMapping()
//    public ResponseEntity<List<Fakultet>> findAll(){
//        return ResponseEntity.status(HttpStatus.OK).body(fakultetService.findAll());
//    }
//    @GetMapping("/{maticni_broj}")
//    public ResponseEntity<Fakultet>findByID(@PathVariable("maticni_broj") String maticni_broj){
//        return new ResponseEntity<Fakultet>(fakultetService.findByID(maticni_broj), HttpStatus.OK);
//    }
//
//    @PostMapping()
//    public ResponseEntity<FakultetDto> createFakultet(@RequestBody FakultetDto fakultetDto){
//        Fakultet fakultet = fakultetService.createFakultet(fakultetDto);
//        FakultetDto fakultetResponse = modelMapper.map(fakultet, FakultetDto.class);
//
//        return new ResponseEntity<FakultetDto>(fakultetResponse, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/{maticniBroj}")
//    public ResponseEntity<Fakultet> updateFakultet(@RequestBody FakultetDto fakultetDto, @PathVariable("maticniBroj") String maticniBroj){
//        return new ResponseEntity<Fakultet>(fakultetService.updateFakultet(fakultetDto, maticniBroj),HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{maticniBroj}")
//    public ResponseEntity<HttpStatus> deleteFakultet(@PathVariable("maticniBroj") String maticniBroj){
//        fakultetService.deleteFakultetByMaticniBroj(maticniBroj);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
}
