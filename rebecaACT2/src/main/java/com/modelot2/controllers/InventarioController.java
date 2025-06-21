package com.modelot2.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.modelot2.models.Inventario;

import com.modelot2.repositories.IInventarioRepository;
import com.modelot2.repositories.IProductoRepository;
import com.modelot2.utils.Alert;

@Controller
@RequestMapping("/inventarios")
public class InventarioController {
	

	@Autowired
	private IInventarioRepository _iInventarioRepository;	
	
	@Autowired
	private IProductoRepository _iIProductoRepository;

	
	// Queremos obtener una vista, poner la 2 opcion de auto
	
	
	@GetMapping("/listadoYllanes")
	public String listado(Model model) {
		List<Inventario> lstInventario = _iInventarioRepository.findAllByOrderByIdInventarioDesc();
		model.addAttribute("lstInventario", lstInventario);
		return "inventarios/listadoYllanes";
	}
	
	//model de ui
		@GetMapping("/nuevoYllanes")
		public String nuevo(Model model) {
			
			model.addAttribute("lstProductos",_iIProductoRepository.findAllByOrderByNombre());
			
			Inventario inventario = new Inventario();
			inventario.setFechaVen(LocalDate.now());
			model.addAttribute("inventario", inventario);
			
			return "inventarios/nuevoYllanes";
		}
		
		@PostMapping("/registrar")
		public String registrar(@ModelAttribute Inventario inventario, Model model, RedirectAttributes flash) {
			
			model.addAttribute("lstProductos",_iIProductoRepository.findAllByOrderByNombre());
			
			Inventario registrado = _iInventarioRepository.save(inventario);
			
			String mensaje = Alert.sweetAlertSuccess("Inventario con código " + registrado.getIdInventario() + " registrado");
			
			flash.addFlashAttribute("alert",mensaje);

			return "redirect:/inventarios/listadoYllanes";
		}
	
		@GetMapping("/edicionYllanes/{id}")
		public String edicion(@PathVariable Integer id, Model model) {
			
			model.addAttribute("lstProductos",_iIProductoRepository.findAllByOrderByNombre());
			
			Inventario inventario = _iInventarioRepository.findById(id).orElseThrow();
			model.addAttribute("inventario", inventario);
			
			return "inventarios/edicionYllanes";
		}
	
		@PostMapping("/guardar")
		public String guardar(@ModelAttribute Inventario inventario, Model model, RedirectAttributes flash) {
			
			model.addAttribute("lstProductos",_iIProductoRepository.findAllByOrderByNombre());
			
			Inventario registrado = _iInventarioRepository.save(inventario);

			String mensaje = Alert.sweetAlertSuccess("Producto con código " + registrado.getIdInventario() + " actualizado");
			
			flash.addFlashAttribute("alert",mensaje);
			
			
			return "redirect:/inventarios/listadoYllanes";
		}
	
		@GetMapping("/eliminarYllanes/{id}")
		public String eliminar(@PathVariable Integer id, Model model) {
		    Inventario inventario = _iInventarioRepository.findById(id).orElseThrow();
		    model.addAttribute("inventario", inventario);
		    return "inventarios/eliminarYllanes";
		}

		@PostMapping("/eliminar/{id}")
		public String eliminarInventario(@PathVariable Integer id, RedirectAttributes flash) {
		    _iInventarioRepository.deleteById(id);
		    String mensaje = Alert.sweetAlertSuccess("Inventario con código " + id + " eliminado");
		    flash.addFlashAttribute("alert", mensaje);
		    return "redirect:/inventarios/listadoYllanes";
		}

	
}
