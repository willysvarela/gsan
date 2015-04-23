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

import gsan.faturamento.debito.DebitoACobrarHistorico;
import gsan.util.Util;



/**
 * [CRC:1710] - Bot�es de imprimir nas abas de Consultar Imovel.<br/><br/>
 * 
 * Classe que servir� para exibir os dados dos Debitos Autom�ticos
 * no RelatorioDadosComplementaresImovel.
 * OBS: Pode ser utilizada por qualquer outro relatorio tambem de modo
 * que n�o mude o que j� existe.
 *
 * @author Marlon Patrick
 * @since 23/09/2009
 */
public class DebitoACobrarHistoricoRelatoriosHelper {
	
	public DebitoACobrarHistoricoRelatoriosHelper() {
	}
	
	public DebitoACobrarHistoricoRelatoriosHelper(DebitoACobrarHistorico c) {
		this.debitoCobrarHistorico = c;
	}

	private DebitoACobrarHistorico debitoCobrarHistorico;

	public DebitoACobrarHistorico getDebitoCobrarHistorico() {
		return debitoCobrarHistorico;
	}

	public void setDebitoCobrarHistorico(DebitoACobrarHistorico c) {
		this.debitoCobrarHistorico = c;
	}
	
	public String getDescricaoTipoDebito(){
		if(this.debitoCobrarHistorico!=null && this.debitoCobrarHistorico.getDebitoTipo()!=null){
			return this.debitoCobrarHistorico.getDebitoTipo().getDescricao();
		}
		
		return "";
	}

	public String getAnoMesReferenciaDebito(){
		if(this.debitoCobrarHistorico!=null){
			if (this.debitoCobrarHistorico.getAnoMesReferenciaDebito() != null &&
					!this.debitoCobrarHistorico.getAnoMesReferenciaDebito().equals("")) {
				return Util.formatarAnoMesParaMesAno(this.debitoCobrarHistorico.getAnoMesReferenciaDebito());
			}
		}
		
		return "";
	}

	public String getAnoMesCobrancaDebito(){
		if(this.debitoCobrarHistorico!=null && this.debitoCobrarHistorico.getAnoMesCobrancaDebito() != null){
			return Util.formatarAnoMesParaMesAno(this.debitoCobrarHistorico.getAnoMesCobrancaDebito());
		}
		
		return "";
	}

	public Short getPrestacaoCobradas(){
		if(this.debitoCobrarHistorico!=null){
			return this.debitoCobrarHistorico.getPrestacaoCobradas();
		}
		
		return null;
	}

	public Short getPrestacaoDebito(){
		if(this.debitoCobrarHistorico!=null){
			return this.debitoCobrarHistorico.getPrestacaoDebito();
		}
		
		return null;
	}

	public Short getNumeroParcelaBonus(){
		if(this.debitoCobrarHistorico!=null){
			return this.debitoCobrarHistorico.getNumeroParcelaBonus();
		}
		
		return null;
	}

	public BigDecimal getValorDebito(){
		if(this.debitoCobrarHistorico!=null){
			return this.debitoCobrarHistorico.getValorDebito();
		}
		
		return null;
	}
	
	public String getDescricaoAbreviadaCreditoSituacaoAtual(){
		if(this.debitoCobrarHistorico!=null && this.debitoCobrarHistorico.getDebitoCreditoSituacaoAtual()!=null){
			return this.debitoCobrarHistorico.getDebitoCreditoSituacaoAtual().getDescricaoAbreviada();
		}
		
		return "";
	}

}
