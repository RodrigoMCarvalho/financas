package br.com.caelum.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TesteFuncoes {

	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		Conta conta = new Conta();
		conta.setId(2);

		String jpql = "SELECT DISTINCT avg(m.valor) FROM Movimentacao m WHERE m.conta = :pConta "
				+ "AND m.tipoMovimentacao = :pTipo " 
				+ "GROUP BY m.data";
//				+ "ORDER BY m.valor DESC";

		TypedQuery<Double> query = em.createQuery(jpql, Double.class);
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", TipoMovimentacao.SAIDA);
		
		List<Double> medias = query.getResultList();
		
		for (Double media : medias) {
			System.out.println("A média é: " + media);
		}
		
		em.getTransaction().commit();
		em.close();

	}
}
