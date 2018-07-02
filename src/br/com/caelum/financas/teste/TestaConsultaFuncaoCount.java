package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TestaConsultaFuncaoCount {
	
	public static void main(String[] args) {
		
		EntityManager manager = new JPAUtil().getEntityManager();
	    Conta conta = manager.find(Conta.class, 2);//id 2 deve existir no banco
	    
	    String jpql = "SELECT COUNT(m) FROM Movimentacao m WHERE m.conta = :pConta";
		Query query = manager.createQuery(jpql);
		query.setParameter("pConta", conta);
		Long quantidade = (Long) query.getSingleResult();
		
		System.out.println("Quantidade de movimentações: " + quantidade);
		

	}
}
