package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Response;
import com.example.demo.models.UsuarioModel;
import com.example.demo.services.UsuarioService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired
	UsuarioService usuarioService;
	
	 @GetMapping()
	 public Response obtenerUsuarios(@RequestParam("limite") int limite, @RequestParam("pagina") int pagina) {
		 Response respuesta = new Response();
		 respuesta.setOk(true);
		 Page<UsuarioModel> paginaRepository = this.usuarioService.obtenerUsuarios(pagina - 1, limite);
		 respuesta.setLimite(paginaRepository.getPageable().getPageSize());
		 respuesta.setPagina(pagina);
		 respuesta.setResults(paginaRepository.getContent().size());
		 respuesta.setRegistros(paginaRepository.getTotalElements());
		 respuesta.setUsuarios(paginaRepository.getContent());
		 return respuesta;
	 }
	 
	 
	 @PostMapping()
	 public ResponseEntity<UsuarioModel> guardarUsuario(@RequestBody UsuarioModel usuario) {
		 usuario.setPrimerNombre(usuario.getPrimerNombre().toLowerCase());
		 return new ResponseEntity<>(this.usuarioService.guardarUsuario(usuario), HttpStatus.CREATED) ;
	 }
	 
	 
	 @GetMapping( path = "/{id}")
	 public Optional<UsuarioModel> obtenerById(@PathVariable("id") long id) {
		 return this.usuarioService.usuarioById(id);
	 }
	 
	 @GetMapping( path = "/buscar")
	 public ArrayList<UsuarioModel> obtenerByNombre(@RequestParam("nombre") String nombre) {
		 return this.usuarioService.obtenerPorNombre(nombre);
	 }
	 
	 @PostMapping( path = "/{id}")
	 public String eliminarById(@PathVariable("id") long id) {
		 boolean response = this.usuarioService.eliminarById(id);
		 if(response) {
			 return "Se elimino el usuario con id " + id;
		 } else {
			 return "No se pudo eliminar el usuario";
		 }
	 }
	 

}
