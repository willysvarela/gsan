/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
*
* This file is part of GSAN, an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Ara�jo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cl�udio de Andrade Lira
* Denys Guimar�es Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fab�ola Gomes de Ara�jo
* Fl�vio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento J�nior
* Homero Sampaio Cavalcanti
* Ivan S�rgio da Silva J�nior
* Jos� Edmar de Siqueira
* Jos� Thiago Ten�rio Lopes
* K�ssia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* M�rcio Roberto Batista da Silva
* Maria de F�tima Sampaio Leite
* Micaela Maria Coelho de Ara�jo
* Nelson Mendon�a de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corr�a Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Ara�jo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* S�vio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa � software livre; voc� pode redistribu�-lo e/ou
* modific�-lo sob os termos de Licen�a P�blica Geral GNU, conforme
* publicada pela Free Software Foundation; vers�o 2 da
* Licen�a.
* Este programa � distribu�do na expectativa de ser �til, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia impl�cita de
* COMERCIALIZA��O ou de ADEQUA��O A QUALQUER PROP�SITO EM
* PARTICULAR. Consulte a Licen�a P�blica Geral GNU para obter mais
* detalhes.
* Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU
* junto com este programa; se n�o, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gsan.faturamento.bean;

import java.math.BigDecimal;

/**
 * Classe que ir� auxiliar no formato de entrada do relat�rio 
 * de emitir histograma de esgoto por economia
 *
 * @author Rafael Pinto
 * 
 * @date 07/11/2007
 */
public class EmitirHistogramaEsgotoEconomiaDetalheHelper {
	
	private static final long serialVersionUID = 1L;
	
	private String descricaoFaixa = null;
	private String descricaoSubcategoria = null;
	
	private int economiasMedido = 0;
	private BigDecimal consumoMedioMedido;
	private BigDecimal consumoExcedenteMedido;
	private int volumeConsumoFaixaMedido = 0;
	private int volumeFaturadoFaixaMedido = 0;
	private BigDecimal receitaMedido = new BigDecimal(0.0);
	
	private int economiasNaoMedido = 0;
	private BigDecimal consumoMedioNaoMedido;
	private BigDecimal consumoExcedenteNaoMedido;
	private int volumeConsumoFaixaNaoMedido = 0;
	private int volumeFaturadoFaixaNaoMedido = 0;
	private BigDecimal receitaNaoMedido = new BigDecimal(0.0);
	
	private boolean existeHistograma = true;
	
	private int ligacoesMedido = 0;
	private int ligacoesNaoMedido = 0;
	
	public EmitirHistogramaEsgotoEconomiaDetalheHelper() { }

	public BigDecimal getConsumoExcedenteMedido() {
		return consumoExcedenteMedido;
	}

	public void setConsumoExcedenteMedido(BigDecimal consumoExcedenteMedido) {
		this.consumoExcedenteMedido = consumoExcedenteMedido;
	}

	public BigDecimal getConsumoExcedenteNaoMedido() {
		return consumoExcedenteNaoMedido;
	}

	public void setConsumoExcedenteNaoMedido(BigDecimal consumoExcedenteNaoMedido) {
		this.consumoExcedenteNaoMedido = consumoExcedenteNaoMedido;
	}

	public BigDecimal getConsumoMedioMedido() {
		return consumoMedioMedido;
	}

	public void setConsumoMedioMedido(BigDecimal consumoMedioMedido) {
		this.consumoMedioMedido = consumoMedioMedido;
	}

	public BigDecimal getConsumoMedioNaoMedido() {
		return consumoMedioNaoMedido;
	}

	public void setConsumoMedioNaoMedido(BigDecimal consumoMedioNaoMedido) {
		this.consumoMedioNaoMedido = consumoMedioNaoMedido;
	}

	public String getDescricaoFaixa() {
		return descricaoFaixa;
	}

	public void setDescricaoFaixa(String descricaoFaixa) {
		this.descricaoFaixa = descricaoFaixa;
	}

	public int getEconomiasMedido() {
		return economiasMedido;
	}

	public void setEconomiasMedido(int economiasMedido) {
		this.economiasMedido = economiasMedido;
	}

	public int getEconomiasNaoMedido() {
		return economiasNaoMedido;
	}

	public void setEconomiasNaoMedido(int economiasNaoMedido) {
		this.economiasNaoMedido = economiasNaoMedido;
	}

	public BigDecimal getReceitaMedido() {
		return receitaMedido;
	}

	public void setReceitaMedido(BigDecimal receitaMedido) {
		this.receitaMedido = receitaMedido;
	}

	public BigDecimal getReceitaNaoMedido() {
		return receitaNaoMedido;
	}

	public void setReceitaNaoMedido(BigDecimal receitaNaoMedido) {
		this.receitaNaoMedido = receitaNaoMedido;
	}

	public int getVolumeConsumoFaixaMedido() {
		return volumeConsumoFaixaMedido;
	}

	public void setVolumeConsumoFaixaMedido(int volumeConsumoFaixaMedido) {
		this.volumeConsumoFaixaMedido = volumeConsumoFaixaMedido;
	}

	public int getVolumeConsumoFaixaNaoMedido() {
		return volumeConsumoFaixaNaoMedido;
	}

	public void setVolumeConsumoFaixaNaoMedido(int volumeConsumoFaixaNaoMedido) {
		this.volumeConsumoFaixaNaoMedido = volumeConsumoFaixaNaoMedido;
	}

	public int getVolumeFaturadoFaixaMedido() {
		return volumeFaturadoFaixaMedido;
	}

	public void setVolumeFaturadoFaixaMedido(int volumeFaturadoFaixaMedido) {
		this.volumeFaturadoFaixaMedido = volumeFaturadoFaixaMedido;
	}

	public int getVolumeFaturadoFaixaNaoMedido() {
		return volumeFaturadoFaixaNaoMedido;
	}

	public void setVolumeFaturadoFaixaNaoMedido(int volumeFaturadoFaixaNaoMedido) {
		this.volumeFaturadoFaixaNaoMedido = volumeFaturadoFaixaNaoMedido;
	}

	public boolean isExisteHistograma() {
		return existeHistograma;
	}

	public void setExisteHistograma(boolean existeHistograma) {
		this.existeHistograma = existeHistograma;
	}

	public int getLigacoesMedido() {
		return ligacoesMedido;
	}

	public void setLigacoesMedido(int ligacoesMedido) {
		this.ligacoesMedido = ligacoesMedido;
	}

	public int getLigacoesNaoMedido() {
		return ligacoesNaoMedido;
	}

	public void setLigacoesNaoMedido(int ligacoesNaoMedido) {
		this.ligacoesNaoMedido = ligacoesNaoMedido;
	}
	
	public String getDescricaoSubcategoria() {
		return descricaoSubcategoria;
	}

	public void setDescricaoSubcategoria(String descricaoSubcategoria) {
		this.descricaoSubcategoria = descricaoSubcategoria;
	}
	
	
}