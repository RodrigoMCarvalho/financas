package br.com.caelum.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TesteConsulta {
	
	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		Conta conta = new Conta();
		conta.setId(6);
		
		String jpql = "SELECT m FROM Movimentacao m WHERE m.conta = :pConta " +
		"AND m.tipoMovimentacao = :pTipo " +
		"ORDER BY m.valor DESC";
		
		Query query = em.createQuery(jpql);
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", TipoMovimentacao.SAIDA);
		
		
		List<Movimentacao> resultados = query.getResultList();
		
		for (Movimentacao movimentacao : resultados) {
			System.out.println("Descrição: " + movimentacao.getDescricao());
			System.out.println("ID: " + movimentacao.getConta().getId());
			System.out.println("Tipo: " + movimentacao.getTipoMovimentacao());
		}

		em.getTransaction().commit();
		em.close();

	}
}
