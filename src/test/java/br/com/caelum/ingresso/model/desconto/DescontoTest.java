package br.com.caelum.ingresso.model.desconto;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.descontos.DescontoParaBancos;
import br.com.caelum.ingresso.model.descontos.DescontoParaEstudantes;
import br.com.caelum.ingresso.model.descontos.Ingresso;
import br.com.caelum.ingresso.model.descontos.SemDesconto;

public	class	DescontoTest	{
	private	Sessao	sessao;

	@Before
	public	void	preparaSessoes(){
		Sala	sala	=	new	Sala("Eldorado	-	IMAX",	new	BigDecimal("20.50"));
		Filme	filme	=	new	Filme("Rogue	One",	Duration.ofMinutes(120),
																		"SCI-FI",	new	BigDecimal("12"));
		this.sessao   	=	new	Sessao(LocalTime.parse("10:00:00"),	filme,	sala);

	}
	
	@Test
	public	void	naoDeveConcederDescontoParaIngressoNormal()	{
		Ingresso	ingresso	=	new	Ingresso(sessao,	new	SemDesconto());
		BigDecimal	precoEsperado	=	new	BigDecimal("32.50");
		Assert.assertEquals(precoEsperado,	ingresso.getPreco());
	}
	
	@Test
	public	void	deveConcederDescontoDe30PorcentoParaIngressosDeClientesDeBancos()	{
					Ingresso	ingresso	=	new	Ingresso(sessao,	new	DescontoParaBancos());
					BigDecimal	precoEsperado	=	new	BigDecimal("22.75");
					Assert.assertEquals(precoEsperado,	ingresso.getPreco());
	}
	@Test
	public	void	deveConcederDescontoDe50PorcentoParaIngressoDeEstudante()	{
					Ingresso	ingresso	=	new	Ingresso(sessao,	new	DescontoParaEstudantes());
					BigDecimal	precoEsperado	=	new	BigDecimal("16.25");
					Assert.assertEquals(precoEsperado,	ingresso.getPreco());
	}
}