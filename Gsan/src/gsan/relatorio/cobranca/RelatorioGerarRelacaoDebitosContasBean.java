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
package gsan.relatorio.cobranca;

import gsan.relatorio.RelatorioBean;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * 
 * Bean do [UC0227] Gerar Rela��o de D�bitos 
 *
 * @author Rafael Santos
 * @date 25/05/2006
 */
public class RelatorioGerarRelacaoDebitosContasBean implements RelatorioBean {
	
	private String contaIndicadorContaRevisao;
	private String contaMesAnoReferenciaConta;
	private String contaDataVencimentoConta;
	private String contaValorOriginal;
	private String contaCodigoBarras;
	
	
	private JRBeanCollectionDataSource arrayJR;
	
	private ArrayList arrayRelatorioGerarRelacaoDebitosContasTotalBean;	
	
	
	/**
	 * Construtor de RelatorioGerarRelacaoDebitosContasBean 
	 * 
	 * @param contaIndicadorContaRevisao
	 * @param contaMesAnoReferenciaConta
	 * @param contaDataVencimentoConta
	 * @param contaValorOriginal
	 * @param contaValorTotalOriginal
	 * @param contaCodigoBarras
	 * @param contaValorTotalAtualizado
	 * @param jrColecaoRelatorioGerarRelacaoDebitosTipoDebitoCreditoBean
	 * @param arrayRelatorioGerarRelacaoDebitosTipoDebitoCreditoBean
	 */
	public RelatorioGerarRelacaoDebitosContasBean(String contaIndicadorContaRevisao, String contaMesAnoReferenciaConta, String contaDataVencimentoConta, String contaValorOriginal,String contaCodigoBarras, Collection colecaoGerarRelacaoDebitosContasTotalBean) {
		this.contaIndicadorContaRevisao = contaIndicadorContaRevisao;
		this.contaMesAnoReferenciaConta = contaMesAnoReferenciaConta;
		this.contaDataVencimentoConta = contaDataVencimentoConta;
		this.contaValorOriginal = contaValorOriginal;
		this.contaCodigoBarras = contaCodigoBarras;
		
		this.arrayRelatorioGerarRelacaoDebitosContasTotalBean = new ArrayList();
		this.arrayRelatorioGerarRelacaoDebitosContasTotalBean.addAll(colecaoGerarRelacaoDebitosContasTotalBean);
		
		
		this.arrayJR = new JRBeanCollectionDataSource(this.arrayRelatorioGerarRelacaoDebitosContasTotalBean);
				
		
	}	

	/**
	 * Construtor de RelatorioGerarRelacaoDebitosContasBean 
	 * 
	 */
	public RelatorioGerarRelacaoDebitosContasBean() {
		
	}		
	
	/**
	 * @return Retorna o campo contaCodigoBarras.
	 */
	public String getContaCodigoBarras() {
		return contaCodigoBarras;
	}

	/**
	 * @param contaCodigoBarras O contaCodigoBarras a ser setado.
	 */
	public void setContaCodigoBarras(String contaCodigoBarras) {
		this.contaCodigoBarras = contaCodigoBarras;
	}

	/**
	 * @return Retorna o campo contaDataVencimentoConta.
	 */
	public String getContaDataVencimentoConta() {
		return contaDataVencimentoConta;
	}

	/**
	 * @param contaDataVencimentoConta O contaDataVencimentoConta a ser setado.
	 */
	public void setContaDataVencimentoConta(String contaDataVencimentoConta) {
		this.contaDataVencimentoConta = contaDataVencimentoConta;
	}

	/**
	 * @return Retorna o campo contaIndicadorContaRevisao.
	 */
	public String getContaIndicadorContaRevisao() {
		return contaIndicadorContaRevisao;
	}

	/**
	 * @param contaIndicadorContaRevisao O contaIndicadorContaRevisao a ser setado.
	 */
	public void setContaIndicadorContaRevisao(String contaIndicadorContaRevisao) {
		this.contaIndicadorContaRevisao = contaIndicadorContaRevisao;
	}

	/**
	 * @return Retorna o campo contaMesAnoReferenciaConta.
	 */
	public String getContaMesAnoReferenciaConta() {
		return contaMesAnoReferenciaConta;
	}

	/**
	 * @param contaMesAnoReferenciaConta O contaMesAnoReferenciaConta a ser setado.
	 */
	public void setContaMesAnoReferenciaConta(String contaMesAnoReferenciaConta) {
		this.contaMesAnoReferenciaConta = contaMesAnoReferenciaConta;
	}

	/**
	 * @return Retorna o campo contaValorOriginal.
	 */
	public String getContaValorOriginal() {
		return contaValorOriginal;
	}

	/**
	 * @param contaValorOriginal O contaValorOriginal a ser setado.
	 */
	public void setContaValorOriginal(String contaValorOriginal) {
		this.contaValorOriginal = contaValorOriginal;
	}

	/**
	 * @return Retorna o campo arrayJR.
	 */
	public JRBeanCollectionDataSource getArrayJR() {
		return arrayJR;
	}

	/**
	 * @param arrayJR O arrayJR a ser setado.
	 */
	public void setArrayJR(JRBeanCollectionDataSource arrayJR) {
		this.arrayJR = arrayJR;
	}

	/**
	 * @return Retorna o campo arrayRelatorioGerarRelacaoDebitosContasTotalBean.
	 */
	public ArrayList getArrayRelatorioGerarRelacaoDebitosContasTotalBean() {
		return arrayRelatorioGerarRelacaoDebitosContasTotalBean;
	}

	/**
	 * @param arrayRelatorioGerarRelacaoDebitosContasTotalBean O arrayRelatorioGerarRelacaoDebitosContasTotalBean a ser setado.
	 */
	public void setArrayRelatorioGerarRelacaoDebitosContasTotalBean(
			ArrayList arrayRelatorioGerarRelacaoDebitosContasTotalBean) {
		this.arrayRelatorioGerarRelacaoDebitosContasTotalBean = arrayRelatorioGerarRelacaoDebitosContasTotalBean;
	}

}
