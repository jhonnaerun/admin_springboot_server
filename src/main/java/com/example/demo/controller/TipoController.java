package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.TipoModel;
import com.example.demo.models.UsuarioModel;
import com.example.demo.services.TipoService;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/tipo_documento")
public class TipoController {
	@Autowired
	TipoService rolService;
	
	 @GetMapping()
	 public ArrayList<TipoModel> obtenerRoles() {
		 return rolService.obtenerRoles();
	 }
	 
	 @PostMapping()
	 public TipoModel guardarRol(@RequestBody TipoModel rol) {
		 rol.setNombre(rol.getNombre().toUpperCase());
		 return rolService.guardarRol(rol);
	 }

}
