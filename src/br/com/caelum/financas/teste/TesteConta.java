package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TesteConta {

	public static void main(String[] args) {

		Conta conta = new Conta();
		conta.setId(1);
		conta.setTitular("Rodrigo");
		conta.setBanco("Banco do Brasil");
		conta.setAgencia("123");
		conta.setNumero("456");

		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		em.merge(conta);
		// conta.setBanco("Bradesco");

		// conta = em.find(Conta.class, conta.getId());
		// em.remove(conta);

		em.getTransaction().commit();
		em.close();

		EntityManager em2 = new JPAUtil().getEntityManager();
		em2.getTransaction().begin();

		// conta.setTitular("Gustavo");
		// em2.merge(conta);

		em2.getTransaction().commit();
		em2.close();
	}
}
