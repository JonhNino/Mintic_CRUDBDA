package com.minticcruddba.app.crontroller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.minticcruddba.app.dao.IClienteDao;
import com.minticcruddba.app.entity.Cliente;

@Controller
@SessionAttributes("cliente")
public class ClienteCrontroller {
	@Autowired
	private IClienteDao clienteDao;

	@RequestMapping(value = { "/resultado" }, method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de Usuarios");
		model.addAttribute("cliente", clienteDao.findall(null));
		return "resultado";
	}

	@RequestMapping(value = { "/eliminar/{id}" })
	public String eliminar(@PathVariable(value = "id") Long id) {
		if (id > 0) {
			clienteDao.delete(id);
		}
		return "redirect:/resultado";
	}

	@GetMapping("/form")
	public String crear(Map<String, Object> model) {
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		model.put("titulo", "Bienvenido formulario cliente");
		return "form";
	}

	@PostMapping("/form")
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status) {
		if (result.hasErrors()) {
			return null;
		} else {
			clienteDao.save(cliente);
			status.setComplete();
			return "redirect:resultado";
		}
	}
	@RequestMapping(value="/form/{id}")
	public String editar(@PathVariable(value="Id")Long Id,Map<String, Object> model) {
		Cliente cliente= null;
		if(Id>0) {
			cliente =clienteDao.findone(Id);
		}else {
			return "redirect:resultado";
		}
		model.put("cliente", cliente);
		return "form";
		
	}
}
