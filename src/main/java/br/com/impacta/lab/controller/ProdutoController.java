package br.com.impacta.lab.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	//http://localhost:8080/produtos
	public static List<Produto> bancoDeDados = new ArrayList<>();
	
	@PostMapping("")
	public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto) {
		bancoDeDados.add(produto);
		
		return ResponseEntity.ok(produto);
	}

  @PostMapping("/lote")
	public ResponseEntity<List<Produto>> criarProdutos(@RequestBody List<Produto> produtos) {
    bancoDeDados.addAll(produtos);
		return ResponseEntity.ok(produtos);
	}
	
	@GetMapping("")
	public ResponseEntity<List<Produto>> consultarProdutos() {
		return ResponseEntity.ok(bancoDeDados);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Produto> consultarProdutoPorCodigo(@PathVariable("codigo") int codigo) {
		
		for (int contador = 0; contador < bancoDeDados.size(); contador++) {
			Produto produto = bancoDeDados.get(contador);
			if (produto.getCodigo() == codigo) {
				return ResponseEntity.ok(produto);
			}
		}
		return ResponseEntity.noContent().build();
		
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Produto> alterarStatusDeVida(@PathVariable("codigo") int codigo,
			@RequestBody Produto newProduto) {
		
		for (int contador = 0; contador < bancoDeDados.size(); contador++) {
			Produto produto = bancoDeDados.get(contador);
			if (produto.getCodigo() == codigo) {
				produto.setCodigo(newProduto.getCodigo());
        produto.setDescricao(newProduto.getDescricao());
        produto.setValor(newProduto.getValor());
				
				return ResponseEntity.ok(produto);
			}
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Produto> deletarProduto(@PathVariable("codigo") int id) {
		
		for (int contador = 0; contador < bancoDeDados.size(); contador++) {
			Produto produto = bancoDeDados.get(contador);
			if (produto.getCodigo() == id) {
				bancoDeDados.remove(contador);
				return ResponseEntity.ok(produto);
			}
		}
		return ResponseEntity.notFound().build();
	}
	
}
