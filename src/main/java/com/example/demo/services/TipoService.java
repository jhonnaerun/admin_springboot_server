package com.example.demo.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.TipoModel;
import com.example.demo.models.UsuarioModel;
import com.example.demo.repositories.TipoRepository;

@Service
public class TipoService {
	@Autowired
	TipoRepository rolRepository;
	
	public ArrayList<TipoModel> obtenerRoles() {
		return (ArrayList<TipoModel>) rolRepository.findAll();
	}
	
	public TipoModel guardarRol(TipoModel rol) {
		return rolRepository.save(rol);
	}

}
