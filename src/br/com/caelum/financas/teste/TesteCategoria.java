package br.com.caelum.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.modelo.Categoria;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TesteCategoria {

	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		Categoria categoria = new Categoria();
		categoria.setId(1);

		String jpql = "SELECT m FROM Movimentacao m JOIN m.categorias c WHERE c = :pCategoria";
		
		Query query = em.createQuery(jpql);
		query.setParameter("pCategoria", categoria);

		List<Movimentacao> resultados = query.getResultList();

		for (Movimentacao movimentacao : resultados) {
			System.out.println("Descri��o: " + movimentacao.getDescricao());
			System.out.println("ID: " + movimentacao.getConta().getId());
			System.out.println("Tipo: " + movimentacao.getTipoMovimentacao());
		}

		em.getTransaction().commit();
		em.close();

	}
}